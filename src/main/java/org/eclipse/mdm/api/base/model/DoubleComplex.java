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


package org.eclipse.mdm.api.base.model;

/**
 * This class represents a complex value with real and imaginary parts of type
 * {@code double}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class DoubleComplex {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final double re;
	private final double im;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param real
	 *            The real part.
	 * @param imaginary
	 *            The imaginary part.
	 */
	public DoubleComplex(double real, double imaginary) {
		re = real;
		im = imaginary;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Parses given {@code String} where real and imaginary parts are separated
	 * by one or more blanks. Furthermore both must be floating point numbers
	 * that can be parsed by calling {@link Double#valueOf(String)}.
	 *
	 * @param s
	 *            The {@code String} to be parsed.
	 * @return The parsed {@link DoubleComplex} value is returned.
	 * @throws NumberFormatException
	 *             Thrown if unable to parse the complex value.
	 */
	public static DoubleComplex valueOf(String s) {
		String[] values = s.split(" +");
		if (values.length != 2) {
			throw new NumberFormatException("Unable to parse complex value.");
		}

		return new DoubleComplex(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
	}

	/**
	 * Returns the real part of this complex value.
	 *
	 * @return The real part is returned.
	 */
	public double real() {
		return re;
	}

	/**
	 * Returns the imaginary part of this complex value.
	 *
	 * @return The imaginary part is returned.
	 */
	public double imaginary() {
		return im;
	}

	/**
	 * Returns a human readable {@code String} representation of this complex
	 * value. The real and imaginary parts are separated with a blank.
	 *
	 * @return The {@code String} representation of this complex value.
	 */
	@Override
	public String toString() {
		return new StringBuilder().append(real()).append(' ').append(imaginary()).toString();
	}

}
