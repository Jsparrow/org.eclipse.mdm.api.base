package org.eclipse.mdm.api.base.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.core.ChildrenStore;
import org.eclipse.mdm.api.base.core.Core;
import org.eclipse.mdm.api.base.core.EntityStore;


public class ContextSensorTest {

	@org.junit.Test
	public void testGetContextComponent() {
		BaseEntityFactory factory = spy(BaseEntityFactory.class);
		Core rootCore = createCore("TESTEQUIPMENT");
	    when(factory.createCore(ContextRoot.class, ContextType.TESTEQUIPMENT)).thenReturn(rootCore);
	    Core componentCore = createCore("");
	    when(factory.createCore("SensorDevice", ContextComponent.class)).thenReturn(componentCore);
	    Core sensorCore = createCore("");
	    when(factory.createCore("Sensor", ContextSensor.class)).thenReturn(sensorCore);
	    
		ContextRoot contextRoot = factory.createContextRoot("SensorDevices", ContextType.TESTEQUIPMENT);
		ContextComponent contextComponent = factory.createContextComponent("SensorDevice", contextRoot);
		ContextSensor contextSensor = factory.createContextSensor("Sensor", contextComponent);
		
		assertThat(contextSensor.getContextComponent()).isEqualTo(contextComponent);
	}
	
	private Core createCore(String type) {
		Core core = mock(Core.class);
		
		Map<String, Value> values = new HashMap<>();
		values.put("Name", ValueType.STRING.create("Name"));
		values.put("Version", ValueType.STRING.create("Version"));
		
		when(core.getValues()).thenReturn(values);
		when(core.getTypeName()).thenReturn(type);
		when(core.getPermanentStore()).thenReturn(new EntityStore());
		when(core.getChildrenStore()).thenReturn(new ChildrenStore());
		
		return core;
	}
}
