/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH and others
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
 * @see RelationType
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

	/**
	 * Returns the {@link RelationType} type for this relation.
	 *
	 * @return The {@code RelationType} is returned.
	 */
	RelationType getRelationType();

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
		return ValueType.STRING.create(getName());
	}

	/**
	 * Returns the name of this relation.
	 *
	 * @return Name of this relation is returned.
	 */
	@Override
	String toString();
	
	/**
	 * Checks whether this relation is of the same {@link RelationType} as the
	 * given one and whether the foreign key is in the table of the source
	 * entity type (or, in other words, if there may be at most 1 related
	 * destination object). 
	 *
	 * @param relationType
	 *            The {@code RelationType}.
	 * @return Returns {@code true} this relation's {@code RelationType} is
	 *         equal with the given one and it is is an outgoing relation.
	 */
	boolean isOutgoing(RelationType relationType);
	
	/**
	 * Checks whether this relation is of the same {@link RelationType} as the
	 * given one and whether the foreign key is in the table of the target
	 * entity type (or, in other words, if there may be more than one related
	 * destination objects).
	 *
	 * @param relationType
	 *            The {@code RelationType}.
	 * @return Returns {@code true} this relation's {@code RelationType} is
	 *         equal with the given one and it is is an incoming relation.
	 */
	boolean isIncoming(RelationType relationType);

}
