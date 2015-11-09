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

public final class Unit extends AbstractDataItem implements Datable, Describable, Deletable {

	public static final String ATTR_FACTOR = "Factor";
	public static final String ATTR_OFFSET= "Offset";
	public static final String ATTR_DB = "dB";
	public static final String ATTR_DB_REF_FACTOR = "dB_reference_factor";
	
	private Unit(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> references) {
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

	@Override
	public Date getDateCreated() {
		return super.getValue(ATTR_DATECREATED).getValue();
	}

	@Override
	public void setDateCreated(Date date) {
		super.getValue(ATTR_DATECREATED).setValue(date);		
	}

	public Double getFactor() {
		return super.getValue(ATTR_FACTOR).getValue();
	}
	
	public void setFactor(Double factor) {
		super.getValue(ATTR_FACTOR).setValue(factor);
	}
	
	public Double getOffset() {
		return super.getValue(ATTR_OFFSET).getValue();
	}
	
	public void setOffset(Double offset) {
		super.getValue(ATTR_OFFSET).setValue(offset);
	}
	
	public Boolean isDB() {
		return super.getValue(ATTR_DB).getValue();
	}
	
	public void setDB(Boolean db) {
		super.getValue(ATTR_DB).setValue(db);
	}
	
	public Float getDBRefFactor() {
		return super.getValue(ATTR_DB_REF_FACTOR).getValue();
	}
	
	public void setDBRefFactor(Float dbRefFactor) {
		super.getValue(ATTR_DB_REF_FACTOR).setValue(dbRefFactor);
	}
	
	public PhysicalDimension getPhysicalDimension() {
		return (PhysicalDimension)super.references.get(PhysicalDimension.class);
	}
	
}
