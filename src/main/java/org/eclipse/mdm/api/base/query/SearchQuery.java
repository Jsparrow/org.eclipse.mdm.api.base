/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;

import org.eclipse.mdm.api.base.model.Value;

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
	 * Returns the distinct value sequence for passed {@link Attribute} and
	 * {@link Filter}. Both must be fully supported by this search query. The
	 * returned value sequence is intended to be used for building filter
	 * criteria for this search query.
	 *
	 * @param attribute The {@code Attribute} whose distinct values will be
	 * 		queried.
	 * @param filter The criteria sequence.
	 * @return Distinct values, each boxed in a {@link Value}, collected in a
	 * 		{@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		query or generating the distinct {@code Value} sequence.
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 */
	List<Value> getFilterValues(Attribute attribute, Filter filter) throws DataAccessException;

	/**
	 * Executes this search query with given {@link EntityType}s and passed {@link
	 * Filter}. Both must be fully supported by this search query. This method
	 * selects all {@link Attribute}s for each passed {@code EntityType}.
	 *
	 * @param entityTypes For all {@code Attribute}s of each passed {@code
	 * 		EntityType} select statements will be added.
	 * @param filter The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		query or generating the {@code Result}s.
	 * @see #fetch(List, Filter)
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 */
	List<Result> fetchComplete(List<EntityType> entityTypes, Filter filter) throws DataAccessException;

	/**
	 * Executes this search query with given {@link Attribute}s and passed
	 * {@link Filter}. Both must be fully supported by this search query. This
	 * method allows fine grained {@link Result} configuration.
	 *
	 * @param attributes For each {@code Attribute} a select statement will be
	 * 		added.
	 * @param filter The criteria sequence.
	 * @return All {@code Result}s are returned in a {@code List}.
	 * @throws DataAccessException Thrown in case of errors while executing the
	 * 		query or generating the {@code Result}s.
	 * @see #fetchComplete(List, Filter)
	 * @see #getSearchableRoot()
	 * @see #listEntityTypes()
	 */
	List<Result> fetch(List<Attribute> attributes, Filter filter) throws DataAccessException;

}
