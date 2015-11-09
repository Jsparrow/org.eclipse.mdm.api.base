/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

public final class MimeType {
	
	private final String mimeType;
	
	public MimeType(String mimeType) {
		this.mimeType = mimeType.toLowerCase();
	}	
	
	public boolean isParentOf(MimeType mimeType) {
		return mimeType == null ? false : mimeType.compareString().startsWith(compareString());
	}	
	
	public boolean equals(Object object) {
		if(object == null || !(object instanceof MimeType)) {
			return false;
		}
		return mimeType.equals(((MimeType) object).mimeType);
	}
	
	@Override
	public String toString() {
		return mimeType;
	}
		
	private String compareString() {
		return mimeType + '.';
	}
	
}
