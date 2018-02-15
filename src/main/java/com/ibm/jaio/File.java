/*
 * Jaio: Java API for libaio
 *
 * Author:
 * Jonas Pfefferle <jpf@zurich.ibm.com>
 *
 * Copyright (C) 2016, IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ibm.jaio;

import java.io.IOException;
import java.nio.file.Path;

public class File {
	private final int fd;
	private final Path path;

	static class LinuxFlags {
		final static int O_RDONLY = NativeDispatcher.o_rdonly();
		final static int O_WRONLY = NativeDispatcher.o_wronly();
		final static int O_RDWR = NativeDispatcher.o_rdwr();
		final static int O_DIRECT = NativeDispatcher.o_direct();
		final static int O_DSYNC = NativeDispatcher.o_dsync();
		final static int O_SYNC = NativeDispatcher.o_sync();

		static int toFlags(OpenOption... options) {
			boolean read = false;
			boolean write = false;
			int flag = 0;
			for (OpenOption option : options) {
				switch (option) {
					case READ:
						read = true;
						break;
					case WRITE:
						write = true;
						break;
					case SYNC:
						flag |= O_SYNC;
						break;
					case DSYNC:
						flag |= O_DSYNC;
						break;
					case DIRECT:
						flag |= O_DIRECT;
						break;
				}
			}
			if (read && write) {
				flag |= O_RDWR;
			} else if (read) {
				flag |= O_RDONLY;
			} else if (write) {
				flag |= O_WRONLY;
			}
			return flag;
		}
	}

	public File(Path path, OpenOption... options) throws IOException {
		fd  = NativeDispatcher.open(path.toString(), LinuxFlags.toFlags(options));
		this.path = path;
	}

	int getFd() {
		return fd;
	}

	public void close() throws IOException {
		NativeDispatcher.close(fd);
	}
}
