/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.Optional;

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
public final class Test extends BaseEntity implements Datable, Deletable, Derived, Describable,
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
	 * @param core The {@link EntityCore}.
	 */
	Test(EntityCore core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the {@link User} who is responsible for this test.
	 *
	 * @return {@code Optional} is empty if the data source does not provide
	 * 		any {@code User} entities at all, which is implementation
	 * 		specific!
	 */
	public Optional<User> getResponsiblePerson() {
		return Optional.ofNullable(getCore().getInfoRelation(User.class));
	}

	/**
	 * Sets new {@link User} as the responsible person for this test.
	 *
	 * @param responsiblePerson The new responsible person.
	 */
	public void setResponsiblePerson(User responsiblePerson) {
		getCore().setInfoRelation(responsiblePerson);
	}

}
