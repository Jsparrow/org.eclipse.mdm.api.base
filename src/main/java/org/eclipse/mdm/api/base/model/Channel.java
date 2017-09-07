/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Implementation of the channel entity type. Entities of this type are based on
 * {@link Quantity}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Measurement
 * @see ChannelGroup
 * @see ContextSensor
 * @see ParameterSet
 */
public class Channel extends BaseEntity implements Deletable, Describable {

	// TODO Channel may have a relation to a sensor!

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link Measurement} parent type.
	 */
	public static final Class<Measurement> PARENT_TYPE_MEASUREMENT = Measurement.class;

	/**
	 * The {@link ChannelGroup} parent type.
	 */
	public static final Class<ChannelGroup> PARENT_TYPE_CHANNELGROUP = ChannelGroup.class;

	/**
	 * The {@link ParameterSet} child type.
	 */
	public static final Class<ParameterSet> CHILD_TYPE_PARAMETERSET = ParameterSet.class;

	/**
	 * The 'Minimum' attribute name.
	 */
	public static final String ATTR_MINIMUM = "Minimum";

	/**
	 * The 'Maximum' attribute name.
	 */
	public static final String ATTR_MAXIMUM = "Maximum";

	/**
	 * The 'Average' attribute name.
	 */
	public static final String ATTR_AVERAGE = "Average";

	/**
	 * The 'Deviation' attribute name.
	 */
	public static final String ATTR_DEVIATION = "Deviation";

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
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	Channel(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the minimum value of this channel.
	 *
	 * @return The minimum value is returned.
	 */
	public Double getMinimum() {
		return getValue(ATTR_MINIMUM).extract();
	}

	/**
	 * Sets new minimum value for this channel.
	 *
	 * @param minimum
	 *            The new minimum value.
	 */
	public void setMinimum(Double minimum) {
		getValue(ATTR_MINIMUM).set(minimum);
	}

	/**
	 * Returns the maximum value of this channel.
	 *
	 * @return The maximum value is returned.
	 */
	public Double getMaximum() {
		return getValue(ATTR_MAXIMUM).extract();
	}

	/**
	 * Sets new maximum value for this channel.
	 *
	 * @param maximum
	 *            The new maximum value.
	 */
	public void setMaximum(Double maximum) {
		getValue(ATTR_MAXIMUM).set(maximum);
	}

	/**
	 * Returns the average value of this channel.
	 *
	 * @return The average value is returned.
	 */
	public Double getAverage() {
		return getValue(ATTR_AVERAGE).extract();
	}

	/**
	 * Sets new average value for this channel.
	 *
	 * @param average
	 *            The new average value.
	 */
	public void setAverage(Double average) {
		getValue(ATTR_AVERAGE).set(average);
	}

	/**
	 * Returns the deviation value of this channel.
	 *
	 * @return The deviation value is returned.
	 */
	public Double getDeviation() {
		return getValue(ATTR_DEVIATION).extract();
	}

	/**
	 * Sets new deviation value for this channel.
	 *
	 * @param deviation
	 *            The new deviation value.
	 */
	public void setDeviation(Double deviation) {
		getValue(ATTR_DEVIATION).set(deviation);
	}

	/**
	 * Returns the {@link ScalarType} of this channel.
	 *
	 * @return The {@code ScalarType} is returned.
	 */
	public ScalarType getScalarType() {
		return getValue(ATTR_SCALAR_TYPE).extract();
	}

	/**
	 * Sets new {@link ScalarType} for this channel.
	 *
	 * @param scalarType
	 *            The new {@code ScalarType}.
	 * @throws IllegalArgumentException
	 *             Thrown if given {@code ScalarType} is
	 *             {@link ScalarType#UNKNOWN}.
	 */
	public void setScalarType(ScalarType scalarType) {
		if (scalarType.isUnknown()) {
			throw new IllegalArgumentException("Scalar type constant is not allowed to be '" + scalarType + "'.");
		}

		getValue(ATTR_SCALAR_TYPE).set(scalarType);
	}

	/**
	 * Returns the {@link Interpolation} of this channel.
	 *
	 * @return The {@code Interpolation} is returned.
	 */
	public Interpolation getInterpolation() {
		return getValue(ATTR_INTERPOLATION).extract();
	}

	/**
	 * Sets new interpolation for this channel.
	 *
	 * @param interpolation
	 *            The new {@code Interpolation}.
	 */
	public void setInterpolation(Interpolation interpolation) {
		getValue(ATTR_INTERPOLATION).set(interpolation);
	}

	/**
	 * Returns the rank of this channel.
	 *
	 * @return The rank is returned.
	 */
	public Integer getRank() {
		return getValue(ATTR_RANK).extract();
	}

	/**
	 * Sets new rank for this channel.
	 *
	 * @param rank
	 *            The new rank.
	 */
	public void setRank(Integer rank) {
		getValue(ATTR_RANK).set(rank);
	}

	/**
	 * Returns the type size of this channel.
	 *
	 * @return The type size is returned.
	 */
	public Integer getTypeSize() {
		return getValue(ATTR_TYPE_SIZE).extract();
	}

	/**
	 * Sets new type size for this channel.
	 *
	 * @param typeSize
	 *            The new type size.
	 */
	public void setTypeSize(Integer typeSize) {
		getValue(ATTR_TYPE_SIZE).set(typeSize);
	}

	/**
	 * Returns the related {@link Unit}.
	 *
	 * @return Related {@code Unit} is returned.
	 */
	public Unit getUnit() {
		return getCore().getMutableStore().get(Unit.class);
	}

	/**
	 * Returns the related {@link Quantity}.
	 *
	 * @return Related {@code Quantity} is returned.
	 */
	public Quantity getQuantity() {
		return getCore().getMutableStore().get(Quantity.class);
	}

}
