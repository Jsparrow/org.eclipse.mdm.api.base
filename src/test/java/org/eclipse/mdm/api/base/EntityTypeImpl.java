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


package org.eclipse.mdm.api.base;

import java.util.List;

import org.eclipse.mdm.api.base.AttributeImpl.Type;
import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.adapter.Relation;
import org.eclipse.mdm.api.base.adapter.RelationType;

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
	public List<Relation> getRelations(RelationType relationType) {
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
