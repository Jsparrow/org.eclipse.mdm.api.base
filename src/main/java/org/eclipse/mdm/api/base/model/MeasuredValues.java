/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import static java.util.stream.IntStream.range;
import static org.eclipse.mdm.api.base.model.Value.readAt;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Class represents a sequence of measured values.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class MeasuredValues {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final ScalarType scalarType;
	private final Object values;
	private final boolean[] flags;

	/*
	 * TODO: - replace name and unit with the corresponding Channel & Unit
	 * entities - provide AxisType, Independent flag and further informations if
	 * required - provide an overall offset for this value sequence
	 */

	private final String name;
	private final String unit;

	private final int length;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param scalarType
	 *            The {@link ScalarType} of this measured values.
	 * @param name
	 *            This is the name of the corresponding {@link Channel}.
	 * @param unit
	 *            Name of the unit the contained values are of.
	 * @param values
	 *            The measured values.
	 * @param flags
	 *            The validity flags of the measured values.
	 * @throws IllegalArgumentException
	 *             Thrown if values or flags is null or length of values and
	 *             flags is not equal.
	 */
	MeasuredValues(ScalarType scalarType, String name, String unit, Object values, boolean[] flags) {
		this.name = name;
		this.unit = unit == null ? "" : unit;
		this.scalarType = scalarType;
		this.values = values;

		if (values == null || flags == null) {
			throw new IllegalArgumentException("Neither values nor flags is allowed to be null.");
		} else if (Array.getLength(values) != flags.length) {
			throw new IllegalArgumentException("Length of values and flags is not equal.");
		}

		this.flags = flags;
		length = flags.length;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the name of this measured values sequence.
	 *
	 * @return The name is returned.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the unit name for this measured values sequence.
	 *
	 * @return The unit name is returned.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Returns the {@link ScalarType} of this measured values sequence.
	 *
	 * @return The {@code ScalarType} is returned.
	 */
	public ScalarType getScalarType() {
		return scalarType;
	}

	/**
	 * Returns the number of values of this measured values sequence.
	 *
	 * @return The number of values is returned.
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Returns a typed {@link ValueIterator}. Its usage is described below:
	 *
	 * <pre>
	 * // assume the measuredValues().getScalarType() == ScalarType.BYTE
	 * ValueIterator&lt;Byte&gt; valueIterator = measuredValues().iterator();
	 * while (valueIterator.hasNext()) {
	 * 	boolean isCurrentValid = valueIterator.isValid();
	 * 	Byte currentValue = valueIterator.next();
	 * }
	 * </pre>
	 *
	 * @param <E>
	 *            This type has to be derived from the {@link ScalarType} of
	 *            this measured values.
	 * @return A typed {@code ValueIterator} is returned.
	 */
	public <E> ValueIterator<E> iterator() {
		// TODO provide custom implementations for each type and typed
		// nextType() methods
		// idea: getScalarType().createIterator(values, flags); // <- package
		// private
		return new ValueIterator<E>() {

			private int index = 0;

			@Override
			public boolean hasNext() {
				return index < length;
			}

			@Override
			public boolean isValid() {
				return flags[index];
			}

			@Override
			@SuppressWarnings("unchecked")
			public E next() {
				if (hasNext()) {
					return (E) Array.get(values, index++);
				}

				throw new NoSuchElementException("Subsequent value is not available.");
			}
		};
	}

	/**
	 * Returns a human readable {@code String} representation of this measured
	 * values. If this sequence contains more than 10 values, only the first and
	 * last 5 values are written. If a value is marked as not valid, then 'XX'
	 * is written instead of its value.
	 *
	 * @return The {@code String} representation of this entity.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("MeasuredValues(ChannelName = ").append(getName());
		sb.append(", ScalarType = ").append(getScalarType());

		if (!getUnit().isEmpty()) {
			sb.append(", Unit = ").append(getUnit());
		}

		sb.append(", Values = [");
		String notValidMarker = "XX";
		if (getLength() > 10) {
			sb.append(range(0, 5).mapToObj(i -> flags[i] ? readAt(values, i) : notValidMarker)
					.collect(Collectors.joining(", ")));
			sb.append(", ..., ");
			sb.append(range(length - 5, length).mapToObj(i -> flags[i] ? readAt(values, i) : notValidMarker)
					.collect(Collectors.joining(", ")));
		} else if (getLength() > 0) {
			sb.append(range(0, length).mapToObj(i -> flags[i] ? readAt(values, i) : notValidMarker)
					.collect(Collectors.joining(", ")));
		}

		return sb.append("])").toString();
	}

	// ======================================================================
	// Inner Types
	// ======================================================================

	/**
	 * The measured values iterator.
	 *
	 * @param <E>
	 *            Type of the returned values.
	 * @since 1.0.0
	 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
	 */
	public interface ValueIterator<E> extends Iterator<E> {

		/**
		 * Returns true if the current value is marked as valid.
		 *
		 * @return True if current value is valid.
		 */
		boolean isValid();

	}

}
