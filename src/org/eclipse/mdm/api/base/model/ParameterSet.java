/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * To persist NVH describing data, the NVH working group decided to use
 * parameters grouped in sets to store such data. These sets may be attached
 * to {@link Measurement} or {@link Channel} data items. The accurate usage of
 * the naming specification is described in the NVH documentation and depends
 * on the stored type of data.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Measurement
 * @see Channel
 * @see Parameter
 */
public final class ParameterSet extends AbstractDataItem {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Version' attribute name.
	 */
	public static final String ATTR_VERSION = "Version";

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final List<Parameter> parameterList = new ArrayList<Parameter>();

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param values This data item's values.
	 * @param uri The data item identifier.
	 * @param relatedDataItems Related data item instances.
	 */
	private ParameterSet(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/*
	 * TODO: discuss how deletion or creation of parameters should be detected!
	 * The decision will have impact on the associated getter
	 */

	public List<Parameter> getParameters() {
		return parameterList;
	}

	/**
	 * Returns the version as {@code String}.
	 *
	 * @return The version in its {@code String} representation is returned.
	 */
	public String getVersion() {
		return getValue(ATTR_VERSION).extract();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getURI().getTypeName()).append('(');
		sb.append(getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ")));
		return sb.append(", parameters =").append(getParameters()).append(')').toString();
	}

}
