/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

import org.eclipse.mdm.api.base.model.ChannelValuesReadRequest.MatrixSource;

/**
 * Implementation of the channel group data item type. It belongs to exactly
 * one a {@link Measurement} and groups a set of its {@link Channel}s. The
 * length of an associated {@code Channel}'s value sequence is the same as
 * returned by {@link #getNumberOfValues()}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Measurement
 * @see Channel
 */
public final class ChannelGroup extends AbstractDataItem implements Deletable, Derived, MatrixSource {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link Measurement} parent type.
	 */
	public static final Class<Measurement> PARENT_TYPE_MEASUREMENT = Measurement.class;

	/**
	 * The {@link Channel} child type.
	 */
	public static final Class<Channel> CHILD_TYPE_CHANNEL = Channel.class;

	/**
	 * The 'NumberOfValues' attribute name.
	 */
	public static final String ATTR_NUMBER_OF_VALUES = "SubMatrixNoRows";

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
	private ChannelGroup(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the number of values, which is exactly the length of each associated {@link Channel}.
	 *
	 * @return The number of values per {@code Channel} is returned.
	 */
	public Integer getNumberOfValues() {
		return getValue(ATTR_NUMBER_OF_VALUES).extract();
	}

}
