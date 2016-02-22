/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * File state enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see FileLink
 */
public enum FileState {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * A {@link FileLink} with this state represents a newly added file, which
	 * has to be uploaded by the data provider as soon as the owning data item
	 * is written.
	 *
	 * TODO in this case a FileLink.File is not allowed to be null!
	 */
	LOCAL,

	/**
	 * A {@link FileLink} with this state represents a file, which is already
	 * present in the data source's file storage. This state is the initial
	 * state of a {@code FileLink} that is contained in a queried data item.
	 *
	 * TODO in this case a FileLink.File is initially null -&gt; data provider
	 * will download the file on request.
	 */
	REMOTE;

}
