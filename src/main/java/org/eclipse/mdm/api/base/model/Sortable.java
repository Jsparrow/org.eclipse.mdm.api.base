/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This interface extends the {@link Entity} interface and provides getter
 * and setter methods for the 'SortIndex' field of an entity. The value in
 * this field is the index of an entity relative to all other entities of
 * the same type beneath their shared parent.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Sortable<T extends Sortable<T>> extends Entity, Comparable<T> {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'SortIndex' attribute name.
	 */
	static final String ATTR_SORT_INDEX = "Sortindex";

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
	 * @param sortIndex The new sort index.
	 */
	default void setSortIndex(Integer sortIndex) {
		getValue(ATTR_SORT_INDEX).set(sortIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	default int compareTo(T other) {
		return getSortIndex().compareTo(other.getSortIndex());
	}

}
