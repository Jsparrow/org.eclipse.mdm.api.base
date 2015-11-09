/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

public final class DataAccessException extends Exception { // TODO extend from DataProviderException?!

	private static final long serialVersionUID = 5024184555274518451L;

	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
