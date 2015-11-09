/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

public enum ContextType {

	UNITUNDERTEST(false),
	TESTSEQUENCE(false),
	TESTEQUIPMENT(true);
	
	private final boolean areSensorsAllowed;
	
	private ContextType(boolean areSensorsAllowed) {
		this.areSensorsAllowed = areSensorsAllowed;
	}
	
	public boolean areSensoresAllowed() {
		return this.areSensorsAllowed;
	}
	
}
