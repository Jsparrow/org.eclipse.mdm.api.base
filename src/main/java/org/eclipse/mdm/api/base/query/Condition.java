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

import java.util.Objects;

import org.eclipse.mdm.api.base.adapter.Attribute;
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
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Condition)) {
			return false;
		}

		Condition condition = (Condition) other;

		return Objects.equals(this.attribute, condition.attribute)
				&& Objects.equals(this.value, condition.value)
				&& Objects.equals(this.comparisonOperator, condition.comparisonOperator);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(attribute, value, comparisonOperator);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(attribute.getEntityType().getName()).append(".").append(attribute.getName()).append(" ").append(comparisonOperator).append(" ")
				.append(value).toString();
	}
}
