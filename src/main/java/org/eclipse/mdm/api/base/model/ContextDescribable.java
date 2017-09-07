/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.BaseEntityManager;
import org.eclipse.mdm.api.base.query.DataAccessException;

/**
 * {@link TestStep} and {@link Measurement} entity types implement this
 * interface to indicate the availability of descriptive context data.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ContextRoot
 * @see ContextComponent
 * @see ContextSensor
 */
public interface ContextDescribable extends Entity {
	/**
	 * Queries available {@link ContextType} for given
	 * {@link ContextDescribable}.
	 *
	 * @param manager
	 *            An object implementing BaseEntityManager.
	 * @return {@code List} contains the {@code ContextType} of each referenced
	 *         {@link ContextRoot}.
	 * @throws DataAccessException
	 *             Thrown if unable to query the available {@code ContextType}s.
	 */
	List<ContextType> loadContextTypes(BaseEntityManager<? extends BaseEntityFactory> manager) throws DataAccessException;

	/**
	 * Loads the requested {@link ContextRoot}s for given
	 * {@link ContextDescribable}.
	 *
	 * @param manager
	 *            An object implementing BaseEntityManager.
	 * @param contextTypes
	 *            The requested context types. If omitted, all types are be
	 *            loaded.
	 * @return The ordered contexts for given {@code TestStep} or the measured
	 *         ones for {@code Measurement} are returned in a {@code Map}.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the {@code
	 * 		ContextRoot}s.
	 * @see ContextType
	 */
	Map<ContextType, ContextRoot> loadContexts(BaseEntityManager<? extends BaseEntityFactory> manager, ContextType... contextTypes)
			throws DataAccessException;

}
