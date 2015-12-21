/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ChannelValuesReadRequest {

	private static MatrixMode DEFAULT_MATRIX_MODE = MatrixMode.CALCULATED;
	private static int DEFAULT_REQUEST_SIZE = 100_000;
	private static int DEFAULT_START_INDEX = 0;

	private final MatrixSource matrixSource;
	private final List<Channel> channels;
	private final MatrixMode matrixMode;

	// this size is per channel - CURRENTLY!
	// might be changed to total number of retrieved values of all channels!
	private final int requestSize;
	private final int startIndex;

	private int numberOfValues = 0;

	private ChannelValuesReadRequest(MatrixSource matrixSource, List<Channel> channels, MatrixMode matrixMode,
			int maxRequsetSize, int startIndex) {
		this.matrixSource = matrixSource;
		this.channels = channels;
		this.matrixMode = matrixMode;
		requestSize = maxRequsetSize;
		this.startIndex = startIndex;
	}

	public MatrixMode getMatrixMode() {
		return matrixMode;
	}

	public int getRequestSize() {
		return requestSize;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public MatrixSource getMatrixSource() {
		return matrixSource;
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

	public Optional<ChannelValuesReadRequest> next() {
		if(getStartIndex() + getRequestSize() < getNumberOfValues()) {
			return Optional.of(new ChannelValuesReadRequest(getMatrixSource(), getChannels(), getMatrixMode(),
					getRequestSize(), getStartIndex() + getRequestSize()));
		}

		return Optional.empty();
	}

	public static ChannelValuesReadRequest createDefault(MatrixSource measurement) {
		return new ChannelValuesReadRequest(measurement, Collections.emptyList(), DEFAULT_MATRIX_MODE,
				DEFAULT_REQUEST_SIZE, DEFAULT_START_INDEX);
	}

	public static ChannelValuesReadRequest createWithDefinedChannels(MatrixSource measurement, List<Channel> channelList) {
		return new ChannelValuesReadRequest(measurement, channelList,
				DEFAULT_MATRIX_MODE, DEFAULT_REQUEST_SIZE, DEFAULT_START_INDEX);
	}

	public static ChannelValuesReadRequest create(MatrixSource channelGroup, List<Channel> channelList, int maxRequsetSize, int startIndex) {
		return new ChannelValuesReadRequest(channelGroup, channelList, MatrixMode.CALCULATED, maxRequsetSize, startIndex);
	}

	public static ChannelValuesReadRequest createRaw(MatrixSource channelGroup, List<Channel> channelList) {
		return new ChannelValuesReadRequest(channelGroup, channelList, MatrixMode.STORAGE, 0, 0);
	}

	public interface MatrixSource extends DataItem {

	}

}
