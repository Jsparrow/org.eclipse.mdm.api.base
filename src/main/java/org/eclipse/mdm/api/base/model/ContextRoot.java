/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Implementation of the context root data item type. This is the root node of
 * the descriptive component structure for a {@link ContextType}. This element
 * is used for both, order and measured result description data. If it belongs
 * to an order description, then a relation to a {@link TestStep} data item
 * exists. Otherwise it represents the description of a measurement and
 * therefore has one ore more relations to {@link Measurement} data items. In
 * the <u>base application model</u> the component structure is provided as is.
 * An extension of the <u>base model</u> may define a template the structure of
 * contained {@link ContextComponent} and {@link ContextSensor} data items will
 * be restricted to. Additionally the names of all related {@code
 * ContextComponent} and {@code ContextSensor} data items must be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see TestStep
 * @see Measurement
 * @see ContextType
 * @see ContextComponent
 * @see ContextSensor
 */
public final class ContextRoot extends BaseDataItem implements Deletable, Derived {

	// ======================================================================
	// Class variables
	// ======================================================================

	public static final String ATTR_VERSION = "Version";

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final ContextType contextType;

	// ======================================================================
	// Constructors
	// ======================================================================

	public ContextRoot(Core core) {
		super(core);
		contextType = ContextType.valueOf(core.getTypeName().toUpperCase(Locale.ROOT));
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the context type of this data item.
	 *
	 * @return The context type is returned.
	 */
	public ContextType getContextType() {
		return contextType;
	}

	public List<ContextComponent> getContextComponents() {
		// TODO provide getter getContextComponent(String name)
		return Collections.unmodifiableList(getCore().getChildren(ContextComponent.class));
	}

	public boolean removeContextComponent(ContextComponent contextComponent) {
		return getCore().removeChild(contextComponent);
	}

	public List<ContextSensor> getContextSensors() {
		if(ContextType.TESTEQUIPMENT == getContextType()) {
			return Collections.emptyList();
		}

		// TODO names of sensors unique per context root or context component?
		// TODO provide getter getContextSensor(String name) ?!

		return getContextComponents().stream().map(ContextComponent::getContextSensors)
				.collect(ArrayList::new, List::addAll, List::addAll);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getCore().getTypeName()).append('(');
		sb.append(getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ")));

		List<ContextComponent> contextComponents = getContextComponents();
		if(!contextComponents.isEmpty()) {
			sb.append(", components = ").append(contextComponents);
			List<ContextSensor> contextSensors = getContextSensors();
			if(!contextSensors.isEmpty()) {
				sb.append(", sensors = ").append(contextSensors);
			}
		}

		return sb.append(')').toString();
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	// TODO JDoc
	void setVersion(String version) {
		// TODO
		// MDM 4 initilaizes this field with an "1" for the insert statement
		// Immediatley after the execution of the insert statement the instance ID
		// is known and written back with an update statement!
		//
		// The base attribute 'version' is meant for storing different versions of the same instance; these
		// may keep their name and may be distinguished from each other by their version only. Note
		// that there is no semantic specification for the version string given by ASAM ODS. It is up to
		// the user to create meaningful version strings, and applications evaluating the version string
		// must adapt to the user-defined semantic.

		getValue(ATTR_VERSION).set(version);
	}

}
