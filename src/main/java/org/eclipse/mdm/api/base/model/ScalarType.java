/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.lang.reflect.Array;
import java.time.LocalDateTime;

/**
 * Scalar type enumeration is a {@link MeasuredValues} factory. Another context
 * where this enumeration is used is the storage of values, represented by the
 * constants of this enumeration, in their {@code String} representation (see:
 * {@link BaseParameter}).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public enum ScalarType {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code String} values.
	 */
	STRING(ValueType.STRING_SEQUENCE, String.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code LocalDateTime} values.
	 */
	DATE(ValueType.DATE_SEQUENCE, LocalDateTime.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code boolean} values.
	 */
	BOOLEAN(ValueType.BOOLEAN_SEQUENCE, boolean.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code byte} values.
	 */
	BYTE(ValueType.BYTE_SEQUENCE, byte.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code short} values.
	 */
	SHORT(ValueType.SHORT_SEQUENCE, short.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code int} values.
	 */
	INTEGER(ValueType.INTEGER_SEQUENCE, int.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code long} values.
	 */
	LONG(ValueType.LONG_SEQUENCE, long.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code float} values.
	 */
	FLOAT(ValueType.FLOAT_SEQUENCE, float.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code double} values.
	 */
	DOUBLE(ValueType.DOUBLE_SEQUENCE, double.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code byte[]} values.
	 */
	BYTE_STREAM(ValueType.BYTE_STREAM_SEQUENCE, byte[].class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code FloatComplex} values.
	 */
	FLOAT_COMPLEX(ValueType.FLOAT_COMPLEX_SEQUENCE, FloatComplex.class),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code DoubleComplex} values.
	 */
	DOUBLE_COMPLEX(ValueType.DOUBLE_COMPLEX_SEQUENCE, DoubleComplex.class),

	/**
	 * {@link MeasuredValues} are not allowed to be of this type. This constant
	 * may be used in other contexts.
	 */
	ENUMERATION(ValueType.ENUMERATION_SEQUENCE),

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code FileLink} values.
	 */
	FILE_LINK(ValueType.FILE_LINK_SEQUENCE, FileLink.class),

	/**
	 * TODO ...
	 */
	BLOB(ValueType.BLOB, Object.class),

	/**
	 * {@link MeasuredValues} are not allowed to be of this type. This constant
	 * may be used in other contexts.
	 */
	UNKNOWN(ValueType.UNKNOWN);

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final ValueType valueType;
	private final Class<?> arrayType;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param valueType The associated {@link ValueType}.
	 */
	private ScalarType(ValueType valueType) {
		this.valueType = valueType;
		arrayType = null;
	}

	/**
	 * Constructor.
	 *
	 * @param valueType The associated {@link ValueType}.
	 * @param componentType The component type of the array held by instances
	 * 		of {@link MeasuredValues}.
	 */
	private ScalarType(ValueType valueType, Class<?> componentType) {
		this.valueType = valueType;
		arrayType = Array.newInstance(componentType, 0).getClass();
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link MeasuredValues} initialized with given name, unit
	 * and values including the related validity flags.
	 *
	 * @param name This is the name of the corresponding {@link Channel}.
	 * @param unit Name of the unit the contained values are of.
	 * @param input The measured values.
	 * @param flags The validity flags of the measured values.
	 * @return The created {@code MeasuredValues} is returned.
	 * @throws IllegalStateException Thrown if {@link #isEnumeration()} or
	 * 		{@link #isUnknown()} returns {@code true}.
	 * @throws IllegalArgumentException Thrown if given values are not assignment
	 * 		compatible.
	 */
	public MeasuredValues createMeasuredValues(String name, String unit, Object input, boolean[] flags) {
		if(isEnumeration() || isUnknown()) {
			throw new IllegalStateException("It is not allowed to create measured values of type '" + this + "'.");
		} else if(!arrayType.isInstance(input)) {
			throw new IllegalArgumentException("Given values of type '" + input.getClass() + "'.");
		}

		return new MeasuredValues(this, name, unit, input, flags);
	}

	/**
	 * Returns true if this scalar type is {@link #STRING}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isString() {
		return STRING == this;
	}

	/**
	 * Returns true if this scalar type is {@link #DATE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDate() {
		return DATE == this;
	}

	/**
	 * Returns true if this scalar type is {@link #BOOLEAN}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isBoolean() {
		return BOOLEAN == this;
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BYTE}</li>
	 * 	<li>{@link #SHORT}</li>
	 * 	<li>{@link #INTEGER}</li>
	 * 	<li>{@link #LONG}</li>
	 * 	<li>{@link #FLOAT}</li>
	 * 	<li>{@link #DOUBLE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isNumericalType() {
		return isIntegerType() || isFloatType();
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BYTE}</li>
	 * 	<li>{@link #SHORT}</li>
	 * 	<li>{@link #INTEGER}</li>
	 * 	<li>{@link #LONG}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isIntegerType() {
		return isByte() || isShort() || isInteger() || isLong();
	}

	/**
	 * Returns true if this scalar type is {@link #BYTE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isByte() {
		return BYTE == this;
	}

	/**
	 * Returns true if this scalar type is {@link #SHORT}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isShort() {
		return SHORT == this;
	}

	/**
	 * Returns true if this scalar type is {@link #INTEGER}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isInteger() {
		return INTEGER == this;
	}

	/**
	 * Returns true if this scalar type is {@link #LONG}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isLong() {
		return LONG == this;
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FLOAT}</li>
	 * 	<li>{@link #DOUBLE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isFloatType() {
		return isFloat() || isDouble();
	}

	/**
	 * Returns true if this scalar type is {@link #FLOAT}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFloat() {
		return FLOAT == this;
	}

	/**
	 * Returns true if this scalar type is {@link #DOUBLE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDouble() {
		return DOUBLE == this;
	}

	/**
	 * Returns true if this scalar type is {@link #BYTE_STREAM}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isByteStream() {
		return BYTE_STREAM == this;
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FLOAT_COMPLEX}</li>
	 * 	<li>{@link #DOUBLE_COMPLEX}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isComplexType() {
		return isFloatComplex() || isDoubleComplex();
	}

	/**
	 * Returns true if this scalar type is {@link #FLOAT_COMPLEX}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFloatComplex() {
		return FLOAT_COMPLEX == this;
	}

	/**
	 * Returns true if this scalar type is {@link #DOUBLE_COMPLEX}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDoubleComplex() {
		return DOUBLE_COMPLEX == this;
	}

	/**
	 * Returns true if this scalar type is {@link #ENUMERATION}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isEnumeration() {
		return ENUMERATION == this;
	}

	/**
	 * Returns true if this scalar type is {@link #FILE_LINK}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFileLink() {
		return FILE_LINK == this;
	}

	/**
	 * Returns true if this scalar type is {@link #BLOB}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isBlob() {
		return BLOB == this;
	}

	/**
	 * Returns true if this scalar type is {@link #UNKNOWN}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isUnknown() {
		return UNKNOWN == this;
	}

	/**
	 * Returns the associated {@link ValueType}.
	 *
	 * @return The associated {@code ValueType} is returned.
	 */
	public ValueType toValueType() {
		return valueType;
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Returns the non sequence {@link ValueType} of the associated {@code
	 * ValueType}.
	 *
	 * @return The non sequence {@code ValueType} is returned.
	 */
	ValueType toSingleValueType() {
		return valueType.toSingleType();
	}

}
