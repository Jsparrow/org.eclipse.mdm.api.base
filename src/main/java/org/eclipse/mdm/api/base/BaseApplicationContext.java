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

package org.eclipse.mdm.api.base;

import java.util.Map;
import java.util.Optional;

import org.eclipse.mdm.api.base.adapter.ModelManager;
import org.eclipse.mdm.api.base.file.FileService;
import org.eclipse.mdm.api.base.model.BaseEntityFactory;
import org.eclipse.mdm.api.base.notification.NotificationService;
import org.eclipse.mdm.api.base.query.QueryService;
import org.eclipse.mdm.api.base.search.SearchService;

/**
 * Base application context represents a connection/session to the underlying data store.
 * It provides access to managers and services.
 *
 * @param <S>
 *            Type of the connected entity factory.
 * @param <T>
 *            Type of the connected entity manager.
 * @since 1.0.0
 */
public interface BaseApplicationContext<S extends BaseEntityFactory, T extends BaseEntityManager> extends AutoCloseable {
	/**
	 * The returned service loads entities from the underlying data source.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 */
	default Optional<T> getEntityManager() {
		return Optional.empty();
	}
	
	/**
	 * The returned service creates new entities.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 */
	default Optional<S> getEntityFactory() {
		return Optional.empty();
	}

	/**
	 * The returned service provides access to the application model's meta data.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see ModelManager
	 */
	default Optional<ModelManager> getModelManager() {
		return Optional.empty();
	}

	/**
	 * The returned service provides advanced search capabilities for supported
	 * entity types.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see SearchService
	 */
	default Optional<SearchService> getSearchService() {
		return Optional.empty();
	}

	/**
	 * The returned service provides access to the low level query API.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see QueryService
	 */
	default Optional<QueryService> getQueryService() {
		return Optional.empty();
	}
	
	/**
	 * The returned service allows to download linked files from the file
	 * storage.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see FileService
	 */
	default Optional<FileService> getFileService() {
		return Optional.empty();
	}
	
	/**
	 * The returned service allows to register/unregister for events at a registration service.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see NotificationService
	 */
	default Optional<NotificationService> getNotificationService() {
		return Optional.empty();
	}
	
	/**
	 * Returns a map with all configuration parameters, which where given to initialize this context.
	 * 
	 * @return map with configuration parameters
	 */
	Map<String, String> getParameters();
	
	/**
	 * Returns a string describing the type of the underlying API implementation.
	 * The exact content is up to the individual adapter and it is intended to be 
	 * interpreted by the client.
	 * 
	 * @return a string describing the type of the underlying API implementation.
	 */
	String getAdapterType();
	
	/**
	 * Closes the BaseContext.
	 * 
	 * This closes all underlying managers and services.
	 */
	@Override
	void close() throws ConnectionException;
}
