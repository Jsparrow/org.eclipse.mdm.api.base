/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This interface extends the {@link DataItem} interface and provides getter
 * and setter methods for the 'SortIndex' field of a data item. The value in
 * this field is the index of a data item relative to all other data items of
 * the same type under their shared parent.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Sortable extends DataItem {

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
	 * Returns the sort index of this data item.
	 *
	 * @return The sort index to this data item is returned.
	 */
	default Integer getSortIndex() {
		return getValue(ATTR_SORT_INDEX).extract();
	}

	/**
	 * Sets a new sort index for this data item.
	 *
	 * @param sortIndex The new sort index.
	 */
	default void setSortIndex(Integer sortIndex) {
		getValue(ATTR_SORT_INDEX).set(sortIndex);
	}

}
