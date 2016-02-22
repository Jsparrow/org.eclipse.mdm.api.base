/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.DataItem;

/**
 * Provides access to any modeled {@link EntityType} within an underlying application
 * model. A {@link Query}, created by this service, uses these {@code EntityType}s
 * to access corresponding records.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see EntityType
 * @see Query
 */
public interface ModelManager {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new and empty {@link Query}.
	 *
	 * @return Created {@code Query} is returned.
	 */
	Query createQuery();

	default EntityType getEntityType(DataItem dataItem) {
		return getEntityType(dataItem.getCore().getTypeName());
	}

	/**
	 * Returns the {@link EntityType} associated with passed {@link DataItem}
	 * type.
	 *
	 * @param type Used as identifier.
	 * @return {@code EntityType} associated with given {@code DataItem} type is
	 * 		returned.
	 * @throws IllegalArgumentException Thrown if {@code EntityType} for given
	 * 		type does not exist.
	 */
	EntityType getEntityType(Class<? extends DataItem> type);

	/**
	 * Returns the {@link EntityType} associated with given {@link ContextType}.
	 *
	 * @param contextType Used as identifier.
	 * @return {@code EntityType} associated with given {@code ContextType} is
	 * 		returned.
	 * @throws IllegalArgumentException Thrown if {@code EntityType} with given
	 * 		{@code ContextType} does not exist.
	 */
	EntityType getEntityType(ContextType contextType);

	/**
	 * Returns the {@link EntityType} identified by passed name.
	 *
	 * @param name Used as identifier.
	 * @return {@code EntityType} with passed name is returned.
	 * @throws IllegalArgumentException Thrown if {@code EntityType} with given
	 * 		name does not exist.
	 */
	EntityType getEntityType(String name);

}
