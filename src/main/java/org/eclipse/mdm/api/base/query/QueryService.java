/*
 * Copyright (c) 2017 Peak Solution GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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
