package org.eclipse.mdm.api.base.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.mdm.api.base.adapter.ChildrenStore;
import org.eclipse.mdm.api.base.adapter.Core;
import org.eclipse.mdm.api.base.adapter.EntityStore;

public class ContextComponentTest {

	@org.junit.Test
	public void testGetContextRoot() {
		BaseEntityFactory factory = spy(BaseEntityFactory.class);
		Core rootCore = createCore("UNITUNDERTEST");
	    when(factory.createCore(ContextRoot.class, ContextType.UNITUNDERTEST)).thenReturn(rootCore);
	    Core componentCore = createCore("");
	    when(factory.createCore("Engine", ContextComponent.class)).thenReturn(componentCore);
	    
		ContextRoot contextRoot = factory.createContextRoot("Car", ContextType.UNITUNDERTEST);
		ContextComponent contextComponent = factory.createContextComponent("Engine", contextRoot);
		
		assertThat(contextComponent.getContextRoot()).isEqualTo(contextRoot);
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
