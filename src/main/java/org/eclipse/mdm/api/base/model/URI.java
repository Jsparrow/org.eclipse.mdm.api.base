/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unique identifier for entities of any kind.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Entity
 */
public final class URI {

	// ======================================================================
	// Instance variables
	// ======================================================================

	public static final Pattern DATAITEM_PATTERN = Pattern.compile("mdmDataItem://(\\S*)/(\\S*)/(\\d*)");
	
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
	
	public static URI formString(String uri) {
		Matcher m = DATAITEM_PATTERN.matcher(uri);
	    if (m.matches() && m.groupCount() >= 3) {	    
	    	return new URI(m.group(1), m.group(2), Long.valueOf(m.group(3)));
	    }
	    throw new IllegalArgumentException("given uri '" + uri + "' is not a valid MDM uri");	    	
	}

}
