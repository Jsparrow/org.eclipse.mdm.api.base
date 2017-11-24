/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Version state enumeration.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see FileLink
 */
public class VersionState extends EnumerationValue {

	// ======================================================================
	// Enumerations
	// ======================================================================

	/**
	 * An entity with this version state is still editable and hence not allowed
	 * be used when creating new entities.
	 */
	public static final VersionState EDITABLE = new VersionState(0); 

	/**
	 * An entity with this version state is no longer editable and is allowed to
	 * be used when creating new entities.
	 *
	 * <p>
	 * <b>Note:</b> If an entity's version state is this state, then its version
	 * state is only allowed to be changed to {@link #ARCHIVED}.
	 */
	public static final VersionState VALID = new VersionState(1); 

	/**
	 * An entity with this version state is neither editable nor is it allowed
	 * to use it when creating new entities.
	 *
	 * <p>
	 * <b>Note:</b> If an entity's version state is this state, then its version
	 * state is no longer allowed to be changed.
	 */
	public static final VersionState ARCHIVED = new VersionState(2); 

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns true if this version state is {@link #EDITABLE}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isEditable() {
		return EDITABLE == this;
	}

	/**
	 * Returns true if this version state is {@link #VALID}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isValid() {
		return VALID == this;
	}

	/**
	 * Returns true if this version state is {@link #ARCHIVED}.
	 *
	 * @return Returns {@code true} if this constant is the constant described
	 *         above.
	 */
	public boolean isArchived() {
		return ARCHIVED == this;
	}
	
	/**
	 *  Constructor, ensures the correct order
	 */
	VersionState(int ord) {
		super(ord);
	}
}
