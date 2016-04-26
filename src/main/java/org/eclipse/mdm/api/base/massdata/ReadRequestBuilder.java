/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.util.Arrays;
import java.util.List;

import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.Unit;

/**
 * Builds measured values read request configurations.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ReadRequestBuilder {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final ReadRequest readRequest;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param readRequest The {@link ReadRequest} that will be configured.
	 */
	ReadRequestBuilder(ReadRequest readRequest) {
		this.readRequest = readRequest;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Configures the {@link ReadRequest} to retrieve measured values of all
	 * related {@link Channel}s.
	 *
	 * @return This builder is returned.
	 */
	public ReadRequestBuilder allChannels() {
		readRequest.setLoadAllChannels();
		return this;
	}

	/**
	 * Adds given {@link Channel}s to the underlying {@link ReadRequest}.
	 *
	 * <p><b>Note:</b> {@code Channel}s added with this method will be ignored,
	 * once {@link #allChannels()} was called.
	 *
	 * @param channels The {@code Channel}s whose measured values will be
	 * 		loaded.
	 * @return This builder is returned.
	 */
	public ReadRequestBuilder channels(List<Channel> channels) {
		channels.forEach(readRequest::addChannel);
		return this;
	}

	/**
	 * Adds given {@link Channel}s to the underlying {@link ReadRequest}.
	 *
	 * <p><b>Note:</b> {@code Channel}s added with this method will be ignored,
	 * once {@link #allChannels()} was called.
	 *
	 * @param channels The {@code Channel}s whose measured values will be
	 * 		loaded.
	 * @return This builder is returned.
	 */
	public ReadRequestBuilder channels(Channel... channels) {
		Arrays.stream(channels).forEach(readRequest::addChannel);
		return this;
	}

	/**
	 * Adds given {@link Channel} to the underlying {@link ReadRequest}.
	 *
	 * <p><b>Note:</b> {@code Channel} added with this method will be ignored,
	 * once {@link #allChannels()} was called.
	 *
	 * @param channel The {@code Channel} whose measured values will be loaded.
	 * @param unit The unit of the loaded measured values.
	 * @return This builder is returned.
	 */
	public ReadRequestBuilder channel(Channel channel, Unit unit) {
		readRequest.addChannel(channel, unit);
		return this;
	}

	/**
	 * Configures the number of values that will be loaded per {@link Channel}.
	 *
	 * <p><b>Note:</b> If the request size is zero, then all available measured
	 * values will be loaded for each configured {@link Channel}.
	 *
	 * @param requestSize The request size.
	 * @return This builder is returned.
	 * @throws IllegalArgumentException Thrown if the request size is smaller than 0.
	 */
	public ReadRequestBuilder requestSize(int requestSize) {
		if(requestSize < 0) {
			throw new IllegalArgumentException("The number of values per channel must be greater or at least equal to 0.");
		}

		readRequest.setRequestSize(requestSize);
		return this;
	}

	/**
	 * Configures the number of values that will be skipped.
	 *
	 * @param startIndex The start index.
	 * @return This builder is returned.
	 * @throws IllegalArgumentException Thrown if the start index is smaller than 0.
	 */
	public ReadRequestBuilder startIndex(int startIndex) {
		if(startIndex < 0) {
			throw new IllegalArgumentException("The start index must be greater or at least equal to 0.");
		}

		readRequest.setStartIndex(startIndex);
		return this;
	}

	/**
	 * Configures the {@link ReadRequest} to load all measured values of each
	 * configured {@link Channel} and returns the configured {@code
	 * ReadRequest}.
	 *
	 * <p><b>Note:</b> Before calling this the {@code Channel}s whose measured
	 * values have to be loaded must be configured.
	 *
	 * @return This builder is returned.
	 */
	public ReadRequest allValues() {
		readRequest.setStartIndex(0);
		readRequest.setRequestSize(0);
		return readRequest;
	}

	/**
	 * Returns an {@link ReadRequestIterable} to iterate over all available
	 * {@link ReadRequest}s.
	 *
	 * @return The {@code ReadRequestIterable} is returned.
	 */
	public ReadRequestIterable createIterable() {
		return new ReadRequestIterable(readRequest);
	}

}
