/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.ScalarType;

/**
 * This builder adds values to the {@link WriteRequest} of types listed below.
 * It is possible to add a sequence where all of its values are valid.
 * Additionally it is possible to provide a validity flag for each single
 * value. In either case neither values nor flags are allowed to be {@code
 * null}. Furthermore if values and flags are given, then their length must be
 * equal.
 *
 * <ul>
 * 	<li>{@link ScalarType#BYTE}</li>
 * 	<li>{@link ScalarType#SHORT}</li>
 * 	<li>{@link ScalarType#INTEGER}</li>
 * 	<li>{@link ScalarType#LONG}</li>
 * 	<li>{@link ScalarType#FLOAT}</li>
 * 	<li>{@link ScalarType#DOUBLE}</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class NumericalValuesBuilder extends BaseValuesBuilder {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest The {@link WriteRequest} given values will be added
	 * 		to.
	 */
	NumericalValuesBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Adds given {@code byte} values to the {@link WriteRequest} with a global
	 * validity flag.
	 *
	 * @param values The {@code byte} array sequence.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder byteValues(byte[] values) {
		createValues(ScalarType.BYTE, values);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code byte} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code byte} array sequence.
	 * @param flags The validity flags for each {@code byte} value.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder byteValues(byte[] values, boolean[] flags) {
		createValues(ScalarType.BYTE, values, flags);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code short} values to the {@link WriteRequest} with a
	 * global validity flag.
	 *
	 * @param values The {@code short} array sequence.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder shortValues(short[] values) {
		createValues(ScalarType.SHORT, values);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code short} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code short} array sequence.
	 * @param flags The validity flags for each {@code short} value.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder shortValues(short[] values, boolean[] flags) {
		createValues(ScalarType.SHORT, values, flags);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code int} values to the {@link WriteRequest} with a global
	 * validity flag.
	 *
	 * @param values The {@code int} array sequence.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder integerValues(int[] values) {
		createValues(ScalarType.INTEGER, values);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code int} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code int} array sequence.
	 * @param flags The validity flags for each {@code int} value.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder integerValues(int[] values, boolean[] flags) {
		createValues(ScalarType.INTEGER, values, flags);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code long} values to the {@link WriteRequest} with a global
	 * validity flag.
	 *
	 * @param values The {@code long} array sequence.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder longValues(long[] values) {
		createValues(ScalarType.LONG, values);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code long} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code long} array sequence.
	 * @param flags The validity flags for each {@code long} value.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder longValues(long[] values, boolean[] flags) {
		createValues(ScalarType.LONG, values, flags);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code float} values to the {@link WriteRequest} with a
	 * global validity flag.
	 *
	 * @param values The {@code float} array sequence.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder floatValues(float[] values) {
		createValues(ScalarType.FLOAT, values);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code float} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code float} array sequence.
	 * @param flags The validity flags for each {@code float} value.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder floatValues(float[] values, boolean[] flags) {
		createValues(ScalarType.FLOAT, values, flags);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code double} values to the {@link WriteRequest} with a
	 * global validity flag.
	 *
	 * @param values The {@code double} array sequence.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder doubleValues(double[] values) {
		createValues(ScalarType.DOUBLE, values);
		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@code double} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code double} array sequence.
	 * @param flags The validity flags for each {@code double} value.
	 * @return The {@link UnitIndependentBuilder} is returned.
	 */
	public final UnitIndependentBuilder doubleValues(double[] values, boolean[] flags) {
		createValues(ScalarType.DOUBLE, values, flags);
		return new UnitIndependentBuilder(getWriteRequest());
	}

}
