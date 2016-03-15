/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import static java.util.stream.IntStream.range;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Generic value container. This value container is tightly coupled with its
 * {@link ValueType}. It allows only to store assignment compatible values.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class Value {

	// ======================================================================
	// Instances variables
	// ======================================================================

	private final ValueType valueType;
	private final String name;
	private final String unit;

	private boolean valid;
	private Object value;

	private final Class<?> valueClass; // type checking
	private final Object defaultValue; // null replacement

	// ======================================================================
	// Constructors
	// ======================================================================

	Value(ValueType valueType, String name, String unit, boolean valid, Object value) {
		this(valueType, name, unit, valid, value, valueType.type, valueType.defaultValue);
	}

	Value(ValueType valueType, String name, String unit, boolean valid, Object value, Class<?> valueClass, Object defaultValue) {
		this.valueType = valueType;
		this.name = name;
		this.unit = unit == null ? "" : unit;

		this.valueClass = valueClass;
		this.defaultValue = defaultValue;

		// set initial value
		set(value);
		// overwrite initial validity flag
		setValid(valid);
	}

	private Value(Value origin, Object input) {
		this(origin.valueType, origin.name, origin.unit, origin.valid, input, origin.valueClass, origin.defaultValue);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the name of this value container.
	 *
	 * @return This value container's name is returned.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the {@link ValueType} this value container is associated with.
	 *
	 * @return The associated {@code ValueType} is returned.
	 */
	public ValueType getValueType() {
		return valueType;
	}

	/**
	 * Returns the unit name of this value container.
	 *
	 * @return The unit name of this value container is returned.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Returns the validity flag of this value container.
	 *
	 * @return True, if the stored value is marked to be valid.
	 */
	public boolean isValid() {
		Object v = extract();

		if(v != null) {
			if(v.getClass().isArray()) {
				return valid && Array.getLength(v) > 0;
			} else if(v instanceof String) {
				return valid && !((String)v).isEmpty();
			}
		}

		return valid && v != null;
	}

	/**
	 * Overwrites validity flag with given flag.
	 *
	 * @param valid The new validity flag.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * Returns currently stored value of this value container.
	 *
	 * @param <T> The expected value type.
	 * @return Currently stored value is returned.
	 */
	@SuppressWarnings("unchecked")
	public <T> T extract() {
		return (T) value;
	}

	/**
	 * Replaces currently stored value with the given one. If {@code null} is
	 * given, then a well defined default value is used instead and the
	 * validity flag is automatically set to {@code false}.
	 *
	 * @param input The new value must be an instance of the type defined in
	 * 		{@link ValueType#type} or in case of an enumeration type an
	 * 		appropriate enumeration constant or array thereof.
	 * @throws IllegalArgumentException Thrown if an incompatible value is
	 * 		given.
	 */
	public void set(Object input) {
		if(input == null) {
			value = defaultValue;
			setValid(false);
		} else if(valueClass.isInstance(input)) {
			value = input;
			setValid(true);
		} else {
			throw new IllegalArgumentException("Incompatible value type '"
					+ input.getClass().getSimpleName() + "' passed, expected '"
					+ getValueType().type.getSimpleName() + "'.");
		}
	}

	/**
	 * Merges given value container with this instance. To be able to do so,
	 * the given value container must be compatible with this one. Value
	 * containers are compatible if the their name, unit and {@link ValueType}
	 * is equal. If the stored values or the validity flags do not match, then
	 * both values are discarded and {@code null} is taken as the initial
	 * value.
	 *
	 * @param value The value container that will be merged with this instance.
	 * @return A new value container with merged value is returned.
	 * @throws IllegalArgumentException Thrown if given value container is not
	 * 		compatible.
	 */
	public Value merge(Value value) {
		boolean nameMissmatch = !getName().equals(value.getName());
		boolean unitMissmatch = !getUnit().equals(value.getUnit());
		boolean typeMissmatch = !getValueType().equals(value.getValueType());
		if(nameMissmatch || unitMissmatch || typeMissmatch) {
			throw new IllegalArgumentException("Unable to merge, incompatible value passed.");
		}

		boolean equalValue = Objects.equals(extract(), value.extract());
		boolean bothValid = isValid() && value.isValid();
		return new Value(this, equalValue && bothValid ? extract() : null);
	}

	/**
	 * Returns a human readable {@code String} representation of this value. In
	 * case of a sequence value container with up to 10 values the complete
	 * sequence will be printed. Otherwise only the 5 first and last values
	 * will be printed. If the contained value is marked as not valid, then the
	 * contained value is omitted in the representation.
	 *
	 * @return The {@code String} representation of this value.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name).append(" = ");

		if(isValid()) {
			Object v = extract();
			if(v != null && v.getClass().isArray()) {
				int length = Array.getLength(v);
				sb.append('[');

				if(length > 10) {
					sb.append(range(0, 5).mapToObj(i -> readAt(v, i)).collect(Collectors.joining(", ")));
					sb.append(", ..., ");
					sb.append(range(length-5, length).mapToObj(i -> readAt(v, i)).collect(Collectors.joining(", ")));
				} else {
					sb.append(range(0, length).mapToObj(i -> readAt(v, i)).collect(Collectors.joining(", ")));
				}
				sb.append(']');
			} else {
				sb.append(v);
			}
		}

		if(!getUnit().isEmpty()) {
			sb.append(" [").append(getUnit()).append(']');
		}

		return sb.toString();
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	static String readAt(Object array, int index) {
		Object value = Array.get(array, index);
		if(value != null && byte[].class.isInstance(value)) {
			return Arrays.toString((byte[])value);
		}

		return String.valueOf(value);
	}

}
