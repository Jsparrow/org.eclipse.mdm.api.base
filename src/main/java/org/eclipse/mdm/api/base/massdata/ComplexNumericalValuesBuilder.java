/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.DoubleComplex;
import org.eclipse.mdm.api.base.model.FloatComplex;
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
 * 	<li>{@link ScalarType#FLOAT_COMPLEX}</li>
 * 	<li>{@link ScalarType#DOUBLE_COMPLEX}</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class ComplexNumericalValuesBuilder extends NumericalValuesBuilder {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest The {@link WriteRequest} given values will be added
	 * 		to.
	 */
	ComplexNumericalValuesBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Adds given {@link FloatComplex} values to the {@link WriteRequest} with
	 * a global validity flag, which means the given sequence does not contain
	 * any {@code null} values.
	 *
	 * @param values The {@code FloatComplex} array sequence.
	 * @return The {@link UnitBuilder} is returned.
	 */
	public final UnitBuilder floatComplexValues(FloatComplex[] values) {
		createValues(ScalarType.FLOAT_COMPLEX, values);
		return new UnitBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@link FloatComplex} values to the {@link WriteRequest} with
	 * a validity flag for each given value.
	 *
	 * @param values The {@code FloatComplex} array sequence.
	 * @param flags The validity flags for each {@code FloatComplex} value.
	 * @return The {@link UnitBuilder} is returned.
	 */
	public final UnitBuilder floatComplexValues(FloatComplex[] values, boolean[] flags) {
		createValues(ScalarType.FLOAT_COMPLEX, values, flags);
		return new UnitBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@link DoubleComplex} values to the {@link WriteRequest} with
	 * a global validity flag, which means the given sequence does not contain
	 * any {@code null} values.
	 *
	 * @param values The {@code DoubleComplex} array sequence.
	 * @return The {@link UnitBuilder} is returned.
	 */
	public final UnitBuilder doubleComplexValues(DoubleComplex[] values) {
		createValues(ScalarType.DOUBLE_COMPLEX, values);
		return new UnitBuilder(getWriteRequest());
	}

	/**
	 * Adds given {@link DoubleComplex} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code DoubleComplex} array sequence.
	 * @param flags The validity flags for each {@code DoubleComplex} value.
	 * @return The {@link UnitBuilder} is returned.
	 */
	public final UnitBuilder doubleComplexValues(DoubleComplex[] values, boolean[] flags) {
		createValues(ScalarType.DOUBLE_COMPLEX, values, flags);
		return new UnitBuilder(getWriteRequest());
	}

}
