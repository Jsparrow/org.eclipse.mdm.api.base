/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.adapter;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.mdm.api.base.model.FileLink;
import org.eclipse.mdm.api.base.model.Value;

/**
 * Provides access to the internals of any entity:
 *
 * <ul>
 * <li>name of the data source</li>
 * <li>name of the type</li>
 * <li>instance ID</li>
 * <li>values</li>
 * <li>added/removed file links</li>
 * <li>related entities</li>
 * <li>parent/child entities</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public interface Core {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the name of the data source this entity was retrieved from.
	 *
	 * @return Name of the data source.
	 */
	String getSourceName();

	/**
	 * Returns the name of the entity type.
	 *
	 * @return Name of the entity type is returned.
	 */
	String getTypeName();

	/**
	 * Returns the instance ID or {@code 0} if this instance is not yet
	 * persisted.
	 *
	 * @return The instance ID is returned.
	 */
	String getID();

	/**
	 * Sets an instance ID.
	 *
	 * @param instanceID
	 *            The new instance ID.
	 */
	void setID(String instanceID);

	/**
	 * Returns <i>all</i> {@link Value} containers of this entity.
	 *
	 * @return Values mapped by their name are returned.
	 */
	Map<String, Value> getValues();

	/**
	 * Hides {@link Value} containers whose name is contained in the given names
	 * {@code Collection}.
	 * E.g. hide attributes from a CatalogComponent when not used in a TemplateComponent.  
	 *
	 * @param names
	 *            Names of the {@code Value} which shall be hidden.
	 */
	void hideValues(Collection<String> names);

	/**
	 * Returns <i>all</i> {@link Value} containers, including the hidden ones.
	 *
	 * @return All {@code Value} containers are returned.
	 */
	Map<String, Value> getAllValues();

	/**
	 * Returns all newly added {@link FileLink}.
	 *
	 * @return New {@code FileLink}s are returned.
	 */
	default List<FileLink> getAddedFileLinks() {
		Predicate<FileLink> isRemote = FileLink::isRemote;

		List<FileLink> fileLinks = getValues().values().stream().filter(v -> v.getValueType().isFileLink())
				.filter(Value::isValid).map(v -> (FileLink) v.extract()).filter(isRemote.negate())
				.collect(Collectors.toList());

		List<Value> values = getValues().values().stream().filter(v -> v.getValueType().isFileLinkSequence())
				.filter(Value::isValid).collect(Collectors.toList());

		for (Value value : values) {
			Arrays.stream((FileLink[]) value.extract()).filter(isRemote.negate()).forEach(fileLinks::add);
		}

		return fileLinks;
	}

	/**
	 * Returns all removed {@link FileLink}s.
	 *
	 * @return Removed {@code FileLink}s are returned.
	 */
	default List<FileLink> getRemovedFileLinks() {
		Predicate<FileLink> isRemote = FileLink::isRemote;

		List<FileLink> fileLinks = getValues().values().stream().filter(v -> v.getValueType().isFileLink())
				.filter(Value::isModified).map(v -> (FileLink) v.extractInitial()).filter(Objects::nonNull)
				.filter(isRemote).collect(Collectors.toList());

		List<Value> values = getValues().values().stream().filter(v -> v.getValueType().isFileLinkSequence())
				.filter(Value::wasValid).filter(Value::isModified).collect(Collectors.toList());

		for (Value value : values) {
			List<FileLink> current = Arrays.asList((FileLink[]) value.extract());
			Arrays.stream((FileLink[]) value.extractInitial()).filter(fl -> !current.contains(fl))
					.forEach(fileLinks::add);
		}

		return fileLinks;
	}

	/**
	 * Applies modifications made to the entity stores and {@link Value}
	 * containers. This method is called when a transaction is finalized 
	 * (all operations completed successfully) to reflect the new state in
	 * the core.
	 */
	default void apply() {
		// apply the removed state to mutable entities
		getMutableStore().apply();

		// apply the removed state to children
		getChildrenStore().apply();

		// apply modified values
		getValues().values().stream().filter(Value::isModified).forEach(Value::apply);
	}

	/**
	 * Returns the mutable {@link EntityStore}. 
	 * Holds entities with mutable (editable) relations to the entity 
	 * bound to this core instance.
	 * This store only contains always only one relation for one entity. 
	 * For child relations use {@link ChildrenStore}.  
	 *
	 * @return The mutable {@code EntityStore} is returned.
	 */
	EntityStore getMutableStore();

	/**
	 * Returns the permanent {@link EntityStore}. 
	 * Holds entities with immutable (non-editable) relations to the entity 
	 * bound to this core instance. 
	 * E.g. relations to the parent entity. 
	 *
	 * @return The permanent {@code EntityStore} is returned.
	 */
	EntityStore getPermanentStore();

	/**
	 * Returns the {@link ChildrenStore}. This store holds child
	 * entities with relations to the entity bound to this core instance. 
	 * 	 
	 * @return The {@code ChildrenStore} is returned.
	 */
	// TODO (8.11.2017; Florian Schmitt, Angelika Wittek)
	// Entities with more than one related entity that do not refer to children, have also to go here, 
	// as it is not permitted to go to the other stores. Does this work at all? 
	ChildrenStore getChildrenStore();

}
