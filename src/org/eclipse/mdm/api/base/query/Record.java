/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.Map;

import org.eclipse.mdm.api.base.model.Value;

public final class Record {

	private final Map<String, Value> values;
	private final Entity entity;
	
	public Record(Entity entity, Map<String, Value> values) {
		this.entity = entity;
		this.values = values;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public Map<String, Value> getValues() {
		return values;
	}
	
	public Long getID() {
		Long id = getValues().get(getEntity().getIDAttribute().getName()).getValue();
		if(id == null) {
			throw new IllegalStateException("ID attribute was not selected.");
		}
		
		return id;
	}
	
}
