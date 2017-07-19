/*
 * Copyright (c) 2017 science + computing ag Tuebingen
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.List;

import org.eclipse.mdm.api.base.AttributeImpl.Type;
import org.eclipse.mdm.api.base.query.Attribute;
import org.eclipse.mdm.api.base.query.EntityType;
import org.eclipse.mdm.api.base.query.Relation;
import org.eclipse.mdm.api.base.query.Relationship;

public class EntityTypeImpl implements EntityType {

	@Override
	public String getSourceName() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getId() {
		return "0";
	}

	@Override
	public List<Attribute> getAttributes() {
		return null;
	}

	@Override
	public Attribute getIDAttribute() {
		return new AttributeImpl(Type.ID);
	}

	@Override
	public Attribute getAttribute(String name) {
		return null;
	}

	@Override
	public List<Relation> getRelations() {
		return null;
	}

	@Override
	public List<Relation> getParentRelations() {
		return null;
	}

	@Override
	public List<Relation> getChildRelations() {
		return null;
	}

	@Override
	public List<Relation> getInfoRelations() {
		return null;
	}

	@Override
	public List<Relation> getRelations(Relationship relationship) {
		return null;
	}

	@Override
	public Relation getRelation(EntityType target) {
		return null;
	}

	@Override
	public Relation getRelation(EntityType target, String name) {
		return null;
	}

}
