/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

/**
 * Filter item contains either a {@link Condition} or an {@link Operator}.
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
	 * Contains the {@link Operator#AND}.
	 */
	static final FilterItem AND = new FilterItem(Operator.AND);

	/**
	 * Contains the {@link Operator#OR}.
	 */
	static final FilterItem OR = new FilterItem(Operator.OR);

	/**
	 * Contains the {@link Operator#NOT}.
	 */
	static final FilterItem NOT = new FilterItem(Operator.NOT);

	/**
	 * Contains the {@link Operator#OPEN}.
	 */
	static final FilterItem OPEN = new FilterItem(Operator.OPEN);

	/**
	 * Contains the {@link Operator#CLOSE}.
	 */
	static final FilterItem CLOSE = new FilterItem(Operator.CLOSE);

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Condition condition;
	private final Operator operator;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param condition The {@link Condition}.
	 */
	FilterItem(Condition condition) {
		this.condition = condition;
		operator = null;
	}

	/**
	 * Constructor.
	 *
	 * @param operator The {@link Operator}.
	 */
	private FilterItem(Operator operator) {
		condition = null;
		this.operator = operator;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Checks whether an {@link Operator} is contained.
	 *
	 * @return True if an {@code Operator} is contained.
	 */
	public boolean isOperator() {
		return operator != null;
	}

	/**
	 * Returns the contained {@link Operator}.
	 *
	 * @return The {@code Operator} is returned.
	 * @throws IllegalStateException Thrown if {@code Operator} is not
	 * 		contained.
	 */
	public Operator getOperator() {
		if(isOperator()) {
			return operator;
		}

		throw new IllegalStateException("Item does not contain an operator.");
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
	 * @throws IllegalStateException Thrown if {@code Condition} is not
	 * 		contained.
	 */
	public Condition getCondition() {
		if(isCondition()) {
			return condition;
		}

		throw new IllegalStateException("Item does not contain a condition.");
	}

}
