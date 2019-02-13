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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * A class which dynamically bundles enumeration values
 *
 * @param <E>
 */
public class Enumeration<E extends EnumerationValue> {

	private Map<String, E> values;

	private Map<E, String> revvalues;

	private Map<Integer, String> ordinals;

	private Map<String, Integer> revordinals;

	private int maxordinal;

	private String name;

	private Class<E> enumclass;

	/**
	 * Constructor. Static fields in the given enumclass of the same type are
	 * added automatically as enum values. This way you can simply prefill the
	 * object with static values.
	 * 
	 * @param enumclass
	 *            the class of the enumeration values
	 * @param name
	 *            the name of the enumeration bundle
	 */
	@SuppressWarnings("unchecked")
	public Enumeration(Class<E> enumclass, String name) {
		this.maxordinal = 0;
		this.values = new HashMap<>();
		this.revvalues = new HashMap<>();
		this.ordinals = new HashMap<>();
		this.revordinals = new HashMap<>();
		this.name = name;
		this.enumclass = enumclass;
		java.lang.reflect.Field[] fields = enumclass.getFields();
		for (Field field : fields) {
			if (java.lang.reflect.Modifier.isStatic(field.getModifiers())) // only
																			// examine
																			// static
																			// fields
			{
				Object fieldObject;
				try {
					fieldObject = field.get(null);
					if (!(enumclass.isInstance(fieldObject))) {
						continue;
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				String fname = ((EnumerationValue) fieldObject).name();
				if (fname == null) {
					fname = field.getName();
					((EnumerationValue) fieldObject).setName(fname);
				}
				addValue((E) fieldObject);				
			}
		}
	}

	/**
	 * adds a value to the dynamic enumeration
	 * 
	 * @param enumeration
	 */
	public void addValue(E enumeration) {
		enumeration.setOwner(this);
		String enumerationName = enumeration.name();
		Integer ordinal = enumeration.ordinal();
		if (ordinal == null) {
			ordinal = ++maxordinal;
			enumeration.setOrdinal(ordinal);
		} else {
			maxordinal = Math.max(ordinal, maxordinal);
		}
		values.put(enumerationName, enumeration);
		revvalues.put(enumeration, enumerationName);
		ordinals.put(ordinal, enumerationName);
		revordinals.put(enumerationName, ordinal);
	}

	/**
	 * returns the value represented by the given name
	 * 
	 * @param name
	 * @return
	 */
	public E valueOf(String name) {
		return values.get(name);
	}

	/**
	 * returns the value represented by the given number
	 * 
	 * @param ordinal
	 * @return
	 */
	public E valueOf(int ordinal) {
		return values.get(ordinals.get(ordinal));
	}

	/**
	 * returns the ordinal represented by the given enumeration value
	 * 
	 * @param enumval
	 * @return
	 */
	public int ordinal(EnumerationValue enumval) {
		return revordinals.get(revvalues.get(enumval));
	}

	/**
	 * @return the class of the enumeration values represented by this dynamic
	 *         enumeration
	 */
	public Class<E> getEnumClass() {
		return enumclass;
	}

	/**
	 * @return the name of the dynamic enumeration collection
	 */
	public String getName() {
		return name;
	}

}
