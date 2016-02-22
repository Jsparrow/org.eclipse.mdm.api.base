/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

public final class ReadRequestBuilder extends ChannelBuilder {

	ReadRequestBuilder(ReadRequest readRequest) {
		super(readRequest);
	}

	// this one overwrites current request size and start index!
	public ChannelBuilder allMeasuredValues() {
		readRequest.setRequestSize(0);
		readRequest.setStartIndex(0);
		return new ChannelBuilder(readRequest);
	}

	public ReadRequestBuilder requestSize(int requestSize) {
		if(requestSize < 0) {
			throw new IllegalArgumentException("Request size must be equal or greater than 0.");
		}
		readRequest.setRequestSize(requestSize);
		return this;
	}

	public ReadRequestBuilder startIndex(int startIndex) {
		if(startIndex < 0) {
			throw new IllegalArgumentException("Start index must be equal or greater than 0.");
		}
		readRequest.setStartIndex(startIndex);
		return this;
	}

}
