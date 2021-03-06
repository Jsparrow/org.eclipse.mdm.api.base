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
 * This is the axis type enumeration as defined in the ASAM ODS NVH model.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
public class AxisType extends EnumerationValue {

	/**
	 * A {@link Channel} of this type may be displayed as the x-axis.
	 */
	public static final AxisType X_AXIS = new AxisType("X_AXIS", 0);

	/**
	 * A {@link Channel} of this type may be displayed as the y-axis.
	 */
	public static final AxisType Y_AXIS = new AxisType("Y_AXIS", 1);

	/**
	 * A {@link Channel} of this type may be displayed as the x- or y-axis.
	 */
	public static final AxisType XY_AXIS = new AxisType("XY_AXIS", 2);

	private AxisType(String name, int ordinal) {
		super(name, ordinal);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this axis type is {@link #X_AXIS}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isXAxis() {
		return X_AXIS == this;
	}

	/**
	 * Returns true if this axis type is {@link #Y_AXIS}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isYAxis() {
		return Y_AXIS == this;
	}

	/**
	 * Returns true if this axis type is {@link #XY_AXIS}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isXYAxis() {
		return XY_AXIS == this;
	}

}
