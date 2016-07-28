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
import org.eclipse.mdm.api.base.model.BaseEntityFactory;
import org.eclipse.mdm.api.base.model.ContextDescribable;
import org.eclipse.mdm.api.base.model.ContextRoot;
import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.Environment;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.model.Measurement;
import org.eclipse.mdm.api.base.model.TestStep;
import org.eclipse.mdm.api.base.model.User;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.eclipse.mdm.api.base.query.ModelManager;
import org.eclipse.mdm.api.base.query.SearchService;

/**
 * Provides business layer CRUD operations and services (CREATE, READ, UPDATE,
 * INSERT).
 *
 * @param <S> Concrete type of the provided entity factory.
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface BaseEntityManager<S extends BaseEntityFactory> {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * The returned service creates new entities.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 */
	default Optional<S> getEntityFactory() {
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
	 * The returned service allows to download linked files from the file
	 * storage.
	 *
	 * @return {@code Optional} is empty if no such service is available.
	 * @see FileService
	 */
	default Optional<FileService> getFileService() {
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
	 * Loads the entity identified by given entity class and its instance ID.
	 *
	 * @param <T>  The desired type.
	 * @param entityClass Type of the returned entity.
	 * @param instanceID The instance ID.
	 * @return The entity with given instance ID is returned.
	 * @throws DataAccessException Thrown if unable to retrieve the entity.
	 */
	<T extends Entity> T load(Class<T> entityClass, Long instanceID) throws DataAccessException;

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
	 * @param entityClass Type of the returned entities.
	 * @return Entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the entities.
	 * @see #loadAll(Class, String)
	 */
	default <T extends Entity> List<T> loadAll(Class<T> entityClass) throws DataAccessException {
		return loadAll(entityClass, "*");
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
	 * <pre>{@code
	 * // retrieve all tests whose name starts with 'Example'
	 * List<Test> tests = entityManager.get(Test.class, "Example*");
	 * }</pre>
	 *
	 * @param <T> The desired type.
	 * @param entityClass Type of the returned entities.
	 * @param pattern Is always case sensitive and may contain wildcard
	 * 		characters as follows: "?" for one matching character and "*"
	 * 		for a sequence of matching characters.
	 * @return Matched entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the entities.
	 * @see #loadAll(Class)
	 */
	<T extends Entity> List<T> loadAll(Class<T> entityClass, String pattern) throws DataAccessException;

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
	 * @param entityClass The desired parent entity type.
	 * @return {@code Optional} is empty if parent entity could not be found.
	 * @throws DataAccessException Thrown if unable to retrieve parent entity.
	 */
	<T extends Entity> Optional<T> loadParent(Entity child, Class<T> entityClass) throws DataAccessException;

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
	 * @param entityClass The desired child entity type.
	 * @return Related child entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the children.
	 * @see #loadChildren(Entity, Class, String)
	 */
	default <T extends Entity> List<T> loadChildren(Entity parent, Class<T> entityClass) throws DataAccessException {
		return loadChildren(parent, entityClass, "*");
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
	 * @param entityClass The desired child entity type.
	 * @param pattern Is always case sensitive and may contain wildcard
	 * 		characters as follows: "?" for one matching character and "*"
	 * 		for a sequence of matching characters.
	 * @return Matched child entities are returned in a {@code List}.
	 * @throws DataAccessException Thrown if unable to retrieve the children.
	 * @see #loadChildren(Entity, Class)
	 */
	<T extends Entity> List<T> loadChildren(Entity parent, Class<T> entityClass, String pattern)
			throws DataAccessException;

	/**
	 * Queries available {@link ContextType} for given {@link
	 * ContextDescribable}.
	 *
	 * @param contextDescribable Either a {@link TestStep} or a {@link
	 * 		Measurement}
	 * @return {@code List} contains the {@code ContextType} of each referenced
	 * 		{@link ContextRoot}.
	 * @throws DataAccessException Thrown if unable to query the available
	 * 		{@code ContextType}s.
	 */
	List<ContextType> loadContextTypes(ContextDescribable contextDescribable) throws DataAccessException;

	/**
	 * Loads the requested {@link ContextRoot}s for given {@link ContextDescribable}.
	 *
	 * @param contextDescribable Either a {@link TestStep} or {@link Measurement}.
	 * @param contextTypes The requested context types. If omitted, all types are
	 * 		be loaded.
	 * @return The ordered contexts for given {@code TestStep} or the measured ones
	 * 		for {@code Measurement} are returned in a {@code Map}.
	 * @throws DataAccessException Thrown if unable to retrieve the {@code
	 * 		ContextRoot}s.
	 * @see ContextType
	 */
	Map<ContextType, ContextRoot> loadContexts(ContextDescribable contextDescribable, ContextType... contextTypes)
			throws DataAccessException;

	/**
	 * Retrieves the {@link MeasuredValues} as specified by the given {@link ReadRequest}.
	 *
	 * @param readRequest Provides all required informations to process the request.
	 * @return Returns the {@code MeasuredValues} in a {@code List} as specified in the
	 * 		given {@code ReadRequest}.
	 * @throws DataAccessException Thrown if unable to access the measured values.
	 */
	List<MeasuredValues> readMeasuredValues(ReadRequest readRequest) throws DataAccessException;

	/**
	 * Creates a new {@link Transaction} for modifying access.
	 *
	 * @return A new {@code Transaction} is returned.
	 * @throws DataAccessException Thrown if unable to create a new {@code Transaction}.
	 */
	Transaction startTransaction() throws DataAccessException;

	/**
	 * Closes the connection to the underlying data source.
	 *
	 * @throws ConnectionException Thrown in case of errors while closing the
	 * 		connection.
	 */
	void close() throws ConnectionException;

}
