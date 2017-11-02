/*
 * Copyright (c) 2017 Peak Solution GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.mdm.api.base;

/**
 * This exception indicates that a service is not provided by an adapter.
 */
public class ServiceNotProvidedException extends RuntimeException {

	private static final long serialVersionUID = -4829618036958929415L;

	/**
	 * Constructor.
	 *
	 * @param clazz the service that is not provided
	 */
	public ServiceNotProvidedException(Class<?> clazz) {
		super("The service '" + clazz + "' is not implemented!");
	}
}
