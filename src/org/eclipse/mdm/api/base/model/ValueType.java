/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

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
	COMPLEX(Object.class, null),

	/**
	 * TODO
	 */
	COMPLEX_SEQUENCE(Object[].class, new Object[0]),

	/**
	 * TODO
	 */
	DOUBLE_COMPLEX(Object.class, null),

	/**
	 * TODO
	 */
	DOUBLE_COMPLEX_SEQUENCE(Object[].class, new Object[0]),

	/**
	 * TODO
	 */
	ENUMERATION(Integer.class, 0),

	/**
	 * TODO
	 */
	ENUMERATION_SEQUENCE(int[].class, new int[0]),

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
	UNKNOWN(Object.class, null);

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
	 * name will omitted. The initial value will be the one defined in {@link
	 * #defaultValue}.
	 *
	 * @param name Name of the attribute.
	 * @return The created {@code Value} is returned.
	 */
	public Value emptyValue(String name) {
		return newValue(name, "", false, defaultValue);
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments. The
	 * {@code Value}'s initial validity flag will be set to {@code true}
	 * and the unit name will omitted.
	 *
	 * @param name Name of the attribute.
	 * @param input Initial value.
	 * @return The created {@code Value} is returned.
	 */
	public Value newValue(String name, Object input) {
		return newValue(name, "", input);
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments. The
	 * {@code Value}'s initial validity flag will be set to {@code true}.
	 *
	 * @param name Name of the attribute.
	 * @param unit Unit name of the attribute.
	 * @param input Initial value.
	 * @return The created {@code Value} is returned.
	 */
	public Value newValue(String name, String unit, Object input) {
		return newValue(name, unit, true, input);
	}

	/**
	 * Creates a new {@link Value} initialized with given arguments.
	 *
	 * @param name Name of the attribute.
	 * @param unit Unit name of the attribute.
	 * @param valid Initial validity flag.
	 * @param input Initial value.
	 * @return The created {@code Value} is returned.
	 */
	public Value newValue(String name, String unit, boolean valid, Object input) {
		return new Value(name, unit, valid, input, this);
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
	 * Checks whether this value type represents an enumeration type (single or
	 * sequence value type).
	 *
	 * @return True is returned if this instance represents an enumeration
	 * 		value type.
	 */
	public boolean isEnumeration() {
		return name().startsWith("ENUMERATION");
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
