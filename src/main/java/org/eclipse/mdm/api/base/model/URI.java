/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unique identifier for entities of any type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 */
public final class URI {

	// ======================================================================
	// Class variables
	// ======================================================================

	public static final Pattern DATAITEM_PATTERN = Pattern.compile("mdmDataItem://(\\S+)/(\\S+)/(\\d+)");

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final String sourceName;
	private final String typeName;
	private final Long id;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param sourceName The name of the data source.
	 * @param typeName The name of the type.
	 * @param id The instance ID.
	 */
	public URI(String sourceName, String typeName, Long id) {
		this.sourceName = sourceName;
		this.typeName = typeName;
		this.id = id;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Parses given uri and returns an instance of this class.
	 *
	 * @param uri The {@code String} representation of an identifier.
	 * @return The identifier is returned.
	 */
	public static URI fromString(String uri) {
		Matcher m = DATAITEM_PATTERN.matcher(uri);
		if (m.matches() && m.groupCount() >= 3) {
			return new URI(m.group(1), m.group(2), Long.valueOf(m.group(3)));
		}

		throw new IllegalArgumentException("Unable to parse given URI '" + uri + "'.");
	}

	/**
	 * Returns the name of the data source.
	 *
	 * @return Name of the data source is returned.
	 */
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * Returns the name of the type.
	 *
	 * @return Name of the type is returned.
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * Returns the instance ID. The ID is {@code 0} if the owning entity was
	 * not yet made permanent.
	 *
	 * @return The instance ID is returned or {@code 0} if the entity is not
	 * 		yet persisted.
	 */
	public Long getID() {
		return id;
	}

	/**
	 * Returns a human readable {@code String} representation of this
	 * identifier.
	 *
	 * @return The {@code String} representation of this identifier.
	 */
	@Override
	public String toString() {
		return String.format("mdmDataItem://%s/%s/%s", sourceName, typeName, id);
	}

}
