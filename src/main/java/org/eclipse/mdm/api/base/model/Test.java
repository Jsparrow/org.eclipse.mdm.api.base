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

import java.util.List;
import java.util.Optional;

import org.eclipse.mdm.api.base.adapter.Core;

/**
 * Implementation of the test entity type. The test groups a number of single
 * {@link TestStep}s. A measurement order always corresponds with exactly one
 * test entity. The name of a test should be chosen in a speaking way, because
 * it is often used in different contexts, e.g. as a link between the
 * measurement data from the device and the order in the database. This type is
 * a root node for persisted tests within a base application model. Extensions
 * are free to introduce new entity types that may act as an immediate parent
 * for entities of this type. In such cases tests have to be unique under the
 * corresponding parent. Children of a test are {@code TestStep}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class Test extends BaseEntity
		implements Datable, Deletable, Describable, FilesAttachable, Tagable, StatusAttachable {

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
	 * @param core
	 *            The {@link Core}.
	 */
	Test(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link User} who is responsible for this test.
	 *
	 * @return {@code Optional} is empty if the data source does not provide any
	 *         {@code User} entities at all, which is implementation specific!
	 */
	public Optional<User> getResponsiblePerson() {
		return Optional.ofNullable(getCore().getMutableStore().get(User.class));
	}

	/**
	 * Sets new {@link User} as the responsible person for this test.
	 *
	 * @param responsiblePerson
	 *            The new responsible person.
	 */
	public void setResponsiblePerson(User responsiblePerson) {
		getCore().getMutableStore().set(responsiblePerson);
	}

	/**
	 * Returns the commissioned {@link TestStep} identified by given name.
	 *
	 * @param name
	 *            The name of the {@code TestStep}.
	 * @return The {@code Optional} is empty if a {@code TestStep} with given
	 *         name does not exist.
	 */
	public Optional<TestStep> getCommissionedTestStep(String name) {
		return getCommissionedTestSteps().stream().filter(ts -> ts.nameEquals(name)).findAny();
	}

	/**
	 * Returns all commissioned {@link TestStep}s related to this test.
	 *
	 * @return The returned {@code List} is unmodifiable.
	 */
	public List<TestStep> getCommissionedTestSteps() {
		return getCore().getChildrenStore().get(TestStep.class);
	}

	/**
	 * Removes the commissioned {@link TestStep} identified by given name.
	 *
	 * @param name
	 *            Name of the {@code TestStep} that has to be removed.
	 * @return Returns {@code true} if the {@code TestStep} with given name has
	 *         been removed.
	 */
	public boolean removeCommissionedTestStep(String name) {
		Optional<TestStep> testStep = getCommissionedTestStep(name);
		if (!testStep.isPresent()) {
			return false;
		}
		getCore().getChildrenStore().remove(testStep.get());
		return true;
	}

}
