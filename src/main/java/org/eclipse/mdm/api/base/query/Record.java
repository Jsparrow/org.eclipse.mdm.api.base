/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.mdm.api.base.model.Value;

/**
 * A record corresponds to an instance of the underlying {@link EntityType}. It
 * contains a subset of its {@link Value}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Attribute
 */
public final class Record {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Map<String, Value> values = new HashMap<>();
	private final EntityType entityType;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param entityType
	 *            The {@link EntityType} this record is associated with.
	 */
	public Record(EntityType entityType) {
		this.entityType = entityType;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the associated {@link EntityType}.
	 *
	 * @return The associated {@code EntityType} is returned.
	 */
	public EntityType getEntityType() {
		return entityType;
	}

	/**
	 * Returns all contained {@link Value}s mapped by their names.
	 *
	 * @return Available {@code Value}s in a mutable {@code Map} are returned.
	 */
	public Map<String, Value> getValues() {
		return values;
	}

	/**
	 * Returns the record ID, which is the ID of the instance, represented by
	 * this record.
	 *
	 * @return Returns the ID of this record.
	 * @throws IllegalStateException
	 *             Thrown if the ID {@code Value} container is not available.
	 */
	public String getID() {
		Value idValue = getValues().get(getEntityType().getIDAttribute().getName());
		if (idValue == null) {
			throw new IllegalStateException("ID attribute was not selected.");
		}

		return idValue.extract();
	}

	/**
	 * Returns the instance ID of the related entity referenced by the given
	 * {@link Relation}.
	 *
	 * @param relation
	 *            The foreign key {@code Relation}.
	 * @return {@code Optional} is empty if there is no related entity.
	 * @throws IllegalStateException
	 *             Thrown if the requested foreign key was not selected prior
	 *             executing the query.
	 */
	public Optional<String> getID(Relation relation) {
		Value idValue = getValues().get(relation.getName());
		if (idValue == null) {
			throw new IllegalStateException("Relation attribute '" + relation + "' was not selected.");
		}

		return Optional.ofNullable(idValue.isValid() ? idValue.extract() : null);
	}

	/**
	 * Adds given {@link Value} to this record.
	 *
	 * @param value
	 *            {@link Value} that will be added.
	 * @throws IllegalArgumentException
	 *             Thrown if given {@code Value} overwrites an existing one.
	 */
	public void addValue(Value value) {
		if (values.put(value.getName(), value) != null) {
			throw new IllegalArgumentException("Value with name '" + value.getName() + "' for entity type '"
					+ entityType + "' record is already defined.");
		}
	}

	/**
	 * Returns a human readable {@code String} representation of this record.
	 *
	 * @return The {@code String} representation of this record.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Record(EntityType = ").append(getEntityType());
		return sb.append(", Values = ").append(getValues().values()).append(')').toString();
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Merges given record with this instance. To be able to do so, the given
	 * record must be compatible with this record. Records are compatible if the
	 * underlying {@link EntityType} and the subset of {@link Value}s is the
	 * same.
	 *
	 * @param record
	 *            The record that will be merged with this instance.
	 * @return A new record with merged {@code Value}s is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if given record is not compatible.
	 */
	Record merge(Record record) {
		boolean entityTypeMissmatch = !getEntityType().equals(record.getEntityType());
		boolean valuesMissmatch = getValues().size() != record.getValues().size();
		if (entityTypeMissmatch || valuesMissmatch) {
			throw new IllegalArgumentException("Unable to merge, incompatible record passed.");
		}

		Record mergedRecord = new Record(getEntityType());
		getValues().keySet().stream().forEach(n -> mergedRecord.addValue(getValue(n).merge(record.getValue(n))));
		return mergedRecord;
	}

	/**
	 * Returns {@link Value} with given name.
	 *
	 * @param name
	 *            Used as identifier.
	 * @return {@code Value} with given name is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if attribute with given name is not contained.
	 */
	Value getValue(String name) {
		Value value = getValues().get(name);
		if (value == null) {
			throw new IllegalArgumentException("Value with name '" + name + "' not found.");
		}

		return value;
	}

}
