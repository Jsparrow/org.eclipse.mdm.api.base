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

/**
 * Builds measured values write request configurations.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class WriteRequestBuilder extends BaseValuesBuilder {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest The {@link WriteRequest} which will be configured.
	 */
	WriteRequestBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Configures the {@link WriteRequest} to create an explicit sequence of
	 * measured values.
	 *
	 * @return An {@link AnyTypeValuesBuilder} is returned.
	 * @see SequenceRepresentation#EXPLICIT
	 */
	public AnyTypeValuesBuilder explicit() {
		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.EXPLICIT);
		return new AnyTypeValuesBuilder(getWriteRequest());
	}

	/**
	 * Configures the {@link WriteRequest} to create an implicit constant
	 * sequence of measured values. An implicit sequence allows only
	 * numerical {@link ScalarType}s as listed below:
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
	 * <p><b>Note:</b> Given offset will be cast to an assignment compatible
	 * type.
	 *
	 * @param scalarType The {@code ScalarType} of each single measured value
	 * 		in the sequence.
	 * @param offset The constant value.
	 * @return An {@link UnitBuilder} is returned.
	 * @throws IllegalArgumentException Thrown if given {@code ScalarType} is
	 * 		not supported.
	 * @see SequenceRepresentation#IMPLICIT_CONSTANT
	 */
	public UnitBuilder implicitConstant(ScalarType scalarType, double offset) {
		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.IMPLICIT_CONSTANT);

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
		createValues(scalarType, values);

		return new UnitBuilder(getWriteRequest());
	}

	/**
	 * Configures the {@link WriteRequest} to create an implicit linear
	 * sequence of measured values. An implicit sequence allows only
	 * numerical {@link ScalarType}s as listed below:
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
	 * <p><b>Note:</b> Given start and increment will be cast to an assignment
	 * compatible type.
	 *
	 * @param scalarType The {@code ScalarType} of each single measured value
	 * 		in the sequence.
	 * @param start The start value of the line.
	 * @param increment The gradient of the line.
	 * @return An {@link UnitIndependentBuilder} is returned.
	 * @throws IllegalArgumentException Thrown if given {@code ScalarType} is
	 * 		not supported.
	 * @see SequenceRepresentation#IMPLICIT_LINEAR
	 */
	public UnitIndependentBuilder implicitLinear(ScalarType scalarType, double start, double increment) {
		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.IMPLICIT_LINEAR);

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
		createValues(scalarType, values);

		return new UnitIndependentBuilder(getWriteRequest());
	}

	/**
	 * Configures the {@link WriteRequest} to create an implicit saw sequence
	 * of measured values. An implicit sequence allows only numerical {@link
	 * ScalarType}s as listed below:
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
	 * <p><b>Note:</b> Given start, increment and valuesPerSaw will be cast to
	 * an assignment compatible type.
	 *
	 * @param scalarType The {@code ScalarType} of each single measured value
	 * 		in the sequence.
	 * @param start The start value of each saw cycle.
	 * @param increment The increment.
	 * @param valuesPerSaw The number of values per saw.
	 * @return An {@link UnitBuilder} is returned.
	 * @throws IllegalArgumentException Thrown if given {@code ScalarType} is
	 * 		not supported.
	 * @see SequenceRepresentation#IMPLICIT_SAW
	 */
	public UnitBuilder implicitSaw(ScalarType scalarType, double start, double increment, double valuesPerSaw) {
		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.IMPLICIT_SAW);

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
		createValues(scalarType, values);

		// NOTE: if it ever should be required to make a channel of this type
		// an independent one, then return an UnitIndependentBuilder instead!
		return new UnitBuilder(getWriteRequest());
	}

	/**
	 * Configures the {@link WriteRequest} to create a raw linear sequence of
	 * measured values.
	 *
	 * @param offset The offset for each value.
	 * @param factor The factor for each value.
	 * @return A {@link ComplexNumericalValuesBuilder} is returned.
	 * @see SequenceRepresentation#RAW_LINEAR
	 */
	public ComplexNumericalValuesBuilder rawLinear(double offset, double factor) {
		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR);
		getWriteRequest().setGenerationParameters(new double[] { offset, factor });
		return new ComplexNumericalValuesBuilder(getWriteRequest());
	}

	/**
	 * Configures the {@link WriteRequest} to create a raw polynomial sequence of
	 * measured values.
	 *
	 * @param coefficients At least 2 coefficients must be provided.
	 * @return A {@link ComplexNumericalValuesBuilder} is returned.
	 * @throws IllegalArgumentException Thrown if coefficients are missing or
	 * 		their length is less than 2.
	 * @see SequenceRepresentation#RAW_POLYNOMIAL
	 */
	public ComplexNumericalValuesBuilder rawPolynomial(double... coefficients) {
		if(coefficients == null || coefficients.length < 2) {
			throw new IllegalArgumentException("Coefficients either missing or their length is "
					+ "inconsitent with given grade");
		}

		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.RAW_POLYNOMIAL);

		double[] generationParameters = new double[coefficients.length + 1];
		generationParameters[0] = coefficients.length - 1;
		System.arraycopy(coefficients, 0, generationParameters, 1, coefficients.length);
		getWriteRequest().setGenerationParameters(generationParameters);

		// TODO: currently it is possible to define such a channel as independent
		// should we prevent this?!
		return new ComplexNumericalValuesBuilder(getWriteRequest());
	}

	/**
	 * Configures the {@link WriteRequest} to create a raw linear calibrated
	 * sequence of measured values.
	 *
	 * @param offset The offset for each value.
	 * @param factor The factor for each value.
	 * @param calibration The calibration factor.
	 * @return A {@link ComplexNumericalValuesBuilder} is returned.
	 * @see SequenceRepresentation#RAW_LINEAR_CALIBRATED
	 */
	public ComplexNumericalValuesBuilder rawLinearCalibrated(double offset, double factor, double calibration) {
		getWriteRequest().setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR_CALIBRATED);
		getWriteRequest().setGenerationParameters(new double[] { offset, factor, calibration });
		return new ComplexNumericalValuesBuilder(getWriteRequest());
	}

	//	public Object explicitExternal() {
	//		writeRequest.setSequenceRepresentation(SequenceRepresentation.EXPLICIT_EXTERNAL);
	//		// TODO new builder for external component structure for all types
	//		// subsequent builder should route to independency builder for sortable types
	//		// (byte, short, int, long, float, double, date)
	//		// see #explicit()
	//		throw new UnsupportedOperationException("Not implemented.");
	//	}
	//
	//	public Object rawLinearExternal(double offset, double factor) {
	//		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR_EXTERNAL);
	//		writeRequest.setGenerationParameters(new double[] { offset, factor });
	//
	//		// TODO new builder for external component structure for numerical, non complex, types
	//		// subsequent builder should route to independency builder
	//		// see #rawLinear(offset, factor)
	//		throw new UnsupportedOperationException("Not implemented.");
	//	}
	//
	//	public Object rawPolynomialExternal(double... coefficients) {
	//		if(coefficients == null || coefficients.length < 2) {
	//			throw new IllegalArgumentException("Coefficients either missing or their length is "
	//					+ "inconsitent with given grade");
	//		}
	//		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_POLYNOMIAL_EXTERNAL);
	//
	//		double[] generationParameters = new double[coefficients.length + 1];
	//		generationParameters[0] = coefficients.length - 1;
	//		System.arraycopy(coefficients, 0, generationParameters, 1, coefficients.length);
	//		writeRequest.setGenerationParameters(generationParameters);
	//
	//		// TODO new builder for external component structure for numerical, non complex, types
	//		// subsequent builder should route to independency builder (or should this be preventd!?)
	//		// see #rawLinear(offset, factor)
	//		throw new UnsupportedOperationException("Not implemented.");
	//	}
	//
	//	public Object rawLinearCalibratedExternal(double offset, double factor, double calibration) {
	//		writeRequest.setSequenceRepresentation(SequenceRepresentation.RAW_LINEAR_CALIBRATED_EXTERNAL);
	//		writeRequest.setGenerationParameters(new double[] { offset, factor, calibration });
	//
	//		// TODO new builder for external component structure for numerical, non complex, types
	//		// subsequent builder should route to independency builder
	//		// see #rawLinear(offset, factor)
	//		throw new UnsupportedOperationException("Not implemented.");
	//	}

}
