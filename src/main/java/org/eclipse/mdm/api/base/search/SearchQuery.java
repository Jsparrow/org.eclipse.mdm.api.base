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

import java.util.List;

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.eclipse.mdm.api.base.query.Filter;
import org.eclipse.mdm.api.base.query.Result;

/**
 * This is an interface for predefined search query implementations.
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
public interface SearchQuery {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns all {@link EntityType}s supported by this search query.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<EntityType> listEntityTypes();

	/**
	 * Returns the {@link Searchable}, which describes a hierarchical order of
	 * the {@link EntityType}s supported by this search query.
	 *
	 * @return The {@code Searchable} root is returned.
	 */
	Searchable getSearchableRoot();

	/**
	 * Returns the distinct {@link Value} sequence for given {@link Attribute}.
	 * The {@code Attribute} must be supported by this search query. The
	 * returned {@code Value} sequence is intended to be used for building
	 * filter criteria for this search query.
	 *
	 * @param attribute
	 *            The {@code Attribute} whose distinct values will be queried.
	 * @return A distinct {@code List} of all available {@code Value}s is
	 *         returned.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the distinct {@code Value} sequence.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 * @see #getFilterValues(Attribute, Filter)
	 */
	default List<Value> getFilterValues(Attribute attribute) {
		return getFilterValues(attribute, Filter.and());
	}

	/**
	 * Returns the distinct {@link Value} sequence for given {@link Attribute}
	 * and {@link Filter}. Both must be fully supported by this search query.
	 * The returned {@code Value} sequence is intended to be used for building
	 * filter criteria for this search query.
	 *
	 * @param attribute
	 *            The {@code Attribute} whose distinct values will be queried.
	 * @param filter
	 *            The criteria sequence.
	 * @return A distinct {@code List} of {@code Value}s is returned.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the distinct {@code Value} sequence.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 * @see #getFilterValues(Attribute)
	 */
	List<Value> getFilterValues(Attribute attribute, Filter filter);

	/**
	 * Executes this search query with given {@link EntityType}s. The {@code
	 * EntityType}s must be fully supported by this search query. This method
	 * selects all {@link Attribute}s of each given {@code EntityType}.
	 *
	 * @param entityTypes
	 *            Select statements will be added for all {@code Attribute}s of
	 *            each given {@code EntityType}.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}s.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 * @see #fetchComplete(List, Filter)
	 * @see #fetch(List)
	 */
	default List<Result> fetchComplete(List<EntityType> entityTypes) {
		return fetchComplete(entityTypes, Filter.and());
	}

	/**
	 * Executes this search query with given {@link EntityType}s and
	 * {@link Filter}. Both must be fully supported by this search query. This
	 * method selects all {@link Attribute}s of each given {@code EntityType}.
	 *
	 * @param entityTypes
	 *            Select statements will be added for all {@code Attribute}s of
	 *            each given {@code EntityType}.
	 * @param filter
	 *            The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}s.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 * @see #fetchComplete(List)
	 * @see #fetch(List, Filter)
	 */
	List<Result> fetchComplete(List<EntityType> entityTypes, Filter filter);

	/**
	 * Executes this search query with given {@link Attribute}s. The {@code
	 * Attribute}s must be fully supported by this search query. This method
	 * allows fine grained {@link Result} configuration.
	 *
	 * @param attributes
	 *            Select statements will be added for each {@code
	 * 		Attribute}.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}s.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 * @see #fetch(List, Filter)
	 * @see #fetchComplete(List)
	 */
	default List<Result> fetch(List<Attribute> attributes) {
		return fetch(attributes, Filter.and());
	}

	/**
	 * Executes this search query with given {@link Attribute}s and
	 * {@link Filter}. Both must be fully supported by this search query. This
	 * method allows fine grained {@link Result} configuration.
	 *
	 * @param attributes
	 *            Select statements will be added for each {@code
	 * 		Attribute}.
	 * @param filter
	 *            The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown in case of errors while executing the query or
	 *             generating the {@code Result}s.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 * @see #fetch(List)
	 * @see #fetchComplete(List, Filter)
	 */
	List<Result> fetch(List<Attribute> attributes, Filter filter);

}
