/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

// TODO the MDM4 versionable does not allow to change the field *.DateCreated!
public interface Versionable extends DataItem, Datable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Version' attribute name.
	 */
	static final String ATTR_VERSION = "Version";

	/**
	 * The 'VersionState' attribute name.
	 */
	static final String ATTR_VERSION_STATE = "ValidFlag";

	// ======================================================================
	// Public methods
	// ======================================================================

	// TODO in ODS this is an STRING field
	default Integer getVersion() {
		// TODO version could be not valid?!
		return Integer.valueOf(getValue(ATTR_VERSION).extract());
	}

	// TODO add DateCreated?!

	default VersionState getVersionState() {
		return getValue(ATTR_VERSION_STATE).extract();
	}

	@Deprecated
	default void setVersionState(VersionState versionState) {
		// TODO only incremental update should be possible!!
		// should we do validation checking here?!
		// NOTE: it is still to ignore this checking by manually accessing the value!!!
		getValue(ATTR_VERSION_STATE).set(versionState);
	}

}
