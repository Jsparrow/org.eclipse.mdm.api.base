/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import org.eclipse.mdm.api.base.core.Core;

/**
 * Implementation of the environment entity type. The {@link Environment} is a
 * singleton within a connected data source.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class Environment extends BaseEntity implements Datable, Describable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'MaxTestLevel' attribute name.
	 */
	public static final String ATTR_MAX_TEST_LEVEL = "Max_test_level";

	/**
	 * The 'BaseModelVersion' attribute name.
	 */
	public static final String ATTR_BASE_MODEL_VERSION = "Base_model_version";

	/**
	 * The 'AppModelVersion' attribute name.
	 */
	public static final String ATTR_APP_MODEL_VERSION = "AppModelVersion";

	/**
	 * The 'AppModelType' attribute name.
	 */
	public static final String ATTR_APP_MODEL_TYPE = "AppModelType";

	/**
	 * The 'Timezone' attribute name.
	 */
	public static final String ATTR_TIMEZONE = "Timezone";

	/**
	 * The 'MeaningOfAliases' attribute name.
	 */
	public static final String ATTR_MEANING_OF_ALIASES = "MeaningOfAliases";

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	Environment(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the max test level of this environment.
	 *
	 * @return The max test level is returned.
	 */
	public Integer getMaxTestLevel() {
		return getValue(ATTR_MAX_TEST_LEVEL).extract();
	}

	/**
	 * Returns the base model version of this environment.
	 *
	 * @return The base model version is returned.
	 */
	public String getBaseModelVersion() {
		return getValue(ATTR_BASE_MODEL_VERSION).extract();
	}

	/**
	 * Returns the application model version of this environment.
	 *
	 * @return The application model version is returned.
	 */
	public String getAppModelVersion() {
		return getValue(ATTR_APP_MODEL_VERSION).extract();
	}

	/**
	 * Returns the application model type of this environment.
	 *
	 * @return The application model type is returned.
	 */
	public String getAppModelType() {
		return getValue(ATTR_APP_MODEL_TYPE).extract();
	}

	/**
	 * Returns the time zone of this environment.
	 *
	 * @return The time zone is returned.
	 */
	public String getTimezone() {
		return getValue(ATTR_TIMEZONE).extract();
	}

	/**
	 * Returns the Meaning of aliases of this environment.
	 *
	 * @return The meaning of aliases are returned.
	 */
	public String[] getMeaningOfAliases() {
		return getValue(ATTR_MEANING_OF_ALIASES).extract();
	}

}
