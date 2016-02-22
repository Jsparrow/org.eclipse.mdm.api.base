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
 * Value type enumeration is a {@link Value} factory.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Value
 */
public enum ValueType {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * A {@link Value} with this type contains a {@code String} value and
	 * replaces {@code null} with an empty {@code String}.
	 */
	STRING(String.class, ""),

	/**
	 * A {@link Value} with this type contains a {@code String[]} value
	 * replaces {@code null} with an empty {@code String} array.
	 */
	STRING_SEQUENCE(String[].class, new String[0]),

	/**
	 * A {@link Value} with this type contains a {@link LocalDateTime} value
	 * and does not replace {@code null}.
	 */
	DATE(LocalDateTime.class, null),

	/**
	 * A {@link Value} with this type contains a {@code LocalDateTime[]} value
	 * and replaces {@code null} with an empty {@code LocalDateTime} array.
	 */
	DATE_SEQUENCE(LocalDateTime[].class, new LocalDateTime[0]),

	/**
	 * A {@link Value} with this type contains a {@link Boolean} value  and
	 * replaces {@code null} with {@link Boolean#FALSE}.
	 */
	BOOLEAN(Boolean.class, Boolean.FALSE),

	/**
	 * A {@link Value} with this type contains a {@code boolean[]} value and
	 * replaces {@code null} with an empty {@code boolean} array.
	 */
	BOOLEAN_SEQUENCE(boolean[].class, new boolean[0]),

	/**
	 * A {@link Value} with this type contains a {@link Byte} value and
	 * replaces {@code null} with a {@code Byte} containing zero.
	 */
	BYTE(Byte.class, Byte.valueOf((byte) 0)),

	/**
	 * A {@link Value} with this type contains a {@code byte[]} value and
	 * replaces {@code null} with an empty {@code byte} array.
	 */
	BYTE_SEQUENCE(byte[].class, new byte[0]),

	/**
	 * A {@link Value} with this type contains a {@link Short} value and
	 * replaces {@code null} with a {@code Short} containing zero.
	 */
	SHORT(Short.class, Short.valueOf((short) 0)),

	/**
	 * A {@link Value} with this type contains a {@code short[]} value and
	 * replaces {@code null} with an empty {@code short} array.
	 */
	SHORT_SEQUENCE(short[].class, new short[0]),

	/**
	 * A {@link Value} with this type contains a {@link Integer} value and
	 * replaces {@code null} with a {@code Integer} containing zero.
	 */
	INTEGER(Integer.class, Integer.valueOf(0)),

	/**
	 * A {@link Value} with this type contains a {@code int[]} value and
	 * replaces {@code null} with an empty {@code int} array.
	 */
	INTEGER_SEQUENCE(int[].class, new int[0]),

	/**
	 * A {@link Value} with this type contains a {@link Long} value and
	 * replaces {@code null} with a {@code Long} containing zero.
	 */
	LONG(Long.class, Long.valueOf(0)),

	/**
	 * A {@link Value} with this type contains a {@code long[]} value and
	 * replaces {@code null} with an empty {@code long} array.
	 */
	LONG_SEQUENCE(long[].class, new long[0]),

	/**
	 * A {@link Value} with this type contains a {@link Float} value and
	 * replaces {@code null} with a {@code Float} containing zero.
	 */
	FLOAT(Float.class, Float.valueOf(0)),

	/**
	 * A {@link Value} with this type contains a {@code float[]} value and
	 * replaces {@code null} with an empty {@code float} array.
	 */
	FLOAT_SEQUENCE(float[].class, new float[0]),

	/**
	 * A {@link Value} with this type contains a {@link Double} value and
	 * replaces {@code null} with a {@code Double} containing zero.
	 */
	DOUBLE(Double.class, Double.valueOf(0)),

	/**
	 * A {@link Value} with this type contains a {@code double[]} value and
	 * replaces {@code null} with an empty {@code double} array.
	 */
	DOUBLE_SEQUENCE(double[].class, new double[0]),

	/**
	 * A {@link Value} with this type contains a {@code byte[]} value and
	 * replaces {@code null} with an empty {@code byte} array.
	 */
	BYTE_STREAM(byte[].class, new byte[0]),

	/**
	 * A {@link Value} with this type contains a {@code byte[][]} value and
	 * replaces {@code null} with an empty {@code byte[]} array.
	 */
	BYTE_STREAM_SEQUENCE(byte[][].class, new byte[0][]),

	/**
	 * TODO
	 */
	FLOAT_COMPLEX(FloatComplex.class, null),

	/**
	 * TODO
	 */
	FLOAT_COMPLEX_SEQUENCE(FloatComplex[].class, new FloatComplex[0]),

	/**
	 * TODO
	 */
	DOUBLE_COMPLEX(DoubleComplex.class, null),

	/**
	 * TODO
	 */
	DOUBLE_COMPLEX_SEQUENCE(DoubleComplex[].class, new DoubleComplex[0]),

	/**
	 * A {@link Value} with this type contains a modeled enumeration
	 * constant value and does not replace {@code null}.
	 *
	 * @see #create(Class, String)
	 * @see #create(Class, String, String, boolean, Object)
	 */
	ENUMERATION,

	/**
	 * A {@link Value} with this type contains a modeled enumeration
	 * constant array value and replaces {@code null} with an empty
	 * array with defined component type.
	 *
	 * @see #create(Class, String)
	 * @see #create(Class, String, String, boolean, Object)
	 */
	ENUMERATION_SEQUENCE,

	/**
	 * A {@link Value} with this type contains a {@link FileLink} value and
	 * does not replace {@code null}.
	 */
	FILE_LINK(FileLink.class, null),

	/**
	 * A {@link Value} with this type contains a {@code FileLink[]} value and
	 * replaces {@code null} with an empty {@code FileLink} array.
	 */
	FILE_LINK_SEQUENCE(FileLink[].class, new FileLink[0]),

	/**
	 * TODO is this really a use case?!?
	 */
	@Deprecated BLOB(Object.class, null),

	/**
	 * A {@link Value} with this type contains a {@link Object} value and
	 * does not replace {@code null}. This value type does not have a
	 * corresponding sequence type.
	 */
	UNKNOWN;

	// ======================================================================
	// Instance variables
	// ======================================================================

	/**
	 * The type is used to check assignment compatibility of non {@code null}
	 * values passed to {@link Value#set(Object)}.
	 */
	public final Class<?> type;

	/**
	 * The default value will be used in {@link Value#set(Object)} to replace
	 * a {@code null} input argument.
	 */
	final Object defaultValue;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor - May only be used to {@link #ENUMERATION}, {@link
	 * #ENUMERATION_SEQUENCE} or {@link #UNKNOWN} types.
	 */
	private ValueType() {
		this(null, null);
	}

	/**
	 * Constructor.
	 *
	 * @param type The type of value a {@link Value} with this value type will
	 * 		accept.
	 * @param defaultValue Will be used as {@code null} replacement in {@link
	 * 		Value#set(Object)}.
	 */
	private ValueType(Class<?> type, Object defaultValue) {
		this.type = type;
		this.defaultValue = defaultValue;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link Value} initialized with given name. The {@code
	 * Value}'s initial validity flag will be set to {@code false}, the unit
	 * name will be omitted. The initial value will be the one defined in {@link
	 * #defaultValue}.
	 *
	 * <p><b>NOTE:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code false}.
	 *
	 * @param name Name of the attribute.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException Thrown if {@link #isEnumerationType()} returns
	 * 		{@code true}.
	 */
	public Value create(String name) {
		return create(name, "", false, defaultValue);
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments.
	 *
	 * <p><b>NOTE:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code false}.
	 *
	 * @param name Name of the attribute.
	 * @param unit Unit name of the attribute.
	 * @param valid Initial validity flag.
	 * @param input Initial value.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException Thrown if {@link #isEnumerationType()} returns
	 * 		{@code true}.
	 */
	public Value create(String name, String unit, boolean valid, Object input) {
		if(isEnumerationType()) {
			throw new IllegalStateException("This value type is an enumeration type.");
		}

		return new Value(this, name, unit, valid, input);
	}

	/**
	 * Creates a new {@link Value} initialized with given name. The {@code
	 * Value}'s initial validity flag will be set to {@code false}, the unit
	 * name will be omitted. The initial value will be {@code null} if
	 * {@link #isSequence()} returns {@code false} otherwise an empty
	 * array with given component type in enumClass is used.
	 *
	 * <p><b>NOTE:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code true}.
	 *
	 * @param <E> Modeled enumeration type.
	 * @param enumClass The enumeration class type will be used for validity checking.
	 * @param name Name of the attribute.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException Thrown if {@link #isEnumerationType()} returns
	 * 		{@code false}.
	 */
	public <E extends Enum<?>> Value create(Class<E> enumClass, String name) {
		return create(enumClass, name, "", false, null);
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments.
	 *
	 * <p><b>NOTE:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code true}.
	 *
	 * @param <E> Modeled enumeration type.
	 * @param enumClass The enumeration class type will be used for validity checking.
	 * @param name Name of the attribute.
	 * @param unit Unit name of the attribute.
	 * @param valid Initial validity flag.
	 * @param input Initial value.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException Thrown if {@link #isEnumerationType()} returns
	 * 		{@code false}.
	 */
	public <E extends Enum<?>> Value create(Class<E> enumClass, String name, String unit,
			boolean valid, Object input) {
		if(isEnumerationType()) {
			Object nullReplacement = null;
			Class<?> valueClass = enumClass;
			if(isSequence()) {
				nullReplacement = Array.newInstance(enumClass, 0);
				valueClass = nullReplacement.getClass();
			}

			return new Value(this, name, unit, valid, input, valueClass, nullReplacement);
		}

		throw new IllegalStateException("This value type is not an enumeration type.");
	}

	public static Value createStringValue(String name, String value) {
		return STRING.create(name, "", true, value);
	}

	public boolean isStringType() {
		return isString() || isStringSequence();
	}

	public boolean isString() {
		return STRING == this;
	}

	public boolean isStringSequence() {
		return STRING_SEQUENCE == this;
	}

	public boolean isDateType() {
		return isDate() || isDateSequence();
	}

	public boolean isDate() {
		return DATE == this;
	}

	public boolean isDateSequence() {
		return DATE_SEQUENCE == this;
	}

	public boolean isBooleanType() {
		return isBoolean() || isBooleanSequence();
	}

	public boolean isBoolean() {
		return BOOLEAN == this;
	}

	public boolean isBooleanSequence() {
		return BOOLEAN_SEQUENCE == this;
	}

	public boolean isNumericalType() {
		return isAnyIntegerType() || isAnyFloatType();
	}

	public boolean isAnyIntegerType() {
		return isByteType() || isShortType() || isIntegerType() || isLongType();
	}

	public boolean isByteType() {
		return isByte() || isByteSequence();
	}

	public boolean isByte() {
		return BYTE == this;
	}

	public boolean isByteSequence() {
		return BYTE_SEQUENCE == this;
	}

	public boolean isShortType() {
		return isShort() || isShortSequence();
	}

	public boolean isShort() {
		return SHORT == this;
	}

	public boolean isShortSequence() {
		return SHORT_SEQUENCE == this;
	}

	public boolean isIntegerType() {
		return isInteger() || isIntegerSequence();
	}

	public boolean isInteger() {
		return INTEGER == this;
	}

	public boolean isIntegerSequence() {
		return INTEGER_SEQUENCE == this;
	}

	public boolean isLongType() {
		return isLong() || isLongSequence();
	}

	public boolean isLong() {
		return LONG == this;
	}

	public boolean isLongSequence() {
		return LONG_SEQUENCE == this;
	}

	public boolean isAnyFloatType() {
		return isFloatType() || isDoubleType();
	}

	public boolean isFloatType() {
		return isFloat() || isFloatSequence();
	}

	public boolean isFloat() {
		return FLOAT == this;
	}

	public boolean isFloatSequence() {
		return FLOAT_SEQUENCE == this;
	}

	public boolean isDoubleType() {
		return isDouble() || isDoubleSequence();
	}

	public boolean isDouble() {
		return DOUBLE == this;
	}

	public boolean isDoubleSequence() {
		return DOUBLE_SEQUENCE == this;
	}

	public boolean isByteStreamType() {
		return isByteStream() || isByteStreamSequence();
	}

	public boolean isByteStream() {
		return BYTE_STREAM == this;
	}

	public boolean isByteStreamSequence() {
		return BYTE_STREAM_SEQUENCE == this;
	}

	public boolean isComplexType() {
		return isFloatComplexType() || isDoubleComplexType();
	}

	public boolean isFloatComplexType() {
		return isFloatComplex() || isFloatComplexSequence();
	}

	public boolean isFloatComplex() {
		return FLOAT_COMPLEX == this;
	}

	public boolean isFloatComplexSequence() {
		return FLOAT_COMPLEX_SEQUENCE == this;
	}

	public boolean isDoubleComplexType() {
		return isDoubleComplex() || isDoubleComplexSequence();
	}

	public boolean isDoubleComplex() {
		return DOUBLE_COMPLEX == this;
	}

	public boolean isDoubleComplexSequence() {
		return DOUBLE_COMPLEX_SEQUENCE == this;
	}

	public boolean isEnumerationType() {
		return isEnumeration() || isEnumerationSequence();
	}

	public boolean isEnumeration() {
		return ENUMERATION == this;
	}

	public boolean isEnumerationSequence() {
		return ENUMERATION_SEQUENCE == this;
	}

	public boolean isFileLinkType() {
		return isFileLink() || isFileLinkSequence();
	}

	public boolean isFileLink() {
		return FILE_LINK == this;
	}

	public boolean isFileLinkSequence() {
		return FILE_LINK_SEQUENCE == this;
	}

	public boolean isBlob() {
		return BLOB == this;
	}

	public boolean isUnknown() {
		return UNKNOWN == this;
	}

	/**
	 * Checks whether this instance is a sequence value type.
	 *
	 * @return True is returned if this instance is a sequence value type.
	 */
	public boolean isSequence() {
		return name().endsWith("SEQUENCE");
	}

	/**
	 * If this instance represents a sequence value type, then itself is
	 * returned, otherwise its sequence value type counterpart is returned.
	 *
	 * @return The returned value type is in either case a sequence value type.
	 * @throws IllegalArgumentException Thrown if a sequence counterpart does
	 * 		not exist.
	 */
	public ValueType toSequenceType() {
		if(isSequence()) {
			return this;
		}

		return ValueType.valueOf(name() + "_SEQUENCE");
	}

	/**
	 * If this instance represents a sequence value type, then its single value
	 * type counterpart is returned, otherwise this instance is returned.
	 *
	 * @return The returned value type is in either case a single value type.
	 */
	public ValueType toSingleType() {
		if(isSequence()) {
			return ValueType.valueOf(name().replace("_SEQUENCE", ""));
		}

		return this;
	}

}
