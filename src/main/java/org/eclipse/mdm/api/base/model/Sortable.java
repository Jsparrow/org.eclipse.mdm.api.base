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

import java.util.Comparator;

/**
 * This interface extends the {@link Entity} interface and provides getter and
 * setter methods for the 'SortIndex' field of an entity. The value in this
 * field is the index of an entity relative to all other entities of the same
 * type beneath their shared parent.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Sortable extends Entity {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * This {@code Comparator} compares entities implementing the
	 * {@link Sortable} interface by the mandatory {@link #ATTR_SORT_INDEX}
	 * property in ascending order.
	 */
	Comparator<Sortable> COMPARATOR = Comparator.comparing(Sortable::getSortIndex);

	/**
	 * The 'SortIndex' attribute name.
	 */
	String ATTR_SORT_INDEX = "Sortindex";

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the sort index of this entity.
	 *
	 * @return The sort index is returned.
	 */
	default Integer getSortIndex() {
		return getValue(ATTR_SORT_INDEX).extract();
	}

	/**
	 * Sets new sort index for this entity.
	 *
	 * @param sortIndex
	 *            The new sort index.
	 */
	default void setSortIndex(Integer sortIndex) {
		getValue(ATTR_SORT_INDEX).set(sortIndex);
	}

}
