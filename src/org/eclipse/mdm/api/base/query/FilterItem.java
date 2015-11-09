/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

public final class FilterItem {
	
	static final FilterItem AND = new FilterItem(Operator.AND);
	static final FilterItem OR = new FilterItem(Operator.OR);
	static final FilterItem NOT = new FilterItem(Operator.NOT);
	static final FilterItem OPEN = new FilterItem(Operator.OPEN);
	static final FilterItem CLOSE = new FilterItem(Operator.CLOSE);

	private final Condition condition;
	private final Operator operator;
	
	FilterItem(Condition condition) {
		this.condition = condition;
		this.operator = null;
	}
	
	private FilterItem(Operator operator) {
		this.condition = null;
		this.operator = operator;
	}
	
	public boolean isOperator() {
		return operator != null;
	}
	
	public boolean isCondition() {
		return condition != null;
	}
	
	public Operator getOperator() {
		if(isOperator()) {
			return operator;
		}
		
		throw new IllegalStateException("Item does not contain an operator.");
	}
	
	public Condition getCondition() {
		if(isCondition()) {
			return condition;
		}
		
		throw new IllegalStateException("Item does not contain a condition.");
	}
	
}
