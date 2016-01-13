/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.time.LocalDateTime;

/**
 * This interface extends the {@link DataItem} interface and provides getter
 * and setter methods for the 'DateCreated' field of a data item.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Datable extends DataItem {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'DateCreated' attribute name.
	 */
	static final String ATTR_DATE_CREATED = "DateCreated";

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the timestamp of the date when this data item was created.
	 *
	 * @return The creation timestamp is returned.
	 */
	default LocalDateTime getDateCreated() {
		return getValue(ATTR_DATE_CREATED).extract();
	}

	/**
	 * Sets a new timestamp as the creation date.
	 *
	 * @param dateCreated The new creation timestamp.
	 */
	default void setDateCreated(LocalDateTime dateCreated) {
		getValue(ATTR_DATE_CREATED).set(dateCreated);
	}

}
