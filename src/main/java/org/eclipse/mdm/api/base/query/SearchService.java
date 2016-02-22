/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.model.DataItem;
import org.eclipse.mdm.api.base.model.Value;

/**
 * This search service uses given {@link DataItem} type to execute the
 * associated {@link SearchQuery}.
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
	 * Returns all {@link DataItem} types this search service provides a
	 * predefined {@link SearchQuery} for.
	 *
	 * @return Returned {@code List} may be immutable.
	 */
	List<Class<? extends DataItem>> listSearchableTypes();

	/**
	 * Returns all {@link EntityType}s supported by the {@link SearchQuery}
	 * associated with given {@link DataItem} type.
	 *
	 * @param type Used as identifier.
	 * @return The returned {@code List} may be immutable.
	 * @throws IllegalArgumentException Thrown if passed type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 */
	List<EntityType> listEntityTypes(Class<? extends DataItem> type);

	/**
	 * Returns the {@link Searchable}, which describes a hierarchical order of
	 * the {@link EntityType}s supported by the {@link SearchQuery} associated
	 * with given {@link DataItem} type.
	 *
	 * @param type Used as identifier.
	 * @return The {@code Searchable} root is returned.
	 * @throws IllegalArgumentException Thrown if passed type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 */
	Searchable getSearchableRoot(Class<? extends DataItem> type);

	/**
	 * Returns the distinct value sequence for passed {@link Attribute} and
	 * {@link Filter}. Both must be fully supported by the {@link SearchQuery}
	 * associated with given {@link DataItem} type. The returned value sequence
	 * is intended to be used for building filter criteria.
	 *
	 * @param type Used as identifier.
	 * @param attribute The {@code Attribute} whose distinct values will be
	 * 		queried.
	 * @param filter The criteria sequence.
	 * @return Distinct values, each boxed in a {@link Value}, collected in a
	 * 		{@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		query or generating the distinct {@code Value} sequence.
	 * @throws IllegalArgumentException Thrown if passed type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #listSearchableTypes()
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 */
	List<Value> getFilterValues(Class<? extends DataItem> type, Attribute attribute, Filter filter)
			throws DataAccessException;

	/**
	 * Executes the associated {@link SearchQuery} with given {@link EntityType}s
	 * and passed {@link Filter}. Both must be fully supported by the {@code
	 * SearchQuery} associated with given {@link DataItem} type. This method
	 * selects all {@link Attribute}s for each passed {@link EntityType}.
	 *
	 * @param <T> Type of the {@code DataItem}s that will be generated for each
	 * 		result.
	 * @param type Used as identifier.
	 * @param entityTypes For all {@code Attribute}s of each passed {@code EntityType}
	 * 		select statements will be added.
	 * @param filter The criteria sequence.
	 * @return All {@link Result}s are returned in a {@code Map}, which maps
	 * 		{@code DataItem}s to related {@link Record}s. Note that related
	 * 		{@code Record}s may be merged according to the cardinality of the
	 * 		underlying relation.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if passed type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #fetch(Class, List, Filter)
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see Record#merge(Record)
	 */
	<T extends DataItem> Map<T, List<Record>> fetchComplete(Class<T> type, List<EntityType> entityTypes, Filter filter)
			throws DataAccessException;

	/**
	 * Executes the associated {@link SearchQuery} with given {@link Attribute}s
	 * and passed {@link Filter}. Both must be fully supported by the {@code
	 * SearchQuery} associated with given {@link DataItem} type. This method
	 * allows fine grained {@link Record} configuration.
	 *
	 * @param <T> Type of the {@code DataItem}s that will be generated for each
	 * 		result.
	 * @param type Used as identifier.
	 * @param attributes For each {@code Attribute} a select statement will be
	 * 		added.
	 * @param filter The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code Map}, which maps
	 * 		{@code DataItem}s to related {@code Record}s. Note that related
	 * 		{@code Record}s may be merged according to the cardinality of the
	 * 		underlying relation.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		{@code SearchQuery} or analyzing its {@code Result}s.
	 * @throws IllegalArgumentException Thrown if passed type is not associated
	 * 		with a predefined {@code SearchQuery}.
	 * @see #fetchComplete(Class, List, Filter)
	 * @see #getSearchableRoot(Class)
	 * @see #listEntityTypes(Class)
	 * @see Record#merge(Record)
	 */
	<T extends DataItem> Map<T, List<Record>> fetch(Class<T> type, List<Attribute> attributes, Filter filter)
			throws DataAccessException;

}
