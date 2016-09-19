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

/* This file is generated - DO NOT CHANGE */
package com.ibm.jaio;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
class Nativeio_iocb_vector {
	final ByteBuffer buf;
	static final int size = 24;
	final int offset;
	public Nativeio_iocb_vector() {
		offset = 0;
		buf = ByteBuffer.allocateDirect(size);
		buf.order(ByteOrder.nativeOrder());
	}
	public Nativeio_iocb_vector(ByteBuffer buf, int offset) {
		this.buf = buf;
		this.offset = offset;
	}
	public void setvec(long vec) {
		this.buf.putLong(0 + this.offset, vec);
	}
	public long getvec() {
		return buf.getLong(0 + this.offset);
	}

	public void setnr(int nr) {
		this.buf.putInt(8 + this.offset, nr);
	}
	public int getnr() {
		return buf.getInt(8 + this.offset);
	}

	public void setoffset(long offset) {
		this.buf.putLong(16 + this.offset, offset);
	}
	public long getoffset() {
		return buf.getLong(16 + this.offset);
	}
}