/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * The sequence representation to describe the mass data storage type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
public enum SequenceRepresentation {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * Mass data is stored as is and values are therefore immediately available.
	 */
	EXPLICIT,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> = p for
	 * i &isin; [1, n], n is the total number of values and generation
	 * parameter p (offset).
	 */
	IMPLICIT_CONSTANT,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+(i-1)*p<sub>2</sub> for i &isin; [1, n], n is the total
	 * number of values and generation parameters p<sub>1</sub> (start value)
	 * and p<sub>2</sub> (increment).
	 */
	IMPLICIT_LINEAR,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+((i-1)mod(p<sub>3</sub>-p<sub>1</sub>)/p<sub>2</sub>)*
	 * p<sub>2</sub> for i &isin; [1, n], n is the total number of values and
	 * generation parameters p<sub>1</sub> (start value), p<sub>2</sub>
	 * (increment) and p<sub>3</sub> (number of values per saw). The
	 * expression (p<sub>3</sub>-p<sub>1</sub>)/p<sub>2</sub> must be
	 * truncated to integer to start each saw curve cycle at p<sub>1</sub>.
	 */
	IMPLICIT_SAW,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub> for i &isin; [1, n], n is the
	 * total number of values and generation parameters p<sub>1</sub> (offset),
	 * p<sub>2</sub> (factor) and the raw value r at position i.
	 */
	RAW_LINEAR,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * &sum; p<sub>j</sub>*r<sub>i</sub><sup>j-1</sup> = p<sub>2</sub>+p<sub>3
	 * </sub>*r<sub>i</sub>+p<sub>4</sub>*r<sub>i</sub><sup>2</sup>+... for
	 * i &isin; [1, n], n is the total number of values and generation
	 * parameters p<sub>j</sub> for j &isin; [1, p<sub>1</sub>] and the raw
	 * value r at position i.
	 */
	RAW_POLYNOMIAL,

	/**
	 * Mass data is stored as is in an external file and values are therefore
	 * immediately available.
	 */
	EXPLICIT_EXTERNAL,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub> for i &isin; [1, n], n is the
	 * total number of values and generation parameters p<sub>1</sub> (offset),
	 * p<sub>2</sub> (factor) and the raw value r at position i read from an
	 * external file.
	 */
	RAW_LINEAR_EXTERNAL,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * &sum; p<sub>j</sub>*r<sub>i</sub><sup>j-1</sup> = p<sub>2</sub>+p<sub>3
	 * </sub>*r<sub>i</sub>+p<sub>4</sub>*r<sub>i</sub><sup>2</sup>+... for
	 * i &isin; [1, n], n is the total number of values and generation
	 * parameters p<sub>j</sub> for j &isin; [1, p<sub>1</sub>] and the raw
	 * value r at position i read from an external file.
	 */
	RAW_POLYNOMIAL_EXTERNAL,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * (p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub>)*p<sub>3</sub> for i &isin;
	 * [1, n], n is the total number of values and generation parameters
	 * p<sub>1</sub> (offset), p<sub>2</sub> (factor), p<sub>2</sub>
	 * (calibration) and the raw value r at position i.
	 */
	RAW_LINEAR_CALIBRATED,

	/**
	 * Each value x<sub>i</sub> is generated as follows: x<sub>i</sub> =
	 * (p<sub>1</sub>+p<sub>2</sub>*r<sub>i</sub>)*p<sub>3</sub> for i &isin;
	 * [1, n], n is the total number of values and generation parameters
	 * p<sub>1</sub> (offset), p<sub>2</sub> (factor), p<sub>2</sub>
	 * (calibration) and the raw value r at position i read from an external
	 * file.
	 */
	RAW_LINEAR_CALIBRATED_EXTERNAL;

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this sequence representation is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #EXPLICIT}</li>
	 * 	<li>{@link #EXPLICIT_EXTERNAL}</li>
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
	 * 	<li>{@link #IMPLICIT_CONSTANT}</li>
	 * 	<li>{@link #IMPLICIT_LINEAR}</li>
	 * 	<li>{@link #IMPLICIT_SAW}</li>
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
	 * 	<li>{@link #IMPLICIT_CONSTANT}</li>
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
	 * 	<li>{@link #IMPLICIT_LINEAR}</li>
	 * 	<li>{@link #RAW_LINEAR}</li>
	 * 	<li>{@link #RAW_LINEAR_EXTERNAL}</li>
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED}</li>
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
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
	 * 	<li>{@link #RAW_POLYNOMIAL}</li>
	 * 	<li>{@link #RAW_POLYNOMIAL_EXTERNAL}</li>
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
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED}</li>
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
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
	 * 	<li>{@link #EXPLICIT_EXTERNAL}</li>
	 * 	<li>{@link #RAW_LINEAR_EXTERNAL}</li>
	 * 	<li>{@link #RAW_POLYNOMIAL_EXTERNAL}</li>
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
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
	 * 	<li>{@link #RAW_LINEAR}</li>
	 * 	<li>{@link #RAW_POLYNOMIAL}</li>
	 * 	<li>{@link #RAW_LINEAR_EXTERNAL}</li>
	 * 	<li>{@link #RAW_POLYNOMIAL_EXTERNAL}</li>
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED}</li>
	 * 	<li>{@link #RAW_LINEAR_CALIBRATED_EXTERNAL}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isRaw() {
		return name().startsWith("RAW");
	}

}
