package org.eclipse.mdm.api.base.notification;

/**
 * Manages the registrations to a notification service.
 *
 */
public interface NotificationManager {

	/**
	 * Registers a NotificationListener at the underlying notification service.
	 * @param registrationName An unique name used for the registration.
	 * @param filter A notification filter, specifying which events should be received by the listener.
	 * @param listener A notification listener which is informed if new events are received.
	 * @throws NotificationException In case the listener cannot be registered at the notification service.
	 */
	void register(String registrationName, NotificationFilter filter, NotificationListener listener) throws NotificationException;
	
	/**
	 * Unregisters a NotificationListener from the underlying notification service.
	 * @param registrationName The unique name previously used for the registration.
	 */
	void deregister(String registrationName);
}
