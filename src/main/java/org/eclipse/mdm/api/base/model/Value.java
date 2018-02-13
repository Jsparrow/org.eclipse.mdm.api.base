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
import java.time.format.DateTimeFormatter;
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
	// Class variables
	// ======================================================================

	public static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	// ======================================================================
	// Instances variables
	// ======================================================================

	private final ValueType<?> valueType;
	private final String name;
	private final String unit;

	private boolean initialValid;
	private Object initialValue;

	private boolean valid;
	private Object value;

	private final Class<?> valueClass;
	private final String valueTypeDescr;
	private final Object defaultValue;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param valueType
	 *            The associated {@link ValueType}.
	 * @param name
	 *            The name of this container.
	 * @param unit
	 *            The name of the unit.
	 * @param valid
	 *            The initial validity flag.
	 * @param value
	 *            The initial value.
	 * @param valueClass
	 *            Used for type checking upon assignment.
	 * @param defaultValue
	 *            Used as null replacement.
	 */
	Value(ValueType<?> valueType, String name, String unit, boolean valid, Object value, Class<?> valueClass,
			Object defaultValue, String valueTypeDescr) {
		this.valueType = valueType;
		this.valueTypeDescr = valueTypeDescr;
		this.name = name;
		this.unit = unit == null ? "" : unit;

		this.valueClass = valueClass;
		this.defaultValue = defaultValue;

		// set initial value
		set(value);
		// overwrite initial validity flag
		setValid(valid);

		// preserve initial values
		initialValid = isValid();
		initialValue = copy(extract());
	}

	/**
	 * Constructor.
	 *
	 * @param origin
	 *            The origin {@link Value}.
	 * @param input
	 *            The new value.
	 */
	private Value(Value origin, Object input) {
		this(origin.valueType, origin.name, origin.unit, origin.valid, input, origin.valueClass, origin.defaultValue,
				origin.valueTypeDescr);
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
	public ValueType<?> getValueType() {
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

		if (v != null) {
			if (v.getClass().isArray()) {
				return valid && Array.getLength(v) > 0;
			} else if (v instanceof String) {
				return valid && !((String) v).isEmpty();
			}
		}

		return valid && v != null;
	}

	/**
	 * Overwrites validity flag with given flag.
	 *
	 * @param valid
	 *            The new validity flag.
	 */
	public void setValid(boolean valid) {
		this.valid = valid;
	}

	/**
	 * Returns currently stored value of this value container.
	 *
	 * @param <T>
	 *            The expected value type.
	 * @param type
	 *            The {@link ValueType}.
	 * @return Currently stored value is returned.
	 */
	@SuppressWarnings("unchecked")
	public <T> T extract(ValueType<T> type) {
		return (T) value;
	}

	/**
	 * Returns currently stored value of this value container.
	 *
	 * @param <T>
	 *            The expected value type.
	 * @return Currently stored value is returned.
	 */
	@SuppressWarnings("unchecked")
	public <T> T extract() {
		return (T) value;
	}

	/**
	 * Replaces currently stored value with the given one. If {@code null} is
	 * given, then a well defined default value is used instead and the validity
	 * flag is automatically set to {@code false}.
	 *
	 * @param input
	 *            The new value must be an instance of the type defined in
	 *            {@link ValueType#type} or in case of an enumeration type an
	 *            appropriate enumeration constant or array thereof.
	 * @throws IllegalArgumentException
	 *             Thrown if an incompatible value is given.
	 */
	public void set(Object input) {
		if (input == null) {
			value = defaultValue;
			setValid(false);
		} else if (valueClass.isInstance(input)) {
			value = input;
			setValid(true);
		} else if (input instanceof EnumerationValue) {
			setForEnumerationValue(input);
		} else {
			throw new IllegalArgumentException("Incompatible value type '" + input.getClass().getSimpleName()
					+ "' passed, expected '" + valueClass.getSimpleName() + "'.");
		}
	}

	/**
	 * Merges given value container with this instance. To be able to do so, the
	 * given value container must be compatible with this one. Value containers
	 * are compatible if the their name, unit and {@link ValueType} is equal. If
	 * the stored values or the validity flags do not match, then both values
	 * are discarded and {@code null} is taken as the initial value.
	 *
	 * @param value
	 *            The value container that will be merged with this instance.
	 * @return A new value container with merged value is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if given value container is not compatible.
	 */
	public Value merge(Value value) {
		boolean nameMissmatch = !getName().equals(value.getName());
		boolean unitMissmatch = !getUnit().equals(value.getUnit());
		boolean typeMissmatch = !getValueType().equals(value.getValueType());
		if (nameMissmatch || unitMissmatch || typeMissmatch) {
			throw new IllegalArgumentException("Unable to merge, incompatible value passed.");
		}

		boolean equalValue = Objects.deepEquals(extract(), value.extract());
		boolean bothValid = isValid() && value.isValid();
		return new Value(this, equalValue && bothValid ? extract() : null);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if (!(other instanceof Value)) {
			return false;
		}

		Value val = (Value) other;

		return Objects.equals(this.valueType, val.valueType)
				&& Objects.equals(this.name, val.name)
				&& Objects.equals(this.unit, val.unit)
				&& Objects.equals(this.initialValid, val.initialValid)
				&& Objects.deepEquals(this.initialValue, val.initialValue)
				&& Objects.equals(this.valid, val.valid)
				&& Objects.deepEquals(this.value, val.value)
				&& Objects.equals(this.valueClass, val.valueClass)
				&& Objects.equals(this.valueTypeDescr, val.valueTypeDescr)
				&& Objects.equals(this.defaultValue, val.defaultValue);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(valueType, name, unit, initialValid, initialValue, valid, value, valueClass, valueTypeDescr, defaultValue);
	}
	
	/**
	 * Returns a human readable {@code String} representation of this value. In
	 * case of a sequence value container with up to 10 values the complete
	 * sequence will be printed. Otherwise only the 5 first and last values will
	 * be printed. If the contained value is marked as not valid, then the
	 * contained value is omitted in the representation.
	 *
	 * @return The {@code String} representation of this value.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name).append(" = ");

		if (isValid()) {
			Object v = extract();
			if (v != null && v.getClass().isArray()) {
				int length = Array.getLength(v);
				sb.append('[');

				if (length > 10) {
					sb.append(range(0, 5).mapToObj(i -> readAt(v, i)).collect(Collectors.joining(", ")));
					sb.append(", ..., ");
					sb.append(range(length - 5, length).mapToObj(i -> readAt(v, i)).collect(Collectors.joining(", ")));
				} else {
					sb.append(range(0, length).mapToObj(i -> readAt(v, i)).collect(Collectors.joining(", ")));
				}
				sb.append(']');
			} else {
				sb.append(v);
			}
		}

		if (!getUnit().isEmpty()) {
			sb.append(" [").append(getUnit()).append(']');
		}

		return sb.toString();
	}

	/**
	 * Checks whether either the validity flag or the value have been modified
	 * since initialization.
	 *
	 * @return Returns {@code true} either if the flag or the value has been
	 *         modified.
	 */
	public boolean isModified() {
		return wasValid() != isValid() || !Objects.deepEquals(extractInitial(), extract());
	}

	/**
	 * Returns the initial validity flag.
	 *
	 * @return Returns {@code true} if the value was initially marked as valid.
	 */
	public boolean wasValid() {
		return initialValid;
	}

	/**
	 * Returns the initial value.
	 *
	 * @return The initial value is returned.
	 */
	public Object extractInitial() {
		return initialValue;
	}

	/**
	 * Overwrites the initial validity flag and value with the current ones.
	 */
	public void apply() {
		initialValid = isValid();
		initialValue = copy(extract());
	}
	
	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Returns the {@code String} value from given array at given position.
	 *
	 * @param array
	 *            The array {@code Object}.
	 * @param index
	 *            The index of the required value.
	 * @return The {@code String} value of the requested value is returned.
	 */
	static String readAt(Object array, int index) {
		Object value = Array.get(array, index);
		if (value != null && byte[].class.isInstance(value)) {
			return Arrays.toString((byte[]) value);
		}

		return value == null ? "" : String.valueOf(value);
	}

	// ======================================================================
	// Private methods
	// ======================================================================

	/**
	 * Returns a copy of given {@code Object}, so modifications in one do not
	 * affect to other.
	 *
	 * @param value
	 *            The object which will be copied.
	 * @return The copy is returned.
	 */
	private static Object copy(Object value) {
		if (value == null) {
			return null;
		}

		Class<?> valueClass = value.getClass();
		if (valueClass.isArray() && Array.getLength(value) > 0) {
			return createDeepCopy(value, valueClass);
		} else if (value instanceof FileLink) {
			return new FileLink((FileLink) value);
		}

		// simple immutable value
		return value;
	}
	
	/**
	 * Replaces currently stored value with the given one.
	 *
	 * @param input
	 *            The new value must be an instance of the enumeration type
	 *            an appropriate enumeration constant.
	 * @throws IllegalArgumentException
	 *             Thrown if an incompatible value is given.
	 */
	private void setForEnumerationValue(Object input) {
		String inpvalueTypeDescr = ((EnumerationValue) input).getOwner().getName();
		if (inpvalueTypeDescr == null) {
			throw new IllegalArgumentException(
					"EnumerationValue value description of input value not correctly initialized");
		}
		if (valueTypeDescr == null) {
			throw new IllegalArgumentException(
					"EnumerationValue value description not correctly initialized got null, '" + "' expected '"
							+ valueClass.getSimpleName() + "'.");
		}

		if (valueTypeDescr.equals(inpvalueTypeDescr)) {
			value = input;
			setValid(true);
		} else {
			throw new IllegalArgumentException("Incompatible value type description'" + inpvalueTypeDescr
					+ "' passed, expected '" + valueTypeDescr + "'.");
		}
	}

	/**
	 * Returns a deep copy of given {@code Object}, so modifications in one do not
	 * affect to other.
	 *
	 * @param value
	 *            The object which will be copied.
	 * @param valueClass
	 *            The class of the value object.
	 * @return The copy is returned.
	 */
	private static Object createDeepCopy(Object value, Class<?> valueClass) {
		int length = Array.getLength(value);

		if (valueClass.getComponentType().isPrimitive()) {
			Object copy = Array.newInstance(valueClass.getComponentType(), length);
			System.arraycopy(value, 0, copy, 0, length);
			return copy;
		} else {
			if (value instanceof byte[][]) {
				return Arrays.stream((byte[][]) value).map(v -> v.clone()).toArray(byte[][]::new);
			} else if (value instanceof FileLink[]) {
				return Arrays.stream((FileLink[]) value).map(FileLink::new).toArray(FileLink[]::new);
			} else {
				return Arrays.copyOf((Object[]) value, length);
			}
		}
	}
}
