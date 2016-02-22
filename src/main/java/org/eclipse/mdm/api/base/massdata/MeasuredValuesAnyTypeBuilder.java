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

public class MeasuredValuesAnyTypeBuilder extends MeasuredValuesComplexNumericalBuilder {

	MeasuredValuesAnyTypeBuilder(WriteRequest writeRequest) {
		super(writeRequest);
	}

	public WriteRequestFinalizer stringValues(String[] values) {
		createMeasuredValues(ScalarType.STRING, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	public WriteRequestFinalizer stringValues(String[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.STRING, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	public IndependentBuilder dateValues(LocalDateTime[] values) {
		createMeasuredValues(ScalarType.DATE, values);
		return new IndependentBuilder(writeRequest);
	}

	public IndependentBuilder dateValues(LocalDateTime[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.DATE, values, flags);
		return new IndependentBuilder(writeRequest);
	}

	public WriteRequestFinalizer booleanValues(boolean[] values) {
		createMeasuredValues(ScalarType.BOOLEAN, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	public WriteRequestFinalizer booleanValues(boolean[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.BOOLEAN, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	public WriteRequestFinalizer byteStreamValues(byte[][] values) {
		createMeasuredValues(ScalarType.BYTE_STREAM, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	public WriteRequestFinalizer byteStreamValues(byte[][] values, boolean[] flags) {
		createMeasuredValues(ScalarType.BYTE_STREAM, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	public WriteRequestFinalizer fileLinkValues(FileLink[] values) {
		createMeasuredValues(ScalarType.FILE_LINK, values);
		return new WriteRequestFinalizer(writeRequest);
	}

	public WriteRequestFinalizer fileLinkValues(FileLink[] values, boolean[] flags) {
		createMeasuredValues(ScalarType.FILE_LINK, values, flags);
		return new WriteRequestFinalizer(writeRequest);
	}

	// TODO: is it possible to provide an blob for each row or is the blob for the whole column?!
	// we assume the latter - one blob for the whole column!
	public WriteRequestFinalizer blobValue(Object values) {
		createMeasuredValues(ScalarType.BLOB, values);
		return new WriteRequestFinalizer(writeRequest);
	}

}