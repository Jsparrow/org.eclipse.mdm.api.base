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
import org.eclipse.mdm.api.base.model.Deletable;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Environment;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.model.Measurement;
import org.eclipse.mdm.api.base.model.ParameterSet;
import org.eclipse.mdm.api.base.model.Parameterizable;
import org.eclipse.mdm.api.base.model.TestStep;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.User;
import org.eclipse.mdm.api.base.model.factory.EntityFactory;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.eclipse.mdm.api.base.query.ModelManager;
import org.eclipse.mdm.api.base.query.SearchService;

/**
 * Provides business layer CRUD operations and services (CREATE, READ, UPDATE,
 * INSERT).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface EntityManager {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * The returned service creates new entities.
	 *
	 * @param transactionID TODO
	 * @return {@code Optional} is empty if no such service is available.
	 * @throws DataAccessException Thrown if a transaction with given ID does not exist.
	 * @see EntityFactory
	 */
	default Optional<EntityFactory> getEntityFactory(String transactionID) throws DataAccessException {
		return Optional.empty();
	}

	/**
	 * The returned service provides advanced search capabilities for supported
	 * entity types.
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
	 * Returns the {@link Environment} this entity manager is connected to.
	 *
	 * @return The {@code Environment} is returned.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code
	 * 		Environment}.
	 */
	Environment loadEnvironment() throws DataAccessException;

	/**
	 * Returns the {@link User} which was used to connect to the underlying data
	 * source.
	 *
	 * @return {@code Optional} is empty if the data source connection is user
	 * 		independent, which is implementation specific.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code User}.
	 */
	default Optional<User> loadLoggedOnUser() throws DataAccessException {
		return Optional.empty();
	}

	/**
	 * Loads the entity identified by given {@link URI}.
	 *
	 * @param <T> The expected entity type.
	 * @param uri The entity identifier.
	 * @return {@code Optional} is empty if the entity could not be found.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code Entity}.
	 * @see URI
	 */
	<T extends Entity> Optional<T> load(URI uri) throws DataAccessException;

	/**
	 * Loads the parent entity for given child. Each modeled entity provides
	 * public fields for available parent entity types e.g.:
	 *
	 * <pre>{@code
	 * Optional<Test> parentTest = entityManager.findParent(testStep, TestStep.PARENT_TYPE_TEST);
	 * }</pre>
	 *
	 * @param <T> The desired parent type.
	 * @param child The child entity.
	 * @param parentType The desired parent entity type.
	 * @return {@code Optional} is empty if parent entity could not be found.
	 * @throws DataAccessException Thrown if unable to retrieve parent entity.
	 */
	<T extends Entity> Optional<T> loadParent(Entity child, Class<T> parentType) throws DataAccessException;

	/**
	 * Loads all available entities of given type. This method is useful while
	 * working with types whose amount is known to be fairly small (e.g.:
	 * {@code Unit}, {@code Quantity}, etc.). If a type is given where thousand
	 * instances exist (e.g.: {@code Test}) the behavior of this method is
	 * implementation specific. The following invocation might take its time
	 * to complete or even result in an exception as soon as too many results
	 * are found:
	 *
	 * <pre>{@code
	 * List<Test> tests = entityManager.get(Test.class);
	 * }</pre>
	 *
	 * @param <T> The desired type.
	 * @param type Type of the returned entities.
	 * @return Entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the entities.
	 * @see #load(Class, String)
	 */
	default <T extends Entity> List<T> load(Class<T> type) throws DataAccessException {
		return load(type, "*");
	}

	/**
	 * Loads all available entities of given type whose name fulfills the given
	 * pattern. This method is useful while working with types whose amount is
	 * known to be fairly small (e.g.: {@code Unit}, {@code Quantity}, etc.).
	 * If a type is given where thousand instances exist (e.g.: {@code Test})
	 * the behavior of this method is implementation specific. The following
	 * invocation might take its time to complete or even result in an exception
	 * as soon as too many results are found:
	 *
	 * <pre> {@code
	 * // retrieve all tests whose name starts with 'Example'
	 * List<Test> tests = entityManager.get(Test.class, "Example*");
	 * }</pre>
	 *
	 * @param <T> The desired type.
	 * @param type Type of the returned entities.
	 * @param pattern Is always case sensitive and may contain wildcard
	 * 		characters as follows: "?" for one matching character and "*"
	 * 		for a sequence of matching characters.
	 * @return Matched entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the entities.
	 * @see #load(Class)
	 */
	<T extends Entity> List<T> load(Class<T> type, String pattern) throws DataAccessException;

	/**
	 * Loads all related children of given type for given parent entity. Each
	 * modeled entity provides public fields for available child entity types
	 * e.g.:
	 *
	 * <pre>{@code
	 * List<Channel> channels = entityManager.getChildren(measurement, Measurement.CHILD_TYPE_CHANNEL);
	 * }</pre>
	 *
	 * @param <T> The desired child type.
	 * @param parent The parent entity.
	 * @param childType The desired child entity type.
	 * @return Related child entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the children.
	 * @see #loadChildren(Entity, Class, String)
	 */
	default <T extends Entity> List<T> loadChildren(Entity parent, Class<T> childType) throws DataAccessException {
		return loadChildren(parent, childType, "*");
	}

	/**
	 * Loads all related children of given type for given parent entity whose
	 * name fulfills the given pattern. Each modeled entity provides public
	 * fields for available child entity types e.g.:
	 *
	 * <pre>{@code
	 * List<TestStep> testSteps = entityManager.getChildren(test, Test.CHILD_TYPE_TESTSTEP, "Example*");
	 * }</pre>
	 *
	 * @param <T> The desired child type.
	 * @param parent The parent entity.
	 * @param childType The desired child entity type.
	 * @param pattern Is always case sensitive and may contain wildcard
	 * 		characters as follows: "?" for one matching character and "*"
	 * 		for a sequence of matching characters.
	 * @return Matched child entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the children.
	 * @see #loadChildren(Entity, Class)
	 */
	<T extends Entity> List<T> loadChildren(Entity parent, Class<T> childType, String pattern) throws DataAccessException;

	/**
	 * Loads the requested {@link ContextRoot}s for given {@link ContextDescribable}.
	 *
	 * @param contextDescribable Either a {@link TestStep} or {@link Measurement}.
	 * @param contextTypes The requested context types. If omitted, all types are
	 * 		be loaded.
	 * @return The ordered contexts for passed {@code TestStep} or the measured ones
	 * 		for {@code Measurement} are returned in a {@code Map}.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code
	 * 		ContextRoot}s.
	 * @see ContextType
	 */
	Map<ContextType, ContextRoot> loadContexts(ContextDescribable contextDescribable, ContextType... contextTypes)
			throws DataAccessException;

	/**
	 * Loads all related {@link ParameterSet}s for given {@link Parameterizable}.
	 *
	 * @param parameterizable Either a {@link Measurement} or {@link Channel}.
	 * @return Returns all related {@code ParameterSet}s in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code ParameterSet}s.
	 * @see #loadParameterSets(Parameterizable, String)
	 */
	default List<ParameterSet> loadParameterSets(Parameterizable parameterizable) throws DataAccessException {
		return loadParameterSets(parameterizable, "*");
	}

	/**
	 * Loads all {@link ParameterSet}s whose name fulfills given pattern and
	 * are related to given {@link Parameterizable}.
	 *
	 * @param parameterizable Either a {@link Measurement} or {@link Channel}.
	 * @param pattern Is always case sensitive and may contain wildcard
	 * 		characters as follows: "?" for one matching character and "*"
	 * 		for a sequence of matching characters.
	 * @return Returns all matched {@code ParameterSet}s in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code ParameterSet}s.
	 * @see #loadParameterSets(Parameterizable)
	 */
	List<ParameterSet> loadParameterSets(Parameterizable parameterizable, String pattern) throws DataAccessException;

	/**
	 * Retrieves the {@link MeasuredValues} as specified by the passed {@link ReadRequest}.
	 *
	 * @param readRequest Provides all required informations to process the request.
	 * @return Returns the {@code MeasuredValues} in a {@code List} as specified in the
	 * 		passed {@code ReadRequest}.
	 * @throws DataAccessException Thrown if unable to access the measured values.
	 */
	List<MeasuredValues> readMeasuredValues(ReadRequest readRequest) throws DataAccessException;

	/**
	 * Creates {@link MeasuredValues} as specified by the passed {@link WriteRequest}s.
	 *
	 * @param transactionID TODO
	 * @param writeRequests Provides all required informations to process the request.
	 * @throws DataAccessException Thrown if unable to create specified measured values.
	 */
	void writeMeasuredValues(String transactionID, List<WriteRequest> writeRequests) throws DataAccessException;

	String startTransaction() throws DataAccessException;

	void commitTransaction(String transactionID) throws DataAccessException;

	void abortTransaction(String transactionID) throws DataAccessException;

	<T extends Entity> void update(String transactionID, List<T> entities) throws DataAccessException;

	<T extends Deletable> List<URI> delete(String transactionID, List<T> entities) throws DataAccessException;

	void close() throws ConnectionException;

}
