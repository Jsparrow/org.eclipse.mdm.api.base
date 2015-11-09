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
import org.eclipse.mdm.api.base.marker.Derived;

public final class ChannelGroup extends AbstractDataItem implements Deletable, Derived {

	public static final Class<Channel> CHILD_TYPE_CHANNEL = Channel.class;
	
	public static String ATTR_NUMBER_OF_VALUES = "SubMatrixNoRows";
	
	private ChannelGroup(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}
	
	public Integer getNumberOfValues() {
		return super.getValue(ATTR_NUMBER_OF_VALUES).getValue();
	}

}
