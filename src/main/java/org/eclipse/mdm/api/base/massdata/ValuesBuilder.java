/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.lang.reflect.Array;

import org.eclipse.mdm.api.base.model.ScalarType;

/**
 * TODO
 * This is a base values builder withvarious protected
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
abstract class ValuesBuilder {

	// ======================================================================
	// Instance variables
	// ======================================================================

	protected final WriteRequest writeRequest;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest The {@link WriteRequest} given values and
	 * 		corresponding {@link ScalarType} will be applied to.
	 */
	ValuesBuilder(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	/**
	 * Adds given value sequence and sets the corresponding {@link ScalarType}
	 * as the raw {@code ScalarType}.
	 *
	 * @param scalarType The {@code ScalarType}.
	 * @param values The array value sequence must be assignment compatible
	 * 		with the type represented by the given {@code ScalarType}.
	 */
	protected final void createValues(ScalarType scalarType, Object values) {
		if(values == null) {
			throw new IllegalArgumentException("Values is not allowed to be null.");
		}

		writeRequest.setRawScalarType(scalarType);
		writeRequest.setValues(values);
		writeRequest.setAllValid();
	}

	/**
	 * Adds given value sequence, associated validity flags and sets the
	 * corresponding {@link ScalarType} as the raw {@code ScalarType}.
	 *
	 * @param scalarType The {@code ScalarType}.
	 * @param values The array value sequence must be assignment compatible
	 * 		with the type represented by the given {@code ScalarType}.
	 * @param flags The validity flags for each value in the values sequence.
	 */
	protected final void createValues(ScalarType scalarType, Object values, boolean[] flags) {
		if(values == null || flags == null) {
			throw new IllegalArgumentException("Neither values nor flags are allowed to be null.");
		} else if(Array.getLength(values) != flags.length) {
			throw new IllegalArgumentException("Length of values and flags must be equal.");
		}

		writeRequest.setRawScalarType(scalarType);
		writeRequest.setValues(values);
		writeRequest.setFlags(flags);
	}

}
