/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/

package org.eclipse.mdm.api.base.adapter;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.Entity;

/**
 * Holds related entities of any kind and keeps track of modifications.
 */
public final class EntityStore {

	private final Map<String, Entity> current = new HashMap<>(0);
	private final Map<String, Entity> removed = new HashMap<>(0);

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

	/**
	 * Clean up list of removed entities.
	 */
	void apply() {
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