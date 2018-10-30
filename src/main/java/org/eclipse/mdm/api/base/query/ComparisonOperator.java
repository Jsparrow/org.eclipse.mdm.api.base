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

import java.util.Arrays;

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.model.ValueType;

/**
 * The selection operations define query instructions like "equal", "greater",
 * etc. Such operations are case sensitive. If an insensitive instruction is
 * required, then its counterpart starting with the "CI_" prefix has to be used.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Condition
 * @see Filter
 */
public enum ComparisonOperator {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * Exact match
	 */
	EQUAL,

	/**
	 * Not equal (!=)
	 */
	NOT_EQUAL,

	/**
	 * Less than (&lt;)
	 */
	LESS_THAN,

	/**
	 * Less than equal (&lt;=)
	 */
	LESS_THAN_OR_EQUAL,

	/**
	 * Greater than (&gt;)
	 */
	GREATER_THAN,

	/**
	 * Greater than equal (&gt;=)
	 */
	GREATER_THAN_OR_EQUAL,

	/**
	 * In set, value can be a sequence.
	 */
	IN_SET,

	/**
	 * Not in set, value can be a sequence.
	 */
	NOT_IN_SET,

	/**
	 * like, use pattern matching, see Pattern for the wildcard definitions.
	 */
	LIKE,

	/**
	 * Not LIKE
	 */
	NOT_LIKE,

	/**
	 * Equal. case insensitive for {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_EQUAL,

	/**
	 * Not equal. case insensitive for {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_NOT_EQUAL,

	/**
	 * Less than. case insensitive for {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_LESS_THAN,

	/**
	 * Less than equal. case insensitive for {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_LESS_THAN_OR_EQUAL,

	/**
	 * Greater than. case insensitive for {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_GREATER_THAN,

	/**
	 * Greater than equal. case insensitive for {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_GREATER_THAN_OR_EQUAL,

	/**
	 * In set, value can be a sequence. case insensitive for
	 * {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_IN_SET,

	/**
	 * Not in set, value can be a sequence. case insensitive for
	 * {@link ValueType#STRING}
	 */
	CASE_INSENSITIVE_NOT_IN_SET,

	/**
	 * like, use pattern matching, see Pattern for the wildcard definitions.
	 * case insensitive for DT_STRING.
	 */
	CASE_INSENSITIVE_LIKE,

	/**
	 * Not LIKE, case insensitive for DT_STRING.
	 */
	CASE_INSENSITIVE_NOT_LIKE,

	/**
	 * Value is NULL
	 */
	IS_NULL,

	/**
	 * Value is not NULL
	 */
	IS_NOT_NULL,

	/**
	 * Between; selects all instances where the value of 'attr' lies between the
	 * first two values given in 'value' (with 'attr', 'value' being elements of
	 * the SelValue structure; 'value' must be of data type S_*).
	 */
	BETWEEN;

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link Condition} for given {@link Attribute} and value.
	 *
	 * @param attribute
	 *            The {@code Attribute} the condition will be applied to.
	 * @param value
	 *            The value.
	 * @return The created {@code Condition} is returned.
	 */
	public Condition create(Attribute attribute, Object value) {
		return new Condition(attribute, this, "", value);
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Checks whether this operation requires a sequence value container. This
	 * is the case for the following types:
	 *
	 * <ul>
	 * <li>{@link ComparisonOperator#IN_SET}</li>
	 * <li>{@link ComparisonOperator#NOT_IN_SET}</li>
	 * <li>{@link ComparisonOperator#CASE_INSENSITIVE_IN_SET}</li>
	 * <li>{@link ComparisonOperator#CASE_INSENSITIVE_NOT_IN_SET}</li>
	 * <li>{@link ComparisonOperator#BETWEEN}</li>
	 * </ul>
	 *
	 * @return True if this operation is one of those listed above.
	 */
	boolean requiresSequence() {
		return Arrays.asList(IN_SET, NOT_IN_SET, CASE_INSENSITIVE_IN_SET, CASE_INSENSITIVE_NOT_IN_SET, BETWEEN)
				.contains(this);
	}

}
