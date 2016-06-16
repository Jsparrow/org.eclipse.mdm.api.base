/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * The entity factory interface.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface EntityFactory {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link Channel}. The name of the returned {@code Channel}
	 * is retrieved from given {@link Quantity}.
	 *
	 * @param measurement The parent {@link Measurement}.
	 * @param quantity The {@code Quantity} is used for default initialization.
	 * @return The created {@code Channel} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	default Channel createChannel(Measurement measurement, Quantity quantity) {
		return createChannel(quantity.getDefaultChannelName(), measurement, quantity);
	}

	/**
	 * Creates a new {@link Channel}.
	 *
	 * @param name Name of the created {@code Channel}.
	 * @param measurement The parent {@link Measurement}.
	 * @param quantity The {@code Quantity} is used for default initialization.
	 * @return The created {@code Channel} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	Channel createChannel(String name, Measurement measurement, Quantity quantity);

	/**
	 * Creates a new {@link ChannelGroup}.
	 *
	 * @param name Name of the created {@code ChannelGroup}.
	 * @param numberOfValues The number of values per each related {@link Channel}.
	 * @param measurement The parent {@link Measurement}.
	 * @return The created {@code ChannelGroup} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	ChannelGroup createChannelGroup(String name, int numberOfValues, Measurement measurement);

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

	/**
	 * Creates a new {@link Measurement}.
	 *
	 * @param name Name of the created {@code Measurement}.
	 * @param testStep The parent {@link TestStep}.
	 * @return The created {@code Measurement} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	Measurement createMeasurement(String name, TestStep testStep);

	/**
	 * Creates a new {@link Parameter} with initialized with given value.
	 *
	 * @param name Name of the created {@code Parameter}.
	 * @param value The value of the created {@code Parameter}.
	 * @param unit An optionally related {@link Unit}.
	 * @param parameterSet The parent {@link ParameterSet}.
	 * @return The created {@code Parameter} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 * @see Parameter#setObjectValue(Object, Unit)
	 */
	Parameter createParameter(String name, Object value, Unit unit, ParameterSet parameterSet);

	ParameterSet createParameterSet(String name, String version, Measurement measurement);

	ParameterSet createParameterSet(String name, String version, Channel channel);

	/**
	 * Creates a new {@link PhysicalDimension}.
	 *
	 * @param name Name of the created {@code PhysicalDimension}.
	 * @return The created {@code PhysicalDimension} is returned.
	 */
	PhysicalDimension createPhysicalDimension(String name);

	/**
	 * Creates a new {@link Quantity}.
	 *
	 * @param name Name of the created {@code Quantity}.
	 * @param defaultUnit The default {@link Unit}.
	 * @return The created {@code Quantity} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	Quantity createQuantity(String name, Unit defaultUnit);

	/**
	 * Creates a new {@link Test}.
	 *
	 * @param name Name of the created {@code Test}.
	 * @param responsiblePerson The responsible {@link User}.
	 * @return The created {@code Test} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	Test createTest(String name, User responsiblePerson);

	/**
	 * Creates a new {@link TestStep}.
	 *
	 * @param name Name of the created {@code TestStep}.
	 * @param test The parent {@link Test}.
	 * @return The created {@code TestStep} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	TestStep createTestStep(String name, Test test);

	/**
	 * Creates a new {@link Unit}.
	 *
	 * @param name Name of the created {@code Unit}.
	 * @param physicalDimension The {@link PhysicalDimension}.
	 * @return The created {@code Unit} is returned.
	 * @throws IllegalArgumentException Thrown if a related entity is not yet
	 * 		persisted.
	 */
	Unit createUnit(String name, PhysicalDimension physicalDimension);

	/**
	 * Creates a new {@link User}.
	 *
	 * @param name Name of the created {@code User}.
	 * @param givenName Given name of the created {@code User}.
	 * @param surname Surname of the created {@code User}.
	 * @return The created {@code User} is returned.
	 */
	User createUser(String name, String givenName, String surname);

}
