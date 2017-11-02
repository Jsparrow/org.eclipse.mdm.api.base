/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.BaseEntityManager;
import org.eclipse.mdm.api.base.query.DataAccessException;

/**
 * Implementation of the test step entity type. A test step is an atomic
 * measurement task as part of a {@link Test}. The boundary conditions do not
 * change within a single test step, therefore every ordered test step has to be
 * fully described. It may have relations to {@link ContextRoot}s, which contain
 * the describing order data. Test steps may have a sort order in the context of
 * their parent {@code Test}, indicating the desired execution order. The name
 * of a test step should be chosen in a speaking way, because it is often used
 * in different contexts, e.g. as a link between the measurement data from the
 * device and the order in the database. Furthermore the name has to be unique
 * under the parent {@code Test}. Children of a test step are
 * {@link Measurement}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public class TestStep extends BaseEntity implements ContextDescribable, Datable, Deletable, Describable,
		FilesAttachable, Sortable, StatusAttachable, Tagable {

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

	/**
	 * The 'Optional' attribute name.
	 */
	public static final String ATTR_OPTIONAL = "Optional";

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param core
	 *            The {@link Core}.
	 */
	TestStep(Core core) {
		super(core);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Returns the optional flag of this test step.
	 *
	 * @return Returns the optional flag.
	 */
	public Boolean isOptional() {
		return getValue(ATTR_OPTIONAL).extract();
	}

	/**
	 * Sets new optional flag for this test step.
	 *
	 * @param optional
	 *            The new optional flag.
	 */
	public void setOptional(Boolean optional) {
		getValue(ATTR_OPTIONAL).set(optional);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ContextType> loadContextTypes(BaseEntityManager manager)
			throws DataAccessException {
		return manager.loadContextTypes(this);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<ContextType, ContextRoot> loadContexts(BaseEntityManager manager,
			ContextType... contextTypes) throws DataAccessException {
		return manager.loadContexts(this, contextTypes);
	}

}
