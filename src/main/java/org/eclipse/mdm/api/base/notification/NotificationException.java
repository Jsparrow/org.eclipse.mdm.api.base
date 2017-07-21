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
