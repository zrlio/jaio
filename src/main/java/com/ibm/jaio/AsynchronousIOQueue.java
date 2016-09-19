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
import java.util.concurrent.ConcurrentHashMap;

public class AsynchronousIOQueue {
	public final int nrEvents;

	private final long context;

	final ConcurrentHashMap<Long, Object> inFlight;

	public AsynchronousIOQueue(int nrEvents) throws IOException {
		this.nrEvents = nrEvents;
		this.context = NativeDispatcher.io_setup(nrEvents);
		this.inFlight = new ConcurrentHashMap<Long, Object>(nrEvents);
	}

	public void submit(AsynchronousIOOperationArray ops) throws IOException {
		submit(ops, ops.length);
	}

	public void submit(AsynchronousIOOperationArray ops, int length) throws IOException {
		for (int i = 0; i < length; i++) {
			AsynchronousIOOperation op = ops.get(i);
			Object attachment = op.getAttachment();
			Object prev = inFlight.put((long)attachment.hashCode(), attachment);
			if (prev != null) {
				throw new IOException("already in flight: " + attachment.hashCode());
			}
		}
		NativeDispatcher.io_submit(context, length, ops.rawDataAddress);
	}

	void cancel(AsynchronousIOOperation op) {
		//TODO
	}

	public int poll(AsynchronousIOResultArray results) throws IllegalArgumentException, IOException {
		return poll(results, 0);
	}

	public int poll(AsynchronousIOResultArray results, long milliSeconds) throws IllegalArgumentException, IOException {
		int n = NativeDispatcher.io_getevents(context, 1, results.length, results.rawDataAddress, milliSeconds);
		for (int i = 0; i < n; i++) {
			Nativeio_event event = results.events[i];
			Long key = event.getdata();
			Object attachment = inFlight.remove(key);
			results.set(i, new AsynchronousIOResult(attachment, (int)event.getres()));
		}
		return n;
	}

	public void close() throws IOException {
		NativeDispatcher.io_destroy(context);
	}
}
