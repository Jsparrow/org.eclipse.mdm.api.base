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


package org.eclipse.mdm.api.base.search;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.adapter.Relation;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.eclipse.mdm.api.base.query.Filter;
import org.eclipse.mdm.api.base.query.Record;
import org.eclipse.mdm.api.base.query.Result;

/**
 * This search service uses given {@link Entity} type to execute the associated
 * predefined {@link SearchQuery} and returns the results.
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
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @return The returned {@code List} may be immutable.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 */
	List<EntityType> listEntityTypes(Class<? extends Entity> entityClass);

	/**
	 * Returns the {@link Searchable}, which describes a hierarchical order of
	 * the {@link EntityType}s supported by the {@link SearchQuery} associated
	 * with given {@link Entity} type.
	 *
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @return The {@code Searchable} root is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 */
	Searchable getSearchableRoot(Class<? extends Entity> entityClass);

	/**
	 * Returns the distinct {@link Value} sequence for given {@link Attribute}.
	 * The {@code Attribute} must be supported by the {@link SearchQuery}
	 * associated with given {@link Entity} type. The returned {@code Value}
	 * sequence is intended to be used for building filter criteria.
	 *
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param attribute
	 *            The {@code Attribute} whose distinct values will be queried.
	 * @return A distinct {@code List} of all available {@code Value}s is
	 *         returned.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the distinct {@code Value} sequence.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #getFilterValues(Class, Attribute, Filter)
	 */
	default List<Value> getFilterValues(Class<? extends Entity> entityClass, Attribute attribute) {
		return getFilterValues(entityClass, attribute, Filter.and());
	}

	/**
	 * Returns the distinct {@link Value} sequence for given {@link Attribute}
	 * and {@link Filter}. Both must be fully supported by the
	 * {@link SearchQuery} associated with given {@link Entity} type. The
	 * returned {@code Value} sequence is intended to be used for building
	 * filter criteria.
	 *
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param attribute
	 *            The {@code Attribute} whose distinct values will be queried.
	 * @param filter
	 *            The criteria sequence.
	 * @return A distinct {@code List} of {@code Value}s is returned.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the distinct {@code Value} sequence.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #getFilterValues(Class, Attribute)
	 */
	List<Value> getFilterValues(Class<? extends Entity> entityClass, Attribute attribute, Filter filter);

	/**
	 * Executes the associated {@link SearchQuery} with given
	 * {@link EntityType}s. The {@code EntityType}s must be fully supported by
	 * the {@code SearchQuery} associated with given {@link Entity} type. This
	 * method selects all {@link Attribute}s of each given {@code EntityType}.
	 *
	 * It is only guaranteed that this method loads the selected entities,
	 * and their relations among themselves. No information about additional related entities 
	 * is necessarily loaded.
	 *
	 * <p>
	 * <b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T>
	 *            Type of the entities that will be generated for each result.
	 * @param entityCass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param entityTypes
	 *            Select statements will be added for all {@code Attribute}s of
	 *            each given {@code EntityType}.
	 * @return All matched entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetch(Class, List)
	 * @see Record#merge(Record)
	 */
	default <T extends Entity> List<T> fetchComplete(Class<T> entityCass, List<EntityType> entityTypes) {
		return fetchComplete(entityCass, entityTypes, Filter.and());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given
	 * {@link EntityType}s and {@link Filter}. Both must be fully supported by
	 * the {@code SearchQuery} associated with given {@link Entity} type. This
	 * method selects all {@link Attribute}s of each given {@code EntityType}.
	 * 
	 * It is only guaranteed that this method loads the selected entities,
	 * and their relations among themselves. No information about additional related entities 
	 * is necessarily loaded.
	 *
	 * <p>
	 * <b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T>
	 *            Type of the entities that will be generated for each. result.
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param entityTypes
	 *            Select statements will be added for all {@code Attribute}s of
	 *            each given {@code EntityType}.
	 * @param filter
	 *            The criteria sequence.
	 * @return All matched entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetch(Class, List, Filter)
	 * @see Record#merge(Record)
	 */
	<T extends Entity> List<T> fetchComplete(Class<T> entityClass, List<EntityType> entityTypes, Filter filter);

	/**
	 * Executes the associated {@link SearchQuery} and returns all available
	 * instances of the specified {@link Entity} type.
	 * 
	 * It is only guaranteed that this method loads the selected entity,
	 * no information about related entities is necessarily loaded.
	 *
	 * @param <T>
	 *            Type of the entities that will be generated for each result.
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @return All available entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #fetch(Class, Filter)
	 */
	default <T extends Entity> List<T> fetch(Class<T> entityClass) {
		return fetch(entityClass, Filter.and());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Filter}.
	 * The {@code Filter} must be fully supported by the {@code SearchQuery}
	 * associated with given {@link Entity} type.
	 * It is only guaranteed that this method loads the selected entity,
	 * no information about related entities is necessarily loaded.
	 *  
	 *
	 * @param <T>
	 *            Type of the entities that will be generated for each result.
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param filter
	 *            The criteria sequence.
	 * @return All matched entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetch(Class)
	 */
	default <T extends Entity> List<T> fetch(Class<T> entityClass, Filter filter) {
		return fetch(entityClass, Collections.emptyList(), filter);
	}

	/**
	 * Executes the associated {@link SearchQuery} with given
	 * {@link Attribute}s. The {@code Attribute}s must be fully supported by the
	 * {@code SearchQuery} associated with given {@link Entity} type. This
	 * method allows fine grained {@link Record} configuration.
	 * 
	 * It is only guaranteed that this method loads the selected entity or attributes,
	 * no additional information about related entities is necessarily loaded.
	 *
	 * <p>
	 * <b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T>
	 *            Type of the entities that will be generated for each result.
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param attributes
	 *            Select statements will be added for each {@code
	 * 		Attribute}.
	 * @return All matched entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetchComplete(Class, List)
	 * @see Record#merge(Record)
	 */
	default <T extends Entity> List<T> fetch(Class<T> entityClass, List<Attribute> attributes) {
		return fetch(entityClass, attributes, Filter.and());
	}

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Attribute}s
	 * and {@link Filter}. Both must be fully supported by the
	 * {@code SearchQuery} associated with given {@link Entity} type. This
	 * method allows fine grained {@link Record} configuration.
	 * 
	 * It is only guaranteed that this method loads the selected entity or attributes,
	 * no additional information about related entities is necessarily loaded.
	 *
	 * <p>
	 * <b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param <T>
	 *            Type of the entities that will be generated for each result.
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param attributes
	 *            Select statements will be added for each {@code
	 * 		Attribute}.
	 * @param filter
	 *            The criteria sequence.
	 * @return All matched entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see #fetchComplete(Class, List, Filter)
	 * @see Record#merge(Record)
	 */
	<T extends Entity> List<T> fetch(Class<T> entityClass, List<Attribute> attributes, Filter filter);

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Attribute}s
	 * and {@link Filter}. Both must be fully supported by the
	 * {@code SearchQuery} associated with given {@link Entity} type. This
	 * method allows fine grained {@link Record} configuration. This method
	 * allows to specify a fulltext search query string.
	 * 
	 * It is only guaranteed that this method loads the selected entity or attributes,
	 * no additional information about related entities is necessarily loaded.
	 *
	 * <p>
	 * <b>Note:</b> Related {@code Record}s may be merged according to the
	 * cardinality of the associated {@link Relation}.
	 *
	 * @param entityClass
	 *            Used as the {@code SearchQuery} identifier.
	 * @param attributes
	 *            Select statements will be added for each {@code
	 * 		Attribute}.
	 * @param filter
	 *            The criteria sequence.
	 * @param query
	 *            The fulltext search query
	 * @return All {@link Result}s found by the {@link SearchQuery} with given
	 *         {@link Attribute}s.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the
	 *             {@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException
	 *             Thrown if given type is not associated with a predefined
	 *             {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see Record#merge(Record)
	 */
	default List<Result> fetchResults(Class<? extends Entity> entityClass, List<Attribute> attributes, Filter filter,
			String query) {
		throw new UnsupportedOperationException();
	}

	default boolean isTextSearchAvailable() {
		return false;
	}

	@SuppressWarnings("unchecked")
	default <T extends Entity> List<T> fetch(Class<T> entityClass, String query) {
		return (List<T>) fetch(query).getOrDefault(entityClass, Collections.emptyList());
	}

	default Map<Class<? extends Entity>, List<Entity>> fetch(String query) {
		throw new UnsupportedOperationException();
	}

}
