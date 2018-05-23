/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.adapter;

import org.eclipse.mdm.api.base.model.Enumeration;
import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.model.ValueType;

import javax.xml.ws.Service;
import java.lang.reflect.Field;

/**
 * Represents a modeled attribute.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see EntityType
 * @see ValueType
 * @see Value
 */
public interface Attribute {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link EntityType} this attribute belongs to.
	 *
	 * @return The owning {@code EntityType} is returned.
	 */
	EntityType getEntityType();

	/**
	 * Returns the name of this attribute.
	 *
	 * @return The name is returned.
	 */
	String getName();

	/**
	 * Returns the unit name of this attribute.
	 *
	 * @return The unit name is returned.
	 */
	String getUnit();

	/**
	 * Returns the {@link ValueType} of this attribute.
	 *
	 * @return The {@code ValueType} is returned.
	 */
	ValueType getValueType();

	/**
	 * Returns the enumeration {@code Class} associated with this
	 * {@code Attribute}.
	 *
	 * @return The enumeration {@code Class} associated with this
	 *         {@code Attribute} is returned.
	 * @throws IllegalStateException
	 *             Thrown if the {@link ValueType} of this {@code Attribute} is
	 *             neither {@link ValueType#ENUMERATION} nor
	 *             {@link ValueType#ENUMERATION_SEQUENCE}.
	 */
	Enumeration<?> getEnumObj();

	/**
	 * Creates a new and empty {@link Value}.
	 *
	 * @return Created {@code Value} is returned.
	 */
	default Value createValue() {
		ValueType valueType = getValueType();
		if (valueType.isEnumerationType()) {
			return valueType.create(getEnumObj(), getName());
		} else {
			return valueType.create(getName());
		}
	}

	/**
	 * Creates a new {@link Value} with given initial value.
	 *
	 * @param input
	 *            The initial value.
	 * @return Created {@code Value} is returned.
	 */
	default Value createValue(Object input) {
		return createValue("", input);
	}

	/**
	 * Creates a new {@link Value} with given unit name and initial value.
	 *
	 * @param unit
	 *            The name of unit.
	 * @param input
	 *            The initial value.
	 * @return Created {@code Value} is returned.
	 */
	default Value createValue(String unit, Object input) {
		return createValue(unit, true, input);
	}

	/**
	 * Creates a new sequence {@link Value} with given unit name and initial
	 * value.
	 *
	 * @param unit
	 *            The name of unit.
	 * @param input
	 *            The initial value.
	 * @return Created {@code Value} is returned.
	 */
	default Value createValueSeq(String unit, Object input) {
		if(getValueType().isEnumerationType()) {
			return createEnumerationSequence(unit, input);
		}
		return createValueSequence(unit, input);
	}

	default Value createValueSequence(String unit, Object input) {
		ValueType valueType = getValueType();
		try {
			Field field = valueType.getClass().getField(valueType.name() + "_SEQUENCE");
			ValueType<?> sequenceValueType = (ValueType<?>) field.get(valueType);
			return sequenceValueType.create(getName(), unit, true, input);

		} catch (NoSuchFieldException | ClassCastException | IllegalAccessException e) {
			throw new RuntimeException("Can't figure out sequence type for " + valueType.name());
		}
	}

	default Value createEnumerationSequence(String unit, Object input) {
		ValueType valueType = getValueType().toSequenceType();
		return valueType.create(getName(), unit, true, input, getEnumObj().getName());
	}

	/**
	 * Creates a new {@link Value} with given unit name, initial valid flag and
	 * value.
	 *
	 * @param unit
	 *            The name of unit.
	 * @param valid
	 *            The initial valid flag.
	 * @param input
	 *            The initial value.
	 * @return Created {@code Value} is returned.
	 */
	default Value createValue(String unit, boolean valid, Object input) {
		ValueType<?> valueType = getValueType();
		if (valueType.isEnumerationType()) {
			return valueType.create(getName(), unit, valid, input, getEnumObj().getName());
		} else {
			return valueType.create(getName(), unit, valid, input);
		}
	}

	/**
	 * Returns the name of this attribute.
	 *
	 * @return The name of this attribute is returned.
	 */
	@Override
	String toString();

}
