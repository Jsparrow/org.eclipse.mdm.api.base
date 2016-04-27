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
 * Implementation of an abstract parameter which holds a value with one of the
 * supported {@link ValueType}s listed below. The value is internally stored in
 * its {@code String} representation. Any modeled entity with such a use case
 * should extends this class. API consumers should never use this class in any
 * way, instead the implementations of this class have to be used.
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
 * @see #setObjectValue(Object, Unit)
 * @see #setStringValue(String)
 * @see #setDateValue(LocalDateTime)
 * @see #setBooleanValue(Boolean)
 * @see #setByteValue(Byte, Unit)
 * @see #setShortValue(Short, Unit)
 * @see #setIntegerValue(Integer, Unit)
 * @see #setLongValue(Long, Unit)
 * @see #setFloatValue(Float, Unit)
 * @see #setDoubleValue(Double, Unit)
 * @see #setFloatComplexValue(FloatComplex, Unit)
 * @see #setDoubleComplexValue(DoubleComplex, Unit)
 */
public abstract class BaseParameter extends BaseEntity implements Deletable {

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

	/**
	 * Constructor.
	 *
	 * @param attrScalarType Name of the {@link ScalarType} attribute.
	 * @param attrValue Name of the {@code String} value attribute.
	 * @param core The {@link EntityCore}.
	 */
	protected BaseParameter(String attrScalarType, String attrValue, EntityCore core) {
		super(core);
		this.attrScalarType = attrScalarType;
		this.attrValue = attrValue;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a virtual representation of this parameter's value. This is done
	 * by converting the internally stored {@code String} value to type
	 * specified by this parameter's {@link ValueType} (the allowed types are
	 * listed below). The name of the returned virtual {@link Value} is the name
	 * of this parameter.
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
	 * <p><b>Note:</b> The returned value is a virtual one and hence intended for
	 * displaying purposes only. To change this parameter's value one of its
	 * {@code setXYValue} methods has to be used.
	 *
	 * @return The created {@code Value} with the converted value is returned.
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
			return ValueType.UNKNOWN.create(getName());
		}

		Value parameterValue = getParameterValue();
		Object value = parameterValue.isValid() ? typeConverter.apply(parameterValue.extract()) : null;
		return scalarType.toSingleValueType().create(getName(), getUnitName(), parameterValue.isValid(), value);
	}

	/**
	 * Takes given value and determines its type. Finally value and the optional
	 * {@code Unit} are passed to the corresponding {@code setXYValue(XY)} method
	 * as listed below. The value is allowed to be an instance of the following
	 * types: {@code String}, {@code LocalDateTime}, {@code Boolean}, {@code Byte},
	 * {@code Short}, {@code Integer}, {@code Long}, {@code Float}, {@code Double},
	 * {@code FloatComplex} and {@code DoubleComplex}.
	 *
	 * <p><b>Note:</b> If the given value is an instance of {@code String}, {@code
	 * LocalDateTime} or a {@code Boolean}, then the given unit is ignored.
	 *
	 * @param value The new value for this parameter is not allowed to be null.
	 * @param unit The optionally related {@code Unit}.
	 * @throws IllegalArgumentException Thrown if the given value is not supported.
	 * @see #setStringValue(String)
	 * @see #setDateValue(LocalDateTime)
	 * @see #setBooleanValue(Boolean)
	 * @see #setByteValue(Byte, Unit)
	 * @see #setShortValue(Short, Unit)
	 * @see #setIntegerValue(Integer, Unit)
	 * @see #setLongValue(Long, Unit)
	 * @see #setFloatValue(Float, Unit)
	 * @see #setDoubleValue(Double, Unit)
	 * @see #setFloatComplexValue(FloatComplex, Unit)
	 * @see #setDoubleComplexValue(DoubleComplex, Unit)
	 */
	public void setObjectValue(Object value, Unit unit) {
		if(value instanceof String) {
			setStringValue((String) value);
		} else if(value instanceof LocalDateTime) {
			setDateValue((LocalDateTime) value);
		} else if(value instanceof Boolean) {
			setBooleanValue((Boolean) value);
		} else if(value instanceof Byte) {
			setByteValue((Byte) value, unit);
		} else if(value instanceof Short) {
			setShortValue((Short) value, unit);
		} else if(value instanceof Integer) {
			setIntegerValue((Integer) value, unit);
		} else if(value instanceof Long) {
			setLongValue((Long) value, unit);
		} else if(value instanceof Float) {
			setFloatValue((Float) value, unit);
		} else if(value instanceof Double) {
			setDoubleValue((Double) value, unit);
		} else if(value instanceof FloatComplex) {
			setFloatComplexValue((FloatComplex) value, unit);
		} else if(value instanceof DoubleComplex) {
			setDoubleComplexValue((DoubleComplex) value, unit);
		} else {
			throw new IllegalArgumentException("Value '" + value + "' of type '" + value.getClass().getSimpleName()
					+ "' is not supported.");
		}
	}

	/**
	 * Replaces current value with given {@code String} value. Any existing
	 * relation to a {@link Unit} will be removed.
	 *
	 * @param value The new value for this parameter.
	 */
	public void setStringValue(String value) {
		setScalarType(ScalarType.STRING);
		getParameterValue().set(value);
		setUnit(null);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code LocalDateTime}. Any existing relation to a {@link Unit} will be
	 * removed.
	 *
	 * @param value The new value for this parameter.
	 */
	public void setDateValue(LocalDateTime value) {
		setScalarType(ScalarType.DATE);
		getParameterValue().set(value.format(LOCAL_DATE_TIME_FORMATTER));
		setUnit(null);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Boolean}. Any existing relation to a {@link Unit} will be removed.
	 *
	 * @param value The new value for this parameter.
	 */
	public void setBooleanValue(Boolean value) {
		setScalarType(ScalarType.BOOLEAN);
		getParameterValue().set(value.toString());
		setUnit(null);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Byte}. Any existing relation to a {@link Unit} will be replaced
	 * with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setByteValue(Byte value, Unit unit) {
		setScalarType(ScalarType.BYTE);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Short}. Any existing relation to a {@link Unit} will be replaced
	 * with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setShortValue(Short value, Unit unit) {
		setScalarType(ScalarType.SHORT);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Integer}. Any existing relation to a {@link Unit} will be replaced
	 * with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setIntegerValue(Integer value, Unit unit) {
		setScalarType(ScalarType.INTEGER);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Long}. Any existing relation to a {@link Unit} will be replaced
	 * with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setLongValue(Long value, Unit unit) {
		setScalarType(ScalarType.LONG);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Float}. Any existing relation to a {@link Unit} will be replaced
	 * with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setFloatValue(Float value, Unit unit) {
		setScalarType(ScalarType.FLOAT);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code Double}. Any existing relation to a {@link Unit} will be replaced
	 * with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setDoubleValue(Double value, Unit unit) {
		setScalarType(ScalarType.DOUBLE);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code FloatComplex}. Any existing relation to a {@link Unit} will be
	 * replaced with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setFloatComplexValue(FloatComplex value, Unit unit) {
		setScalarType(ScalarType.FLOAT_COMPLEX);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * Replaces current value with the {@code String} representation of given
	 * {@code DoubleComplex}. Any existing relation to a {@link Unit} will be
	 * replaced with the given one.
	 *
	 * @param value The new value for this parameter.
	 * @param unit The relation to a unit is optional.
	 */
	public void setDoubleComplexValue(DoubleComplex value, Unit unit) {
		setScalarType(ScalarType.DOUBLE_COMPLEX);
		getParameterValue().set(value.toString());
		setUnit(unit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append("(Name = ").append(getName());

		sb.append(", Value = ");
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
	 * @param scalarType The new {@code ScalarType}.
	 */
	private void setScalarType(ScalarType scalarType) {
		getValue(attrScalarType).set(scalarType);
	}

	/**
	 * Returns the name of the related {@link Unit}. If no {@code Unit} is
	 * related, an empty {@code String} is returned instead.
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
	 * Returns an optionally related {@link Unit}.
	 *
	 * @return The returned {@code Optional} is empty if no {@code Unit} is
	 * 		related.
	 */
	private Optional<Unit> getUnit() {
		return Optional.ofNullable(getCore().getMutableStore().get(Unit.class));
	}

	/**
	 * Replaces current {@link Unit} relation with the given one, if the {@code
	 * Optional} is not empty. Otherwise any current relations is removed.
	 *
	 * @param unit The new {@code Unit} may be {@code null}.
	 */
	private void setUnit(Unit unit) {
		if(unit == null) {
			getCore().getMutableStore().remove(Unit.class);
		} else {
			getCore().getMutableStore().set(unit);
		}
	}

}
