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

	final static TypeSpecification BOOLEAN = new TypeSpecification();

	final static TypeSpecification BYTE = new TypeSpecification();

	final static TypeSpecification SHORT = new TypeSpecification();

	final static TypeSpecification INTEGER = new TypeSpecification();

	final static TypeSpecification LONG = new TypeSpecification();

	final static TypeSpecification FLOAT = new TypeSpecification();

	final static TypeSpecification DOUBLE = new TypeSpecification();

	final static TypeSpecification SHORT_BEO = new TypeSpecification();

	final static TypeSpecification INTEGER_BEO = new TypeSpecification();

	final static TypeSpecification LONG_BEO = new TypeSpecification();

	final static TypeSpecification FLOAT_BEO = new TypeSpecification();

	final static TypeSpecification DOUBLE_BEO = new TypeSpecification();

	final static TypeSpecification STRING = new TypeSpecification();

	final static TypeSpecification BYTE_STREAM = new TypeSpecification();

	final static TypeSpecification BLOB = new TypeSpecification();

	final static TypeSpecification BOOLEAN_FLAGS_BEO = new TypeSpecification();

	final static TypeSpecification BYTE_FLAGS_BEO = new TypeSpecification();

	final static TypeSpecification STRING_FLAGS_BEO = new TypeSpecification();

	final static TypeSpecification BYTE_STREAM_BEO = new TypeSpecification();

	// SBYTE,
	final static TypeSpecification SIGNED_BYTE = new TypeSpecification();

	// SBYTE_FLAGS_BEO,
	final static TypeSpecification SIGNED_BYTE_FLAGS_BEO = new TypeSpecification();

	// USHORT,
	final static TypeSpecification UNSIGNED_SHORT = new TypeSpecification();

	// USHORT_BEO,
	final static TypeSpecification UNSIGNED_SHORT_BEO = new TypeSpecification();

	// UINTEGER,
	final static TypeSpecification UNSIGNED_INTEGER = new TypeSpecification();

	// UINTEGER_BEO,
	final static TypeSpecification UNSIGNED_INTEGER_BEO = new TypeSpecification();

	final static TypeSpecification STRING_UTF8 = new TypeSpecification();

	final static TypeSpecification STRING_UTF8_FLAGS_BEO = new TypeSpecification();

	// BIT_INT,
	final static TypeSpecification BIT_INTEGER = new TypeSpecification();

	// BIT_INT_BEO,
	final static TypeSpecification BIT_INTEGER_BEO = new TypeSpecification();

	// BIT_UINT,
	final static TypeSpecification BIT_UNSIGNED_INTEGER = new TypeSpecification();

	// BIT_UINT_BEO,
	final static TypeSpecification BIT_UNSIGNED_INTEGER_BEO = new TypeSpecification();

	final static TypeSpecification BIT_FLOAT = new TypeSpecification();

	final static TypeSpecification BIT_FLOAT_BEO = new TypeSpecification();

}
