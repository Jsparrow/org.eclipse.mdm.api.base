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

import org.eclipse.mdm.api.base.marker.ContextDescribable;
import org.eclipse.mdm.api.base.marker.Deletable;
import org.eclipse.mdm.api.base.marker.Derived;
import org.eclipse.mdm.api.base.marker.Statable;
import org.eclipse.mdm.api.base.marker.Tagable;

public final class TestStep extends AbstractDataItem implements Describable, Datable, FilesAttachable, Sortable, ContextDescribable, 
	Deletable, Statable, Tagable, Derived {

	public static final Class<Measurement> CHILD_TYPE_MEASUREMENT = Measurement.class;
	
	private TestStep(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
		super(uri, values, references);
	}

	@Override
	public Integer getSortIndex() {
		return super.getValue(ATTR_SORTINDEX).getValue();
	}

	@Override
	public void setSortIndex(Integer sortIndex) {
		super.getValue(ATTR_SORTINDEX).setValue(sortIndex);
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

}
