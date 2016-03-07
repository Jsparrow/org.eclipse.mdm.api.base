package org.eclipse.mdm.api.base.model;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation of the parameter entity type. Instances of this class hold a
 * value in its {@code String} representation, which is converted upon request
 * and provided as a virtual {@link Value}. The returned {@code Value} is for
 * displaying purposes only. To change the value, held by this parameter, one
 * of its {@code setXYValue(XY)} methods has to be used. {@link ValueType}s
 * supported by this parameter are listed below:
 *
 * <ul>
 * 	<li>{@link ValueType#STRING}</li>
 * 	<li>{@link ValueType#DATE}</li>
 * 	<li>{@link ValueType#BOOLEAN}</li>
 * 	<li>{@link ValueType#BYTE}</li>
 * 	<li>{@link ValueType#SHORT}</li>
 * 	<li>{@link ValueType#INTEGER}</li>
 * 	<li>{@link ValueType#LONG}</li>
 * 	<li>{@link ValueType#FLOAT}</li>
 * 	<li>{@link ValueType#DOUBLE}</li>
 * 	<li>{@link ValueType#FLOAT_COMPLEX}</li>
 * 	<li>{@link ValueType#DOUBLE_COMPLEX}</li>
 * </ul>
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 * @author Sebastian Dirsch, Gigatronik Ingolstadt GmbH
 * @see ParameterSet
 * @see #getVirtualValue()
 * @see #setObjectValue(Object, Optional)
 * @see #setStringValue(String)
 * @see #setDateValue(LocalDateTime)
 * @see #setBooleanValue(Boolean)
 * @see #setByteValue(Byte, Optional)
 * @see #setShortValue(Short, Optional)
 * @see #setIntegerValue(Integer, Optional)
 * @see #setLongValue(Long, Optional)
 * @see #setFloatValue(Float, Optional)
 * @see #setDoubleValue(Double, Optional)
 * @see #setFloatComplexValue(FloatComplex, Optional)
 * @see #setDoubleComplexValue(DoubleComplex, Optional)
 */
public final class Parameter extends BaseParameter {

	// ======================================================================
	// Class variables
	// ======================================================================

	/**
	 * The 'ScalarType' attribute name.
	 */
	public static final String ATTR_SCALAR_TYPE = "DataType";

	/**
	 * The 'Value' attribute name.
	 */
	public static final String ATTR_VALUE = "Value";

	// ======================================================================
	// Constructors
	// ======================================================================

	public Parameter(Core core) {
		super(ATTR_SCALAR_TYPE, ATTR_VALUE, core);
	}

}
