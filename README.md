# gc-overhead-reproducer

Based on [this StackOverflow post](https://stackoverflow.com/a/17235181).

## Compile

```
javac GCOverheadReproducer.java
```

## Run with ParallelGC
```
java -Xmx32m -XX:+UseParallelGC GCOverheadReproducer
```
Result:
```
Consuming all memory
java.lang.OutOfMemoryError: Java heap space
OOME triggered. Releasing 131072 bytes of memory.
Running allocate-release loop
Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
	at GCOverheadReproducer.main(GCOverheadReproducer.java:23)
```

## Run with G1GC
```
timeout 5s java -Xmx32m -XX:+UseG1GC GCOverheadReproducer
```

Result:
```
Consuming all memory
java.lang.OutOfMemoryError: Java heap space
OOME triggered. Releasing 131072 bytes of memory.
Running allocate-release loop
```

The allocate-release loop runs indefinitely, only interrupted by the timeout command.
The GC overhead limit is apparently not exceeded.


## GC Logging

Add `-Xlog:gc` to java command to enable GC logging, or `-Xlog:gc*` for verbose GC logging.

