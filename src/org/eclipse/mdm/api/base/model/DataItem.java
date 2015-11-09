/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

public interface DataItem {

	static final String ATTR_MIMETYPE = "MimeType";
	static final String ATTR_NAME = "Name";
	static final String ATTR_ID = "Id";
	
	URI getURI();
	
	String getName();
	
	void setName(String name);
	
	MimeType getMimeType();
	
	void setMimeType(MimeType mimeType);
	
	Value getValue(String name);
	
	Map<String, Value> getValues();	
	
}
