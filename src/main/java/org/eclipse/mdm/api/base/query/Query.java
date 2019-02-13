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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.adapter.Relation;

/**
 * Provides methods to easily build queries by adding select, join, group by and
 * order by statements. Finally an optional {@link Filter} may be given to
 * select data that match specified criteria.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see EntityType
 * @see Attribute
 * @see Aggregation
 * @see Relation
 * @see JoinType
 * @see Result
 */
public interface Query {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Checks whether given {@link EntityType} will be queried in any way
	 * (select or join).
	 *
	 * @param entityType
	 *            The checked {@code EntityType}.
	 * @return True if given {@code EntityType} will be queried.
	 */
	boolean isQueried(EntityType entityType);

	/**
	 * Adds select statements for all {@link Attribute}s of each given
	 * {@link EntityType}.
	 *
	 * @param entityTypes
	 *            The {@code Attribute}s of each {@code EntityType} will be
	 *            passed to {@link #select(List)}.
	 * @return This query is returned.
	 */
	default Query selectAll(List<EntityType> entityTypes) {
		entityTypes.stream().map(EntityType::getAttributes).forEach(this::select);
		return this;
	}

	/**
	 * Adds select statements for all {@link Attribute}s of each given
	 * {@link EntityType}.
	 *
	 * @param entityTypes
	 *            The {@code Attribute}s of each {@code EntityType} will be
	 *            passed to {@link #select(List)}.
	 * @return This query is returned.
	 */
	default Query selectAll(EntityType... entityTypes) {
		Arrays.stream(entityTypes).map(EntityType::getAttributes).forEach(this::select);
		return this;
	}

	/**
	 * Adds select statements for all {@link Attribute}s identified by given
	 * names at given {@link EntityType}.
	 *
	 * @param entityType
	 *            The {@code EntityType} whose {@code Attribute}s will be passed
	 *            to {@link #select(Attribute)}.
	 * @param names
	 *            The names of the {@code Attribute}s that have to be added.
	 * @return This query is returned.
	 * @see EntityType#getAttribute(String)
	 */
	default Query select(EntityType entityType, String... names) {
		Arrays.stream(names).map(entityType::getAttribute).forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for the ID {@link Attribute} of each given
	 * {@link EntityType}.
	 *
	 * @param entityTypes
	 *            The {@code EntityType}s whose ID {@code Attribute}s will be
	 *            passed to {@link #select(Attribute)}.
	 * @return This query is returned.
	 */
	default Query selectID(EntityType... entityTypes) {
		Arrays.stream(entityTypes).map(EntityType::getIDAttribute).forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for each given {@link Attribute}.
	 *
	 * @param attributes
	 *            Each {@code Attribute} will be passed to
	 *            {@link #select(Attribute)}.
	 * @return This query is returned.
	 */
	default Query select(List<Attribute> attributes) {
		attributes.forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for each given {@link Attribute}.
	 *
	 * @param attributes
	 *            Each {@code Attribute} will be passed to
	 *            {@link #select(Attribute)}.
	 * @return This query is returned.
	 */
	default Query select(Attribute... attributes) {
		Arrays.stream(attributes).forEach(this::select);
		return this;
	}

	/**
	 * Adds a select statement for given {@link Attribute}.
	 *
	 * @param attribute
	 *            {@code Attribute} will be passed to
	 *            {@link #select(Attribute, Aggregation)} with
	 *            {@link Aggregation#NONE}.
	 * @return This query is returned.
	 */
	default Query select(Attribute attribute) {
		return select(attribute, Aggregation.NONE);
	}

	/**
	 * Adds a select statement for given {@link Attribute} with given
	 * {@link Aggregation} function.
	 *
	 * @param attribute
	 *            The {@code Attribute} a select statement will be added for.
	 * @param aggregation
	 *            The {@code Aggregation} that will be applied.
	 * @return This query is returned.
	 */
	Query select(Attribute attribute, Aggregation aggregation);

	/**
	 * For all unambiguous {@link Relation}s from the source {@link EntityType}
	 * to each given target {@code EntityType} a join statement will be added.
	 *
	 * @param source
	 *            The source {@code EntityType}.
	 * @param targets
	 *            The unambiguous {@code Relation} for each target {@code
	 * 		EntityType} is retrieved from the source {@code EntityType} using
	 *            {@link EntityType#getRelation(EntityType)} and passed to
	 *            {@link #join(Relation)}.
	 * @return This query is returned.
	 */
	default Query join(EntityType source, EntityType... targets) {
		Arrays.stream(targets).map(source::getRelation).forEach(this::join);
		return this;
	}

	/**
	 * Adds a join statement for each given {@link Relation}s.
	 *
	 * @param relations
	 *            Each {@code Relation} will be passed to
	 *            {@link #join(Relation)}.
	 * @return This query is returned.
	 */
	default Query join(List<Relation> relations) {
		relations.forEach(this::join);
		return this;
	}

	/**
	 * Adds a join statement for each given {@link Relation}s.
	 *
	 * @param relations
	 *            Each {@code Relation} will be passed to
	 *            {@link #join(Relation)}.
	 * @return This query is returned.
	 */
	default Query join(Relation... relations) {
		Arrays.stream(relations).forEach(this::join);
		return this;
	}

	/**
	 * Adds a join statement for given {@link Relation}.
	 *
	 * @param relation
	 *            {@code Relation} will be passed to
	 *            {@link #join(Relation, JoinType)} with {@link JoinType#INNER}.
	 * @return This query is returned.
	 */
	default Query join(Relation relation) {
		return join(relation, JoinType.INNER);
	}

	/**
	 * Adds a join statement for given {@link Relation} with given {@link JoinType}
	 * type.
	 *
	 * @param relation
	 *            The {@code Relation} a join statement will be added for.
	 * @param join
	 *            The {@code JoinType} type that will be applied.
	 * @return This query is returned.
	 */
	Query join(Relation relation, JoinType join);

	/**
	 * Adds a group by statement for each given {@link Attribute}.
	 *
	 * @param attributes
	 *            Each {@code Attribute} will be passed to
	 *            {@link #group(Attribute)}.
	 * @return This query is returned.
	 */
	default Query group(List<Attribute> attributes) {
		attributes.forEach(this::group);
		return this;
	}

	/**
	 * Adds a group by statement for each given {@link Attribute}.
	 *
	 * @param attributes
	 *            Each {@code Attribute} will be passed to
	 *            {@link #group(Attribute)}.
	 * @return This query is returned.
	 */
	default Query group(Attribute... attributes) {
		Arrays.stream(attributes).forEach(this::group);
		return this;
	}

	/**
	 * Adds a group by statement for given {@link Attribute}.
	 *
	 * @param attribute
	 *            The {@code Attribute} a group by statement will be added for.
	 * @return This query is returned.
	 */
	Query group(Attribute attribute);

	/**
	 * Adds an order by statement for each given {@link Attribute}.
	 *
	 * @param attributes
	 *            Each {@code Attribute} will be passed to
	 *            {@link #order(Attribute)}.
	 * @return This query is returned.
	 */
	default Query order(List<Attribute> attributes) {
		attributes.forEach(this::order);
		return this;
	}

	/**
	 * Adds an order by statement for each given {@link Attribute}.
	 *
	 * @param attributes
	 *            Each {@code Attribute} will be passed to
	 *            {@link #order(Attribute)}.
	 * @return This query is returned.
	 */
	default Query order(Attribute... attributes) {
		Arrays.stream(attributes).forEach(this::order);
		return this;
	}

	/**
	 * Adds an order by statement for given {@link Attribute}.
	 *
	 * @param attribute
	 *            The {@code Attribute} is passed to
	 *            {@link #order(Attribute, boolean)} with
	 *            {@code ascending = true}.
	 * @return This query is returned.
	 */
	default Query order(Attribute attribute) {
		return order(attribute, true);
	}

	/**
	 * Adds an order by statement for given {@link Attribute} with given
	 * ascending flag.
	 *
	 * @param attribute
	 *            The {@code Attribute} an order by statement will be added for.
	 * @param ascending
	 *            The ascending flag that will be applied.
	 * @return This query is returned.
	 */
	Query order(Attribute attribute, boolean ascending);

	/**
	 * Executes this query with an empty {@link Filter} and returns the
	 * {@link Result}.
	 *
	 * @return {@code Optional} is empty if {@code Result} could not be found.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}.
	 */
	default Optional<Result> fetchSingleton() {
		return fetchSingleton(Filter.and());
	}

	/**
	 * Executes this query with given {@link Filter} and returns the
	 * {@link Result}.
	 *
	 * @param filter
	 *            The criteria sequence.
	 * @return {@code Optional} is empty if {@code Result} could not be found.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}.
	 */
	Optional<Result> fetchSingleton(Filter filter);

	/**
	 * Executes this query with an empty {@link Filter} and returns the
	 * {@link Result}s.
	 *
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}s.
	 */
	default List<Result> fetch() {
		return fetch(Filter.and());
	}

	/**
	 * Executes this query with given {@link Filter} and returns the
	 * {@link Result}s.
	 *
	 * @param filter
	 *            The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}s.
	 */
	List<Result> fetch(Filter filter);

}
