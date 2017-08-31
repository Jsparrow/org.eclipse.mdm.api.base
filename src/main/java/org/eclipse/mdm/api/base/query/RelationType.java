/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

/**
 * RelationType enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see EntityType
 * @see Relation
 */
public enum RelationType {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * Represents a hierarchical relation between two {@link EntityType}s e.g.:
	 * {@code Test} and {@code TestStep}.
	 */
	FATHER_CHILD,

	/**
	 * Represents an informational relation between two {@link EntityType}s
	 * e.g.: {@code Measurement} and {@code ParameterSet}.
	 */
	INFO,

	/**
	 * Represents an inheritance relation between two {@link EntityType}s.
	 */
	INHERITANCE

}
