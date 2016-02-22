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

public interface Core {

	String getTypeName();

	URI getURI();

	void setURI(URI uri);

	Map<String, Value> getValues();

	Map<Class<? extends DataItem>, DataItem> getInfoRelations();

	@SuppressWarnings("unchecked")
	default <T extends DataItem> T getInfoRelation(Class<T> type) {
		return (T) getInfoRelations().get(type);
	}

	default void setInfoRelation(DataItem dataItem) {
		getInfoRelations().put(dataItem.getClass(), dataItem);
	}

	default void removeInfoRelation(Class<? extends DataItem> type) {
		getInfoRelations().remove(type);
	}

	Map<Class<? extends DataItem>, List<? extends DataItem>> getChildren();

	@SuppressWarnings("unchecked")
	default <T extends DataItem> List<T> getChildren(Class<T> type) {
		return Collections.unmodifiableList((List<T>) getChildren().computeIfAbsent(type, k -> new ArrayList<>()));
	}

	Map<Class<? extends DataItem>, List<? extends DataItem>> getRemovedChildren();

	@SuppressWarnings("unchecked")
	default void addChild(DataItem child) {
		getRemovedChildren().getOrDefault(child.getClass(), new ArrayList<>()).remove(child);
		((List<DataItem>) getChildren().computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
	}

	@SuppressWarnings("unchecked")
	default boolean removeChild(DataItem child) {
		List<DataItem> current = (List<DataItem>) getChildren().getOrDefault(child.getClass(), new ArrayList<>());
		boolean removed = current.remove(child);
		if(removed) {
			((List<DataItem>) getRemovedChildren().computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
		}

		return removed;
	}

}
