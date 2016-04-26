/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ChannelGroup;
import org.eclipse.mdm.api.base.model.Unit;

/**
 * This class provides all required informations to load measured values.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ReadRequest {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Map<Channel, Unit> channels = new HashMap<>();
	private final ChannelGroup channelGroup;

	private boolean loadAllChannels;

	private int requestSize = 100_000;
	private int startIndex;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param readRequest The previous {@link ReadRequest}.
	 */
	ReadRequest(ReadRequest readRequest) {
		this(readRequest.getChannelGroup());
		channels.putAll(readRequest.getChannels());
		loadAllChannels = readRequest.loadAllChannels;
		requestSize = readRequest.getRequestSize();
		startIndex = readRequest.getStartIndex() + readRequest.getRequestSize();
	}

	/**
	 * Constructor.
	 *
	 * @param channelGroup The {@link ChannelGroup} is the source entity to
	 * 		access measured values.
	 */
	private ReadRequest(ChannelGroup channelGroup) {
		this.channelGroup = channelGroup;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link ReadRequestBuilder} with the given {@link
	 * ChannelGroup} as the source entity for the requested measured values.
	 *
	 * <pre> {@code
	 * ReadRequest readRequest1 = ReadRequest.create(ChannelGroup)
	 * 	.allChannels() // load values of all related channels
	 * 	.allValues()   // load all values of each channel
	 * 	.get();
	 *
	 * ReadRequest readRequest2 = ReadRequest.create(ChannelGroup)
	 * 	.channels(channel1, channel2) // load measured values of these channels
	 * 	.requestSize(1_000)           // load 1000 values of each channel (default is 100_000)
	 * 	.get();
	 * }</pre>
	 *
	 * @param channelGroup Used to access measured values.
	 * @return Returns the {@link ReadRequestBuilder}.
	 */
	public static ReadRequestBuilder create(ChannelGroup channelGroup) {
		return new ReadRequestBuilder(new ReadRequest(channelGroup));
	}

	/**
	 * Returns the selected {@link Channel}s whose values will be loaded and
	 * the corresponding {@link Unit} configuration.
	 *
	 * @return The returned {@code Map} is empty, if this request is configured
	 * 		to load values of all related {@code Channel}s.
	 */
	public Map<Channel, Unit> getChannels() {
		return Collections.unmodifiableMap(channels);
	}

	/**
	 * Returns the {@link ChannelGroup} which will be used as the source entity
	 * to access measured values.
	 *
	 * @return The measured values source, the {@code ChannelGroup} is returned.
	 */
	public ChannelGroup getChannelGroup() {
		return channelGroup;
	}

	/**
	 * Checks whether to load measured values of all related {@link Channel}s.
	 *
	 * @return True if this request is configured to load values of all {@code
	 * 		Channel}s.
	 */
	public boolean isLoadAllChannels() {
		return loadAllChannels;
	}

	/**
	 * Returns the number of values that are loaded per {@link Channel} .
	 *
	 * @return The number of values per {@code Channel} per is returned.
	 */
	public int getRequestSize() {
		return requestSize;
	}

	/**
	 * Returns the overall index of the measured values within the underlying
	 * {@link ChannelGroup}. This index is used while processing this request
	 * and means how many values of the values sequences will be skipped.
	 *
	 * @return The overall start index is returned.
	 */
	public int getStartIndex() {
		return startIndex;
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Adds a new {@link Channel} whose measured values have to be loaded. The
	 * measured values will be retrieved in the unit they were stored.
	 *
	 * @param channel This {@code Channel} has to related to the underlying
	 * 		{@link ChannelGroup}.
	 */
	void addChannel(Channel channel) {
		addChannel(channel, channel.getUnit());
	}

	/**
	 * Adds a new {@link Channel} whose measured values have to be loaded. The
	 * measured values will be retrieved in the given unit.
	 *
	 * <p><b>Note:</b> The processing of this request may fail if it is not
	 * possible to convert the values.
	 *
	 * @param channel This {@code Channel} has to related to the underlying
	 * 		{@link ChannelGroup}.
	 * @param unit {@code Unit} the measured values have to be loaded in.
	 */
	void addChannel(Channel channel, Unit unit) {
		channels.put(channel, unit);
	}

	/**
	 * Configures this request to retrieve measured values of all related
	 * {@link Channel}s.
	 */
	void setLoadAllChannels() {
		loadAllChannels = true;
		channels.clear();
	}

	/**
	 * Sets the number of values that will be loaded per {@link Channel} while
	 * processing this request.
	 *
	 * <p><b>Note:</b> If the request size is zero, then all available measured
	 * values will be loaded for each configured {@link Channel}.
	 *
	 * @param requestSize The number of values loaded per {@code Channel}.
	 */
	void setRequestSize(int requestSize) {
		this.requestSize = requestSize;
	}

	/**
	 * Sets the number of values that will be skipped.
	 *
	 * @param startIndex The number of values that will be skipped.
	 */
	void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	/**
	 * Checks whether there are still more values to retrieve.
	 *
	 * @return Returns true if there are more values to retrieve.
	 */
	boolean hasNext() {
		return getStartIndex() + getRequestSize() < getChannelGroup().getNumberOfValues().intValue();
	}

}
