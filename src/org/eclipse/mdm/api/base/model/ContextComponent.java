/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

import org.eclipse.mdm.api.base.marker.Deletable;
import org.eclipse.mdm.api.base.marker.Derived;

public final class ContextComponent extends AbstractDataItem implements Deletable, Derived {


	private ContextComponent(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getURI().getTypeName()).append('(');
		for(Value value : getValues().values()) {
			sb.append(value.toString()).append(", ");
		}
		return sb.delete(sb.length()-2, sb.length()).append(')').toString();
	}
	
}
