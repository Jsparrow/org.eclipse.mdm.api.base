/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class ParameterSet extends AbstractDataItem {

	public static final String ATTR_VERSION = "Version";
	
	private final List<Parameter> parameterList = new ArrayList<Parameter>();
	
	private ParameterSet(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	public List<Parameter> getParameters() {
		return this.parameterList;
	}
	
	public String getVersion() {
		return super.getValue(ATTR_VERSION).getValue();
	}
	
}
