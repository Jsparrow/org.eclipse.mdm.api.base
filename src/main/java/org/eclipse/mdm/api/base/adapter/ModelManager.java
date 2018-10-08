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


package org.eclipse.mdm.api.base.adapter;

import java.util.List;

import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.query.Query;

/**
 * Provides access to any modeled {@link EntityType} within an underlying
 * application model. A {@link Query}, created by this service, uses these
 * {@code EntityType}s to access corresponding records.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface ModelManager {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns all available {@link EntityType}s.
	 *
	 * @return The returned {@code List} is unmodifiable.
	 */
	List<EntityType> listEntityTypes();

	/**
	 * Returns the {@link EntityType} associated with given {@link Entity}.
	 *
	 * @param entity
	 *            Its type name is used as identifier.
	 * @return {@code EntityType} associated with given entity is returned.
	 */
	default EntityType getEntityType(Entity entity) {
		return getEntityType(entity.getTypeName());
	}

	/**
	 * Returns the {@link EntityType} associated with given entity class type.
	 *
	 * @param entityClass
	 *            Used as identifier.
	 * @return {@code EntityType} associated with given entity class is
	 *         returned.
	 * @throws IllegalArgumentException
	 *             Thrown if {@code EntityType} for given type does not exist.
	 */
	EntityType getEntityType(Class<? extends Entity> entityClass);

	/**
	 * Returns the {@link EntityType} associated with given entity class and
	 * {@link ContextType}.
	 *
	 * @param entityClass
	 *            Used as identifier.
	 * @param contextType
	 *            Used as identifier.
	 * @return {@code EntityType} associated with given entity class and {@code
	 * 		ContextType} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if {@code EntityType} for given type does not exist.
	 */
	EntityType getEntityType(Class<? extends Entity> entityClass, ContextType contextType);

	/**
	 * Returns the {@link EntityType} identified by given name.
	 *
	 * @param name
	 *            Used as identifier.
	 * @return {@code EntityType} with given name is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if {@code EntityType} with given name does not exist.
	 */
	EntityType getEntityType(String name);

	/**
	 * Returns the {@link EntityType} identified by given ID.
	 *
	 * @param id
	 *            Used as ID.
	 * @return {@code EntityType} with given ID is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if {@code EntityType} with given ID does not exist.
	 */
	EntityType getEntityTypeById(String id);
}
