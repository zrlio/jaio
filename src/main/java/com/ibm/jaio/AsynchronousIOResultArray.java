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


public class AsynchronousIOResultArray<T> extends NativeArray<AsynchronousIOResult<T>> {
	final Nativeio_event[] events;

	public AsynchronousIOResultArray(int length) {
		super(length, Nativeio_event.size, new AsynchronousIOResult[length]);
		this.events = new Nativeio_event[length];
		for (int i = 0; i < events.length; i++) {
			events[i] = new Nativeio_event(this.rawData, i * Nativeio_event.size);
		}
	}

	@Override
	void set(int index, AsynchronousIOResult<T> result) {
		data[index] = result;
	}

}
