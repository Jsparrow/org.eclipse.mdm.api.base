/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This interface extends the {@link DataItem} interface and provides getter
 * and setter methods for the 'Description' field of a data item.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Describable extends DataItem {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Description' attribute name.
	 */
	static final String ATTR_DESCRIPTION = "Description";

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the description of this data item.
	 *
	 * @return The description to this data item is returned.
	 */
	default String getDescription() {
		return getValue(ATTR_DESCRIPTION).extract();
	}

	/**
	 * Sets a new description for this data item.
	 *
	 * @param description The new description.
	 */
	default void setDescription(String description) {
		getValue(ATTR_DESCRIPTION).set(description);
	}

}
