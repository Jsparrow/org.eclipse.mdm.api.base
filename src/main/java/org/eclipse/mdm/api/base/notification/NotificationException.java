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


package org.eclipse.mdm.api.base.notification;

/**
 * Thrown to indicate a errors concerning notifications.
 *
 * @since 1.0.0
 * @author Matthias Koller, Peak Solution GmbH
 */
public class NotificationException extends Exception {

	private static final long serialVersionUID = 4011877631559261716L;

	/**
	 * Constructor.
	 *
	 * @param message
	 *            The error message.
	 */
	public NotificationException(String message) {
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
	public NotificationException(String message, Throwable cause) {
		super(message, cause);
	}

}
