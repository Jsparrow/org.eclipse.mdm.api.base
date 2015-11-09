/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public final class ConditionJoiner {

	private Deque<FilterItem> filterItems = new ArrayDeque<>();
	private final FilterItem operatorItem;
	
	private ConditionJoiner(FilterItem operatorConditionItem) {
		this.operatorItem = operatorConditionItem;
	}
	
	public static ConditionJoiner andJoiner() {
		return new ConditionJoiner(FilterItem.AND);
	}
	
	public static ConditionJoiner orJoiner() {
		return new ConditionJoiner(FilterItem.OR);
	}
	
	public ConditionJoiner addAll(List<Condition> conditions) {
		for(Condition condition : conditions) {
			add(condition);
		}
		
		return this;
	}
	
	public ConditionJoiner addAll(Condition... conditions) {
		for(Condition condition : conditions) {
			add(condition);
		}
		
		return this;
	}
	
	public ConditionJoiner add(Condition condition) {
		insertOperator();
		filterItems.addLast(new FilterItem(condition));
		
		return this;
	}
	
	public ConditionJoiner mergeAll(List<ConditionJoiner> joiners) {
		for(ConditionJoiner joiner : joiners) {
			merge(joiner);
		}
		
		return this;
	}
	
	public ConditionJoiner mergeAll(ConditionJoiner... joiners) {
		for(ConditionJoiner joiner : joiners) {
			merge(joiner);
		}
		
		return this;
	}
	
	public ConditionJoiner merge(ConditionJoiner joiner) {
		boolean addBrackets = !joiner.isInverted() || joiner.hasMultiple() && !operatorItem.equals(joiner.operatorItem);
		insertOperator();
		
		if(addBrackets) {
			filterItems.addLast(FilterItem.OPEN);
		}
		
		for(FilterItem filterItem : joiner.filterItems) {
			filterItems.addLast(filterItem);
		}
		
		if(addBrackets) {
			filterItems.addLast(FilterItem.CLOSE);
		}
		
		return this;
	}
	
	public ConditionJoiner invert() {
		if(filterItems.isEmpty()) {
			throw new IllegalStateException("Current set of conditions is empty.");
		}
		
		if(isInverted()) {
			throw new IllegalStateException("Current set of conditions is already inverted.");
		} else if(hasMultiple()) {
			filterItems.addFirst(FilterItem.OPEN);
			filterItems.addLast(FilterItem.CLOSE);
		}
		
		filterItems.addFirst(FilterItem.NOT);
		
		return this;
	}
	
	public Filter getFilter() {
		return new Filter(this);
	}
	
	Collection<FilterItem> getFilterItems() {
		return Collections.unmodifiableCollection(filterItems);
	}
	
	private void insertOperator() {
		if(filterItems.size() > 0) {
			filterItems.addLast(operatorItem);
		}
	}
	
	private boolean isInverted() {
		return filterItems.size() > 0 && Operator.NOT.equals(filterItems.getFirst());
	}
	
	private boolean hasMultiple() {
		return filterItems.size() > 1;
	}
	
}
