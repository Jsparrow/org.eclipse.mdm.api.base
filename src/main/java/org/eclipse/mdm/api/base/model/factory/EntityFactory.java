/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model.factory;

import java.time.LocalDateTime;
import java.util.Optional;

import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ChannelGroup;
import org.eclipse.mdm.api.base.model.ContextRoot;
import org.eclipse.mdm.api.base.model.ContextSensor;
import org.eclipse.mdm.api.base.model.Core;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Interpolation;
import org.eclipse.mdm.api.base.model.Measurement;
import org.eclipse.mdm.api.base.model.MimeType;
import org.eclipse.mdm.api.base.model.Parameter;
import org.eclipse.mdm.api.base.model.ParameterSet;
import org.eclipse.mdm.api.base.model.Parameterizable;
import org.eclipse.mdm.api.base.model.PhysicalDimension;
import org.eclipse.mdm.api.base.model.Quantity;
import org.eclipse.mdm.api.base.model.ScalarType;
import org.eclipse.mdm.api.base.model.Sortable;
import org.eclipse.mdm.api.base.model.Test;
import org.eclipse.mdm.api.base.model.TestStep;
import org.eclipse.mdm.api.base.model.Unit;
import org.eclipse.mdm.api.base.model.User;
import org.eclipse.mdm.api.base.query.DataAccessException;

public abstract class EntityFactory {

	public Channel createChannel(Measurement measurement, Quantity quantity, Optional<ContextSensor> contextSensor) throws DataAccessException {
		return createChannel(quantity.getDefaultChannelName(), measurement, quantity, contextSensor);
	}

	public Channel createChannel(String name, Measurement measurement, Quantity quantity, Optional<ContextSensor> contextSensor) throws DataAccessException {
		// TODO a channel may have a relation to a sensor...

		Channel channel = new Channel(createCore(Channel.class));

		channel.getCore().setInfoRelation(quantity);
		channel.getCore().setInfoRelation(quantity.getDefaultUnit());
		contextSensor.ifPresent(s -> channel.getCore().setInfoRelation(s));

		channel.setName(name);
		channel.setMimeType(getDefaultMimeType(Channel.class));
		channel.setDescription(quantity.getDescription());
		channel.setInterpolation(Interpolation.NONE);
		channel.setScalarType(quantity.getDefaultScalarType());
		channel.setRank(quantity.getDefaultRank());
		// TODO channel.setDimension omitted as done by MDM4 API
		channel.setTypeSize(quantity.getDefaultTypeSize());

		return create(channel, measurement);
	}

	public ChannelGroup createChannelGroup(String name, int numberOfValues, Measurement measurement) throws DataAccessException {
		if(numberOfValues < 0) {
			throw new IllegalArgumentException("Number of values must be equal or greater than 0.");
		}

		ChannelGroup channelGroup = new ChannelGroup(createCore(ChannelGroup.class));

		channelGroup.setName(name);
		channelGroup.setMimeType(getDefaultMimeType(ChannelGroup.class));
		channelGroup.getValue(ChannelGroup.ATTR_NUMBER_OF_VALUES).set(Integer.valueOf(numberOfValues));

		return create(channelGroup, measurement);
	}

	//	public ContextComponent createContextComponent(String name, EntityType entityType, ContextRoot contextRoot) {
	//      // TODO: we need to know the entity type name
	//      // in the base application model we do not have templates
	//		throw new UnsupportedOperationException();
	//	}
	//
	//	public ContextRoot createContextRoot(String name, ContextType contextType, TestStep testStep) {
	//		throw new UnsupportedOperationException();
	//	}
	//
	//	public ContextRoot createContextRoot(String name, ContextType contextType, Measurement... measurements) {
	//		throw new UnsupportedOperationException();
	//	}
	//
	//	public ContextSensor createContextSensor(String name, ContextComponent contextComponent) {
	//      // TODO: we need to know the entity type name
	//      // in the base application model we do not have templates
	//		throw new UnsupportedOperationException();
	//	}

	public Measurement createMeasurement(String name, TestStep testStep, ContextRoot... contextRoots) throws DataAccessException {
		Measurement measurement = new Measurement(createCore(Measurement.class));

		measurement.setName(name);
		measurement.setMimeType(getDefaultMimeType(Measurement.class));
		measurement.setDateCreated(LocalDateTime.now());

		Entity[] implicitlyRelated = new Entity[contextRoots.length + 1];
		implicitlyRelated[0] = testStep;
		System.arraycopy(contextRoots, 0, implicitlyRelated, 1, contextRoots.length);

		return create(measurement, implicitlyRelated);
	}

	// TODO document allowed types for value!
	public Parameter createParameter(String name, Object value, Optional<Unit> unit, ParameterSet parameterSet) throws DataAccessException {
		Parameter parameter = new Parameter(createCore(Parameter.class));

		parameter.setName(name);
		parameter.setMimeType(getDefaultMimeType(Parameter.class));
		parameter.setObjectValue(value, unit);
		parameterSet.getCore().addChild(parameter);

		return create(parameter, parameterSet);
	}

	public ParameterSet createParameterSet(String name, String version, Parameterizable parameterizable) throws DataAccessException {
		ParameterSet parameterSet = new ParameterSet(createCore(ParameterSet.class));

		parameterSet.setName(name);
		parameterSet.setMimeType(getDefaultMimeType(ParameterSet.class));
		parameterSet.setVersion(version);

		return create(parameterSet, parameterizable);
	}

	public PhysicalDimension createPhysicalDimension(String name) throws DataAccessException {
		PhysicalDimension physicalDimension = new PhysicalDimension(createCore(PhysicalDimension.class));

		physicalDimension.setName(name);
		physicalDimension.setMimeType(getDefaultMimeType(PhysicalDimension.class));
		physicalDimension.setLength(Integer.valueOf(0));
		physicalDimension.setMass(Integer.valueOf(0));
		physicalDimension.setTime(Integer.valueOf(0));
		physicalDimension.setCurrent(Integer.valueOf(0));
		physicalDimension.setMolarAmount(Integer.valueOf(0));
		physicalDimension.setLuminousIntensity(Integer.valueOf(0));
		physicalDimension.setAngle(Integer.valueOf(0));

		return create(physicalDimension);
	}

	public Quantity createQuantity(String name, Unit unit) throws DataAccessException {
		Quantity quantity = new Quantity(createCore(Quantity.class));

		quantity.getCore().setInfoRelation(unit);

		quantity.setName(name);
		quantity.setMimeType(getDefaultMimeType(Quantity.class));
		quantity.setDateCreated(LocalDateTime.now());
		quantity.setDefaultRank(Integer.valueOf(1));
		quantity.setDefaultDimension(new int[] { 0 });
		quantity.setDefaultTypeSize(Integer.valueOf(1));
		quantity.setDefaultChannelName(name);
		quantity.setDefaultScalarType(ScalarType.FLOAT);

		return create(quantity);
	}

	public Test createTest(String name, Optional<User> responsiblePerson) throws DataAccessException {
		Test test = new Test(createCore(Test.class));

		responsiblePerson.ifPresent(u -> test.getCore().setInfoRelation(u));

		/**
		 * TODO
		 * - structure level / pool parent
		 * - status - part of the default API!
		 *
		 * ODS adapter should override and throw an IllStateException as soon as the default API is defined!
		 */

		test.setName(name);
		test.setMimeType(getDefaultMimeType(Test.class));
		test.setDateCreated(LocalDateTime.now());

		return create(test);
	}

	public TestStep createTestStep(String name, Test test) throws DataAccessException {
		TestStep testStep = new TestStep(createCore(TestStep.class));

		/**
		 * TODO
		 * - status - part of the default API!
		 *
		 * ODS adapter should override and throw an IllStateException as soon as the default API is defined!
		 */

		testStep.setName(name);
		testStep.setMimeType(getDefaultMimeType(TestStep.class));
		testStep.setDateCreated(LocalDateTime.now());
		testStep.setOptional(Boolean.TRUE);
		testStep.setSortIndex(nextSortIndex(test, TestStep.class));

		return create(testStep, test);
	}

	public Unit createUnit(String name, PhysicalDimension physicalDimension) throws DataAccessException {
		Unit unit = new Unit(createCore(Unit.class));

		unit.getCore().setInfoRelation(physicalDimension);

		unit.setName(name);
		unit.setMimeType(getDefaultMimeType(Unit.class));
		unit.setOffset(Double.valueOf(0D));
		unit.setFactor(Double.valueOf(1D));
		unit.setDB(Float.valueOf(0F));

		return create(unit);
	}

	public User createUser(String name, String givenName, String surname) throws DataAccessException {
		User user = new User(createCore(User.class));

		user.setName(name);
		user.setMimeType(getDefaultMimeType(User.class));
		user.setGivenName(givenName);
		user.setSurname(surname);
		// TODO user.setDepartment("");
		// TODO user.setPhone("");
		// TODO user.setMail("");

		return create(user);
	}

	protected abstract Core createCore(Class<? extends Entity> type);

	protected abstract MimeType getDefaultMimeType(Class<? extends Entity> type);

	protected abstract <T extends Entity> T create(T entity, Entity... implicitlyRelated) throws DataAccessException;

	protected <T extends Sortable> Integer nextSortIndex(Entity parent, Class<T> type) {
		// TODO highest of siblings + 10 (requires query execution!)
		return Integer.valueOf(10);
	}

}
