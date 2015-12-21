/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This is a base implementation for modeled {@link DataItem}s. API consumers
 * should never use this class in any way, instead the most common interface
 * should be used (e.g.: {@link DataItem}, {@link ContextDescribable}, etc.).
 * API producers should let their data item implementations extend this class.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class AbstractDataItem implements DataItem {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Map<Class<? extends DataItem>, DataItem> relatedDataItems;
	private final Map<String, Value> values;
	private final URI uri;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param values This data item's values.
	 * @param uri The data item identifier.
	 * @param relatedDataItems Related data item instances.
	 */
	protected AbstractDataItem(Map<String, Value> values, URI uri,
			Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		this.relatedDataItems = relatedDataItems;
		this.values = values;
		this.uri = uri;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI getURI() {
		return uri;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, Value> getValues() {
		return Collections.unmodifiableMap(values);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Class<? extends DataItem>, DataItem> getRelatedDataItems() {
		return Collections.unmodifiableMap(relatedDataItems);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String prefix = new StringBuilder(getClass().getSimpleName()).append('(').toString();
		return getValues().values().stream().map(Value::toString).collect(Collectors.joining(", ", prefix, ")"));
	}

	// ======================================================================
	// Protected methods
	// ======================================================================

	/**
	 * Returns the related data item identified by passed type.
	 *
	 * @param <T> The desired type of the related data item.
	 * @param type The desired type of the related data item.
	 * @return Returns the related data item or null, if none found.
	 */
	@SuppressWarnings("unchecked")
	protected <T extends DataItem> T getRelatedDataItem(Class<T> type) {
		return (T) relatedDataItems.get(type);
	}

	/**
	 * Adds a missing or replaces an existing 1:1 relation for passed data item.
	 * This relation is persisted as soon as this data item is.
	 *
	 * @param dataItem Related date item is not allowed to be null.
	 * @throws IllegalArgumentException Thrown if if data item is null, from another
	 * 		data source or it is not persisted (ID < 1).
	 */
	protected void setRelatedDataItem(DataItem dataItem) {
		if(dataItem == null) {
			throw new IllegalArgumentException("A related data item is not allowed to be null.");
		} else if(getURI().getEnvironmentName().equals(dataItem.getURI().getEnvironmentName())) {
			throw new IllegalArgumentException("It is not allowed to define a relation to a "
					+ "data itom from another data source.");
		} else if(dataItem.getURI().getID() <= 0) {
			throw new IllegalArgumentException("Data item is not persisted.");
		}


		relatedDataItems.put(dataItem.getClass(), dataItem);
	}

}
