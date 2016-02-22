/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Core;
import org.eclipse.mdm.api.base.model.DataItem;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.query.EntityType;
import org.eclipse.mdm.api.base.query.Record;

public final class EntityCore implements Core {

	private final Map<Class<? extends DataItem>, List<? extends DataItem>> currentChildren = new HashMap<>();
	private final Map<Class<? extends DataItem>, List<? extends DataItem>> removedChildren = new HashMap<>();

	private final Map<Class<? extends DataItem>, DataItem> currentInfoRelations = new HashMap<>();

	private final Map<String, Value> values = new HashMap<>();

	private final String typeName;

	private URI uri;

	public EntityCore(Record record) {
		setURI(record.createURI());
		values.putAll(record.getValues());
		values.remove(DataItem.ATTR_ID);

		typeName = record.getEntityType().getName();
	}

	public EntityCore(EntityType entityType) {
		values.putAll(entityType.createValues());
		values.remove(DataItem.ATTR_ID);

		typeName = entityType.getName();
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public void setURI(URI uri) {
		if(this.uri != null) {
			throw new IllegalStateException("It is not allowed to replace the URI");
		}

		this.uri = uri;
	}

	@Override
	public Map<String, Value> getValues() {
		return values;
	}

	@Override
	public Map<Class<? extends DataItem>, DataItem> getInfoRelations() {
		return currentInfoRelations;
	}

	@Override
	public Map<Class<? extends DataItem>, List<? extends DataItem>> getChildren() {
		return currentChildren;
	}

	@Override
	public Map<Class<? extends DataItem>, List<? extends DataItem>> getRemovedChildren() {
		return removedChildren;
	}

}
