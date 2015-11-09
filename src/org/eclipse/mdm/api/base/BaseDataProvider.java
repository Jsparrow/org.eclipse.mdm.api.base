/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.marker.ContextDescribable;
import org.eclipse.mdm.api.base.model.ContextRoot;
import org.eclipse.mdm.api.base.model.ContextType;
import org.eclipse.mdm.api.base.model.DataItem;
import org.eclipse.mdm.api.base.model.Environment;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.User;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.eclipse.mdm.api.base.query.QueryService;

public interface BaseDataProvider {
	
	QueryService getQueryService();
	
	Environment getEnvironment() throws DataAccessException;

	User getLoggedOnUser() throws DataAccessException;
	
	<T extends DataItem> T findByURI(URI uri) throws DataAccessException;
	
	@Deprecated
	<T extends DataItem> T findByID(Class<T> type, Long id) throws DataAccessException;
	
	<T extends DataItem> List<T> list(Class<T> type) throws DataAccessException;
	
	<T extends DataItem> List<T> listChildren(DataItem parent, Class<T> childType) throws DataAccessException;
	
	Map<ContextType, ContextRoot> getContextData(ContextDescribable contextDescribable, ContextType... contextTypes) throws DataAccessException;
	
	//public <T extends DataItem> List<T> create(Class<T> type, List<String> names) throws MDMDataProviderException;
	
	//public <T extends DataItem, S extends DataItem> List<S> insert(T parent, List<S> dataItems) throws MDMDataProviderException;
	
	//public void update(List<? super DataItem> dataItems);	
	
	//public void startTransaction() throws MDMDataProviderException;
	
	//public void commitTransaction() throws MDMDataProviderException;
	
	//public void abortTransaction() throws MDMDataProviderException;
	
}
