/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.List;

import org.eclipse.mdm.api.base.model.Value;

/**
 * Takes connection parameters and produces a corresponding data provider. As
 * soon as the data provider is no longer required, it's connection should be
 * closed by using this factory's disconnect method.
 *
 * @param <T> Type of the connected data provider.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Value
 */
public interface DataProviderFactory<T extends BaseDataProvider> {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Takes given connection parameters and creates a new data provider, which
	 * is connected with configured data source.
	 *
	 * @param connectionParameters The connection parameters.
	 * @return The connected data provider is returned.
	 * @throws DataProviderException Thrown if unable to connect to a data source.
	 * @see Value
	 */
	T connect(List<Value> connectionParameters) throws DataProviderException;

	/**
	 * Takes given data provider and ensures it's data source connection is
	 * closed.
	 *
	 * @param dataProvider The data provider, whose connection will be closed.
	 * @throws DataProviderException Thrown if unable to close the connection.
	 */
	void disconnect(T dataProvider) throws DataProviderException;

}
