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

import org.eclipse.mdm.api.base.marker.Deletable;
import org.eclipse.mdm.api.base.marker.Derived;

public final class ContextRoot extends AbstractDataItem implements Deletable, Derived {
	
	private final List<ContextComponent> contextComponents = new ArrayList<ContextComponent>();
	private final List<ContextSensor> contextSensors = new ArrayList<ContextSensor>();

	private final ContextType contextType;
	
	private ContextRoot(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);		
		this.contextType = ContextType.valueOf(ContextType.class, uri.getTypeName().toUpperCase());
	}

	public ContextType geContextType() {
		return this.contextType;
	}
	
	public List<ContextComponent> getContextComponents() {
		return this.contextComponents;
	}
	
	public List<ContextSensor> getContextSensors() {
		return this.contextSensors;
	}
	
	public boolean hasSensors() {
		return contextType.areSensoresAllowed() && (!contextSensors.isEmpty());
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(contextType.name()).append('(');
		
		for(Value value : getValues().values()) {
			sb.append(value.toString()).append(',');
		}
		
		sb.append(this.contextComponents);
		if(hasSensors()) {
			sb.append(this.contextSensors);
		}		
		return sb.append(')').toString();
	}

}
