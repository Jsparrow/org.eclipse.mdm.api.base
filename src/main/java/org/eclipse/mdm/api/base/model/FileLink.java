/*
 * Copyright (c) 2016 Gigatronik Ingolstadt GmbH
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileLink {

	private enum State {
		REMOTE,
		LOCAL
	}

	private final State state;

	private String remotePath;
	private MimeType mimeType;
	private String description;

	private Path localPath;

	private long size = -1;

	private FileLink(String remotePath, MimeType mimeType) {
		this.remotePath = remotePath;
		this.mimeType = mimeType;

		state = State.REMOTE;
	}

	private FileLink(Path localPath) throws IOException {
		this.localPath = localPath;
		String type = Files.probeContentType(localPath);
		mimeType =  new MimeType(type == null ? "application/octet-stream" : type);
		size = Files.size(localPath);
		description = localPath.getFileName().toString();

		state = State.LOCAL;
	}

	FileLink(FileLink fileLink) {
		remotePath = fileLink.remotePath;
		mimeType = fileLink.mimeType;
		description = fileLink.description;
		localPath = fileLink.localPath;
		size = fileLink.size;
		state = fileLink.state;
	}

	public static FileLink newRemote(String remotePath, MimeType mimeType, String description) {
		FileLink fileLink = new FileLink(remotePath, mimeType);
		fileLink.setDescription(description);
		return fileLink;
	}

	public static FileLink newLocal(Path localPath) throws IOException {
		if(Files.isDirectory(localPath)) {
			throw new IllegalArgumentException("Local path is a directory.");
		} else if(!Files.exists(localPath)) {
			throw new IllegalArgumentException("Local path does not exist.");
		} else if(!Files.isReadable(localPath)) {
			throw new IllegalArgumentException("Local path is not readable.");
		}

		return new FileLink(localPath);
	}

	public String getFileName() {
		if(isLocal()) {
			return getLocalPath().getFileName().toString();
		} else if(isRemote()) {
			// quick and dirty (no parsing...)
			// TODO ...
			try {
				return Paths.get(URLDecoder.decode(remotePath, StandardCharsets.UTF_8.name())).getFileName().toString();
			} catch(UnsupportedEncodingException e) {
				// ignore
			}
		}

		throw new IllegalStateException(); // TODO
	}

	public MimeType getMimeType() {
		return mimeType;
	}

	public Path getLocalPath() {
		return localPath;
	}

	public void setLocalPath(Path localPath) {
		if(State.LOCAL == state) {
			throw new IllegalStateException("It is not allowed to replace an existing local path.");
		}
		this.localPath = localPath;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		if(State.REMOTE == state) {
			throw new IllegalStateException("It is not allowed to replace an existing remote path.");
		}
		this.remotePath = remotePath;
	}

	public String getDescription() {
		return description == null ? "" : description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isLocal() {
		return getLocalPath() != null;
	}

	public boolean isRemote() {
		return getRemotePath() != null && !getRemotePath().isEmpty();
	}

	public long getSize() {
		return size;
	}

	public String getSize(Format format) {
		return format.getSize(size);
	}

	public void setFileSize(long size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		if(State.LOCAL == state) {
			return getLocalPath().hashCode();
		}

		return getRemotePath().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if(object instanceof FileLink) {
			FileLink other = (FileLink) object;
			if(state == other.state) {
				if(State.LOCAL == state) {
					return getLocalPath().equals(other.getLocalPath());
				}

				return getRemotePath().equals(other.getRemotePath());
			}
		}

		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("FileLink(Description = ");

		if(getDescription() != null) {
			sb.append(getDescription());
		}

		if(isLocal()) {
			sb.append(", LocalPath = ").append(getLocalPath().toAbsolutePath());
		}

		if(isRemote()) {
			sb.append(", RemotePath = ").append(getRemotePath());
		}

		sb.append(", Size = ");
		if(getSize() > 0) {
			sb.append(getSize(Format.SI)).append(" / ").append(getSize(Format.BINARY));
		} else {
			sb.append("UNKNOWN");
		}

		return sb.append(')').toString();
	}

	static boolean areEqual(FileLink o1, FileLink o2) {
		return isLocalPathEqual(o1, o2) || isRemotePathEqual(o1, o2);
	}

	private static boolean isLocalPathEqual(FileLink o1, FileLink o2) {
		return o1.isLocal() && o2.isLocal() && o1.getLocalPath().equals(o2.getLocalPath());
	}

	private static boolean isRemotePathEqual(FileLink o1, FileLink o2) {
		return o1.isRemote() && o2.isRemote() && o1.getRemotePath().equals(o2.getRemotePath());
	}

	public enum Format {
		SI(1000, "kMGTPE"),
		BINARY(1024, "KMGTPE");

		private final String prefixChars;
		private final int unit;

		private Format(int unit, String prefixChars) {
			this.prefixChars = prefixChars;
			this.unit = unit;
		}

		public String getSize(long size) {
			if(size < 0) {
				return "UNKNOWN";
			} else if (size < unit) {
				return size + " B";
			}

			int exponent = (int) (Math.log(size) / Math.log(unit));
			String prefixChar = prefixChars.charAt(exponent-1) + (SI == this ? "" : "i");
			return String.format("%.1f %sB", size / Math.pow(unit, exponent), prefixChar);
		}
	}

}
