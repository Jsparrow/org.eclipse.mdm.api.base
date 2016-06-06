package org.eclipse.mdm.api.base.notification;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.mdm.api.base.query.EntityType;

/**
 * Class represents a filter for notifications.
 * 
 * @since 1.0.0
 * @author Matthias Koller, Peak Solution GmbH
 */
public class NotificationFilter {

	/**
	 * Modification type enumeration
	 * 
	 * @since 1.0.0
	 * @author Matthias Koller, Peak Solution GmbH
	 */
	public static enum ModificationType
	{
		INSTANCE_CREATED,
		INSTANCE_MODIFIED,
		INSTANCE_DELETED,
		MODEL_MODIFIED,
		SECURITY_MODIFIED;
	}
	
	private EnumSet<ModificationType> types = EnumSet.allOf(ModificationType.class);
	
	private Set<EntityType> entityTypes = Collections.emptySet();
	
	/**
	 * Returns the {@link ModificationType}s of this filter.
	 * @return Set with {@link ModificationType} is returned. 
	 */
	public Set<ModificationType> getTypes() {
		return types;
	}
	
	/**
	 * Sets the {@link ModificationType}s used to filter the notifications.
	 * @param types Set with {@link ModificationType}
	 */
	public void setTypes(Set<ModificationType> types) {
		this.types = EnumSet.copyOf(types);
	}
	
	/**
	 * Returns the {@link EntityType}s of this filter.
	 * @return Set with {@link EntityType} is returned. 
	 */
	public Set<EntityType> getEntityTypes() {
		return entityTypes;
	}
	
	/**
	 * Sets the {@link EntityType}s used to filter the notifications.
	 * @param entityTypes Set with {@link EntityType}.
	 */
	public void setEntityTypes(Set<EntityType> entityTypes) {
		this.entityTypes = new HashSet<>(entityTypes);
	}
}
