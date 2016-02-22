/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.model.ScalarType;

abstract class MeasuredValuesBuilder {

	protected final WriteRequest writeRequest;

	public MeasuredValuesBuilder(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	protected void createMeasuredValues(ScalarType scalarType, Object values) {
		writeRequest.setMeasuredValues(createMeasuredValues(scalarType, writeRequest.getChannel(), values));
	}

	private MeasuredValues createMeasuredValues(ScalarType scalarType, Channel channel, Object values) {
		return scalarType.createMeasuredValues(channel.getName(), channel.getUnit().getName(), values);
	}

	protected void createMeasuredValues(ScalarType scalarType, Object values, boolean[] flags) {
		writeRequest.setMeasuredValues(createMeasuredValues(scalarType, writeRequest.getChannel(), values, flags));
	}

	private MeasuredValues createMeasuredValues(ScalarType scalarType, Channel channel, Object values, boolean[] flags) {
		return scalarType.createMeasuredValues(channel.getName(), channel.getUnit().getName(), values, flags);
	}

}
