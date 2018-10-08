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

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.adapter.Relation;
import org.eclipse.mdm.api.base.adapter.RelationType;

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
	public RelationType getRelationType() {
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

	@Override
	public boolean isOutgoing(RelationType relationType) {
		return true;
	}

	@Override
	public boolean isIncoming(RelationType relationType) {
		return false;
	}
}
