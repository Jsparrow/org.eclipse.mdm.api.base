/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/

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
		super(new StringBuilder().append("The service '").append(clazz).append("' is not implemented!").toString());
	}
}
