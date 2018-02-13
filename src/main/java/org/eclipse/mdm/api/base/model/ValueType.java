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
public final class ValueType<T> extends EnumerationValue {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * A {@link Value} with this type contains a {@code String} value and
	 * replaces {@code null} with an empty {@code String}.
	 */
	public static final ValueType<String> STRING = new ValueType<>(0, String.class, "");

	/**
	 * A {@link Value} with this type contains a {@code String[]} value replaces
	 * {@code null} with an empty {@code String} array.
	 */
	public static final ValueType<String[]> STRING_SEQUENCE = new ValueType<>(1, String[].class, new String[0]);

	/**
	 * A {@link Value} with this type contains a {@link LocalDateTime} value and
	 * does not replace {@code null}.
	 */
	public static final ValueType<LocalDateTime> DATE = new ValueType<>(2, LocalDateTime.class, null);

	/**
	 * A {@link Value} with this type contains a {@code LocalDateTime[]} value
	 * and replaces {@code null} with an empty {@code LocalDateTime} array.
	 */
	public static final ValueType<LocalDateTime[]> DATE_SEQUENCE = new ValueType<>(3, LocalDateTime[].class,
			new LocalDateTime[0]);

	/**
	 * A {@link Value} with this type contains a {@link Boolean} value and
	 * replaces {@code null} with {@link Boolean#FALSE}.
	 */
	public static final ValueType<Boolean> BOOLEAN = new ValueType<>(4, Boolean.class, Boolean.FALSE);

	/**
	 * A {@link Value} with this type contains a {@code boolean[]} value and
	 * replaces {@code null} with an empty {@code boolean} array.
	 */
	public static final ValueType<boolean[]> BOOLEAN_SEQUENCE = new ValueType<>(5, boolean[].class, new boolean[0]);

	/**
	 * A {@link Value} with this type contains a {@link Byte} value and replaces
	 * {@code null} with a {@code Byte} containing zero.
	 */
	public static final ValueType<Byte> BYTE = new ValueType<>(6, Byte.class, Byte.valueOf((byte) 0));

	/**
	 * A {@link Value} with this type contains a {@code byte[]} value and
	 * replaces {@code null} with an empty {@code byte} array.
	 */
	public static final ValueType<byte[]> BYTE_SEQUENCE = new ValueType<>(7, byte[].class, new byte[0]);

	/**
	 * A {@link Value} with this type contains a {@link Short} value and
	 * replaces {@code null} with a {@code Short} containing zero.
	 */
	public static final ValueType<Short> SHORT = new ValueType<>(8, Short.class, Short.valueOf((short) 0));

	/**
	 * A {@link Value} with this type contains a {@code short[]} value and
	 * replaces {@code null} with an empty {@code short} array.
	 */
	public static final ValueType<short[]> SHORT_SEQUENCE = new ValueType<>(9, short[].class, new short[0]);

	/**
	 * A {@link Value} with this type contains a {@link Integer} value and
	 * replaces {@code null} with a {@code Integer} containing zero.
	 */
	public static final ValueType<Integer> INTEGER = new ValueType<>(10, Integer.class, Integer.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code int[]} value and
	 * replaces {@code null} with an empty {@code int} array.
	 */
	public static final ValueType<int[]> INTEGER_SEQUENCE = new ValueType<>(11, int[].class, new int[0]);

	/**
	 * A {@link Value} with this type contains a {@link Long} value and replaces
	 * {@code null} with a {@code Long} containing zero.
	 */
	public static final ValueType<Long> LONG = new ValueType<>(12, Long.class, Long.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code long[]} value and
	 * replaces {@code null} with an empty {@code long} array.
	 */
	public static final ValueType<long[]> LONG_SEQUENCE = new ValueType<>(13, long[].class, new long[0]);

	/**
	 * A {@link Value} with this type contains a {@link Float} value and
	 * replaces {@code null} with a {@code Float} containing zero.
	 */
	public static final ValueType<Float> FLOAT = new ValueType<>(14, Float.class, Float.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code float[]} value and
	 * replaces {@code null} with an empty {@code float} array.
	 */
	public static final ValueType<float[]> FLOAT_SEQUENCE = new ValueType<>(15, float[].class, new float[0]);

	/**
	 * A {@link Value} with this type contains a {@link Double} value and
	 * replaces {@code null} with a {@code Double} containing zero.
	 */
	public static final ValueType<Double> DOUBLE = new ValueType<>(16, Double.class, Double.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code double[]} value and
	 * replaces {@code null} with an empty {@code double} array.
	 */
	public static final ValueType<double[]> DOUBLE_SEQUENCE = new ValueType<>(17, double[].class, new double[0]);

	/**
	 * A {@link Value} with this type contains a {@code byte[]} value and
	 * replaces {@code null} with an empty {@code byte} array.
	 */
	public static final ValueType<byte[]> BYTE_STREAM = new ValueType<>(18, byte[].class, new byte[0]);

	/**
	 * A {@link Value} with this type contains a {@code byte[][]} value and
	 * replaces {@code null} with an empty {@code byte[][]} array.
	 */
	public static final ValueType<byte[][]> BYTE_STREAM_SEQUENCE = new ValueType<>(19, byte[][].class, new byte[0][]);

	/**
	 * A {@link Value} with this type contains a {@link FloatComplex} value and
	 * does not replaces {@code null}.
	 */
	public static final ValueType<FloatComplex> FLOAT_COMPLEX = new ValueType<>(22, FloatComplex.class, null);

	/**
	 * A {@link Value} with this type contains a {@code FloatComplex[]} value
	 * and replaces {@code null} with an empty {@code FloatComplex[]} array.
	 */
	public static final ValueType<FloatComplex[]> FLOAT_COMPLEX_SEQUENCE = new ValueType<>(21, FloatComplex[].class,
			new FloatComplex[0]);

	/**
	 * A {@link Value} with this type contains a {@link DoubleComplex} value and
	 * does not replaces {@code null}.
	 */
	public static final ValueType<DoubleComplex> DOUBLE_COMPLEX = new ValueType<>(22, DoubleComplex.class, null);

	/**
	 * A {@link Value} with this type contains a {@code DoubleComplex[]} value
	 * and replaces {@code null} with an empty {@code DoubleComplex[]} array.
	 */
	public static final ValueType<DoubleComplex[]> DOUBLE_COMPLEX_SEQUENCE = new ValueType<>(23, DoubleComplex[].class,
			new DoubleComplex[0]);

	/**
	 * A {@link Value} with this type contains a modeled enumeration constant
	 * value and does not replace {@code null}.
	 *
	 * @see #create(Class, String)
	 * @see #create(Class, String, String, boolean, Object)
	 */
	public static final ValueType<EnumerationValue> ENUMERATION = new ValueType<>(24);

	/**
	 * A {@link Value} with this type contains a modeled enumeration constant
	 * array value and replaces {@code null} with an empty array with defined
	 * component type.
	 *
	 * @see #create(Class, String)
	 * @see #create(Class, String, String, boolean, Object)
	 */
	public static final ValueType<EnumerationValue[]> ENUMERATION_SEQUENCE = new ValueType<>(25);

	/**
	 * A {@link Value} with this type contains a {@link FileLink} value and does
	 * not replace {@code null}.
	 */
	public static final ValueType<FileLink> FILE_LINK = new ValueType<>(26, FileLink.class, null);

	/**
	 * A {@link Value} with this type contains a {@code FileLink[]} value and
	 * replaces {@code null} with an empty {@code FileLink} array.
	 */
	public static final ValueType<FileLink[]> FILE_LINK_SEQUENCE = new ValueType<>(27, FileLink[].class,
			new FileLink[0]);

	/**
	 * TODO ...
	 */
	public static final ValueType<Object> BLOB = new ValueType<>(28, Object.class, null);

	/**
	 * A {@link Value} with this type contains a {@link Object} value and does
	 * not replace {@code null}. This value type does not have a corresponding
	 * sequence type.
	 */
	public static final ValueType<Object> UNKNOWN = new ValueType<>(29, Object.class, null);

	// ======================================================================
	// Instance variables
	// ======================================================================

	/**
	 * The type is used to check assignment compatibility of non {@code null}
	 * values passed to {@link Value#set(Object)}.
	 */
	private final Class<T> type;

	/**
	 * The default value will be used in {@link Value#set(Object)} to replace a
	 * {@code null} input argument.
	 */
	private final T defaultValue;

	/**
	 * Constructor - May only be used to create {@link #ENUMERATION},
	 * {@link #ENUMERATION_SEQUENCE} or {@link #UNKNOWN} types.
	 */
	private ValueType(int ordinal) {
		this(ordinal, null, null);
	}

	/**
	 * Constructor.
	 *
	 * @param ordinal
	 *            The ordinal value of a {@link Value} with this value type.
	 * @param type
	 *            The type of value a {@link Value} with this value type will
	 *            accept.
	 * @param defaultValue
	 *            Will be used as {@code null} replacement in
	 *            {@link Value#set(Object)}.
	 */
	private ValueType(int ordinal, Class<T> type, T defaultValue) {
		super(ordinal);
		this.type = type;
		this.defaultValue = defaultValue;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link Value} initialized with given name. The {@code
	 * Value}'s initial validity flag will be set to {@code true}, the unit name
	 * will be omitted.
	 *
	 * <p>
	 * <b>Note:</b> This method is only allowed to be called where
	 * {@link #isEnumerationType()} returns {@code false}.
	 *
	 * @param name
	 *            The name of the attribute.
	 * @param input
	 *            The initial value.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumerationType()} returns {@code true}.
	 */
	public Value create(String name, Object input) {
		return create(name, "", true, input);
	}

	/**
	 * Creates a new {@link Value} initialized with given name. The {@code
	 * Value}'s initial validity flag will be set to {@code false}, the unit
	 * name will be omitted. The initial value will be the one defined in
	 * {@link #defaultValue}.
	 *
	 * <p>
	 * <b>Note:</b> This method is only allowed to be called where
	 * {@link #isEnumerationType()} returns {@code false}.
	 *
	 * @param name
	 *            The name of the attribute.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumerationType()} returns {@code true}.
	 */
	public Value create(String name) {
		return create(name, "", false, defaultValue);
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments.
	 *
	 * <p>
	 * <b>Note:</b> This method is only allowed to be called where
	 * {@link #isEnumerationType()} returns {@code false}.
	 *
	 * @param name
	 *            The name of the attribute.
	 * @param unit
	 *            The unit name of the attribute.
	 * @param valid
	 *            The initial validity flag.
	 * @param input
	 *            The initial value.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumerationType()} returns {@code true}.
	 */
	public Value create(String name, String unit, boolean valid, Object input) {
		if (isEnumerationType()) {
			throw new IllegalStateException("This value type is an enumeration type.");
		}

		return new Value(this, name, unit, valid, input, type, defaultValue, null);
	}

	/**
	 * Creates a new {@link Value} initialized with given name. The {@code
	 * Value}'s initial validity flag will be set to {@code false}, the unit
	 * name will be omitted. The initial value will be {@code null} if
	 * {@link #isSequence()} returns {@code false} otherwise an empty array with
	 * given component type in enumClass is used.
	 *
	 * <p>
	 * <b>Note:</b> This method is only allowed to be called where
	 * {@link #isEnumerationType()} returns {@code true}.
	 *
	 * @param <E>
	 *            Modeled enumeration type.
	 * @param enumClass
	 *            The enumeration class type will be used for validity checking.
	 * @param name
	 *            The name of the attribute.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumerationType()} returns {@code false}.
	 */
	public <E extends EnumerationValue> Value create(Enumeration<E> enumObj, String name) {
		return create(name, "", false, null, enumObj.getName());
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments.
	 *
	 * <p>
	 * <b>Note:</b> This method is only allowed to be called where
	 * {@link #isEnumerationType()} returns {@code true}.
	 *
	 * @param <E>
	 *            Modeled enumeration type.
	 * @param enumClass
	 *            The enumeration class type will be used for validity checking.
	 * @param name
	 *            The name of the attribute.
	 * @param unit
	 *            The unit name of the attribute.
	 * @param valid
	 *            The initial validity flag.
	 * @param input
	 *            The initial value.
	 * @return The created {@code Value} is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumerationType()} returns {@code false}.
	 */
	public <E extends EnumerationValue> Value create(String name, String unit, boolean valid, Object input,
			String valueTypeDescr) {
		if (isEnumerationType()) {
			Object nullReplacement = null;
			Class<?> valueClass = EnumerationValue.class;
			if (isSequence()) {
				nullReplacement = Array.newInstance(valueClass, 0);
				valueClass = nullReplacement.getClass();
			}

			return new Value(this, name, unit, valid, input, valueClass, nullReplacement, valueTypeDescr);
		}

		throw new IllegalStateException("This value type is not an enumeration type.");
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #STRING}</li>
	 * <li>{@link #STRING_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isString() {
		return STRING == this;
	}

	/**
	 * Returns true if this value type is {@link #STRING_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isStringSequence() {
		return STRING_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #DATE}</li>
	 * <li>{@link #DATE_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDate() {
		return DATE == this;
	}

	/**
	 * Returns true if this value type is {@link #DATE_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDateSequence() {
		return DATE_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #BOOLEAN}</li>
	 * <li>{@link #BOOLEAN_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isBoolean() {
		return BOOLEAN == this;
	}

	/**
	 * Returns true if this value type is {@link #BOOLEAN_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isBooleanSequence() {
		return BOOLEAN_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #BYTE}</li>
	 * <li>{@link #BYTE_SEQUENCE}</li>
	 * <li>{@link #SHORT}</li>
	 * <li>{@link #SHORT_SEQUENCE}</li>
	 * <li>{@link #INTEGER}</li>
	 * <li>{@link #INTEGER_SEQUENCE}</li>
	 * <li>{@link #LONG}</li>
	 * <li>{@link #LONG_SEQUENCE}</li>
	 * <li>{@link #FLOAT}</li>
	 * <li>{@link #FLOAT_SEQUENCE}</li>
	 * <li>{@link #DOUBLE}</li>
	 * <li>{@link #DOUBLE_SEQUENCE}</li>
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
	 * <li>{@link #BYTE}</li>
	 * <li>{@link #BYTE_SEQUENCE}</li>
	 * <li>{@link #SHORT}</li>
	 * <li>{@link #SHORT_SEQUENCE}</li>
	 * <li>{@link #INTEGER}</li>
	 * <li>{@link #INTEGER_SEQUENCE}</li>
	 * <li>{@link #LONG}</li>
	 * <li>{@link #LONG_SEQUENCE}</li>
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
	 * <li>{@link #BYTE}</li>
	 * <li>{@link #BYTE_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isByte() {
		return BYTE == this;
	}

	/**
	 * Returns true if this value type is {@link #BYTE_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isByteSequence() {
		return BYTE_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #SHORT}</li>
	 * <li>{@link #SHORT_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isShort() {
		return SHORT == this;
	}

	/**
	 * Returns true if this value type is {@link #SHORT_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isShortSequence() {
		return SHORT_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #INTEGER}</li>
	 * <li>{@link #INTEGER_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isInteger() {
		return INTEGER == this;
	}

	/**
	 * Returns true if this value type is {@link #INTEGER_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isIntegerSequence() {
		return INTEGER_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #LONG}</li>
	 * <li>{@link #LONG_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isLong() {
		return LONG == this;
	}

	/**
	 * Returns true if this value type is {@link #LONG_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isLongSequence() {
		return LONG_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #FLOAT}</li>
	 * <li>{@link #FLOAT_SEQUENCE}</li>
	 * <li>{@link #DOUBLE}</li>
	 * <li>{@link #DOUBLE_SEQUENCE}</li>
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
	 * <li>{@link #FLOAT}</li>
	 * <li>{@link #FLOAT_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFloat() {
		return FLOAT == this;
	}

	/**
	 * Returns true if this value type is {@link #FLOAT_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFloatSequence() {
		return FLOAT_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #DOUBLE}</li>
	 * <li>{@link #DOUBLE_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDouble() {
		return DOUBLE == this;
	}

	/**
	 * Returns true if this value type is {@link #DOUBLE_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDoubleSequence() {
		return DOUBLE_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #BYTE_STREAM}</li>
	 * <li>{@link #BYTE_STREAM_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isByteStream() {
		return BYTE_STREAM == this;
	}

	/**
	 * Returns true if this value type is {@link #BYTE_STREAM_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isByteStreamSequence() {
		return BYTE_STREAM_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #FLOAT_COMPLEX}</li>
	 * <li>{@link #FLOAT_COMPLEX_SEQUENCE}</li>
	 * <li>{@link #DOUBLE_COMPLEX}</li>
	 * <li>{@link #DOUBLE_COMPLEX_SEQUENCE}</li>
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
	 * <li>{@link #FLOAT_COMPLEX}</li>
	 * <li>{@link #FLOAT_COMPLEX_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFloatComplex() {
		return FLOAT_COMPLEX == this;
	}

	/**
	 * Returns true if this value type is {@link #FLOAT_COMPLEX_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFloatComplexSequence() {
		return FLOAT_COMPLEX_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #DOUBLE_COMPLEX}</li>
	 * <li>{@link #DOUBLE_COMPLEX_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDoubleComplex() {
		return DOUBLE_COMPLEX == this;
	}

	/**
	 * Returns true if this value type is {@link #DOUBLE_COMPLEX_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isDoubleComplexSequence() {
		return DOUBLE_COMPLEX_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #ENUMERATION}</li>
	 * <li>{@link #ENUMERATION_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isEnumeration() {
		return ENUMERATION == this;
	}

	/**
	 * Returns true if this value type is {@link #ENUMERATION_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isEnumerationSequence() {
		return ENUMERATION_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #FILE_LINK}</li>
	 * <li>{@link #FILE_LINK_SEQUENCE}</li>
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
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFileLink() {
		return FILE_LINK == this;
	}

	/**
	 * Returns true if this value type is {@link #FILE_LINK_SEQUENCE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isFileLinkSequence() {
		return FILE_LINK_SEQUENCE == this;
	}

	/**
	 * Returns true if this value type is {@link #BLOB}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isBlob() {
		return BLOB == this;
	}

	/**
	 * Returns true if this value type is {@link #UNKNOWN}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isUnknown() {
		return UNKNOWN == this;
	}

	/**
	 * Returns true if this value type is one of the following:
	 *
	 * <ul>
	 * <li>{@link #STRING_SEQUENCE}</li>
	 * <li>{@link #DATE_SEQUENCE}</li>
	 * <li>{@link #BOOLEAN_SEQUENCE}</li>
	 * <li>{@link #BYTE_SEQUENCE}</li>
	 * <li>{@link #SHORT_SEQUENCE}</li>
	 * <li>{@link #INTEGER_SEQUENCE}</li>
	 * <li>{@link #LONG_SEQUENCE}</li>
	 * <li>{@link #FLOAT_SEQUENCE}</li>
	 * <li>{@link #DOUBLE_SEQUENCE}</li>
	 * <li>{@link #BYTE_STREAM_SEQUENCE}</li>
	 * <li>{@link #FLOAT_COMPLEX_SEQUENCE}</li>
	 * <li>{@link #DOUBLE_COMPLEX_SEQUENCE}</li>
	 * <li>{@link #ENUMERATION_SEQUENCE}</li>
	 * <li>{@link #FILE_LINK_SEQUENCE}</li>
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
	 * If you extend the class ValueType, you have to ensure that T contains a
	 * field of the correct name and type, otherwise a runtime error will occur.
	 *
	 * @return The sequence version of this value type is returned.
	 */
	@SuppressWarnings("unchecked")
	public <S extends ValueType<?>> S toSequenceType() {
		if (isSequence()) {
			return (S) this;
		}

		return (S) valueOf(name() + "_SEQUENCE");
	}

	/**
	 * Returns the scalar version of this value type. This method returns
	 * itself, if this value type is a scalar type.
	 * 
	 * If you extend the class ValueType, you have to ensure that T contains a
	 * field of the correct name and type, otherwise a runtime error will occur.
	 *
	 * @return The sequence version of this value type is returned.
	 */
	@SuppressWarnings("unchecked")
	public <S extends ValueType<?>> S toSingleType() {
		if (isSequence()) {
			return (S) valueOf(name().replace("_SEQUENCE", ""));
		}

		return (S) this;
	}

	/**
	 * Returns the value class for this value type.
	 *
	 * @return The value class is returned.
	 * @throws IllegalStateException
	 *             Thrown if {@link #isEnumerationType()} returns {@code true}.
	 */
	public Class<?> getValueClass() {
		if (isEnumerationType()) {
			throw new IllegalStateException("");
		}
		
		return type;
	}
}
