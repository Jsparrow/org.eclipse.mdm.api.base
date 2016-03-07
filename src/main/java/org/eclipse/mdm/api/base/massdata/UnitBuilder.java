/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
	 *  @param writeRequest The {@link WriteRequest}, whose measured value's
	 *  	source {@link Unit} may be defined.
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
	 * @param sourceUnit The source {@code Unit}.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer sourceUnit(Unit sourceUnit) {
		writeRequest.setSourceUnit(sourceUnit);
		return this;
	}

}
