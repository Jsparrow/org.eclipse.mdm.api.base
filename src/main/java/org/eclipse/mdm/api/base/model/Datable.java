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
 * This interface extends the {@link Entity} interface and provides getter and
 * setter methods for the 'DateCreated' field of an entity.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Datable extends Entity {

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
	 * Returns the creation time stamp of this entity.
	 *
	 * @return The creation time stamp is returned.
	 */
	default LocalDateTime getDateCreated() {
		return getValue(ATTR_DATE_CREATED).extract();
	}

	/**
	 * Set new creation time stamp for this entity.
	 *
	 * @param dateCreated
	 *            The new creation time stamp.
	 */
	default void setDateCreated(LocalDateTime dateCreated) {
		getValue(ATTR_DATE_CREATED).set(dateCreated);
	}

}
