/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Date;

public enum ValueType {

	UNKNOWN(Object.class),
	STRING(String.class), STRING_SEQUENCE(String[].class),
	DATE(Date.class), DATE_SEQUENCE(Date[].class),
	BOOLEAN(Boolean.class), BOOLEAN_SEQUENCE(boolean[].class),
	BYTE(Byte.class), BYTE_SEQUENCE(byte[].class),
	SHORT(Short.class), SHORT_SEQUENCE(short[].class),
	INTEGER(Integer.class), INTEGER_SEQUENCE(int[].class),
	LONG(Long.class), LONG_SEQUENCE(long[].class),
	FLOAT(Float.class), FLOAT_SEQUENCE(float[].class),
	DOUBLE(Double.class), DOUBLE_SEQUENCE(double[].class),
	BYTE_STREAM(byte[].class), BYTE_STREAM_SEQUENCE(byte[][].class),
	COMPLEX(Object.class), COMPLEX_SEQUENCE(Object.class),
	DCOMPLEX(Object.class), DCOMPLEX_SEQUENCE(Object.class),
	ENUM(Object.class), ENUM_SEQUENCE(Object.class),
	FILE_LINK(Object.class), FILE_LINK_SEQUENCE(Object.class),
	BLOB(Object.class);
	
	public final Class<?> type;
	
	private ValueType(Class<?> type) {
		this.type = type;
	}
	
    public Value newValue(String name, Object value, String unit, boolean validFlag) {     
        Value newValue = new Value(name, this, unit);
        newValue.setValue(value);
        newValue.setValid(validFlag);
        return newValue;
    }
    
    public Value newValue(String name, Object value) {
    	return newValue(name, value, "");
    }
    
    public Value newValue(String name, Object value, String unit) {     
        Value newValue = new Value(name, this, unit);
        newValue.setValue(value);
        newValue.setValid(true);
        return newValue;
    }
    
    public boolean isSequence() {
    	return name().endsWith("SEQUENCE");
    }
    
}
