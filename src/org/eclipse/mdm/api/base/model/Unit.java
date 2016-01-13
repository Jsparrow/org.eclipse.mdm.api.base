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
 * Implementation of the unit data item type. Units referring to the same
 * {@link PhysicalDimension} can be converted to each other by means of their
 * attribute values "Offset" and "Factor". Names of the units must be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see PhysicalDimension
 */
public final class Unit extends AbstractDataItem implements Copyable, Datable, Deletable, Describable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'Factor' attribute name.
	 */
	public static final String ATTR_FACTOR = "Factor";

	/**
	 * The 'Offset' attribute name.
	 */
	public static final String ATTR_OFFSET= "Offset";

	/**
	 * The 'DB' attribute name.
	 */
	public static final String ATTR_DB = "dB";

	/**
	 * The 'DBRreferenceFactor' attribute name.
	 */
	public static final String ATTR_DB_REFERENCE_FACTOR = "dB_reference_factor";

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
	private Unit(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the factor of this unit.
	 *
	 * @return The factor is returned.
	 */
	public Double getFactor() {
		return getValue(ATTR_FACTOR).extract();
	}

	/**
	 * Sets new factor for this unit.
	 *
	 * @param factor The new factor.
	 */
	public void setFactor(Double factor) {
		getValue(ATTR_FACTOR).set(factor);
	}

	/**
	 * Returns the offset of this unit.
	 *
	 * @return The offset is returned.
	 */
	public Double getOffset() {
		return getValue(ATTR_OFFSET).extract();
	}

	/**
	 * Sets new offset for this unit.
	 *
	 * @param offset The new offset.
	 */
	public void setOffset(Double offset) {
		getValue(ATTR_OFFSET).set(offset);
	}

	/**
	 * Returns the decibel flag of this unit. If the flag is true, then
	 * {@link #getDBRefFactor()} should return a finite value as returned
	 * by {@link Float#isFinite(float)} that is not 0.
	 *
	 * @return True if this unit is a decibel unit.
	 * @see #getDBRefFactor()
	 */
	public Boolean isDB() {
		return getValue(ATTR_DB).extract();
	}

	/**
	 * Returns the factor, which allows to convert the decibel values back
	 * into linear values. If the decibel flag is set to false, this method
	 * should return 0. This is described in the ASAM NVH specification
	 * (Chapter 11, 10.3).
	 *
	 * @return The decibel reference factor is returned.
	 * @see #isDB()
	 */
	public Float getDBRefFactor() {
		return getValue(ATTR_DB_REFERENCE_FACTOR).extract();
	}

	/**
	 * Changes the decibel status of this unit. The decibel flag is deduced from
	 * passed reference factor. If the reference factor is finite, as returned
	 * by {@link Float#isFinite(float)}, and not 0, then the decibel flag is set
	 * to true and passed reference factor is taken. In any other case both, the
	 * decibel flag and the reference factor, will be reset.
	 *
	 * @param dbReferenceFactor The new decibel reference factor.
	 */
	public void setDB(Float dbReferenceFactor) {
		boolean isValid = dbReferenceFactor != null && Float.isFinite(dbReferenceFactor) &&
				dbReferenceFactor.floatValue() != 0;
		getValue(ATTR_DB).set(isValid);

		if(isValid) {
			getValue(ATTR_DB_REFERENCE_FACTOR).set(dbReferenceFactor);
		} else {
			getValue(ATTR_DB_REFERENCE_FACTOR).setValid(false);
		}
	}

	/**
	 * Returns the related default {@link PhysicalDimension} data item for
	 * this unit.
	 *
	 * @return Related default {@code PhysicalDimension} data item is returned.
	 */
	public PhysicalDimension getPhysicalDimension() {
		return getRelatedDataItem(PhysicalDimension.class);
	}

	/**
	 * Sets new related default {@link PhysicalDimension} data item for this unit.
	 *
	 * @param physicalDimension The new related {@code PhysicalDimension} data item.
	 */
	public void setPhysicalDimension(PhysicalDimension physicalDimension) {
		setRelatedDataItem(physicalDimension);
	}

}
