/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.ScalarType;
import org.eclipse.mdm.api.base.model.SequenceRepresentation;

public final class WriteRequestBuilder extends MeasuredValuesBuilder {

	WriteRequestBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	public MeasuredValuesAnyTypeBuilder explicit() {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.EXPLICIT);
		return new MeasuredValuesAnyTypeBuilder(writeRequest);
	}

	public UnitBuilder implicitConstant(ScalarType scalarType, double offset) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.IMPLICIT_CONSTANT);

		Object values;
		if(scalarType.isByte()) {
			values = new byte[] { (byte) offset };
		} else if(scalarType.isShort()) {
			values = new short[] { (short) offset };
		} else if(scalarType.isInteger()) {
			values = new int[] { (int) offset };
		} else if(scalarType.isLong()) {
			values = new long[] { (long) offset };
		} else if(scalarType.isFloat()) {
			values = new float[] { (float) offset };
		} else if(scalarType.isDouble()) {
			values = new double[] { offset };
		} else {
			throw new IllegalArgumentException("Scalar type '" + scalarType + "' is not supported.");
		}
		createMeasuredValues(scalarType, values);

		return new UnitBuilder(writeRequest);
	}

	public UnitIndependentBuilder implicitLinear(ScalarType scalarType, double start, double increment) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.IMPLICIT_LINEAR);

		Object values;
		if(scalarType.isByte()) {
			values = new byte[] { (byte) start, (byte) increment };
		} else if(scalarType.isShort()) {
			values = new short[] { (short) start, (short) increment };
		} else if(scalarType.isInteger()) {
			values = new int[] { (int) start, (int) increment };
		} else if(scalarType.isLong()) {
			values = new long[] { (long) start, (long) increment };
		} else if(scalarType.isFloat()) {
			values = new float[] { (float) start, (float) increment };
		} else if(scalarType.isDouble()) {
			values = new double[] { start };
		} else {
			throw new IllegalArgumentException("Scalar type '" + scalarType + "' is not supported.");
		}
		createMeasuredValues(scalarType, values);

		return new UnitIndependentBuilder(writeRequest);
	}

	public UnitBuilder implicitSaw(ScalarType scalarType, double start, double increment, double valuesPerSaw) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.IMPLICIT_SAW);

		Object values;
		if(scalarType.isByte()) {
			values = new byte[] { (byte) start, (byte) increment, (byte) valuesPerSaw };
		} else if(scalarType.isShort()) {
			values = new short[] { (short) start, (short) increment, (short) valuesPerSaw };
		} else if(scalarType.isInteger()) {
			values = new int[] { (int) start, (int) increment, (int) valuesPerSaw };
		} else if(scalarType.isLong()) {
			values = new long[] { (long) start, (long) increment, (long) valuesPerSaw };
		} else if(scalarType.isFloat()) {
			values = new float[] { (float) start, (float) increment, (float) valuesPerSaw };
		} else if(scalarType.isDouble()) {
			values = new double[] { start };
		} else {
			throw new IllegalArgumentException("Scalar type '" + scalarType + "' is not supported.");
		}
		createMeasuredValues(scalarType, values);

		// NOTE: if it ever should be required to make a channel of this type
		// an independent one, then return an UnitIndependentBuilder instead!
		return new UnitBuilder(writeRequest);
	}

	public MeasuredValuesComplexNumericalBuilder rawLinear(double offset, double factor) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR);
		writeRequest.setGenerationParameters(new double[] { offset, factor });
		return new MeasuredValuesComplexNumericalBuilder(writeRequest);
	}

	public MeasuredValuesComplexNumericalBuilder rawPolynomial(double... coefficients) {
		// TODO int grade (> 0), coefficients.length == grade
		if(coefficients == null || coefficients.length < 2) {
			throw new IllegalArgumentException("At least 2 coefficients must be provided");
		}

		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_POLYNOMIAL);
		writeRequest.setGenerationParameters(coefficients);

		// TODO: currently it is possible to define such a channel as independent
		// should we prevent this?!
		return new MeasuredValuesComplexNumericalBuilder(writeRequest);
	}

	public MeasuredValuesComplexNumericalBuilder rawLinearCalibrated(double offset, double factor, double calibration) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR_CALIBRATED);
		writeRequest.setGenerationParameters(new double[] { offset, factor, calibration });
		return new MeasuredValuesComplexNumericalBuilder(writeRequest);
	}

	// ##########################################################################################################################################

	public Object explicitExternal() {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.EXPLICIT_EXTERNAL);
		// TODO new builder for external component structure for all types
		// subsequent builder should route to independency builder for sortable types (byte, short, int, long, float, double, date)
		// see #explicit()
		throw new IllegalStateException();
	}

	public Object rawLinearExternal(double offset, double factor) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR_EXTERNAL);
		writeRequest.setGenerationParameters(new double[] { offset, factor });

		// TODO new builder for external component structure for numerical, non complex, types
		// subsequent builder should route to independency builder
		// see #rawLinear(offset, factor)
		throw new IllegalStateException();
	}

	public Object rawPolynomialExternal(double... coefficients) {
		if(coefficients == null || coefficients.length < 2) {
			throw new IllegalArgumentException("At least 2 coefficients must be provided");
		}

		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_POLYNOMIAL_EXTERNAL);
		writeRequest.setGenerationParameters(coefficients);
		// TODO new builder for external component structure for numerical, non complex, types
		// subsequent builder should route to independency builder (or should this be preventd!?)
		// see #rawLinear(offset, factor)
		throw new IllegalStateException();
	}

	public Object rawLinearCalibratedExternal(double offset, double factor, double calibration) {
		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR_CALIBRATED_EXTERNAL);
		writeRequest.setGenerationParameters(new double[] { offset, factor, calibration });

		// TODO new builder for external component structure for numerical, non complex, types
		// subsequent builder should route to independency builder
		// see #rawLinear(offset, factor)
		throw new IllegalStateException();
	}

}
