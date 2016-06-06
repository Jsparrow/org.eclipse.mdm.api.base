package org.eclipse.mdm.api.base.notification;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.eclipse.mdm.api.base.query.EntityType;

public class NotificationFilter {

	public static enum ModificationType
	{
		INSTANCE_CREATED,
		INSTANCE_MODIFIED,
		INSTANCE_DELETED,
		MODEL_MODIFIED,
		SECURITY_MODIFIED;
	}
	
	private EnumSet<ModificationType> types = EnumSet.allOf(ModificationType.class);
	
	private List<EntityType> entityTypes = Collections.emptyList();
	
	public EnumSet<ModificationType> getTypes() {
		return types;
	}
	public List<EntityType> getEntityTypes() {
		return entityTypes;
	}
}
