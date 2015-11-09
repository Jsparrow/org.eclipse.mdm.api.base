/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

import org.eclipse.mdm.api.base.marker.Deletable;

public final class PhysicalDimension extends AbstractDataItem implements Describable, Deletable {

	public static final String ATTR_LENGTH = "Length";
	public static final String ATTR_MASS = "Mass";
	public static final String ATTR_TIME = "Time";
	public static final String ATTR_CURRENT = "Current";
	public static final String ATTR_TEMPERATURE = "Temperature";
	public static final String ATTR_MOLAR_AMOUNT = "MolarAmount";
	public static final String ATTR_LUMINOUS_INTENSITY = "LuminousIntensity";
	public static final String ATTR_ANGLE = "angle";
	
	private PhysicalDimension(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	@Override
	public String getDescription() {
		return super.getValue(ATTR_DESCRIPTION).getValue();
	}

	@Override
	public void setDescription(String description) {
		super.getValue(ATTR_DESCRIPTION).setValue(description);		
	}
	
	public Integer getLengthExponent() {
		return super.getValue(ATTR_LENGTH).getValue();
	}
	
	public void setLengthExponent(Integer lengthExp) {
		super.getValue(ATTR_LENGTH).setValue(lengthExp);
	}
	
	public Integer getMassExponent() {
		return super.getValue(ATTR_MASS).getValue();
	}
	
	public void setMassExponent(Integer massExp) {
		super.getValue(ATTR_MASS).setValue(massExp);
	}
	
	public Integer getTimeExponent() {
		return super.getValue(ATTR_TIME).getValue();
	}
	
	public void setTimeExponent(Integer timeExp) {
		super.getValue(ATTR_TIME).setValue(timeExp);
	}	
	
	public Integer getCurrentExponent() {
		return super.getValue(ATTR_CURRENT).getValue();
	}
	
	public void setCurrentExponent(Integer currentExp) {
		super.getValue(ATTR_CURRENT).setValue(currentExp);
	}
	
	public Integer getTemperatureExponent() {
		return super.getValue(ATTR_TEMPERATURE).getValue();
	}
	
	public void setTemperatureExponent(Integer temperatureExp) {
		super.getValue(ATTR_TEMPERATURE).setValue(temperatureExp);
	}
	
	public Integer getMolarAmountExponent() {
		return super.getValue(ATTR_MOLAR_AMOUNT).getValue();
	}
	
	public void setMolarAmountExponent(Integer molarAmountExp) {
		super.getValue(ATTR_MOLAR_AMOUNT).setValue(molarAmountExp);
	}
	
	public Integer getLuminousIntensityExponent() {
		return super.getValue(ATTR_LUMINOUS_INTENSITY).getValue();
	}
	
	public void setLuminousIntensityExponent(Integer luminousIntensityExp) {
		super.getValue(ATTR_LUMINOUS_INTENSITY).setValue(luminousIntensityExp);
	}
	
	public Integer getAngleExponent() {
		return super.getValue(ATTR_ANGLE).getValue();
	}
	
	public void setAngleExponent(Integer angleExp) {
		super.getValue(ATTR_ANGLE).setValue(angleExp);
	}
	
}
