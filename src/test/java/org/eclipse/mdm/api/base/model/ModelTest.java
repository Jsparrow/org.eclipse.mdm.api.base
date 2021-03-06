/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/


package org.eclipse.mdm.api.base.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.CoreImpl;
import org.eclipse.mdm.api.base.EntityFactoryImpl;
import org.eclipse.mdm.api.base.adapter.Core;
import org.eclipse.mdm.api.base.massdata.UnitBuilder;
import org.eclipse.mdm.api.base.massdata.WriteRequest;
import org.eclipse.mdm.api.base.massdata.WriteRequestBuilder;
import org.eclipse.mdm.api.base.model.MeasuredValues.ValueIterator;

/**
 * 
 * some unit test to test functionality of org.eclipse.mdm.api.base.model. At
 * this point, please don't expect anything near complete test coverage.
 * 
 * @author Florian Schmitt
 *
 */
public class ModelTest {

	// compare doubles up to this accuracy
	private static final double EPSILON = 0.00005d;


	/**
	 * basic test for reading the value of a parameter.The intialization via
	 * maps is needed to simulate the data store.
	 */
	@org.junit.Test
	public void parameterValue() {
		Map<String, Value> map = new HashMap<>();
		map.put("DataType", new Value(ValueType.STRING, "DataType", null, true, ScalarType.FLOAT, ScalarType.class,
				ScalarType.FLOAT, EnumRegistry.SCALAR_TYPE));
		map.put("Value", ValueType.STRING.create("Value", null, true, "5.7"));
		map.put("Name", ValueType.STRING.create("Name", null, true, "paramname"));
		Core core = new CoreImpl(map);
		Parameter tp = new Parameter(core);
		Value vv = tp.getVirtualValue();
		Float extracted = vv.extract();
		assertEquals(5.7f, extracted, EPSILON);
	}

	/**
	 * basic test for reading measured float values
	 */
	@org.junit.Test
	public void measuredValueFloat() {
		Float[] vals = { 1.0f, 2.0f, 3.7f, 2.1f };
		boolean[] flags = { true, true, false, true };
		MeasuredValues mv = new MeasuredValues(ScalarType.FLOAT, "Value1", "lightyears", vals, flags);
		ValueIterator<Float> valueIterator = mv.iterator();
		int i = 0;
		while (valueIterator.hasNext()) {
			boolean isCurrentValid = valueIterator.isValid();
			Float currentValue = valueIterator.next();
			assertEquals(vals[i], currentValue, EPSILON);
			assertEquals(flags[i], isCurrentValid);
			i++;
		}
		assertEquals(i, vals.length);
	}

	/**
	 * basic test for reading measured double values
	 */
	@org.junit.Test
	public void measuredValueDouble() {
		Double[] vals = { 1.0d, 2.0d, 3.7d, 2.1d };
		boolean[] flags = { true, true, false, true };
		MeasuredValues mv = new MeasuredValues(ScalarType.DOUBLE, "Value1", "lightyears", vals, flags);
		ValueIterator<Double> valueIterator = mv.iterator();
		int i = 0;
		while (valueIterator.hasNext()) {
			boolean isCurrentValid = valueIterator.isValid();
			Double currentValue = valueIterator.next();
			assertEquals(vals[i], currentValue, EPSILON);
			assertEquals(flags[i], isCurrentValid);
			i++;
		}
		assertEquals(i, vals.length);
	}

	/**
	 * basic test for reading measured string values
	 */
	@org.junit.Test
	public void measuredValueString() {
		String[] vals = { "str1", "str2", "str3", "str4" };
		boolean[] flags = { true, true, false, true };
		MeasuredValues mv = new MeasuredValues(ScalarType.STRING, "Value1", "lightyears", vals, flags);
		ValueIterator<String> valueIterator = mv.iterator();
		int i = 0;
		while (valueIterator.hasNext()) {
			boolean isCurrentValid = valueIterator.isValid();
			String currentValue = valueIterator.next();
			assertEquals(vals[i], currentValue);
			assertEquals(flags[i], isCurrentValid);
			i++;
		}
		assertEquals(i, vals.length);
	}

	/**
	 * basic test for reading attributes of the channel.The intialization via
	 * maps is needed to simulate the data store.
	 */
	@org.junit.Test
	public void getChannelAttrs() {
		Map<String, Value> map = new HashMap<>();
		float min_src = 5.7f;
		float max_src = 10.23f;
		map.put("Minimum", ValueType.DOUBLE.create("Minimum", "m/s", true, Double.valueOf(min_src)));
		map.put("Maximum", ValueType.DOUBLE.create("Maximum", "m/s", true, Double.valueOf(max_src)));
		Core core = new CoreImpl(map);
		Channel ch = new Channel(core);
		Double min = ch.getMinimum();
		Double max = ch.getMaximum();
		assertEquals(min, min_src, EPSILON);
		assertEquals(max, max_src, EPSILON);
	}

	/**
	 * basic test to read the default scalar type of a quantity. The
	 * intialization via maps is needed to simulate the data store.
	 */
	@org.junit.Test
	public void getQuantity() {
		Map<String, Value> map = new HashMap<>();
		map.put("DefDataType", new Value(ValueType.ENUMERATION, "name", "unit", true, ScalarType.FLOAT,
				ScalarType.class, ScalarType.FLOAT, EnumRegistry.SCALAR_TYPE));
		Core core = new CoreImpl(map);
		Quantity quantity = new Quantity(core);
		ScalarType defaultScalarType = quantity.getDefaultScalarType();
		assertTrue(defaultScalarType.isFloat());
	}

	/**
	 * basic test for building a write request
	 */
	@org.junit.Test
	public void writeRequest() {
		AxisType axisType = AxisType.X_AXIS;
		Core core = new CoreImpl(new HashMap<>());
		ChannelGroup channelGroup = new ChannelGroup(core);
		Channel channel = new Channel(core);
		WriteRequestBuilder wrb = WriteRequest.create(channelGroup, channel, axisType);
		UnitBuilder ub = wrb.implicitConstant(ScalarType.DOUBLE, 0.7d);
		WriteRequest wr = ub.build();
		SequenceRepresentation sequenceRepresentation = wr.getSequenceRepresentation();
		assertTrue(sequenceRepresentation.isConstant());
		assertEquals(AxisType.X_AXIS, wr.getAxisType());
	}

	/**
	 * basic test for getting a measurement channel and all necessary related
	 * objects (measurment, and so on) via factory methods. The intialization
	 * via maps is needed to simulate the data store.
	 */
	@org.junit.Test
	public void entityFactory() {
		Map<String, Value> map = new HashMap<>();
		map.put("Name", ValueType.STRING.create("Name", null, true, "pdnameabc"));
		map.put("Length", ValueType.INTEGER.create("Length", null, true, 13));
		map.put("Mass", ValueType.INTEGER.create("Length", null, true, 0));
		map.put("Time", ValueType.INTEGER.create("Length", null, true, 0));
		map.put("Temperature", ValueType.INTEGER.create("Length", null, true, 0));
		map.put("Current", ValueType.INTEGER.create("Length", null, true, 0));
		map.put("MolarAmount", ValueType.INTEGER.create("Length", null, true, 0));
		map.put("LuminousIntensity", ValueType.INTEGER.create("Length", null, true, 0));
		// TODO (Florian Schmitt): check if angle in lower case is correct
		map.put("angle", ValueType.INTEGER.create("Length", null, true, 0));
		Core core = new CoreImpl(map);
		EntityFactoryImpl ef = new EntityFactoryImpl(core);
		PhysicalDimension physicalDimension = ef.createPhysicalDimension("physdim");

		map = new HashMap<>();
		map.put("Offset", ValueType.DOUBLE.create("Length", null, true, 0d));
		map.put("Factor", ValueType.DOUBLE.create("Length", null, true, 0d));
		map.put("Name", ValueType.STRING.create("Name", null, true, "unitname"));
		core = new CoreImpl(map);
		ef = new EntityFactoryImpl(core);
		Unit unit = ef.createUnit("unit", physicalDimension);

		map = new HashMap<>();
		map.put("Name", ValueType.STRING.create("Name", null, true, "quantname"));
		map.put("Version", ValueType.STRING.create("Version", null, true, "4711"));
		map.put("DateCreated", ValueType.DATE.create("DateCreated", null, true, null));
		map.put("DefaultRank", ValueType.INTEGER.create("DefaultRank", null, true, 5));
		map.put("DefDimension", ValueType.INTEGER_SEQUENCE.create("DefDimension", null, true, new int[] { 5 }));
		map.put("DefTypeSize", ValueType.INTEGER.create("DefTypeSize", null, true, 8));
		map.put("DefMQName", ValueType.STRING.create("DefMQName", null, true, "mqname"));
		map.put("DefDataType", new Value(ValueType.ENUMERATION, "DefDataType", "", true, ScalarType.DOUBLE,
				ScalarType.class, ScalarType.DOUBLE, EnumRegistry.SCALAR_TYPE));
		map.put("ValidFlag", new Value(ValueType.ENUMERATION, "ValidFlag", "", false, VersionState.ARCHIVED,
				VersionState.class, null, EnumRegistry.VERSION_STATE));
		map.put("Description", ValueType.STRING.create("Description", null, true, null));
		core = new CoreImpl(map);
		ef = new EntityFactoryImpl(core);
		Quantity quantity = ef.createQuantity("quantity", unit);
		// Note that default values are set in createQuantity and thus should
		// differ from above.
		assertEquals(ScalarType.FLOAT, quantity.getDefaultScalarType());
		assertEquals((Integer) 1, quantity.getDefaultRank());
		assertEquals("quantity", quantity.getName());
		assertEquals(unit, quantity.getDefaultUnit());

		map = new HashMap<>();
		map.put("Name", ValueType.STRING.create("Name", null, true, null));
		map.put("DateCreated", ValueType.DATE.create("DateCreated", null, true, null));
		core = new CoreImpl(map);
		ef = new EntityFactoryImpl(core);
		Test test = ef.createTest("mytest");

		map = new HashMap<>();
		map.put("Name", ValueType.STRING.create("Name", null, true, null));
		map.put("DateCreated", ValueType.DATE.create("DateCreated", null, true, null));
		map.put("Optional", ValueType.BOOLEAN.create("Optional", null, true, null));
		map.put("Sortindex", ValueType.INTEGER.create("Sortindex", null, true, null));
		core = new CoreImpl(map);
		ef = new EntityFactoryImpl(core);
		TestStep testStep = ef.createTestStep("teststep", test);

		map = new HashMap<>();
		map.put("Name", ValueType.STRING.create("Name", null, true, null));
		map.put("DateCreated", ValueType.DATE.create("DateCreated", null, true, null));
		core = new CoreImpl(map);
		ef = new EntityFactoryImpl(core);
		Measurement measurement = ef.createMeasurement("measurement", testStep);

		map = new HashMap<>();
		map.put("Name", ValueType.STRING.create("Name", null, true, null));
		map.put("Description", ValueType.STRING.create("Description", null, true, null));
		map.put("Interpolation", new Value(ValueType.ENUMERATION, "Interpolation", "", true, null, Interpolation.class,
				null, EnumRegistry.SCALAR_TYPE));
		map.put("DataType", new Value(ValueType.ENUMERATION, "DataType", "", true, null, ScalarType.class, null,
				EnumRegistry.SCALAR_TYPE));

		map.put("TypeSize", ValueType.INTEGER.create("TypeSize", null, true, null));
		map.put("Rank", ValueType.INTEGER.create("Rank", null, true, null));
		core = new CoreImpl(map);
		ef = new EntityFactoryImpl(core);
		Channel channel = ef.createChannel("channel", measurement, quantity);
		assertEquals(Interpolation.NONE, channel.getInterpolation());
	}

	/**
	 * basic test of some ValuType methods.
	 */
	@org.junit.Test
	public void valueType() {
		EnumRegistry.getInstance();
		assertEquals(ValueType.SHORT_SEQUENCE.toSingleType(), ValueType.SHORT);
		assertEquals(ValueType.DATE.toSequenceType(), ValueType.DATE_SEQUENCE);
		assertEquals(ValueType.ENUMERATION_SEQUENCE.toSingleType(), ValueType.ENUMERATION);
		assertEquals(ValueType.ENUMERATION.toSingleType(), ValueType.ENUMERATION);
		assertEquals(ValueType.ENUMERATION, ValueType.ENUMERATION.valueOf(ValueType.ENUMERATION.toSingleType().name()));
		assertEquals(Float.class, ValueType.FLOAT.getValueClass());
		assertEquals(true, ValueType.DOUBLE.isAnyFloatType());
		assertEquals(true, ValueType.DOUBLE.isDouble());
		assertEquals(false, ValueType.DOUBLE.isFloat());
		assertEquals(true, ValueType.FLOAT.isAnyFloatType());
		assertEquals(false, ValueType.INTEGER.isAnyFloatType());
		assertEquals(true, ValueType.INTEGER.isInteger());
		assertEquals(false, ValueType.FLOAT.isInteger());
		assertEquals(true, ValueType.FLOAT.isFloat());
	}

	/**
	 * basic tests of some ScalarType methods
	 */
	@org.junit.Test
	public void scalarType() {
		assertEquals(ValueType.BYTE_SEQUENCE, ScalarType.BYTE.toValueType());
		assertEquals(true, ScalarType.LONG.isLong());
		assertEquals(false, ScalarType.DOUBLE_COMPLEX.isLong());
	}

	/**
	 * basic test of some SequenceReprestentaion methods
	 */
	@org.junit.Test
	public void sequenceRepresentation() {
		assertNotEquals(SequenceRepresentation.EXPLICIT, SequenceRepresentation.IMPLICIT_CONSTANT);
		assertTrue(SequenceRepresentation.EXPLICIT.isExplicit());
		assertTrue(SequenceRepresentation.EXPLICIT_EXTERNAL.isExplicit());
		assertTrue(SequenceRepresentation.EXPLICIT_EXTERNAL.isExternal());
		assertFalse(SequenceRepresentation.EXPLICIT_EXTERNAL.isImplicit());
		assertEquals(SequenceRepresentation.EXPLICIT.ordinal(), (Integer) 0);
		assertEquals((Integer) 5, SequenceRepresentation.RAW_POLYNOMIAL.ordinal());
		assertEquals((Integer) 7, SequenceRepresentation.EXPLICIT_EXTERNAL.ordinal());
		assertEquals((Integer) 9, SequenceRepresentation.RAW_POLYNOMIAL_EXTERNAL.ordinal());
		assertEquals((Integer) 10, SequenceRepresentation.RAW_LINEAR_CALIBRATED.ordinal());
		assertEquals((Integer) 11, SequenceRepresentation.RAW_LINEAR_CALIBRATED_EXTERNAL.ordinal());
	}

	/**
	 * basic tests of TypeSpecification
	 */
	@org.junit.Test
	public void typeSpecification() {
		assertNotEquals(TypeSpecification.BIT_INTEGER, TypeSpecification.BIT_FLOAT_BEO);
		assertEquals(TypeSpecification.BOOLEAN, TypeSpecification.BOOLEAN);
	}

	/**
	 * basic tests of AxisTzpe methods
	 */
	@org.junit.Test
	public void axisType() {
		assertTrue(AxisType.X_AXIS.isXAxis());
		assertTrue(AxisType.Y_AXIS.isYAxis());
		assertTrue(AxisType.XY_AXIS.isXYAxis());
		assertFalse(AxisType.X_AXIS.isYAxis());
		assertFalse(AxisType.Y_AXIS.isXYAxis());
		assertFalse(AxisType.XY_AXIS.isXAxis());
	}

	/**
	 * basic tests of VersionState enumeration
	 */
	@org.junit.Test
	public void versionState() {
		assertFalse(VersionState.ARCHIVED.isEditable());
		assertTrue(VersionState.ARCHIVED.isArchived());
		assertFalse(VersionState.ARCHIVED.isValid());
		assertTrue(VersionState.EDITABLE.isEditable());
		assertFalse(VersionState.EDITABLE.isArchived());
		assertFalse(VersionState.EDITABLE.isValid());
		assertFalse(VersionState.VALID.isEditable());
		assertFalse(VersionState.VALID.isArchived());
		assertTrue(VersionState.VALID.isValid());
	}

	/**
	 * basic tests of interpolation enumeration
	 */
	@org.junit.Test
	public void interpolation() {
		assertTrue(Interpolation.LINEAR.isLinear());
		assertFalse(Interpolation.NONE.isSpecific());
		assertEquals(Interpolation.NONE.ordinal(), (Integer) 0);
		assertEquals(Interpolation.LINEAR.ordinal(), (Integer) 1);
		assertEquals(Interpolation.SPECIFIC.ordinal(), (Integer) 2);
	}

	@org.junit.Test
	public void enumRegistry() {
		EnumRegistry er = EnumRegistry.getInstance();
		assertTrue(er.get("Interpolation") != null);
	}

}
