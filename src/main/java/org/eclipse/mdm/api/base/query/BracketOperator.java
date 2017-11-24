/*
 * Copyright (c) 2017 Florian Schmitt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
