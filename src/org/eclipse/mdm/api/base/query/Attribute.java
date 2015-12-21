/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.model.ValueType;

/**
 * Represents modeled attribute.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 * @see ValueType
 */
public interface Attribute {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link Entity} this attribute belongs to.
	 *
	 * @return The owning {@code Entity} is returned.
	 */
	Entity getEntity();

	/**
	 * Returns the name of the attribute.
	 *
	 * @return The name is returned.
	 */
	String getName();

	/**
	 * Returns the {@link ValueType} of this attribute.
	 *
	 * @return The {@code ValueType} is returned.
	 */
	ValueType getType();

	/**
	 * Creates a new {@link Value} based on this attribute with an undefined
	 * unit.
	 *
	 * @param value The initial value.
	 * @return The created {@code Value} is returned.
	 */
	default Value create(Object value /** TODO sequence! */) {
		return create("", value);
	}

	/**
	 * TODO: When generating query conditions some operations (INSET, BETWEEN, etc.)
	 * require a sequence value therefore it would be useful if a sequence flag
	 * could be passed to each of the listed create methods...
	 * Proper handling is then realized in the implementations of this interface!
	 */


	/**
	 * Creates a new {@link Value} based on this attribute.
	 *
	 * @param unit The name of the unit.
	 * @param value The initial value.
	 * @return The created {@code Value} is returned.
	 */
	default Value create(String unit, Object value /** TODO sequence! */) {
		return getType().newValue(getName(), unit, value);
	}

}
