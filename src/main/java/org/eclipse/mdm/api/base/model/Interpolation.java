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
public class Interpolation extends EnumerationValue {

	/**
	 * No interpolation is used.
	 */
	public static final Interpolation NONE = new Interpolation(0);

	/**
	 * Interpolation is linear.
	 */
	public static final Interpolation LINEAR = new Interpolation(1);

	/**
	 * Interpolation is application specific.
	 */
	public static final Interpolation SPECIFIC = new Interpolation(2);

	/**
	 * Returns true if this interpolation is {@link #NONE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isNone() {
		return NONE == this;
	}

	/**
	 * Returns true if this interpolation is {@link #LINEAR}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isLinear() {
		return LINEAR == this;
	}

	/**
	 * Returns true if this interpolation is {@link #SPECIFIC}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isSpecific() {
		return SPECIFIC == this;
	}

	/**
	 * Constructor, sets the order
	 */
	Interpolation(int ord) {
		super(ord);
	}

}
