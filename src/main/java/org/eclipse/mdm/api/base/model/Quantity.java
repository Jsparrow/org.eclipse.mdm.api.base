/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Implementation of the quantity data item type. {@link Channel} data items are
 * based on instances of this class.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Unit
 * @see Channel
 */
public final class Quantity extends BaseDataItem implements Copyable, Datable, Deletable, Describable, Versionable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'DefaultRank' attribute name.
	 */
	public static final String ATTR_DEFAULT_RANK = "DefaultRank";

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

	public Quantity(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	public Integer getDefaultRank() {
		return getValue(ATTR_DEFAULT_RANK).extract();
	}

	public void setDefaultRank(Integer defaultRank) {
		getValue(ATTR_DEFAULT_RANK).set(defaultRank);
	}

	public Integer getDefaultTypeSize() {
		return getValue(ATTR_DEFAULT_TYPE_SIZE).extract();
	}

	public void setDefaultTypeSize(Integer defaultTypeSize) {
		getValue(ATTR_DEFAULT_TYPE_SIZE).set(defaultTypeSize);
	}

	public String getDefaultChannelName() {
		return getValue(ATTR_DEFAULT_CHANNEL_NAME).extract();
	}

	public void setDefaultChannelName(String defaultChannelName) {
		getValue(ATTR_DEFAULT_CHANNEL_NAME).set(defaultChannelName);
	}

	public ScalarType getDefaultScalarType() {
		return getValue(ATTR_DEFAULT_SCALAR_TYPE).extract();
	}

	public void setDefaultScalarType(ScalarType scalarType) {
		if(scalarType.isUnknown()) {
			throw new IllegalArgumentException("Scalar type constant is not allowed to be '" + scalarType + "'.");
		}
		getValue(ATTR_DEFAULT_SCALAR_TYPE).set(scalarType);
	}

	public Unit getDefaultUnit() {
		return getCore().getInfoRelation(Unit.class);
	}

	public void setDefaultUnit(Unit defaultUnit) {
		getCore().setInfoRelation(defaultUnit);
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	@Deprecated // TODO this one belongs to the Versionable interface (would be visible to the outer world)
	public void setVersion(Integer version) {
		getValue(ATTR_VERSION).set(version);
	}

}
