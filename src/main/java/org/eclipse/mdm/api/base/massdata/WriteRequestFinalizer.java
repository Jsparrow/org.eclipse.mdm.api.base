/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

public class WriteRequestFinalizer {

	final WriteRequest writeRequest;

	WriteRequestFinalizer(WriteRequest writeRequest) {
		this.writeRequest = writeRequest;
	}

	public WriteRequest build() {
		return writeRequest;
	}

}