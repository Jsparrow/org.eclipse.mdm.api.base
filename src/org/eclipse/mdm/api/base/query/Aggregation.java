/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
	 * Within the result rows for the selected attribute all distinct values
	 * are counted.
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
	 * All result rows for the selected attribute are summed. Only for
	 * numerical values.
	 */
	SUM,

	/**
	 * Within the result rows for the selected attribute all distinct values
	 * are collected.
	 */
	DISTINCT

}
