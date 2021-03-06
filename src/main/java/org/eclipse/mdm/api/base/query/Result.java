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


package org.eclipse.mdm.api.base.query;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.model.Value;

/**
 * A result consists of one or more {@link Record}s, which is mapped from an {@link EntityType} 
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class Result implements Iterable<Record> {

	private final Map<EntityType, Record> records = new HashMap<>();

	/**
	 * Adds given {@link Record} to this result.
	 *
	 * @param record
	 *            {@code Record} that will be added.
	 * @throws IllegalArgumentException
	 *             Thrown if given {@code Record} overwrites an existing one.
	 */
	public void addRecord(Record record) {
		if (records.put(record.getEntityType(), record) != null) {
			throw new IllegalArgumentException(
					new StringBuilder().append("Record for entity type '").append(record.getEntityType()).append("' is already defined.").toString());
		}
	}

	/**
	 * Returns the {@link Record} associated with given {@link EntityType}.
	 *
	 * @param entityType
	 *            Used as identifier.
	 * @return The {@code Record} associated with given {@code EntityType} is
	 *         returned.
	 * @throws IllegalArgumentException
	 *             Thrown if associated {@code Record} does not exist.
	 */
	public Record getRecord(EntityType entityType) {
		Record record = records.get(entityType);
		if (record == null) {
			throw new IllegalArgumentException(new StringBuilder().append("Record for entity type '").append(entityType).append("' is not available.").toString());
		}

		return record;
	}

	/**
	 * Removes the {@link Record} associated with given {@link EntityType} from
	 * this result and returns it.
	 *
	 * @param entityType
	 *            Used as identifier.
	 * @return The removed {@code Record} associated with given
	 *         {@code EntityType} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if associated {@code Record} does not exists.
	 */
	public Record removeRecord(EntityType entityType) {
		Record record = records.remove(entityType);
		if (record == null) {
			throw new IllegalArgumentException(new StringBuilder().append("Record for entity type '").append(entityType).append("' is not available.").toString());
		}

		return record;
	}

	/**
	 * Returns the {@link Value} container associated with given
	 * {@link Attribute} and no aggregation.
	 *
	 * @param attribute
	 *            Used as identifier to find the associated {@code Value}.
	 * @return Associated {@code Value} is returned.
	 */
	public Value getValue(Attribute attribute) {
		return getValue(attribute, Aggregation.NONE);
	}
	
	/**
	 * Returns the {@link Value} container associated with given {@link Attribute}
	 * and {@link Aggregation}.
	 *
	 * @param attribute
	 *            Used as identifier to find the associated {@code Value}.
	 * @param aggregation
	 *            {@link Aggregation} used with the attribute.
	 * @return Associated {@code Value} is returned.
	 */
	public Value getValue(Attribute attribute, Aggregation aggregation) {
		String key;
		if (Aggregation.NONE == aggregation) {
			key = attribute.getName();
		} else {
			key = String.format("%s(%s)", aggregation.name(), attribute.getName());
		}

		return getRecord(attribute.getEntityType()).getValue(key);
	}

	/**
	 * Merges given result with this instance. To be able to do so, the given
	 * result must be compatible with this result. Results are compatible if the
	 * subset of {@link Record}s is the same.
	 *
	 * @param result
	 *            The result that will be merged with this instance.
	 * @return A new result with merged {@code Record}s is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if given result is not compatible.
	 */
	public Result merge(Result result) {
		if (records.size() != result.records.size()) {
			throw new IllegalArgumentException("Unable to merge, incompatible result passed.");
		}

		Result mergedResult = new Result();
		records.keySet().stream().forEach(e -> mergedResult.addRecord(getRecord(e).merge(result.getRecord(e))));
		return mergedResult;
	}

	/**
	 * Returns a sequential stream with this result as its source.
	 *
	 * @return A sequential stream with records of this result.
	 */
	public Stream<Record> stream() {
		return StreamSupport.stream(spliterator(), false);
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
		return new StringBuilder("Result(Records = ").append(records.values()).append(')').toString();
	}

}
