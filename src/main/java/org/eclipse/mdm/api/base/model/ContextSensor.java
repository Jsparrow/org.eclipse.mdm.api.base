/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.stream.Collectors;

/**
 * Implementation of the context sensor entity types. Instances of this class
 * are only provided / managed via the owning {@link ContextComponent}. A
 * context sensor may be related to a {@link Channel}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ContextSensor extends BaseEntity implements Deletable, Derived, Sortable<ContextSensor> {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core The {@link EntityCore}.
	 */
	ContextSensor(EntityCore core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String prefix = new StringBuilder(getURI().getTypeName()).append('(').toString();
		return getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ", prefix, ")"));
	}

}
