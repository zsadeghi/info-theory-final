# Encoder/Decoder

Everything in this implementation is broken into parts to allow for an easier
reading of the code.

The main package is `me.theyinspire.projects.infotheory.encdec`
and everything else comes under that.

## Matrix Tools

Since this code uses matrices for calculations, I have implemented a simple
matrix interface using object arrays.

This can be found under `~.matrix.*`.

The operations "transpose" (`M^T`) and "combine" (`M = [X|Y]`)
have been implemented using view classes that do not store additional
data. This is so that such operations can be completed quickly.

The matrix printer class simply reads the contents of a matrix and returns
a string representation of it.

## Bit-wise Operations

All the bit-related operations are done via the enum `Bit` which can be
found under `~.bit.*`. This is so that we can create a bit matrix
via this enum and the matrix tools introduced above:

```java
Matrix<Bit> m = new ArrayMatrix<>();
``` 

## Running the Application

This application can be built and run using Maven. On a Mac machine, you
can install Maven via [Homebrew](http://brew.sh) by doing

```
$ brew install maven
```

Once done, you can then build the application by doing

```java
mvn clean install
```

in the same folder as the one that contains the `pom.xml` folder.

This command creates the `encdec.jar` file under `target/` which you can
then run as

```java
java -jar encdec.jar
```

This command will run the `EncoderDecoder#main()` method.

You can also pass 2 arguments to the class:

```java
java -jar encdec.jar 30 20
```

which will set the values for `n` and `k` respectively.