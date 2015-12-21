/*
 * Copyright (c) 2015 OpenMDM(r) Working Group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.mdm.api.base.model;

import java.io.File;

public final class FileLink {

	/**
	 * TODO Handling of files is currently NOT implemented!
	 */

	private String name;
	private MimeType mimeType;
	private String description;
	private String path;
	private FileState state;
	private long size;
	private File file;

}
