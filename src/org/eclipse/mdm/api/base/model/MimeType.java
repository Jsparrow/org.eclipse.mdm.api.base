/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Class provides easy to use type comparison methods.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see DataItem
 */
public final class MimeType {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final String mimeType;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param mimeType passed type is not allowed to be null.
	 */
	public MimeType(String mimeType) {
		this.mimeType = mimeType.toLowerCase();
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Checks whether passed {@code MimeType} is either the same as or a sub
	 * type of this instance. See the following examples:
	 *
	 * <pre> {@code
	 * MimeType type = new MimeType("application/x-asam.aomeasurement");
	 * MimeType subType = new MimeType("application/x-asam.aomeasurement.subtype");
	 *
	 * type.isParentOf(subType);  // true
	 * subType.isParentOf(type);  // false
	 * type.isParentOf(type);     // true
	 * }</pre>
	 *
	 * @param mimeType The checked type.
	 * @return Returns true if either this instance and passed type are the
	 * 		same or the passed one is a sub type of this instance.
	 */
	public boolean isParentOf(MimeType mimeType) {
		return mimeType != null && mimeType.compareString().startsWith(compareString());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return mimeType.hashCode();
	}

	/**
	 * Checks whether passed {@code Object} represents the same type as
	 * this instance.
	 *
	 * <pre> {@code
	 * MimeType type = new MimeType("application/x-asam.aomeasurement");
	 * MimeType equalType = new MimeType("application/x-asam.aomeasurement");
	 * MimeType subType = new MimeType("application/x-asam.aomeasurement.subtype");
	 * MimeType anotherType = new MimeType("application/x-asam.aounit");
	 *
	 * type.equals(equalType);   // true
	 * subType.equals(type);     // false
	 * type.equals(anotherType); // false
	 * }</pre>
	 *
	 * @param object The checked object.
	 * @return True if passed object is of type {@code MimeType} and
	 * 		represents the same type as this instance.
	 */
	@Override
	public boolean equals(Object object) {
		if(object == null || !(object instanceof MimeType)) {
			return false;
		}

		return mimeType.equals(((MimeType) object).mimeType);
	}

	/**
	 * Returns the type {@code String}.
	 *
	 * @return The type in lower case is returned.
	 */
	@Override
	public String toString() {
		return mimeType;
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
		return mimeType + '.';
	}

}
