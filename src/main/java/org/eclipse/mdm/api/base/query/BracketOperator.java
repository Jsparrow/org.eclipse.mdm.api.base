/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/


package org.eclipse.mdm.api.base.query;

/**
* The bracket operator enumeration.
*
* @since 1.0.0
* @see Condition
* @see Filter
*/

public enum BracketOperator {
	/**
	 * Left parenthesis.
	 */
	OPEN,

	/**
	 * Right parenthesis.
	 */
	CLOSE;
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		switch (this) {
		case OPEN:
			return "(";
		case CLOSE:
			return ")";
		default:
			return this.toString();
		}
	}
}
