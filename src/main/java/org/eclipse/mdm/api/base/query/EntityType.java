/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Test;
import org.eclipse.mdm.api.base.model.Value;

/**
 * Represents a modeled entity type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Attribute
 * @see Relation
 * @see Relationship
 */
public interface EntityType {

	// ======================================================================
	// Public methods
	// ======================================================================

	// TODO JDoc define a proper name
	// this is the name of the data source and not the name of this entity type!
	String getSourceName();

	/**
	 * Returns the name of this entity type.
	 *
	 * @return The name is returned.
	 */
	String getName();

	/**
	 * Returns all {@link Attribute}s of this entity type.
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
	 * Returns the {@link Attribute} associated with passed name.
	 *
	 * @param name The {@code Attribute} identifier.
	 * @return The associated {@code Attribute} is returned.
	 * @throws IllegalArgumentException Thrown if an {@code Attribute} with
	 * 		passed name does not exist.
	 */
	Attribute getAttribute(String name);

	/**
	 * Returns all {@link Relation}s to other entity types.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getRelations();

	/**
	 * Returns the {@link Relation} to a parent entity type whose relationship
	 * is {@link Relationship#FATHER_CHILD}.
	 *
	 * <p><b>Note:</b> Due to the extensibility of the application model such
	 * a usually mandatory relation may exist only within the extended model
	 * (e.g.: parent relation of a {@link Test}).
	 *
	 * @return {@code Optional} is empty if the {@code Relation} does not exist.
	 */
	Optional<Relation> getParentRelation();

	/**
	 * Returns all available child {@link Relation}s whose relationship is
	 * {@link Relationship#FATHER_CHILD}.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getChildRelations();

	/**
	 * Returns all child {@link Relation}s whose relationship is
	 * {@link Relationship#INFO}.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getInfoRelations();

	/**
	 * Returns all {@link Relation}s whose relationship is of the passed type.
	 *
	 * @param relationship The relationship type.
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getRelations(Relationship relationship);

	/**
	 * Returns the unambiguous {@link Relation} to given target entity type.
	 *
	 * @param target Used as identifier.
	 * @return The associated {@code Relation} is returned.
	 * @throws IllegalArgumentException Thrown if a relation to given target
	 * 		entity type does not exist or is ambiguous.
	 * @see #getRelation(EntityType, String)
	 */
	Relation getRelation(EntityType target);

	/**
	 * Returns the {@link Relation} with given target entity type and relation
	 * name.
	 *
	 * @param target The target entity type of the requested {@code Relation}.
	 * @param name The name of the requested {@code Relation}.
	 * @return The associated {@code Relation} is returned.
	 * @throws IllegalArgumentException Thrown if {@code Relation} with given
	 * 		target entity type and name does not exist.
	 * @see #getRelation(EntityType)
	 */
	Relation getRelation(EntityType target, String name);

	/**
	 * Creates for each {@link Attribute} a corresponding empty {@link Value} and
	 * returns them mapped by their name.
	 *
	 * @return Returns created empty {@code Value}s mapped by their name.
	 */
	default Map<String, Value> createValues() {
		return getAttributes().stream().map(Attribute::createValue)
				.collect(Collectors.toMap(Value::getName, Function.identity()));
	}

}