/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.Objects;

/**
 * Filter item contains either a {@link Condition} or an {@link BooleanOperator}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class FilterItem {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * Contains the {@link BooleanOperator#AND}.
	 */
	static final FilterItem AND = new FilterItem(BooleanOperator.AND);

	/**
	 * Contains the {@link BooleanOperator#OR}.
	 */
	static final FilterItem OR = new FilterItem(BooleanOperator.OR);

	/**
	 * Contains the {@link BooleanOperator#NOT}.
	 */
	static final FilterItem NOT = new FilterItem(BooleanOperator.NOT);

	/**
	 * Contains the {@link BooleanOperator#OPEN}.
	 */
	static final FilterItem OPEN = new FilterItem(BracketOperator.OPEN);

	/**
	 * Contains the {@link BooleanOperator#CLOSE}.
	 */
	static final FilterItem CLOSE = new FilterItem(BracketOperator.CLOSE);

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Condition condition;
	private final BooleanOperator booleanOperator;
	private final BracketOperator bracketOperator;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param condition
	 *            The {@link Condition}.
	 */
	FilterItem(Condition condition) {
		this.condition = condition;
		this.bracketOperator = null;
		this.booleanOperator = null;
	}

	/**
	 * Constructor.
	 *
	 * @param booleanOperator
	 *            The {@link BooleanOperator}.
	 */
	private FilterItem(BooleanOperator booleanOperator) {
		this.condition = null;
		this.bracketOperator = null;
		this.booleanOperator = booleanOperator;
	}
	
	/**
	 * Constructor.
	 *
	 * @param bracketoperator
	 *            The {@link BracketOperator}.
	 */
	private FilterItem(BracketOperator bracketoperator) {
		this.condition = null;
		this.booleanOperator = null;
		this.bracketOperator = bracketoperator;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Checks whether an {@link BooleanOperator} is contained.
	 *
	 * @return True if an {@code BooleanOperator} is contained.
	 */
	public boolean isBooleanOperator() {
		return booleanOperator != null;
	}
	

	/**
	 * Returns the contained {@link BooleanOperator}.
	 *
	 * @return The {@code BooleanOperator} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@code BooleanOperator} is not contained.
	 */
	public BooleanOperator getBooleanOperator() {
		if (isBooleanOperator()) {
			return booleanOperator;
		}

		throw new IllegalStateException("Item does not contain an booleanOperator.");
	}

	/**
	 * Checks whether an {@link Condition} is contained.
	 *
	 * @return True if an {@code Condition} is contained.
	 */
	public boolean isCondition() {
		return condition != null;
	}

	/**
	 * Returns the contained {@link Condition}.
	 *
	 * @return The {@code Condition} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@code Condition} is not contained.
	 */
	public Condition getCondition() {
		if (isCondition()) {
			return condition;
		}

		throw new IllegalStateException("Item does not contain a condition.");
	}
	
	/**
	 * Checks whether an {@link BrackerOperator} is contained.
	 *
	 * @return True if an {@code BracketOperator} is contained.
	 */
	public boolean isBracketOperator() {
		return bracketOperator != null;
	}
	
	/**
	 * Returns the contained {@link Condition}.
	 *
	 * @return The {@code Condition} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@code Condition} is not contained.
	 */
	public BracketOperator getBracketOperator() {
		if (isBracketOperator()) {
			return bracketOperator;
		}

		throw new IllegalStateException("Item does not contain a condition.");
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
		if (!(other instanceof FilterItem)) {
			return false;
		}

		FilterItem filterItem = (FilterItem) other;

		return Objects.equals(this.condition, filterItem.condition)
				&& Objects.equals(this.booleanOperator, filterItem.booleanOperator)
				&& Objects.equals(this.bracketOperator, filterItem.bracketOperator);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(condition, booleanOperator, bracketOperator);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if (isBooleanOperator()) {
			return " " + booleanOperator.toString() + " ";
		} else if (isBracketOperator()) {
			return bracketOperator.toString();
		} else {
			return condition.toString();
		}
	}

}
