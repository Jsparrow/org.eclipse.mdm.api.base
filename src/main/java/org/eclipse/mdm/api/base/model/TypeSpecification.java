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

	// ======================================================================
	// Enumerations
	// ======================================================================

	final static TypeSpecification BOOLEAN = new TypeSpecification(0);

	final static TypeSpecification BYTE = new TypeSpecification(1);

	final static TypeSpecification SHORT = new TypeSpecification(2);

	final static TypeSpecification INTEGER = new TypeSpecification(3);

	final static TypeSpecification LONG = new TypeSpecification(4);

	final static TypeSpecification FLOAT = new TypeSpecification(5);

	final static TypeSpecification DOUBLE = new TypeSpecification(6);

	final static TypeSpecification SHORT_BEO = new TypeSpecification(7);

	final static TypeSpecification INTEGER_BEO = new TypeSpecification(8);

	final static TypeSpecification LONG_BEO = new TypeSpecification(9);

	final static TypeSpecification FLOAT_BEO = new TypeSpecification(10);

	final static TypeSpecification DOUBLE_BEO = new TypeSpecification(11);

	final static TypeSpecification STRING = new TypeSpecification(12);

	final static TypeSpecification BYTE_STREAM = new TypeSpecification(13);

	final static TypeSpecification BLOB = new TypeSpecification(14);

	final static TypeSpecification BOOLEAN_FLAGS_BEO = new TypeSpecification(15);

	final static TypeSpecification BYTE_FLAGS_BEO = new TypeSpecification(16);

	final static TypeSpecification STRING_FLAGS_BEO = new TypeSpecification(17);

	final static TypeSpecification BYTE_STREAM_BEO = new TypeSpecification(18);

	// SBYTE,
	final static TypeSpecification SIGNED_BYTE = new TypeSpecification(19);

	// SBYTE_FLAGS_BEO,
	final static TypeSpecification SIGNED_BYTE_FLAGS_BEO = new TypeSpecification(20);

	// USHORT,
	final static TypeSpecification UNSIGNED_SHORT = new TypeSpecification(21);

	// USHORT_BEO,
	final static TypeSpecification UNSIGNED_SHORT_BEO = new TypeSpecification(22);

	// UINTEGER,
	final static TypeSpecification UNSIGNED_INTEGER = new TypeSpecification(23);

	// UINTEGER_BEO,
	final static TypeSpecification UNSIGNED_INTEGER_BEO = new TypeSpecification(24);

	final static TypeSpecification STRING_UTF8 = new TypeSpecification(25);

	final static TypeSpecification STRING_UTF8_FLAGS_BEO = new TypeSpecification(26);

	// BIT_INT,
	final static TypeSpecification BIT_INTEGER = new TypeSpecification(27);

	// BIT_INT_BEO,
	final static TypeSpecification BIT_INTEGER_BEO = new TypeSpecification(28);

	// BIT_UINT,
	final static TypeSpecification BIT_UNSIGNED_INTEGER = new TypeSpecification(29);

	// BIT_UINT_BEO,
	final static TypeSpecification BIT_UNSIGNED_INTEGER_BEO = new TypeSpecification(30);

	final static TypeSpecification BIT_FLOAT = new TypeSpecification(31);

	final static TypeSpecification BIT_FLOAT_BEO = new TypeSpecification(32);

	/**
	 * Constructor, sets the order
	 */
	TypeSpecification(int ord) {
		super(ord);
	}
}
