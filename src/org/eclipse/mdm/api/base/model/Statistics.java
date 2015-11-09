/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

public class Statistics {

	private static final String ATTR_MINIMUM = "Minimum";
	private static final String ATTR_MAXIMUM = "Maximum";	
	private static final String ATTR_AVERAGE = "Average";
	private static final String ATTR_DEVIATION = "Deviation";
	
	private final Map<String, Value> values;
	
	public Statistics(Map<String, Value> values) {
		this.values = values;
	}
	
	public Double getMinimum() {	
		return this.values.get(ATTR_MINIMUM).getValue();
	}
	
	public void setMinimum(Double minimum) {
		this.values.get(ATTR_MINIMUM).setValue(minimum);
	}
	
	public Double getMaximum() {	
		return this.values.get(ATTR_MAXIMUM).getValue();
	}
	
	public void setMaximum(Double maximum) {
		this.values.get(ATTR_MAXIMUM).setValue(maximum);
	}
	
	public Double getAverage() {	
		return this.values.get(ATTR_AVERAGE).getValue();
	}
	
	public void setAverage(Double average) {
		this.values.get(ATTR_AVERAGE).setValue(average);
	}
	
	public Double getDeviation() {	
		return this.values.get(ATTR_DEVIATION).getValue();
	}
	
	public void setDeviation(Double deviation) {
		this.values.get(ATTR_DEVIATION).setValue(deviation);
	}
	
}
