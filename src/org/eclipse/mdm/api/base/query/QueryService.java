/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.List;

import org.eclipse.mdm.api.base.model.DataItem;

public interface QueryService {
	
	Query createQuery();
	
	SearchQuery getSearchQuery(String name);
	
	Entity getEntity(Class<? extends DataItem> type);
	
	@Deprecated
	Entity getEntity(String name);
	
	Attribute getAttribute(Class<? extends DataItem> type, String name);
	
	Relation getRelation(Entity source, Entity target);
	
	List<Relation> getRelations(Entity source, Relationship relationType);
	
	List<Relation> getRelations(Entity source);
	
	void clear();
	
}
