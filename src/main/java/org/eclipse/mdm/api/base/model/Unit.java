/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * Implementation of the unit entity type. Units referring to the same {@link
 * PhysicalDimension} can be converted to each other by means of their attribute
 * values "Offset" and "Factor". The names of the units have to be unique.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class Unit extends BaseEntity implements Copyable, Datable, Deletable, Describable {

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

	public Unit(Core core) {
		super(core);
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
	 * should return 0, which is described in the ASAM NVH specification
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
	 * given reference factor. If the reference factor is finite, as returned
	 * by {@link Float#isFinite(float)}, and not 0, then the decibel flag is set
	 * to true and given reference factor is taken. In any other case both, the
	 * decibel flag and the reference factor, will be reset.
	 *
	 * @param dbReferenceFactor The new decibel reference factor.
	 */
	public void setDB(Float dbReferenceFactor) {
		boolean isValid = Float.isFinite(dbReferenceFactor)
				&& Float.compare(Math.abs(dbReferenceFactor), 0F) != 0;
		getValue(ATTR_DB).set(isValid);

		if(isValid) {
			getValue(ATTR_DB_REFERENCE_FACTOR).set(dbReferenceFactor);
		} else {
			getValue(ATTR_DB_REFERENCE_FACTOR).setValid(false);
		}
	}

	/**
	 * Returns the {@link PhysicalDimension} of this unit.
	 *
	 * @return The {@code PhysicalDimension} is returned.
	 */
	public PhysicalDimension getPhysicalDimension() {
		return getCore().getInfoRelation(PhysicalDimension.class);
	}

	/**
	 * Sets new {@link PhysicalDimension} for this unit.
	 *
	 * @param physicalDimension The new {@code PhysicalDimension}.
	 */
	public void setPhysicalDimension(PhysicalDimension physicalDimension) {
		getCore().setInfoRelation(physicalDimension);
	}

}
