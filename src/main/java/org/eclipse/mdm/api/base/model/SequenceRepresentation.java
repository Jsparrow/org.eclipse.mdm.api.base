/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * The sequence representation to describe the storage type of measured values.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
public class SequenceRepresentation extends EnumerationValue {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * Measured values are stored as is and values are therefore immediately
	 * available.
	 */
	public static final SequenceRepresentation EXPLICIT = new SequenceRepresentation("EXPLICIT", 0);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> = p for i
	 * &isin; [1, n], n is the total number of values and generation parameter p
	 * (offset).
	 */
	public static final SequenceRepresentation IMPLICIT_CONSTANT = new SequenceRepresentation("IMPLICIT_CONSTANT", 1);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+(i-1)*p<sub>2</sub> for i &isin; [1, n], n is the total
	 * number of values and generation parameters p<sub>1</sub> (start value)
	 * and p<sub>2</sub> (increment).
	 */
	public static final SequenceRepresentation IMPLICIT_LINEAR = new SequenceRepresentation("IMPLICIT_LINEAR", 2);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+((i-1)mod(p<sub>3</sub>-p<sub>1</sub>)/p<sub>2</sub>)*
	 * p<sub>2</sub> for i &isin; [1, n], n is the total number of values and
	 * generation parameters p<sub>1</sub> (start value), p<sub>2</sub>
	 * (increment) and p<sub>3</sub> (number of values per saw). The expression
	 * (p<sub>3</sub>-p<sub>1</sub>)/p<sub>2</sub> must be truncated to integer
	 * to start each saw curve cycle at p<sub>1</sub>.
	 */
	public static final SequenceRepresentation IMPLICIT_SAW = new SequenceRepresentation("IMPLICIT_SAW", 3);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub> for i &isin; [1, n], n is the
	 * total number of values and generation parameters p<sub>1</sub> (offset),
	 * p<sub>2</sub> (factor) and the raw value r at position i.
	 */
	public static final SequenceRepresentation RAW_LINEAR = new SequenceRepresentation("RAW_LINEAR", 4);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> = &sum;
	 * p<sub>j</sub>*r<sub>i</sub><sup>j-1</sup> = p<sub>2</sub>+p<sub>3
	 * </sub>*r<sub>i</sub>+p<sub>4</sub>*r<sub>i</sub><sup>2</sup>+... for i
	 * &isin; [1, n], n is the total number of values and generation parameters
	 * p<sub>j</sub> for j &isin; [1, p<sub>1</sub>] and the raw value r at
	 * position i.
	 */
	public static final SequenceRepresentation RAW_POLYNOMIAL = new SequenceRepresentation("RAW_POLYNOMIAL", 5);

	/*
	 * Not used. But keep here to show ordinal sequence.
	 */
	public static final SequenceRepresentation FORMULA = new SequenceRepresentation("FORMULA", 6);
	
	/**
	 * Measured values are stored as is in an external file and values are
	 * therefore immediately available.
	 */
	public static final SequenceRepresentation EXPLICIT_EXTERNAL = new SequenceRepresentation("EXPLICIT_EXTERNAL", 7);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub> for i &isin; [1, n], n is the
	 * total number of values and generation parameters p<sub>1</sub> (offset),
	 * p<sub>2</sub> (factor) and the raw value r at position i read from an
	 * external file.
	 */
	public static final SequenceRepresentation RAW_LINEAR_EXTERNAL = new SequenceRepresentation("RAW_LINEAR_EXTERNAL", 8);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> = &sum;
	 * p<sub>j</sub>*r<sub>i</sub><sup>j-1</sup> = p<sub>2</sub>+p<sub>3
	 * </sub>*r<sub>i</sub>+p<sub>4</sub>*r<sub>i</sub><sup>2</sup>+... for i
	 * &isin; [1, n], n is the total number of values and generation parameters
	 * p<sub>j</sub> for j &isin; [1, p<sub>1</sub>] and the raw value r at
	 * position i read from an external file.
	 */
	public static final SequenceRepresentation RAW_POLYNOMIAL_EXTERNAL = new SequenceRepresentation("RAW_POLYNOMIAL_EXTERNAL", 9);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * (p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub>)*p<sub>3</sub> for i &isin;
	 * [1, n], n is the total number of values and generation parameters
	 * p<sub>1</sub> (offset), p<sub>2</sub> (factor), p<sub>2</sub>
	 * (calibration) and the raw value r at position i.
	 */
	public static final SequenceRepresentation RAW_LINEAR_CALIBRATED = new SequenceRepresentation("RAW_LINEAR_CALIBRATED", 10);

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * (p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub>)*p<sub>3</sub> for i &isin;
	 * [1, n], n is the total number of values and generation parameters
	 * p<sub>1</sub> (offset), p<sub>2</sub> (factor), p<sub>2</sub>
	 * (calibration) and the raw value r at position i read from an external
	 * file.
	 */
	public static final SequenceRepresentation RAW_LINEAR_CALIBRATED_EXTERNAL = new SequenceRepresentation("RAW_LINEAR_CALIBRATED_EXTERNAL", 11);

	private SequenceRepresentation(String name, int ordinal) {
		super(name, ordinal);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #EXPLICIT}</li>
	 * <li>{@link #EXPLICIT_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isExplicit() {
		return name().startsWith("EXPLICIT");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #IMPLICIT_CONSTANT}</li>
	 * <li>{@link #IMPLICIT_LINEAR}</li>
	 * <li>{@link #IMPLICIT_SAW}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isImplicit() {
		return name().startsWith("IMPLICIT");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #IMPLICIT_CONSTANT}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isConstant() {
		return name().contains("CONSTANT");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #IMPLICIT_LINEAR}</li>
	 * <li>{@link #RAW_LINEAR}</li>
	 * <li>{@link #RAW_LINEAR_EXTERNAL}</li>
	 * <li>{@link #RAW_LINEAR_CALIBRATED}</li>
	 * <li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isLinear() {
		return name().contains("LINEAR");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #RAW_POLYNOMIAL}</li>
	 * <li>{@link #RAW_POLYNOMIAL_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isPolynomial() {
		return name().contains("POLYNOMIAL");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #RAW_LINEAR_CALIBRATED}</li>
	 * <li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isCalibrated() {
		return name().contains("CALIBRATED");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #EXPLICIT_EXTERNAL}</li>
	 * <li>{@link #RAW_LINEAR_EXTERNAL}</li>
	 * <li>{@link #RAW_POLYNOMIAL_EXTERNAL}</li>
	 * <li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isExternal() {
		return name().endsWith("EXTERNAL");
	}

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * <li>{@link #RAW_LINEAR}</li>
	 * <li>{@link #RAW_POLYNOMIAL}</li>
	 * <li>{@link #RAW_LINEAR_EXTERNAL}</li>
	 * <li>{@link #RAW_POLYNOMIAL_EXTERNAL}</li>
	 * <li>{@link #RAW_LINEAR_CALIBRATED}</li>
	 * <li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isRaw() {
		return name().startsWith("RAW");
	}

}
