/********************************************************************************
 * Copyright (c) 2015-2018 Contributors to the Eclipse Foundation
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 ********************************************************************************/


package org.eclipse.mdm.api.base.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility class to manage links to externally stored files (local/remote).
 *
 * @since 1.0.0
 * @author Viktor Stoehr, Gigatronik Ingolstadt GmbH
 */
public final class FileLink {

	// ======================================================================
	// Instance variables
	// ======================================================================

	private final State state;

	private String remotePath;
	private MimeType mimeType;
	private String description;

	private Path localPath;

	private long size = -1;

	// ======================================================================
	// Constructors
	// ======================================================================

	/**
	 * Constructor.
	 *
	 * @param fileLink
	 *            Will be copied.
	 */
	FileLink(FileLink fileLink) {
		remotePath = fileLink.remotePath;
		mimeType = fileLink.mimeType;
		description = fileLink.description;
		localPath = fileLink.localPath;
		size = fileLink.size;
		state = fileLink.state;
	}

	/**
	 * Constructor.
	 *
	 * @param remotePath
	 *            The remote path.
	 * @param mimeType
	 *            The MIME type of the linked file.
	 */
	private FileLink(String remotePath, MimeType mimeType) {
		this.remotePath = remotePath;
		this.mimeType = mimeType;

		state = State.REMOTE;
	}

	/**
	 * Constructor.
	 *
	 * @param localPath
	 *            The local {@link Path}.
	 * @throws IOException
	 *             Thrown in case of errors.
	 */
	private FileLink(Path localPath) throws IOException {
		this.localPath = localPath;
		String type = Files.probeContentType(localPath);
		mimeType = new MimeType(type == null ? "application/octet-stream" : type);
		size = Files.size(localPath);
		Path fileNamePath = localPath.getFileName();
		if (fileNamePath != null) {
			description = fileNamePath.toString();
		}

		state = State.LOCAL;
	}

	// ======================================================================
	// Public methods
	// ======================================================================

	/**
	 * Creates a new {@link FileLink} instance which remotely available.
	 *
	 * @param remotePath
	 *            The remote path.
	 * @param mimeType
	 *            The MIME type.
	 * @param description
	 *            Description of the file.
	 * @return The created {@code FileLink} instance is returned.
	 */
	public static FileLink newRemote(String remotePath, MimeType mimeType, String description) {
		FileLink fileLink = new FileLink(remotePath, mimeType);
		fileLink.setDescription(description);
		return fileLink;
	}

	/**
	 * Creates a new {@link FileLink} instance which locally available.
	 *
	 * @param localPath
	 *            The local {@link Path} to the file.
	 * @return The created {@code FileLink} instance is returned.
	 * @throws IOException
	 *             Thrown if unable to access file with given {@code
	 * 		Path}.
	 */
	public static FileLink newLocal(Path localPath) throws IOException {
		if (Files.isDirectory(localPath)) {
			throw new IllegalArgumentException("Local path is a directory.");
		} else if (!Files.exists(localPath)) {
			throw new IllegalArgumentException("Local path does not exist.");
		} else if (!Files.isReadable(localPath)) {
			throw new IllegalArgumentException("Local path is not readable.");
		}

		return new FileLink(localPath);
	}

	/**
	 * Returns the name of the linked file.
	 *
	 * @return Name of the file is returned.
	 * @throws IllegalStateException
	 *             Thrown if unable to retrieve the file name.
	 */
	public String getFileName() {
		Path fileNamePath = null;
		if (isLocal()) {
			fileNamePath = getLocalPath().getFileName();
		} else if (isRemote()) {
			try {
				fileNamePath = Paths.get(URLDecoder.decode(remotePath, StandardCharsets.UTF_8.name())).getFileName();
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException("Unable to decode remote path due to: " + e.getMessage(), e);
			}
		}

		if (fileNamePath == null) {
			throw new IllegalStateException("File name is unknown.");
		}

		return fileNamePath.toString();
	}

	/**
	 * Returns the MIME type of the linked file.
	 *
	 * @return The MIME type is returned.
	 */
	public MimeType getMimeType() {
		return mimeType;
	}

	/**
	 * Checks whether a local {@link Path} is available for the linked file.
	 *
	 * @return Returns {@code true} if a local {@code Path} is available.
	 */
	public boolean isLocal() {
		return localPath != null;
	}

	/**
	 * Returns the local {@link Path} to the linked file. Calling this method is
	 * only allowed if calling {@link #isLocal()} returns {@code
	 * true}.
	 *
	 * @return The local {@code Path} to the linked file is returned.
	 */
	public Path getLocalPath() {
		if (isLocal()) {
			return localPath;
		}

		throw new IllegalStateException("Local path is not available.");
	}

	/**
	 * This method is called by API providers to set the local {@link Path} once
	 * the remote file was downloaded.
	 *
	 * @param localPath
	 *            The local {@code Path} of the downloaded file.
	 * @throws IllegalStateException
	 *             Thrown if this file link is 'LOCAL'.
	 */
	public void setLocalPath(Path localPath) {
		if (State.LOCAL == state) {
			throw new IllegalStateException("It is not allowed to replace an existing local path.");
		}

		this.localPath = localPath;
	}

	/**
	 * Checks whether a remote path is available for for linked file.
	 *
	 * @return Returns {@code true} if a remote path is available.
	 */
	public boolean isRemote() {
		return remotePath != null && !remotePath.isEmpty();
	}

	/**
	 * Returns the remote path to the linked file. Calling this method is only
	 * allowed if calling {@link #isRemote()} returns {@code true}.
	 *
	 * @return The remote path to the linked file is returned.
	 */
	public String getRemotePath() {
		if (isRemote()) {
			return remotePath;
		}

		throw new IllegalStateException("Remote path is not available.");
	}

	/**
	 * This method is called by API providers to set the remote path once the
	 * local file has been uploaded.
	 *
	 * @param remotePath
	 *            The remote path of the uploaded file.
	 * @throws IllegalStateException
	 *             Thrown if this file link is 'REMOTE'.
	 */
	public void setRemotePath(String remotePath) {
		if (State.REMOTE == state) {
			throw new IllegalStateException("It is not allowed to replace an existing remote path.");
		}
		this.remotePath = remotePath;
	}

	/**
	 * Returns the description of the linked file.
	 *
	 * @return The description is returned.
	 */
	public String getDescription() {
		return description == null ? "" : description;
	}

	/**
	 * Sets a new description for the linked file.
	 *
	 * @param description
	 *            The new description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the size of the linked file or {@code -1} if unknown.
	 *
	 * @return The file size in bytes is returned.
	 */
	public long getSize() {
		return size;
	}

	/**
	 * Returns the formatted file size of the linked file.
	 *
	 * @param format
	 *            Used to format the size.
	 * @return The formatted file size is returned.
	 */
	public String getSize(Format format) {
		return format.getSize(size);
	}

	/**
	 * This method is called by API providers to set the file size for the
	 * linked file.
	 *
	 * @param size
	 *            The size of the file in bytes.
	 */
	public void setFileSize(long size) {
		this.size = size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (State.LOCAL == state) {
			if (!isLocal()) {
				return "".hashCode();
			}
			return getLocalPath().hashCode();
		}
		if (!isRemote()) {
			return "".hashCode();
		}
		return getRemotePath().hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object object) {
		if (object instanceof FileLink) {
			FileLink other = (FileLink) object;
			if (state == other.state) {
				if (State.LOCAL == state) {
					if (!isLocal()) {
						return !other.isLocal();
					}
					return getLocalPath().equals(other.getLocalPath());
				}
				if (!isRemote()) {
					return !other.isRemote();
				}
				return getRemotePath().equals(other.getRemotePath());
			}
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("FileLink(Description = ");

		if (!getDescription().isEmpty()) {
			sb.append(getDescription());
		}

		if (isLocal()) {
			sb.append(", LocalPath = ").append(getLocalPath().toAbsolutePath());
		}

		if (isRemote()) {
			sb.append(", RemotePath = ").append(getRemotePath());
		}

		sb.append(", Size = ");
		if (getSize() > 0) {
			sb.append(getSize(Format.DECIMAL)).append(" / ").append(getSize(Format.BINARY));
		} else {
			sb.append("UNKNOWN");
		}

		return sb.append(')').toString();
	}

	// ======================================================================
	// Package methods
	// ======================================================================

	/**
	 * Checks whether given {@link FileLink} may be treated as equal. This is
	 * the case if either their local {@link Path}s or remote paths are equal.
	 *
	 * @param o1
	 *            The first {@code FileLink}.
	 * @param o2
	 *            The second {@code FileLink}.
	 * @return Returns {@code true} if either their local {@code Path}s or
	 *         remote paths are equal.
	 */
	static boolean areEqual(FileLink o1, FileLink o2) {
		return isLocalPathEqual(o1, o2) || isRemotePathEqual(o1, o2);
	}

	// ======================================================================
	// Private methods
	// ======================================================================

	/**
	 * Checks whether both {@link FileLink}s return {@code true} when
	 * {@link #isLocal()} is called and their {@link Path}s are equal.
	 *
	 * @param o1
	 *            The first {@code FileLink}.
	 * @param o2
	 *            The second {@code FileLink}.
	 * @return Returns {@code true} if both {@code FileLink}s have a local
	 *         {@code Path} which are equal.
	 */
	private static boolean isLocalPathEqual(FileLink o1, FileLink o2) {
		return o1.isLocal() && o2.isLocal() && o1.getLocalPath().equals(o2.getLocalPath());
	}

	/**
	 * Checks whether both {@link FileLink}s return {@code true} when
	 * {@link #isRemote()} is called and their remote paths are equal.
	 *
	 * @param o1
	 *            The first {@code FileLink}.
	 * @param o2
	 *            The second {@code FileLink}.
	 * @return Returns {@code true} if both {@code FileLink}s have a remote path
	 *         which are equal.
	 */
	private static boolean isRemotePathEqual(FileLink o1, FileLink o2) {
		return o1.isRemote() && o2.isRemote() && o1.getRemotePath().equals(o2.getRemotePath());
	}

	// ======================================================================
	// Inner classes
	// ======================================================================

	/**
	 * Used to format a number of bytes into a human readable size.
	 */
	public enum Format {

		// ======================================================================
		// Enum constants
		// ======================================================================

		/**
		 * Counts 1000 bits as 1 byte, so formats 110592 to '110.6 kB'.
		 */
		DECIMAL(1000, "kMGTPE"),

		/**
		 * Counts 1024 bits as 1 byte, so formats 110592 to '108.0 KiB'.
		 */
		BINARY(1024, "KMGTPE");

		// ======================================================================
		// Inner classes
		// ======================================================================

		private final String prefixChars;
		private final int unit;

		// ======================================================================
		// Constructors
		// ======================================================================

		/**
		 * Constructor.
		 *
		 * @param unit
		 *            The unit.
		 * @param prefixChars
		 *            The prefix characters.
		 */
		private Format(int unit, String prefixChars) {
			this.prefixChars = prefixChars;
			this.unit = unit;
		}

		// ======================================================================
		// Private methods
		// ======================================================================

		/**
		 * Formats given file size in bytes into a human readable one.
		 *
		 * @param size
		 *            The number of bytes.
		 * @return Formatted file size is returned.
		 */
		private String getSize(long size) {
			if (size < 0) {
				return "UNKNOWN";
			} else if (size < unit) {
				return size + " B";
			}

			int exponent = (int) (Math.log(size) / Math.log(unit));
			String prefixChar = prefixChars.charAt(exponent - 1) + (DECIMAL == this ? "" : "i");
			return String.format("%.1f %sB", size / Math.pow(unit, exponent), prefixChar);
		}
	}

	/**
	 * Used to preserve the initial state of a {@link FileLink}.
	 */
	private enum State {

		/**
		 * {@link FileLink} was initially only remotely available.
		 */
		REMOTE,

		/**
		 * {@link FileLink} was initially only locally available.
		 */
		LOCAL

	}

}
