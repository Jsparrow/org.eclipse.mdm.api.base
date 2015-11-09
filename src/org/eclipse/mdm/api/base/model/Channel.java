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
import org.eclipse.mdm.api.base.marker.Parameterizable;

public final class Channel extends AbstractDataItem implements Describable, Parameterizable, Deletable {

	public static final String ATTR_VALUETYPE = "DataType";
	
	private final Statistics statistics;

	private Channel(Map<String, Value> values, URI uri,	Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
		this.statistics = new Statistics(values);			
	}

	@Override
	public String getDescription() {
		return super.getValue(ATTR_DESCRIPTION).getValue();
	}

	@Override
	public void setDescription(String description) {
		super.getValue(ATTR_DESCRIPTION).setValue(description);
	}
	
	//TODO: mapping enum values
	public Integer getValueTypeEnum() {
		return super.getValue(ATTR_VALUETYPE).getValue();
	}
	

	public Unit getUnit() {
		return (Unit)super.references.get(Unit.class);
	}
	
	public Quantity getQuantity() {
		return (Quantity)super.references.get(Quantity.class);
	}	
	
	public ChannelInfo getChannelDataInfo() {
		return (ChannelInfo)super.references.get(ChannelInfo.class);
	}
	
	public Statistics getStatistics() {
		return this.statistics;
	}
}
