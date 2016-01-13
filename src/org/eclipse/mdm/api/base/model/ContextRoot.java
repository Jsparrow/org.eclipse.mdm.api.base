/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
 * be restricted to. Additionally the names of all contained {@code
 * ContextComponent} data items must be unique.
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
public final class ContextRoot extends AbstractDataItem implements Deletable, Derived {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final List<ContextComponent> contextComponents = new ArrayList<>();
	@Deprecated private final List<ContextSensor> contextSensors = new ArrayList<>();

	private final ContextType contextType;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param values This data item's values.
	 * @param uri The data item identifier.
	 * @param references Related data item instances.
	 */
	private ContextRoot(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
		contextType = ContextType.valueOf(ContextType.class, uri.getTypeName().toUpperCase());
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the context type of this data item.
	 *
	 * @return The context type is returned.
	 */
	public ContextType geContextType() {
		return contextType;
	}

	// TODO
	public List<ContextComponent> getContextComponents() {
		return contextComponents;
	}

	/*
	 * TODO: discuss how deletion or creation of context-components / -sensor should be detected!
	 * The decision will have impact on the associated getters
	 */

	@Deprecated // TODO context sensors will be moved to context components
	public List<ContextSensor> getContextSensors() {
		/**
		 * TODO: iterate conditionally (ContextType == TESTEQUIPMENT) over all context components
		 * and collect all sensors
		 */
		return contextSensors;
	}

	/**
	 * Returns true in case of available sensors.
	 *
	 * @return True if this context root allows sensors and there is at least
	 * 		one present.
	 */
	public boolean hasSensors() {
		return contextType.areSensoresAllowed() && !getContextSensors().isEmpty();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getURI().getTypeName()).append('(');
		sb.append(getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ")));

		if(getContextComponents().size() > 0) {
			sb.append(", components = ").append(getContextComponents());
			if(hasSensors()) {
				sb.append(", sensors = ").append(getContextSensors());
			}
		}

		return sb.append(')').toString();
	}

}
