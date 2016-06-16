/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Collection;

import org.eclipse.mdm.api.base.model.Entity;
import org.eclipse.mdm.api.base.model.FileLink;

public interface FileService {

	default void downloadSequential(Entity entity, Path target, Collection<FileLink> fileLinks) throws IOException {
		downloadSequential(entity, target, fileLinks, null);
	}

	void downloadSequential(Entity entity, Path target, Collection<FileLink> fileLinks, ProgressListener progressListener) throws IOException;

	default void downloadParallel(Entity entity, Path target, Collection<FileLink> fileLinks) throws IOException {
		downloadParallel(entity, target, fileLinks, null);
	}

	void downloadParallel(Entity entity, Path target, Collection<FileLink> fileLinks, ProgressListener progressListener ) throws IOException;

	default void download(Entity entity, Path target, FileLink fileLink) throws IOException {
		download(entity, target, fileLink, null);
	}

	void download(Entity entity, Path target, FileLink fileLink, ProgressListener progressListener) throws IOException;

	// TODO java doc: make sure this stream is closed when finished!
	default InputStream createInputStream(Entity entity, FileLink fileLink) throws IOException {
		return createInputStream(entity, fileLink, null);
	}

	// TODO java doc: make sure this stream is closed when finished!
	InputStream createInputStream(Entity entity, FileLink fileLink, ProgressListener progressListener) throws IOException;

	void loadSize(Entity entity, FileLink fileLink) throws IOException;

	@FunctionalInterface
	interface ProgressListener {

		// TODO: percent in [0.0, 1.0]
		void progress(int bytes, float percent);

	}

}
