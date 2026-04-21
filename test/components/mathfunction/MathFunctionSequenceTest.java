package components.mathfunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MathFunctionSequenceTest {
    /**
     * allowed error due to floating point.
     */
    private final double delta = .0005;

    @Test
    public void addtermNonZeroSizeBlankRep() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 2);
        assertEquals(1, f.size());

    }

    @Test
    public void addtermNonZeroCoefficientBlankRep() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 2);
        assertEquals(2, f.coefficient(0), this.delta);

    }

    @Test
    public void addTermReplaceCurrentSizeTest() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        f.add(1, 2);
        f.add(0, 3);
        assertEquals(2, f.size());
    }

    @Test
    public void addTermReplaceCurrentCoefficientTest() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        f.add(1, 2);
        f.add(0, 3);
        assertEquals(4, f.coefficient(0), this.delta);
    }

    @Test
    public void addTermWithMiddleZeros() {
        MathFunction f = new MathFunctionSequence();
        f.add(12, 2);
        assertEquals(2, f.coefficient(12), this.delta);
    }

    //f() tests

    @Test
    public void fOfZeroFunction() {
        MathFunction f = new MathFunctionSequence();
        assertEquals(0, f.f(12), this.delta);
    }

    @Test
    public void fOfConstant() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 2);
        System.out.print(f.f(12313131));
        assertEquals(2, f.f(123131), this.delta);
    }

    @Test
    public void fOfArbitraryFunction() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        assertEquals(4, f.f(2), this.delta);
    }

    //coefficient tests

    @Test
    public void coefficeintLeadingTerm() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        assertEquals(1, f.coefficient(0), this.delta);
    }

    @Test
    public void coefficeintNonLeadingTerm() {
        MathFunction f = new MathFunctionSequence();
        f.add(6, 1);
        assertEquals(1, f.coefficient(6), this.delta);
    }

    //size tests
    @Test
    public void sizeZeroFunc() {
        MathFunction f = new MathFunctionSequence();
        //guaranteed leading term of zero upon instantiation
        assertEquals(1, f.size());
    }

    @Test
    public void leadingZeroFunc() {
        MathFunction f = new MathFunctionSequence();
        f.add(8, 2);
        assertEquals(9, f.size());
    }

    //Equals tests
    @Test
    public void zeroFuncEqual() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        assertTrue(f1.equals(f2));
    }

    @Test
    public void trailingZeros() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();

        f1.add(3, 2);
        f2.add(3, 2);
        f2.add(10, 0);
        assertTrue(f1.equals(f2));
    }

    @Test
    public void testNotEqual() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f2.add(0, 1);
        assertTrue(!f1.equals(f2));
    }
}
