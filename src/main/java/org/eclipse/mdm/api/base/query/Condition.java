/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.Value;

/**
 * Describes a filter condition.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Attribute
 * @see ComparisonOperator
 */
public final class Condition {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Attribute attribute;
	private final Value value;
	private final ComparisonOperator comparisonOperator;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param attribute
	 *            The {@link Attribute} this condition will be applied to.
	 * @param comparisonOperator
	 *            The condition {@link ComparisonOperator}.
	 * @param unit
	 *            The unit of the created value.
	 * @param input
	 *            The condition value.
	 */
	Condition(Attribute attribute, ComparisonOperator comparisonOperator, String unit, Object input) {
		this.attribute = attribute;
		this.comparisonOperator = comparisonOperator;
		value = comparisonOperator.requiresSequence() ? attribute.createValueSeq(unit, input)
				: attribute.createValue(unit, input);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the target {@link Attribute}.
	 *
	 * @return The target {@code Attribute} is returned.
	 */
	public Attribute getAttribute() {
		return attribute;
	}

	/**
	 * Returns the condition {@link Value}.
	 *
	 * @return The condition {@code Value} is returned.
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Returns the condition {@link ComparisonOperator}.
	 *
	 * @return The condition {@code ComparisonOperator} is returned.
	 */
	public ComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

}
