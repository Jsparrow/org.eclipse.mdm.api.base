package org.eclipse.mdm.api.base.query;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.mdm.api.base.EntityTypeImpl;
import org.eclipse.mdm.api.base.RelationImpl;
import org.eclipse.mdm.api.base.RelationImpl.AttributeType;
import org.junit.Test;

/**
 * Test the Filter class
 * 
 * @author Alexander Nehmer
 *
 */
public class FilterTest {

	static String[] values = new String[] { "1", "2", "3" };

	/**
	 * Tests ids(EntityType entityType, Collection<String> ids)
	 */
	@Test
	public void testIdsEntityType() {
		Filter filter = Filter.and();
		assertEquals(filter.stream().toArray().length, 0);

		filter = Filter.and().ids(new EntityTypeImpl(), Arrays.asList(values));

		String[] filterCondition = filter.iterator().next().getCondition().getValue().extract();

		Iterator<String> valuesIterator = Arrays.asList(values).iterator();
		Iterator<String> filterConditionIterator = Arrays.stream(filterCondition).iterator();

		while (valuesIterator.hasNext() && filterConditionIterator.hasNext()) {
			assertEquals(valuesIterator.next(), filterConditionIterator.next());
		}
	}

	/**
	 * Tests ids(Relation relation, Collection<String> ids)
	 */
	@Test
	public void testIdsRelation() {
		Filter filter = Filter.and();
		assertEquals(filter.stream().toArray().length, 0);

		filter = Filter.and().ids(new RelationImpl(AttributeType.ID), Arrays.asList(values));

		String[] filterCondition = filter.iterator().next().getCondition().getValue().extract();

		Iterator<String> valuesIterator = Arrays.asList(values).iterator();
		Iterator<String> filterConditionIterator = Arrays.stream(filterCondition).iterator();

		while (valuesIterator.hasNext() && filterConditionIterator.hasNext()) {
			assertEquals(valuesIterator.next(), filterConditionIterator.next());
		}
	}

	/**
	 * Tests id(EntityType entityType, String id)
	 */
	@Test
	public void testIdEntity() {
		Filter filter = Filter.and();
		assertEquals(filter.stream().toArray().length, 0);

		filter = Filter.and().id(new EntityTypeImpl(), "1");

		String filterCondition = filter.iterator().next().getCondition().getValue().extract();

		assertEquals(filterCondition, "1");
	}

	/**
	 * Tests id(Relation relation, String id)
	 */
	@Test
	public void testIdRelation() {
		Filter filter = Filter.and();
		assertEquals(filter.stream().toArray().length, 0);

		filter = Filter.and().id(new RelationImpl(AttributeType.ID), "1");

		String filterCondition = filter.iterator().next().getCondition().getValue().extract();

		assertEquals(filterCondition, "1");
	}
}
