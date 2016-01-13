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
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A filter is a sequence of {@link FilterItem}s containing {@link Operator}s
 * or {@link Condition}s.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Condition
 * @see FilterItem
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
	 * @param operatorItem Will be added between {@link Condition}s or merged filters.
	 */
	private Filter(FilterItem operatorItem) {
		this.operatorItem = operatorItem;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new filter as returned by {@link #and()} and adds an ID {@link
	 * Condition} to it.
	 *
	 * @param entity The entity whose ID condition will be applied.
	 * @param id The instance ID.
	 * @return A newly created filter is returned with the corresponding ID
	 * 		{@code Condition}.
	 */
	public static Filter id(Entity entity, Long id) {
		return and().add(Operation.EQ.create(entity.getIDAttribute(), id));
	}

	/**
	 * Creates a new filter that implicitly adds {@link Operator#AND} {@code
	 * FilterItem}s between {@link Condition}s or merged filters.
	 *
	 * <pre> {@code
	 * Filter filter = Filter.and();
	 * filter.add(conditionA);    // conditionA
	 * filter.add(conditionB);    // conditionA AND conditionB
	 * filter.invert();           // !(conditionA AND conditionB)
	 * filter.merge(otherFilter); // !(conditionA AND conditionB) AND (otherFilter)
	 * }</pre>
	 *
	 * @return A newly created filter is returned.
	 */
	public static Filter and() {
		return new Filter(FilterItem.AND);
	}

	/**
	 * Creates a new filter that implicitly adds {@link Operator#OR} {@code
	 * FilterItem}s between {@link Condition}s or merged filters.
	 *
	 * <pre> {@code
	 * Filter filter = Filter.or();
	 * filter.add(conditionA);    // conditionA
	 * filter.add(conditionB);    // conditionA OR conditionB
	 * filter.invert();           // !(conditionA OR conditionB)
	 * filter.merge(otherFilter); // !(conditionA OR conditionB) OR otherFilter
	 * }</pre>
	 *
	 * @return A newly created filter is returned.
	 */
	public static Filter or() {
		return new Filter(FilterItem.OR);
	}

	/**
	 * Adds all {@link Condition}s to this filter.
	 *
	 * @param conditions The {@link Condition}s that will be added.
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
	 * @param conditions The {@link Condition}s that will be added.
	 * @return Returns this filter.
	 * @see Filter#add(Condition)
	 */
	public Filter addAll(Condition... conditions) {
		Arrays.stream(conditions).forEach(this::add);
		return this;
	}

	/**
	 * Adds passed {@link Condition} to this filter's {@link FilterItem} sequence.
	 *
	 * @param condition {@code Condition} that will be added.
	 * @return Returns this filter.
	 */
	public Filter add(Condition condition) {
		insertOperator();
		filterItems.addLast(new FilterItem(condition));
		return this;
	}

	/**
	 * Merges all passed filters with this filter.
	 *
	 * @param filters Filters that will be merged.
	 * @return Returns this filter.
	 * @see #merge(Filter)
	 */
	public Filter merge(List<Filter> filters) {
		filters.stream().forEach(this::merge);
		return this;
	}

	/**
	 * Merges all passed filters with this filter.
	 *
	 * @param filters Filters that will be merged.
	 * @return Returns this filter.
	 * @see #merge(Filter)
	 */
	public Filter merge(Filter... filters) {
		Arrays.stream(filters).forEach(this::merge);
		return this;
	}

	/**
	 * The {@link FilterItem}s of the passed filter are added to this filter.
	 * If required, then parenthesis are implicitly added as shown in the
	 * example below:
	 *
	 * <pre> {@code
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
	 * }</pre>
	 *
	 * @param filter Filter that will be merged.
	 * @return Returns this filter.
	 */
	public Filter merge(Filter filter) {
		boolean addBrackets = !filter.isInverted() || filter.hasMultiple() && !(operatorItem == filter.operatorItem);
		insertOperator();

		if(addBrackets) {
			filterItems.addLast(FilterItem.OPEN);
		}

		filter.filterItems.stream().forEach(filterItems::addLast);

		if(addBrackets) {
			filterItems.addLast(FilterItem.CLOSE);
		}

		return this;
	}

	/**
	 * Inverts this filter by prepending the {@link Operator#NOT} to the
	 * {@link FilterItem} sequence as shown in the following examples:
	 *
	 * <pre> {@code
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
	 * orFilter.invert();     // IllegalStateException -> filter is already inverted!
	 * }</pre>
	 *
	 * @return Returns this filter.
	 * @throws IllegalStateException Thrown either if the current set of
	 * 		conditions is empty or this filter is already inverted.
	 */
	public Filter invert() {
		if(filterItems.isEmpty()) {
			throw new IllegalStateException("Current set of conditions is empty.");
		}

		if(isInverted()) {
			throw new IllegalStateException("Current set of conditions is already inverted.");
		} else if(hasMultiple()) {
			filterItems.addFirst(FilterItem.OPEN);
			filterItems.addLast(FilterItem.CLOSE);
		}

		filterItems.addFirst(FilterItem.NOT);
		return this;
	}

	/**
	 * Returns a sequential stream with the internally stored {@link
	 * FilterItem}s as its source.
	 *
	 * @return A stream over the contained {@code FilterItem}s is returned.
	 */
	public Stream<FilterItem> stream() {
		return filterItems.stream();
	}

	/**
	 * Returns an iterator over the {@link FilterItem}s contained in this
	 * filter in proper sequence.
	 *
	 * @return The returned iterator does not support the {@link
	 * 		Iterator#remove()} operation.
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
		if(filterItems.size() > 0) {
			filterItems.addLast(operatorItem);
		}
	}

	/**
	 * Determines whether the {@link FilterItem} sequence starts with the
	 * {@link Operator#NOT}.
	 *
	 * @return True if this filter is inverted.
	 */
	private boolean isInverted() {
		return filterItems.size() > 0 && Operator.NOT.equals(filterItems.getFirst());
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
