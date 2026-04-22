package components.mathfunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 *
 */
public class MathFunctionTest {
    /**
     * Allowed error for floating point.
     */
    private final double delta = .0005;

    /**
     * adding zero function doesnt change anything.
     */
    @Test
    public void addZeroFuncs() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f1.add(f2);
        assertTrue(f2.equals(f1));
    }

    /**
     * adding terms of the same degree properly sums coefficent.
     */
    @Test
    public void addSameDegree() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        MathFunction expected = new MathFunctionSequence();
        expected.add(0, 2);
        f1.add(0, 1);
        f2.add(0, 1);
        f1.add(f2);

        assertTrue(f1.equals(expected));
    }

    /**
     * Adding functions with varying degree preserves and sums.
     */
    @Test
    public void addDifDegree() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        MathFunction expected = new MathFunctionSequence();
        f1.add(0, 1);
        f2.add(1, 1);
        f1.add(f2);
        expected.add(0, 1);
        expected.add(1, 1);
        assertTrue(f1.equals(expected));
    }

    /**
     * zero function scaled is the zero function.
     */
    @Test
    public void scalerZero() {
        MathFunction f1 = new MathFunctionSequence();
        f1.scale(2);
        assertEquals(0, f1.coefficient(0), this.delta);
    }

    /**
     * scaling a function with non-zero coefficients correctly scales all terms.
     */
    @Test
    public void scaleNonZero() {
        final int five = 5;
        MathFunction f1 = new MathFunctionSequence();
        MathFunction expected = new MathFunctionSequence();
        for (int i = 0; i < five; i++) {
            f1.add(i, 1.0);
            expected.add(i, 2.0);
        }
        f1.scale(2);
        assertTrue(f1.equals(expected));
    }

    //derivative tests

    /**
     * derivative of zero function at arbitrary point = 0.
     */
    @Test
    public void zeroFunctionDeriv() {
        final int twelve = 12;
        MathFunction f1 = new MathFunctionSequence();
        assertEquals(0, f1.derivative(twelve), this.delta);

    }

    /**
     * Derivative of function at given point.
     */
    @Test
    public void arbitraryDeriv() {
        MathFunction f1 = new MathFunctionSequence();
        f1.add(2, 1);
        assertEquals(2, f1.derivative(1), this.delta);
    }

    //Integral tests
    /**
     * Integral of the zero function equals zero regardless of bounds.
     */
    @Test
    public void zeroFuncIntegral() {
        final int twoHundred = 200;
        MathFunction f1 = new MathFunctionSequence();
        assertEquals(0, f1.integral(0, twoHundred), this.delta);
    }

    /**
     * Test integral of a given function.
     */
    @Test
    public void integralOverArbitraryFunc() {
        MathFunction f1 = new MathFunctionSequence();
        final int four = 4;
        f1.add(1, 2);
        assertEquals(four, f1.integral(0, 2), this.delta);
    }

    //zeros tests
    /**
     * Check that zeros isnt returning false positives.
     */
    @Test
    public void noZeros() {
        final int ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        double[] arr = f.zeros(-1 * ten, ten);

        assertEquals(1, arr.length);
    }

    /**
     * Check that -1 is returned when there arent zeros.
     */
    @Test
    public void noZerosValue() {
        final int ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        double[] arr = f.zeros(-1 * ten, ten);

        assertEquals(-1, arr[0], this.delta);
    }

    /**
     * Check the size of array of zeros.
     */
    @Test
    public void zerosQuadraticSize() {
        final int two = 2;
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        f.add(2, 1);
        assertEquals(2, f.zeros(-1 * two, 2).length);

    }

    /**
     * Check the value of first zero, ensure correct value.
     */
    @Test
    public void zerosQuadraticFirstZero() {
        final int two = 2;
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        f.add(2, 1);
        assertEquals(-1, f.zeros(-1 * two, 2)[0], this.delta);

    }

    /**
     * Check the value of second zero, ensure correct value.
     */
    @Test
    public void zerosQuadraticSecondZero() {
        final int two = 2;
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        f.add(2, 1);
        assertEquals(1, f.zeros(-1 * two, 2)[1], this.delta);

    }

    //relativeMaxs tests
    /**
     * Ensure that there are no false positive zeros.
     */
    @Test
    public void zeroRelativeMaxArrayLength() {
        final int ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        assertEquals(1, f.relativeMaxs(-1 * ten, ten).length);

    }

    /**
     * no relative maxes returns correct value.
     */
    @Test
    public void zeroRelativeMaxArrayValue() {
        final int ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        assertEquals(-1, f.relativeMaxs(-1 * ten, ten)[0], this.delta);

    }

    /**
     * Check that array of function with 2 relative maxes returns correct
     * length.
     */
    @Test
    public void relativeMaxsArrayLength() {
        final int four = 4, ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        f.add(four, -1);
        assertEquals(2, f.relativeMaxs(-1 * ten, ten).length);
    }

    /**
     * CHeck that first relative max is correct.
     */
    @Test
    public void relativeMaxsArrayFirstValue() {
        final double max = -0.70711, ten = 10;
        final int four = 4;
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        f.add(four, -1);
        assertEquals(max, f.relativeMaxs(-1 * ten, ten)[0], this.delta);
    }

    /**
     * CHeck that second relative max is correct.
     */
    @Test
    public void relativeMaxsArraySecondtValue() {
        final double max = 0.70711, ten = 10;
        final int four = 4;
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        f.add(four, -1);
        assertEquals(max, f.relativeMaxs(-1 * ten, ten)[0], this.delta);
    }

    /**
     * Check that function with zero relative min returns array of correct size.
     */
    @Test
    public void zeroRelativeMinArrayLength() {
        final int ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        assertEquals(1, f.relativeMins(-1 * ten, ten).length);

    }

    /**
     * Check that mins array is correct size.
     */
    @Test
    public void relativeMinsArrayLength() {
        final int four = 4, ten = 10;
        MathFunction f = new MathFunctionSequence();
        f.add(2, -1);
        f.add(four, 1);
        assertEquals(2, f.relativeMins(-1 * ten, ten).length);
    }

    /**
     *
     */
    @Test
    public void relativeMinsArrayFirstValue() {
        final int four = 4, ten = 10;
        final double min = -0.70711;
        MathFunction f = new MathFunctionSequence();
        f.add(2, -1);
        f.add(four, 1);
        assertEquals(min, f.relativeMins(-1 * ten, ten)[0], this.delta);
    }

    /**
     * Check second min is correct value.
     */
    @Test
    public void relativeMinssArraySecondtValue() {
        final int four = 4, ten = 10;
        final double min = 0.70711;
        MathFunction f = new MathFunctionSequence();
        f.add(2, -1);
        f.add(four, 1);
        assertEquals(min, f.relativeMins(-1 * ten, ten)[0], this.delta);
    }
}
