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


package org.eclipse.mdm.api.base.search;

import java.util.List;

import org.eclipse.mdm.api.base.adapter.EntityType;

/**
 * A searchable is used to describe the hierarchical order of
 * {@link EntityType}s being used for searching.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
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
	List<Searchable> getRelatedSearchables();

	/**
	 * Returns the {@link EntityType} represented by this searchable.
	 *
	 * @return The {@code EntityType} represented by this searchable is
	 *         returned.
	 */
	EntityType getEntityType();

	/**
	 * Returns {@code true} if this searchable doesn't have further related
	 * searchables.
	 *
	 * @return True if this instance does not relate further searchables.
	 */
	default boolean isLeaf() {
		return getRelatedSearchables().isEmpty();
	}

}
