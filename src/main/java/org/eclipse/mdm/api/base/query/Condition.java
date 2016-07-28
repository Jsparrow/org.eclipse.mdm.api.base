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
 * @see Operation
 */
public final class Condition {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Attribute attribute;
	private final Value value;
	private final Operation operation;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param attribute The {@link Attribute} this condition will be applied to.
	 * @param operation The condition {@link Operation}.
	 * @param unit The unit of the created value.
	 * @param input The condition value.
	 */
	Condition(Attribute attribute, Operation operation, String unit, Object input) {
		this.attribute = attribute;
		this.operation = operation;
		value = operation.requiresSequence() ?
				attribute.createValueSeq(unit, input) : attribute.createValue(unit, input);
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
	 * Returns the condition {@link Operation}.
	 *
	 * @return The condition {@code Operation} is returned.
	 */
	public Operation getOperation() {
		return operation;
	}

}
