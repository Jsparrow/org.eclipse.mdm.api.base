/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.EntityCore;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.Value;

public final class DefaultEntityCore implements EntityCore {

	private final EntityStore permanentEntityStorage = new EntityStore();
	private final EntityStore mutableEntityStorage = new EntityStore();
	private final ChildrenStore childrenStore = new ChildrenStore();

	private final Map<String, Value> values = new HashMap<>();
	private final Map<String, Value> hiddenValues = new HashMap<>();

	private URI uri;

	public DefaultEntityCore(Record record) {
		setURI(record.createURI());
		values.putAll(record.getValues());
		values.remove(Entity.ATTR_ID);

		// remove any contained relation attributes
		record.getEntityType().getRelations().stream().map(Relation::getName).forEach(values::remove);
	}

	public DefaultEntityCore(EntityType entityType) {
		values.putAll(entityType.createValues());
		values.remove(Entity.ATTR_ID);

		setURI(new URI(entityType.getSourceName(), entityType.getName(), 0L));
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public void setURI(URI uri) {
		this.uri = uri;
	}

	@Override
	public Map<String, Value> getValues() {
		return values;
	}

	@Override
	public void hideValues(Collection<String> names) {
		if(names.isEmpty()) {
			return;
		}

		for(String name : names) {
			Value value = values.remove(name);
			if(name != null) {
				hiddenValues.put(name, value);
			}
		}
	}

	@Override
	public Map<String, Value> getAllValues() {
		if(hiddenValues.isEmpty()) {
			return values;
		}

		Map<String, Value> allValues = new HashMap<>(values);
		allValues.putAll(hiddenValues);
		return allValues;
	}

	@Override
	public EntityStore getMutableStore() {
		return mutableEntityStorage;
	}

	@Override
	public EntityStore getPermanentStore() {
		return permanentEntityStorage;
	}

	@Override
	public ChildrenStore getChildrenStore() {
		return childrenStore;
	}

}
