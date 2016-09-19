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
class Nativeiocb {
	final ByteBuffer buf;
	static final int size = 64;
	final int offset;
	public Nativeiocb() {
		offset = 0;
		buf = ByteBuffer.allocateDirect(size);
		buf.order(ByteOrder.nativeOrder());
	}
	public Nativeiocb(ByteBuffer buf, int offset) {
		this.buf = buf;
		this.offset = offset;
	}
	public void setdata(long data) {
		this.buf.putLong(0 + this.offset, data);
	}
	public long getdata() {
		return buf.getLong(0 + this.offset);
	}

	public void setkey(int key) {
		this.buf.putInt(8 + this.offset, key);
	}
	public int getkey() {
		return buf.getInt(8 + this.offset);
	}

	public void setaio_lio_opcode(short aio_lio_opcode) {
		this.buf.putShort(16 + this.offset, aio_lio_opcode);
	}
	public short getaio_lio_opcode() {
		return buf.getShort(16 + this.offset);
	}

	public void setaio_reqprio(short aio_reqprio) {
		this.buf.putShort(18 + this.offset, aio_reqprio);
	}
	public short getaio_reqprio() {
		return buf.getShort(18 + this.offset);
	}

	public void setaio_fildes(int aio_fildes) {
		this.buf.putInt(20 + this.offset, aio_fildes);
	}
	public int getaio_fildes() {
		return buf.getInt(20 + this.offset);
	}

	public void setu_c(Nativeio_iocb_common u_c) {
		this.buf.clear(); this.buf.position(24);
		this.buf.put(u_c.buf.array(), u_c.offset, u_c.size);
		this.buf.clear();
	}
	private Nativeio_iocb_common u_c;
	public Nativeio_iocb_common getu_c() {
		if (u_c == null) {
			u_c = new Nativeio_iocb_common(buf, 24 + this.offset);
		}
		return u_c;
	}

	public void setu_v(Nativeio_iocb_vector u_v) {
		this.buf.clear(); this.buf.position(24);
		this.buf.put(u_v.buf.array(), u_v.offset, u_v.size);
		this.buf.clear();
	}
	private Nativeio_iocb_vector u_v;
	public Nativeio_iocb_vector getu_v() {
		if (u_v == null) {
			u_v = new Nativeio_iocb_vector(buf, 24 + this.offset);
		}
		return u_v;
	}

	public void setu_poll(Nativeio_iocb_poll u_poll) {
		this.buf.clear(); this.buf.position(24);
		this.buf.put(u_poll.buf.array(), u_poll.offset, u_poll.size);
		this.buf.clear();
	}
	private Nativeio_iocb_poll u_poll;
	public Nativeio_iocb_poll getu_poll() {
		if (u_poll == null) {
			u_poll = new Nativeio_iocb_poll(buf, 24 + this.offset);
		}
		return u_poll;
	}

	public void setu_saddr(Nativeio_iocb_sockaddr u_saddr) {
		this.buf.clear(); this.buf.position(24);
		this.buf.put(u_saddr.buf.array(), u_saddr.offset, u_saddr.size);
		this.buf.clear();
	}
	private Nativeio_iocb_sockaddr u_saddr;
	public Nativeio_iocb_sockaddr getu_saddr() {
		if (u_saddr == null) {
			u_saddr = new Nativeio_iocb_sockaddr(buf, 24 + this.offset);
		}
		return u_saddr;
	}
}