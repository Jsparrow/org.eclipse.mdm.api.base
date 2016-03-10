/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is a base implementation for modeled entities. API consumers should
 * never use this class in any way, instead the most common interface should
 * be used (e.g.: {@link Entity}, {@link ContextDescribable}, etc.). API
 * producers must let their {@code Entity} implementations extend this class.
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
	public URI getURI() {
		return getCore().getURI();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Value getValue(String name) {
		// TODO core should never be available to the outer world -> unmodifiable wrapping can be omitted
		return getCore().getValues().get(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Value> getValues() {
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

	@Override
	@Deprecated
	// TODO provide read only access to related entities
	// - related info entities (unit, quantity, etc.)
	// - children (added / removed)
	// - mandatory / implicit (parent or other mandatory related entities (ContextRoot when creating a new measurement along existing siblings))
	public final Core getCore() {
		return core;
	}

}