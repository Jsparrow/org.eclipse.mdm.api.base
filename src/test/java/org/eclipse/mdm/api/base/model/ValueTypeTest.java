package org.eclipse.mdm.api.base.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;

public class ValueTypeTest {

	@Before
	public void init() {
		 EnumRegistry.getInstance();
	}

	@org.junit.Test
	public void testCreateUnknown() {
		Value v = ValueType.UNKNOWN.create("Unknown");
		
		assertThat(v.extract(ValueType.UNKNOWN)).isNull();
	}
	
	@org.junit.Test
	public void testExtractString() {
		Value v = ValueType.STRING.create("Name", "abcd");

		assertThat((String) v.extract()).isEqualTo("abcd");
		assertThat(v.extract(ValueType.STRING)).isEqualTo("abcd");
	}
	
	@org.junit.Test
	public void testExtractInteger() {
		Value v = ValueType.INTEGER.create("Id", 1);

		assertThat((int) v.extract()).isEqualTo(1);
		assertThat(v.extract(ValueType.INTEGER)).isEqualTo(1);
	}
	
	@org.junit.Test
	public void testExtractIntegerSequence() {
		int[] ints = new int[] { 1, 2, 3 };
		
		Value v = ValueType.INTEGER_SEQUENCE.create("Ids", ints);

		assertThat((int[]) v.extract()).isEqualTo(ints);
		assertThat(v.extract(ValueType.INTEGER_SEQUENCE)).isEqualTo(ints);
	}
	
	@org.junit.Test
	public void testExtractEnumeration() {
		Value v = ValueType.ENUMERATION.create("ScalarTypeField", "", true, ScalarType.INTEGER, EnumRegistry.SCALAR_TYPE);
		
		assertThat(v.extract(ValueType.ENUMERATION)).isEqualTo(ScalarType.INTEGER);
	}
	
	@org.junit.Test
	public void testExtractEnumerationSequence() {
		ScalarType[] scalaTypes = new ScalarType[] { ScalarType.FLOAT, ScalarType.INTEGER };
		
		Value v = ValueType.ENUMERATION_SEQUENCE.create("ScalarTypeSeqField", "", true, scalaTypes, EnumRegistry.SCALAR_TYPE);
		
		assertThat(v.extract(ValueType.ENUMERATION_SEQUENCE)).isEqualTo(scalaTypes);
	}
}
