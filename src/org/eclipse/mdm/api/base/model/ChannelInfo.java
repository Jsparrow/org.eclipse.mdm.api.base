/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

public final class ChannelInfo extends AbstractDataItem {

	public static final String ATTR_INDEPENDENT = "IndependentFlag";
	public static final String ATTR_SEQUENCE_REPRESENTATION = "SequenceRepresentation";
	public static final String ATTR_AXISTYPE = "axistype"; // TODO attribute on other (newer systems) 'AxisType' 

	
	private ChannelInfo(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	public Boolean isIndependent() {
		return super.getValue(ATTR_INDEPENDENT).getValue();
	}
	
	//TODO: mapping enum values
	public Integer getAxisType() {
		return super.getValue(ATTR_AXISTYPE).getValue();
	}
	
	//TODO: mapping enum values
	public Integer getSequenceRepresentation() {
		return super.getValue(ATTR_SEQUENCE_REPRESENTATION).getValue();
	}
	
} 
