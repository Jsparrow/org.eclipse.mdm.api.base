/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	/**
	 * Returns current set of linked files of this entity.
	 *
	 * @return Current set of linked files are returned.
	 */
	default FileLink[] getFileLinks() {
		return ((FileLink[]) getValue(ATTR_FILE_LINKS).extract()).clone();
	}

	/**
	 * Replaces current set of linked files with given {@link FileLink}s.
	 *
	 * @param fileLinks The new {@code FileLink}s.
	 */
	default void setFileLinks(FileLink[] fileLinks) {
		getValue(ATTR_FILE_LINKS).set(fileLinks);
	}

	/**
	 * Adds given {@link FileLink} to the current set of linked files.
	 *
	 * @param fileLink The new {@code FileLink}.
	 */
	default void addFileLink(FileLink fileLink) {
		FileLink[] fileLinks = getFileLinks();

		FileLink[] newFileLinks = new FileLink[fileLinks.length + 1];
		System.arraycopy(fileLinks, 0, newFileLinks, 0, fileLinks.length);
		newFileLinks[fileLinks.length] = fileLink;
		setFileLinks(newFileLinks);
	}

	/**
	 * Removes given {@link FileLink} from current set of linked files.
	 *
	 * @param fileLink The {@code FileLink} which shall be removed.
	 */
	default void removeFileLink(FileLink fileLink) {
		List<FileLink> fileLinks = new ArrayList<>(Arrays.asList(getFileLinks()));
		fileLinks.remove(fileLink);
		setFileLinks(fileLinks.toArray(new FileLink[fileLinks.size()]));
	}

}
