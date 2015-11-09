/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Date;
import java.util.Map;

import org.eclipse.mdm.api.base.marker.Deletable;
import org.eclipse.mdm.api.base.marker.Derived;
import org.eclipse.mdm.api.base.marker.Statable;
import org.eclipse.mdm.api.base.marker.Tagable;

public final class Test extends AbstractDataItem implements Describable, Datable, FilesAttachable, Derived, Deletable, Tagable, Statable {
	
	public static final Class<TestStep> CHILD_TYPE_TESTSTEP = TestStep.class;
	
	private Test(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);				
	}

	@Override
	public Date getDateCreated() {
		return super.getValue(ATTR_DATECREATED).getValue();
	}

	@Override
	public void setDateCreated(Date date) {
		super.getValue(ATTR_DATECREATED).setValue(date);
	}

	@Override
	public String getDescription() {
		return super.getValue(ATTR_DESCRIPTION).getValue();
	}

	@Override
	public void setDescription(String description) {
		super.getValue(ATTR_DESCRIPTION).setValue(description);
		
	}

	public User getResponsiblePerson() {
		return (User)super.references.get(User.class);
	}
}
