/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Core {

	String getSourceName();

	String getTypeName();

	Long getID();

	@Deprecated
	URI getURI();

	void setID(Long instanceID);

	@Deprecated
	void setURI(URI uri);

	Map<String, Value> getValues();

	void hideValues(Collection<String> names);

	Map<String, Value> getAllValues();

	default void apply() {
		// apply removed mutable entities
		getMutableStore().apply();

		// apply removed children
		getChildrenStore().apply();
	}

	// mutable mostly at any time (templates are critical!)
	EntityStore getMutableStore();

	// permanent once populated
	// - relation to parent (insert statement or navigation from child to parent)
	// - write relations to entities that are not directly available via the entity
	//   types API
	EntityStore getPermanentStore();

	// mutable().related(Unit.class);

	ChildrenStore getChildrenStore();

	public static final class EntityStore {

		private final Map<String, Entity> current = new HashMap<>(0);
		private final Map<String, Entity> removed = new HashMap<>(0);

		public Collection<Entity> getCurrent() {
			return Collections.unmodifiableCollection(current.values());
		}

		public Collection<Entity> getRemoved() {
			return Collections.unmodifiableCollection(removed.values());
		}

		public <T extends Entity> T get(Class<T> entityClass) {
			return entityClass.cast(current.get(entityClass.getSimpleName()));
		}

		public void set(Entity entity) {
			String key = entity.getClass().getSimpleName();
			Entity old = current.put(key, entity);
			if(old != null) {
				removed.put(key, old);
			}
		}

		public void remove(Class<? extends Entity> entityClass) {
			String key = entityClass.getSimpleName();
			Entity old = current.remove(key);
			if(old != null) {
				removed.put(key, old);
			}
		}

		public <T extends Entity> T get(Class<T> entityClass, ContextType contextType) {
			return entityClass.cast(current.get(createContextTypeKey(entityClass, contextType)));
		}

		public void set(Entity entity, ContextType contextType) {
			String key = createContextTypeKey(entity.getClass(), contextType);
			Entity old = current.put(key, entity);
			if(old != null) {
				removed.put(key, old);
			}
		}

		public void remove(Class<? extends Entity> entityClass, ContextType contextType) {
			String key = createContextTypeKey(entityClass, contextType);
			Entity old = current.remove(key);
			if(old != null) {
				removed.put(key, old);
			}
		}

		private void apply() {
			removed.clear();
		}

		private static String createContextTypeKey(Class<? extends Entity> entityClass, ContextType contextType) {
			return entityClass.getSimpleName() + '_' + contextType;
		}

	}

	public static final class ChildrenStore {

		private final Map<Class<? extends Deletable>, List<? extends Deletable>> current = new HashMap<>(0);
		private final Map<Class<? extends Deletable>, List<? extends Deletable>> removed = new HashMap<>(0);

		public Map<Class<? extends Deletable>, List<? extends Deletable>> getCurrent() {
			return Collections.unmodifiableMap(current);
		}

		public Map<Class<? extends Deletable>, List<? extends Deletable>> getRemoved() {
			return Collections.unmodifiableMap(removed);
		}

		@SuppressWarnings("unchecked")
		public <T extends Deletable> List<T> get(Class<T> entityClass) {
			return Collections.unmodifiableList((List<T>) current.computeIfAbsent(entityClass, k -> new ArrayList<>()));
		}

		@SuppressWarnings("unchecked")
		public <T extends Deletable> void sort(Class<T> entityClass, Comparator<? super T> comparator) {
			List<T> children = (List<T>) current.get(entityClass);
			if(children != null) {
				children.sort(comparator);
			}
		}

		@SuppressWarnings("unchecked")
		public void add(Deletable child) {
			removed.getOrDefault(child.getClass(), new ArrayList<>()).remove(child);
			((List<Deletable>) current.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
		}

		@SuppressWarnings("unchecked")
		public void remove(Deletable child) {
			List<Deletable> children = (List<Deletable>) current.getOrDefault(child.getClass(), new ArrayList<>());
			if(children.remove(child) && child.getID() > 0) {
				((List<Deletable>) removed.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
			}
		}

		private void apply() {
			removed.clear();
		}

	}

}
