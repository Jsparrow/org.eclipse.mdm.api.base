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
 * Implementation of the parameter set data item type. Instances of this class
 * group a set of further describing data stored in parameters. A parameter set
 * is attached either to a {@link Measurement} or {@link Channel} data item.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Measurement
 * @see Channel
 * @see Parameter
 */
public final class ParameterSet extends BaseDataItem {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Version' attribute name.
	 */
	public static final String ATTR_VERSION = "Version";

	// ======================================================================
	// Constructors
	// ======================================================================

	public ParameterSet(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	public List<Parameter> getParameters() {
		return getCore().getChildren(Parameter.class);
	}

	public boolean removeParameter(Parameter parameter) {
		return getCore().removeChild(parameter);
	}

	// TODO
	public String getVersion() {
		return getValue(ATTR_VERSION).extract();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append('(');
		sb.append(getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ")));
		return sb.append(", parameters =").append(getParameters()).append(')').toString();
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	// TODO read only property
	public void setVersion(String version) {
		getValue(ATTR_VERSION).set(version);
	}

}
