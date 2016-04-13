/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.time.LocalDateTime;

import org.eclipse.mdm.api.base.model.EntityCore.ChildrenStore;
import org.eclipse.mdm.api.base.model.EntityCore.EntityStore;

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
		getPermanentStore(channel).setParent(measurement, true);
		getMutableStore(channel).set(quantity.getDefaultUnit());
		getMutableStore(channel).set(quantity);

		//		if(contextSensor != null) {
		//			channel.getCore().setInfoRelation(contextSensor);
		//		}

		// properties
		channel.setName(name);
		channel.setMimeType(getDefaultMimeType(Channel.class));
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
		getPermanentStore(channelGroup).setParent(measurement, true);

		// properties
		channelGroup.setName(name);
		channelGroup.setMimeType(getDefaultMimeType(ChannelGroup.class));
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
		getPermanentStore(measurement).setParent(testStep, true);
		//		for(ContextRoot contextRoot : contextRoots) {
		//			measurement.getCore().setImplicitRelation(contextRoot);
		//		}

		// properties
		measurement.setName(name);
		measurement.setMimeType(getDefaultMimeType(Measurement.class));
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
		getPermanentStore(parameter).setParent(parameterSet, false);
		getChildrenStore(parameterSet).add(parameter);

		// properties
		parameter.setName(name);
		parameter.setMimeType(getDefaultMimeType(Parameter.class));
		parameter.setObjectValue(value, unit);

		return parameter;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParameterSet createParameterSet(String name, String version, Parameterizable parameterizable) {
		ParameterSet parameterSet = new ParameterSet(createCore(ParameterSet.class));

		// relations
		getPermanentStore(parameterSet).setParent(parameterizable, true);

		// properties
		parameterSet.setName(name);
		parameterSet.setMimeType(getDefaultMimeType(ParameterSet.class));
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
		physicalDimension.setMimeType(getDefaultMimeType(PhysicalDimension.class));
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
		quantity.setMimeType(getDefaultMimeType(Quantity.class));
		quantity.setDateCreated(LocalDateTime.now());
		quantity.setDefaultRank(Integer.valueOf(1));
		quantity.setDefaultDimension(new int[] { 0 });
		quantity.setDefaultTypeSize(Integer.valueOf(1));
		quantity.setDefaultChannelName(name);
		quantity.setDefaultScalarType(ScalarType.FLOAT);

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
		test.setMimeType(getDefaultMimeType(Test.class));
		test.setDateCreated(LocalDateTime.now());

		return test;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TestStep createTestStep(String name, Test test) {
		TestStep testStep = new TestStep(createCore(TestStep.class));

		// relations
		getPermanentStore(testStep).setParent(test, true);

		/**
		 * TODO
		 * - status - part of the default API!
		 *
		 * ODS adapter should override and throw an IllStateException as soon as the default API is defined!
		 */

		// properties
		testStep.setName(name);
		testStep.setMimeType(getDefaultMimeType(TestStep.class));
		testStep.setDateCreated(LocalDateTime.now());
		testStep.setOptional(Boolean.TRUE);
		testStep.setSortIndex(Integer.valueOf(0)); // TODO

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
		unit.setMimeType(getDefaultMimeType(Unit.class));
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
		user.setMimeType(getDefaultMimeType(User.class));
		user.setGivenName(givenName);
		user.setSurname(surname);
		// user.setDepartment("");
		// user.setPhone("");
		// user.setMail("");

		return user;
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	protected final ChildrenStore getChildrenStore(BaseEntity entity) {
		return getCore(entity).getChildrenStore();
	}

	protected final EntityStore getMutableStore(BaseEntity entity) {
		return getCore(entity).getMutableStore();
	}

	protected final EntityStore getPermanentStore(BaseEntity entity) {
		return getCore(entity).getPermanentStore();
	}

	@Deprecated
	protected final EntityCore getCore(BaseEntity entity) {
		return entity.getCore();
	}

	/**
	 * Creates an {@link EntityCore} associated with given type.
	 *
	 * @param type The type of the entity.
	 * @return A new {@code EntityCore} is returned.
	 */
	protected abstract EntityCore createCore(Class<? extends Entity> type);

	/**
	 * Creates a default {@link MimeType} for given type.
	 *
	 * @param type The type of the entity.
	 * @return A default {@code MimeType} is returned.
	 */
	protected abstract MimeType getDefaultMimeType(Class<? extends Entity> type);


}
