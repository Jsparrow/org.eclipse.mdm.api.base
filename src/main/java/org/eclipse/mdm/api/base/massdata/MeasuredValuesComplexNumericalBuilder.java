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

public class MeasuredValuesComplexNumericalBuilder extends MeasuredValuesNumericalBuilder {

	MeasuredValuesComplexNumericalBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	public UnitBuilder floatComplexValues(FloatComplex[] values) {
		createMeasuredValues(ScalarType.FLOAT_COMPLEX, values);
		return new UnitBuilder(writeRequest);
	}

	public UnitBuilder floatComplexValues(FloatComplex[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.FLOAT_COMPLEX, values, flags);
		return new UnitBuilder(writeRequest);
	}

	public UnitBuilder doubleComplexValues(DoubleComplex[] values) {
		createMeasuredValues(ScalarType.DOUBLE_COMPLEX, values);
		return new UnitBuilder(writeRequest);
	}

	public UnitBuilder doubleComplexValues(DoubleComplex[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.DOUBLE_COMPLEX, values, flags);
		return new UnitBuilder(writeRequest);
	}

}
