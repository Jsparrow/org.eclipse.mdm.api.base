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
import org.eclipse.mdm.api.base.model.Core;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.Value;

public final class DefaultCore implements Core {

	private final EntityStore permanentEntityStorage = new EntityStore();
	private final EntityStore mutableEntityStorage = new EntityStore();
	private final ChildrenStore childrenStore = new ChildrenStore();

	private final Map<String, Value> values = new HashMap<>();
	private final Map<String, Value> hiddenValues = new HashMap<>();

	private final String sourceName;
	private final String typeName;

	private Long instanceID;
	private URI uri;

	public DefaultCore(Record record) {
		setURI(record.createURI());
		setID(record.getID());
		values.putAll(record.getValues());
		values.remove(Entity.ATTR_ID);

		// remove any contained relation attributes
		EntityType entityType = record.getEntityType();
		entityType.getRelations().stream().map(Relation::getName).forEach(values::remove);

		sourceName = entityType.getSourceName();
		typeName = entityType.getName();
	}

	public DefaultCore(EntityType entityType) {
		setID(Long.valueOf(0L));
		values.putAll(entityType.createValues());
		values.remove(Entity.ATTR_ID);

		setURI(new URI(entityType.getSourceName(), entityType.getName(), 0L));

		sourceName = entityType.getSourceName();
		typeName = entityType.getName();
	}

	@Override
	public String getSourceName() {
		return sourceName;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public Long getID() {
		return instanceID;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public void setID(Long instanceID) {
		this.instanceID = instanceID;
	}

	@Override
	@Deprecated
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
