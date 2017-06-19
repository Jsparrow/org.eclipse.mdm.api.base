/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
