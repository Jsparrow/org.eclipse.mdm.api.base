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

/**
 * This interface extends the {@link Entity} interface and provides getter and
 * setter methods for the 'Description' field of an entity.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Describable extends Entity {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Description' attribute name.
	 */
	String ATTR_DESCRIPTION = "Description";

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the description of this entity.
	 *
	 * @return The description is returned.
	 */
	default String getDescription() {
		return getValue(ATTR_DESCRIPTION).extract();
	}

	/**
	 * Sets new description for this entity.
	 *
	 * @param description
	 *            The new description.
	 */
	default void setDescription(String description) {
		getValue(ATTR_DESCRIPTION).set(description);
	}

}
