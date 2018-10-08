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


package org.eclipse.mdm.api.base.adapter;

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
