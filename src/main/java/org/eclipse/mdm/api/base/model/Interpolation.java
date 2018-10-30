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
	public static final Interpolation NONE = new Interpolation("NONE", 0);

	/**
	 * Interpolation is linear.
	 */
	public static final Interpolation LINEAR = new Interpolation("LINEAR", 1);

	/**
	 * Interpolation is application specific.
	 */
	public static final Interpolation SPECIFIC = new Interpolation("SPECIFIC", 2);

	private Interpolation(String name, int ordinal) {
		super(name, ordinal);
	}

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

}
