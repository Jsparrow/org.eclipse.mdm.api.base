/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;
import java.util.Optional;

import org.eclipse.mdm.api.base.model.DataItem;
import org.eclipse.mdm.api.base.model.Test;

/**
 * Represents modeled entity.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Attribute
 * @see Relation
 * @see Relationship
 */
public interface Entity {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the name of this entity.
	 *
	 * @return The name is returned.
	 */
	String getName();

	/**
	 * Returns all {@link Attribute}s of this entity.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Attribute> getAttributes();

	/**
	 * Returns ID {@link Attribute} for this entity.
	 *
	 * @return The ID {@code Attribute} is returned.
	 * @see DataItem#ATTR_ID
	 */
	default Attribute getIDAttribute() {
		return getAttribute(DataItem.ATTR_ID);
	}

	/**
	 * Returns name {@link Attribute} for this entity.
	 *
	 * @return The name {@code Attribute} is returned.
	 * @see DataItem#ATTR_NAME
	 */
	default Attribute getNameAttribute() {
		return getAttribute(DataItem.ATTR_NAME);
	}

	/**
	 * Returns mime type {@link Attribute} for this entity.
	 *
	 * @return The mime type {@code Attribute} is returned.
	 * @see DataItem#ATTR_MIMETYPE
	 */
	default Attribute getMimeTypeAttribute() {
		return getAttribute(DataItem.ATTR_MIMETYPE);
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
	 * Returns all {@link Relation}s to other entities.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getRelations();

	/**
	 * Returns a {@link Relation} to a parent entity whose relationship is
	 * {@link Relationship#FATHER_CHILD}. Due to the extensibility of the
	 * application model such a usually mandatory relation may exist only
	 * within the extended model. Therefore an {@code Optional} is returned
	 * here to cover such gaps (e.g. parent relation of a {@link Test}).
	 *
	 * @return Optional is empty only if the usually mandatory relation does
	 * 		not exist due to the reason described above.
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
	 * {@link Relationship.INFO}.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getInfoRelations();

	/**
	 * Returns all {@link Relation}s whose relationship is of the passed type.
	 *
	 * @return The returned {@code List} may be immutable.
	 */
	List<Relation> getRelations(Relationship relationship);

	/**
	 * Returns the unambiguous {@link Relation} to given target entity.
	 *
	 * @param target Used as identifier.
	 * @return The associated {@code Relation} is returned.
	 * @throws IllegalArgumentException Thrown if a relation to given target
	 * 		entity does not exists or is ambiguous.
	 * @see #getRelation(Entity, String)
	 */
	Relation getRelation(Entity target);

	/**
	 * Returns the {@link Relation} with given target entity and name.
	 *
	 * @param target The target entity of the requested {@code Relation}.
	 * @param name The name of the requested {@code Relation}.
	 * @return The associated {@code Relation} is returned.
	 * @throws IllegalArgumentException Thrown if {@code Relation} with given
	 * 		target target entity and name does not exist.
	 * @see #getRelation(Entity)
	 */
	Relation getRelation(Entity target, String name);

}
