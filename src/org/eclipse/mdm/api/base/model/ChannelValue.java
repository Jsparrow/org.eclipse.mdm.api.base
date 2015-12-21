/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

// TODO: refactor this class to provide simple value iteration!
@Deprecated
public class ChannelValue {

	private Value dataValues;
	private boolean[] flags;

	public ChannelValue(Value dataValues, boolean[] flags) {
		this.dataValues = dataValues;
		this.flags = flags;
	}

	public Value getDataValues() {
		return dataValues;
	}

	public boolean[] getFlags() {
		return flags;
	}

	@Override
	public String toString() {
		return dataValues.toString();
	}

}
