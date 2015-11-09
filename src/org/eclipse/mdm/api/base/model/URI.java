/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

public final class URI {

	private final String envName;
	private final String typeName;
	private final long id;
	
	public URI(String envName, String typeName, long id) {
		this.envName = envName;
		this.typeName = typeName;
		this.id = id;		
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getTypeName() {
		return this.typeName;
	}	
	
	public String getEnvironmentName() {
		return this.envName;
	}	
	
	public String toString() {
		StringBuilder sb = new StringBuilder("mdmDataItem://");
		sb.append(this.envName).append('/').append(this.typeName).append('/').append(this.id);
		return sb.toString();
	}

}
