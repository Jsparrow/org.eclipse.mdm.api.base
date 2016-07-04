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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of the context root entity types. This is the root node of
 * the descriptive component structure for a {@link ContextType}. This element
 * is used for both, order and measured result description data. If it belongs
 * to an order description, then a relation to a {@link TestStep} exists.
 * Otherwise it represents the description of a measurement and therefore has
 * one ore more relations to {@link Measurement}s. In the base application
 * model the component structure is provided as is. An extension of the base
 * application model may define a template, the structure of contained {@link
 * ContextComponent}s and {@link ContextSensor}s will be restricted to.
 * Additionally the <b>names</b> of all related {@code ContextComponent}s have
 * to be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class ContextRoot extends BaseEntity implements Deletable {

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

	/**
	 * Constructor.
	 *
	 * @param core The {@link Core}.
	 */
	ContextRoot(Core core) {
		super(core);
		contextType = ContextType.valueOf(core.getTypeName().toUpperCase(Locale.ROOT));
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link ContextType} of this context root.
	 *
	 * @return The {@code ContextType} is returned.
	 */
	public ContextType getContextType() {
		return contextType;
	}

	/**
	 * Returns the {@link ContextComponent} identified by given name.
	 *
	 * @param name The name of the {@code ContextComponent}.
	 * @return The {@code Optional} is empty if a {@code ContextComponent}
	 * 		with given name does not exist.
	 */
	public Optional<ContextComponent> getContextComponent(String name) {
		return getContextComponents().stream().filter(cc -> cc.nameMatches(name)).findAny();
	}

	/**
	 * Returns all available {@link ContextComponent}s related to this context
	 * root.
	 *
	 * @return The returned {@code List} is unmodifiable.
	 */
	public List<ContextComponent> getContextComponents() {
		return getCore().getChildrenStore().get(ContextComponent.class);
	}

	/**
	 * Removes the {@link ContextComponent} identified by given name.
	 *
	 * @param name Name of the {@code ContextComponent} that have to be removed.
	 * @return Returns {@code true} if the {@code ContextComponent} with given
	 * 		name has been removed.
	 */
	public boolean removeContextComponent(String name) {
		Optional<ContextComponent> contextComponent = getContextComponent(name);
		if(contextComponent.isPresent()) {
			getCore().getChildrenStore().remove(contextComponent.get());
			return true;
		}

		return false;
	}

	/**
	 * Returns all available {@link ContextSensor}s related to the {@link
	 * ContextComponent}s, which are held by this context root.
	 *
	 * @return The returned {@code List} will always be empty if this context
	 * 		root is of type {@link ContextType#UNITUNDERTEST} or {@link
	 * 		ContextType#TESTSEQUENCE}.
	 */
	public List<ContextSensor> getContextSensors() {
		if(!getContextType().isTestEquipment()) {
			return Collections.emptyList();
		}

		return getContextComponents().stream().map(ContextComponent::getContextSensors)
				.collect(ArrayList::new, List::addAll, List::addAll);
	}

	/**
	 * Returns the version of this context root.
	 *
	 * @return The version is returned.
	 */
	public String getVersion() {
		return getValue(ATTR_VERSION).extract();
	}

	/**
	 * Sets new version for this context root.
	 *
	 * @param version The new version.
	 */
	public void setVersion(String version) {
		getValue(ATTR_VERSION).set(version);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(getClass().getSimpleName()).append('(');
		sb.append("ContextType = ").append(getContextType()).append(", ");
		sb.append(getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ")));

		List<ContextComponent> contextComponents = getContextComponents();
		if(!contextComponents.isEmpty()) {
			sb.append(", ContextComponents = ").append(contextComponents);
		}

		return sb.append(')').toString();
	}

	public static List<ContextRoot> of(TestStep testStep) {
		return of((Entity) testStep);
	}

	public static List<ContextRoot> of(Measurement measurement) {
		return of((Entity) measurement);
	}

	private static List<ContextRoot> of(Entity entity) {
		List<ContextRoot> contextRoots = new ArrayList<>();
		of(entity, ContextType.UNITUNDERTEST).ifPresent(contextRoots::add);
		of(entity, ContextType.TESTSEQUENCE).ifPresent(contextRoots::add);
		of(entity, ContextType.TESTEQUIPMENT).ifPresent(contextRoots::add);
		return contextRoots;
	}

	private static Optional<ContextRoot> of(Entity entity, ContextType contextType) {
		return Optional.ofNullable(getCore(entity).getMutableStore().get(ContextRoot.class, contextType));
	}

}
