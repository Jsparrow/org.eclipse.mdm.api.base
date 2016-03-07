/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This class represents a complex value with real and imaginary parts of type
 * {@code float}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class FloatComplex {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final float imaginary;
	private final float real;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param real The real part.
	 * @param imaginary The imaginary part.
	 */
	public FloatComplex(float real, float imaginary) {
		this.imaginary = imaginary;
		this.real = real;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Parses given {@code String} where real and imaginary parts are separated
	 * by one or more blanks. Furthermore both must be floating point numbers
	 * that can be parsed by calling {@link Float#valueOf(String)}.
	 *
	 * @param s The {@code String} to be parsed.
	 * @return The parsed {@link FloatComplex} value is returned.
	 * @throws NumberFormatException Thrown if unable to parse the complex value.
	 */
	public static FloatComplex valueOf(String s) {
		String[] values = s.split(" +");
		if(values.length != 2) {
			throw new NumberFormatException("Unable to parse complex value.");
		}

		return new FloatComplex(Float.valueOf(values[0]), Float.valueOf(values[1]));
	}

	/**
	 * Returns the real part of this complex value.
	 *
	 * @return The real part is returned.
	 */
	public float real() {
		return real;
	}

	/**
	 * Returns the imaginary part of this complex value.
	 *
	 * @return The imaginary part is returned.
	 */
	public float imaginary() {
		return imaginary;
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
