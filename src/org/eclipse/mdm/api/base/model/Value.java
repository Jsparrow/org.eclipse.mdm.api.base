/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Generic value container. This value container is tightly coupled with its
 * {@link ValueType} and therefore only allows to store values of the type
 * defined in {@link ValueType#type}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ValueType
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

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param name Name of the attribute.
	 * @param unit Unit name of the attribute.
	 * @param valid Initial validity flag.
	 * @param value Initial value.
	 * @param valueType The value type.
	 */
	Value(String name, String unit, boolean valid, Object value, ValueType valueType) {
		this.name = name;
		this.unit = unit == null ? "" : unit;
		this.valueType = valueType;

		// set initial value
		set(value);
		// overwrite initial validity flag
		setValid(valid);
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
		return valid;
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
	 * passed, then the default value as defined in {@link
	 * ValueType#defaultValue} is stored as the current value and the validity
	 * flag is set to {@code false}.
	 *
	 * @param input The new value must be an instance of the type defined in
	 * 		{@link ValueType#type}.
	 * @throws IllegalArgumentException Thrown if passed value is not an
	 * 		instance of {@link ValueType#type}.
	 */
	public void set(Object input) {
		if(input == null) {
			value = getValueType().defaultValue;
			setValid(false);
			return;
		}

		if(valueType.type.isInstance(input)) {
			/**
			 * TODO: This will not work in case of enumerations!!
			 */
			value = input;
			setValid(true);
			return;
		}

		throw new IllegalArgumentException("Incompatible value type '"
				+ input.getClass().getSimpleName() + "' passed, expected '"
				+ getValueType().type.getSimpleName() + "'.");
	}

	/**
	 * Merges passed value container with this instance. To be able to do so,
	 * the passed value container must be compatible with this one. Value
	 * containers are compatible if the their name, unit and {@link ValueType}
	 * is equal. If the stored values or the validity flags do not match, then
	 * both values are discarded and {@code null} is taken as the initial
	 * value.
	 *
	 * @param value The value container that will be merged with this instance.
	 * @return A new value container with merged value is returned.
	 * @throws IllegalArgumentException Thrown if passed value container is not
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
		return getValueType().newValue(getName(), getUnit(), equalValue && bothValid ? extract() : null);
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
					sb.append(IntStream.range(0, 5).mapToObj(i -> Array.get(v, i))
							.map(String::valueOf).collect(Collectors.joining(", ")));
					sb.append(", ..., ");
					sb.append(IntStream.range(length-5, length).mapToObj(i -> Array.get(v, i))
							.map(String::valueOf).collect(Collectors.joining(", ")));
				} else {
					sb.append(IntStream.range(0, length).mapToObj(i -> Array.get(v, i))
							.map(String::valueOf).collect(Collectors.joining(", ")));
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

}
