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
import org.eclipse.mdm.api.base.model.Unit;

/**
 * This builder allows to specify a source {@link Unit} which is different to
 * the origin {@code Unit} of the underlying {@link Channel}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class UnitBuilder extends WriteRequestFinalizer {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest
	 *            The {@link WriteRequest}, whose measured value's source
	 *            {@link Unit} may be defined.
	 */
	UnitBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Sets a source {@link Unit} for the previously defined measured values.
	 *
	 * @param sourceUnit
	 *            The source {@code Unit}.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer sourceUnit(Unit sourceUnit) {
		getWriteRequest().setSourceUnit(sourceUnit);
		return this;
	}

}
