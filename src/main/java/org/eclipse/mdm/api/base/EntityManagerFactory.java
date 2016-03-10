/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.List;

import javax.ejb.Local;

import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.model.ValueType;

/**
 * Takes connection parameters and produces a corresponding entity manager. As
 * soon as the entity manager is no longer required, its connection should be
 * closed by using this factory's disconnect method.
 *
 * @param <T> Type of the connected entity manager.
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Value
 */
@Local
public interface EntityManagerFactory<T extends EntityManager> {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Takes given connection parameters and creates a new entity manager, which
	 * is connected with configured data source.
	 *
	 * @param connectionParameters The connection parameters.
	 * @return The connected entity manager is returned.
	 * @throws ConnectionException Thrown if unable to connect to a data source.
	 * @see ValueType#createValue(String, Object)
	 * @see ValueType
	 * @see Value
	 */
	T connect(List<Value> connectionParameters) throws ConnectionException;

}
