/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.eclipse.mdm.api.base.model.Core.ChildrenStore;
import org.eclipse.mdm.api.base.model.Core.EntityStore;

/**
 * Implementation of an abstract entity factory which creates new entities.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public abstract class BaseEntityFactory implements EntityFactory {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Channel createChannel(String name, Measurement measurement, Quantity quantity) {
		Channel channel = new Channel(createCore(Channel.class));

		// relations
		getPermanentStore(channel).set(measurement);
		getChildrenStore(measurement).add(channel);
		getMutableStore(channel).set(quantity.getDefaultUnit());
		getMutableStore(channel).set(quantity);

		//		if(contextSensor != null) {
		//			channel.getCore().setInfoRelation(contextSensor);
		//		}

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
	 * {@inheritDoc}
	 */
	@Override
	public ChannelGroup createChannelGroup(String name, int numberOfValues, Measurement measurement) {
		if(numberOfValues < 0) {
			throw new IllegalArgumentException("Number of values must be equal or greater than 0.");
		}

		ChannelGroup channelGroup = new ChannelGroup(createCore(ChannelGroup.class));

		// relations
		getPermanentStore(channelGroup).set(measurement);
		getChildrenStore(measurement).add(channelGroup);

		// properties
		channelGroup.setName(name);
		channelGroup.setNumberOfValues(Integer.valueOf(numberOfValues));

		return channelGroup;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Measurement createMeasurement(String name, TestStep testStep) {
		Measurement measurement = new Measurement(createCore(Measurement.class));

		// relations
		getPermanentStore(measurement).set(testStep);
		getChildrenStore(testStep).add(measurement);

		// TODO
		//		for(ContextRoot contextRoot : contextRoots) {
		//			measurement.getCore().setImplicitRelation(contextRoot);
		//		}

		// properties
		measurement.setName(name);
		measurement.setDateCreated(LocalDateTime.now());

		return measurement;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Parameter createParameter(String name, Object value, Unit unit, ParameterSet parameterSet) {
		if(parameterSet.getParameter(name).isPresent()) {
			throw new IllegalArgumentException("Parameter with name '" + name + "' already exists.");
		}

		Parameter parameter = new Parameter(createCore(Parameter.class));

		// relations
		getPermanentStore(parameter).set(parameterSet);
		getChildrenStore(parameterSet).add(parameter);

		// properties
		parameter.setName(name);
		parameter.setObjectValue(value, unit);

		return parameter;
	}

	@Override
	public ParameterSet createParameterSet(String name, String version, Measurement measurement) {
		ParameterSet parameterSet = new ParameterSet(createCore(ParameterSet.class));

		// relations
		getPermanentStore(parameterSet).set(measurement);
		getChildrenStore(measurement).add(parameterSet);

		// properties
		parameterSet.setName(name);
		parameterSet.setVersion(version);

		return parameterSet;
	}

	@Override
	public ParameterSet createParameterSet(String name, String version, Channel channel) {
		ParameterSet parameterSet = new ParameterSet(createCore(ParameterSet.class));

		// relations
		getPermanentStore(parameterSet).set(channel);
		getChildrenStore(channel).add(parameterSet);

		// properties
		parameterSet.setName(name);
		parameterSet.setVersion(version);

		return parameterSet;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PhysicalDimension createPhysicalDimension(String name) {
		PhysicalDimension physicalDimension = new PhysicalDimension(createCore(PhysicalDimension.class));

		// properties
		physicalDimension.setName(name);
		physicalDimension.setLength(Integer.valueOf(0));
		physicalDimension.setMass(Integer.valueOf(0));
		physicalDimension.setTime(Integer.valueOf(0));
		physicalDimension.setCurrent(Integer.valueOf(0));
		physicalDimension.setMolarAmount(Integer.valueOf(0));
		physicalDimension.setLuminousIntensity(Integer.valueOf(0));
		physicalDimension.setAngle(Integer.valueOf(0));

		return physicalDimension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Quantity createQuantity(String name, Unit defaultUnit) {
		Quantity quantity = new Quantity(createCore(Quantity.class));

		// relations
		getMutableStore(quantity).set(defaultUnit);

		// properties
		quantity.setName(name);
		quantity.setDateCreated(LocalDateTime.now());
		quantity.setDefaultRank(Integer.valueOf(1));
		quantity.setDefaultDimension(new int[] { 0 });
		quantity.setDefaultTypeSize(Integer.valueOf(1));
		quantity.setDefaultChannelName(name);
		quantity.setDefaultScalarType(ScalarType.FLOAT);

		// TODO we should check whether this property exists (seems to be a deprecated one..)
		quantity.getValue("Version").set("1");
		quantity.getValue("ValidFlag").set(VersionState.VALID);

		return quantity;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Test createTest(String name, User responsiblePerson) {
		Test test = new Test(createCore(Test.class));

		// relations
		if(responsiblePerson != null) {
			// may be null if user entities are not available
			getMutableStore(test).set(responsiblePerson);
		}

		/**
		 * TODO
		 * - structure level / pool parent
		 * - status - part of the default API!
		 *
		 * ODS adapter should override and throw an IllStateException as soon as the default API is defined!
		 */

		// properties
		test.setName(name);
		test.setDateCreated(LocalDateTime.now());

		return test;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	// TODO if test already exists sortindex is set to -1
	// as soon as test step is written with a negative sort index
	// current max index is queried before test step fields are written
	public TestStep createTestStep(String name, Test test) {
		TestStep testStep = new TestStep(createCore(TestStep.class));

		// relations
		getPermanentStore(testStep).set(test);
		getChildrenStore(test).add(testStep);

		/**
		 * TODO
		 * - status - part of the default API!
		 *
		 * ODS adapter should override and throw an IllStateException as soon as the default API is defined!
		 */

		// properties
		testStep.setName(name);
		testStep.setDateCreated(LocalDateTime.now());
		testStep.setOptional(Boolean.TRUE);

		if(test.getID() > 0) {
			testStep.setSortIndex(Integer.valueOf(-1));
		} else {
			testStep.setSortIndex(nextIndex(getChildrenStore(test).get(TestStep.class)));
		}

		return testStep;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Unit createUnit(String name, PhysicalDimension physicalDimension) {
		Unit unit = new Unit(createCore(Unit.class));

		// relations
		getMutableStore(unit).set(physicalDimension);

		// properties
		unit.setName(name);
		unit.setOffset(Double.valueOf(0D));
		unit.setFactor(Double.valueOf(1D));
		unit.setDB(Float.valueOf(0F));

		return unit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
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

	protected final Integer nextIndex(List<? extends Sortable> sortables) {
		Optional<Integer> maxIndex = sortables.stream().max(Sortable.COMPARATOR).map(Sortable::getSortIndex);
		return Integer.valueOf(maxIndex.isPresent() ? maxIndex.get().intValue() + 1 : 1);
	}

	protected final ChildrenStore getChildrenStore(BaseEntity entity) {
		return getCore(entity).getChildrenStore();
	}

	protected final EntityStore getMutableStore(BaseEntity entity) {
		return getCore(entity).getMutableStore();
	}

	protected final EntityStore getPermanentStore(BaseEntity entity) {
		return getCore(entity).getPermanentStore();
	}

	protected abstract <T extends Entity> Core createCore(Class<T> entityClass);

	protected abstract <T extends Entity> Core createCore(Class<T> entityClass, ContextType contextType);

	private final Core getCore(BaseEntity entity) {
		return entity.getCore();
	}

}
