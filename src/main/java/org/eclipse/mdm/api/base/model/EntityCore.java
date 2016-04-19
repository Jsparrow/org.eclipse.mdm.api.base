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

public interface EntityCore {

	URI getURI();

	void setURI(URI uri);

	Map<String, Value> getValues();

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

	ChildrenStore getChildrenStore();

	// TODO: persistence checks should be completely removed here
	// before an entity is created or updated, all of it's related
	// entities - mandatory or not - have to be checked to ensure
	// that each of them have are already persistent
	public static final class EntityStore {

		private final Map<String, Entity> current = new HashMap<>(0);
		private final Map<String, Entity> removed = new HashMap<>(0);

		public Collection<Entity> getCurrent() {
			return Collections.unmodifiableCollection(current.values());
		}

		public Collection<Entity> getRemoved() {
			return Collections.unmodifiableCollection(removed.values());
		}

		@SuppressWarnings("unchecked")
		public <T extends Entity> T get(Class<T> type) {
			return (T) current.get(type.getSimpleName());
		}

		public void set(Entity entity) {
			Long id = entity.getURI().getID();
			if(id.longValue() < 1) {
				throw new IllegalArgumentException("Entity '" + entity + "' is not persisted.");
			}

			String key = entity.getClass().getSimpleName();
			Entity old = current.put(key, entity);
			if(old != null) {
				removed.put(key, old);
			}
		}

		@Deprecated // this should not be required!
		public void setParent(Entity entity, boolean checkPersistence) {
			Long id = entity.getURI().getID();
			if(id.longValue() < 1 && checkPersistence) {
				throw new IllegalArgumentException("Entity '" + entity + "' is not persisted.");
			}

			String key = entity.getClass().getSimpleName();
			Entity old = current.put(key, entity);
			if(old != null) {
				removed.put(key, old);
			}
		}

		public void remove(Class<? extends Entity> type) {
			String key = type.getSimpleName();
			Entity old = current.remove(key);
			if(old != null) {
				removed.put(key, old);
			}
		}

		@SuppressWarnings("unchecked")
		public <T extends Entity> T get(Class<T> type, ContextType contextType) {
			return (T) current.get(createContextTypeKey(type, contextType));
		}

		public void set(Entity entity, ContextType contextType) {
			Long id = entity.getURI().getID();
			if(id.longValue() < 1) {
				throw new IllegalArgumentException("Entity '" + entity + "' is not persisted.");
			}

			String key = createContextTypeKey(entity.getClass(), contextType);
			Entity old = current.put(key, entity);
			if(old != null) {
				removed.put(key, old);
			}
		}

		public void remove(Class<? extends Entity> type, ContextType contextType) {
			String key = createContextTypeKey(type, contextType);
			Entity old = current.remove(key);
			if(old != null) {
				removed.put(key, old);
			}
		}

		private void apply() {
			removed.clear();
		}

		private static String createContextTypeKey(Class<? extends Entity> type, ContextType contextType) {
			return type.getSimpleName() + '_' + contextType;
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
		public <T extends Deletable> List<T> get(Class<T> type) {
			return Collections.unmodifiableList((List<T>) current.computeIfAbsent(type, k -> new ArrayList<>()));
		}

		@SuppressWarnings("unchecked")
		public <T extends Deletable & Sortable<T>> void sort(Class<T> type) {
			List<T> children = (List<T>) current.get(type);
			if(children != null) {
				Collections.sort(children);
			}
		}

		public <T extends Deletable & Sortable<T>> void sort(Class<T> type, Comparator<T> comparator) {
			@SuppressWarnings("unchecked")
			List<T> children = (List<T>) current.get(type);
			if(children != null) {
				Collections.sort(children, comparator);
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
			if(children.remove(child) && child.getURI().getID() > 0) {
				((List<Deletable>) removed.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
			}
		}

		private void apply() {
			removed.clear();
		}

	}

}
