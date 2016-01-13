/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

/**
 * Represents modeled relation.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
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
	 * The source {@link Entity} of this relation.
	 *
	 * @return The source {@code Entity} is returned.
	 */
	Entity getSource();

	/**
	 * The target {@link Entity} of this relation.
	 *
	 * @return The target {@code Entity} is returned.
	 */
	Entity getTarget();

	/** Returns the {@link Relationship} type for this relation.
	 *
	 * @return The {@code Relationship} is returned.
	 */
	Relationship getRelationship();

}