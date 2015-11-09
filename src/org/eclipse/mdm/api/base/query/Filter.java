/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.mdm.api.base.utils.ReadOnlyIterator;

public final class Filter implements Iterable<FilterItem> {
	
	private static final Filter EMPTY = new Filter(); 
	
	private final List<FilterItem> filterItems = new ArrayList<>();
	
	Filter(ConditionJoiner joiner) {
		this(joiner.getFilterItems());
	}
	
	private Filter(Collection<FilterItem> conditionItems) {
		this.filterItems.addAll(conditionItems);
	}
	
	public static Filter singleton(Condition condition) {
		return new Filter(Collections.singletonList(new FilterItem(condition)));
	}
	
	public static Filter id(Entity entity, Long id) {
		return singleton(Operation.EQ.create(entity.getIDAttribute(), id));
	}
	
	public static Filter empty() {
		return EMPTY;
	}
	
	private Filter() {}

	@Override
	public Iterator<FilterItem> iterator() {
		return new ReadOnlyIterator<>(filterItems.iterator());
	}
	
}
