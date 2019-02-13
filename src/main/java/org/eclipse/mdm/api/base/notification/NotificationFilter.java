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

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.mdm.api.base.adapter.EntityType;

/**
 * Class represents a filter for notifications.
 * 
 * @since 1.0.0
 * @author Matthias Koller, Peak Solution GmbH
 */
public class NotificationFilter {

	/**
	 * Modification type enumeration
	 * 
	 * @since 1.0.0
	 * @author Matthias Koller, Peak Solution GmbH
	 */
	public static enum ModificationType {
		INSTANCE_CREATED, INSTANCE_MODIFIED, INSTANCE_DELETED, MODEL_MODIFIED, SECURITY_MODIFIED;
	}

	private EnumSet<ModificationType> types = EnumSet.allOf(ModificationType.class);

	private Set<EntityType> entityTypes = Collections.emptySet();

	/**
	 * Returns the {@link ModificationType}s of this filter.
	 * 
	 * @return Set with {@link ModificationType} is returned.
	 */
	public Set<ModificationType> getTypes() {
		return types;
	}

	/**
	 * Sets the {@link ModificationType}s used to filter the notifications.
	 * 
	 * @param types
	 *            Set with {@link ModificationType}
	 */
	public void setTypes(Set<ModificationType> types) {
		this.types = EnumSet.copyOf(types);
	}

	/**
	 * Returns the {@link EntityType}s of this filter.
	 * 
	 * @return Set with {@link EntityType} is returned.
	 */
	public Set<EntityType> getEntityTypes() {
		return entityTypes;
	}

	/**
	 * Sets the {@link EntityType}s used to filter the notifications.
	 * 
	 * @param entityTypes
	 *            Set with {@link EntityType}.
	 */
	public void setEntityTypes(Set<EntityType> entityTypes) {
		this.entityTypes = new HashSet<>(entityTypes);
	}
}
