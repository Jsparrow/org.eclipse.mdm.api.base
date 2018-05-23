/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.mdm.api.base.massdata.ReadRequest;
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

/**
 * Provides business layer CRUD operations and services (CREATE, READ, UPDATE,
 * INSERT).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public interface BaseEntityManager {

	/**
	 * Returns the {@link Environment} this entity manager is connected to.
	 *
	 * @return The {@code Environment} is returned.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the {@code
	 * 		Environment}.
	 */
	Environment loadEnvironment() throws DataAccessException;

	/**
	 * Returns the {@link User} which was used to connect to the underlying data
	 * source.
	 *
	 * @return {@code Optional} is empty if the data source connection is user
	 *         independent, which is implementation specific.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the {@code User}.
	 */
	default Optional<User> loadLoggedOnUser() throws DataAccessException {
		return Optional.empty();
	}

	/**
	 * Loads the entity identified by given entity class and its instance ID.
	 *
	 * @param <T>
	 *            The desired type.
	 * @param entityClass
	 *            Type of the returned entity.
	 * @param instanceID
	 *            The instance ID.
	 * @return The entity with given instance ID is returned.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the entity.
	 */
	default <T extends Entity> T load(Class<T> entityClass, String instanceID) throws DataAccessException {
		List<T> entities = load(entityClass, Collections.singletonList(instanceID));
		if (entities.size() != 1) {
			throw new DataAccessException("Failed to load entity by instance ID.");
		}
		return entities.get(0);
	}

	/**
	 * Loads the entities identified by given entity class and its instance IDs.
	 *
	 * @param <T>  The desired type.
	 * @param entityClass Type of the returned entities.
	 * @param instanceIDs The instance IDs to load. If a instance ID does not exist, it is ignored.
	 * @return Entities are returned in a list.
	 * @throws DataAccessException Thrown if unable to retrieve the entities.
	 */
	<T extends Entity> List<T> load(Class<T> entityClass, Collection<String> instanceIDs) throws DataAccessException;
	
	/**
	 * Loads all available entities of given type. This method is useful while
	 * working with types whose amount is known to be fairly small (e.g.:
	 * {@code Unit}, {@code Quantity}, etc.). If a type is given where thousand
	 * instances exist (e.g.: {@code Test}) the behavior of this method is
	 * implementation specific. The following invocation might take its time to
	 * complete or even result in an exception as soon as too many results are
	 * found:
	 *
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	List<Test> tests = entityManager.loadAll(Test.class);
	 * }
	 * </pre>
	 *
	 * @param <T>
	 *            The desired type.
	 * @param entityClass
	 *            Type of the returned entities.
	 * @return Entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the entities.
	 * @see #loadAll(Class, String)
	 */
	default <T extends Entity> List<T> loadAll(Class<T> entityClass) throws DataAccessException {
		return loadAll(entityClass, "*");
	}

	/**
	 * Loads all available entities of given type whose name fulfills the given
	 * pattern. This method is useful while working with types whose amount is
	 * known to be fairly small (e.g.: {@code Unit}, {@code Quantity}, etc.). If
	 * a type is given where thousand instances exist (e.g.: {@code Test}) the
	 * behavior of this method is implementation specific. The following
	 * invocation might take its time to complete or even result in an exception
	 * as soon as too many results are found:
	 *
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	// retrieve all tests whose name starts with 'Example'
	 * 	List<Test> tests = entityManager.loadAll(Test.class, "Example*");
	 * }
	 * </pre>
	 *
	 * @param <T>
	 *            The desired type.
	 * @param entityClass
	 *            Type of the returned entities.
	 * @param pattern
	 *            Is always case sensitive and may contain wildcard characters
	 *            as follows: "?" for one matching character and "*" for a
	 *            sequence of matching characters.
	 * @return Matched entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the entities.
	 * @see #loadAll(Class)
	 */
	<T extends Entity> List<T> loadAll(Class<T> entityClass, String pattern) throws DataAccessException;

	/**
	 * Loads the parent entity for given child. Each modeled entity provides
	 * public fields for available parent entity types e.g.:
	 *
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	Optional<Test> parentTest = entityManager.loadParent(testStep, TestStep.PARENT_TYPE_TEST);
	 * }
	 * </pre>
	 *
	 * @param <T>
	 *            The desired parent type.
	 * @param child
	 *            The child entity.
	 * @param entityClass
	 *            The desired parent entity type.
	 * @return {@code Optional} is empty if parent entity could not be found.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve parent entity.
	 */
	<T extends Entity> Optional<T> loadParent(Entity child, Class<T> entityClass) throws DataAccessException;

	/**
	 * Loads all related children of given type for given parent entity. Each
	 * modeled entity provides public fields for available child entity types
	 * e.g.:
	 *
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	List<Channel> channels = entityManager.loadChildren(measurement, Measurement.CHILD_TYPE_CHANNEL);
	 * }
	 * </pre>
	 *
	 * @param <T>
	 *            The desired child type.
	 * @param parent
	 *            The parent entity.
	 * @param entityClass
	 *            The desired child entity type.
	 * @return Related child entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the children.
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
	 * <pre>
	 * {
	 * 	&#64;code
	 * 	List<TestStep> testSteps = entityManager.loadChildren(test, Test.CHILD_TYPE_TESTSTEP, "Example*");
	 * }
	 * </pre>
	 *
	 * @param <T>
	 *            The desired child type.
	 * @param parent
	 *            The parent entity.
	 * @param entityClass
	 *            The desired child entity type.
	 * @param pattern
	 *            Is always case sensitive and may contain wildcard characters
	 *            as follows: "?" for one matching character and "*" for a
	 *            sequence of matching characters.
	 * @return Matched child entities are returned in a {@code List}.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the children.
	 * @see #loadChildren(Entity, Class)
	 */
	<T extends Entity> List<T> loadChildren(Entity parent, Class<T> entityClass, String pattern)
			throws DataAccessException;

	/**
	 * Queries available {@link ContextType} for given
	 * {@link ContextDescribable}.
	 *
	 * @param contextDescribable
	 *            Either a {@link TestStep} or a {@link Measurement}
	 * @return {@code List} contains the {@code ContextType} of each referenced
	 *         {@link ContextRoot}.
	 * @throws DataAccessException
	 *             Thrown if unable to query the available {@code ContextType}s.
	 */
	List<ContextType> loadContextTypes(ContextDescribable contextDescribable) throws DataAccessException;

	/**
	 * Loads the requested {@link ContextRoot}s for given
	 * {@link ContextDescribable}.
	 *
	 * @param contextDescribable
	 *            Either a {@link TestStep} or {@link Measurement}.
	 * @param contextTypes
	 *            The requested context types. If omitted, all types are be
	 *            loaded.
	 * @return The ordered contexts for given {@code TestStep} or the measured
	 *         ones for {@code Measurement} are returned in a {@code Map}.
	 * @throws DataAccessException
	 *             Thrown if unable to retrieve the {@code
	 * 		ContextRoot}s.
	 * @see ContextType
	 */
	Map<ContextType, ContextRoot> loadContexts(ContextDescribable contextDescribable, ContextType... contextTypes)
			throws DataAccessException;

	/**
	 * Retrieves the {@link MeasuredValues} as specified by the given
	 * {@link ReadRequest}.
	 *
	 * @param readRequest
	 *            Provides all required informations to process the request.
	 * @return Returns the {@code MeasuredValues} in a {@code List} as specified
	 *         in the given {@code ReadRequest}.
	 * @throws DataAccessException
	 *             Thrown if unable to access the measured values.
	 */
	List<MeasuredValues> readMeasuredValues(ReadRequest readRequest) throws DataAccessException;

	/**
	 * Creates a new {@link Transaction} for modifying access.
	 *
	 * @return A new {@code Transaction} is returned.
	 * @throws DataAccessException
	 *             Thrown if unable to create a new {@code Transaction}.
	 */
	Transaction startTransaction() throws DataAccessException;

	/**
	 * Returns links to the given entities in the data store. The format of the links 
	 * is adapter specific and depends on the underlying data store. The links can be used by the client
	 * to directly access the entities by bypassing the MDM API, if the client is capable of 
	 * accessing the adapters data store. The client can invoke {@link BaseApplicationContext#getAdapterType()}
	 * to get the type of the adapter.
	 * 
	 * @param entities the Entities for which the links are retrieved
	 * @return the links to the given entities in the data store abstracted by the current adapter. The map 
	 * contains the requested entities as keys and the corresponding links as values.
	 */
	Map<Entity, String> getLinks(Collection<Entity> entities);
}
