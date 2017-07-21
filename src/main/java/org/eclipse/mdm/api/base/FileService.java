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

/**
 * Provides stream and download operations to access externally linked files.
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public interface FileService {

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Sequential download of given {@link FileLink}s into given target {@code
	 * Path}. Remote paths linked multiple times are downloaded only once.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param target
	 *            Must be a directory.
	 * @param fileLinks
	 *            Collection of {@code FileLink}s to download.
	 * @throws IOException
	 *             Thrown if unable to download files.
	 */
	default void downloadSequential(Entity entity, Path target, Collection<FileLink> fileLinks) throws IOException {
		downloadSequential(entity, target, fileLinks, null);
	}

	/**
	 * Sequential download of given {@link FileLink}s into given target {@code
	 * Path}. Remote paths linked multiple times are downloaded only once. The
	 * download progress may be traced with a progress listener.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param target
	 *            Must be a directory.
	 * @param fileLinks
	 *            Collection of {@code FileLink}s to download.
	 * @param progressListener
	 *            The progress listener.
	 * @throws IOException
	 *             Thrown if unable to download files.
	 */
	void downloadSequential(Entity entity, Path target, Collection<FileLink> fileLinks,
			ProgressListener progressListener) throws IOException;

	/**
	 * Parallel download of given {@link FileLink}s into given target {@code
	 * Path}. Remote paths linked multiple times are downloaded only once.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param target
	 *            Must be a directory.
	 * @param fileLinks
	 *            Collection of {@code FileLink}s to download.
	 * @throws IOException
	 *             Thrown if unable to download files.
	 */
	default void downloadParallel(Entity entity, Path target, Collection<FileLink> fileLinks) throws IOException {
		downloadParallel(entity, target, fileLinks, null);
	}

	/**
	 * Parallel download of given {@link FileLink}s into given target {@code
	 * Path}. Remote paths linked multiple times are downloaded only once. The
	 * download progress may be traced with a progress listener.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param target
	 *            Must be a directory.
	 * @param fileLinks
	 *            Collection of {@code FileLink}s to download.
	 * @param progressListener
	 *            The progress listener.
	 * @throws IOException
	 *             Thrown if unable to download files.
	 */
	void downloadParallel(Entity entity, Path target, Collection<FileLink> fileLinks, ProgressListener progressListener)
			throws IOException;

	/**
	 * Downloads given {@link FileLink} into given target {@code Path}.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param target
	 *            Must be a directory.
	 * @param fileLink
	 *            The {@code FileLink} to download.
	 * @throws IOException
	 *             Thrown if unable to download file.
	 */
	default void download(Entity entity, Path target, FileLink fileLink) throws IOException {
		download(entity, target, fileLink, null);
	}

	/**
	 * Downloads given {@link FileLink} into given target {@code Path}. The
	 * download progress may be traced with a progress listener.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param target
	 *            Must be a directory.
	 * @param fileLink
	 *            The {@code FileLink} to download.
	 * @param progressListener
	 *            The progress listener.
	 * @throws IOException
	 *             Thrown if unable to download file.
	 */
	void download(Entity entity, Path target, FileLink fileLink, ProgressListener progressListener) throws IOException;

	/**
	 * Opens an {@code InputStream} for given {@link FileLink}. The returned
	 * stream should be consumed with a try-with-resources statement to ensure
	 * the stream is closed properly, e.g:
	 *
	 * <pre>
	 * try (InputStream is = openStream(entity, fileLink)) {
	 * 	// do something useful
	 * }
	 * </pre>
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param fileLink
	 *            The {@code FileLink} to be provided as a stream.
	 * @return A consumable {@code InputStream} is returned.
	 * @throws IOException
	 *             Thrown if unable to provide as stream.
	 */
	default InputStream openStream(Entity entity, FileLink fileLink) throws IOException {
		return openStream(entity, fileLink, null);
	}

	/**
	 * Opens an {@code InputStream} for given {@link FileLink}. The progress of
	 * the stream consumption may be traced with a progress listener. The
	 * returned stream should be consumed with a try-with-resources statement to
	 * ensure the stream is closed properly, e.g:
	 *
	 * <pre>
	 * try (InputStream is = openStream(entity, fileLink)) {
	 * 	// do something useful
	 * }
	 * </pre>
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param fileLink
	 *            The {@code FileLink} to be provided as a stream.
	 * @param progressListener
	 *            The progress listener.
	 * @return A consumable {@code InputStream} is returned.
	 * @throws IOException
	 *             Thrown if unable to provide as stream.
	 */
	InputStream openStream(Entity entity, FileLink fileLink, ProgressListener progressListener) throws IOException;

	/**
	 * Loads the file size for given {@link FileLink}. The file size is stored
	 * in the given {@code FileLink}.
	 *
	 * @param entity
	 *            Used for security checks.
	 * @param fileLink
	 *            The {@code FileLink} whose file size is requested.
	 * @throws IOException
	 *             Thrown if unable to load file size.
	 */
	void loadSize(Entity entity, FileLink fileLink) throws IOException;

	// ======================================================================
	// Inner classes
	// ======================================================================

	/**
	 * A {@link FileService} consumer may implement this interface to get
	 * notified about stream or download progress.
	 */
	@FunctionalInterface
	interface ProgressListener {

		/**
		 * Progress notification.
		 *
		 * @param bytes
		 *            Number of transferred bytes since last notification.
		 * @param percent
		 *            The overall percentage, where percent p &isin; [0.0, 1.0].
		 */
		void progress(int bytes, float percent);

	}

}
