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
	String ATTR_DATE_CREATED = "DateCreated";

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
