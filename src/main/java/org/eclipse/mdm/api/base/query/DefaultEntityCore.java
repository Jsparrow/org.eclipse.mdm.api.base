package org.eclipse.mdm.api.base.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Deletable;
import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.EntityCore;
import org.eclipse.mdm.api.base.model.URI;
import org.eclipse.mdm.api.base.model.Value;

public final class DefaultEntityCore implements EntityCore {

	private final Map<Class<? extends Deletable>, List<? extends Deletable>> currentChildren = new HashMap<>();
	private final Map<Class<? extends Deletable>, List<? extends Deletable>> removedChildren = new HashMap<>();

	private final Map<Class<? extends Entity>, Entity> infoRelations = new HashMap<>();

	private final Map<String, Entity> implicitRelations = new HashMap<>();

	private final Map<String, Value> values = new HashMap<>();

	private URI uri;

	public DefaultEntityCore(Record record) {
		setURI(record.createURI());
		values.putAll(record.getValues());
		values.remove(Entity.ATTR_ID);

		// remove any contained relation attributes
		record.getEntityType().getRelations().stream().map(Relation::getName).forEach(values::remove);
	}

	public DefaultEntityCore(EntityType entityType) {
		values.putAll(entityType.createValues());
		values.remove(Entity.ATTR_ID);

		setURI(new URI(entityType.getSourceName(), entityType.getName(), 0L));
	}

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public void setURI(URI uri) {
		this.uri = uri;
	}

	@Override
	public Map<String, Value> getValues() {
		return values;
	}

	@Override
	public Map<Class<? extends Entity>, Entity> getInfoRelations() {
		return infoRelations;
	}

	@Override
	public Map<Class<? extends Deletable>, List<? extends Deletable>> getChildren() {
		return currentChildren;
	}

	@Override
	public Map<Class<? extends Deletable>, List<? extends Deletable>> getRemovedChildren() {
		return removedChildren;
	}

	@Override
	public Map<String, Entity> getImplicitRelations() {
		return implicitRelations;
	}

}
