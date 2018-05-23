/*
 * Copyright (c) 2017 Florian Schmitt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Objects;

/**
 * This class emulates the behaviour of a java enum. The reason for its
 * existence is that classic enums can't be extended by new values. This class
 * on the other hand can be extended by java's inheritance mechanism.
 * 
 * For a basic emulation of java enums, just extend this class and add static
 * fields whose type is of the derivative class.
 * 
 * To be able to add dynamically enum values and for full initialization, you
 * should set an owner enumeration (either by choosing the right constructor or
 * by calling the setOwner method)
 * 
 * @author Florian Schmitt
 *
 */
public class EnumerationValue {

	private String name;
	private Integer ordinal;

	private Enumeration<? extends EnumerationValue> owner;

	/**
	 * explicitly set the name of this element.
	 *
	 * @param name
	 */
	protected void setName(String name) {
		this.name = Objects.requireNonNull(name);
	}

	/**
	 * explicitly set the ordinal of this element.
	 */
	protected void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	/**
	 * 
	 * set the owner dynamic enumeration of this object.
	 * 
	 * @param er
	 */
	protected void setOwner(Enumeration<? extends EnumerationValue> er) {
		owner = er;
	}

	/**
	 * get the owner dynamic enumeration of this object
	 * 
	 * @return
	 */
	public Enumeration<? extends EnumerationValue> getOwner() {
		return owner;
	}

	/**
	 * This Constructor is protected to avoid accidental misuse.
	 * 
	 * be sure to initialize the enumeration fully, by either adding it as a
	 * static field in an extending class, or setting the ordinal by hand.
	 * You'll also have to add the resulting object to a DynamicEnumeration for
	 * it to be completely usable.
	 * 
	 * @param name
	 */
	protected EnumerationValue(String name) {
		this(name, null);
	}

	/**
	 * This Constructor is protected to avoid accidental misuse.
	 * 
	 * You'll have to add the resulting object to a Enumeration for it to be
	 * completely usable.
	 * 
	 * @param name
	 */
	protected EnumerationValue(String name, Integer ordinal) {
		this.name = Objects.requireNonNull(name, "Name of an EnumerationValue can never be null!");
		this.ordinal = ordinal;
		this.owner = null;
	}

	/**
	 * @return the name of this enumeration value
	 */
	public String name() {
		return name;
	}

	/**
	 * @return the enumeration value represented by the given name
	 */
	public EnumerationValue valueOf(String name) {
		return owner.valueOf(name);
	}

	/**
	 * @return the ordinal value represented by this enumeration value
	 */
	public Integer ordinal() {
		return ordinal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name();
	}

}
