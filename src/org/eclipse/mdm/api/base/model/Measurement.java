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

import org.eclipse.mdm.api.base.marker.ContextDescribable;
import org.eclipse.mdm.api.base.marker.Derived;
import org.eclipse.mdm.api.base.marker.Parameterizable;
import org.eclipse.mdm.api.base.marker.Statable;
import org.eclipse.mdm.api.base.marker.Tagable;

public final class Measurement extends AbstractDataItem implements ContextDescribable, Describable, Datable, FilesAttachable, 
	Parameterizable, Statable, Tagable, Derived {
	
	public static final Class<ChannelGroup> CHILD_TYPE_CHANNELGROUP = ChannelGroup.class;
	public static final Class<Channel> CHILD_TYPE_CHANNEL = Channel.class;
	
	public static final String ATTR_MEASUREMENT_BEGIN = "MeasurementBegin";
	public static final String ATTR_MEASUREMENT_END = "MeasurementEnd";

	private Measurement(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}		

	@Override
	public Date getDateCreated() {
		return super.getValue(ATTR_DATECREATED).getValue();
	}

	@Override
	public void setDateCreated(Date date) {
		super.getValue(ATTR_DATECREATED).setValue(date);
	}

	@Override
	public String getDescription() {
		return super.getValue(ATTR_DESCRIPTION).getValue();
	}

	@Override
	public void setDescription(String description) {
		super.getValue(ATTR_DESCRIPTION).setValue(description);
	}
	
	public Date getMeasurementBegin() {
		return super.getValue(ATTR_MEASUREMENT_BEGIN).getValue();
	}
	
	public void setMeasurementBegin(Date measurementBegin) {
		super.getValue(ATTR_MEASUREMENT_BEGIN).setValue(measurementBegin);
	}
	
	public Date getMeasurementEnd() {
		return super.getValue(ATTR_MEASUREMENT_END).getValue();
	}
	
	public void setMeasurementEnd(Date measurementEnd) {
		super.getValue(ATTR_MEASUREMENT_END).setValue(measurementEnd);
	}
	
}
