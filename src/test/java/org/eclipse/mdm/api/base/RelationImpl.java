/*
 * Copyright (c) 2017 science + computing ag Tuebingen
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import org.eclipse.mdm.api.base.query.Attribute;
import org.eclipse.mdm.api.base.query.EntityType;
import org.eclipse.mdm.api.base.query.Relation;
import org.eclipse.mdm.api.base.query.Relationship;

public class RelationImpl implements Relation {

	public enum AttributeType {
		ID, UNKNOWN;
	}

	AttributeType type;

	public RelationImpl(AttributeType type) {
		this.type = type;
	}

	@Override
	public String getName() {
		return type.name();
	}

	@Override
	public EntityType getSource() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EntityType getTarget() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Relationship getRelationship() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Attribute getAttribute() {
		switch (type) {
		case ID:
			return new AttributeImpl(AttributeImpl.Type.ID);
		default:
			throw new UnsupportedOperationException();
		}
	}
}
