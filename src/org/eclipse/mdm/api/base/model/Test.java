/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Map;
import java.util.Optional;

/**
 * Implementation of the test data item type. The test groups a number of single
 * {@link TestStep}s. A measurement order always corresponds with exactly one
 * test data item. The name of a test should be chosen in a speaking way,
 * because it is often used in different contexts, e.g. as a link between the
 * measurement data from the device and the order in the database. This type is
 * a root node for persisted tests within a <u>base application model</u>.
 * Extensions of the <u>base model</u> are free to introduce new data item types
 * that may act as an immediate parent for instances of this type. In such cases
 * tests have to be unique under the corresponding parent. Children of a test are
 * {@link TestStep} data items.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see TestStep
 */
public final class Test extends AbstractDataItem implements Datable, Deletable, Derived, Describable,
FilesAttachable, Tagable, Statable {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The {@link TestStep} child type.
	 */
	public static final Class<TestStep> CHILD_TYPE_TESTSTEP = TestStep.class;

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
	private Test(Map<String, Value> values, URI uri, Map<Class<? extends DataItem>, DataItem> relatedDataItems) {
		super(values, uri, relatedDataItems);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link User} data item that states who is responsible for
	 * this test.
	 *
	 * @return {@code Optional} is empty if the data source does not provide
	 * 		any {@code User} data items at all, which is implementation
	 * 		specific!
	 */
	public Optional<User> getResponsiblePerson() {
		return Optional.ofNullable(getRelatedDataItem(User.class));
	}

	/**
	 * Sets a new {@link User} as the responsible person for this test.
	 *
	 * @param responsiblePerson The new responsible person is not allowed to be null.
	 */
	public void setResponsiblePerson(User responsiblePerson) {
		setRelatedDataItem(responsiblePerson);
	}

}
