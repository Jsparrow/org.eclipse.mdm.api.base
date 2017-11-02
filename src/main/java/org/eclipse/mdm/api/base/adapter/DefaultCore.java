/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.adapter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Core;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.query.Record;

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
public class DefaultCore implements Core {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final EntityStore permanentEntityStorage = new EntityStore();
	private final EntityStore mutableEntityStorage = new EntityStore();
	private final ChildrenStore childrenStore = new ChildrenStore();

	private final Map<String, Value> values = new HashMap<>();
	private final Map<String, Value> hiddenValues = new HashMap<>();

	private final String sourceName;
	private final String typeName;

	private String instanceID;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructs a new {@link Core} initialized with a queried {@link Record}.
	 *
	 * @param record
	 *            The queried {@code Record}.
	 */
	public DefaultCore(Record record) {
		setID(record.getID());
		values.putAll(record.getValues());
		values.remove(Entity.ATTR_ID);

		// remove any contained relation attributes
		EntityType entityType = record.getEntityType();
		entityType.getRelations().stream().map(Relation::getName).forEach(values::remove);

		sourceName = entityType.getSourceName();
		typeName = entityType.getName();
	}

	/**
	 * Constructs a new empty {@link Core}. The ID is set to an empty String.
	 *
	 * @param entityType
	 *            The associated {@link EntityType}.
	 */
	public DefaultCore(EntityType entityType) {
		setID("");
		values.putAll(entityType.createValues());
		values.remove(Entity.ATTR_ID);

		sourceName = entityType.getSourceName();
		typeName = entityType.getName();
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSourceName() {
		return sourceName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getTypeName() {
		return typeName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getID() {
		return instanceID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setID(String instanceID) {
		this.instanceID = instanceID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Value> getValues() {
		return values;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void hideValues(Collection<String> names) {
		if (names.isEmpty()) {
			return;
		}

		for (String name : names) {
			Value value = values.remove(name);
			if (name != null) {
				hiddenValues.put(name, value);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Value> getAllValues() {
		if (hiddenValues.isEmpty()) {
			return values;
		}

		Map<String, Value> allValues = new HashMap<>(values);
		allValues.putAll(hiddenValues);
		return allValues;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityStore getMutableStore() {
		return mutableEntityStorage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EntityStore getPermanentStore() {
		return permanentEntityStorage;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ChildrenStore getChildrenStore() {
		return childrenStore;
	}

}
