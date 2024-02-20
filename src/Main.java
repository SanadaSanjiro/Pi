import java.math.BigInteger;
import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        long n = 10_000_000;
        long now = System.currentTimeMillis();
        System.out.println("Найдено предположительно простых чисел меньше n:" + pi(n));
        System.out.println("Затрачено времени при последовательной обработке: "
                + (System.currentTimeMillis() - now) + " мсек.");

        now = System.currentTimeMillis();
        System.out.println("Найдено предположительно простых чисел меньше n:" + parallelPi(n));
        System.out.println("Затрачено времени при параллельной обработке: "
                + (System.currentTimeMillis() - now) + " мсек.");
    }

    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
                .mapToObj(BigInteger::valueOf)
                .filter(i->i.isProbablePrime(50))
                .count();
    }

    static long parallelPi(long n) {
        return LongStream.rangeClosed(2, n)
                .parallel()
                .mapToObj(BigInteger::valueOf)
                .filter(i->i.isProbablePrime(50))
                .count();
    }
}
