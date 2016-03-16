/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the parameter set entity type. Instances of this class
 * group a set of further describing data stored in {@link Parameter}s.
 * Parameter sets are attached either to a {@link Measurement} or {@link
 * Channel}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ParameterSet extends BaseEntity implements Deletable {

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

	/**
	 * Constructor.
	 *
	 * @param core The {@link EntityCore}.
	 */
	ParameterSet(EntityCore core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns all available {@link Parameter}s related to this parameter set.
	 *
	 * @return The returned {@code List} is unmodifiable.
	 */
	public List<Parameter> getParameters() {
		return Collections.unmodifiableList(getCore().getChildren(Parameter.class));
	}

	/**
	 * Removes given {@link Parameter} from this parameter set.
	 *
	 * @param parameter The {@code Parameter} that will be removed.
	 * @return Returns {@code true} if this parameter set held given {@code
	 * 		Parameter}.
	 */
	public boolean removeParameter(Parameter parameter) {
		return getCore().removeChild(parameter);
	}

	/**
	 * Returns the version of this parameter set.
	 *
	 * @return The version is returned.
	 */
	public String getVersion() {
		return getValue(ATTR_VERSION).extract();
	}

	/**
	 * Sets new version for this parameter set.
	 *
	 * @param version The new version.
	 */
	public void setVersion(String version) {
		getValue(ATTR_VERSION).set(version);
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

}
