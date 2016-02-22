/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.ScalarType;

public class MeasuredValuesNumericalBuilder extends MeasuredValuesBuilder {

	MeasuredValuesNumericalBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	public UnitIndependentBuilder byteValues(byte[] values) {
		createMeasuredValues(ScalarType.BYTE, values);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder byteValues(byte[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.BYTE, values, flags);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder shortValues(short[] values) {
		createMeasuredValues(ScalarType.SHORT, values);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder shortValues(short[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.SHORT, values, flags);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder integerValues(int[] values) {
		createMeasuredValues(ScalarType.INTEGER, values);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder integerValues(int[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.INTEGER, values, flags);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder longValues(long[] values) {
		createMeasuredValues(ScalarType.LONG, values);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder longValues(long[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.LONG, values, flags);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder floatValues(float[] values) {
		createMeasuredValues(ScalarType.FLOAT, values);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder floatValues(float[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.FLOAT, values, flags);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder doubleValues(double[] values) {
		createMeasuredValues(ScalarType.DOUBLE, values);
		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitIndependentBuilder doubleValues(double[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.DOUBLE, values, flags);
		return new UnitIndependentBuilder(writeRequest);
	}

}
