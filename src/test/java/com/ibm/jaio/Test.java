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
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;

public class Test {
	public static void main(String[] args) {
		try {
			AsynchronousIOQueue aio = new AsynchronousIOQueue(10);
			AsynchronousIOOperationArray ops = new AsynchronousIOOperationArray(1);
			ByteBuffer dest = ByteBuffer.allocateDirect(512);
			File file = new File(FileSystems.getDefault().getPath("/tmp/test"),
					OpenOption.DIRECT, OpenOption.READ, OpenOption.WRITE);
			Integer i = 5;
			AsynchronousIORead<Integer> read = new AsynchronousIORead();
			read.set(file, dest, 0, i);
			ops.set(0, read);
			aio.submit(ops);
			AsynchronousIOResultArray<Integer> result = new AsynchronousIOResultArray(1);
			int n;
			do {
				n = aio.poll(result);
			} while (n == 0);
			System.out.println("result: " + result.get(0).getResult());
			while (dest.hasRemaining()) {
				System.out.print((byte)dest.get());
			}
			System.out.println();
			aio.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
