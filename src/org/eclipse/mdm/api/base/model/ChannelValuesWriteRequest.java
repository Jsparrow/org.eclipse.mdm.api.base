/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import org.eclipse.mdm.api.base.query.DataAccessException;

public class ChannelValuesWriteRequest {

	public static final String VALUES_VALUE_NAME = "Values";

	private final Channel channel;
	private final ChannelGroup channelGroup;

	private final AxisType axisType;
	private final Representation representation;
	private final boolean independent;
	private final double[] parameters;

	private final boolean globalFlag;
	private final ChannelValue channelValue;

	private ChannelValuesWriteRequest(Channel channel, ChannelGroup channelGroup, ChannelValue channelValue,
			boolean globalFlag,	AxisType axisType, Representation representation, boolean independent, double[] parameters)
					throws DataAccessException {

		this.channel = channel;
		this.channelGroup = channelGroup;

		this.axisType = axisType;
		this.representation = representation;
		this.independent = independent;
		this.parameters = parameters;

		this.globalFlag = globalFlag;
		this.channelValue = channelValue;
	}

	public AxisType getAxisType() {
		return axisType;
	}

	public Representation getRepresentation() {
		return representation;
	}

	public double[] getParameters() {
		return parameters.clone();
	}

	public boolean isIndependent() {
		return independent;
	}

	public Channel getChannel() {
		return channel;
	}

	public ChannelGroup getChannelGroup() {
		return channelGroup;
	}

	public boolean isGlobalValid() {
		return globalFlag;
	}

	public ChannelValue getChannelValue() {
		return channelValue;
	}

	public static ChannelValuesWriteRequest createDefault(Channel channel, ChannelGroup channelGroup,
			ChannelValue channelValue) throws DataAccessException {
		boolean globalFlag = areAllValid(channelValue.getFlags());
		return new ChannelValuesWriteRequest(channel, channelGroup, channelValue, globalFlag,
				AxisType.Y_AXIS, Representation.EXPLICIT, false, new double[0]);
	}


	public static ChannelValuesWriteRequest create(Channel channel, ChannelGroup channelGroup,
			ChannelValue channelValue, AxisType axisType, Representation representation, boolean independent,
			double[] parameters) throws DataAccessException {
		boolean globalFlag = areAllValid(channelValue.getFlags());
		return new ChannelValuesWriteRequest(channel, channelGroup, channelValue, globalFlag, axisType,
				representation, independent, parameters);
	}

	private static boolean areAllValid(boolean[] flags) {
		for(boolean flag : flags) {
			if(!flag) {
				return false;
			}
		}
		return true;
	}

}
