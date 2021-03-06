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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.BaseEntityManager;
import org.eclipse.mdm.api.base.adapter.Core;

/**
 * Implementation of the measurement entity type. The measurement entity holds
 * data of a measurement or an analysis. It is the linking point to the
 * following related data:
 *
 * <ul>
 * <li>To ensure any persisted measurement can always be reinterpreted it,
 * always should have relations to {@link ContextRoot}s that contain the
 * description of the test run. All measurement entities under the same parent
 * {@link TestStep} must reference the same {@code
 * 		ContextRoot}.</li>
 * <li>The results of a test run are accessible via the children of type
 * {@link ChannelGroup} and {@link Channel}.</li>
 * </ul>
 *
 * The name of a measurement should be chosen in a speaking way. It has to be
 * unique under the parent {@code TestStep}.
 * <p>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ParameterSet
 */
public class Measurement extends BaseEntity
		implements ContextDescribable, Datable, Deletable, Describable, FilesAttachable, Tagable, StatusAttachable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link TestStep} parent type.
	 */
	public static final Class<TestStep> PARENT_TYPE_TESTSTEP = TestStep.class;

	/**
	 * The {@link ChannelGroup} child type.
	 */
	public static final Class<ChannelGroup> CHILD_TYPE_CHANNELGROUP = ChannelGroup.class;

	/**
	 * The {@link ParameterSet} child type.
	 */
	public static final Class<ParameterSet> CHILD_TYPE_PARAMETERSET = ParameterSet.class;

	/**
	 * The {@link Channel} child type.
	 */
	public static final Class<Channel> CHILD_TYPE_CHANNEL = Channel.class;

	/**
	 * The 'MeasurementBegin' attribute name.
	 */
	public static final String ATTR_MEASUREMENT_BEGIN = "MeasurementBegin";

	/**
	 * The 'MeasurementEnd' attribute name.
	 */
	public static final String ATTR_MEASUREMENT_END = "MeasurementEnd";

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	Measurement(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the time stamp of the date when this measurement was started.
	 *
	 * @return Measurement execution start time stamp is returned.
	 */
	public LocalDateTime getMeasurementBegin() {
		return getValue(ATTR_MEASUREMENT_BEGIN).extract();
	}

	/**
	 * Sets new time stamp for the date when this measurement was started.
	 *
	 * @param measurementBegin
	 *            The new measurement start time stamp.
	 */
	public void setMeasurementBegin(LocalDateTime measurementBegin) {
		getValue(ATTR_MEASUREMENT_BEGIN).set(measurementBegin);
	}

	/**
	 * Returns the time stamp of the date when this measurement was finished.
	 *
	 * @return Measurement execution end time stamp is returned.
	 */
	public LocalDateTime getMeasurementEnd() {
		return getValue(ATTR_MEASUREMENT_END).extract();
	}

	/**
	 * Sets new time stamp for the date when this measurement was finished.
	 *
	 * @param measurementEnd
	 *            The new measurement execution end time stamp.
	 */
	public void setMeasurementEnd(LocalDateTime measurementEnd) {
		getValue(ATTR_MEASUREMENT_END).set(measurementEnd);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ContextType> loadContextTypes(BaseEntityManager manager) {
		return manager.loadContextTypes(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<ContextType, ContextRoot> loadContexts(BaseEntityManager manager,
			ContextType... contextTypes) {
		return manager.loadContexts(this, contextTypes);
	}

}
