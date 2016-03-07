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
	 * has to be uploaded soon as the owning entity is is written.
	 */
	LOCAL,

	/**
	 * A {@link FileLink} with this state represents a file, which is already
	 * present in the data source's file storage. This state is the initial
	 * state of a {@code FileLink} that is contained in a queried entity.
	 */
	REMOTE;

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this file state is {@link #LOCAL}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isLocal() {
		return LOCAL == this;
	}

	/**
	 * Returns true if this file state is the {@link #REMOTE}.
	 *
	 * @return Returns {@code true} if this constant is the constant
	 * 		described above.
	 */
	public boolean isRemote() {
		return REMOTE == this;
	}

}
