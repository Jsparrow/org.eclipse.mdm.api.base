/*
 * Copyright (c) 2017 Peak Solution GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.mdm.api.base;

import java.util.Map;

import org.eclipse.mdm.api.base.model.BaseEntityFactory;

/**
 * Takes connection parameters and produces a corresponding base application context.
 *
 * @param <T>
 *            Type of the connected entity manager.
 * @since 1.0.0
 */
public interface BaseApplicationContextFactory<T extends BaseApplicationContext<? extends BaseEntityFactory, ? extends BaseEntityManager>> {

	/**
	 * Takes given connection parameters and creates a new context, which
	 * is permanently connected with configured data source.
	 *
	 * @param connectionParameters
	 *            The connection parameters.
	 * @return The connected context is returned.
	 * @throws ConnectionException
	 *             Thrown if unable to connect to a data source.
	 */
	T connect(Map<String, String> connectionParameters) throws ConnectionException;
}