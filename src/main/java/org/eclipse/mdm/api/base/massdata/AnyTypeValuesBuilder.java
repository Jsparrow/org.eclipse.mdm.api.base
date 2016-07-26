/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.time.LocalDateTime;

import org.eclipse.mdm.api.base.model.FileLink;
import org.eclipse.mdm.api.base.model.ScalarType;

/**
 * This builder adds values to the {@link WriteRequest} of types listed below.
 * It is possible to add a sequence where all of its values are valid.
 * Additionally it is possible to provide a validity flag for each single
 * value. In either case neither values nor flags are allowed to be {@code
 * null}. Furthermore if values and flags are given, then their length must be
 * equal.
 *
 * <ul>
 * 	<li>{@link ScalarType#STRING}</li>
 * 	<li>{@link ScalarType#DATE}</li>
 * 	<li>{@link ScalarType#BOOLEAN}</li>
 * 	<li>{@link ScalarType#BYTE}</li>
 * 	<li>{@link ScalarType#SHORT}</li>
 * 	<li>{@link ScalarType#INTEGER}</li>
 * 	<li>{@link ScalarType#LONG}</li>
 * 	<li>{@link ScalarType#FLOAT}</li>
 * 	<li>{@link ScalarType#DOUBLE}</li>
 * 	<li>{@link ScalarType#BYTE_STREAM}</li>
 * 	<li>{@link ScalarType#FLOAT_COMPLEX}</li>
 * 	<li>{@link ScalarType#DOUBLE_COMPLEX}</li>
 * 	<li>{@link ScalarType#FILE_LINK}</li>
 * 	<li>{@link ScalarType#BLOB}</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class AnyTypeValuesBuilder extends ComplexNumericalValuesBuilder {

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param writeRequest The {@link WriteRequest} given values will be added
	 * 		to.
	 */
	AnyTypeValuesBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Adds given {@code String} values to the {@link WriteRequest} with a
	 * global validity flag, which means the given sequence does not contain
	 * any {@code null} values.
	 *
	 * @param values The {@code String} array sequence.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer stringValues(String[] values) {
		createValues(ScalarType.STRING, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@code String} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code String} array sequence.
	 * @param flags The validity flags for each {@code String} value.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer stringValues(String[] values, boolean[] flags) {
		createValues(ScalarType.STRING, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@code LocalDateTime} values to the {@link WriteRequest} with
	 * a global validity flag, which means the given sequence does not contain
	 * any {@code null} values.
	 *
	 * @param values The {@code LocalDateTime} array sequence.
	 * @return The {@link IndependentBuilder} is returned.
	 */
	public IndependentBuilder dateValues(LocalDateTime[] values) {
		createValues(ScalarType.DATE, values);
		return new IndependentBuilder(writeRequest);
	}

	/**
	 * Adds given {@code LocalDateTime} values to the {@link WriteRequest} with
	 * a validity flag for each given value.
	 *
	 * @param values The {@code LocalDateTime} array sequence.
	 * @param flags The validity flags for each {@code LocalDateTime} value.
	 * @return The {@link IndependentBuilder} is returned.
	 */
	public IndependentBuilder dateValues(LocalDateTime[] values, boolean[] flags) {
		createValues(ScalarType.DATE, values, flags);
		return new IndependentBuilder(writeRequest);
	}

	/**
	 * Adds given {@code boolean} values to the {@link WriteRequest} with a
	 * global validity flag.
	 *
	 * @param values The {@code boolean} array sequence.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer booleanValues(boolean[] values) {
		createValues(ScalarType.BOOLEAN, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@code boolean} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code boolean} array sequence.
	 * @param flags The validity flags for each {@code boolean} value.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer booleanValues(boolean[] values, boolean[] flags) {
		createValues(ScalarType.BOOLEAN, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@code byte[]} values to the {@link WriteRequest} with a
	 * global validity flag, which means the given sequence does not contain
	 * any {@code null} values.
	 *
	 * @param values The {@code byte[]} array sequence.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer byteStreamValues(byte[][] values) {
		createValues(ScalarType.BYTE_STREAM, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@code byte[]} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code byte[]} array sequence.
	 * @param flags The validity flags for each {@code byte[]} value.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer byteStreamValues(byte[][] values, boolean[] flags) {
		createValues(ScalarType.BYTE_STREAM, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@link FileLink} values to the {@link WriteRequest} with a
	 * global validity flag, which means the given sequence does not contain
	 * any {@code null} values.
	 *
	 * @param values The {@code FileLink} array sequence.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer fileLinkValues(FileLink[] values) {
		createValues(ScalarType.FILE_LINK, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	/**
	 * Adds given {@link FileLink} values to the {@link WriteRequest} with a
	 * validity flag for each given value.
	 *
	 * @param values The {@code FileLink} array sequence.
	 * @param flags The validity flags for each {@code FileLink} value.
	 * @return The {@link WriteRequestFinalizer} is returned.
	 */
	public WriteRequestFinalizer fileLinkValues(FileLink[] values, boolean[] flags) {
		createValues(ScalarType.FILE_LINK, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	// TODO: is it possible to provide a blob for each row or is the blob for the whole column?!
	// we assume the latter - one blob for the whole column!
	public WriteRequestFinalizer blobValue(Object values) {
		createValues(ScalarType.BLOB, values);
		throw new UnsupportedOperationException("Not implemented.");
	}

}
