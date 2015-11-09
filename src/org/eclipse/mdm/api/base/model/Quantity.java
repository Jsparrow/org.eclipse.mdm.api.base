/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Date;
import java.util.Map;

import org.eclipse.mdm.api.base.marker.Deletable;

public final class Quantity extends AbstractDataItem implements Datable, Describable, Deletable {

	public static final String ATTR_DEFAULT_VALUETYPE = "DefDataType";
	
	private Quantity(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	@Override
	public String getDescription() {
		return super.getValue(ATTR_DESCRIPTION).getValue();
	}

	@Override
	public void setDescription(String description) {
		super.getValue(ATTR_DESCRIPTION).setValue(description);
	}

	@Override
	public Date getDateCreated() {
		return super.getValue(ATTR_DATECREATED).getValue();
	}

	@Override
	public void setDateCreated(Date date) {
		super.getValue(ATTR_DATECREATED).setValue(date);		
	}	
	
	//TODO: cast integer value to enumeration Value (ValueType)
	public Integer getDefaultValueType() {
		return super.getValue(ATTR_DEFAULT_VALUETYPE).getValue();
	}
	
	//TODO: same as above
	public void setDefaultValueType(Integer enumValue) {
		super.getValue(ATTR_DEFAULT_VALUETYPE).setValue(enumValue);
	}
	
	public Unit getDefaultUnit() {
		return (Unit)super.references.get(Unit.class);
	}
	
}
