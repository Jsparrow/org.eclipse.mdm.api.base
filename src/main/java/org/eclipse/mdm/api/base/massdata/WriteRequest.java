/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.mdm.api.base.model.AxisType;
import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ChannelGroup;
import org.eclipse.mdm.api.base.model.ScalarType;
import org.eclipse.mdm.api.base.model.SequenceRepresentation;
import org.eclipse.mdm.api.base.model.Unit;

/**
 * Holds required data to write mass data.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 */
public final class WriteRequest {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final List<ExternalComponent> externalComponents = new ArrayList<>();
	private final ChannelGroup channelGroup;
	private final Channel channel;
	private final AxisType axisType;

	private SequenceRepresentation sequenceRepresentation;
	private double[] generationParameters;
	private boolean independent;

	private ScalarType rawScalarType;
	private Object values;
	private boolean allValid;
	private boolean[] flags;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param channelGroup The {@link ChannelGroup} for this request.
	 * @param channel The {@link Channel} specified mass data will be dedicated to.
	 * @param axisType The {@link AxisType} of the written mass data.
	 */
	private WriteRequest(ChannelGroup channelGroup, Channel channel, AxisType axisType) {
		this.channelGroup = channelGroup;
		this.channel = channel;
		this.axisType = axisType;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Starts a new {@link WriteRequest} by returning a {@link WriteRequestBuilder}.
	 *
	 * @param channelGroup The {@link ChannelGroup} for this request.
	 * @param channel The {@link Channel} specified mass data will be dedicated to.
	 * @param axisType The {@link AxisType} of the written mass data.
	 * @return A {@code WriteRequestBuilder} is returned.
	 */
	public static WriteRequestBuilder create(ChannelGroup channelGroup, Channel channel, AxisType axisType) {
		return new WriteRequestBuilder(new WriteRequest(channelGroup, channel, axisType));
	}

	/**
	 * Returns the {@link ChannelGroup} of this request.
	 *
	 * @return The {@code ChannelGroup} is returned.
	 */
	public ChannelGroup getChannelGroup() {
		return channelGroup;
	}

	/**
	 * Returns the {@link Channel} of this request.
	 *
	 * @return The {@code Channel} is returned.
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Returns the {@link AxisType} of this request.
	 *
	 * @return The {@code AxisType} is returned.
	 */
	public AxisType getAxisType() {
		return axisType;
	}

	/**
	 * Returns the {@link SequenceRepresentation} of this request.
	 *
	 * @return The {@code SequenceRepresentation} is returned.
	 */
	public SequenceRepresentation getSequenceRepresentation() {
		return sequenceRepresentation;
	}

	/**
	 * Returns the generation parameters of this request. The length of the
	 * returned array depends on the {@link SequenceRepresentation} of this
	 * request.
	 *
	 * <p><b>NOTE:</b> In case of an implicit sequence representation this
	 * method will always return an empty array since these generation
	 * parameters are correctly typed and stored as measured values.
	 *
	 * @return If none available, then an empty array is returned.
	 */
	public double[] getGenerationParameters() {
		return generationParameters == null ? new double[0] : generationParameters.clone();
	}

	/**
	 * Returns the independent flag of this request.
	 *
	 * @return Returns {@code true} if the measured values do not depend on
	 * 		those of other {@link Channel}s within their common {@link
	 * 		ChannelGroup}.
	 */
	public boolean isIndependent() {
		return independent;
	}

	/**
	 * Checks whether this request has measured values.
	 *
	 * @return Returns {@code true} if {@link
	 * 		SequenceRepresentation#isExternal()} returns {@code false}.
	 * @see #hasExternalComponents()
	 */
	public boolean hasValues() {
		return !getSequenceRepresentation().isExternal();
	}

	/**
	 * Returns the stored measured values.
	 *
	 * @return The measured values are returned.
	 * @throws IllegalStateException Thrown if values are not available.
	 */
	public Object getValues() {
		if(hasValues()) {
			return values;
		}

		throw new IllegalStateException("Values are not available.");
	}

	/**
	 * Checks whether this request has measured values, stored in externally
	 * linked files.
	 *
	 * @return Returns {@code true} if {@link
	 * 		SequenceRepresentation#isExternal()} returns {@code true}.
	 * @see #hasValues()
	 */
	public boolean hasExternalComponents() {
		return getSequenceRepresentation().isExternal();
	}

	/**
	 * Returns the configurations for measured values stored in externally
	 * referenced files.

	 * @return Returned {@code List} is unmodifiable.
	 * @throws IllegalStateException Thrown if configurations are not available.
	 */
	public List<ExternalComponent> getExternalComponents() {
		if(!hasExternalComponents()) {
			throw new IllegalStateException("External components are not available.");
		}

		return Collections.unmodifiableList(externalComponents);
	}

	/**
	 * Returns the {@link ScalarType} of the stored measured value sequence.
	 *
	 * @return The raw {@code ScalarType} is returned.
	 */
	public ScalarType getRawScalarType() {
		return rawScalarType;
	}

	/**
	 * Returns the calculated {@link ScalarType} which reflects the final
	 * {@code ScalarType} of the generated measured value sequence. If {@link
	 * #getGenerationParameters()} returns a not empty array and {@link
	 * #getRawScalarType()} is an integer type (byte, short, int, long), then
	 * the returned {@code ScalarType} is bumped to the next suitable floating
	 * point type as listed below:
	 *
	 * <ul>
	 * 	<li>{@link ScalarType#BYTE} -&gt; {@link ScalarType#FLOAT}</li>
	 * 	<li>{@link ScalarType#SHORT} -&gt; {@link ScalarType#FLOAT}</li>
	 * 	<li>{@link ScalarType#INTEGER} -&gt; {@link ScalarType#FLOAT}</li>
	 * 	<li>{@link ScalarType#LONG} -&gt; {@link ScalarType#DOUBLE}</li>
	 * </ul>
	 *
	 * @return The calculated {@code ScalarType} is returned.
	 */
	public ScalarType getCalculatedScalarType() {
		if(getGenerationParameters().length > 0 && getRawScalarType().isIntegerType()) {
			return getRawScalarType().isLong() ? ScalarType.DOUBLE : ScalarType.FLOAT;
		}

		return getRawScalarType();
	}

	/**
	 * Checks whether all measured values within the whole sequence are valid.
	 * If this method returns {@code true}, then {@link #getFlags()} will
	 * return an empty array.
	 *
	 * @return Returns {@code true} if all measured values are valid.
	 * @see #getFlags()
	 */
	public boolean areAllValid() {
		return allValid;
	}

	/**
	 * Returns the validity flags sequence for the stored measured values.
	 * If {@link #areAllValid()} returns {@code false} this method will
	 * return an array with the same length as the measured values sequence,
	 * where each flag indicates whether the corresponding measured value is
	 * valid or not.
	 *
	 * @return The validity flags sequence is returned.
	 * @see #allValid
	 */
	public boolean[] getFlags() {
		return flags;
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Sets the {@link SequenceRepresentation} for this request.
	 *
	 * @param sequenceRepresentation The {@link SequenceRepresentation}.
	 */
	void setSequenceRepresentation(SequenceRepresentation sequenceRepresentation) {
		this.sequenceRepresentation = sequenceRepresentation;
	}

	/**
	 * Sets generation parameters for this request.
	 *
	 * @param generationParameters The generation parameters.
	 */
	void setGenerationParameters(double[] generationParameters) {
		this.generationParameters = generationParameters.clone();
	}

	/**
	 * Sets the independent flag for this request.
	 */
	void setIndependent() {
		independent = true;
	}

	/**
	 * Triggers an adjustment of the generation parameters and modification
	 * of the {@link SequenceRepresentation} of this request to match the
	 * {@link Channel}s default {@link Unit}.
	 *
	 * @param sourceUnit The {@link Unit} of the measured values.
	 */
	void setSourceUnit(Unit sourceUnit) {
		Unit targetUnit = getChannel().getUnit();

		if(!targetUnit.nameMatches(sourceUnit.getName())) {
			if(!targetUnit.getPhysicalDimension().nameMatches(sourceUnit.getPhysicalDimension().getName())) {
				// conversion is not possible!
				//
				// different physical dimensions -> replace Unit of channel with given sourceUnit!
				// MeaQuantityImpl.addDataValues()
				//
				//  or simply throw an exception?!
				// MeaQuantityImpl.addDataFromExternalComponentInUnit
				//
				// what to do?!
			}

			// convert from source to target unit!
			SequenceRepresentation sequenceRepresentation = getSequenceRepresentation();
			if(sequenceRepresentation.isExplicit()) {
				if(sequenceRepresentation.isExternal()) {
					// values stored in file so what to do?!
					// switch sequenceRepresentation to SequenceRepresentation.RAW_LINEAR?
				} else {
					// convert this.values
				}
			} else if(sequenceRepresentation.isImplicit()) {
				// adjust generation parameters
				// ATTENTION -> generation parameters are stored in this.values! (new ODS standard!)
			} else {
				// adjust generation parameters for all other SeqReps....
			}



			// Problematik bei Umrechnung impliziter INTEGER Kanäle -> Anpassen des RAW Datentyps?!

			// Problematik bei Umrechung Einheit in dB zu Einheit in nicht dB
			// Problematik bei Umrechung Einheit in nicht dB zu Einheit in dB

			//  should we do this implicitly within the WritRequest?!
			// ==> implementation is central and adapter implementation independent!
		}

		throw new UnsupportedOperationException("Conversion between units is not implemented yet.");
	}

	/**
	 * Sets the raw {@link ScalarType} for this request.
	 *
	 * @param rawScalarType The {@link ScalarType}.
	 */
	void setRawScalarType(ScalarType rawScalarType) {
		this.rawScalarType = rawScalarType;
	}

	/**
	 * Sets the measured values sequence where each value in the sequence is
	 * expected to be valid.
	 *
	 * @param values The measured value sequence.
	 * @throws IllegalStateException Thrown if {@link #hasValues()} returns
	 * 		{@code false}.
	 */
	void setValues(Object values) {
		if(hasValues()) {
			this.values = values;
			allValid = true;
		} else {
			throw new IllegalStateException("Measured values stored in externally linked files expected.");
		}
	}

	/**
	 * Sets the measured values sequence where each value's validity is
	 * specified in the given flags array.
	 *
	 * @param values The measured value sequence.
	 * @param flags The validity flag sequence.
	 * @throws IllegalStateException Thrown if {@link #hasValues()} returns
	 * 		{@code false}.
	 */
	void setValues(Object values, boolean[] flags) {
		if(hasValues()) {
			this.values = values;
			this.flags = flags.clone();
		} else {
			throw new IllegalStateException("Measured values stored in externally linked files expected.");
		}
	}

	/**
	 * Adds a configuration for measured values stored in externally linked files.
	 *
	 * @param externalComponent The new configuration.
	 * @throws IllegalStateException Thrown if {@link #hasExternalComponents()}
	 * 		returns {@code false}.
	 */
	void addExternalComponent(ExternalComponent externalComponent) {
		if(hasExternalComponents()) {
			externalComponents.add(externalComponent);
		} else {
			throw new IllegalStateException("Measured values expected");
		}
	}

}
