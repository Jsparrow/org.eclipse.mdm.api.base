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


package org.eclipse.mdm.api.base.model;

import org.eclipse.mdm.api.base.adapter.Core;

/**
 * Implementation of the context sensor entity types. Instances of this class
 * are only provided / managed via the owning {@link ContextComponent}. A
 * context sensor may be related to a {@link Channel}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class ContextSensor extends BaseEntity implements Deletable {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	ContextSensor(Core core) {
		super(core);
	}

	/**
	 * Returns the {@link ContextComponent} this context sensor belongs to.
	 *
	 * @return The parent {@link ContextComponent}
	 */
	public ContextComponent getContextComponent() {
		return getCore().getPermanentStore().get(ContextComponent.class);
	}
}
