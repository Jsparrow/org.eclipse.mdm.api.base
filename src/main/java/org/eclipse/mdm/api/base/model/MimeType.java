/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Locale;

/**
 * Class provides easy to use MIME type comparison methods.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 */
public final class MimeType {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final String internal;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param mimeType The MIME type will be changed to lower case.
	 */
	public MimeType(String mimeType) {
		internal = mimeType.toLowerCase(Locale.ROOT);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	public MimeType addSubType(String name) {
		return new MimeType(internal + '.' + name);
	}

	/**
	 * Checks whether given {@code MimeType} is either the same as or a sub
	 * type of this MIME type. See the following examples:
	 *
	 * <pre>
	 * MimeType type = new MimeType("application/x-asam.aomeasurement");
	 * MimeType subType = new MimeType("application/x-asam.aomeasurement.subtype");
	 *
	 * type.isParentOf(subType);  // true
	 * subType.isParentOf(type);  // false
	 * type.isParentOf(type);     // true
	 * </pre>
	 *
	 * @param mimeType The checked type.
	 * @return Returns true if either this MIME type and given one are the
	 * 		same or the given one is a sub type of this MIME type.
	 */
	public boolean isParentOf(MimeType mimeType) {
		return mimeType != null && mimeType.compareString().startsWith(compareString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return internal.hashCode();
	}

	/**
	 * Checks whether given {@code Object} represents the same type as this
	 * MIME type.
	 *
	 * <pre>
	 * MimeType type = new MimeType("application/x-asam.aomeasurement");
	 * MimeType equalType = new MimeType("application/x-asam.aomeasurement");
	 * MimeType subType = new MimeType("application/x-asam.aomeasurement.subtype");
	 * MimeType anotherType = new MimeType("application/x-asam.aounit");
	 *
	 * type.equals(equalType);   // true
	 * subType.equals(type);     // false
	 * type.equals(anotherType); // false
	 * </pre>
	 *
	 * @param object The checked object.
	 * @return True if given object is of type {@code MimeType} and
	 * 		represents exactly the same type as this  MIME type.
	 */
	@Override
	public boolean equals(Object object) {
		if(object instanceof MimeType) {
			return internal.equals(((MimeType) object).internal);
		}

		return false;
	}

	/**
	 * Returns the MIME type.
	 *
	 * @return The MIME type is returned in lower case.
	 */
	@Override
	public String toString() {
		return internal;
	}

	// ======================================================================
	// Private methods
	// ======================================================================

	/**
	 * Adds a termination character to the internally stored string representation.
	 *
	 * @return A closed comparison string is returned.
	 */
	private String compareString() {
		return internal + '.';
	}

}
