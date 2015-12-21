/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

/**
 * Implementation of the quantity data item type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Unit
 */
public final class Quantity extends AbstractDataItem implements Copyable, Datable, Deletable, Describable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'DefaultValueType' attribute name.
	 */
	public static final String ATTR_DEFAULT_VALUE_TYPE = "DefDataType";

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
	private Quantity(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the default value type for this quantity.
	 *
	 * @return Default value type is returned.
	 */
	//TODO: cast integer value to enumeration Value (ValueType)
	public Integer getDefaultValueTypeEnum() {
		return getValue(ATTR_DEFAULT_VALUE_TYPE).extract();
	}

	/**
	 * Sets new default value type for this quantity.
	 *
	 * @param defaultValueType The new default value type.
	 */
	//TODO: same as above
	public void setDefaultValueTypeEnum(Integer defaultValueType) {
		getValue(ATTR_DEFAULT_VALUE_TYPE).set(defaultValueType);
	}

	/**
	 * Returns the related default {@link Unit} data item for this quantity.
	 *
	 * @return Related default {@code Unit} data item is returned.
	 */
	public Unit getDefaultUnit() {
		return getRelatedDataItem(Unit.class);
	}

	/**
	 * Sets new related default {@link Unit} data item for this quantity.
	 *
	 * @param defaultUnit The new related default {@code Unit} data item.
	 */
	public void setDefaultUnit(Unit defaultUnit) {
		setRelatedDataItem(defaultUnit);
	}

}
