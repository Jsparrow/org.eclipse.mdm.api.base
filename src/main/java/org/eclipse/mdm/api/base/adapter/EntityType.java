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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Value;

/**
 * Represents a modeled entity type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Attribute
 * @see Relation
 * @see RelationType
 */
public interface EntityType {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the name of the data source.
	 *
	 * @return Name of the data source is returned.
	 */
	String getSourceName();

	/**
	 * Returns the name of this entity type.
	 *
	 * @return The name is returned.
	 */
	String getName();

	/**
	 * Returns the ID of this entity type.
	 *
	 * @return The ID is returned.
	 */
	String getId();

	/**
	 * Returns all {@link Attribute}s of this entity type.
	 *
	 * <p>
	 * <b>NOTE:</b> Relation attributes are <i>not</i> part of the returned
	 * {@code List}.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Attribute> getAttributes();

	/**
	 * Returns the ID {@link Attribute} of this entity type.
	 *
	 * @return The ID {@code Attribute} is returned.
	 * @see Entity#ATTR_ID
	 */
	default Attribute getIDAttribute() {
		return getAttribute(Entity.ATTR_ID);
	}

	/**
	 * Returns the name {@link Attribute} of this entity type.
	 *
	 * @return The name {@code Attribute} is returned.
	 * @see Entity#ATTR_NAME
	 */
	default Attribute getNameAttribute() {
		return getAttribute(Entity.ATTR_NAME);
	}

	/**
	 * Returns the mime type {@link Attribute} for this entity type.
	 *
	 * @return The mime type {@code Attribute} is returned.
	 * @see Entity#ATTR_MIMETYPE
	 */
	default Attribute getMimeTypeAttribute() {
		return getAttribute(Entity.ATTR_MIMETYPE);
	}

	/**
	 * Returns either the {@link Attribute} identified by the given name or the
	 * corresponding relation {@code Attribute} (foreign key) as returned by
	 * {@link Relation#getAttribute()}.
	 *
	 * @param name
	 *            The {@code Attribute} identifier.
	 * @return The associated {@code Attribute} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if neither an {@code Attribute} nor a {@link Relation}
	 *             with given name exists.
	 */
	Attribute getAttribute(String name);

	/**
	 * Returns all {@link Relation}s to other entity types.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getRelations();

	/**
	 * Returns all {@link Relation}s to allowed parent entity types.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getParentRelations();

	/**
	 * Returns all available child {@link Relation}s whose relationship is
	 * {@link RelationType#FATHER_CHILD}.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getChildRelations();

	/**
	 * Returns all child {@link Relation}s whose relationship is
	 * {@link RelationType#INFO}.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getInfoRelations();

	/**
	 * Returns all {@link Relation}s whose relationship is of the given type.
	 *
	 * @param relationType
	 *            The relationship type.
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getRelations(RelationType relationType);

	/**
	 * Returns a {@link Relation} to given target entity type. At first it is
	 * tried to find a unambiguous {@code Relation} to given {@code EntityType}.
	 * If this fails it is tried to identify it by the name of the given
	 * {@link EntityType}.
	 *
	 * @param target
	 *            Used as identifier.
	 * @return The associated {@code Relation} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if a relation to given target entity type does not
	 *             exist.
	 */
	Relation getRelation(EntityType target);

	/**
	 * Returns the {@link Relation} with given target entity type and relation
	 * name.
	 *
	 * @param target
	 *            The target entity type of the requested {@code Relation}.
	 * @param name
	 *            The name of the requested {@code Relation}.
	 * @return The associated {@code Relation} is returned.
	 * @throws IllegalArgumentException
	 *             Thrown if {@code Relation} with given target entity type and
	 *             name does not exist.
	 * @see #getRelation(EntityType)
	 */
	Relation getRelation(EntityType target, String name);

	/**
	 * Creates for each {@link Attribute} a corresponding empty {@link Value}
	 * and returns them mapped by their name.
	 *
	 * @return Returns created empty {@code Value}s mapped by their name.
	 */
	default Map<String, Value> createValues() {
		return getAttributes().stream().map(Attribute::createValue)
				.collect(Collectors.toMap(Value::getName, Function.identity()));
	}

	/**
	 * Returns the name of this entity type.
	 *
	 * @return The name of this entity type is returned.
	 */
	@Override
	String toString();

}
