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
import org.eclipse.mdm.api.base.model.Unit;

/**
 * This builder allows to specify a source {@link Unit} which is different to
 * the origin {@code Unit} of the underlying {@link Channel}. In Addition to
 * that this builder allows to configure numerically sortable measured values to
 * be independent, which means the measured values do not depend on those of
 * other {@link Channel}s within their common {@link ChannelGroup}. The
 * numerically sortable types are listed below:
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
public final class UnitIndependentBuilder extends IndependentBuilder {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest
	 *            The {@link WriteRequest} whose measured values source
	 *            {@link Unit} may be changed or marked as independent.
	 */
	UnitIndependentBuilder(WriteRequest writeRequest) {
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
	 * @return The {@link IndependentBuilder} is returned.
	 */
	public IndependentBuilder sourceUnit(Unit sourceUnit) {
		getWriteRequest().setSourceUnit(sourceUnit);
		return this;
	}

}
