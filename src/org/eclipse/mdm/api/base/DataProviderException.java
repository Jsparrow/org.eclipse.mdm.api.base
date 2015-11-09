/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

public class DataProviderException extends Exception {

	private static final long serialVersionUID = -1299685705186484972L;

	public DataProviderException(String message) {
		super(message);
	}
	
	public DataProviderException(String message, Throwable t) {
		super(message, t);
	}
}
