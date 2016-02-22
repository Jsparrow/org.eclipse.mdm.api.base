/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.mdm.api.base.massdata.ReadRequest;
import org.eclipse.mdm.api.base.massdata.WriteRequest;
import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ContextDescribable;
import org.eclipse.mdm.api.base.model.ContextRoot;
import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.DataItem;
import org.eclipse.mdm.api.base.model.Deletable;
import org.eclipse.mdm.api.base.model.Environment;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.model.Measurement;
import org.eclipse.mdm.api.base.model.ParameterSet;
import org.eclipse.mdm.api.base.model.Parameterizable;
import org.eclipse.mdm.api.base.model.TestStep;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.User;
import org.eclipse.mdm.api.base.model.factory.BaseEntityFactory;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.eclipse.mdm.api.base.query.ModelManager;
import org.eclipse.mdm.api.base.query.SearchService;

/**
 * Provides business layer CRUD operations and services (CREATE, READ, UPDATE, INSERT).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface BaseDataProvider {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * The returned service creates new {@link DataItem} instances.
	 *
	 * @param transactionID TODO
	 * @return {@code Optional} is empty if no such service is available.
	 * @throws DataAccessException Thrown if a transaction with given ID does not exist.
	 * @see BaseEntityFactory
	 */
	default Optional<BaseEntityFactory> getEntityFactory(String transactionID) throws DataAccessException {
		return Optional.empty();
	}

	/**
	 * The returned service provides advanced search capabilities for supported
	 * data item types.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see SearchService
	 */
	default Optional<SearchService> getSearchService() {
		return Optional.empty();
	}

	/**
	 * The returned service provides access to the low level query API and the
	 * application model's meta data.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see ModelManager
	 */
	default Optional<ModelManager> getModelManager() {
		return Optional.empty();
	}

	/**
	 * Returns the {@link Environment} data item this data provider is connected to.
	 *
	 * @return The {@code Environment} data item is returned.
	 * @throws DataAccessException Thrown if unable to retrieve {@link DataItem}.
	 */
	Environment getEnvironment() throws DataAccessException;

	/**
	 * Returns the {@code User} data item this data provider has used to connect to
	 * the underlying data source.
	 *
	 * @return {@code Optional} is empty if the data source connection is user
	 * 		independent, which is implementation specific.
	 * @throws DataAccessException Thrown if unable to retrieve {@link DataItem}.
	 */
	default Optional<User> getLoggedOnUser() throws DataAccessException {
		return Optional.empty();
	}

	/**
	 * Loads the {@link DataItem} identified by passed {@link URI}.
	 *
	 * @param <T> The desired type.
	 * @param uri The data item identifier.
	 * @return {@code Optional} is empty if data item was not found.
	 * @throws DataAccessException Thrown if unable to retrieve {@link DataItem}.
	 */
	<T extends DataItem> Optional<T> findByURI(URI uri) throws DataAccessException;

	/**
	 * Loads the parent {@link DataItem} for passed child. Each modeled data item provides
	 * public fields for available parent types e.g.: {@link TestStep#PARENT_TYPE_TEST}.
	 *
	 * <pre>{@code
	 * Optional<Test> parentTest = dataProvider.findParent(testStep, TestStep.PARENT_TYPE_TEST);
	 * }</pre>
	 *
	 * @param <T> The desired parent type.
	 * @param child The child data item.
	 * @param parentType The desired parent type.
	 * @return {@code Optional} is empty if parent data item was not found.
	 * @throws DataAccessException Thrown if unable to retrieve {@link DataItem}.
	 */
	<T extends DataItem> Optional<T> findParent(DataItem child, Class<T> parentType) throws DataAccessException;

	/**
	 * Loads all available {@link DataItem}s of passed type. This method is useful while working
	 * with types whose amount is known to be fairly small (e.g. {@code Unit}, {@code Quantity},
	 * etc.). If a type is passed where thousand instances exist (e.g. {@code Test}) the behavior
	 * of this method is implementation dependent. The following invocation might take its time
	 * to complete or even result in a {@link DataAccessException} as soon as too many results
	 * are found:
	 *
	 * <pre>{@code
	 * List<Test> tests = dataProvider.get(Test.class);
	 * }</pre>
	 *
	 * @param <T> The desired type.
	 * @param type Type of the returned data items.
	 * @return Returned {@code List} contains all available data items.
	 * @throws DataAccessException Thrown if unable to retrieve {@link DataItem}s.
	 */
	<T extends DataItem> List<T> get(Class<T> type) throws DataAccessException;

	/**
	 * Loads all related children of passed type for given parent {@link DataItem}. Each modeled
	 * data item provides public fields for available child types e.g.:
	 * {@link Measurement#CHILD_TYPE_CHANNEL}.
	 *
	 * <pre>{@code
	 * List<Channel> channels = dataProvider.getChildren(measurement, Measurement.CHILD_TYPE_CHANNEL);
	 * }</pre>
	 *
	 * @param <T> The desired child type.
	 * @param parent The parent data item.
	 * @param childType The desired child type.
	 * @return Returned {@code List} contains all related data items.
	 * @throws DataAccessException Thrown if unable to retrieve {@link DataItem}s.
	 */
	<T extends DataItem> List<T> getChildren(DataItem parent, Class<T> childType) throws DataAccessException;

	/**
	 * Loads the requested {@link ContextRoot}s for given {@link ContextDescribable} data item.
	 *
	 * @param contextDescribable Either a {@link TestStep} or {@link Measurement} data item.
	 * @param contextTypes The requested context types. If omitted, all types are be loaded.
	 * @return The ordered contexts for passed {@code TestStep} or the measured ones for
	 * 		{@code Measurement} are returned in a {@code Map}.
	 * @throws DataAccessException Thrown if unable to retrieve the desired contexts.
	 */
	Map<ContextType, ContextRoot> getContexts(ContextDescribable contextDescribable, ContextType... contextTypes)
			throws DataAccessException;

	/**
	 * Loads all related {@link ParameterSet}s for given {@link Parameterizable} data item.
	 *
	 * @param parameterizable Either a {@link Measurement} or {@link Channel} data item.
	 * @return Returns all related {@link ParameterSet}s in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the {@link ParameterSet}s.
	 */
	List<ParameterSet> getParameterSets(Parameterizable parameterizable) throws DataAccessException;

	/**
	 * Retrieves the {@link MeasuredValues} as specified by the passed {@link ReadRequest}.
	 *
	 * @param readRequest Provides all required informations to process the request.
	 * @return Returns the {@code MeasuredValues} in a {@code List} as specified in the
	 * 		passed {@code ReadRequest}.
	 * @throws DataAccessException Thrown if unable to access the measured values.
	 * @see ReadRequest
	 * @see MeasuredValues
	 */
	List<MeasuredValues> readMeasuredValues(ReadRequest readRequest) throws DataAccessException;

	/**
	 * Creates {@link MeasuredValues} as specified by the passed {@link WriteRequest}s.
	 *
	 * @param transactionID TODO
	 * @param writeRequests Provides all required informations to process the request.
	 * @throws DataAccessException Thrown if unable to create specified measured values.
	 * @see WriteRequest
	 * @see MeasuredValues
	 */
	void writeMeasuredValues(String transactionID, List<WriteRequest> writeRequests) throws DataAccessException;

	String startTransaction() throws DataAccessException;

	void commitTransaction(String transactionID) throws DataAccessException;

	void abortTransaction(String transactionID) throws DataAccessException;

	<T extends DataItem> void update(String transactionID, List<T> dataItems) throws DataAccessException;

	<T extends Deletable> List<URI> delete(String transactionID, List<T> dataItems) throws DataAccessException;

}
