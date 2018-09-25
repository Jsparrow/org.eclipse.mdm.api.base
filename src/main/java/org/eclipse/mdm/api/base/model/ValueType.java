/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDateTime;

/**
 * Value type enumeration is a {@link Value} factory.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class ValueType<T> extends EnumerationValue {

	/**
	 * A {@link Value} with this type contains a {@code String} value and
	 * replaces {@code null} with an empty {@code String}.
	 */
	public static final ValueType<String> STRING = new ValueType<>(String.class, "STRING", "");

	/**
	 * A {@link Value} with this type contains a {@code String[]} value replaces
	 * {@code null} with an empty {@code String} array.
	 */
	public static final ValueType<String[]> STRING_SEQUENCE = new ValueType<>(String[].class, "STRING_SEQUENCE", new String[0]);

	/**
	 * A {@link Value} with this type contains a {@link LocalDateTime} value and
	 * does not replace {@code null}.
	 */
	public static final ValueType<LocalDateTime> DATE = new ValueType<>(LocalDateTime.class, "DATE", null);

	/**
	 * A {@link Value} with this type contains a {@code LocalDateTime[]} value
	 * and replaces {@code null} with an empty {@code LocalDateTime} array.
	 */
	public static final ValueType<LocalDateTime[]> DATE_SEQUENCE = new ValueType<>(LocalDateTime[].class, "DATE_SEQUENCE", new LocalDateTime[0]);

	/**
	 * A {@link Value} with this type contains a {@link Boolean} value and
	 * replaces {@code null} with {@link Boolean#FALSE}.
	 */
	public static final ValueType<Boolean> BOOLEAN = new ValueType<>(Boolean.class, "BOOLEAN", Boolean.FALSE);

	/**
	 * A {@link Value} with this type contains a {@code boolean[]} value and
	 * replaces {@code null} with an empty {@code boolean} array.
	 */
	public static final ValueType<boolean[]> BOOLEAN_SEQUENCE = new ValueType<>(boolean[].class, "BOOLEAN_SEQUENCE", new boolean[0]);

	/**
	 * A {@link Value} with this type contains a {@link Byte} value and replaces
	 * {@code null} with a {@code Byte} containing zero.
	 */
	public static final ValueType<Byte> BYTE = new ValueType<>(Byte.class, "BYTE", Byte.valueOf((byte) 0));

	/**
	 * A {@link Value} with this type contains a {@code byte[]} value and
	 * replaces {@code null} with an empty {@code byte} array.
	 */
	public static final ValueType<byte[]> BYTE_SEQUENCE = new ValueType<>(byte[].class, "BYTE_SEQUENCE", new byte[0]);

	/**
	 * A {@link Value} with this type contains a {@link Short} value and
	 * replaces {@code null} with a {@code Short} containing zero.
	 */
	public static final ValueType<Short> SHORT = new ValueType<>(Short.class, "SHORT", Short.valueOf((short) 0));

	/**
	 * A {@link Value} with this type contains a {@code short[]} value and
	 * replaces {@code null} with an empty {@code short} array.
	 */
	public static final ValueType<short[]> SHORT_SEQUENCE = new ValueType<>(short[].class, "SHORT_SEQUENCE", new short[0]);

	/**
	 * A {@link Value} with this type contains a {@link Integer} value and
	 * replaces {@code null} with a {@code Integer} containing zero.
	 */
	public static final ValueType<Integer> INTEGER = new ValueType<>(Integer.class, "INTEGER", Integer.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code int[]} value and
	 * replaces {@code null} with an empty {@code int} array.
	 */
	public static final ValueType<int[]> INTEGER_SEQUENCE = new ValueType<>(int[].class, "INTEGER_SEQUENCE", new int[0]);

	/**
	 * A {@link Value} with this type contains a {@link Long} value and replaces
	 * {@code null} with a {@code Long} containing zero.
	 */
	public static final ValueType<Long> LONG = new ValueType<>(Long.class, "LONG", Long.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code long[]} value and
	 * replaces {@code null} with an empty {@code long} array.
	 */
	public static final ValueType<long[]> LONG_SEQUENCE = new ValueType<>(long[].class, "LONG_SEQUENCE", new long[0]);

	/**
	 * A {@link Value} with this type contains a {@link Float} value and
	 * replaces {@code null} with a {@code Float} containing zero.
	 */
	public static final ValueType<Float> FLOAT = new ValueType<>(Float.class, "FLOAT", Float.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code float[]} value and
	 * replaces {@code null} with an empty {@code float} array.
	 */
	public static final ValueType<float[]> FLOAT_SEQUENCE = new ValueType<>(float[].class, "FLOAT_SEQUENCE", new float[0]);

	/**
	 * A {@link Value} with this type contains a {@link Double} value and
	 * replaces {@code null} with a {@code Double} containing zero.
	 */
	public static final ValueType<Double> DOUBLE = new ValueType<>(Double.class, "DOUBLE", Double.valueOf(0));

	/**
	 * A {@link Value} with this type contains a {@code double[]} value and
	 * replaces {@code null} with an empty {@code double} array.
	 */
	public static final ValueType<double[]> DOUBLE_SEQUENCE = new ValueType<>(double[].class, "DOUBLE_SEQUENCE", new double[0]);

	/**
	 * A {@link Value} with this type contains a {@code byte[]} value and
	 * replaces {@code null} with an empty {@code byte} array.
	 */
	public static final ValueType<byte[]> BYTE_STREAM = new ValueType<>(byte[].class, "BYTE_STREAM", new byte[0]);

	/**
	 * A {@link Value} with this type contains a {@code byte[][]} value and
	 * replaces {@code null} with an empty {@code byte[][]} array.
	 */
	public static final ValueType<byte[][]> BYTE_STREAM_SEQUENCE = new ValueType<>(byte[][].class, "BYTE_STREAM_SEQUENCE", new byte[0][]);

	/**
	 * A {@link Value} with this type contains a {@link FloatComplex} value and
	 * does not replaces {@code null}.
	 */
	public static final ValueType<FloatComplex> FLOAT_COMPLEX = new ValueType<>(FloatComplex.class, "FLOAT_COMPLEX", null);

	/**
	 * A {@link Value} with this type contains a {@code FloatComplex[]} value
	 * and replaces {@code null} with an empty {@code FloatComplex[]} array.
	 */
	public static final ValueType<FloatComplex[]> FLOAT_COMPLEX_SEQUENCE = new ValueType<>(FloatComplex[].class, "FLOAT_COMPLEX_SEQUENCE", new FloatComplex[0]);

	/**
	 * A {@link Value} with this type contains a {@link DoubleComplex} value and
	 * does not replaces {@code null}.
	 */
	public static final ValueType<DoubleComplex> DOUBLE_COMPLEX = new ValueType<>(DoubleComplex.class, "DOUBLE_COMPLEX", null);

	/**
	 * A {@link Value} with this type contains a {@code DoubleComplex[]} value
	 * and replaces {@code null} with an empty {@code DoubleComplex[]} array.
	 */
	public static final ValueType<DoubleComplex[]> DOUBLE_COMPLEX_SEQUENCE = new ValueType<>(DoubleComplex[].class, "DOUBLE_COMPLEX_SEQUENCE" , new DoubleComplex[0]);

	/**
	 * A {@link Value} with this type contains a modeled enumeration constant
	 * value and does not replace {@code null}.
	 *
	 */
	public static final ValueType<EnumerationValue> ENUMERATION = new ValueType<>("ENUMERATION");

	/**
	 * A {@link Value} with this type contains a modeled enumeration constant
	 * array value and replaces {@code null} with an empty array with defined
	 * component type.
	 *
	 */
	public static final ValueType<EnumerationValue[]> ENUMERATION_SEQUENCE = new ValueType<>("ENUMERATION_SEQUENCE");

	/**
	 * A {@link Value} with this type contains a {@link FileLink} value and does
	 * not replace {@code null}.
	 */
	public static final ValueType<FileLink> FILE_LINK = new ValueType<>(FileLink.class, "FILE_LINK" , null);

	/**
	 * A {@link Value} with this type contains a {@code FileLink[]} value and
	 * replaces {@code null} with an empty {@code FileLink} array.
	 */
	public static final ValueType<FileLink[]> FILE_LINK_SEQUENCE = new ValueType<>(FileLink[].class, "FILE_LINK_SEQUENCE" , new FileLink[0]);

	/**
	 * TODO ...
	 */
	public static final ValueType<Object> BLOB  = new ValueType<>(Object.class, "", null);

	/**
	 * A {@link Value} with this type contains a {@link Object} value and does
	 * not replace {@code null}. This value type does not have a corresponding
	 * sequence type.
	 */
	public static final ValueType<Object> UNKNOWN  = new ValueType<>(Object.class, "UNKNOWN", null);

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
	 * 
	 * @param name
	 */
	private ValueType(String name) {
		this(null, name, null);
	}

	/**
	 * Constructor.
	 * @param type
	 *            The type of value a {@link Value} with this value type will
	 *            accept.
	 * @param name
	 * @param defaultValue
	 *            Will be used as {@code null} replacement in
	 *            {@link Value#set(Object)}.
	 */
	private ValueType(Class<T> type, String name, T defaultValue) {
		super(name);
		this.type = type;
		this.defaultValue = defaultValue;
	}

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
	public <E extends EnumerationValue> Value create(String name, String unit, boolean valid, Object input, String valueTypeDescr) {
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
		return name() != null && name().endsWith("SEQUENCE");
	}

	/**
	 * Returns the sequence version of this value type. This method returns
	 * itself, if this value type is a sequence type.
	 * 
	 * If you extend the class ValueType, you have to ensure that T contains a field 
	 * of the correct name and type,
	 * otherwise a runtime error will occur. 
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
	 * If you extend the class ValueType, you have to ensure that T 
	 * contains a field of the correct name and type,
	 * otherwise a runtime error will occur. 
	 *
	 * @return The sequence version of this value type is returned.
	 */
	@SuppressWarnings("unchecked")
	public <S extends ValueType<?>> S toSingleType() {

		if(isEnumerationType()) {
			if (isSequence()) {
				return (S) valueOf(name().replace("_SEQUENCE", ""));
			}

			return (S) this;
		} else {
			try {
				if(isSequence()) {
					Field field = getClass().getField(name().replace("_SEQUENCE", ""));
					return (S) field.get(this);
				}
				return (S) this;
			} catch (NoSuchFieldException | ClassCastException | IllegalAccessException e) {
				throw new RuntimeException("Can't figure out single type for " + name());
			}
		}
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
