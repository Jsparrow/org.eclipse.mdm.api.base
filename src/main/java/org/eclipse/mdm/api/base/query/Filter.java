/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.query;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A filter is a sequence of {@link FilterItem}s containing {@link BooleanOperator}s or
 * {@link Condition}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Query
 * @see SearchQuery
 * @see SearchService
 */
public final class Filter implements Iterable<FilterItem> {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final Deque<FilterItem> filterItems = new ArrayDeque<>();
	private final FilterItem operatorItem;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param operatorItem
	 *            Will be added between {@link Condition}s or merged filters.
	 */
	private Filter(FilterItem operatorItem) {
		this.operatorItem = operatorItem;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new filter with an instance ID condition for given
	 * {@link EntityType} and instance ID.
	 *
	 * @param entityType
	 *            The {@code EntityType}.
	 * @param id
	 *            The instance ID.
	 * @return The created filter is returned.
	 * @see #id(EntityType, String)
	 */
	public static Filter idOnly(EntityType entityType, String id) {
		return and().id(entityType, id);
	}

	/**
	 * Creates a new filter with an foreign ID condition for given
	 * {@link Relation} and instance ID.
	 *
	 * @param relation
	 *            The {@code Relation}.
	 * @param id
	 *            The instance ID.
	 * @return The created filter is returned.
	 * @see #id(Relation, String)
	 */
	public static Filter idOnly(Relation relation, String id) {
		return and().id(relation, id);
	}

	/**
	 * Creates a new filter with an instance IDs condition for given
	 * {@link EntityType} and instance IDs.
	 *
	 * @param entityType
	 *            The {@code EntityType}.
	 * @param ids
	 *            The instance IDs.
	 * @return The created filter is returned.
	 * @see #ids(EntityType, Collection)
	 */
	public static Filter idsOnly(EntityType entityType, Collection<String> ids) {
		return and().ids(entityType, ids);
	}

	/**
	 * Creates a new filter with an foreign IDs condition for given
	 * {@link Relation} and instance IDs.
	 *
	 * @param relation
	 *            The {@code Relation}.
	 * @param ids
	 *            The instance IDs.
	 * @return The created filter is returned.
	 * @see #ids(Relation, Collection)
	 */
	public static Filter idsOnly(Relation relation, Collection<String> ids) {
		return and().ids(relation, ids);
	}

	/**
	 * Creates a new filter with an instance name condition for given
	 * {@link EntityType} and instance name pattern.
	 *
	 * @param entityType
	 *            The {@code EntityType}.
	 * @param pattern
	 *            Is always case sensitive and may contain wildcard characters
	 *            as follows: "?" for one matching character and "*" for a
	 *            sequence of matching characters.
	 * @return The created filter is returned.
	 * @see #name(EntityType, String)
	 */
	public static Filter nameOnly(EntityType entityType, String pattern) {
		return and().name(entityType, pattern);
	}

	/**
	 * Creates a new instance of this class that implicitly adds
	 * {@link BooleanOperator#AND} {@code FilterItem}s between {@link Condition}s or
	 * merged filters.
	 *
	 * <pre>
	 * Filter filter = Filter.and();
	 * filter.add(conditionA); // conditionA
	 * filter.add(conditionB); // conditionA AND conditionB
	 * filter.invert(); // !(conditionA AND conditionB)
	 * filter.merge(otherFilter); // !(conditionA AND conditionB) AND
	 * 							// (otherFilter)
	 * </pre>
	 *
	 * @return A newly created filter is returned.
	 */
	public static Filter and() {
		return new Filter(FilterItem.AND);
	}

	/**
	 * Creates a new instance of this class that implicitly adds
	 * {@link BooleanOperator#OR} {@code FilterItem}s between {@link Condition}s or
	 * merged filters.
	 *
	 * <pre>
	 * Filter filter = Filter.or();
	 * filter.add(conditionA); // conditionA
	 * filter.add(conditionB); // conditionA OR conditionB
	 * filter.invert(); // !(conditionA OR conditionB)
	 * filter.merge(otherFilter); // !(conditionA OR conditionB) OR otherFilter
	 * </pre>
	 *
	 * @return A newly created filter is returned.
	 */
	public static Filter or() {
		return new Filter(FilterItem.OR);
	}

	/**
	 * Adds all {@link Condition}s to this filter.
	 *
	 * @param conditions
	 *            The {@link Condition}s that will be added.
	 * @return Returns this filter.
	 * @see Filter#add(Condition)
	 */
	public Filter addAll(List<Condition> conditions) {
		conditions.forEach(this::add);
		return this;
	}

	/**
	 * Adds all {@link Condition}s to this filter.
	 *
	 * @param conditions
	 *            The {@link Condition}s that will be added.
	 * @return Returns this filter.
	 * @see Filter#add(Condition)
	 */
	public Filter addAll(Condition... conditions) {
		Arrays.stream(conditions).forEach(this::add);
		return this;
	}

	/**
	 * Adds a new instance ID condition ({@link ComparisonOperator#EQUAL}) for given
	 * {@link EntityType} and instance ID to this filter.
	 *
	 * @param entityType
	 *            The {@code EntityType}.
	 * @param id
	 *            The instance ID.
	 * @return Returns this filter.
	 * @see #add(Condition)
	 */
	public Filter id(EntityType entityType, String id) {
		add(ComparisonOperator.EQUAL.create(entityType.getIDAttribute(), id));
		return this;
	}

	/**
	 * Adds a new foreign ID condition ({@link ComparisonOperator#EQUAL}) for given
	 * {@link Relation} and instance ID to this filter.
	 *
	 * @param relation
	 *            The {@code Relation}.
	 * @param id
	 *            The instance ID.
	 * @return Returns this filter.
	 * @see #add(Condition)
	 */
	public Filter id(Relation relation, String id) {
		add(ComparisonOperator.EQUAL.create(relation.getAttribute(), id));
		return this;
	}

	/**
	 * Adds a new instance IDs condition ({@link ComparisonOperator#IN_SET}) for given
	 * {@link EntityType} and instance IDs to this filter.
	 *
	 * @param entityType
	 *            The {@code EntityType}.
	 * @param ids
	 *            The instance IDs.
	 * @return Returns this filter
	 * @see #add(Condition)
	 */
	public Filter ids(EntityType entityType, Collection<String> ids) {
		add(ComparisonOperator.IN_SET.create(entityType.getIDAttribute(),
				ids.stream().distinct().toArray(String[]::new)));
		return this;
	}

	/**
	 * Adds a new foreign IDs condition ({@link ComparisonOperator#IN_SET}) for given
	 * {@link Relation} and instance IDs to this filter.
	 *
	 * @param relation
	 *            The {@code Relation}.
	 * @param ids
	 *            The instance IDs.
	 * @return Returns this filter.
	 * @see #add(Condition)
	 */
	public Filter ids(Relation relation, Collection<String> ids) {
		add(ComparisonOperator.IN_SET.create(relation.getAttribute(),
				ids.stream().distinct().toArray(String[]::new)));
		return this;
	}

	/**
	 * Adds a instance name condition ({@link ComparisonOperator#LIKE}) for given
	 * {@link EntityType} and instance name pattern.
	 *
	 * <p>
	 * <b>NOTE:</b> If the given pattern equals "*", then no {@link Condition}
	 * will be added since "*" means take all.
	 *
	 * @param entityType
	 *            The {@code EntityType}.
	 * @param pattern
	 *            Is always case sensitive and may contain wildcard characters
	 *            as follows: "?" for one matching character and "*" for a
	 *            sequence of matching characters.
	 * @return Returns this filter.
	 * @see #add(Condition)
	 */
	public Filter name(EntityType entityType, String pattern) {
		if (!"*".equals(pattern)) {
			add(ComparisonOperator.LIKE.create(entityType.getNameAttribute(), pattern));
		}
		return this;
	}

	/**
	 * Adds given {@link Condition} to this filter's {@link FilterItem}
	 * sequence.
	 *
	 * @param condition
	 *            {@code Condition} that will be added.
	 * @return Returns this filter.
	 */
	public Filter add(Condition condition) {
		insertOperator();
		filterItems.addLast(new FilterItem(condition));
		return this;
	}

	/**
	 * Merges all given filters with this filter.
	 *
	 * @param filters
	 *            Filters that will be merged.
	 * @return Returns this filter.
	 * @see #merge(Filter)
	 */
	public Filter merge(List<Filter> filters) {
		filters.stream().forEach(this::merge);
		return this;
	}

	/**
	 * Merges all given filters with this filter.
	 *
	 * @param filters
	 *            Filters that will be merged.
	 * @return Returns this filter.
	 * @see #merge(Filter)
	 */
	public Filter merge(Filter... filters) {
		Arrays.stream(filters).forEach(this::merge);
		return this;
	}

	/**
	 * The {@link FilterItem}s of the given filter are added to this filter. If
	 * required, then parenthesis are implicitly added as shown in the example
	 * below:
	 *
	 * <pre>
	 * Filter filter1 = Filter.and();
	 * Condition a = ...;
	 * Condition b = ...;
	 * filter1.addAll(a, b); // a AND b
	 *
	 * Filter filter2 = Filter.or();
	 * Condition c = ...;
	 * Condition d = ...;
	 * Condition e = ...;
	 * filter2.addAll(c, d, e); // c OR d OR e
	 *
	 * Filter filter = Filter.or();
	 * filter.merge(filter1, filter2); // (filter1) OR filter2
	 * </pre>
	 *
	 * @param filter
	 *            Filter that will be merged.
	 * @return Returns this filter.
	 */
	public Filter merge(Filter filter) {
		boolean addBrackets = !filter.isInverted() || filter.hasMultiple() && !(operatorItem == filter.operatorItem);
		insertOperator();

		if (addBrackets) {
			filterItems.addLast(FilterItem.OPEN);
		}

		filter.filterItems.stream().forEach(filterItems::addLast);

		if (addBrackets) {
			filterItems.addLast(FilterItem.CLOSE);
		}

		return this;
	}

	/**
	 * Inverts this filter by prepending the {@link BooleanOperator#NOT} to the
	 * {@link FilterItem} sequence as shown in the following examples:
	 *
	 * <pre>
	 * // inverting an AND filter
	 * Filter andFilter = Filter.and();
	 * Condition a = ...;      // any condition
	 * Condition b = ...;      // another condition
	 * andFilter.addAll(a, b); // a AND b
	 * andFilter.invert();     // !(a AND b)
	 *
	 * // inverting an OR filter
	 * Filter orFilter = Filter.or();
	 * Condition a = ...;     // any condition
	 * Condition b = ...;     // another condition
	 * orFilter.addAll(a, b); // a OR b
	 * orFilter.invert();     // !(a OR b)
	 * orFilter.invert();     // IllegalStateException -&gt; filter is already inverted!
	 * </pre>
	 *
	 * @return Returns this filter.
	 * @throws IllegalStateException
	 *             Thrown either if the current set of conditions is empty or
	 *             this filter is already inverted.
	 */
	public Filter invert() {
		if (filterItems.isEmpty()) {
			throw new IllegalStateException("Current set of conditions is empty.");
		}

		if (isInverted()) {
			throw new IllegalStateException("Current set of conditions is already inverted.");
		} else if (hasMultiple()) {
			filterItems.addFirst(FilterItem.OPEN);
			filterItems.addLast(FilterItem.CLOSE);
		}

		filterItems.addFirst(FilterItem.NOT);
		return this;
	}

	/**
	 * Checks whether this filter has no conditions at all.
	 *
	 * @return Returns {@code false} if at least one {@link Condition} is
	 *         contained.
	 */
	public boolean isEmtpty() {
		return filterItems.isEmpty();
	}

	/**
	 * Returns a sequential stream with the internally stored
	 * {@link FilterItem}s as its source.
	 *
	 * @return A stream over the contained {@code FilterItem}s is returned.
	 */
	public Stream<FilterItem> stream() {
		return filterItems.stream();
	}

	/**
	 * Returns an iterator over the {@link FilterItem}s contained in this filter
	 * in proper sequence.
	 *
	 * @return The returned iterator does not support the
	 *         {@link Iterator#remove()} operation.
	 */
	@Override
	public Iterator<FilterItem> iterator() {
		Iterator<FilterItem> internal = filterItems.iterator();
		return new Iterator<FilterItem>() {

			@Override
			public boolean hasNext() {
				return internal.hasNext();
			}

			@Override
			public FilterItem next() {
				return internal.next();
			}
		};
	}

	// ======================================================================
	// Private methods
	// ======================================================================

	/**
	 * If required the {@link #operatorItem} is added to the end (tail) of the
	 * {@link FilterItem} sequence.
	 */
	private void insertOperator() {
		if (!filterItems.isEmpty()) {
			filterItems.addLast(operatorItem);
		}
	}

	/**
	 * Determines whether the {@link FilterItem} sequence starts with the
	 * {@link BooleanOperator#NOT}.
	 *
	 * @return True if this filter is inverted.
	 */
	private boolean isInverted() {
		return !filterItems.isEmpty() && FilterItem.NOT == filterItems.getFirst();
	}

	/**
	 * Determines whether multiple {@link FilterItem}s are contained.
	 *
	 * @return True if multiple {@code FilterItem}s are contained.
	 */
	private boolean hasMultiple() {
		return filterItems.size() > 1;
	}

}
