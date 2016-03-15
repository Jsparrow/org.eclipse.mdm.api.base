/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collection;
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

	private final EntityCore core;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core The {@link EntityCore}.
	 */
	protected BaseEntity(EntityCore core) {
		this.core = core;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final URI getURI() {
		return getCore().getURI();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Value getValue(String name) {
		return getCore().getValues().get(name);
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
	public Collection<Entity> getInfoRelations() {
		return Collections.unmodifiableCollection(getCore().getInfoRelations().values());
	}

	//	/**
	//	 * {@inheritDoc}
	//	 */
	//	@Override
	//	public Map<Class<? extends Entity>, List<? extends Entity>> getChildren() {
	//		return Collections.unmodifiableMap(getCore().getChildren());
	//	}
	//
	//	/**
	//	 * {@inheritDoc}
	//	 */
	//	@Override
	//	public Map<Class<? extends Entity>, List<? extends Entity>> getRemovedChildren() {
	//		return Collections.unmodifiableMap(getCore().getRemovedChildren());
	//	}

	@Override
	public Collection<Entity> getImplicitRelations() {
		return Collections.unmodifiableCollection(getCore().getImplicitRelations().values());
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
	 * Returns the internally stored {@link EntityCore}.
	 *
	 * @return The {@link EntityCore} is returned.
	 */
	protected EntityCore getCore() {
		return core;
	}

}
