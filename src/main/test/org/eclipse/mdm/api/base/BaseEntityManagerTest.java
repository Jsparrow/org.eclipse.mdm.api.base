/*
 * Copyright (c) 2017 Peak Solution GmbH and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.mdm.api.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.mdm.api.base.model.BaseEntityFactory;
import org.eclipse.mdm.api.base.model.TestStep;
import org.eclipse.mdm.api.base.query.DataAccessException;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class BaseEntityManagerTest {

	@Test
	public void loadShouldReturnEntity() throws DataAccessException {
		
		@SuppressWarnings("unchecked")
		BaseEntityManager<BaseEntityFactory> entityManager = mock(BaseEntityManager.class);
		TestStep mockedTestStep = mock(TestStep.class);
		
		when(entityManager.load(any(), anyString())).thenCallRealMethod();
		when(entityManager.load(TestStep.class, Arrays.asList("id1"))).thenReturn(Arrays.asList(mockedTestStep));
		
		assertThat(entityManager.load(TestStep.class, "id1"))
			.isEqualTo(mockedTestStep);
	}
	
	@Test
	public void loadNotExistingIdShouldThrowDataAccessException() throws DataAccessException {
		
		@SuppressWarnings("unchecked")
		BaseEntityManager<BaseEntityFactory> entityManager = mock(BaseEntityManager.class);
		
		when(entityManager.load(any(), anyString())).thenCallRealMethod();
		when(entityManager.load(eq(TestStep.class), anyCollection())).thenReturn(Collections.<TestStep>emptyList());
		
		assertThatThrownBy(() -> entityManager.load(TestStep.class, "xyz"))
			.isInstanceOf(DataAccessException.class)
			.hasMessageContaining("Failed to load entity by instance ID.");
	}
	
	@Test
	public void loadNotUniqueIdShouldThrowDataAccessException() throws DataAccessException {
		
		@SuppressWarnings("unchecked")
		BaseEntityManager<BaseEntityFactory> entityManager = mock(BaseEntityManager.class);
		TestStep mockedTestStep1 = mock(TestStep.class);
		TestStep mockedTestStep2 = mock(TestStep.class);

		when(entityManager.load(any(), anyString())).thenCallRealMethod();
		when(entityManager.load(TestStep.class, Arrays.asList("id1")))
			.thenReturn(Arrays.asList(mockedTestStep1, mockedTestStep2));
		
		assertThatThrownBy(() -> entityManager.load(TestStep.class, "id1"))
			.isInstanceOf(DataAccessException.class)
			.hasMessageContaining("Failed to load entity by instance ID.");
	}
}
