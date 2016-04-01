/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface EntityCore {

	URI getURI();

	void setURI(URI uri);

	Map<String, Value> getValues();

	Map<Class<? extends Entity>, Entity> getInfoRelations();

	@SuppressWarnings("unchecked")
	default <T extends Entity> T getInfoRelation(Class<T> type) {
		return (T) getInfoRelations().get(type);
	}

	default void setInfoRelation(Entity entity) {
		Long id = entity.getURI().getID();
		if(id.longValue() < 1) {
			throw new IllegalArgumentException("Entity '" + entity + "' is not persisted.");
		}
		getInfoRelations().put(entity.getClass(), entity);
	}

	default void removeInfoRelation(Class<? extends Entity> type) {
		getInfoRelations().remove(type);
	}

	Map<Class<? extends Deletable>, List<? extends Deletable>> getChildren();

	@SuppressWarnings("unchecked")
	default <T extends Deletable> List<T> getChildren(Class<T> type) {
		return Collections.unmodifiableList((List<T>) getChildren().computeIfAbsent(type, k -> new ArrayList<>()));
	}

	Map<Class<? extends Deletable>, List<? extends Deletable>> getRemovedChildren();

	@SuppressWarnings("unchecked")
	default void addChild(Deletable child) {
		getRemovedChildren().getOrDefault(child.getClass(), new ArrayList<>()).remove(child);
		((List<Deletable>) getChildren().computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
	}

	@SuppressWarnings("unchecked")
	default void removeChild(Deletable child) {
		List<Deletable> current = (List<Deletable>) getChildren().getOrDefault(child.getClass(), new ArrayList<>());
		if(current.remove(child)) {
			((List<Deletable>) getRemovedChildren().computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
		}
	}

	Map<String, Entity> getImplicitRelations();

	default void setImplicitRelation(Entity entity, boolean checkPersistance) {
		Long id = entity.getURI().getID();
		if(id.longValue() < 1 && checkPersistance) {
			throw new IllegalArgumentException("Entity '" + entity + "' is not persisted.");
		}
		getImplicitRelations().put(entity.getURI().getTypeName(), entity);
	}

}
