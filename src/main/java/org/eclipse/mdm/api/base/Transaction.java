/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.Collection;
import java.util.List;

import org.eclipse.mdm.api.base.massdata.WriteRequest;
import org.eclipse.mdm.api.base.model.Deletable;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.query.DataAccessException;

/**
 * Provides business layer CRUD operations (CREATE, READ, UPDATE, INSERT).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface Transaction {

	/**
	 * Persists given entities and implicitly updates their {@link URI} IDs.
	 *
	 * @param <T> Most common type of the given entities.
	 * @param entities Entities that will be persisted.
	 * @throws DataAccessException Thrown in case of errors while writing
	 * 		entities.
	 */
	<T extends Entity> void create(Collection<T> entities) throws DataAccessException;

	/**
	 * Updates given entities.
	 *
	 * @param <T> Most common type of the given entities.
	 * @param entities Entities that will be updated.
	 * @throws DataAccessException Thrown in case of errors while writing
	 * 		entities.
	 */
	<T extends Entity> void update(Collection<T> entities) throws DataAccessException;

	/**
	 * Deletes given entities. Related children will be searched and automatically removed.
	 *
	 * @param <T> Most common type of the given entities.
	 * @param entities Entities that will be deleted (including their children).
	 * @return {@code List} contains {@link URI}s of all deleted entities.
	 * @throws DataAccessException Thrown in case of errors while deleting
	 * 		entities.
	 */
	<T extends Deletable> List<URI> delete(Collection<T> entities) throws DataAccessException;

	/**
	 * Creates {@link MeasuredValues} as specified by the given {@link WriteRequest}s.
	 *
	 * @param writeRequests Provides all required informations to process the request.
	 * @throws DataAccessException Thrown if unable to create specified measured values.
	 */
	void writeMeasuredValues(Collection<WriteRequest> writeRequests) throws DataAccessException;

	/**
	 * Commit this transaction. Any changes made within this transaction become
	 * permanent.
	 *
	 * @throws DataAccessException Thrown if unable to commit this transaction.
	 */
	void commit() throws DataAccessException;

	/**
	 * Aborts (rollback) this transaction. Any changes made within this
	 * transaction will be lost.
	 */
	void abort();

}
