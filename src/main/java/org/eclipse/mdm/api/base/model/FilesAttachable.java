/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * This interface extends the {@link Entity} interface and provides getter
 * and setter methods for the 'FileLinks' sequence field of an entity.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see FileLink
 */
public interface FilesAttachable extends Entity {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'FileLinks' attribute name.
	 */
	static final String ATTR_FILE_LINKS = "MDMLinks";

	// ======================================================================
	// Public methods
	// ======================================================================

	/*
	 * TODO ...
	 */

	@Deprecated
	default FileLink[] getFileLinks() {
		return getValue(ATTR_FILE_LINKS).extract();
	}
	@Deprecated
	default void setFileLinks(FileLink[] fileLinks) {
		getValue(ATTR_FILE_LINKS).set(fileLinks);
	}

}
