/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

/**
 * Class provides access to statistics of a {@link Channel}'s underlying
 * value sequence. Changes in this statistics will be persisted as soon
 * as the owning {@code Channel} is.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
public final class Statistics {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Minimum' attribute name.
	 */
	public static final String ATTR_MINIMUM = "Minimum";

	/**
	 * The 'Maximum' attribute name.
	 */
	public static final String ATTR_MAXIMUM = "Maximum";

	/**
	 * The 'Average' attribute name.
	 */
	public static final String ATTR_AVERAGE = "Average";

	/**
	 * The 'Deviation' attribute name.
	 */
	public static final String ATTR_DEVIATION = "Deviation";

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Map<String, Value> values;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param values The attribute value container map of the owning
	 * 		{@link Channel}.
	 */
	Statistics(Map<String, Value> values) {
		this.values = values;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the minimum value of this statistics.
	 *
	 * @return The minimum value is returned.
	 */
	public Double getMinimum() {
		return getValue(ATTR_MINIMUM).extract();
	}

	/**
	 * Sets new minimum for this statistics.
	 *
	 * @param minimum The new minimum value.
	 */
	public void setMinimum(Double minimum) {
		getValue(ATTR_MINIMUM).set(minimum);
	}

	/**
	 * Returns the maximum value of this statistics.
	 *
	 * @return The maximum value is returned.
	 */
	public Double getMaximum() {
		return getValue(ATTR_MAXIMUM).extract();
	}

	/**
	 * Sets new maximum for this statistics.
	 *
	 * @param maximum The new maximum value.
	 */
	public void setMaximum(Double maximum) {
		getValue(ATTR_MAXIMUM).set(maximum);
	}

	/**
	 * Returns the average value of this statistics.
	 *
	 * @return The average value is returned.
	 */
	public Double getAverage() {
		return getValue(ATTR_AVERAGE).extract();
	}

	/**
	 * Sets new average for this statistics.
	 *
	 * @param average The new average value.
	 */
	public void setAverage(Double average) {
		getValue(ATTR_AVERAGE).set(average);
	}

	/**
	 * Returns the deviation value of this statistics.
	 *
	 * @return The deviation value is returned.
	 */
	public Double getDeviation() {
		return getValue(ATTR_DEVIATION).extract();
	}

	/**
	 * Sets new deviation for this statistics.
	 *
	 * @param deviation The new deviation value.
	 */
	public void setDeviation(Double deviation) {
		getValue(ATTR_DEVIATION).set(deviation);
	}

	// ======================================================================
	// Private methods
	// ======================================================================

	/**
	 * Returns the attribute value container associated with passed name.
	 *
	 * @param name Name of the attribute.
	 * @return The value container is returned.
	 */
	private Value getValue(String name) {
		return values.get(name);
	}

}
