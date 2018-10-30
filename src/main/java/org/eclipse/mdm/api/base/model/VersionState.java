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
	public static final VersionState EDITABLE = new VersionState("EDITABLE", 0);

	/**
	 * An entity with this version state is no longer editable and is allowed to
	 * be used when creating new entities.
	 *
	 * <p>
	 * <b>Note:</b> If an entity's version state is this state, then its version
	 * state is only allowed to be changed to {@link #ARCHIVED}.
	 */
	public static final VersionState VALID = new VersionState("VALID", 1);

	/**
	 * An entity with this version state is neither editable nor is it allowed
	 * to use it when creating new entities
	 *
	 * <p>
	 * <b>Note:</b> If an entity's version state is this state, then its version
	 * state is no longer allowed to be changed.
	 */
	public static final VersionState ARCHIVED = new VersionState("ARCHIVED", 2);

	private VersionState(String name, int ordinal) {
		super(name, ordinal);
	}

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
	
}
