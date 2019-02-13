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


package org.eclipse.mdm.api.base.massdata;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an read request iterable for comfortable processing of subsequent
 * {@link ReadRequest}s
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ReadRequestIterable implements Iterable<ReadRequest>, Iterator<ReadRequest> {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private ReadRequest readRequest;
	private int count;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param readRequest
	 *            The {@link ReadRequest}.
	 */
	ReadRequestIterable(ReadRequest readRequest) {
		this.readRequest = readRequest;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return count == 0 || readRequest.hasNext();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReadRequest next() {
		if (hasNext()) {
			readRequest = count == 0 ? readRequest : new ReadRequest(readRequest);
			count++;
			return readRequest;
		}

		throw new NoSuchElementException("Subsequent read request is not available.");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<ReadRequest> iterator() {
		return this;
	}

}
