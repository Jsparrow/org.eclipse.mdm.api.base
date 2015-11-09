/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import org.eclipse.mdm.api.base.model.Value;
import org.eclipse.mdm.api.base.model.ValueType;

public interface Attribute {

	Entity getEntity();

	String getName();
	
	@Deprecated
	ValueType getType();
	
	/**
	 * TODO: When generating query conditions some operations (INSET, BETWEEN, etc.)
	 * require a sequence value therefore it would be useful if a sequence flag 
	 * could be passed to each of the listed create methods... 
	 * Proper handling is then realized in the implementations of this interface!
	 */
	
	Value create(Object value /** TODO sequence! */);
	
	Value create(String unit, Object value /** TODO sequence! */);
	
}
