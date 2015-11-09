/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Value;

public final class Result implements Iterable<Record> {
	
	private final Map<Entity, Record> records = new HashMap<>();
	
	public void addRecord(Entity entity, Map<String, Value> values) {
		if(records.put(entity, new Record(entity, values)) != null) {
			throw new IllegalArgumentException("Record for entity '" + entity.getName() + "' is already defined.");
		}
	}
	
	public Record getRecord(Entity entity) {
		Record record = records.get(entity);
		if(record == null) {
			throw new IllegalArgumentException("Record for entity '" + entity.getName() + "' is not available.");
		}
		
		return record;
	}
	
	public Record removeRecord(Entity entity) {
		Record record = records.remove(entity);
		if(record == null) {
			throw new IllegalArgumentException("Record for entity '" + entity.getName() + "' is not available.");
		}
		
		return record;
	}

	@Override
	public Iterator<Record> iterator() {
		return records.values().iterator();
	}

}
