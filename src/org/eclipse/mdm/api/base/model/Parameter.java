/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

/**
 * Implementation of the parameter data item type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ParameterSet
 */
public final class Parameter extends AbstractDataItem implements Deletable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'ValueType' attribute name.
	 */
	public static final String ATTR_VALUE_TYPE = "DataType";

	/**
	 * The 'Value' attribute name.
	 */
	public static final String ATTR_VALUE = "Value";

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
	private Parameter(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	public Value getValue() {
		// TODO create a virtual Value out of ParameterValue, ValueType and Unit
		return null;
	}

	public void setValue(Value value, Unit unit) {
		// TODO replace current setting of ParameterValue, ValueType and Unit
	}

	/**
	 * Returns the stored value as a {@code String}. For parsing its real
	 * type must be retrieved using {@link #getValueTypeEnum()}.
	 *
	 * @return Returns the value in its {@code String} representation.
	 */
	@Deprecated // TODO will be omitted or made private
	public String getParameterValue() {
		return getValue(ATTR_VALUE).extract();
	}

	/**
	 * Sets a new value where its real type must be compatible with the type
	 * returned by {@link #getValueTypeEnum()}. Note that the current real
	 * type can be overwritten using {@link #setValueTypeEnum(Integer)}.
	 *
	 * @param value New value in its {@code String} representation.
	 */
	@Deprecated // TODO will be omitted or made private
	public void setParameterValue(String value) {
		getValue(ATTR_VALUE).set(value);
	}

	//TODO: mapping enum values
	@Deprecated // TODO will be omitted or made private
	public Integer getValueTypeEnum() {
		return getValue(ATTR_VALUE_TYPE).extract();
	}

	//TODO: mapping enum values
	@Deprecated // TODO will be omitted or made private
	public void setValueTypeEnum(Integer valueType) {
		getValue(ATTR_VALUE_TYPE).set(valueType);
	}

	@Deprecated // TODO will be omitted or made private
	public Unit getUnit() {
		return getRelatedDataItem(Unit.class);
	}

	@Deprecated // TODO will be omitted or made private
	public void setUnit(Unit unit) {
		// TODO it must be allowed to unset a unit (pass null!)
		setRelatedDataItem(unit);
	}

	@Override
	public String toString() {
		/*
		 * TODO: Parameter(value = xyx [unit]) NOTHING more?!
		 *
		 * or
		 *
		 * TODO: Parameter(value = xyx, valueType = DT_XY, Unit(name="blah", ...))
		 */
		return super.toString();
	}

}
