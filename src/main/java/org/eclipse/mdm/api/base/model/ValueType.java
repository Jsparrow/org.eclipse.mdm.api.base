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
	 * replaces {@code null} with an empty {@code byte[][]} array.
	 */
	BYTE_STREAM_SEQUENCE(byte[][].class, new byte[0][]),

	/**
	 * A {@link Value} with this type contains a {@link FloatComplex} value
	 * and does not replaces {@code null}.
	 */
	FLOAT_COMPLEX(FloatComplex.class, null),

	/**
	 * A {@link Value} with this type contains a {@code FloatComplex[]} value
	 * and replaces {@code null} with an empty {@code FloatComplex[]} array.
	 */
	FLOAT_COMPLEX_SEQUENCE(FloatComplex[].class, new FloatComplex[0]),

	/**
	 * A {@link Value} with this type contains a {@link DoubleComplex} value
	 * and does not replaces {@code null}.
	 */
	DOUBLE_COMPLEX(DoubleComplex.class, null),

	/**
	 * A {@link Value} with this type contains a {@code DoubleComplex[]} value
	 * and replaces {@code null} with an empty {@code DoubleComplex[]} array.
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
	 * TODO ...
	 */
	BLOB(Object.class, null),

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
	 * Constructor - May only be used to create {@link #ENUMERATION}, {@link
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
	 * Value}'s initial validity flag will be set to {@code true}, the unit
	 * name will be omitted.
	 *
	 * <p><b>Note:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code false}.
	 *
	 * @param name The name of the attribute.
	 * @param input The initial value.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException Thrown if {@link #isEnumerationType()} returns
	 * 		{@code true}.
	 */
	public Value createValue(String name, Object input) {
		return create(name, "", true, input);
	}

	/**
	 * Creates a new {@link Value} initialized with given name. The {@code
	 * Value}'s initial validity flag will be set to {@code false}, the unit
	 * name will be omitted. The initial value will be the one defined in {@link
	 * #defaultValue}.
	 *
	 * <p><b>Note:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code false}.
	 *
	 * @param name The name of the attribute.
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
	 * <p><b>Note:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code false}.
	 *
	 * @param name The name of the attribute.
	 * @param unit The unit name of the attribute.
	 * @param valid The initial validity flag.
	 * @param input The initial value.
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
	 * <p><b>Note:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code true}.
	 *
	 * @param <E> Modeled enumeration type.
	 * @param enumClass The enumeration class type will be used for validity checking.
	 * @param name The name of the attribute.
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
	 * <p><b>Note:</b> This method is only allowed to be called where {@link
	 * #isEnumerationType()} returns {@code true}.
	 *
	 * @param <E> Modeled enumeration type.
	 * @param enumClass The enumeration class type will be used for validity checking.
	 * @param name The name of the attribute.
	 * @param unit The unit name of the attribute.
	 * @param valid The initial validity flag.
	 * @param input The initial value.
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

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #STRING}</li>
	 * 	<li>{@link #STRING_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isStringType() {
		return isString() || isStringSequence();
	}

	/**
	 * Returns true if this value type is {@link #STRING}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isString() {
		return STRING == this;
	}

	/**
	 * Returns true if this value type is {@link #STRING_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isStringSequence() {
		return STRING_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #DATE}</li>
	 * 	<li>{@link #DATE_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isDateType() {
		return isDate() || isDateSequence();
	}

	/**
	 * Returns true if this value type is {@link #DATE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDate() {
		return DATE == this;
	}

	/**
	 * Returns true if this value type is {@link #DATE_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDateSequence() {
		return DATE_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BOOLEAN}</li>
	 * 	<li>{@link #BOOLEAN_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isBooleanType() {
		return isBoolean() || isBooleanSequence();
	}

	/**
	 * Returns true if this value type is {@link #BOOLEAN}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isBoolean() {
		return BOOLEAN == this;
	}

	/**
	 * Returns true if this value type is {@link #BOOLEAN_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isBooleanSequence() {
		return BOOLEAN_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BYTE}</li>
	 * 	<li>{@link #BYTE_SEQUENCE}</li>
	 * 	<li>{@link #SHORT}</li>
	 * 	<li>{@link #SHORT_SEQUENCE}</li>
	 * 	<li>{@link #INTEGER}</li>
	 * 	<li>{@link #INTEGER_SEQUENCE}</li>
	 * 	<li>{@link #LONG}</li>
	 * 	<li>{@link #LONG_SEQUENCE}</li>
	 * 	<li>{@link #FLOAT}</li>
	 * 	<li>{@link #FLOAT_SEQUENCE}</li>
	 * 	<li>{@link #DOUBLE}</li>
	 * 	<li>{@link #DOUBLE_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isNumericalType() {
		return isAnyIntegerType() || isAnyFloatType();
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BYTE}</li>
	 * 	<li>{@link #BYTE_SEQUENCE}</li>
	 * 	<li>{@link #SHORT}</li>
	 * 	<li>{@link #SHORT_SEQUENCE}</li>
	 * 	<li>{@link #INTEGER}</li>
	 * 	<li>{@link #INTEGER_SEQUENCE}</li>
	 * 	<li>{@link #LONG}</li>
	 * 	<li>{@link #LONG_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isAnyIntegerType() {
		return isByteType() || isShortType() || isIntegerType() || isLongType();
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BYTE}</li>
	 * 	<li>{@link #BYTE_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isByteType() {
		return isByte() || isByteSequence();
	}

	/**
	 * Returns true if this value type is {@link #BYTE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isByte() {
		return BYTE == this;
	}

	/**
	 * Returns true if this value type is {@link #BYTE_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isByteSequence() {
		return BYTE_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #SHORT}</li>
	 * 	<li>{@link #SHORT_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isShortType() {
		return isShort() || isShortSequence();
	}

	/**
	 * Returns true if this value type is {@link #SHORT}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isShort() {
		return SHORT == this;
	}

	/**
	 * Returns true if this value type is {@link #SHORT_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isShortSequence() {
		return SHORT_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #INTEGER}</li>
	 * 	<li>{@link #INTEGER_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isIntegerType() {
		return isInteger() || isIntegerSequence();
	}

	/**
	 * Returns true if this value type is {@link #INTEGER}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isInteger() {
		return INTEGER == this;
	}

	/**
	 * Returns true if this value type is {@link #INTEGER_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isIntegerSequence() {
		return INTEGER_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #LONG}</li>
	 * 	<li>{@link #LONG_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isLongType() {
		return isLong() || isLongSequence();
	}

	/**
	 * Returns true if this value type is {@link #LONG}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isLong() {
		return LONG == this;
	}

	/**
	 * Returns true if this value type is {@link #LONG_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isLongSequence() {
		return LONG_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FLOAT}</li>
	 * 	<li>{@link #FLOAT_SEQUENCE}</li>
	 * 	<li>{@link #DOUBLE}</li>
	 * 	<li>{@link #DOUBLE_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isAnyFloatType() {
		return isFloatType() || isDoubleType();
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FLOAT}</li>
	 * 	<li>{@link #FLOAT_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isFloatType() {
		return isFloat() || isFloatSequence();
	}

	/**
	 * Returns true if this value type is {@link #FLOAT}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFloat() {
		return FLOAT == this;
	}

	/**
	 * Returns true if this value type is {@link #FLOAT_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFloatSequence() {
		return FLOAT_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #DOUBLE}</li>
	 * 	<li>{@link #DOUBLE_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isDoubleType() {
		return isDouble() || isDoubleSequence();
	}

	/**
	 * Returns true if this value type is {@link #DOUBLE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDouble() {
		return DOUBLE == this;
	}

	/**
	 * Returns true if this value type is {@link #DOUBLE_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDoubleSequence() {
		return DOUBLE_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #BYTE_STREAM}</li>
	 * 	<li>{@link #BYTE_STREAM_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isByteStreamType() {
		return isByteStream() || isByteStreamSequence();
	}

	/**
	 * Returns true if this value type is {@link #BYTE_STREAM}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isByteStream() {
		return BYTE_STREAM == this;
	}

	/**
	 * Returns true if this value type is {@link #BYTE_STREAM_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isByteStreamSequence() {
		return BYTE_STREAM_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FLOAT_COMPLEX}</li>
	 * 	<li>{@link #FLOAT_COMPLEX_SEQUENCE}</li>
	 * 	<li>{@link #DOUBLE_COMPLEX}</li>
	 * 	<li>{@link #DOUBLE_COMPLEX_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isComplexType() {
		return isFloatComplexType() || isDoubleComplexType();
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FLOAT_COMPLEX}</li>
	 * 	<li>{@link #FLOAT_COMPLEX_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isFloatComplexType() {
		return isFloatComplex() || isFloatComplexSequence();
	}

	/**
	 * Returns true if this value type is {@link #FLOAT_COMPLEX}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFloatComplex() {
		return FLOAT_COMPLEX == this;
	}

	/**
	 * Returns true if this value type is {@link #FLOAT_COMPLEX_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFloatComplexSequence() {
		return FLOAT_COMPLEX_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #DOUBLE_COMPLEX}</li>
	 * 	<li>{@link #DOUBLE_COMPLEX_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isDoubleComplexType() {
		return isDoubleComplex() || isDoubleComplexSequence();
	}

	/**
	 * Returns true if this value type is {@link #DOUBLE_COMPLEX}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDoubleComplex() {
		return DOUBLE_COMPLEX == this;
	}

	/**
	 * Returns true if this value type is {@link #DOUBLE_COMPLEX_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isDoubleComplexSequence() {
		return DOUBLE_COMPLEX_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #ENUMERATION}</li>
	 * 	<li>{@link #ENUMERATION_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isEnumerationType() {
		return isEnumeration() || isEnumerationSequence();
	}

	/**
	 * Returns true if this value type is {@link #ENUMERATION}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isEnumeration() {
		return ENUMERATION == this;
	}

	/**
	 * Returns true if this value type is {@link #ENUMERATION_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isEnumerationSequence() {
		return ENUMERATION_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #FILE_LINK}</li>
	 * 	<li>{@link #FILE_LINK_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isFileLinkType() {
		return isFileLink() || isFileLinkSequence();
	}

	/**
	 * Returns true if this value type is {@link #FILE_LINK}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFileLink() {
		return FILE_LINK == this;
	}

	/**
	 * Returns true if this value type is {@link #FILE_LINK_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isFileLinkSequence() {
		return FILE_LINK_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is {@link #BLOB}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isBlob() {
		return BLOB == this;
	}

	/**
	 * Returns true if this value type is {@link #UNKNOWN}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isUnknown() {
		return UNKNOWN == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * 	<li>{@link #STRING_SEQUENCE}</li>
	 * 	<li>{@link #DATE_SEQUENCE}</li>
	 * 	<li>{@link #BOOLEAN_SEQUENCE}</li>
	 * 	<li>{@link #BYTE_SEQUENCE}</li>
	 * 	<li>{@link #SHORT_SEQUENCE}</li>
	 * 	<li>{@link #INTEGER_SEQUENCE}</li>
	 * 	<li>{@link #LONG_SEQUENCE}</li>
	 * 	<li>{@link #FLOAT_SEQUENCE}</li>
	 * 	<li>{@link #DOUBLE_SEQUENCE}</li>
	 * 	<li>{@link #BYTE_STREAM_SEQUENCE}</li>
	 * 	<li>{@link #FLOAT_COMPLEX_SEQUENCE}</li>
	 * 	<li>{@link #DOUBLE_COMPLEX_SEQUENCE}</li>
	 * 	<li>{@link #ENUMERATION_SEQUENCE}</li>
	 * 	<li>{@link #FILE_LINK_SEQUENCE}</li>
	 * </ul>
	 *
	 * @return Returns {@code true} in the cases listed above.
	 */
	public boolean isSequence() {
		return name().endsWith("SEQUENCE");
	}

	/**
	 * Returns the sequence version of this value type. This method returns
	 * itself, if this value type is a sequence type.
	 *
	 * @return The sequence version of this value type is returned.
	 */
	public ValueType toSequenceType() {
		if(isSequence()) {
			return this;
		}

		return ValueType.valueOf(name() + "_SEQUENCE");
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Returns the scalar version of this value type. This method returns
	 * itself, if this value type is a scalar type.
	 *
	 * @return The sequence version of this value type is returned.
	 */
	ValueType toSingleType() {
		if(isSequence()) {
			return ValueType.valueOf(name().replace("_SEQUENCE", ""));
		}

		return this;
	}

}
