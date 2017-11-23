/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.eclipse.mdm.api.base.core.ChildrenStore;
import org.eclipse.mdm.api.base.core.Core;
import org.eclipse.mdm.api.base.core.EntityStore;

/**
 * Implementation of an abstract entity factory which creates new entities.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public abstract class BaseEntityFactory {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link Channel}. The name of the returned {@code Channel}
	 * is retrieved from given {@link Quantity}.
	 *
	 * @param measurement
	 *            The parent {@link Measurement}.
	 * @param quantity
	 *            The {@code Quantity} is used for default initialization.
	 * @return The created {@code Channel} is returned.
	 */
	public Channel createChannel(Measurement measurement, Quantity quantity) {
		return createChannel(quantity.getDefaultChannelName(), measurement, quantity);
	}

	/**
	 * Creates a new {@link Channel}.
	 *
	 * @param name
	 *            Name of the created {@code Channel}.
	 * @param measurement
	 *            The parent {@link Measurement}.
	 * @param quantity
	 *            The {@code Quantity} is used for default initialization.
	 * @return The created {@code Channel} is returned.
	 */
	public Channel createChannel(String name, Measurement measurement, Quantity quantity) {
		Channel channel = new Channel(createCore(Channel.class));

		// relations
		getCore(channel).getPermanentStore().set(measurement);
		getCore(measurement).getChildrenStore().add(channel);
		getCore(channel).getMutableStore().set(quantity.getDefaultUnit());
		getCore(channel).getMutableStore().set(quantity);

		// properties
		channel.setName(name);
		channel.setDescription(quantity.getDescription());
		channel.setInterpolation(Interpolation.NONE);
		channel.setScalarType(quantity.getDefaultScalarType());
		channel.setRank(quantity.getDefaultRank());
		channel.setTypeSize(quantity.getDefaultTypeSize());

		return channel;
	}

	/**
	 * Creates a new {@link ChannelGroup}.
	 *
	 * @param name
	 *            Name of the created {@code ChannelGroup}.
	 * @param numberOfValues
	 *            The number of values per each related {@link Channel}.
	 * @param measurement
	 *            The parent {@link Measurement}.
	 * @return The created {@code ChannelGroup} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if numberOfValues is negative.
	 */
	public ChannelGroup createChannelGroup(String name, int numberOfValues, Measurement measurement) {
		if (numberOfValues < 0) {
			throw new IllegalArgumentException("Number of values must be equal or greater than 0.");
		}

		ChannelGroup channelGroup = new ChannelGroup(createCore(ChannelGroup.class));

		// relations
		getCore(channelGroup).getPermanentStore().set(measurement);
		getCore(measurement).getChildrenStore().add(channelGroup);

		// properties
		channelGroup.setName(name);
		channelGroup.setNumberOfValues(Integer.valueOf(numberOfValues));

		return channelGroup;
	}

	/**
	 * Creates a new {@link Measurement}.
	 *
	 * @param name
	 *            Name of the created {@code Measurement}.
	 * @param testStep
	 *            The parent {@link TestStep}.
	 * @param contextRoots
	 *            {@link ContextRoot}s containing the descriptive data.
	 * @return The created {@code Measurement} is returned.
	 */
	public Measurement createMeasurement(String name, TestStep testStep, ContextRoot... contextRoots) {
		Measurement measurement = new Measurement(createCore(Measurement.class));

		// relations
		getCore(measurement).getPermanentStore().set(testStep);
		getCore(testStep).getChildrenStore().add(measurement);
		for (ContextRoot contextRoot : contextRoots) {
			getCore(measurement).getMutableStore().set(contextRoot, contextRoot.getContextType());
		}

		// properties
		measurement.setName(name);
		measurement.setDateCreated(LocalDateTime.now());

		return measurement;
	}

	/**
	 * Creates a new {@link Parameter} with initialized with given value.
	 *
	 * @param name
	 *            Name of the created {@code Parameter}.
	 * @param value
	 *            The value of the created {@code Parameter}.
	 * @param unit
	 *            An optionally related {@link Unit}.
	 * @param parameterSet
	 *            The parent {@link ParameterSet}.
	 * @return The created {@code Parameter} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if the {@code ParameterSet} already contains a
	 *             {@code Parameter} with given name.
	 * @see Parameter#setObjectValue(Object, Unit)
	 */
	public Parameter createParameter(String name, Object value, Unit unit, ParameterSet parameterSet) {
		if (parameterSet.getParameter(name).isPresent()) {
			throw new IllegalArgumentException("Parameter with name '" + name + "' already exists.");
		}

		Parameter parameter = new Parameter(createCore(Parameter.class));

		// relations
		getCore(parameter).getPermanentStore().set(parameterSet);
		getCore(parameterSet).getChildrenStore().add(parameter);

		// properties
		parameter.setName(name);
		parameter.setObjectValue(value, unit);

		return parameter;
	}

	/**
	 * Creates a new {@link ParameterSet} for given {@link Measurement}.
	 *
	 * @param name
	 *            Name of the created {@code ParameterSet}.
	 * @param version
	 *            Version of the created {@code ParameterSet}.
	 * @param measurement
	 *            The owning {@code Measurement}.
	 * @return The created {@code ParameterSet} is returned.
	 */
	public ParameterSet createParameterSet(String name, String version, Measurement measurement) {
		ParameterSet parameterSet = new ParameterSet(createCore(ParameterSet.class));

		// relations
		getCore(parameterSet).getPermanentStore().set(measurement);
		getCore(measurement).getChildrenStore().add(parameterSet);

		// properties
		parameterSet.setName(name);
		parameterSet.setVersion(version);

		return parameterSet;
	}

	/**
	 * Creates a new {@link ParameterSet} for given {@link Channel}.
	 *
	 * @param name
	 *            Name of the created {@code ParameterSet}.
	 * @param version
	 *            Version of the created {@code ParameterSet}.
	 * @param channel
	 *            The owning {@code Channel}.
	 * @return The created {@code ParameterSet} is returned.
	 */
	public ParameterSet createParameterSet(String name, String version, Channel channel) {
		ParameterSet parameterSet = new ParameterSet(createCore(ParameterSet.class));

		// relations
		getCore(parameterSet).getPermanentStore().set(channel);
		getCore(channel).getChildrenStore().add(parameterSet);

		// properties
		parameterSet.setName(name);
		parameterSet.setVersion(version);

		return parameterSet;
	}

	/**
	 * Creates a new {@link PhysicalDimension}.
	 *
	 * @param name
	 *            Name of the created {@code PhysicalDimension}.
	 * @return The created {@code PhysicalDimension} is returned.
	 */
	public PhysicalDimension createPhysicalDimension(String name) {
		PhysicalDimension physicalDimension = new PhysicalDimension(createCore(PhysicalDimension.class));

		// properties
		physicalDimension.setName(name);
		physicalDimension.setLength(Integer.valueOf(0));
		physicalDimension.setMass(Integer.valueOf(0));
		physicalDimension.setTime(Integer.valueOf(0));
		physicalDimension.setTemperature(Integer.valueOf(0));
		physicalDimension.setCurrent(Integer.valueOf(0));
		physicalDimension.setMolarAmount(Integer.valueOf(0));
		physicalDimension.setLuminousIntensity(Integer.valueOf(0));
		physicalDimension.setAngle(Integer.valueOf(0));

		return physicalDimension;
	}

	/**
	 * Creates a new {@link Quantity}.
	 *
	 * @param name
	 *            Name of the created {@code Quantity}.
	 * @param defaultUnit
	 *            The default {@link Unit}.
	 * @return The created {@code Quantity} is returned.
	 */
	public Quantity createQuantity(String name, Unit defaultUnit) {
		Quantity quantity = new Quantity(createCore(Quantity.class));

		// relations
		getCore(quantity).getMutableStore().set(defaultUnit);

		// properties
		quantity.setName(name);
		quantity.setDateCreated(LocalDateTime.now());
		quantity.setDefaultRank(Integer.valueOf(1));
		quantity.setDefaultDimension(new int[] { 0 });
		quantity.setDefaultTypeSize(Integer.valueOf(1));
		quantity.setDefaultChannelName(name);
		quantity.setDefaultScalarType(ScalarType.FLOAT);

		quantity.getValue("Version").set("1");
		quantity.getValue("ValidFlag").set(VersionState.VALID);

		return quantity;
	}

	/**
	 * Creates a new {@link Test} with a reference to the logged in
	 * {@link User}, if there is one.
	 *
	 * @param name
	 *            Name of the created {@code Test}.
	 * @return The created {@code Test} is returned.
	 */
	public Test createTest(String name) {
		Test test = new Test(createCore(Test.class));

		// relations
		Optional<User> responsiblePerson = getLoggedInUser();
		if (responsiblePerson.isPresent()) {
			// may be null if user entities are not available
			getCore(test).getMutableStore().set(responsiblePerson.get());
		}

		// properties
		test.setName(name);
		test.setDateCreated(LocalDateTime.now());

		return test;
	}

	/**
	 * Creates a new {@link TestStep}.
	 *
	 * @param name
	 *            Name of the created {@code TestStep}.
	 * @param test
	 *            The parent {@link Test}.
	 * @return The created {@code TestStep} is returned.
	 */
	public TestStep createTestStep(String name, Test test) {
		TestStep testStep = new TestStep(createCore(TestStep.class));

		// relations
		getCore(testStep).getPermanentStore().set(test);
		getCore(test).getChildrenStore().add(testStep);

		// properties
		testStep.setName(name);
		testStep.setDateCreated(LocalDateTime.now());
		testStep.setOptional(Boolean.TRUE);

		if (test.getID() != null && test.getID().length() > 0) {
			// highest sort index in use will be queried before written
			testStep.setSortIndex(Integer.valueOf(-1));
		} else {
			testStep.setSortIndex(nextIndex(getCore(test).getChildrenStore().get(TestStep.class)));
		}

		return testStep;
	}

	/**
	 * Creates a new {@link Unit}.
	 *
	 * @param name
	 *            Name of the created {@code Unit}.
	 * @param physicalDimension
	 *            The {@link PhysicalDimension}.
	 * @return The created {@code Unit} is returned.
	 */
	public Unit createUnit(String name, PhysicalDimension physicalDimension) {
		Unit unit = new Unit(createCore(Unit.class));

		// relations
		getCore(unit).getMutableStore().set(physicalDimension);

		// properties
		unit.setName(name);
		unit.setOffset(Double.valueOf(0D));
		unit.setFactor(Double.valueOf(1D));

		return unit;
	}

	/**
	 * Creates a new {@link User}.
	 *
	 * @param name
	 *            Name of the created {@code User}.
	 * @param givenName
	 *            Given name of the created {@code User}.
	 * @param surname
	 *            Surname of the created {@code User}.
	 * @return The created {@code User} is returned.
	 */
	public User createUser(String name, String givenName, String surname) {
		User user = new User(createCore(User.class));

		// properties
		user.setName(name);
		user.setGivenName(givenName);
		user.setSurname(surname);

		return user;
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	/**
	 * Creates a new {@link ContextRoot}.
	 *
	 * @param name
	 *            Name of the created {@code ContextRoot}.
	 * @param contextType
	 *            {@link ContextType} of the created {@code ContextRoot}.
	 * @return The created {@code ContextRoot} is returned.
	 */
	protected ContextRoot createContextRoot(String name, ContextType contextType) {
		ContextRoot contextRoot = new ContextRoot(createCore(ContextRoot.class, contextType));

		// properties
		contextRoot.setName(name);
		contextRoot.setVersion(String.valueOf(0));

		return contextRoot;
	}

	/**
	 * Creates a new {@link ContextComponent}.
	 *
	 * @param name
	 *            Name of the created {@code ContextComponent}.
	 * @param contextRoot
	 *            The parent {@link ContextRoot}.
	 * @return The created {@code ContextComponent} is returned.
	 */
	protected ContextComponent createContextComponent(String name, ContextRoot contextRoot) {
		ContextComponent contextComponent = new ContextComponent(createCore(name, ContextComponent.class));

		// relations
		getCore(contextComponent).getPermanentStore().set(contextRoot);
		getCore(contextRoot).getChildrenStore().add(contextComponent);

		// properties
		contextComponent.setName(name);

		return contextComponent;
	}

	/**
	 * Creates a new {@link ContextSensor}.
	 *
	 * @param name
	 *            Name of the created {@code ContextSensor}.
	 * @param contextComponent
	 *            The parent {@link ContextComponent}.
	 * @return The created {@code ContextSensor} is returned.
	 */
	protected ContextSensor createContextSensor(String name, ContextComponent contextComponent) {
		ContextSensor contextSensor = new ContextSensor(createCore(name, ContextSensor.class));
		// relations
		getCore(contextSensor).getPermanentStore().set(contextComponent);
		getCore(contextComponent).getChildrenStore().add(contextSensor);

		// properties
		contextSensor.setName(name);

		return contextSensor;
	}

	/**
	 * Returns the next usable sort index for given {@code List} of
	 * {@link Sortable}s.
	 *
	 * @param sortables
	 *            The {@code Sortable} whose max sort index will be searched.
	 * @return {@code 1} is returned if given {@code List} is empty, otherwise
	 *         the max sort index increased by 1 is returned.
	 */
	protected static final Integer nextIndex(List<? extends Sortable> sortables) {
		Optional<Integer> maxIndex = sortables.stream().max(Sortable.COMPARATOR).map(Sortable::getSortIndex);
		return Integer.valueOf(maxIndex.isPresent() ? maxIndex.get().intValue() + 1 : 1);
	}

	/**
	 * Returns the {@link ChildrenStore} for given {@link BaseEntity}.
	 * Please be aware that the contents of this store is subject
	 * to the adapter creating it. It is up to the adapter to decide
	 * which related entities are included.
	 * As there are adapter specific details to the contents of the store it
	 * should be protected from external access.
	 * Therefore by declaring this method protected, the
	 * related functionality is hidden from other classes since the only
	 * classes which can access them (outside this package) are derivatives of
	 * BaseEntityFactory, and it is through such derivatives that BaseEntity's
	 * Stores should be accessed elsewhere if needed.
	 *
	 * @param entity
	 *            The {@code BaseEntity} whose {@code ChildrenStore} will be
	 *            returned.
	 * @return The {@code ChildrenStore} is returned.
	 */
	protected static ChildrenStore getChildrenStore(BaseEntity entity) {
		return getCore(entity).getChildrenStore();
	}

	/**
	 * Returns the mutable {@link EntityStore} for given {@link BaseEntity}.
	 * Please be aware that the contents of this store is subject
	 * to the adapter creating it. It is up to the adapter to decide
	 * which related entities are included. 
	 * As there are adapter specific details to the contents of the store it
	 * should be protected from external access. 
	 * Therefore by declaring this method protected, the
	 * related functionality is hidden from other classes since the only
	 * classes which can access them (outside this package) are derivatives of
	 * BaseEntityFactory, and it is through such derivatives that BaseEntity's
	 * Stores should be accessed elsewhere if needed.
	 * 
	 *
	 * @param entity
	 *            The {@code BaseEntity} whose {@code ChildrenStore} will be
	 *            returned.
	 * @return The mutable {@code EntityStore} is returned.
	 */
	protected static EntityStore getMutableStore(BaseEntity entity) {
		return getCore(entity).getMutableStore();
	}

	/**
	 * Returns the permanent {@link EntityStore} for given {@link BaseEntity}.
	 * Please be aware that the contents of this store is subject
	 * to the adapter creating it. It is up to the adapter to decide
	 * which related entities are included.
	 * As there are adapter specific details to the contents of the store it
	 * should be protected from external access.
	 * Therefore by declaring this method protected, the
	 * related functionality is hidden from other classes since the only
	 * classes which can access them (outside this package) are derivatives of
	 * BaseEntityFactory, and it is through such derivatives that BaseEntity's
	 * Stores should be accessed elsewhere if needed.
	 *    
	 * @param entity
	 *            The {@code BaseEntity} whose {@code ChildrenStore} will be
	 *            returned.
	 * @return The permanent {@code EntityStore} is returned.
	 */
	protected static EntityStore getPermanentStore(BaseEntity entity) {
		return getCore(entity).getPermanentStore();
	}

	/**
	 * Returns {@link Core} of given {@link Entity}. Uses protected method from
	 * BaseEntity, which is accessible here as BaseEntity resides in the same
	 * package as BaseEntityFactory. By declaring this method protected, the
	 * Core-related functionality is hidden from other classes since the only
	 * classes which can access them (outside this package) are derivatives of
	 * BaseEntityFactory, and it is through such derivatives that a BaseEntity's
	 * Core should be accessed elsewhere if needed.
	 *
	 * @param entity
	 *            The {@code BaseEntity} whose {@code Core} is required.
	 * @return The {@code Core} is returned.
	 */
	protected static final Core getCore(BaseEntity entity) {
		return entity.getCore();
	}

	/**
	 * Create an instance of {@link BaseEntity} (or derivative) with core as the
	 * instance's {@link Core}. By declaring this method protected, the Core-related
	 * functionality is hidden from other classes since the only classes which can
	 * access them (outside this package) are derivatives of BaseEntityFactory, and
	 * it is through such derivatives that a BaseEntity's Core should be accessed
	 * elsewhere if needed.
	 * <br> <br>
	 * This method accesses the (usually protected) Constructor(Core) of clazz, so
	 * this method should be overridden in a derived class and call
	 * super.createBaseEntity() if that constructor is invisible from the derived
	 * class.
	 * 
	 * @param clazz
	 *            The class to instantiate, must extend {@link BaseEntity}.
	 * @param core
	 *            The {@link Core} to use for the newly created instance.
	 * @return The newly created instance.
	 */
	protected <T extends BaseEntity> T createBaseEntity(Class<T> clazz, Core core) {
		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor(Core.class);
			try {
				return constructor.newInstance(core);
			} catch (IllegalAccessException exc) {
				throw new IllegalStateException("Cannot access Constructor(Core) of class '" + clazz.getName() + "'!");
			}
		} catch (NoSuchMethodException | InvocationTargetException | InstantiationException exc) {
			throw new IllegalStateException(exc.getMessage(), exc);
		}
	}

	/**
	 * Returns the {@link User} which is bound to the current session.
	 *
	 * @return {@code Optional} is empty if {@code User} entities do not exist.
	 */
	protected abstract Optional<User> getLoggedInUser();

	/**
	 * Returns a new {@link Core} for given entity class.
	 *
	 * @param <T>
	 *            The entity class type.
	 * @param entityClass
	 *            The entity class.
	 * @return A new {@code Core} instance is returned.
	 */
	protected abstract <T extends Entity> Core createCore(Class<T> entityClass);

	/**
	 * Returns a new {@link Core} for given entity class and
	 * {@link ContextType}.
	 *
	 * @param <T>
	 *            The entity class type.
	 * @param entityClass
	 *            The entity class.
	 * @param contextType
	 *            The {@code ContextType}.
	 * @return A new {@code Core} instance is returned.
	 */
	protected abstract <T extends Entity> Core createCore(Class<T> entityClass, ContextType contextType);

	/**
	 * Returns a new {@link Core} for given entity class and type name.
	 *
	 * @param <T>
	 *            The entity class type.
	 * @param name
	 *            Name of the entity type.
	 * @param entityClass
	 *            The entity class.
	 * @return A new {@code Core} instance is returned.
	 */
	protected abstract <T extends Entity> Core createCore(String name, Class<T> entityClass);

}
