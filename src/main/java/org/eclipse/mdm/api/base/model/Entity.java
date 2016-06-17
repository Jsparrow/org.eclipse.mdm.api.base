/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Comparator;
import java.util.Map;

/**
 * This is the base interface for any modeled entity type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Entity {

	// ======================================================================
	// Class variables
	// ======================================================================

	static final Comparator<Entity> COMPARATOR = Comparator.comparing(Entity::getName);

	/**
	 * The 'MimeType' attribute name.
	 */
	static final String ATTR_MIMETYPE = "MimeType";

	/**
	 * The 'Name' attribute name.
	 */
	static final String ATTR_NAME = "Name";

	/**
	 * The 'Id' attribute name.
	 */
	static final String ATTR_ID = "Id";

	// ======================================================================
	// Public methods
	// ======================================================================

	String getSourceName();

	String getTypeName();

	Long getID();

	/**
	 * Returns the name of this entity.
	 *
	 * @return The name is returned.
	 */
	default String getName() {
		return getValue(ATTR_NAME).extract();
	}

	/**
	 * Sets new name for this entity.
	 *
	 * @param name The new name.
	 */
	default void setName(String name) {
		getValue(ATTR_NAME).set(name);
	}

	/**
	 * Returns the {@link MimeType} of this entity.
	 *
	 * @return The {@code MimeType} is returned.
	 */
	default MimeType getMimeType() {
		return new MimeType(getValue(ATTR_MIMETYPE).extract());
	}

	/**
	 * Sets new {@link MimeType} for this entity.
	 *
	 * @param mimeType The new {@code MimeType}.
	 */
	default void setMimeType(MimeType mimeType) {
		getValue(ATTR_MIMETYPE).set(mimeType.toString());
	}

	/**
	 * Returns the {@link Value} container associated with given name.
	 *
	 * @param name Name of the attribute.
	 * @return The {@code Value} container is returned.
	 */
	Value getValue(String name);

	/**
	 * Returns <i>all</i> {@link Value} containers of this entity mapped by
	 * their name (no matter a value is valid or not).
	 *
	 * @return Returned {@code Map} is unmodifiable.
	 */
	Map<String, Value> getValues();

	/**
	 * Returns a human readable {@code String} representation of this entity.
	 *
	 * @return The {@code String} representation of this entity.
	 */
	@Override
	String toString();

	default boolean nameMatches(String name) {
		return getName().equals(name);
	}

}
