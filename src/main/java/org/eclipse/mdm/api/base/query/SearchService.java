/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Value;

/**
 * This search service uses given {@link Entity} type to execute the associated
 * {@link SearchQuery}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see EntityType
 * @see Searchable
 * @see Attribute
 * @see Value
 * @see Filter
 * @see Result
 */
public interface SearchService {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns all {@link Entity} types this search service provides a
	 * predefined {@link SearchQuery} for.
	 *
	 * @return The returned {@code List} with supported types may be immutable.
	 */
	List<Class<? extends Entity>> listSearchableTypes();

	/**
	 * Returns all {@link EntityType}s supported by the {@link SearchQuery}
	 * associated with given {@link Entity} type.
	 *
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @return The returned {@code List} may be immutable.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 */
	List<EntityType> listEntityTypes(Class<? extends Entity> entityClass);

	/**
	 * Returns the {@link Searchable}, which describes a hierarchical order of
	 * the {@link EntityType}s supported by the {@link SearchQuery} associated
	 * with given {@link Entity} type.
	 *
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @return The {@code Searchable} root is returned.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 */
	Searchable getSearchableRoot(Class<? extends Entity> entityClass);

	/**
	 * Returns the distinct {@link Value} sequence for given {@link Attribute}.
	 * The {@code Attribute} must be supported by the {@link SearchQuery}
	 * associated with given {@link Entity} type. The returned {@code Value}
	 * sequence is intended to be used for building filter criteria.
	 *
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @param attribute The {@code Attribute} whose distinct values will be
	 * 		queried.
	 * @return A distinct {@code List} of all available {@code Value}s is returned.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		query or generating the distinct {@code Value} sequence.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #getFilterValues(Class, Attribute, Filter)
	 */
	default List<Value> getFilterValues(Class<? extends Entity> entityClass, Attribute attribute)
			throws DataAccessException {
		return getFilterValues(entityClass, attribute, Filter.and());
	}

	/**
	 * Returns the distinct {@link Value} sequence for given {@link Attribute}
	 * and {@link Filter}. Both must be fully supported by the {@link SearchQuery}
	 * associated with given {@link Entity} type. The returned {@code Value}
	 * sequence is intended to be used for building filter criteria.
	 *
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @param attribute The {@code Attribute} whose distinct values will be
	 * 		queried.
	 * @param filter The criteria sequence.
	 * @return A distinct {@code List} of {@code Value}s is returned.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		query or generating the distinct {@code Value} sequence.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #getFilterValues(Class, Attribute)
	 */
	List<Value> getFilterValues(Class<? extends Entity> entityClass, Attribute attribute, Filter filter)
			throws DataAccessException;

	/**
	 * Executes the associated {@link SearchQuery} with given {@link EntityType}s.
	 * The {@code EntityType}s must be fully supported by the {@code SearchQuery}
	 * associated with given {@link Entity} type. This method selects all {@link
	 * Attribute}s of each given {@code EntityType}.
	 *
	 * <p><b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T> Type of the entities that will be generated for each result.
	 * @param entityCass Used as the {@code SearchQuery} identifier.
	 * @param entityTypes Select statements will be added for all {@code Attribute}s
	 * 		of each given {@code EntityType}.
	 * @return All {@link Result}s are returned in a {@code Map}, which maps
	 * 		entities to related {@link Record}s.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetch(Class, List)
	 * @see Record#merge(Record)
	 */
	default <T extends Entity> Map<T, Result> fetchComplete(Class<T> entityCass, List<EntityType> entityTypes)
			throws DataAccessException {
		return fetchComplete(entityCass, entityTypes, Filter.and());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given {@link EntityType}s
	 * and {@link Filter}. Both must be fully supported by the {@code SearchQuery}
	 * associated with given {@link Entity} type. This method selects all {@link
	 * Attribute}s of each given {@code EntityType}.
	 *
	 * <p><b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T> Type of the entities that will be generated for each.
	 * 		result.
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @param entityTypes Select statements will be added for all {@code Attribute}s
	 * 		of each given {@code EntityType}.
	 * @param filter The criteria sequence.
	 * @return All {@link Result}s are returned in a {@code Map}, which maps
	 * 		entities to related {@link Record}s.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetch(Class, List, Filter)
	 * @see Record#merge(Record)
	 */
	<T extends Entity> Map<T, Result> fetchComplete(Class<T> entityClass, List<EntityType> entityTypes, Filter filter)
			throws DataAccessException;

	/**
	 * Executes the associated {@link SearchQuery} and returns all available
	 * instances of the specified {@link Entity} type.
	 *
	 * @param <T> Type of the entities that will be generated for each result.
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @return All available entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #fetch(Class, Filter)
	 */
	default <T extends Entity> List<T> fetch(Class<T> entityClass) throws DataAccessException {
		return fetch(entityClass, Filter.and());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Filter}.
	 * The {@code Filter} must be fully supported by the {@code SearchQuery}
	 * associated with given {@link Entity} type.
	 *
	 * @param <T> Type of the entities that will be generated for each result.
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @param filter The criteria sequence.
	 * @return All matched entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetch(Class)
	 */
	default <T extends Entity> List<T> fetch(Class<T> entityClass, Filter filter) throws DataAccessException {
		return fetch(entityClass, Collections.emptyList(), filter).keySet().stream().collect(Collectors.toList());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Attribute}s.
	 * The {@code Attribute}s must be fully supported by the {@code SearchQuery}
	 * associated with given {@link Entity} type. This method allows fine
	 * grained {@link Record} configuration.
	 *
	 * <p><b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T> Type of the entities that will be generated for each result.
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @param attributes Select statements will be added for each {@code
	 * 		Attribute}.
	 * @return All {@link Result}s are returned in a {@code Map}, which maps
	 * 		entities to related {@code Record}s.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetchComplete(Class, List)
	 * @see Record#merge(Record)
	 */
	default <T extends Entity> Map<T, Result> fetch(Class<T> entityClass, List<Attribute> attributes)
			throws DataAccessException {
		return fetch(entityClass, attributes, Filter.and());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Attribute}s
	 * and {@link Filter}. Both must be fully supported by the {@code SearchQuery}
	 * associated with given {@link Entity} type. This method allows fine
	 * grained {@link Record} configuration.
	 *
	 * <p><b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T> Type of the entities that will be generated for each result.
	 * @param entityClass Used as the {@code SearchQuery} identifier.
	 * @param attributes Select statements will be added for each {@code
	 * 		Attribute}.
	 * @param filter The criteria sequence.
	 * @return All {@link Result}s are returned in a {@code Map}, which maps
	 * 		entities to related {@code Record}s.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if given type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetchComplete(Class, List, Filter)
	 * @see Record#merge(Record)
	 */
	<T extends Entity> Map<T, Result> fetch(Class<T> entityClass, List<Attribute> attributes, Filter filter)
			throws DataAccessException;

	default boolean isTextSearchAvailable() {
		return false;
	}

	@SuppressWarnings("unchecked")
	default <T extends Entity> List<T> fetch(Class<T> entityClass, String query) throws DataAccessException {
		return (List<T>) fetch(query).getOrDefault(entityClass, Collections.emptyList());
	}

	default Map<Class<? extends Entity>, List<Entity>> fetch(String query) throws DataAccessException {
		throw new UnsupportedOperationException();
	}

}
