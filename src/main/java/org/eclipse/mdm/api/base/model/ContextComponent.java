/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/


package org.eclipse.mdm.api.base.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.eclipse.mdm.api.base.adapter.Core;

/**
 * Implementation of the context component entity types. Instances of this class
 * are only provided / managed via the owning descriptive {@link ContextRoot}.
 * Additionally if the owning {@code ContextRoot} is of type
 * {@link ContextType#TESTEQUIPMENT} this context component may have relations
 * to {@link ContextSensor}s whose names have to be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class ContextComponent extends BaseEntity implements Deletable {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	ContextComponent(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link ContextSensor} identified by given name.
	 *
	 * @param name
	 *            The name of the {@code ContextSensor}.
	 * @return The {@code Optional} is empty if a {@code ContextSensor} with
	 *         given name does not exist.
	 */
	public Optional<ContextSensor> getContextSensor(String name) {
		return getContextSensors().stream().filter(cs -> cs.nameEquals(name)).findAny();
	}

	/**
	 * Returns all available {@link ContextSensor}s related to this context
	 * component.
	 *
	 * @return The returned {@code List} is unmodifiable.
	 */
	public List<ContextSensor> getContextSensors() {
		return getCore().getChildrenStore().get(ContextSensor.class);
	}

	/**
	 * Returns the {@link ContextRoot} this context component belongs to.
	 *
	 * @return The parent {@link ContextRoot}.
	 */
	public ContextRoot getContextRoot() {
		return getCore().getPermanentStore().get(ContextRoot.class);
	}
	
	/**
	 * Removes the {@link ContextSensor} identified by given name.
	 *
	 * @param name
	 *            Name of the {@code ContextSensor} that has to be removed.
	 * @return Returns {@code true} if the {@code ContextSensor} with given name
	 *         has been removed.
	 */
	public boolean removeContextSensor(String name) {
		Optional<ContextSensor> catalogSensor = getContextSensor(name);
		if (!catalogSensor.isPresent()) {
			return false;
		}
		getCore().getChildrenStore().remove(catalogSensor.get());
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append('(');
		sb.append(getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ")));

		List<ContextSensor> contextSensors = getContextSensors();
		if (!contextSensors.isEmpty()) {
			sb.append(", ContextSensors = ").append(contextSensors);
		}

		return sb.append(')').toString();
	}

}
