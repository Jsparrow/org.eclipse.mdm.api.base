/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the context component entity types. Instances of this class
 * are only provided / managed via the owning descriptive {@link ContextRoot}.
 * Additionally if the owning {@code ContextRoot} is of type {@link
 * ContextType#TESTEQUIPMENT} this context component may have relations to
 * {@link ContextSensor}s whose names have to be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ContextComponent extends BaseEntity implements Deletable, Derived {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core The {@link EntityCore}.
	 */
	ContextComponent(EntityCore core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link ContextSensor} identified by given name.
	 *
	 * @param name The name of the {@code ContextSensor}.
	 * @return The {@code Optional} is empty if a {@code ContextSensor} with
	 * 		given name does not exist.
	 */
	public Optional<ContextSensor> getContextSensor(String name) {
		return getContextSensors().stream().filter(s -> s.getName().equals(name)).findAny();
	}

	/**
	 * Returns all available {@link ContextSensor}s related to this context
	 * component.
	 *
	 * @return The returned {@code List} is unmodifiable.
	 */
	public List<ContextSensor> getContextSensors() {
		return Collections.unmodifiableList(getCore().getChildren(ContextSensor.class));
	}

	/**
	 * Removes given {@code ContextSensor} from this context component.
	 *
	 * @param contextSensor The {@code ContextSensor} that will be removed.
	 * @return Returns {@code true} if this context component held given {@code
	 * 		ContextSensor}.
	 */
	public boolean removeContextSensor(ContextSensor contextSensor) {
		return getCore().removeChild(contextSensor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String prefix = new StringBuilder(getURI().getTypeName()).append('(').toString();
		return getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ", prefix, ")"));
	}

}
