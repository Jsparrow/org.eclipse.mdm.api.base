/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

public enum Operation {

	EQ, CI_EQ, NEQ, CI_NEQ, 
	LT, CI_LT, LTE, CI_LTE,
	GT, CI_GT, GTE, CI_GTE, 
	INSET(true), CI_INSET(true), NOT_INSET(true), CI_NOT_INSET(true), 
	LIKE, CI_LIKE, NOT_LIKE, CI_NOT_LIKE,
	IS_NULL, IS_NOT_NULL, BETWEEN(true);
	
	/**
	 * TODO: When generating query conditions some operations (INSET, BETWEEN, etc.)
	 * require a sequence value therefore it would be useful if a sequence flag 
	 * could be passed to each of the listed create methods... 
	 * Proper handling is then realized in the implementations of this interface!
	 */
	private boolean sequence;
	
	private Operation(boolean sequence) {
		this.sequence = sequence;
	}
	
	private Operation() {
		sequence = false;
	}
	
	public Condition create(Attribute attribute, Object value) {
		return create(attribute, "", value);
	}
	
	public Condition create(Attribute attribute, String unit, Object value) {
		return new Condition(attribute, attribute.create(unit, value), this);
	}

}
