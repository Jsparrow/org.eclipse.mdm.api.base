/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/


package org.eclipse.mdm.api.base.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This singleton class registers globally available enumerations.
 *
 */
public final class EnumRegistry {

	// singleton instance
	private static final EnumRegistry instance = new EnumRegistry();

	public static final String SCALAR_TYPE = "ScalarType";
	public static final String INTERPOLATION = "Interpolation";
	public static final String SEQUENCE_REPRESENTATION = "SequenceRepresentation";
	public static final String TYPE_SPECIFICATION = "TypeSpecification";
	public static final String VALUE_TYPE = "ValueType";
	public static final String VERSION_STATE = "VersionState";
	public static final String AXIS_TYPE = "AxisType";

	private Map<String, Enumeration<? extends EnumerationValue>> enumerations;

	/**
	 * Constructor. Not called directly. Use getInstance() instead.
	 */
	private EnumRegistry() {
		enumerations = new HashMap<>();
		add(INTERPOLATION, new Enumeration<Interpolation>(Interpolation.class, INTERPOLATION));
		add(SCALAR_TYPE, new Enumeration<ScalarType>(ScalarType.class, SCALAR_TYPE));
		add(SEQUENCE_REPRESENTATION,
				new Enumeration<SequenceRepresentation>(SequenceRepresentation.class, SEQUENCE_REPRESENTATION));
		add(TYPE_SPECIFICATION, new Enumeration<TypeSpecification>(TypeSpecification.class, TYPE_SPECIFICATION));
		add(VALUE_TYPE, new Enumeration<ValueType>(ValueType.class, VALUE_TYPE));
		add(VERSION_STATE, new Enumeration<VersionState>(VersionState.class, VERSION_STATE));
		add(AXIS_TYPE, new Enumeration<AxisType>(AxisType.class, AXIS_TYPE));
	}

	/**
	 * adds a new enumeration to the registry
	 * 
	 * @param name
	 *            the name under which the enumeration can be accessed
	 * @param enumeration
	 *            the dynamic enumeration object
	 */
	public void add(String name, Enumeration<? extends EnumerationValue> enumeration) {
		enumerations.put(name, enumeration);
	}

	/**
	 * @param name
	 *            the name of the registered enumeration
	 * @return the dynamic enumeration object
	 */
	public Enumeration<?> get(String name) {
		return enumerations.get(name);
	}

	/**
	 * @return the instance of this singleton
	 */
	public static EnumRegistry getInstance() {
		return instance;
	}
}
