/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

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
public enum Operation {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * Exact match
	 */
	EQ,

	/**
	 * Not equal (!=)
	 */
	NEQ,

	/**
	 * Less than (<)
	 */
	LT,

	/**
	 * Less than equal (<=)
	 */
	LTE,

	/**
	 * Greater than (>)
	 */
	GT,

	/**
	 * Greater than equal (>=)
	 */
	GTE,

	/**
	 * In set, value can be a sequence.
	 */
	INSET(true),

	/**
	 * Not in set, value can be a sequence.
	 */
	NOT_INSET(true),

	/**
	 * like, use  pattern matching, see Pattern for the
	 * wildcard definitions.
	 */
	LIKE,

	/**
	 * Not LIKE
	 */
	NOT_LIKE,

	/**
	 * Equal. case insensitive for {@link ValueType#STRING}
	 */
	CI_EQ,

	/**
	 * Not equal. case insensitive for {@link ValueType#STRING}
	 */
	CI_NEQ,

	/**
	 * Less than. case insensitive for {@link ValueType#STRING}
	 */
	CI_LT,

	/**
	 * Less than equal. case insensitive for {@link ValueType#STRING}
	 */
	CI_LTE,

	/**
	 * Greater than. case insensitive for {@link ValueType#STRING}
	 */
	CI_GT,

	/**
	 * Greater than equal. case insensitive for {@link ValueType#STRING}
	 */
	CI_GTE,

	/**
	 * In set, value can be a sequence. case insensitive for {@link ValueType#STRING}
	 */
	CI_INSET(true),

	/**
	 * Not in set, value can be a sequence. case insensitive for {@link ValueType#STRING}
	 */
	CI_NOT_INSET(true),

	/**
	 * like, use  pattern matching, see Pattern for the wildcard
	 * definitions. case insensitive for DT_STRING.
	 */
	CI_LIKE,

	/**
	 * Not LIKE, case insensitive for DT_STRING.
	 */
	CI_NOT_LIKE,

	/**
	 * Value is NULL
	 */
	IS_NULL,

	/**
	 * Value is not NULL
	 */
	IS_NOT_NULL,

	/**
	 * Between; selects all instances where the value of 'attr' lies between
	 * the first two values given in 'value' (with 'attr', 'value' being
	 * elements of the SelValue structure; 'value' must be of data type S_*).
	 */
	BETWEEN(true);

	// ======================================================================
	// Instance variables
	// ======================================================================

	private boolean sequence;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param sequence Flag indicates whether the corresponding {@link Operation}
	 * 		requires a value sequence or a scalar value.
	 */
	private Operation(boolean sequence) {
		this.sequence = sequence;
	}

	/**
	 * Constructor.
	 */
	private Operation() {
		sequence = false;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link Condition} for given {@link Attribute} and value.
	 *
	 * @param attribute The {@code Attribute} the condition will be applied to.
	 * @param value The value.
	 * @return The created {@code Condition} is returned.
	 */
	public Condition create(Attribute attribute, Object value) {
		return create(attribute, "", value);
	}

	/**
	 * TODO: When generating query conditions some operations (INSET, BETWEEN, etc.)
	 * require a sequence value therefore it would be useful if a sequence flag
	 * could be passed to each of the listed create methods...
	 * Proper handling is then realized in the implementations of the Attribute interface!
	 */

	/**
	 * Creates a new {@link Condition} for given {@link Attribute}, value and
	 * unit.
	 *
	 * @param attribute The {@code Attribute} the condition will be applied to.
	 * @param unit The unit name.
	 * @param value The value.
	 * @return The created {@code Condition} is returned.
	 */
	public Condition create(Attribute attribute, String unit, Object value) {
		return new Condition(attribute, unit, value, this);
	}

}
