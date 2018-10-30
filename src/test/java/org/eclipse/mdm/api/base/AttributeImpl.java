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
