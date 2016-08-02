/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.model.ValueType;

/**
 * Represents a modeled relation.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see EntityType
 * @see Relationship
 */
public interface Relation {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * The name of this relation.
	 *
	 * @return The name is returned.
	 */
	String getName();

	/**
	 * The source {@link EntityType} of this relation.
	 *
	 * @return The source {@code EntityType} is returned.
	 */
	EntityType getSource();

	/**
	 * The target {@link EntityType} of this relation.
	 *
	 * @return The target {@code EntityType} is returned.
	 */
	EntityType getTarget();

	/** Returns the {@link Relationship} type for this relation.
	 *
	 * @return The {@code Relationship} is returned.
	 */
	Relationship getRelationship();

	/**
	 * Returns the foreign key {@link Attribute} for this {@link Relation}.
	 *
	 * @return The foreign key {@code Attribute} is returned.
	 */
	Attribute getAttribute();

	/**
	 * Creates a new and empty {@link Value} with {@link ValueType#LONG}.
	 *
	 * @return Created {@code Value} is returned.
	 */
	default Value createValue() {
		return ValueType.LONG.create(getName());
	}

	/**
	 * Returns the name of this relation.
	 *
	 * @return Name of this relation is returned.
	 */
	@Override
	String toString();

}
