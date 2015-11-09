/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

import org.eclipse.mdm.api.base.marker.Deletable;

public final class User extends AbstractDataItem implements Describable, Deletable {
	
	public static final String ATTR_SURNAME = "Surname";
	public static final String ATTR_GIVENNAME = "GivenName";
	public static final String ATTR_DEPARTMENT = "Department";
	public static final String ATTR_TELEPHONE = "Telephone";
	public static final String ATTR_EMAIL = "E-Mail";

	private User(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	@Override
	public String getDescription() {
		return super.getValue(ATTR_DESCRIPTION).getValue();
	}

	@Override
	public void setDescription(String description) {
		super.getValue(ATTR_DESCRIPTION).setValue(description);
	}
	
	public String getEMail() {
		return super.getValue(ATTR_EMAIL).getValue();
	}
	
	public void setEMail(String eMail) {
		super.getValue(ATTR_EMAIL).setValue(eMail);
	}
	
	public String getSurname() {
		return super.getValue(ATTR_SURNAME).getValue();
	}
	
	public void setSurname(String surname) {
		super.getValue(ATTR_SURNAME).setValue(surname);
	}
	
	public String getGivenName() {
		return super.getValue(ATTR_GIVENNAME).getValue();
	}
	
	public void setGivenName(String givenName) {
		super.getValue(ATTR_GIVENNAME).setValue(givenName);
	}
	
	public String getDepartment() {
		return super.getValue(ATTR_DEPARTMENT).getValue();
	}
	
	public void setDepartment(String department) {
		super.getValue(ATTR_DEPARTMENT).setValue(department);
	}
	
	public String getTelephone() {
		return super.getValue(ATTR_TELEPHONE).getValue();
	}
	
	public void setTelephone(String telephone) {
		super.getValue(ATTR_TELEPHONE).setValue(telephone);
	}
	
}
