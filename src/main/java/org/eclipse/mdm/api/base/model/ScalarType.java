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

public final class ScalarType extends EnumerationValue {

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code String} values.
	 */
	public static final ScalarType STRING = new ScalarType(0, "STRING", ValueType.STRING_SEQUENCE, String.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code LocalDateTime} values.
	 */
	public static final ScalarType DATE = new ScalarType(1, "DATE", ValueType.DATE_SEQUENCE, LocalDateTime.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code boolean} values.
	 */
	public static final ScalarType BOOLEAN = new ScalarType(2, "BOOLEAN", ValueType.BOOLEAN_SEQUENCE, boolean.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code byte} values.
	 */
	public static final ScalarType BYTE = new ScalarType(3, "BYTE", ValueType.BYTE_SEQUENCE, byte.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code short} values.
	 */
	public static final ScalarType SHORT = new ScalarType(4, "SHORT", ValueType.SHORT_SEQUENCE, short.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code int} values.
	 */
	public static final ScalarType INTEGER = new ScalarType(5, "INTEGER", ValueType.INTEGER_SEQUENCE, int.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code long} values.
	 */
	public static final ScalarType LONG = new ScalarType(6, "LONG", ValueType.LONG_SEQUENCE, long.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code float} values.
	 */
	public static final ScalarType FLOAT = new ScalarType(7, "FLOAT", ValueType.FLOAT_SEQUENCE, float.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code double} values.
	 */
	public static final ScalarType DOUBLE = new ScalarType(8, "DOUBLE", ValueType.DOUBLE_SEQUENCE, double.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code byte[]} values.
	 */
	public static final ScalarType BYTE_STREAM = new ScalarType(9, "BYTE_STREAM", ValueType.BYTE_STREAM_SEQUENCE, byte[].class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code FloatComplex} values.
	 */
	public static final ScalarType FLOAT_COMPLEX = new ScalarType(10, "FLOAT_COMPLEX", ValueType.FLOAT_COMPLEX_SEQUENCE,
			FloatComplex.class);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code DoubleComplex} values.
	 */
	public static final ScalarType DOUBLE_COMPLEX = new ScalarType(11, "DOUBLE_COMPLEX", ValueType.DOUBLE_COMPLEX_SEQUENCE,
			DoubleComplex.class);

	/**
	 * {@link MeasuredValues} are not allowed to be of this type. This constant
	 * may be used in other contexts.
	 */
	public static final ScalarType ENUMERATION = new ScalarType(12, "ENUMERATION", ValueType.ENUMERATION_SEQUENCE);

	/**
	 * A {@link MeasuredValues} with this type contains an array sequence of
	 * {@code FileLink} values.
	 */
	public static final ScalarType FILE_LINK = new ScalarType(13, "FILE_LINK", ValueType.FILE_LINK_SEQUENCE, FileLink.class);

	/**
	 * TODO ...
	 */
	public static final ScalarType BLOB = new ScalarType(14, "BLOB", ValueType.BLOB, Object.class);

	/**
	 * {@link MeasuredValues} are not allowed to be of this type. This constant
	 * may be used in other contexts.
	 */
	public static final ScalarType UNKNOWN = new ScalarType(15, "UNKNOWN", ValueType.UNKNOWN);

	private final ValueType valueType;
	private final Class<?> arrayType;

	/**
	 * Constructor.
	 * 
	 * @param ord
	 *            ordinal number of the enum value
	 * @param name
	 *            name of the enum value
	 * @param valueType
	 *            The associated {@link ValueType}.
	 */
	private ScalarType(int ord, String name, ValueType valueType) {
		super(name, ord);
		this.valueType = valueType;
		arrayType = null;
	}

	/**
	 * Constructor.
	 *
	 * @param ord
	 *            ordinal number of the enum value
	 * @param name
	 *            name of the enum value
	 * @param valueType
	 *            The associated {@link ValueType}.
	 * @param componentType
	 *            The component type of the array held by instances of
	 *            {@link MeasuredValues}.
	 */
	private ScalarType(int ord, String name, ValueType valueType, Class<?> componentType) {
		super(name, ord);
		this.valueType = valueType;
		arrayType = Array.newInstance(componentType, 0).getClass();
	}

	/**
	 * Creates a new {@link MeasuredValues} initialized with given name, unit
	 * and values including the related validity flags.
	 *
	 * @param name
	 *            This is the name of the corresponding {@link Channel}.
	 * @param unit
	 *            Name of the unit the contained values are of.
	 * @param input
	 *            The measured values.
	 * @param flags
	 *            The validity flags of the measured values.
	 * @return The created {@code MeasuredValues} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumeration()} or {@link #isUnknown()}
	 *             returns {@code true}.
	 * @throws IllegalArgumentException
	 *             Thrown if given values are not assignment compatible.
	 */
	public MeasuredValues createMeasuredValues(String name, String unit, Object input, boolean[] flags) {
		if (isEnumeration() || isUnknown()) {
			throw new IllegalStateException("It is not allowed to create measured values of type '" + this + "'.");
		} else if (!arrayType.isInstance(input)) {
			throw new IllegalArgumentException("Given values of type '" + input.getClass() + "'.");
		}

		return new MeasuredValues(this, name, unit, input, flags);
	}

	/**
	 * Returns true if this scalar type is {@link #STRING}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isString() {
		return STRING == this;
	}

	/**
	 * Returns true if this scalar type is {@link #DATE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDate() {
		return DATE == this;
	}

	/**
	 * Returns true if this scalar type is {@link #BOOLEAN}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isBoolean() {
		return BOOLEAN == this;
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * <li>{@link #BYTE}</li>
	 * <li>{@link #SHORT}</li>
	 * <li>{@link #INTEGER}</li>
	 * <li>{@link #LONG}</li>
	 * <li>{@link #FLOAT}</li>
	 * <li>{@link #DOUBLE}</li>
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
	 * <li>{@link #BYTE}</li>
	 * <li>{@link #SHORT}</li>
	 * <li>{@link #INTEGER}</li>
	 * <li>{@link #LONG}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isByte() {
		return BYTE == this;
	}

	/**
	 * Returns true if this scalar type is {@link #SHORT}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isShort() {
		return SHORT == this;
	}

	/**
	 * Returns true if this scalar type is {@link #INTEGER}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isInteger() {
		return INTEGER == this;
	}

	/**
	 * Returns true if this scalar type is {@link #LONG}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isLong() {
		return LONG == this;
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * <li>{@link #FLOAT}</li>
	 * <li>{@link #DOUBLE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFloat() {
		return FLOAT == this;
	}

	/**
	 * Returns true if this scalar type is {@link #DOUBLE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDouble() {
		return DOUBLE == this;
	}

	/**
	 * Returns true if this scalar type is {@link #BYTE_STREAM}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isByteStream() {
		return BYTE_STREAM == this;
	}

	/**
	 * Returns true if this scalar type on of the following:
	 *
	 * <ul>
	 * <li>{@link #FLOAT_COMPLEX}</li>
	 * <li>{@link #DOUBLE_COMPLEX}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFloatComplex() {
		return FLOAT_COMPLEX == this;
	}

	/**
	 * Returns true if this scalar type is {@link #DOUBLE_COMPLEX}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDoubleComplex() {
		return DOUBLE_COMPLEX == this;
	}

	/**
	 * Returns true if this scalar type is {@link #ENUMERATION}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isEnumeration() {
		return ENUMERATION == this;
	}

	/**
	 * Returns true if this scalar type is {@link #FILE_LINK}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFileLink() {
		return FILE_LINK == this;
	}

	/**
	 * Returns true if this scalar type is {@link #BLOB}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isBlob() {
		return BLOB == this;
	}

	/**
	 * Returns true if this scalar type is {@link #UNKNOWN}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
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

	/**
	 * Returns the non sequence {@link ValueType} of the associated {@code
	 * ValueType}.
	 *
	 * @return The non sequence {@code ValueType} is returned.
	 */
	public ValueType toSingleValueType() {
		return valueType.toSingleType();
	}
}
