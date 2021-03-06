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


package org.eclipse.mdm.api.base.query;

/**
 * Aggregation enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Query
 */
public enum Aggregation {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * No aggregate function is used for the selected attribute.
	 */
	NONE,

	/**
	 * Used to count the number of result rows.
	 */
	COUNT,

	/**
	 * Within the result rows for the selected attribute all distinct values are
	 * counted.
	 */
	DISTINCT_COUNT,

	/**
	 * Within the result rows for the selected attribute the smallest value is
	 * selected. Only for numerical values.
	 */
	MINIMUM,

	/**
	 * Within the result rows for the selected attribute the highest value is
	 * selected. Only for numerical values.
	 */
	MAXIMUM,

	/**
	 * Over all result rows for the selected attribute the average value is
	 * calculated. Only for numerical values.
	 */
	AVERAGE,

	/**
	 * Over all result rows for the selected attribute the standard deviation
	 * value is calculated. Only for numerical values.
	 */
	DEVIATION,

	/**
	 * All result rows for the selected attribute are summed. Only for numerical
	 * values.
	 */
	SUM,

	/**
	 * Within the result rows for the selected attribute all distinct values are
	 * collected.
	 */
	DISTINCT

}
