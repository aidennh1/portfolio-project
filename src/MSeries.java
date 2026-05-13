import java.util.Scanner;

import components.mathfunction.MathFunction;
import components.mathfunction.MathFunctionSequence;

/**
 * Maclourin series of sin proof of utility, the same can be done with cos in
 * turn enabling the user to express any trig function.
 */
final class MSeries {
    /**
     * Prevent instantiation.
     */
    private MSeries() {

    }

    /**
     *
     * @param n
     *            number to find factorial of
     * @return n!
     */
    public static double factorial(int n) {
        double sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    /**
     * Main method.
     *
     * @param args
     *            main method arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MathFunction sin = new MathFunctionSequence();
        System.out.print("Enter the number of terms for sinx approximation: ");
        int terms = in.nextInt();
        for (int i = 0; i < terms; i++) {
            sin.add((2 * i + 1), Math.pow(-1, i) / (factorial(2 * i + 1)));

        }
        System.out.println(sin.toString());
        System.out.println(sin.f((Math.PI) / 2));
        in.close();
    }
}
