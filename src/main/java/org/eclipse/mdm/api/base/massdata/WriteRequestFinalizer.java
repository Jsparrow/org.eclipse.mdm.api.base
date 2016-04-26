/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
	// Constructors
	// ======================================================================

	protected final WriteRequest writeRequest;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest The configured {@link WriteRequest}.
	 */
	WriteRequestFinalizer(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Returns the configured {@link WriteRequest}.
	 *
	 * @return The configured {@code WriteRequest} is returned.
	 */
	public final WriteRequest build() {
		return writeRequest;
	}

}
