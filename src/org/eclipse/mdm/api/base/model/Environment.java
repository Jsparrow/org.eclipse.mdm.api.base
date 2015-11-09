/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.Map;

public final class Environment implements DataItem {
	
	private final URI uri;
	private final Map<String, Value> values;
	private final Map<String, Value> context;
	
	private Environment(Map<String, Value> values, Map<String, Value> context) {
		this.values = values;
		this.context = context;
		this.uri = new URI(getName(), Environment.class.getSimpleName(), (long)values.remove(ATTR_ID).getValue());
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public String getName() {
		return this.values.get(ATTR_NAME).getValue();
	}

	@Override
	public void setName(String name) {
		this.values.get(ATTR_NAME).setValue(name);
	}

	@Override
	public MimeType getMimeType() {		
		String mimeType = this.values.get(ATTR_MIMETYPE).getValue();
		return new MimeType(mimeType);
	}

	@Override
	public void setMimeType(MimeType mimeType) {
		this.values.get(ATTR_NAME).setValue(mimeType.toString());		
	}

	@Override
	public Value getValue(String name) {
		return this.values.get(name);
	}

	@Override
	public Map<String, Value> getValues() {
		return Collections.unmodifiableMap(this.values);
	}
	
	public Value getContextValue(String name) {
		return getContextValue(name);
	}
	
	public Map<String, Value> getContext() {
		return Collections.unmodifiableMap(this.context);
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
