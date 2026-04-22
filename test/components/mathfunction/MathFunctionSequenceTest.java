package components.mathfunction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests for abstract class.
 */
public class MathFunctionSequenceTest {
    /**
     * allowed error due to floating point.
     */
    private final double delta = .0005;
    /**
     * the number three.
     */
    private final int three = 3;
    /**
     * the number four.
     */
    private final int four = 4;

    /**
     * Adding a term to blank/0 function, checking size.
     */
    @Test
    public void addtermNonZeroSizeBlankRep() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 2);
        assertEquals(1, f.size());

    }

    /**
     * Adding term to blank/0 function, checking coefficient of said term.
     */
    @Test
    public void addtermNonZeroCoefficientBlankRep() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 2);
        assertEquals(2, f.coefficient(0), this.delta);

    }

    /**
     * Add to an existing term, checks size.
     */
    @Test
    public void addTermToCurrentSizeTest() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        f.add(1, 2);
        f.add(0, this.three);
        assertEquals(2, f.size());
    }

    /**
     * Add to existing term and check value.
     */
    @Test
    public void addTermReplaceCurrentCoefficientTest() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        f.add(1, 2);
        f.add(0, this.three);
        assertEquals(this.four, f.coefficient(0), this.delta);
    }

    /**
     * Add coefficient at term not yet in rep, check value of that position.
     */
    @Test
    public void addTermWithMiddleZeros() {
        final int twelve = 12;
        MathFunction f = new MathFunctionSequence();
        f.add(twelve, 2);
        assertEquals(2, f.coefficient(twelve), this.delta);
    }

    /**
     * evaluate zero function value at an arbitrary x, in this case four.
     */
    @Test
    public void fOfZeroFunction() {
        MathFunction f = new MathFunctionSequence();
        assertEquals(0, f.f(this.four), this.delta);
    }

    /**
     * evaluate constant function at arbitray x, in this case 3.
     */
    @Test
    public void fOfConstant() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 2);
        assertEquals(2, f.f(this.three), this.delta);
    }

    /**
     * evaluate x^2 at x = 2.
     */
    @Test
    public void fOfArbitraryFunction() {
        MathFunction f = new MathFunctionSequence();
        f.add(2, 1);
        assertEquals(this.four, f.f(2), this.delta);
    }

    //coefficient tests
    /**
     * Check coefficient of leading term.
     */
    @Test
    public void coefficeintLeadingTerm() {
        MathFunction f = new MathFunctionSequence();
        f.add(0, 1);
        assertEquals(1, f.coefficient(0), this.delta);
    }

    /**
     * Check coefficient of non-leading term.
     */
    @Test
    public void coefficeintNonLeadingTerm() {
        final int six = 6;
        MathFunction f = new MathFunctionSequence();
        f.add(six, 1);
        assertEquals(1, f.coefficient(six), this.delta);
    }

    //size tests
    /**
     * Check size of the zero function.
     */
    @Test
    public void sizeZeroFunc() {
        MathFunction f = new MathFunctionSequence();
        //guaranteed leading term of zero upon instantiation
        assertEquals(1, f.size());
    }

    /**
     * Check size of a function with leading zeros.
     */
    @Test
    public void leadingZeroFunc() {
        final int eight = 8, nine = 9;
        MathFunction f = new MathFunctionSequence();
        f.add(eight, 2);
        assertEquals(nine, f.size());
    }

    //Equals tests
    /**
     * Check that two zero functions are equal.
     */
    @Test
    public void zeroFuncEqual() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        assertTrue(f1.equals(f2));
    }

    /**
     * Check that two functions are equal when one has trailing zeros.
     */
    @Test
    public void trailingZeros() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        final int ten = 10;

        f1.add(this.three, 2);
        f2.add(this.three, 2);
        f2.add(ten, 0);
        assertTrue(f1.equals(f2));
    }

    /**
     * Test that two functions are properly evaluated as not equal.
     */
    @Test
    public void testNotEqual() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f2.add(0, 1);
        assertTrue(!f1.equals(f2));
    }

    /**
     * Check that hash code is equal for two equal functions.
     */
    @Test
    public void testSameHashForEqualFunc() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        final int ten = 10;
        for (int i = 0; i < ten; i++) {
            f1.add(i, 1);
            f2.add(i, 1);
        }
        assertTrue(f1.hashCode() == f2.hashCode());
    }

    /**
     * CHeck collision minimzing.
     */
    @Test
    public void checkCollisionPrevention() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        final int ten = 10;
        for (int i = 0; i < ten; i++) {
            f1.add(i, i);
            f2.add(i, ten - i);
        }
        assertTrue(f1.hashCode() != f2.hashCode());
    }

    //transfer
    /**
     * Check transfer from original size.
     */
    @Test
    public void transferFromOriginalSize() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f1.add(2, 2);
        f2.transferFrom(f1);
        assertTrue(f1.size() == 1);

    }

    /**
     * Check that object transfered to has proper size.
     */
    @Test
    public void transferFromTransferSize() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f1.add(2, 2);
        f2.transferFrom(f1);
        assertTrue(f2.size() == this.three);

    }

    /**
     * Check function transfered to has right data.
     */
    @Test
    public void transferData() {
        MathFunction f1 = new MathFunctionSequence();
        MathFunction f2 = new MathFunctionSequence();
        f1.add(2, 2);
        f2.transferFrom(f1);
        assertTrue(f2.coefficient(2) == 2);
    }

    /**
     * Check that cleared function has size 1 (0).
     */
    @Test
    public void clearedSize() {
        MathFunction f1 = new MathFunctionSequence();
        final int ten = 10;
        f1.add(ten, 1);
        f1.clear();
        assertTrue(f1.size() == 1);
    }
}
