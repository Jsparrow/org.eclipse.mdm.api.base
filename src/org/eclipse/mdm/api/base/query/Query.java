/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Provides methods to easily build queries by adding select, join, group and
 * order by statements. Finally an optional {@link Filter} may be given to
 * select data that match specified criteria.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 * @see Attribute
 * @see Aggregation
 * @see Relation
 * @see Join
 * @see Filter
 * @see Result
 */
public interface Query {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Checks whether passed {@link Entity} will be queried in any way (select
	 * or join).
	 *
	 * @param entity The checked {@code Entity}.
	 * @return True if passed {@code Entity} will be queried.
	 */
	boolean isQueried(Entity entity);

	/**
	 * Adds select statements for all {@link Attribute}s of each passed
	 * {@link Entity}.
	 *
	 * @param entities The {@code Attribute}s of each {@code Entity} will be
	 * 		passed to {@link #select(List)}
	 * @return This query is returned.
	 */
	default Query selectAll(List<Entity> entities) {
		entities.stream().map(Entity::getAttributes).forEach(this::select);
		return this;
	}

	/**
	 * Adds select statements for all {@link Attribute}s of each passed
	 * {@link Entity}.
	 *
	 * @param entities The {@code Attribute}s of each {@code Entity} will be
	 * 		passed to {@link #select(List)}
	 * @return This query is returned.
	 */
	default Query selectAll(Entity... entities) {
		Arrays.stream(entities).map(Entity::getAttributes).forEach(this::select);
		return this;
	}

	/**
	 * Adds select statements for all {@link Attribute}s identified by passed
	 * names at given {@link Entity}.
	 *
	 * @param entity The {@code Entity} whose {@code Attribute}s will be passed
	 * 		to {@link #select(Attribute)}.
	 * @param names The names of the {@code Attribute}s that have to be added.
	 * @return This query is returned.
	 * @see {@link Entity#getAttribute(String)}
	 */
	default Query select(Entity entity, String... names) {
		Arrays.stream(names).map(entity::getAttribute).forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for the ID {@link Attribute} of each passed
	 * {@link Entity}.
	 *
	 * @param entities The entities whose ID {@code Attribute}s will be passed
	 * 		to {@link #select(Attribute)}.
	 * @return This query is returned.
	 */
	default Query selectID(Entity... entities) {
		Arrays.stream(entities).map(Entity::getIDAttribute).forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for each passed {@link Attribute}.
	 *
	 * @param attributes Each passed {@code Attribute} will be passed to
	 * 		{@link #select(Attribute)}.
	 * @return This query is returned.
	 */
	default Query select(List<Attribute> attributes) {
		attributes.stream().forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for each passed {@link Attribute}.
	 *
	 * @param attributes Each passed {@code Attribute} will be passed to
	 * 		{@link #select(Attribute)}.
	 * @return This query is returned.
	 */
	default Query select(Attribute... attributes) {
		Arrays.stream(attributes).forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for passed {@link Attribute}.
	 *
	 * @param attribute {@code Attribute} will be passed to {@link
	 * 		#select(Attribute, Aggregation)} with {@link Aggregation#NONE}.
	 * @return This query is returned.
	 */
	default Query select(Attribute attribute) {
		return select(attribute, Aggregation.NONE);
	}

	/**
	 * Adds a select statement for passed {@link Attribute} with given {@link
	 * Aggregation} function.
	 *
	 * @param attribute {@code Attribute} will be passed to {@link
	 * 		#select(Attribute, Aggregation, String)} with given {@code
	 * 		Aggregation} and an empty unit name.
	 * @param aggregation The {@code Aggregation} that will be applied.
	 * @return This query is returned.
	 */
	default Query select(Attribute attribute, Aggregation aggregation) {
		return select(attribute, aggregation, "");
	}

	/**
	 * Adds a select statement for passed {@link Attribute} with given {@link
	 * Aggregation} function and unit.
	 *
	 * @param attribute The {@code Attribute} a select statement will be
	 * 		added for.
	 * @param aggregation The {@code Aggregation} that will be applied.
	 * @param unit Name of the unit the result values shall be returned in.
	 * @return This query is returned.
	 */
	Query select(Attribute attribute, Aggregation aggregation, String unit);

	/**
	 * For all unambiguous {@link Relation}s from the source {@link Entity}
	 * to each given target {@code Entity} a join statement will be added.
	 *
	 * @param source The source {@code Entity}.
	 * @param targets The unambiguous {@code Relation} to each passed target
	 * 		{@code Entity} is retrieved from the source {@code Entity} using
	 * 		{@link Entity#getRelation(Entity)} and passed to {@link
	 * 		#join(Relation)}.
	 * @return This query is returned.
	 */
	default Query join(Entity source, Entity... targets) {
		Arrays.stream(targets).map(source::getRelation).forEach(this::join);
		return this;
	}

	/**
	 * Adds a join statement for each passed {@link Relation}s.
	 *
	 * @param relations Each {@code Relation} will be passed to {@link
	 * 		#join(Relation)}.
	 * @return This query is returned.
	 */
	default Query join(List<Relation> relations) {
		relations.stream().forEach(this::join);
		return this;
	}

	/**
	 * Adds a join statement for each passed {@link Relation}s.
	 *
	 * @param relations Each {@code Relation} will be passed to {@link
	 * 		#join(Relation)}.
	 * @return This query is returned.
	 */
	default Query join(Relation... relations) {
		Arrays.stream(relations).forEach(this::join);
		return this;
	}

	/**
	 * Adds a join statement for passed {@link Relation}.
	 *
	 * @param relation {@code Relation} will be passed to {@link
	 * 		#join(Relation, Join)} with {@link Join#INNER}.
	 * @return This query is returned.
	 */
	default Query join(Relation relation) {
		return join(relation, Join.INNER);
	}

	/**
	 * Adds a join statement for passed {@link Relation} with given {@link
	 * Join} type.
	 *
	 * @param relation The {@code Relation} a join statement will be added for.
	 * @param join The {@code Join} type that will be applied.
	 * @return This query is returned.
	 */
	Query join(Relation relation, Join join);

	/**
	 * Adds a group by statement for each passed {@link Attribute}.
	 *
	 * @param attributes Each {@code Attribute} will be passed to {@link
	 * 		#group(Attribute)}.
	 * @return This query is returned.
	 */
	default Query group(List<Attribute> attributes) {
		attributes.stream().forEach(this::group);
		return this;
	}

	/**
	 * Adds a group by statement for each passed {@link Attribute}.
	 *
	 * @param attributes Each {@code Attribute} will be passed to {@link
	 * 		#group(Attribute)}.
	 * @return This query is returned.
	 */
	default Query group(Attribute... attributes) {
		Arrays.stream(attributes).forEach(this::group);
		return this;
	}

	/**
	 * Adds a group by statement for passed {@link Attribute}.
	 *
	 * @param attribute The {@code Attribute} a group by statement will be
	 * 		added for.
	 * @return This query is returned.
	 */
	Query group(Attribute attribute);

	/**
	 * Adds an order by statement for each passed {@link Attribute}.
	 *
	 * @param attributes Each {@code Attribute} will be passed to {@link
	 * 		#order(Attribute)}.
	 * @return This query is returned.
	 */
	default Query order(List<Attribute> attributes) {
		attributes.stream().forEach(this::order);
		return this;
	}

	/**
	 * Adds an order by statement for each passed {@link Attribute}.
	 *
	 * @param attributes Each {@code Attribute} will be passed to {@link
	 * 		#order(Attribute)}.
	 * @return This query is returned.
	 */
	default Query order(Attribute... attributes) {
		Arrays.stream(attributes).forEach(this::order);
		return this;
	}

	/**
	 * Adds an order by statement for passed {@link Attribute}.
	 *
	 * @param attribute The {@code Attribute} is passed to {@link
	 * 		#order(Attribute, boolean)} with {@code ascending = true}.
	 * @return This query is returned.
	 */
	default Query order(Attribute attribute) {
		return order(attribute, true);
	}

	/**
	 * Adds an order by statement for passed {@link Attribute} with given
	 * ascending flag.
	 *
	 * @param attribute The {@code Attribute} an order by statement will be
	 * 		added for.
	 * @param ascending The ascending flag that will be applied.
	 * @return This query is returned.
	 */
	Query order(Attribute attribute, boolean ascending);

	/**
	 * Executes this query with an empty {@link Filter} and returns the
	 * {@link Result}.
	 *
	 * @return {@code Optional} is empty if no {@code Result} could be found.
	 * @throws DataAccessException Thrown in case of errors while executing
	 * 		the query or generating the {@code Result}.
	 */
	default Optional<Result> fetchSingleton() throws DataAccessException {
		return fetchSingleton(Filter.and());
	}

	/**
	 * Executes this query with passed {@link Filter} and returns the {@link
	 * Result}.
	 *
	 * @param filter The criteria sequence.
	 * @return {@code Optional} is empty if no {@code Result} could be found.
	 * @throws DataAccessException Thrown in case of errors while executing
	 * 		the query or generating the {@code Result}.
	 */
	Optional<Result> fetchSingleton(Filter filter) throws DataAccessException;

	/**
	 * Executes this query with an empty {@link Filter} and returns the {@link
	 * Result}s.
	 *
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing
	 * 		the query or generating the {@code Result}s.
	 */
	default List<Result> fetch() throws DataAccessException {
		return fetch(Filter.and());
	}

	/**
	 * Executes this query with passed {@link Filter} and returns the {@link
	 * Result}s.
	 *
	 * @param filter The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing
	 * 		the query or generating the {@code Result}s.
	 */
	List<Result> fetch(Filter filter) throws DataAccessException;

}

