/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Unique identifier for {@link DataItem}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see DataItem
 */
public final class URI {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final String envName;
	private final String typeName;
	private final Long id;

	// ======================================================================
	// Constructors
	// ======================================================================

	public URI(String envName, String typeName, Long id) {
		this.envName = envName;
		this.typeName = typeName;
		this.id = id;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	public Long getID() {
		return id;
	}

	public String getTypeName() {
		return typeName;
	}

	public String getEnvironmentName() {
		return envName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("mdmDataItem://");
		sb.append(envName).append('/').append(typeName).append('/').append(id);
		return sb.toString();
	}

}
