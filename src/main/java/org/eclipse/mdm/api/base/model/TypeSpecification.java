/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
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
public enum TypeSpecification {

	// ======================================================================
	// Enumerations
	// ======================================================================

	BOOLEAN,

	BYTE,

	SHORT,

	INTEGER,

	LONG,

	FLOAT,

	DOUBLE,

	SHORT_BEO,

	INTEGER_BEO,

	LONG_BEO,

	FLOAT_BEO,

	DOUBLE_BEO,

	STRING,

	BYTE_STREAM,

	BLOB,

	BOOLEAN_FLAGS_BEO,

	BYTE_FLAGS_BEO,

	STRING_FLAGS_BEO,

	BYTE_STREAM_BEO,

	// SBYTE,
	SIGNED_BYTE,

	// SBYTE_FLAGS_BEO,
	SIGNED_BYTE_FLAGS_BEO,

	// USHORT,
	UNSIGNED_SHORT,

	// USHORT_BEO,
	UNSIGNED_SHORT_BEO,

	// UINTEGER,
	UNSIGNED_INTEGER,

	// UINTEGER_BEO,
	UNSIGNED_INTEGER_BEO,

	STRING_UTF8,

	STRING_UTF8_FLAGS_BEO,

	// BIT_INT,
	BIT_INTEGER,

	// BIT_INT_BEO,
	BIT_INTEGER_BEO,

	// BIT_UINT,
	BIT_UNSIGNED_INTEGER,

	// BIT_UINT_BEO,
	BIT_UNSIGNED_INTEGER_BEO,

	BIT_FLOAT,

	BIT_FLOAT_BEO

}
