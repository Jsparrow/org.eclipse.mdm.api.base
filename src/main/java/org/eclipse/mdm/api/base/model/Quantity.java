/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import org.eclipse.mdm.api.base.adapter.Core;

/**
 * Implementation of the quantity entity type. {@link Channel}s are based on
 * entities of this type. Each quantity has a relation to a default
 * {@link Unit}.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class Quantity extends BaseEntity implements Datable, Deletable, Describable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'DefaultRank' attribute name.
	 */
	public static final String ATTR_DEFAULT_RANK = "DefaultRank";

	/**
	 * The 'DefaultDimension' attribute name.
	 */
	public static final String ATTR_DEFAULT_DIMENSION = "DefDimension";

	/**
	 * The 'DefaultTypeSize' attribute name.
	 */
	public static final String ATTR_DEFAULT_TYPE_SIZE = "DefTypeSize";

	/**
	 * The 'DefaultChannelName' attribute name.
	 */
	public static final String ATTR_DEFAULT_CHANNEL_NAME = "DefMQName";

	/**
	 * The 'DefaultScalarType' attribute name.
	 */
	public static final String ATTR_DEFAULT_SCALAR_TYPE = "DefDataType";

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	Quantity(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the default rank of this quantity.
	 *
	 * @return The default rank is returned.
	 */
	public Integer getDefaultRank() {
		return getValue(ATTR_DEFAULT_RANK).extract();
	}

	/**
	 * Sets new default rank for this quantity.
	 *
	 * @param defaultRank
	 *            The new default rank.
	 */
	public void setDefaultRank(Integer defaultRank) {
		getValue(ATTR_DEFAULT_RANK).set(defaultRank);
	}

	/**
	 * Returns the default dimension of this quantity.
	 *
	 * @return The default dimension is returned.
	 */
	public int[] getDefaultDimension() {
		return getValue(ATTR_DEFAULT_DIMENSION).extract();
	}

	/**
	 * Sets new default dimension for this quantity.
	 *
	 * @param defaultDimension
	 *            The new default dimension.
	 */
	public void setDefaultDimension(int[] defaultDimension) {
		getValue(ATTR_DEFAULT_DIMENSION).set(defaultDimension);
	}

	/**
	 * Returns the default type size of this quantity.
	 *
	 * @return The default type size is returned.
	 */
	public Integer getDefaultTypeSize() {
		return getValue(ATTR_DEFAULT_TYPE_SIZE).extract();
	}

	/**
	 * Sets new default type size for this quantity.
	 *
	 * @param defaultTypeSize
	 *            The new default type size.
	 */
	public void setDefaultTypeSize(Integer defaultTypeSize) {
		getValue(ATTR_DEFAULT_TYPE_SIZE).set(defaultTypeSize);
	}

	/**
	 * Returns the default {@link Channel} name of this quantity.
	 *
	 * @return The default {@code Channel} name is returned.
	 */
	public String getDefaultChannelName() {
		return getValue(ATTR_DEFAULT_CHANNEL_NAME).extract();
	}

	/**
	 * Sets new default {@link Channel} name for this quantity.
	 *
	 * @param defaultChannelName
	 *            The new default {@code Channel} name.
	 */
	public void setDefaultChannelName(String defaultChannelName) {
		getValue(ATTR_DEFAULT_CHANNEL_NAME).set(defaultChannelName);
	}

	/**
	 * Returns the default {@link ScalarType} of this quantity.
	 *
	 * @return The default {@code ScalarType} is returned.
	 */
	public ScalarType getDefaultScalarType() {
		return getValue(ATTR_DEFAULT_SCALAR_TYPE).extract();
	}

	/**
	 * Sets new default {@link ScalarType} for this quantity.
	 *
	 * @param defaultScalarType
	 *            The new default {@code ScalarType}.
	 * @throws IllegalArgumentException
	 *             Thrown if given {@code ScalarType} is
	 *             {@link ScalarType#UNKNOWN}.
	 */
	public void setDefaultScalarType(ScalarType defaultScalarType) {
		if (defaultScalarType.isUnknown()) {
			throw new IllegalArgumentException(
					"Default scalar type constant is not allowed to be '" + defaultScalarType + "'.");
		}

		getValue(ATTR_DEFAULT_SCALAR_TYPE).set(defaultScalarType);
	}

	/**
	 * Returns the default {@link Unit} of this quantity.
	 *
	 * @return The default {@code Unit} is returned.
	 */
	public Unit getDefaultUnit() {
		return getCore().getMutableStore().get(Unit.class);
	}

	/**
	 * Sets new default {@link Unit} for this quantity.
	 *
	 * @param defaultUnit
	 *            The new default {@code Unit}.
	 */
	public void setDefaultUnit(Unit defaultUnit) {
		getCore().getMutableStore().set(defaultUnit);
	}

}
