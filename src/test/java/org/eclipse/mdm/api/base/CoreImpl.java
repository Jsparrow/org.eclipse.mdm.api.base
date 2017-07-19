/*
 * Copyright (c) 2017 science + computing ag Tuebingen
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.Collection;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Core;
import org.eclipse.mdm.api.base.model.Value;

/**
 * 
 * Very basic implementation of the Core class. The contents is initialized via
 * a given map (instead of using a database or some other method)
 * 
 * @author Florian Schmitt
 */
public class CoreImpl implements Core {

	private Map<String, Value> values;
	private EntityStore mutableStore;

	public CoreImpl(Map<String, Value> values) {
		super();
		this.values = values;
		this.mutableStore = new EntityStore();
	}

	@Override
	public String getSourceName() {
		return "UnitTestSource";
	}

	@Override
	public String getTypeName() {
		return "UnitTestType";
	}

	@Override
	public String getID() {
		return "4711l";
	}

	@Override
	public void setID(String instanceID) {

	}

	@Override
	public Map<String, Value> getValues() {
		return values;
	}

	@Override
	public void hideValues(Collection<String> names) {

	}

	@Override
	public Map<String, Value> getAllValues() {
		return values;
	}

	@Override
	public EntityStore getMutableStore() {
		return mutableStore;
	}

	@Override
	public EntityStore getPermanentStore() {
		return new EntityStore();
	}

	@Override
	public ChildrenStore getChildrenStore() {
		return new ChildrenStore();
	}

}