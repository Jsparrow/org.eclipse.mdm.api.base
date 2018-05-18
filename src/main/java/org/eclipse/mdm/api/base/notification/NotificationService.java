/*
 * Copyright (c) 2017-2018 Peak Solution GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.notification;

/**
 * Manages registrations to a notification service.
 * 
 * @since 1.0.0
 * @author Matthias Koller, Peak Solution GmbH
 */
public interface NotificationService {

	/**
	 * Registers a NotificationListener at the underlying notification service.
	 * 
	 * @param registrationName
	 *            An unique name used for the registration.
	 * @param filter
	 *            A notification filter, specifying which events should be
	 *            received by the listener.
	 * @param listener
	 *            A notification listener which is informed if new events are
	 *            received.
	 * @throws NotificationException
	 *             In case the listener cannot be registered at the notification
	 *             service.
	 */
	void register(String registrationName, NotificationFilter filter, NotificationListener listener)
			throws NotificationException;

	/**
	 * Unregisters a NotificationListener from the underlying notification
	 * service.
	 * 
	 * @param registrationName
	 *            The unique name previously used for the registration.
	 * @throws NotificationException
	 *             In case the listener cannot be deregistered.
	 */
	void deregister(String registrationName) throws NotificationException;

	/**
	 * Closes the NotificationManager and all registered NotificationListeners
	 * 
	 * @param deregisterAll
	 *            if true, all NotificationListeners are also deregistered
	 *            before closing.
	 * @throws NotificationException
	 *             In case the listeners cannot be deregistered or the manager
	 *             cannot be closed.
	 */
	void close(boolean deregisterAll) throws NotificationException;
}
