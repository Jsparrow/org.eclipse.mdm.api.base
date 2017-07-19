/*
 * Copyright (c) 2017 Florian Schmitt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * A class which dynamically bundles enumeration values
 *
 * @param <E>
 */
public class Enumeration<E extends EnumerationValue> {
	public E ens = null;

	private HashMap<String, E> values;

	private HashMap<E, String> revvalues;

	private HashMap<Integer, String> ordinals;

	private HashMap<String, Integer> revordinals;

	private int nextordinal;

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
		this.nextordinal = 0;
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
				((EnumerationValue) fieldObject).setOwner(this);
				values.put(fname, (E) fieldObject);
				revvalues.put((E) fieldObject, fname);
				ordinals.put(nextordinal, fname);
				revordinals.put(fname, nextordinal);
				nextordinal++;
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
		String name = enumeration.name();
		int ordinal = enumeration.ordinal();
		values.put(name, enumeration);
		revvalues.put(enumeration, name);
		ordinals.put(ordinal, name);
		revordinals.put(name, ordinal);
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
