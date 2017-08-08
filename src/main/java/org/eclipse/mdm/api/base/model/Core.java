/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Provides access to the internals of any entity:
 *
 * <ul>
 * <li>name of the data source</li>
 * <li>name of the type</li>
 * <li>instance ID</li>
 * <li>values</li>
 * <li>added/removed file links</li>
 * <li>related entities</li>
 * <li>parent/child entities</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public interface Core {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the name of the data source this entity was retrieved from.
	 *
	 * @return Name of the data source.
	 */
	String getSourceName();

	/**
	 * Returns the name of the entity type.
	 *
	 * @return Name of the entity type is returned.
	 */
	String getTypeName();

	/**
	 * Returns the instance ID or {@code 0} if this instance is not yet
	 * persisted.
	 *
	 * @return The instance ID is returned.
	 */
	String getID();

	/**
	 * Sets an instance ID.
	 *
	 * @param instanceID
	 *            The new instance ID.
	 */
	void setID(String instanceID);

	/**
	 * Returns <i>all</i> {@link Value} containers of this entity.
	 *
	 * @return Values mapped by their name are returned.
	 */
	Map<String, Value> getValues();

	/**
	 * Hides {@link Value} containers whose name is contained in the given names
	 * {@code Collection}.
	 *
	 * @param names
	 *            Names of the {@code Value} which shall be hidden.
	 */
	void hideValues(Collection<String> names);

	/**
	 * Returns <i>all</i> {@link Value} containers, including the hidden ones.
	 *
	 * @return All {@code Value} containers are returned.
	 */
	Map<String, Value> getAllValues();

	/**
	 * Returns all newly added {@link FileLink}.
	 *
	 * @return New {@code FileLink}s are returned.
	 */
	default List<FileLink> getAddedFileLinks() {
		Predicate<FileLink> isRemote = FileLink::isRemote;

		List<FileLink> fileLinks = getValues().values().stream().filter(v -> v.getValueType().isFileLink())
				.filter(Value::isValid).map(v -> (FileLink) v.extract()).filter(isRemote.negate())
				.collect(Collectors.toList());

		List<Value> values = getValues().values().stream().filter(v -> v.getValueType().isFileLinkSequence())
				.filter(Value::isValid).collect(Collectors.toList());

		for (Value value : values) {
			Arrays.stream((FileLink[]) value.extract()).filter(isRemote.negate()).forEach(fileLinks::add);
		}

		return fileLinks;
	}

	/**
	 * Returns all removed {@link FileLink}s.
	 *
	 * @return Removed {@code FileLink}s are returned.
	 */
	default List<FileLink> getRemovedFileLinks() {
		Predicate<FileLink> isRemote = FileLink::isRemote;

		List<FileLink> fileLinks = getValues().values().stream().filter(v -> v.getValueType().isFileLink())
				.filter(Value::isModified).map(v -> (FileLink) v.extractInitial()).filter(Objects::nonNull)
				.filter(isRemote).collect(Collectors.toList());

		List<Value> values = getValues().values().stream().filter(v -> v.getValueType().isFileLinkSequence())
				.filter(Value::wasValid).filter(Value::isModified).collect(Collectors.toList());

		for (Value value : values) {
			List<FileLink> current = Arrays.asList((FileLink[]) value.extract());
			Arrays.stream((FileLink[]) value.extractInitial()).filter(fl -> !current.contains(fl))
					.forEach(fileLinks::add);
		}

		return fileLinks;
	}

	/**
	 * Applies modifications made to the entity stores and {@link Value}
	 * containers.
	 */
	default void apply() {
		// apply removed mutable entities
		getMutableStore().apply();

		// apply removed children
		getChildrenStore().apply();

		// apply modified values
		getValues().values().stream().filter(Value::isModified).forEach(Value::apply);
	}

	/**
	 * Returns the mutable {@link EntityStore}. This store holds related
	 * entities of any kind.
	 *
	 * @return The mutable {@code EntityStore} is returned.
	 */
	EntityStore getMutableStore();

	/**
	 * Returns the permanent {@link EntityStore}. This store holds usually only
	 * the related parent entity.
	 *
	 * @return The permanent {@code EntityStore} is returned.
	 */
	EntityStore getPermanentStore();

	/**
	 * Returns the {@link ChildrenStore}. This store holds related child
	 * entities of any kind.
	 *
	 * @return The {@code ChildrenStore} is returned.
	 */
	ChildrenStore getChildrenStore();

	// ======================================================================
	// Inner classes
	// ======================================================================

	/**
	 * Holds related entities of any kind and keeps track of modifications.
	 */
	public static final class EntityStore {

		// ======================================================================
		// Instance variables
		// ======================================================================

		private final Map<String, Entity> current = new HashMap<>(0);
		private final Map<String, Entity> removed = new HashMap<>(0);

		// ======================================================================
		// Public methods
		// ======================================================================

		/**
		 * Returns current set of related entities.
		 *
		 * @return Returned {@code Collection} is unmodifiable.
		 */
		public Collection<Entity> getCurrent() {
			return Collections.unmodifiableCollection(current.values());
		}

		/**
		 * Returns current set of removed related entities.
		 *
		 * @return Returned {@code Collection} is unmodifiable.
		 */
		public Collection<Entity> getRemoved() {
			return Collections.unmodifiableCollection(removed.values());
		}

		/**
		 * Returns related entity identified by given entity class.
		 *
		 * @param <T>
		 *            The desired entity type.
		 * @param entityClass
		 *            Used as identifier.
		 * @return The related entity is returned or null of not defined.
		 */
		public <T extends Entity> T get(Class<T> entityClass) {
			return get(entityClass.getSimpleName(), entityClass);
		}

		/**
		 * Returns related entity identified by given entity class.
		 *
		 * @param <T>
		 *            The desired entity type.
		 * @param relationName
		 *            The relation name the entity is referenced by.
		 * @param entityClass
		 *            Used as identifier.
		 * @return The related entity is returned or null of not defined.
		 */
		public <T extends Entity> T get(String relationName, Class<T> entityClass) {
			return entityClass.cast(current.get(relationName));
		}

		/**
		 * Replaces a related entity with the given one.
		 *
		 * @param entity
		 *            The new related entity.
		 */
		public void set(Entity entity) {
			set(entity.getClass().getSimpleName(), entity);
		}

		/**
		 * Replaces a related entity with the given one.
		 *
		 * @param name
		 *            The name of the relation the entity is referenced by.
		 * @param entity
		 *            The new related entity.
		 */
		public void set(String name, Entity entity) {
			Entity old = current.put(name, entity);
			if (old != null) {
				removed.put(name, old);
			}
		}

		/**
		 * Removes a related entity for given entity class.
		 *
		 * @param entityClass
		 *            Used as identifier.
		 */
		public void remove(Class<? extends Entity> entityClass) {
			String key = entityClass.getSimpleName();
			remove(key, entityClass);
		}

		/**
		 * Removes a related entity for given relation name and entity class.
		 *
		 * @param name
		 *            The name of the relation the entity is referenced by.
		 * @param entityClass
		 *            Used as identifier.
		 */
		public void remove(String name, Class<? extends Entity> entityClass) {
			Entity old = current.remove(name);
			if (old != null) {
				removed.put(name, old);
			}
		}

		/**
		 * Returns related entity identified by given entity class and
		 * {@link ContextType}.
		 *
		 * @param <T>
		 *            The desired entity type.
		 * @param entityClass
		 *            Used as identifier.
		 * @param contextType
		 *            Used as identifier.
		 * @return The related entity is returned or null of not defined.
		 */
		public <T extends Entity> T get(Class<T> entityClass, ContextType contextType) {
			return entityClass.cast(current.get(createContextTypeKey(entityClass, contextType)));
		}

		/**
		 * Replaces a related entity with the given one.
		 *
		 * @param entity
		 *            The new related entity.
		 * @param contextType
		 *            Used as identifier.
		 */
		public void set(Entity entity, ContextType contextType) {
			String key = createContextTypeKey(entity.getClass(), contextType);
			Entity old = current.put(key, entity);
			if (old != null) {
				removed.put(key, old);
			}
		}

		/**
		 * Removes a related entity for given entity class and
		 * {@link ContextType}.
		 *
		 * @param entityClass
		 *            Used as identifier.
		 * @param contextType
		 *            Used as identifier.
		 */
		public void remove(Class<? extends Entity> entityClass, ContextType contextType) {
			String key = createContextTypeKey(entityClass, contextType);
			Entity old = current.remove(key);
			if (old != null) {
				removed.put(key, old);
			}
		}

		// ======================================================================
		// Private methods
		// ======================================================================

		/**
		 * Drops removed entities.
		 */
		private void apply() {
			removed.clear();
		}

		/**
		 * Generates a key from given entity class and {@link ContextType}.
		 *
		 * @param entityClass
		 *            Identifier part 1.
		 * @param contextType
		 *            Identifier part 2.
		 * @return A context type dependent key is returned.
		 */
		private static String createContextTypeKey(Class<? extends Entity> entityClass, ContextType contextType) {
			return entityClass.getSimpleName() + '_' + contextType;
		}

	}

	/**
	 * Holds related entities of any kind and keeps track of modifications.
	 */
	public static final class ChildrenStore {

		// ======================================================================
		// Instance variables
		// ======================================================================

		private final Map<Class<? extends Deletable>, List<? extends Deletable>> current = new HashMap<>(0);
		private final Map<Class<? extends Deletable>, List<? extends Deletable>> removed = new HashMap<>(0);

		// ======================================================================
		// Public methods
		// ======================================================================

		/**
		 * Returns current set of related children mapped by their type.
		 *
		 * @return Returned {@code Map} is unmodifiable.
		 */
		public Map<Class<? extends Deletable>, List<? extends Deletable>> getCurrent() {
			return Collections.unmodifiableMap(current);
		}

		/**
		 * Returns current set of removed related children mapped by their type.
		 *
		 * @return Returned {@code Map} is unmodifiable.
		 */
		public Map<Class<? extends Deletable>, List<? extends Deletable>> getRemoved() {
			return Collections.unmodifiableMap(removed);
		}

		/**
		 * Returns related child entities of given type.
		 *
		 * @param <T>
		 *            Desired entity type.
		 * @param entityClass
		 *            Used as identifier.
		 * @return Returned {@code List} is unmodifiable.
		 */
		@SuppressWarnings("unchecked")
		public <T extends Deletable> List<T> get(Class<T> entityClass) {
			return Collections.unmodifiableList((List<T>) current.computeIfAbsent(entityClass, k -> new ArrayList<>()));
		}

		/**
		 * Sorts the child entities with given {@code Comparator}.
		 *
		 * @param <T>
		 *            Desired entity type.
		 * @param entityClass
		 *            Used as identifier.
		 * @param comparator
		 *            Used for sorting.
		 */
		@SuppressWarnings("unchecked")
		public <T extends Deletable> void sort(Class<T> entityClass, Comparator<? super T> comparator) {
			List<T> children = (List<T>) current.get(entityClass);
			if (children != null) {
				children.sort(comparator);
			}
		}

		/**
		 * Adds given child entity.
		 *
		 * @param child
		 *            The new child.
		 */
		@SuppressWarnings("unchecked")
		public void add(Deletable child) {
			removed.getOrDefault(child.getClass(), new ArrayList<>()).remove(child);
			((List<Deletable>) current.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
		}

		/**
		 * Removes given child entity.
		 *
		 * @param child
		 *            The child which will be removed.
		 */
		@SuppressWarnings("unchecked")
		public void remove(Deletable child) {
			List<Deletable> children = (List<Deletable>) current.getOrDefault(child.getClass(), new ArrayList<>());
			if (children.remove(child) && child.getID() != null && child.getID().length() > 0) {
				((List<Deletable>) removed.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
			}
		}

		// ======================================================================
		// Private methods
		// ======================================================================

		/**
		 * Drops removed children.
		 */
		private void apply() {
			removed.clear();
		}

	}

}
