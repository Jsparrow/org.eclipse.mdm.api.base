/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

/**
 * The type specification of mass data stored in external files.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see Channel
 */
public class TypeSpecification extends EnumerationValue {

	public static final TypeSpecification BOOLEAN = new TypeSpecification("BOOLEAN", 0);

	public static final TypeSpecification BYTE = new TypeSpecification("BYTE", 1);

	public static final TypeSpecification SHORT = new TypeSpecification("SHORT", 2);

	public static final TypeSpecification INTEGER = new TypeSpecification("INTEGER", 3);

	public static final TypeSpecification LONG = new TypeSpecification("LONG", 4);

	public static final TypeSpecification FLOAT = new TypeSpecification("FLOAT", 5);

	public static final TypeSpecification DOUBLE = new TypeSpecification("DOUBLE", 6);

	public static final TypeSpecification SHORT_BEO = new TypeSpecification("SHORT_BEO", 7);

	public static final TypeSpecification INTEGER_BEO = new TypeSpecification("INTEGER_BEO", 8);

	public static final TypeSpecification LONG_BEO = new TypeSpecification("LONG_BEO", 9);

	public static final TypeSpecification FLOAT_BEO = new TypeSpecification("FLOAT_BEO", 10);

	public static final TypeSpecification DOUBLE_BEO = new TypeSpecification("DOUBLE_BEO", 11);

	public static final TypeSpecification STRING = new TypeSpecification("STRING", 12);

	public static final TypeSpecification BYTE_STREAM = new TypeSpecification("BYTE_STREAM", 13);

	public static final TypeSpecification BLOB = new TypeSpecification("BLOB", 14);

	public static final TypeSpecification BOOLEAN_FLAGS_BEO = new TypeSpecification("BOOLEAN_FLAGS_BEO", 15);

	public static final TypeSpecification BYTE_FLAGS_BEO = new TypeSpecification("BYTE_FLAGS_BEO", 16);

	public static final TypeSpecification STRING_FLAGS_BEO = new TypeSpecification("STRING_FLAGS_BEO", 17);

	public static final TypeSpecification BYTE_STREAM_BEO = new TypeSpecification("BYTE_STREAM_BEO", 18);

	// SBYTE,
	public static final TypeSpecification SIGNED_BYTE = new TypeSpecification("SIGNED_BYTE", 19);

	// SBYTE_FLAGS_BEO,
	public static final TypeSpecification SIGNED_BYTE_FLAGS_BEO = new TypeSpecification("SIGNED_BYTE_FLAGS_BEO", 20);

	// USHORT,
	public static final TypeSpecification UNSIGNED_SHORT = new TypeSpecification("UNSIGNED_SHORT", 21);

	// USHORT_BEO,
	public static final TypeSpecification UNSIGNED_SHORT_BEO = new TypeSpecification("UNSIGNED_SHORT_BEO", 22);

	// UINTEGER,
	public static final TypeSpecification UNSIGNED_INTEGER = new TypeSpecification("UNSIGNED_INTEGER", 23);

	// UINTEGER_BEO,
	public static final TypeSpecification UNSIGNED_INTEGER_BEO = new TypeSpecification("UNSIGNED_INTEGER_BEO", 24);

	public static final TypeSpecification STRING_UTF8 = new TypeSpecification("STRING_UTF8", 25);

	public static final TypeSpecification STRING_UTF8_FLAGS_BEO = new TypeSpecification("STRING_UTF8_FLAGS_BEO", 26);

	// BIT_INT,
	public static final TypeSpecification BIT_INTEGER = new TypeSpecification("BIT_INTEGER", 27);

	// BIT_INT_BEO,
	public static final TypeSpecification BIT_INTEGER_BEO = new TypeSpecification("BIT_INTEGER_BEO", 28);

	// BIT_UINT,
	public static final TypeSpecification BIT_UNSIGNED_INTEGER = new TypeSpecification("BIT_UNSIGNED_INTEGER", 29);

	// BIT_UINT_BEO,
	public static final TypeSpecification BIT_UNSIGNED_INTEGER_BEO = new TypeSpecification("BIT_UNSIGNED_INTEGER_BEO", 30);

	public static final TypeSpecification BIT_FLOAT = new TypeSpecification("BIT_FLOAT", 31);

	public static final TypeSpecification BIT_FLOAT_BEO = new TypeSpecification("BIT_FLOAT_BEO", 32);

	private TypeSpecification(String name, int ordinal) {
		super(name, ordinal);
	}
}
