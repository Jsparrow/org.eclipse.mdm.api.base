/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.DataItem;

/**
 * Provides access to any modeled {@link Entity} within an underlying application
 * model. A {@link Query}, created by this service, may use those entities to
 * access the corresponding records.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 * @see Query
 */
public interface QueryService {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new and empty {@link Query}.
	 *
	 * @return Created {@code Query} is returned.
	 */
	Query createQuery();

	/**
	 * Returns the {@link Entity} associated with passed {@link DataItem}
	 * type.
	 *
	 * @param type Used as identifier.
	 * @return {@link Entity} associated with given {@code DataItem} type is
	 * 		returned.
	 * @throws IllegalArgumentException Thrown if {@code Entity} for given
	 * 		type does not exist.
	 */
	Entity getEntity(Class<? extends DataItem> type);

	/**
	 * Returns the {@link Entity} associated with given {@link ContextType}.
	 *
	 * @param contextType Used as identifier.
	 * @return {@code Entity} associated with given {@code ContextType} is
	 * 		returned.
	 * @throws IllegalArgumentException Thrown if {@code Entity} with given
	 * 		{@code ContextType} does not exist.
	 */
	Entity getEntity(ContextType contextType);

	/**
	 * Returns the {@link Entity} identified by passed name.
	 *
	 * @param name Used as identifier.
	 * @return {@code Entity} with passed name is returned.
	 * @throws IllegalArgumentException Thrown if {@code Entity} with given
	 * 		name does not exist.
	 */
	Entity getEntity(String name);

}
