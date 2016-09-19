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

class NativeDispatcher {

	static {
		System.loadLibrary("jaio");
	}

	static native long io_setup(int nrEvents) throws IOException;

	static native int io_submit(long context, long nr, long iocbpp) throws IOException;

	static native int io_getevents(long context, long min, long nr, long events, long milliSeconds) throws IOException;

	static native void io_destroy(long context) throws IOException;

	static native int open(String path, int flags) throws IOException;

	static native void close(int fd) throws IOException;
}
