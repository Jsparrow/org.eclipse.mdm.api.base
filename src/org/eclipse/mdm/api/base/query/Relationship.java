/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

/**
 * Relationship enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 * @see Relation
 */
public enum Relationship {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * Represents a hierarchical relation between two entities e.g.: {@code
	 * Test} and {@code TestStep}.
	 */
	FATHER_CHILD,

	/**
	 * Represents an informational relation between two entities e.g.: {@code
	 * Measurement} and {@code ParameterSet}.
	 */
	INFO,

	/**
	 * Represents an inheritance relation between two entities.
	 */
	INHERITANCE

}
