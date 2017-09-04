/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

/**
 * The operator enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Condition
 * @see Filter
 */
public enum BooleanOperator {

	// ======================================================================
	// Enumerations
	// ======================================================================


	/**
	 * Logical conjunction.
	 */
	AND,

	/**
	 * Logical disjunction.
	 */
	OR,

	/**
	 * Logical Negation.
	 */
	NOT

}
