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

/**
 * This class provides a terminal {@link WriteRequest} build operation to
 * retrieve the configured {@code WriteRequest}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class WriteRequestFinalizer {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final WriteRequest writeRequest;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest
	 *            The configured {@link WriteRequest}.
	 */
	WriteRequestFinalizer(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the configured {@link WriteRequest}.
	 *
	 * @return The configured {@code WriteRequest} is returned.
	 */
	public final WriteRequest build() {
		return getWriteRequest();
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	/**
	 * Returns the {@link WriteRequest}.
	 *
	 * @return The {@code WriteRequest} is returned.
	 */
	protected final WriteRequest getWriteRequest() {
		return writeRequest;
	}

}
