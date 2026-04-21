package components.mathfunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MathFunctionTest {

    private final double delta = .0005;

    @Test
    public void addZeroFuncs() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f1.add(f2);
        assertTrue(f2.equals(f1));
    }

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

    //scale tests
    @Test
    public void scalerZero() {
        MathFunction f1 = new MathFunctionSequence();
        f1.scale(2);
        assertEquals(0, f1.coefficient(0), this.delta);
    }

    @Test
    public void scaleNonZero() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction expected = new MathFunctionSequence();
        for (int i = 0; i < 5; i++) {
            f1.add(i, 1.0);
            expected.add(i, 2.0);
        }
        f1.scale(2);
        assertTrue(f1.equals(expected));
    }

    //derivative tests
    @Test
    public void zeroFunctionDeriv() {
        MathFunction f1 = new MathFunctionSequence();
        assertEquals(0, f1.derivative(123), this.delta);

    }

    @Test
    public void arbitraryDeriv() {
        MathFunction f1 = new MathFunctionSequence();
        f1.add(2, 1);
        assertEquals(2, f1.derivative(1), this.delta);
    }

    //Integral tests
    @Test
    public void zeroFuncIntegral() {
        MathFunction f1 = new MathFunctionSequence();
        assertEquals(0, f1.integral(0, 200), this.delta);
    }

    @Test
    public void integralOverArbitraryFunc() {
        MathFunction f1 = new MathFunctionSequence();
        f1.add(1, 2);
        assertEquals(4, f1.integral(0, 2), this.delta);
    }

    //zeros tests
    @Test
    public void noZeros() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        double[] arr = f.zeros(-10, 10);

        assertEquals(1, arr.length);
    }

    @Test
    public void noZerosValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        double[] arr = f.zeros(-10, 10);

        assertEquals(-1, arr[0], this.delta);
    }

    @Test
    public void zerosQuadraticSize() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        f.add(2, 1);
        assertEquals(2, f.zeros(-2, 2).length);

    }

    @Test
    public void zerosQuadraticFirstZero() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        f.add(2, 1);
        assertEquals(-1, f.zeros(-2, 2)[0], this.delta);

    }

    @Test
    public void zerosQuadraticSecondZero() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        f.add(2, 1);
        assertEquals(1, f.zeros(-2, 2)[1], this.delta);

    }

    //relativeMaxs tests
    @Test
    public void zeroRelativeMaxArrayLength() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        assertEquals(1, f.relativeMaxs(-10, 10).length);

    }

    @Test
    public void zeroRelativeMaxArrayValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        assertEquals(-1, f.relativeMaxs(-10, 10)[0], this.delta);

    }

    @Test
    public void relativeMaxsArrayLength() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        f.add(4, -1);
        assertEquals(2, f.relativeMaxs(-10, 10).length);
    }

    @Test
    public void relativeMaxsArrayFirstValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        f.add(4, -1);
        assertEquals(-0.70711, f.relativeMaxs(-10, 10)[0], this.delta);
    }

    @Test
    public void relativeMaxsArraySecondtValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        f.add(4, -1);
        assertEquals(0.70711, f.relativeMaxs(-10, 10)[1], this.delta);
    }

    //relative mins tests
    @Test
    public void zeroRelativeMinArrayLength() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        assertEquals(1, f.relativeMins(-10, 10).length);

    }

    @Test
    public void zeroRelativeMinArrayValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, -1);
        assertEquals(-1, f.relativeMins(-10, 10)[0], this.delta);

    }

    @Test
    public void relativeMinsArrayLength() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, -1);
        f.add(4, 1);
        assertEquals(2, f.relativeMins(-10, 10).length);
    }

    @Test
    public void relativeMinsArrayFirstValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, -1);
        f.add(4, 1);
        assertEquals(-0.70711, f.relativeMins(-10, 10)[0], this.delta);
    }

    @Test
    public void relativeMinssArraySecondtValue() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, -1);
        f.add(4, 1);
        assertEquals(0.70711, f.relativeMins(-10, 10)[1], this.delta);
    }
}
