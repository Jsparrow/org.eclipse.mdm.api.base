package org.eclipse.mdm.api.base.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.mdm.api.base.model.Deletable;

/**
 * Holds related entities of any kind and keeps track of modifications.
 */
public final class ChildrenStore {

	private final Map<Class<? extends Deletable>, List<? extends Deletable>> current = new HashMap<>(0);
	private final Map<Class<? extends Deletable>, List<? extends Deletable>> removed = new HashMap<>(0);

	/**
	 * Returns current set of related children mapped by their type.
	 *
	 * @return Returned {@code Map} is unmodifiable.
	 */
	public Map<Class<? extends Deletable>, List<? extends Deletable>> getCurrent() {
		return Collections.unmodifiableMap(current);
	}

	/**
	 * Returns current set of removed related children mapped by their type.
	 *
	 * @return Returned {@code Map} is unmodifiable.
	 */
	public Map<Class<? extends Deletable>, List<? extends Deletable>> getRemoved() {
		return Collections.unmodifiableMap(removed);
	}

	/**
	 * Returns related child entities of given type.
	 *
	 * @param <T>
	 *            Desired entity type.
	 * @param entityClass
	 *            Used as identifier.
	 * @return Returned {@code List} is unmodifiable.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Deletable> List<T> get(Class<T> entityClass) {
		return Collections.unmodifiableList((List<T>) current.computeIfAbsent(entityClass, k -> new ArrayList<>()));
	}

	/**
	 * Sorts the child entities with given {@code Comparator}.
	 *
	 * @param <T>
	 *            Desired entity type.
	 * @param entityClass
	 *            Used as identifier.
	 * @param comparator
	 *            Used for sorting.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Deletable> void sort(Class<T> entityClass, Comparator<? super T> comparator) {
		List<T> children = (List<T>) current.get(entityClass);
		if (children != null) {
			children.sort(comparator);
		}
	}

	/**
	 * Adds given child entity.
	 *
	 * @param child
	 *            The new child.
	 */
	@SuppressWarnings("unchecked")
	public void add(Deletable child) {
		removed.getOrDefault(child.getClass(), new ArrayList<>()).remove(child);
		((List<Deletable>) current.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
	}

	/**
	 * Removes given child entity.
	 *
	 * @param child
	 *            The child which will be removed.
	 */
	@SuppressWarnings("unchecked")
	public void remove(Deletable child) {
		List<Deletable> children = (List<Deletable>) current.getOrDefault(child.getClass(), new ArrayList<>());
		if (children.remove(child) && child.getID() != null && child.getID().length() > 0) {
			((List<Deletable>) removed.computeIfAbsent(child.getClass(), k -> new ArrayList<>())).add(child);
		}
	}

	/**
	 * Clean up list of removed entities.
	 */
	void apply() {
		removed.clear();
	}

}