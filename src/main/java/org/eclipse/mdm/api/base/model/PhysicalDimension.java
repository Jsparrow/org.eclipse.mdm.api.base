/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Implementation of the physical dimension data item type. Each {@link Unit}
 * must have a relation to an instance of this type. The attributes represent
 * the exponents of the seven SI base units and additionally the angle exponent
 * as defined in the ASAM NVH model. A {@code Unit} with a certain physical
 * dimension can be converted to all other units having the same relation.
 * Names of the physical dimensions must be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Unit
 */
public final class PhysicalDimension extends BaseDataItem implements Copyable, Describable, Deletable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Length' attribute name.
	 */
	public static final String ATTR_LENGTH = "Length";

	/**
	 * The 'Mass' attribute name.
	 */
	public static final String ATTR_MASS = "Mass";

	/**
	 * The 'Time' attribute name.
	 */
	public static final String ATTR_TIME = "Time";

	/**
	 * The 'Current' attribute name.
	 */
	public static final String ATTR_CURRENT = "Current";

	/**
	 * The 'Temperature' attribute name.
	 */
	public static final String ATTR_TEMPERATURE = "Temperature";

	/**
	 * The 'MolarAmount' attribute name.
	 */
	public static final String ATTR_MOLAR_AMOUNT = "MolarAmount";

	/**
	 * The 'LuminousIntensity' attribute name.
	 */
	public static final String ATTR_LUMINOUS_INTENSITY = "LuminousIntensity";

	/**
	 * The 'Angle' attribute name.
	 */
	public static final String ATTR_ANGLE = "angle";

	// ======================================================================
	// Constructors
	// ======================================================================

	public PhysicalDimension(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the length exponent of this physical dimension.
	 *
	 * @return The length exponent is returned.
	 */
	public Integer getLength() {
		return getValue(ATTR_LENGTH).extract();
	}

	/**
	 * Sets new length exponent for this physical dimension.
	 *
	 * @param exponent The new length exponent.
	 */
	public void setLength(Integer exponent) {
		getValue(ATTR_LENGTH).set(exponent);
	}

	/**
	 * Returns the mass exponent of this physical dimension.
	 *
	 * @return The mass exponent is returned.
	 */
	public Integer getMass() {
		return getValue(ATTR_MASS).extract();
	}

	/**
	 * Sets new mass exponent for this physical dimension.
	 *
	 * @param exponent The new mass exponent.
	 */
	public void setMass(Integer exponent) {
		getValue(ATTR_MASS).set(exponent);
	}

	/**
	 * Returns the time exponent of this physical dimension.
	 *
	 * @return The time exponent is returned.
	 */
	public Integer getTime() {
		return getValue(ATTR_TIME).extract();
	}

	/**
	 * Sets new time exponent for this physical dimension.
	 *
	 * @param exponent The new time exponent.
	 */
	public void setTime(Integer exponent) {
		getValue(ATTR_TIME).set(exponent);
	}

	/**
	 * Returns the current exponent of this physical dimension.
	 *
	 * @return The current exponent is returned.
	 */
	public Integer getCurrent() {
		return getValue(ATTR_CURRENT).extract();
	}

	/**
	 * Sets new current exponent for this physical dimension.
	 *
	 * @param exponent The new current exponent.
	 */
	public void setCurrent(Integer exponent) {
		getValue(ATTR_CURRENT).set(exponent);
	}

	/**
	 * Returns the temperature exponent of this physical dimension.
	 *
	 * @return The temperature exponent is returned.
	 */
	public Integer getTemperature() {
		return getValue(ATTR_TEMPERATURE).extract();
	}

	/**
	 * Sets new temperature exponent for this physical dimension.
	 *
	 * @param exponent The new temperature exponent.
	 */
	public void setTemperature(Integer exponent) {
		getValue(ATTR_TEMPERATURE).set(exponent);
	}

	/**
	 * Returns the molar amount exponent of this physical dimension.
	 *
	 * @return The molar amount exponent is returned.
	 */
	public Integer getMolarAmount() {
		return getValue(ATTR_MOLAR_AMOUNT).extract();
	}

	/**
	 * Sets new molar amount exponent for this physical dimension.
	 *
	 * @param exponent The new molar amount exponent.
	 */
	public void setMolarAmount(Integer exponent) {
		getValue(ATTR_MOLAR_AMOUNT).set(exponent);
	}

	/**
	 * Returns the luminous intensity exponent of this physical dimension.
	 *
	 * @return The luminous intensity exponent is returned.
	 */
	public Integer getLuminousIntensity() {
		return getValue(ATTR_LUMINOUS_INTENSITY).extract();
	}

	/**
	 * Sets new luminous intensity exponent for this physical dimension.
	 *
	 * @param exponent The new luminous intensity exponent.
	 */
	public void setLuminousIntensity(Integer exponent) {
		getValue(ATTR_LUMINOUS_INTENSITY).set(exponent);
	}

	/**
	 * Returns the angle exponent of this physical dimension.
	 *
	 * @return The angle exponent is returned.
	 */
	public Integer getAngle() {
		return getValue(ATTR_ANGLE).extract();
	}

	/**
	 * Sets new angle exponent for this physical dimension.
	 *
	 * @param exponent The new angle exponent.
	 */
	public void setAngle(Integer exponent) {
		getValue(ATTR_ANGLE).set(exponent);
	}

}
