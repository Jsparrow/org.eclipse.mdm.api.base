/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

/**
 * Thrown to indicate errors while querying data.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public final class DataAccessException extends Exception {

	// ======================================================================
	// Class variables
	// ======================================================================

	private static final long serialVersionUID = 5024184555274518451L;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param message The error message.
	 */
	public DataAccessException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message The error message.
	 * @param throwable The origin cause.
	 */
	public DataAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
