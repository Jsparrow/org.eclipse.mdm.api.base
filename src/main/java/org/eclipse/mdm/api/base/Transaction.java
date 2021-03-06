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

import java.util.Collection;

import org.eclipse.mdm.api.base.massdata.WriteRequest;
import org.eclipse.mdm.api.base.model.Deletable;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.query.DataAccessException;

/**
 * Provides business layer write operations (CREATE, UPDATE, DELETE).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public interface Transaction {

	// ======================================================================
	// Public methods
	// ======================================================================

	// TODO: it should be possible to a attach a listener
	// -> progress notification updates while uploading files
	// -> any other useful informations?!

	/**
	 * Persists given entities and implicitly updates their instance IDs.
	 *
	 * @param <T>
	 *            Most common type of the given entities.
	 * @param entities
	 *            Entities that will be persisted.
	 * @throws DataAccessException
	 *             Thrown in case of errors while writing entities.
	 */
	<T extends Entity> void create(Collection<T> entities);

	/**
	 * Updates given entities.
	 *
	 * @param <T>
	 *            Most common type of the given entities.
	 * @param entities
	 *            Entities that will be updated.
	 * @throws DataAccessException
	 *             Thrown in case of errors while writing entities.
	 */
	<T extends Entity> void update(Collection<T> entities);

	/**
	 * Deletes given entities. Related children will be searched and
	 * automatically removed.
	 *
	 * @param <T>
	 *            Most common type of the given entities.
	 * @param entities
	 *            Entities that will be deleted (including their children).
	 * @throws DataAccessException
	 *             Thrown in case of errors while deleting entities.
	 */
	<T extends Deletable> void delete(Collection<T> entities);

	/**
	 * Creates {@link MeasuredValues} as specified by the given
	 * {@link WriteRequest}s.
	 *
	 * @param writeRequests
	 *            Provides all required informations to process the request.
	 * @throws DataAccessException
	 *             Thrown if unable to create specified measured values.
	 */
	void writeMeasuredValues(Collection<WriteRequest> writeRequests);

	/**
	 * Commit this transaction. Any changes made within this transaction become
	 * permanent.
	 *
	 * @throws DataAccessException
	 *             Thrown if unable to commit this transaction.
	 */
	void commit();

	/**
	 * Aborts (rollback) this transaction. Any changes made within this
	 * transaction will be lost.
	 */
	void abort();

}
