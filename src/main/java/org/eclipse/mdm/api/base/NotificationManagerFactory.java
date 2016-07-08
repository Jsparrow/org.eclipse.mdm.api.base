package org.eclipse.mdm.api.base;

import java.util.Map;

import org.eclipse.mdm.api.base.model.BaseEntityFactory;
import org.eclipse.mdm.api.base.notification.NotificationManager;

/**
 * Takes a base entity manager and connection parameters and produces a corresponding notification manager.
 *
 * @since 1.0.0
 * @author Matthias Koller, Peak Solution GmbH
 */
public interface NotificationManagerFactory {

	/**
	 * Creates a notification manager based on the given entity manager and parameters
	 *
	 * @param entityManager The entityManager.
	 * @param connectionParameters The connection parameters.
	 * @return The configured NotificationManager is returned.
	 * @throws ConnectionException Thrown if unable to connect to a data source.
	 */
	NotificationManager create(BaseEntityManager<? extends BaseEntityFactory> baseEntityManager, Map<String, String> parameters) throws ConnectionException;
}
