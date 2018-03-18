package me.theyinspire.projects.infotheory.encdec;

import me.theyinspire.projects.infotheory.encdec.bit.Bit;
import me.theyinspire.projects.infotheory.encdec.bit.Syndrome;
import me.theyinspire.projects.infotheory.encdec.matrix.Matrix;

import static me.theyinspire.projects.infotheory.encdec.bit.BitMatrix.*;

public class EncoderDecoder {

    public static void main(String[] args) {
        final int n;
        final int k;
        if (args.length != 2) {
            System.out.println("To set `n` and `k` launch the program with /path/to/app n k");
            System.out.println("No input data given. Assuming n=30 and k=20. The first two arguments"
                                       + " can be set to change this.");
            System.out.println();
            n = 30;
            k = 20;
        } else {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        }
        final Matrix<Bit> random = random(n, k);
        final Matrix<Bit> generator = identity(n).combine(random);
        final Matrix<Bit> check = random.transpose().combine(identity(k));
        final Matrix<Bit> message = random(1, k);
        final Syndrome syndrome = syndrome(message, generator, check.transpose(), n, k);
        System.out.println("Original generator matrix:");
        System.out.println(random);
        System.out.println();
        System.out.println("Systematic form of generator matrix:");
        System.out.println(generator);
        System.out.println();
        System.out.println("Check matrix:");
        System.out.println(check);
        System.out.println();
        System.out.println("Sample message:");
        System.out.println(asBlock(message));
        System.out.println();
        System.out.println("Codeword for message:");
        System.out.println(asBlock(encode(message, generator, n, k)));
        System.out.println();
        System.out.println("Syndrome mapping:");
        System.out.println(syndrome);
        System.out.println();
    }

}
