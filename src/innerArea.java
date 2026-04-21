import components.mathfunction.MathFunction;
import components.mathfunction.MathFunctionSequence;

/**
 * Proof of utiility calculating the are between two functions.
 */
final class InnerArea {
    /**
     * Prevent instantiation.
     */
    private InnerArea() {
    }

    /**
     * Main Method.
     *
     * @param args
     *            main methods arguments
     */
    public static void main(String[] args) {
        final double lowerBound = 1.0 / 4;
        final double upperBound = 10;
        final double expected = 90.1875;
        MathFunction polynomial1 = new MathFunctionSequence();
        MathFunction polynomial2 = new MathFunctionSequence();
        polynomial1.add(0, 1);
        polynomial2.add(1, 2);
        double enclosedArea = (polynomial2.integral(lowerBound, upperBound)

                - polynomial1.integral(lowerBound, upperBound));
        System.out.println(enclosedArea);
        System.out.print("Error: " + ((enclosedArea - expected)));
    }
}
