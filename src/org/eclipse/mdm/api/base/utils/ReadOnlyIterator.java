/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.utils;

import java.util.Iterator;

public final class ReadOnlyIterator<E> implements Iterator<E> {
	
	private final Iterator<E> internal;
	
	public ReadOnlyIterator(Iterator<E> internal) {
		this.internal = internal;
	}

	@Override
	public boolean hasNext() {
		return internal.hasNext();
	}

	@Override
	public E next() {
		return internal.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException("Iterator provides read only access.");
	}

}
