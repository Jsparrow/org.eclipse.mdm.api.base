/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import org.eclipse.mdm.api.base.model.Unit;

public class UnitIndependentBuilder extends IndependentBuilder {

	UnitIndependentBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	public IndependentBuilder targetUnit(Unit targetUnit) {
		writeRequest.setTargetUnit(targetUnit);
		return this;
	}

}
