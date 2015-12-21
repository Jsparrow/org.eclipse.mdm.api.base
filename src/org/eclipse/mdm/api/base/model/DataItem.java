/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.Map;

/**
 * This is the base interface for any modeled data item.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface DataItem {

	// ======================================================================
	// Class variables
	// ======================================================================

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

	/**
	 * Returns the {@link URI} of this data item, which is a unique identifier
	 * within the data source.
	 *
	 * @return The returned {@code URI} is never null.
	 */
	URI getURI();

	/**
	 * Returns the name of this data item.
	 *
	 * @return Name of this instance is returned.
	 */
	default String getName() {
		return getValue(ATTR_NAME).extract();
	}

	/**
	 * Sets a new name for this data item.
	 *
	 * @param name Passed name is not allowed to be null.
	 */
	default void setName(String name) {
		getValue(ATTR_NAME).set(name);
	}

	/**
	 * Returns the {@link MimeType} of this data item.
	 *
	 * @return The returned {@code MimeType} is never null.
	 */
	default MimeType getMimeType() {
		return new MimeType(getValue(ATTR_MIMETYPE).extract());
	}

	/**
	 * Sets a new {@link MimeType} for this data item.
	 *
	 * @param mimeType Passed {@code MimeType} is not allowed to be null.
	 */
	default void setMimeType(MimeType mimeType) {
		getValue(ATTR_MIMETYPE).set(mimeType.toString());
	}

	/**
	 * Returns the attribute value container associated with passed name.
	 *
	 * @param name Name of the attribute.
	 * @return The value container or null if none found is returned.
	 * @see Value
	 */
	default Value getValue(String name) {
		return getValues().get(name);
	}

	/**
	 * Returns <i>all</i> attribute value containers of this data item
	 * mapped by their name (no matter a value is valid or not).
	 *
	 * @return Returns an unmodifiable view of the internal map using
	 * 		{@link Collections#unmodifiableMap(Map)}.
	 * @see Value
	 */
	Map<String, Value> getValues();

	/**
	 * Returns directly related data items with 1:1 cardinality. If it is allowed
	 * to replace a related data item, then the implementation of the modeled
	 * data item has to provide a corresponding setter method, which replaces the
	 * related data item in the internal map. The corresponding relations will be
	 * written on insert or update operations for this data item.
	 *
	 * @return Returns an unmodifiable view of the internal map using
	 * 		{@link Collections#unmodifiableMap(Map)}.
	 */
	default Map<Class<? extends DataItem>, DataItem> getRelatedDataItems() {
		return Collections.emptyMap();
	}

	/**
	 * Returns a human readable {@code String} representation of this data item.
	 *
	 * @return The {@code String} representation of this data item.
	 */
	@Override
	String toString();

}
