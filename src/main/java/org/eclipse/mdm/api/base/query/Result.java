/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.mdm.api.base.model.Value;

/**
 * A result consists of one or more {@link Record}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class Result implements Iterable<Record> {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Map<EntityType, Record> records = new HashMap<>();

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Adds passed {@link Record} to this result.
	 *
	 * @param record {@code Record} that will be added.
	 * @throws IllegalArgumentException Thrown if passed {@code Record}
	 * 		overwrites an existing one.
	 */
	public void addRecord(Record record) {
		if(records.put(record.getEntityType(), record) != null) {
			throw new IllegalArgumentException("Record for entity type '"
					+ record.getEntityType() + "' is already defined.");
		}
	}

	/**
	 * Returns the {@link Record} associated with passed {@link EntityType}.
	 *
	 * @param entityType Used as identifier.
	 * @return The {@code Record} associated with passed {@code EntityType} is returned.
	 * @throws IllegalArgumentException Thrown if associated {@code Record}
	 * 		does not exist.
	 */
	public Record getRecord(EntityType entityType) {
		Record record = records.get(entityType);
		if(record == null) {
			throw new IllegalArgumentException("Record for entity type '" + entityType + "' is not available.");
		}

		return record;
	}

	/**
	 * Removes the {@link Record} associated with passed {@link EntityType} from
	 * this result and returns it.
	 *
	 * @param entityType Used as identifier.
	 * @return The removed {@code Record} associated with passed {@code EntityType}
	 * 		is returned.
	 * @throws IllegalArgumentException Thrown if associated {@code Record}
	 * 		does not exists.
	 */
	public Record removeRecord(EntityType entityType) {
		Record record = records.remove(entityType);
		if(record == null) {
			throw new IllegalArgumentException("Record for entity type '" + entityType + "' is not available.");
		}

		return record;
	}

	/**
	 * Returns the {@link Value} container associated with passed {@link Attribute}.
	 *
	 * @param attribute Used as identifier to find the associated {@code Value}.
	 * @return Associated {@code Value} is returned.
	 */
	public Value getValue(Attribute attribute) {
		return getRecord(attribute.getEntityType()).getValue(attribute.getName());
	}

	/**
	 * Merges passed result with this instance. To be able to do so, the passed
	 * result must be compatible with this result. Results are compatible if
	 * the subset of {@link Record}s is the same.
	 *
	 * @param result The result that will be merged with this instance.
	 * @return A new result with merged {@code Record}s is returned.
	 * @throws IllegalArgumentException Thrown if passed result is not compatible.
	 */
	public Result merge(Result result) {
		if(records.size() != result.records.size()) {
			throw new IllegalArgumentException("Unable to merge, incompatible result passed.");
		}

		Result mergedResult = new Result();
		records.keySet().stream().forEach(e -> mergedResult.addRecord(getRecord(e).merge(result.getRecord(e))));
		return mergedResult;
	}

	/**
	 * Returns an iterator over the {@link Record}s contained in this result.
	 *
	 * @return The iterator over the contained {@code Record}s is returned.
	 */
	@Override
	public Iterator<Record> iterator() {
		return records.values().iterator();
	}

	/**
	 * Returns a human readable {@code String} representation of this result.
	 *
	 * @return The {@code String} representation of this result.
	 */
	@Override
	public String toString() {
		return new StringBuilder("Result(records = ").append(records.values()).append(')').toString();
	}

	@Deprecated // TODO replace in a more readable and understandable way...
	public List<Record> retainAll(List<EntityType> entityTypes) {
		Iterator<Entry<EntityType, Record>> recordIterator = records.entrySet().iterator();
		List<Record> removedRecords = new ArrayList<>();

		while(recordIterator.hasNext()) {
			Entry<EntityType, Record> entry = recordIterator.next();

			if(!entityTypes.contains(entry.getKey())) {
				recordIterator.remove();
				removedRecords.add(entry.getValue());
			}
		}

		return removedRecords;
	}

}