/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This is the axis type enumeration as defined in the ASAM ODS NVH model.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
public enum AxisType {

	/**
	 * A {@link Channel} with of this type may be displayed as the x-axis.
	 */
	X_AXIS,

	/**
	 * A {@link Channel} with of this type may be displayed as the y-axis.
	 */
	Y_AXIS,

	/**
	 * A {@link Channel} with of this type may be displayed as the x- or y-axis.
	 */
	BOTH

}
