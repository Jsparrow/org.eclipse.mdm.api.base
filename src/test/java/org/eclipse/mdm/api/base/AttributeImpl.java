/*
 * Copyright (c) 2017 science + computing ag Tuebingen
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import org.eclipse.mdm.api.base.adapter.Attribute;
import org.eclipse.mdm.api.base.adapter.EntityType;
import org.eclipse.mdm.api.base.model.Enumeration;
import org.eclipse.mdm.api.base.model.ValueType;

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
	public Enumeration<?> getEnumObj() {
		throw new UnsupportedOperationException();
	}
}
