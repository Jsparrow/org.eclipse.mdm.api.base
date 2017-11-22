/*
 * Copyright (c) 2017 science + computing ag Tuebingen
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.Optional;

import org.eclipse.mdm.api.base.core.Core;
import org.eclipse.mdm.api.base.model.BaseEntityFactory;
import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.User;

/**
 * 
 * Very basic implementation of BaseEntityFactory for testing purposes. We
 * deliver a core containing data on initialization to simulate other data
 * sources.
 * 
 * @author Florian Schmitt
 *
 */
public class EntityFactoryImpl extends BaseEntityFactory {

	private Core core;

	public EntityFactoryImpl(Core core) {
		this.core = core;
	}

	@Override
	protected Optional<User> getLoggedInUser() {
		return Optional.ofNullable(null);
	}

	@Override
	protected <T extends Entity> Core createCore(Class<T> entityClass) {
		return core;
	}

	@Override
	protected <T extends Entity> Core createCore(Class<T> entityClass, ContextType contextType) {
		return core;
	}

	@Override
	protected <T extends Entity> Core createCore(String name, Class<T> entityClass) {
		return core;
	}
}