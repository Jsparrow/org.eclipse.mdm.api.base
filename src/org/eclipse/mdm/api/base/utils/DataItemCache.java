/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.model.DataItem;
import org.eclipse.mdm.api.base.query.Entity;

public final class DataItemCache {
	
	private final Map<Entity, Map<Long, DataItem>> dataItemsByEntity = new HashMap<>();
	
	public boolean isCached(Entity entity) {
		return dataItemsByEntity.containsKey(entity);
	}
	
	public <T extends DataItem> void cache(Entity entity, List<T> dataItems) {
		Map<Long, DataItem> cachedMap = this.dataItemsByEntity.get(entity);
		if(cachedMap == null) {
			cachedMap = new HashMap<Long, DataItem>();
			this.dataItemsByEntity.put(entity, cachedMap);
		} else {
			cachedMap.clear();
		}
		
		for(DataItem dataItem : dataItems) {
			cachedMap.put(dataItem.getURI().getId(), dataItem);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends DataItem> List<T> getAllCached(Entity entity) {
		Map<Long, DataItem> cachedMap = this.dataItemsByEntity.get(entity);
		if(cachedMap == null) {
			throw new IllegalArgumentException("no DataItem cache exist for entity '" + entity.getName() + "'");
		}
		
		return (List<T>) new ArrayList<>(cachedMap.values());
	}

	@SuppressWarnings("unchecked")
	public <T extends DataItem> T getCached(Entity entity, Long id) {
		Map<Long, DataItem> cachedMap = this.dataItemsByEntity.get(entity);
		if(cachedMap == null) {
			throw new IllegalArgumentException("no DataItem cache exist for entity '" + entity.getName() + "'");
		}
		DataItem cachedDataItem = cachedMap.get(id);
		if(cachedDataItem == null) {
			throw new IllegalArgumentException("no DataItem with id '" + id + "' found at entity '" + entity.getName() + "' cache!");
		}
		return (T)cachedDataItem;
	}
	
	public void clear() {
		for(Map<Long, DataItem> c : dataItemsByEntity.values()) {
			c.clear();
		}
		
		dataItemsByEntity.clear();
	}
	
}
