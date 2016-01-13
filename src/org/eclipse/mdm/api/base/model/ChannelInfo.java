/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

/**
 * Implementation of the channel info data item type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
@Deprecated
// TODO: relation to Channel (one channel relates to one or more ChannelInfo)
// TODO: relation to ChannelGroup
public final class ChannelInfo extends AbstractDataItem {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'SequenceRepresentation' attribute name.
	 */
	public static final String ATTR_SEQUENCE_REPRESENTATION = "SequenceRepresentation";

	/**
	 * The 'Independent' attribute name.
	 */
	public static final String ATTR_INDEPENDENT = "IndependentFlag";

	/**
	 * The 'AxisType' attribute name.
	 */
	public static final String ATTR_AXIS_TYPE = "axistype";

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
	private ChannelInfo(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the independence flag.
	 *
	 * @return True if this instance is independent.
	 */
	public Boolean isIndependent() {
		return getValue(ATTR_INDEPENDENT).extract();
	}

	/**
	 * Returns the axis type.
	 *
	 * @return Axis type is returned.
	 */
	//TODO: mapping enum values
	public Integer getAxisTypeEnum() {
		return getValue(ATTR_AXIS_TYPE).extract();
	}

	/**
	 * Returns the sequence representation.
	 *
	 * @return Sequence representation is returned.
	 */
	//TODO: mapping enum values
	public Integer getSequenceRepresentationEnum() {
		return getValue(ATTR_SEQUENCE_REPRESENTATION).extract();
	}

}
