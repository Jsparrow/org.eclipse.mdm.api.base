package org.eclipse.mdm.api.base.notification;

import java.util.List;

import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.User;
import org.eclipse.mdm.api.base.query.EntityType;

/**
 * Listener interface for notifications.
 * 
 * @since 1.0.0
 * @author Matthias Koller, Peak Solution GmbH
 */
public interface NotificationListener {
	  /**
	   * Called when a new instance element was created by the server.
	   * 
	   * @param entities A list of newly created entities.
	   * @param user The user who created the entities.
	   */
	  void instanceCreated(List<? extends Entity> entities, User user);
	  
	  /**
	   * Called when a existing instance element was modified by the server.
	   * 
	   * @param entities A list of modified entities.
	   * @param user The user who modified the entities.
	   */
	  void instanceModified(List<? extends Entity> entities, User user);
	  
	  /**
	   * Called when a existing instance element was deleted by the server.
	   * 
	   * @param entities A list of deleted entities.
	   * @param user The user who deleted the entities.
	   */
	  void instanceDeleted(List<? extends Entity> entities, User user);
	  
	  /**
	   * Called when the application model is changed by the server
	   * 
	   * @param entityType Modified entityType.
	   * @param user The user who modified the application model.
	   */
	  void modelModified(EntityType entityType, User user);
	  
	  /**
	   * Called when security related information is changed by the server
	   * 
	   * @param entities The entities related to the change. Empty if change was only relevant for entityType.
	   * @param user The user who modified security information.
>>>>>>> af19c06 Added javadoc comments to notification interfaces
	   */
	  void securityModified(List<? extends Entity> entities, User user);
}
