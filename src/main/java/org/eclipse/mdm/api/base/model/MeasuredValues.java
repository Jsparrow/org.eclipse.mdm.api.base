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
import java.util.stream.Collectors;

public final class MeasuredValues {

	private final ScalarType scalarType;
	private final Object values;
	private final boolean allValid;
	private final boolean[] flags;

	// TODO: replace name and unit Strings with Channel!
	private final String name;
	private final String unit;

	private final int length;


	MeasuredValues(ScalarType scalarType, String name, String unit, Object values) {
		this.name = name;
		this.unit = unit == null  ? "" : unit;
		this.scalarType = scalarType;
		this.values = values;
		flags = new boolean[0];

		if(values == null) {
			length = 0;
			allValid = false;
		} else {
			length = Array.getLength(values);
			allValid = length > 0;
		}
	}

	MeasuredValues(ScalarType scalarType, String name, String unit, Object values, boolean[] flags) {
		this.name = name;
		this.unit = unit == null  ? "" : unit;
		this.scalarType = scalarType;
		this.values = values;
		allValid = false;

		if(values == null || flags == null) {
			throw new IllegalArgumentException(); // TODO neither values nor flags allowed to be null
		} else if(Array.getLength(values) != flags.length) {
			throw new IllegalArgumentException(); // TODO: length of values and flags does not match!
		}

		this.flags = flags;
		length = flags.length;
	}

	public String getName() {
		return name;
	}

	public ScalarType getScalarType() {
		return scalarType;
	}

	public boolean areAllValid() {
		return allValid;
	}

	public Value createMeasuredValuesValue(String name) {
		return scalarType.toValueType().create(name, unit, true, values);
	}

	public boolean[] getFlags() {
		return flags.clone();
	}

	public int getLength() {
		// TODO maybe useful for validation when multiple requests for same channel group created?!
		// -> appropriate builder required!
		return length;
	}

	public String getUnit() {
		return unit;
	}

	// TODO docs not valid values are replaced with 'XX'
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("MeasuredValues(channelName = ").append(getName());
		sb.append(", scalarType = ").append(getScalarType());

		if(!getUnit().isEmpty()) {
			sb.append(", unit = ").append(getUnit());
		}

		sb.append(", values = [");
		String notValidMarker = "XX";
		if(getLength() > 10) {
			if(areAllValid()) {
				sb.append(range(0, 5).mapToObj(i -> readAt(values, i)).collect(Collectors.joining(", ")));
				sb.append(", ..., ");
				sb.append(range(length-5, length).mapToObj(i -> readAt(values, i)).collect(Collectors.joining(", ")));
			} else {
				sb.append(range(0, 5).mapToObj(i -> flags[i] ? readAt(values, i) : notValidMarker)
						.collect(Collectors.joining(", ")));
				sb.append(", ..., ");
				sb.append(range(length-5, length).mapToObj(i -> flags[i] ? readAt(values, i) : notValidMarker)
						.collect(Collectors.joining(", ")));
			}
		} else if(getLength() > 0) {
			if(areAllValid()) {
				sb.append(range(0, length).mapToObj(i -> readAt(values, i)).collect(Collectors.joining(", ")));
			} else {
				sb.append(range(0, length).mapToObj(i -> flags[i] ? readAt(values, i) : notValidMarker)
						.collect(Collectors.joining(", ")));
			}
		}

		return sb.append("])").toString();
	}

}
