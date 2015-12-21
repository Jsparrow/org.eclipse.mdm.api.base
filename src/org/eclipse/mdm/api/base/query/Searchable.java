/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;

/**
 * A searchable is used to describe a hierarchical order of entities being used
 * for searching.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 * @see SearchService
 * @see SearchQuery
 */
public interface Searchable {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns all related searchables.
	 *
	 * @return Returned {@code List} may be immutable.
	 */
	List<Searchable> getRelated();

	/**
	 * Returns the {@link Entity} represented by this searchable.
	 *
	 * @return The {@code Entity} represented by this searchable is returned.
	 */
	Entity getEntity();


	/**
	 * Checks whether the represented {@link Entity} is implicitly selected
	 * for searching and therefore manual selection of its {@link Attribute}s
	 * is neither required nor recommended.
	 *
	 * @return True is returned if the represented {@code Entity} is implicitly
	 * 		selected for searching.
	 */
	boolean isImplicit();

	/**
	 * Returns {@code true} if this searchable doesn't have further related
	 * searchables.
	 *
	 * @return True if this instance does not relate further searchables.
	 */
	default boolean isLeaf() {
		return getRelated().isEmpty();
	}

}
