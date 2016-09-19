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
class Nativeio_event {
	final ByteBuffer buf;
	static final int size = 32;
	final int offset;
	public Nativeio_event() {
		offset = 0;
		buf = ByteBuffer.allocateDirect(size);
		buf.order(ByteOrder.nativeOrder());
	}
	public Nativeio_event(ByteBuffer buf, int offset) {
		this.buf = buf;
		this.offset = offset;
	}
	public void setdata(long data) {
		this.buf.putLong(0 + this.offset, data);
	}
	public long getdata() {
		return buf.getLong(0 + this.offset);
	}

	public void setobj(long obj) {
		this.buf.putLong(8 + this.offset, obj);
	}
	public long getobj() {
		return buf.getLong(8 + this.offset);
	}

	public void setres(long res) {
		this.buf.putLong(16 + this.offset, res);
	}
	public long getres() {
		return buf.getLong(16 + this.offset);
	}

	public void setres2(long res2) {
		this.buf.putLong(24 + this.offset, res2);
	}
	public long getres2() {
		return buf.getLong(24 + this.offset);
	}
}