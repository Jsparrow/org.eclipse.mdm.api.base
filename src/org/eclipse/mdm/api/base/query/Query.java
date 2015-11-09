/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;


public interface Query {
	
	Query selectAll(List<Entity> entities);
	
	Query selectAll(Entity entity);
	
	Query select(Entity entity, String... names);
	
	Query selectID(Entity entity);
	
	Query select(List<Attribute> attributes);
	
	Query select(Attribute attribute);
	
	Query select(Attribute attribute, Aggregation aggregation);	
	

	Query join(List<Relation> relations);

	Query join(Relation relation);
	
	Query join(Relation relation, Join joinType);
	

	Query group(List<Attribute> attributes);

	Query group(Attribute attribute);
	
	
	Query order(Attribute attribute);
	
	Query order(Attribute attribute, boolean ascending);

	
	Result fetchSingleton() throws DataAccessException;
	
	Result fetchSingleton(Filter filter) throws DataAccessException;
	
	List<Result> fetch() throws DataAccessException;

	List<Result> fetch(Filter filter) throws DataAccessException;
	
	
	Query clone();
	
}

