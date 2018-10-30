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
 * Implementation of the channel group entity type. It belongs to exactly one
 * {@link Measurement} and groups a set of its {@link Channel}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class ChannelGroup extends BaseEntity implements Deletable {

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
	 * @param core
	 *            The {@link Core}.
	 */
	ChannelGroup(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the number of measured values of each related {@link Channel}.
	 *
	 * @return The number of measured values per {@code Channel} is returned.
	 */
	public Integer getNumberOfValues() {
		return getValue(ATTR_NUMBER_OF_VALUES).extract();
	}

	/**
	 * Sets new number of values for this channel group.
	 *
	 * @param numberOfValues
	 *            The new number of values.
	 */
	public void setNumberOfValues(Integer numberOfValues) {
		getValue(ATTR_NUMBER_OF_VALUES).set(numberOfValues);
	}

}
