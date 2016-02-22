/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Implementation of the channel data item type. Instances of this class are
 * based on {@link Quantity} data items.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Measurement
 * @see ChannelGroup
 * @see ContextSensor
 * @see ParameterSet
 * @see Quantity
 */
public final class Channel extends BaseDataItem implements Deletable, Describable, Parameterizable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link Measurement} parent type.
	 */
	public static final Class<Measurement> PARENT_TYPE_MEASUREMENT = Measurement.class;

	/**
	 * The 'ScalarType' attribute name.
	 */
	public static final String ATTR_SCALAR_TYPE = "DataType";

	/**
	 * The 'Interpolation' attribute name.
	 */
	public static final String ATTR_INTERPOLATION = "Interpolation";

	/**
	 * The 'Rank' attribute name.
	 */
	public static final String ATTR_RANK = "Rank";

	/**
	 * The 'TypeSize' attribute name.
	 */
	public static final String ATTR_TYPE_SIZE = "TypeSize";

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Statistics statistics;

	// ======================================================================
	// Constructors
	// ======================================================================

	public Channel(Core core) {
		super(core);
		statistics = new Statistics(core.getValues());
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the scalar value type.
	 *
	 * @return Scalar value type is returned.
	 */
	public ScalarType getScalarType() {
		return getValue(ATTR_SCALAR_TYPE).extract();
	}

	/**
	 * Sets new scalar type for this channel.
	 *
	 * @param scalarType The new scalar type for this channel.
	 */
	// TODO it might be useful to change the scalar type in post processing ?!
	public void setScalarType(ScalarType scalarType) {
		getValue(ATTR_SCALAR_TYPE).set(scalarType);
	}

	/**
	 * Returns the interpolation type.
	 *
	 * @return Interpolation type is returned.
	 */
	public Interpolation getInterpolation() {
		return getValue(ATTR_INTERPOLATION).extract();
	}

	/**
	 * Returns the rank.
	 *
	 * @return Rank is returned.
	 */
	public Integer getRank() {
		return getValue(ATTR_RANK).extract();
	}

	/**
	 * Returns the type size.
	 *
	 * @return Type size is returned.
	 */
	public Integer getTypeSize() {
		return getValue(ATTR_TYPE_SIZE).extract();
	}

	/**
	 * Returns the related {@link Unit} data item.
	 *
	 * @return Related {@code Unit} data item is returned.
	 */
	public Unit getUnit() {
		return getCore().getInfoRelation(Unit.class);
	}

	/**
	 * Returns the related {@link Quantity} data item.
	 *
	 * @return Related {@code Quantity} data item is returned.
	 */
	public Quantity getQuantity() {
		return getCore().getInfoRelation(Quantity.class);
	}

	/**
	 * Returns the {@link Statistics} of the underlying channel values.
	 *
	 * @return The {@code Statistics} item is returned.
	 */
	public Statistics getStatistics() {
		return statistics;
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Sets new interpolation for this channel.
	 *
	 * @param interpolation The new interpolation type for this channel.
	 */
	// TODO Interpolation was completely hidden in the MDM4 API
	public void setInterpolation(Interpolation interpolation) {
		getValue(ATTR_INTERPOLATION).set(interpolation);
	}

	/**
	 * Sets new rank for this channel.
	 *
	 * @param rank The new rank for this channel.
	 */
	// TODO this is a read only property
	public void setRank(Integer rank) {
		getValue(ATTR_RANK).set(rank);
	}

	/**
	 * Sets new type size for this channel.
	 *
	 * @param typeSize The new type size for this channel.
	 */
	// TODO this is a read only property too
	public void setTypeSize(Integer typeSize) {
		getValue(ATTR_TYPE_SIZE).set(typeSize);
	}

}
