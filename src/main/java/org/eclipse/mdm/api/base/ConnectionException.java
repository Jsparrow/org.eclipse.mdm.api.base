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

/**
 * Thrown to indicate (dis-)connect errors with a data source.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public class ConnectionException extends Exception {

	// ======================================================================
	// Class variables
	// ======================================================================

	private static final long serialVersionUID = -1299685705186484972L;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param message
	 *            The error message.
	 */
	public ConnectionException(String message) {
		super(message);
	}

	/**
	 * Constructor.
	 *
	 * @param message
	 *            The error message.
	 * @param throwable
	 *            The origin cause.
	 */
	public ConnectionException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
