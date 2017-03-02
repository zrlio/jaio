# jaio

A Java API for libaio.

## Building jaio

- Build libjaio:
```bash
mkdir Release
cd Release
cmake -DCMAKE_BUILD_TYPE=RELEASE ..
```
- Build jaio:
```bash
mvn -DskipTests install
```

## API usage

The following code snippet shows how to create an AIO queue and perform an asynchronous read operation:

```java
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
```

## Contributions

PRs are always welcome. Please fork, and make necessary modifications you propose, and let us know.

## Contact

If you have questions or suggestions, feel free to post at:

https://groups.google.com/forum/#!forum/zrlio-users

or email: zrlio-users@googlegroups.com  

