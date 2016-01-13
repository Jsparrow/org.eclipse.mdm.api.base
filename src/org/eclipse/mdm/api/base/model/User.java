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
 * Implementation of the user data item type.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class User extends AbstractDataItem implements Deletable, Describable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Surname' attribute name.
	 */
	public static final String ATTR_SURNAME = "Surname";

	/**
	 * The 'GivenName' attribute name.
	 */
	public static final String ATTR_GIVEN_NAME = "GivenName";

	/**
	 * The 'Department' attribute name.
	 */
	public static final String ATTR_DEPARTMENT = "Department";

	/**
	 * The 'Telephone' attribute name.
	 */
	public static final String ATTR_PHONE = "Telephone";

	/**
	 * The 'EMail' attribute name.
	 */
	public static final String ATTR_EMAIL = "E-Mail";

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
	private User(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the surname of this user.
	 *
	 * @return The surname is returned.
	 */
	public String getSurname() {
		return getValue(ATTR_SURNAME).extract();
	}

	/**
	 * Sets new surname for this user.
	 *
	 * @param surame The new surname.
	 */
	public void setSurname(String surname) {
		getValue(ATTR_SURNAME).set(surname);
	}

	/**
	 * Returns the given name of this user.
	 *
	 * @return The given name is returned.
	 */
	public String getGivenName() {
		return getValue(ATTR_GIVEN_NAME).extract();
	}

	/**
	 * Sets new given name for this user.
	 *
	 * @param givenName The new given name.
	 */
	public void setGivenName(String givenName) {
		getValue(ATTR_GIVEN_NAME).set(givenName);
	}

	/**
	 * Returns the department of this user.
	 *
	 * @return The department is returned.
	 */
	public String getDepartment() {
		return getValue(ATTR_DEPARTMENT).extract();
	}

	/**
	 * Sets new department for this user.
	 *
	 * @param department The new department.
	 */
	public void setDepartment(String department) {
		getValue(ATTR_DEPARTMENT).set(department);
	}

	/**
	 * Returns the phone number of this user.
	 *
	 * @return The phone number is returned.
	 */
	public String getPhone() {
		return getValue(ATTR_PHONE).extract();
	}

	/**
	 * Sets new phone number for this user.
	 *
	 * @param phone The new phone number.
	 */
	public void setPhone(String phone) {
		getValue(ATTR_PHONE).set(phone);
	}

	/**
	 * Returns the e-mail address of this user.
	 *
	 * @return The e-mail address is returned.
	 */
	public String getMail() {
		return getValue(ATTR_EMAIL).extract();
	}

	/**
	 * Sets new e-mail address for this user.
	 *
	 * @param eMail The new e-mail address.
	 */
	public void setMail(String eMail) {
		getValue(ATTR_EMAIL).set(eMail);
	}


}
