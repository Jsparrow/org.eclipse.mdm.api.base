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


package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ChannelGroup;
import org.eclipse.mdm.api.base.model.ScalarType;

/**
 * This builder allows to configure numerically sortable measured values to be
 * independent, which means the measured values do not depend on those of other
 * {@link Channel}s within their common {@link ChannelGroup}. The numerically
 * sortable types are listed below:
 *
 * <ul>
 * <li>{@link ScalarType#DATE}</li>
 * <li>{@link ScalarType#BYTE}</li>
 * <li>{@link ScalarType#SHORT}</li>
 * <li>{@link ScalarType#INTEGER}</li>
 * <li>{@link ScalarType#LONG}</li>
 * <li>{@link ScalarType#FLOAT}</li>
 * <li>{@link ScalarType#DOUBLE}</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class IndependentBuilder extends WriteRequestFinalizer {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest
	 *            The {@link WriteRequest}, whose measured values of the
	 *            underlying {@link Channel} may be marked as independent.
	 */
	IndependentBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Triggers the measured values of the underlying {@link Channel} to be
	 * independent.
	 *
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public final WriteRequestFinalizer independent() {
		getWriteRequest().setIndependent();
		return this;
	}

}
