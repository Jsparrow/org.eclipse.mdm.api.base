/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ChannelGroup;

public class ReadRequest {

	private static final int DEFAULT_REQUEST_SIZE = 100_000;
	private static final int DEFAULT_START_INDEX = 0;

	private final List<Channel> channels = new ArrayList<>();

	private final ChannelGroup channelGroup;

	// this size is per channel - CURRENTLY!
	// might be changed to total number of retrieved values of all channels!
	private int requestSize = DEFAULT_REQUEST_SIZE;
	private int startIndex = DEFAULT_START_INDEX;

	private int numberOfValues = 0;

	private ReadRequest(ChannelGroup channelGroup) {
		this.channelGroup = channelGroup;
	}

	public int getRequestSize() {
		return requestSize;
	}

	void setRequestSize(int requestSize) {
		this.requestSize = requestSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public ChannelGroup getChannelGroup() {
		return channelGroup;
	}

	public List<Channel> getChannels() {
		return channels;
	}

	public int getNumberOfValues() {
		return numberOfValues;
	}

	public void setNumberOfValues(int size) {
		// TODO size must be greater than 0
		// is automatically adjusted (if current value is zero) while processing this request
		numberOfValues = size;
	}

	public Optional<ReadRequest> next() {
		if(getStartIndex() + getRequestSize() < getNumberOfValues()) {
			ReadRequest readRequest = new ReadRequest(getChannelGroup());

			// TODO the request size depends on the number of channels!
			readRequest.setRequestSize(getRequestSize());
			readRequest.setStartIndex(getStartIndex() + getRequestSize());

			return Optional.of(readRequest);
		}

		return Optional.empty();
	}

	public static ReadRequestBuilder create(ChannelGroup channelGroup) {
		return new ReadRequestBuilder(new ReadRequest(channelGroup));
	}

	void addChannel(Channel channel) {
		channels.add(channel);
	}

}
