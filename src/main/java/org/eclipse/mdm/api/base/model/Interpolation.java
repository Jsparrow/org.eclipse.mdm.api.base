/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This is the interpolation enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public enum Interpolation {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * No interpolation is used.
	 */
	NONE,

	/**
	 * Interpolation is linear.
	 */
	LINEAR,

	/**
	 * Interpolation is application specific.
	 */
	SPECIFIC;

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this interpolation is {@link #NONE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isNone() {
		return NONE == this;
	}

	/**
	 * Returns true if this interpolation is {@link #LINEAR}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isLinear() {
		return LINEAR == this;
	}

	/**
	 * Returns true if this interpolation is {@link #SPECIFIC}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isSpecific() {
		return SPECIFIC == this;
	}

}
