/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the context component data item type. Instances of this
 * class are only provided / managed via the descriptive {@link ContextRoot}
 * data item.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ContextRoot
 */
public final class ContextComponent extends BaseDataItem implements Deletable, Derived {

	// ======================================================================
	// Constructors
	// ======================================================================

	public ContextComponent(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	public List<ContextSensor> getContextSensors() {
		return getCore().getChildren(ContextSensor.class);
	}

	public boolean removeContextSensor(ContextSensor contextSensor) {
		return getCore().removeChild(contextSensor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String prefix = new StringBuilder(getCore().getTypeName()).append('(').toString();
		return getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ", prefix, ")"));
	}

}
