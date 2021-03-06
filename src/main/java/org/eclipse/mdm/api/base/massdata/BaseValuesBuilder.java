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


package org.eclipse.mdm.api.base.massdata;

import java.lang.reflect.Array;

import org.eclipse.mdm.api.base.model.ScalarType;

/**
 * This is a base values builder provides methods for configuring values.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
abstract class BaseValuesBuilder {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final WriteRequest writeRequest;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest
	 *            The {@link WriteRequest} given values and corresponding
	 *            {@link ScalarType} will be applied to.
	 */
	BaseValuesBuilder(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	/**
	 * Adds given value sequence and sets the corresponding {@link ScalarType}
	 * as the raw {@code ScalarType}.
	 *
	 * @param scalarType
	 *            The {@code ScalarType}.
	 * @param values
	 *            The array value sequence must be assignment compatible with
	 *            the type represented by the given {@code ScalarType}.
	 */
	protected final void createValues(ScalarType scalarType, Object values) {
		if (values == null) {
			throw new IllegalArgumentException("Values is not allowed to be null.");
		}

		getWriteRequest().setRawScalarType(scalarType);
		getWriteRequest().setValues(values);
	}

	/**
	 * Adds given value sequence, associated validity flags and sets the
	 * corresponding {@link ScalarType} as the raw {@code ScalarType}.
	 *
	 * @param scalarType
	 *            The {@code ScalarType}.
	 * @param values
	 *            The array value sequence must be assignment compatible with
	 *            the type represented by the given {@code ScalarType}.
	 * @param flags
	 *            The validity flags for each value in the values sequence.
	 */
	protected final void createValues(ScalarType scalarType, Object values, boolean[] flags) {
		if (values == null || flags == null) {
			throw new IllegalArgumentException("Neither values nor flags are allowed to be null.");
		} else if (Array.getLength(values) != flags.length) {
			throw new IllegalArgumentException("Length of values and flags must be equal.");
		}

		getWriteRequest().setRawScalarType(scalarType);
		getWriteRequest().setValues(values, flags);
	}

	/**
	 * Returns the {@link WriteRequest}.
	 *
	 * @return The {@code WriteRequest} is returned.
	 */
	protected final WriteRequest getWriteRequest() {
		return writeRequest;
	}

}
