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
	UNITUNDERTEST,

	/**
	 * A {@link ContextRoot} of this type unites meta data of the test
	 * conditions.
	 */
	TESTSEQUENCE,

	/**
	 * A {@link ContextRoot} of this type unites meta data of the used equipment
	 * (hardware and sensors).
	 */
	TESTEQUIPMENT;

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this context type is {@link #UNITUNDERTEST}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isUnitUnderTest() {
		return UNITUNDERTEST == this;
	}

	/**
	 * Returns true if this context type is {@link #TESTSEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isTestSequence() {
		return TESTSEQUENCE == this;
	}

	/**
	 * Returns true if this context type is {@link #TESTEQUIPMENT}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isTestEquipment() {
		return TESTEQUIPMENT == this;
	}

}
