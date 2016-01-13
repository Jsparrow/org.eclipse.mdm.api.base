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

import org.eclipse.mdm.api.base.model.Value;

/**
 * A record corresponds an instance of the underlying {@link Entity} type. It
 * contains a subset of the its {@link Value}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 * @see Attribute
 * @see Value
 */
public final class Record {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private Map<String, Value> values = new HashMap<>();
	private final Entity entity;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param entity The {@link Entity} this record is associated with.
	 */
	public Record(Entity entity) {
		this.entity = entity;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the associated {@link Entity}.
	 *
	 * @return The associated {@code Entity} is returned.
	 */
	public Entity getEntity() {
		return entity;
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
	 * @throws IllegalStateException Thrown if the ID {@code Value} container
	 * 		is not available.
	 */
	public Long getID() {
		Value idValue = getValues().get(getEntity().getIDAttribute().getName());
		if(idValue == null) {
			throw new IllegalStateException("ID attribute was not selected.");
		}

		return idValue.extract();
	}

	/**
	 * Adds passed {@link Value} to this record.
	 *
	 * @param value {@link Value} that will be added.
	 * @throws IllegalArgumentException Thrown if passed {@code Value}
	 * 		overwrites an existing one.
	 */
	public void addValue(Value value) {
		if(values.put(value.getName(), value) != null) {
			throw new IllegalArgumentException("Value with name '" + value.getName() + "' for entity record '"
					+ entity + "' is already defined.");
		}
	}

	/**
	 * Returns a human readable {@code String} representation of this record.
	 *
	 * @return The {@code String} representation of this record.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Record(entity = ").append(getEntity());
		return sb.append(", values = ").append(getValues().values()).append(')').toString();
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Merges passed record with this instance. To be able to do so, the passed
	 * record must be compatible with this record. Records are compatible if
	 * the underlying {@link Entity} and the subset of {@link Value}s is the
	 * same.
	 *
	 * @param record The record that will be merged with this instance.
	 * @return A new record with merged {@code Value}s is returned.
	 * @throws IllegalArgumentException Thrown if passed record is not compatible.
	 */
	Record merge(Record record) {
		boolean entityMissmatch = !getEntity().equals(record.getEntity());
		boolean valuesMissmatch = getValues().size() != record.getValues().size();
		if(entityMissmatch || valuesMissmatch) {
			throw new IllegalArgumentException("Unable to merge, incompatible record passed.");
		}

		Record mergedRecord = new Record(getEntity());
		getValues().keySet().stream().forEach(n -> mergedRecord.addValue(getValue(n).merge(record.getValue(n))));
		return mergedRecord;
	}

	/**
	 * Returns {@link Value} with passed name.
	 *
	 * @param name Used as identifier.
	 * @return {@code Value} with passed name is returned.
	 * @throws IllegalArgumentException Thrown if attribute with passed name is
	 * 		not contained.
	 */
	Value getValue(String name) {
		Value value = getValues().get(name);
		if(value == null) {
			throw new IllegalArgumentException("Value with name '" + name + "' not found.");
		}

		return value;
	}

}
