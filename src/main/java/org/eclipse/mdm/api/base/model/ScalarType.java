/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.lang.reflect.Array;
import java.time.LocalDateTime;

public enum ScalarType {

	// ======================================================================
	// Enumerations
	// ======================================================================

	STRING(ValueType.STRING_SEQUENCE, String.class),

	DATE(ValueType.DATE_SEQUENCE, LocalDateTime.class),

	BOOLEAN(ValueType.BOOLEAN_SEQUENCE, boolean.class),

	BYTE(ValueType.BYTE_SEQUENCE, byte.class),

	SHORT(ValueType.SHORT_SEQUENCE, short.class),

	INTEGER(ValueType.INTEGER_SEQUENCE, int.class),

	LONG(ValueType.LONG_SEQUENCE, long.class),

	FLOAT(ValueType.FLOAT_SEQUENCE, float.class),

	DOUBLE(ValueType.DOUBLE_SEQUENCE, double.class),

	BYTE_STREAM(ValueType.BYTE_STREAM_SEQUENCE, byte[].class),

	FLOAT_COMPLEX(ValueType.FLOAT_COMPLEX_SEQUENCE, FloatComplex.class),

	DOUBLE_COMPLEX(ValueType.DOUBLE_COMPLEX_SEQUENCE, DoubleComplex.class),

	ENUMERATION(ValueType.ENUMERATION_SEQUENCE),

	FILE_LINK(ValueType.FILE_LINK_SEQUENCE, FileLink.class),

	// TODO
	@Deprecated BLOB(ValueType.BLOB, Object.class), // TODO there is no BLOB_SEQUENCE TYPE!!!

	UNKNOWN(ValueType.UNKNOWN);

	private final ValueType valueType;
	private final Class<?> arrayType;

	private ScalarType(ValueType valueType) {
		this.valueType = valueType;
		arrayType = null;
	}

	private ScalarType(ValueType valueType, Class<?> componentType) {
		this.valueType = valueType;
		arrayType = Array.newInstance(componentType, 0).getClass();
	}

	public MeasuredValues createMeasuredValues(Channel channel, Object input) {
		if(isEnumeration() || isUnknown()) {
			throw new IllegalStateException(); // TODO msg - not allowed!
		} else if(!arrayType.isInstance(input)) {
			throw new IllegalArgumentException(); // TODO msg - incompatible type
		}

		throw new IllegalStateException(); // TODO implement!
	}

	@Deprecated public MeasuredValues createMeasuredValues(String name, String unit, Object input) {
		if(isEnumeration() || isUnknown()) {
			throw new IllegalStateException(); // TODO msg - not allowed!
		} else if(!arrayType.isInstance(input)) {
			throw new IllegalArgumentException(); // TODO msg - incompatible type
		}

		// TODO check values for null?!

		return new MeasuredValues(this, name, unit, input);
	}

	public MeasuredValues createMeasuredValues(Channel channel, Object input, boolean[] flags) {
		if(isEnumeration() || isUnknown()) {
			throw new IllegalStateException(); // TODO msg - not allowed!
		} else if(!arrayType.isInstance(input)) {
			throw new IllegalArgumentException(); // TODO msg - incompatible type
		}

		throw new IllegalStateException(); // TODO implement!
	}

	@Deprecated public MeasuredValues createMeasuredValues(String name, String unit, Object input, boolean[] flags) {
		if(isEnumeration() || isUnknown()) {
			throw new IllegalStateException(); // TODO msg - not allowed!
		} else if(!arrayType.isInstance(input)) {
			throw new IllegalArgumentException(); // TODO msg - incompatible type
		}

		// TODO check values for null?!

		return new MeasuredValues(this, name, unit, input, flags);
	}

	public boolean isString() {
		return STRING == this;
	}

	public boolean isDate() {
		return DATE == this;
	}

	public boolean isBoolean() {
		return BOOLEAN == this;
	}

	public boolean isNumericalType() {
		return isIntegerType() || isFloatType();
	}

	public boolean isIntegerType() {
		return isByte() || isShort() || isInteger() || isLong();
	}

	public boolean isByte() {
		return BYTE == this;
	}

	public boolean isShort() {
		return SHORT == this;
	}

	public boolean isInteger() {
		return INTEGER == this;
	}

	public boolean isLong() {
		return LONG == this;
	}

	public boolean isFloatType() {
		return isFloat() || isDouble();
	}

	public boolean isFloat() {
		return FLOAT == this;
	}

	public boolean isDouble() {
		return DOUBLE == this;
	}

	public boolean isByteStream() {
		return BYTE_STREAM == this;
	}

	public boolean isComplexType() {
		return isFloatComplex() || isDoubleComplex();
	}

	public boolean isFloatComplex() {
		return FLOAT_COMPLEX == this;
	}

	public boolean isDoubleComplex() {
		return DOUBLE_COMPLEX == this;
	}

	public boolean isEnumeration() {
		return ENUMERATION == this;
	}

	public boolean isFileLink() {
		return FILE_LINK == this;
	}

	public boolean isBlob() {
		return BLOB == this;
	}

	public boolean isUnknown() {
		return UNKNOWN == this;
	}

	public ValueType toValueType() {
		return valueType;
	}

	public ValueType toSingleValueType() {
		return valueType.toSingleType();
	}

}
