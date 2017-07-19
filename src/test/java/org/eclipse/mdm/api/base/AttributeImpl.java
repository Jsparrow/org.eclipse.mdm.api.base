package org.eclipse.mdm.api.base;

import org.eclipse.mdm.api.base.model.ValueType;
import org.eclipse.mdm.api.base.query.Attribute;
import org.eclipse.mdm.api.base.query.EntityType;

public class AttributeImpl implements Attribute {

	public enum Type {
		ID, UNKNOWN;
	}

	Type type;

	public AttributeImpl(Type type) {
		this.type = type;
	}

	@Override
	public EntityType getEntityType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return type.name();
	}

	@Override
	public String getUnit() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ValueType getValueType() {
		switch (type) {
		case ID:
			return ValueType.STRING;
		default:
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public Class<? extends Enum<?>> getEnumClass() {
		throw new UnsupportedOperationException();
	}
}
