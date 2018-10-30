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

package org.eclipse.mdm.api.base.query;

/**
 * The {@link QueryService} provides access to the low level query API.
 * 
 * A {@link Query}, created by this service, can be used to build and execute 
 * queries on the underlying datastore. The results are returned as a list 
 * of {@link Result}s.
 *
 * @since 1.0.0
 */
public interface QueryService {
	
	/**
	 * Creates a new and empty {@link Query}.
	 *
	 * @return Created {@code Query} is returned.
	 */
	Query createQuery();
}
