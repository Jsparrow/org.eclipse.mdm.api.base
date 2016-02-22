/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Function;

/**
 * Implementation of an abstract parameter which holds a value with one of
 * the supported {@link ValueType} types listed below. The value is internally
 * stored in its {@code String} representation. Any modeled data items with such
 * a use case should extends this class. API consumers should never use this
 * class in any way, instead the implementation of this class has to be used.
 *
 * <ul>
 * 	<li>{@link ValueType#STRING}</li>
 * 	<li>{@link ValueType#DATE}</li>
 * 	<li>{@link ValueType#BOOLEAN}</li>
 * 	<li>{@link ValueType#BYTE}</li>
 * 	<li>{@link ValueType#SHORT}</li>
 * 	<li>{@link ValueType#INTEGER}</li>
 * 	<li>{@link ValueType#LONG}</li>
 * 	<li>{@link ValueType#FLOAT}</li>
 * 	<li>{@link ValueType#DOUBLE}</li>
 * 	<li>{@link ValueType#FLOAT_COMPLEX}</li>
 * 	<li>{@link ValueType#DOUBLE_COMPLEX}</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see #getVirtualValue()
 * @see #setVirtualValue(Value, Optional)
 */
public abstract class BaseParameter extends BaseDataItem implements Deletable {

	// ======================================================================
	// Class variables
	// ======================================================================

	private static final DateTimeFormatter LOCAL_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final String attrScalarType;
	private final String attrValue;

	// ======================================================================
	// Constructors
	// ======================================================================

	protected BaseParameter(String attrScalarType, String attrValue, Core core) {
		super(core);
		this.attrScalarType = attrScalarType;
		this.attrValue = attrValue;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a virtual representation of this parameter's value. This is done
	 * by converting the internally stored {@code String} value to type specified
	 * by this parameter's {@link ScalarType}, which is mapped to one of the
	 * supported {@link ValueType}s listed below. The name of the returned
	 * {@link Value} is the name of this parameter.
	 *
	 * <ul>
	 * 	<li>{@link ValueType#STRING}</li>
	 * 	<li>{@link ValueType#DATE}</li>
	 * 	<li>{@link ValueType#BOOLEAN}</li>
	 * 	<li>{@link ValueType#BYTE}</li>
	 * 	<li>{@link ValueType#SHORT}</li>
	 * 	<li>{@link ValueType#INTEGER}</li>
	 * 	<li>{@link ValueType#LONG}</li>
	 * 	<li>{@link ValueType#FLOAT}</li>
	 * 	<li>{@link ValueType#DOUBLE}</li>
	 * 	<li>{@link ValueType#FLOAT_COMPLEX}</li>
	 * 	<li>{@link ValueType#DOUBLE_COMPLEX}</li>
	 * </ul>
	 *
	 * @return The created {@link Value} with the converted value is returned.
	 * @throws IllegalStateException Thrown if it is not possible to map the
	 * 		internal {@code ScalarType} to the corresponding {@code ValueType}.
	 *
	 * ################ see TODO in the last else block!
	 */
	public Value getVirtualValue() {
		Function<String, Object> typeConverter;

		ScalarType scalarType = getScalarType();
		if(scalarType.isString()) {
			typeConverter = v -> v;
		} else if(scalarType.isDate()) {
			typeConverter = v -> LocalDateTime.parse(v, LOCAL_DATE_TIME_FORMATTER);
		} else if(scalarType.isBoolean()) {
			typeConverter = Boolean::valueOf;
		} else if(scalarType.isByte()) {
			typeConverter = Byte::valueOf;
		} else if(scalarType.isShort()) {
			typeConverter = Short::valueOf;
		} else if(scalarType.isInteger()) {
			typeConverter = Integer::valueOf;
		} else if(scalarType.isLong()) {
			typeConverter = Long::valueOf;
		} else if(scalarType.isFloat()) {
			typeConverter = Float::valueOf;
		} else if(scalarType.isDouble()) {
			typeConverter = Double::valueOf;
		} else if(scalarType.isFloatComplex()) {
			typeConverter = FloatComplex::valueOf;
		} else if(scalarType.isDoubleComplex()) {
			typeConverter = DoubleComplex::valueOf;
		} else {
			// TODO should we return a value of type UNKNOWN?! -> no exception would be raised!
			throw new IllegalStateException("Stored scalar type '" + scalarType + "' is not supported.");
		}

		Value parameterValue = getParameterValue();
		Object value = parameterValue.isValid() ? typeConverter.apply(parameterValue.extract()) : null;
		return scalarType.toSingleValueType().create(getName(), getUnitName(), parameterValue.isValid(), value);
	}

	/**
	 * Takes given virtual {@link Value}, converts its {@link ValueType} to the
	 * corresponding {@link ScalarType} and stores the contained value in its
	 * {@code String} representation. The {@code ValueType} of the virtual
	 * {@code Value} may be one of the following:
	 *
	 * <ul>
	 * 	<li>{@link ValueType#STRING}</li>
	 * 	<li>{@link ValueType#DATE}</li>
	 * 	<li>{@link ValueType#BOOLEAN}</li>
	 * 	<li>{@link ValueType#BYTE}</li>
	 * 	<li>{@link ValueType#SHORT}</li>
	 * 	<li>{@link ValueType#INTEGER}</li>
	 * 	<li>{@link ValueType#LONG}</li>
	 * 	<li>{@link ValueType#FLOAT}</li>
	 * 	<li>{@link ValueType#DOUBLE}</li>
	 * 	<li>{@link ValueType#FLOAT_COMPLEX}</li>
	 * 	<li>{@link ValueType#DOUBLE_COMPLEX}</li>
	 * </ul>
	 *
	 * @param value The virtual {@code Value} will be internally stored in its
	 * 		{@code String} representation.
	 * @param unit If the {@code Optional} is not empty, then a relation will be
	 * 		set to this {@code Unit}. Otherwise any existing relation to a {@code
	 * 		Unit} will be removed.
	 * @throws IllegalArgumentException Thrown if the {@code ValueType} of given
	 * 		{@code Value} is not supported.
	 */
	public void setVirtualValue(Value value, Optional<Unit> unit) {
		ValueType valueType = value.getValueType();
		Function<Object, String> stringConverter = Object::toString;

		if(valueType.isString()) {
			setScalarType(ScalarType.STRING);
		} else if(valueType.isDate()) {
			setScalarType(ScalarType.DATE);
			stringConverter = v -> ((LocalDateTime)v).format(LOCAL_DATE_TIME_FORMATTER);
		} else if(valueType.isBoolean()) {
			setScalarType(ScalarType.BOOLEAN);
		} else if(valueType.isByte()) {
			setScalarType(ScalarType.BYTE);
		} else if(valueType.isShort()) {
			setScalarType(ScalarType.SHORT);
		} else if(valueType.isInteger()) {
			setScalarType(ScalarType.INTEGER);
		} else if(valueType.isLong()) {
			setScalarType(ScalarType.LONG);
		} else if(valueType.isFloat()) {
			setScalarType(ScalarType.FLOAT);
		} else if(valueType.isDouble()) {
			setScalarType(ScalarType.DOUBLE);
		} else if(valueType.isFloatComplex()) {
			setScalarType(ScalarType.FLOAT_COMPLEX);
		} else if(valueType.isDoubleComplex()) {
			setScalarType(ScalarType.DOUBLE_COMPLEX);
		} else {
			throw new IllegalArgumentException("Given value type '" + valueType + "' is not supported.");
		}

		getParameterValue().set(value.isValid() ? stringConverter.apply(value.extract()) : "");
		setUnit(unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(value = ");

		Value parameterValue = getParameterValue();
		if(parameterValue.isValid()) {
			sb.append((String) getParameterValue().extract());
		}

		Optional<Unit> unit = getUnit();
		if(unit != null) {
			sb.append(" [").append(unit.get().getName()).append(']');
		}

		return sb.append(')').toString();
	}

	// ======================================================================
	// Private methods
	// ======================================================================

	/**
	 * Returns the {@link Value} of this parameter.
	 *
	 * @return This parameter's {@code Value} is returned.
	 */
	private Value getParameterValue() {
		return getValue(attrValue);
	}

	/**
	 * Returns the scalar value type of this parameter.
	 *
	 * @return This parameter's scalar type is returned.
	 */
	private ScalarType getScalarType() {
		return getValue(attrScalarType).extract();
	}

	/**
	 * Sets new scalar type for this parameter.
	 *
	 * @param scalarType The new scalar type of this parameter.
	 */
	private void setScalarType(ScalarType scalarType) {
		getValue(attrScalarType).set(scalarType);
	}

	/**
	 * Returns the name of the related {@link Unit} data item. If no {@code Unit}
	 * is related, an empty {@code String} is returned instead.
	 *
	 * @return The name of the related {@code Unit} is returned or an empty
	 * 		{@code String}.
	 */
	private String getUnitName() {
		Optional<Unit> unit = getUnit();
		if(unit.isPresent()) {
			return unit.get().getName();
		}

		return "";
	}

	/**
	 * Returns an optionally related {@link Unit} data item.
	 *
	 * @return The returned {@code Optional} is empty if no {@code Unit} is
	 * 		related.
	 */
	private Optional<Unit> getUnit() {
		return Optional.ofNullable(getCore().getInfoRelation(Unit.class));
	}

	/**
	 * Replaces current {@link Unit} relation with the given one, if the {@code
	 * Optional} is not empty. Otherwise any current relations is removed.
	 *
	 * @param unit The new {@code Unit}.
	 */
	private void setUnit(Optional<Unit> unit) {
		if(unit.isPresent()) {
			getCore().setInfoRelation(unit.get());
		} else {
			getCore().removeInfoRelation(Unit.class);
		}
	}

}
