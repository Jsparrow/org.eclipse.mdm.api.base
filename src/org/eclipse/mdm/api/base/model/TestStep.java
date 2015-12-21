/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;

/**
 * Implementation of the test step data item type. A test step is an atomic
 * measurement task as part of a {@link Test}. The boundary conditions do not
 * change within a single test step, therefore every ordered test step has to
 * be fully described. It may have relations to {@link ContextRoot}s, which
 * contain the describing order data. Test steps may have a sort order in the
 * context of their parent {@code Test}, indicating the desired execution
 * order. The name of a test step should be chosen in a speaking way, because
 * it is often used in different context, e.g. as a link between the
 * measurement data from the device and the order in the database. Furthermore
 * the name has to be unique under the parent {@code Test}. Children of a test
 * step are {@link Measurement} data items.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Test
 * @see Measurement
 * @see ContextRoot
 */
public final class TestStep extends AbstractDataItem implements ContextDescribable, Datable, Deletable, Derived,
Describable, FilesAttachable, Sortable, Statable, Tagable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link Test} parent type.
	 */
	public static final Class<Test> PARENT_TYPE_TEST = Test.class;

	/**
	 * The {@link Measurement} child type.
	 */
	public static final Class<Measurement> CHILD_TYPE_MEASUREMENT = Measurement.class;

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param values This data item's values.
	 * @param uri The data item identifier.
	 * @param relatedDataItems Related data item instances.
	 */
	private TestStep(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

}
