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


public abstract class AsynchronousIOOperation<T> {
	protected final Nativeiocb iocb;
	final long iocbAddress;
	private T attachment;

	public static int size = Nativeiocb.size;

	AsynchronousIOOperation() {
		this.iocb = new Nativeiocb();
		this.iocbAddress = MemoryUtils.getAddress(iocb.buf);
		this.iocb.setaio_lio_opcode(getOpcode());
	}

	T getAttachment() {
		return attachment;
	}

	void setAttachment(T attachment) {
		this.iocb.setdata(attachment.hashCode());
		this.attachment = attachment;
	}

	void setFile(File file) {
		iocb.setaio_fildes(file.getFd());
	}

	abstract short getOpcode();
}
