/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

public final class Value {

	private final String name;	
	private final ValueType valueType;
	private final String unit;
	private boolean valid = true;
	private Object value;

	Value(String name, ValueType valueType, String unit) {	
		this.name = name;
		this.valueType = valueType;
		this.unit = unit;
	}
		
	public String getName() {
		return this.name;
	}
	
	public ValueType getValueType() {
		return this.valueType;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public boolean isValid() {
		return this.valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T)value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(name).append(" = ").append(value).toString();
	}
}
