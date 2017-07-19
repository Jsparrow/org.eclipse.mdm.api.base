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
