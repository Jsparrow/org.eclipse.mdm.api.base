/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.Value;

public final class Condition {

	private final Attribute attribute;
	private final Value value;
	private final Operation operation;
	
	Condition(Attribute attribute, Value value, Operation operation) {
		this.attribute = attribute;
		this.value = value;
		this.operation = operation;
	}
	
	public Attribute getAttribute() {
		return attribute;
	}
	
	public Value getValue() {
		return value;
	}
	
	public Operation getOperation() {
		return operation;
	}
	
}
