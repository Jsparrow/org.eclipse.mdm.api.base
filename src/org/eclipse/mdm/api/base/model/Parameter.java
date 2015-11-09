/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

import org.eclipse.mdm.api.base.marker.Deletable;

final class Parameter extends AbstractDataItem implements Deletable {

	public static final String ATTR_VALUE = "Value";
	public static final String ATTR_DATA_TYPE = "DataType";
	
	private Parameter(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}
	
	public String getParameterValue() {
		return super.getValue(ATTR_VALUE).getValue();
	}
	
	public void setParameterValue(String value) {
		super.getValue(ATTR_VALUE).setValue(value);
	}
	
	public Integer getValueTypeEnum() {
		return super.getValue(ATTR_DATA_TYPE).getValue();
	}

	public void setValueTypeEnum(Integer valueType) {
		super.getValue(ATTR_DATA_TYPE).setValue(valueType);
	}

}
