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

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.mdm.api.base.adapter.Core;

/**
 * This is a base implementation for modeled entities. API consumers should
 * never use this class in any way, instead the most common interface should be
 * used (e.g.: {@link Entity}, {@link ContextDescribable}, etc.). API producers
 * must let their {@code Entity} implementations extend this class.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public abstract class BaseEntity implements Entity {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Core core;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	protected BaseEntity(Core core) {
		this.core = core;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSourceName() {
		return getCore().getSourceName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeName() {
		return getCore().getTypeName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getID() {
		return getCore().getID();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Value getValue(String name) {
		Value value = getCore().getValues().get(name);
		if (value == null) {
			throw new IllegalStateException(new StringBuilder().append("Value with name '").append(name).append("' does not exist").toString());
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, Value> getValues() {
		return Collections.unmodifiableMap(getCore().getValues());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String prefix = new StringBuilder(getClass().getSimpleName()).append('(').toString();
		return getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ", prefix, ")"));
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	/**
	 * Returns the internally stored {@link Core}.
	 *
	 * @return The {@link Core} is returned.
	 */
	protected Core getCore() {
		return core;
	}

	/**
	 * Convenience method to extract {@link Core} of given {@link Entity}.
	 *
	 * @param entity
	 *            The {@code Entity} whose {@code Core} is required.
	 * @return The {@code Core} is returned.
	 */
	protected static Core getCore(Entity entity) {
		return ((BaseEntity) entity).getCore();
	}

}
