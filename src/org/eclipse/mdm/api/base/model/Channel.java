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
 * Implementation of the channel data item type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Measurement
 * @see ChannelGroup
 * @see ContextSensor
 * @see ParameterSet
 */
public final class Channel extends AbstractDataItem implements Deletable, Describable, Parameterizable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link Measurement} parent type.
	 */
	public static final Class<Measurement> PARENT_TYPE_MEASUREMENT = Measurement.class;

	/**
	 * The 'ValueType' attribute name.
	 */
	public static final String ATTR_VALUE_TYPE = "DataType";

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Statistics statistics;

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
	private Channel(Map<String, Value> values, URI uri,	Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
		statistics = new Statistics(values);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the value type.
	 *
	 * @return Value type is returned.
	 */
	public Integer getValueTypeEnum() {
		return getValue(ATTR_VALUE_TYPE).extract(); //TODO: mapping enum values
	}

	/**
	 * Returns the related {@link Unit} data item.
	 *
	 * @return Related {@code Unit} data item is returned.
	 */
	public Unit getUnit() {
		return getRelatedDataItem(Unit.class);
	}

	/**
	 * Returns the related {@link Quantity} data item.
	 *
	 * @return Related {@code Quantity} data item is returned.
	 */
	public Quantity getQuantity() {
		return getRelatedDataItem(Quantity.class);
	}

	/**
	 * Returns the {@link Statistics} of the underlying channel values.
	 *
	 * @return The {@code Statistics} item is returned.
	 */
	public Statistics getStatistics() {
		return statistics;
	}

}
