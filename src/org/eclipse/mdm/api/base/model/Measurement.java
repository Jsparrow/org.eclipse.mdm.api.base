/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.eclipse.mdm.api.base.model.ChannelValuesReadRequest.MatrixSource;

/**
 * Implementation of the measurement data item type. The measurement data item
 * holds data of a measurement or analysis. It is the linking point to the
 * following related data:
 *
 * <ul>
 * 	<li>To ensure any persisted measurement can always be reinterpreted it,
 * 		always should have relations to {@link ContextRoot} data items, which
 * 		contain the description of the test run. All measurement data items
 * 		under the same parent {@link TestStep} should reference to the same
 * 		{@code ContextRoot} data items.</li>
 * 	<li>The results of a test run are accessible via the child data items of
 * 		type {@link ChannelGroup} and {@link Channel}.</li>
 * 	<li>Detailed storage of NVH data is possible using {@link ParameterSet}
 * 		data items.</li>
 * </ul>
 * The name of a measurement should be chosen in a speaking way. It has to be
 * unique under the parent {@code TestStep}.
 *
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see TestStep
 * @see ContextRoot
 * @see ChannelGroup
 * @see Channel
 * @see ParameterSet
 */
public final class Measurement extends AbstractDataItem implements ContextDescribable, Derived, Describable, Datable,
FilesAttachable, MatrixSource, Parameterizable, Statable, Tagable {

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
	 * @param values This data item's values.
	 * @param uri The data item identifier.
	 * @param relatedDataItems Related data item instances.
	 */
	private Measurement(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the timestamp of the date when the measurement was started.
	 *
	 * @return Measurement execution start timestamp is returned.
	 */
	public LocalDateTime getMeasurementBegin() {
		return getValue(ATTR_MEASUREMENT_BEGIN).extract();
	}

	/**
	 * Sets a new timestamp for the date when the measurement was started.
	 *
	 * @param measurementBegin The new measurement start timestamp.
	 */
	public void setMeasurementBegin(LocalDateTime measurementBegin) {
		getValue(ATTR_MEASUREMENT_BEGIN).set(measurementBegin);
	}

	/**
	 * Returns the timestamp of the date when the measurement was finished.
	 *
	 * @return Measurement execution end timestamp is returned.
	 */
	public LocalDateTime getMeasurementEnd() {
		return getValue(ATTR_MEASUREMENT_END).extract();
	}

	/**
	 * Sets a new timestamp for the date when the measurement was finished.
	 *
	 * @param measurementEnd The new measurement finish timestamp.
	 */
	public void setMeasurementEnd(LocalDateTime measurementEnd) {
		getValue(ATTR_MEASUREMENT_END).set(measurementEnd);
	}

}
