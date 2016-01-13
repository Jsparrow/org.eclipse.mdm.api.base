/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Context type enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ContextRoot
 */
public enum ContextType {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * A {@link ContextRoot} of this type unites meta data of the unit under
	 * test.
	 */
	UNITUNDERTEST(false),

	/**
	 * A {@link ContextRoot} of this type unites meta data of the test
	 * conditions.
	 */
	TESTSEQUENCE(false),

	/**
	 * A {@link ContextRoot} of this type unites meta data of the used equipment
	 * (hardware and sensors)
	 */
	TESTEQUIPMENT(true);

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final boolean sensorsAllowed;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param sensorsAllowed Indicates whether this type allows the modeling of
	 * 		sensors.
	 */
	private ContextType(boolean sensorsAllowed) {
		this.sensorsAllowed = sensorsAllowed;
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Returns true if a {@link ContextRoot} of this type supports sensors.
	 *
	 * @return True if sensors are allowed.
	 */
	boolean areSensoresAllowed() {
		return sensorsAllowed;
	}

}
