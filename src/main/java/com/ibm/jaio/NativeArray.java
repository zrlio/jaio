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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class NativeArray<T> {

	public final int length;

	protected final T[] data;
	protected final ByteBuffer rawData;

	final long rawDataAddress;

	NativeArray(int length, int nativeElementSize, T[] data) {
		if (length < 0 || data.length != length) {
			throw new IllegalArgumentException("negative length");
		}
		this.length = length;
		this.data = data;
		this.rawData = ByteBuffer.allocateDirect(nativeElementSize * length);
		this.rawData.order(ByteOrder.nativeOrder());
		this.rawDataAddress = MemoryUtils.getAddress(this.rawData);
	}


	public T get(int index) {
		return data[index];
	}

	abstract void set(int index, T t);
}
