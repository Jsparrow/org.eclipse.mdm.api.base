/*
 * Copyright (c) 2017-2018 Peak Solution GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.notification;

import java.util.List;

import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.User;

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
	 * @param entities
	 *            A list of newly created entities.
	 * @param user
	 *            The user who created the entities.
	 */
	void instanceCreated(List<? extends Entity> entities, User user);

	/**
	 * Called when a existing instance element was modified by the server.
	 * 
	 * @param entities
	 *            A list of modified entities.
	 * @param user
	 *            The user who modified the entities.
	 */
	void instanceModified(List<? extends Entity> entities, User user);

	/**
	 * Called when a existing instance element was deleted by the server.
	 * 
	 * @param entityType
	 *            EntityType of the deleted entities.
	 * @param entities
	 *            A list with the IDs of deleted entities.
	 * @param user
	 *            The user who deleted the entities.
	 */
	void instanceDeleted(EntityType entityType, List<String> ids, User user);

	/**
	 * Called when the application model is changed by the server
	 * 
	 * @param entityType
	 *            Modified entityType.
	 * @param user
	 *            The user who modified the application model.
	 */
	void modelModified(EntityType entityType, User user);

	/**
	 * Called when security related information is changed by the server
	 * 
	 * @param entityType
	 *            EntityType with changed security
	 * @param entities
	 *            A list with the IDs of entities related to the change. Empty
	 *            if change was only relevant for entityType.
	 * @param user
	 *            The user who modified security information.
	 */
	void securityModified(EntityType entityType, List<String> ids, User user);
}
