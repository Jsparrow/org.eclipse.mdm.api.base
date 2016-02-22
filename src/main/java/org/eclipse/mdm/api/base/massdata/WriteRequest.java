/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.massdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.mdm.api.base.model.AxisType;
import org.eclipse.mdm.api.base.model.Channel;
import org.eclipse.mdm.api.base.model.ChannelGroup;
import org.eclipse.mdm.api.base.model.MeasuredValues;
import org.eclipse.mdm.api.base.model.ScalarType;
import org.eclipse.mdm.api.base.model.SequenceRepresentation;
import org.eclipse.mdm.api.base.model.Unit;

public final class WriteRequest {

	private final List<ExternalComponent> externalComponents = new ArrayList<>();
	private final ChannelGroup channelGroup;
	private final Channel channel;

	private final AxisType axisType;

	private SequenceRepresentation sequenceRepresentation;
	private double[] generationParameters = new double[0];
	private boolean independent;

	private MeasuredValues measuredValues;

	private WriteRequest(ChannelGroup channelGroup, Channel channel, AxisType axisType) {
		this.channelGroup = channelGroup;
		this.channel = channel;
		this.axisType = axisType;
	}

	public static WriteRequestBuilder create(ChannelGroup channelGroup, Channel channel, AxisType axisType) {
		return new WriteRequestBuilder(new WriteRequest(channelGroup, channel, axisType));
	}

	public ChannelGroup getChannelGroup() {
		return channelGroup;
	}

	public Channel getChannel() {
		return channel;
	}

	public SequenceRepresentation getSequenceRepresentation() {
		return sequenceRepresentation;
	}

	public AxisType getAxisType() {
		return axisType;
	}

	public double[] getGenerationParameters() {
		return generationParameters.clone();
	}

	public boolean isIndependent() {
		return independent;
	}

	public boolean hasMeasuredValues() {
		return measuredValues != null;
	}

	public MeasuredValues getMeasuredValues() {
		if(!hasMeasuredValues()) {
			throw new IllegalStateException(); // TODO: must have external components...
		}

		return measuredValues;
	}

	public boolean hasExternalComponents() {
		return externalComponents.isEmpty();
	}

	public List<ExternalComponent> getExternalComponents() {
		if(!hasExternalComponents()) {
			throw new IllegalStateException(); // TODO: must measured values...
		}

		return Collections.unmodifiableList(externalComponents);
	}

	public ScalarType getCalculatedScalarType() {
		if(getSequenceRepresentation().isImplicit()) {
			return getRawScalarType();
		}

		ScalarType rawScalarType = getRawScalarType();
		boolean hasGenerationParameters = getGenerationParameters().length > 0;
		boolean isIntegerType = rawScalarType.isIntegerType();

		if(hasGenerationParameters && isIntegerType) {
			return rawScalarType.isLong() ? ScalarType.DOUBLE : ScalarType.FLOAT;
		}

		return rawScalarType;
	}

	public boolean areAllValid() {
		if(hasMeasuredValues()) {
			return getMeasuredValues().areAllValid();
		} else if(hasExternalComponents()) {
			// TODO
		}

		throw new IllegalStateException(); // TODO
	}

	public ScalarType getRawScalarType() {
		if(hasMeasuredValues()) {
			return getMeasuredValues().getScalarType();
		} else if(hasExternalComponents()) {
			// TODO
		}

		throw new IllegalStateException(); // TODO msg...
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("WriteRequest(channelGroup = ");

		sb.append(getChannelGroup()).append(", channel = ").append(getChannel()).append(", axisType = ").append(getAxisType());
		sb.append(", sequenceRepresentation = ").append(getSequenceRepresentation());

		if(getGenerationParameters().length > 0) {
			sb.append(", generationParameters = ").append(Arrays.toString(generationParameters));
		}

		sb.append(", independent = ").append(isIndependent());

		if(hasMeasuredValues()) {
			sb.append(", measuredValues = ").append(getMeasuredValues());
		} else if(hasExternalComponents()) {
			sb.append(", externalComponents = ").append(getExternalComponents());
		}

		return sb.append(')').toString();
	}

	void setSequenceRepresentation(SequenceRepresentation sequenceRepresentation) {
		this.sequenceRepresentation = sequenceRepresentation;
	}

	void setGenerationParameters(double[] generationParameters) {
		this.generationParameters = generationParameters;
	}

	void setIndependent(boolean independent) {
		this.independent = independent;
	}

	void setTargetUnit(Unit sourceUnit) {
		Unit targetUnit = getChannel().getUnit();

		if(!targetUnit.getName().equals(sourceUnit.getName())) {
			if(!targetUnit.getPhysicalDimension().getName().equals(sourceUnit.getPhysicalDimension().getName())) {
				// conversion is not possible!

				// TODO: different physical dimensions -> replace Unit of channel with given sourceUnit!
				// MeaQuantityImpl.addDataValues()

				// TODO or simply throw an exception?!
				// MeaQuantityImpl.addDataFromExternalComponentInUnit

				// what to do?!
				return;
			}

			// TODO convert from source to target unit!
			SequenceRepresentation sequenceRepresentation = getSequenceRepresentation();
			if(sequenceRepresentation.isExplicit()) {
				if(sequenceRepresentation.isExternal()) {
					// TODO convert value in measuredValues
				} else {
					// values stored in file so what to do?!
				}
			} else if(sequenceRepresentation.isImplicit()) {
				// TODO adjust generation parameters
				// ATTENTION -> generation parameters are stored in MeasuredValues! (new ODS standard!)
			} else {
				// TODO adjust generation parameters for all other SeqReps....
			}



			// TODO Problematik bei Umrechnung impliziter INTEGER Kanäle -> Anpassen des RAW Datentyps?!

			// TODO Problematik bei Umrechung Einheit in dB zu Einheit in nicht dB
			// TODO Problematik bei Umrechung Einheit in nicht dB zu Einheit in dB

			// TODO: should we do this implicitly within the WritRequest?!
			// ==> implementation is central and adapter implementation independent!
		}

		/*
		 * TODO do conversion here implicitly!
		 */

	}

	void setMeasuredValues(MeasuredValues measuredValues) {
		this.measuredValues = measuredValues;
	}

	void addExternalComponent(ExternalComponent externalComponent) {
		externalComponents.add(externalComponent);
	}

}
