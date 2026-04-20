
/*
* Convention: this.rep is a Sequence<Double> where each entry at index i
* represents the coefficient of x^i. Trailing zeros are allowed but the
* sequence is never null.
*
* Correspondence: this = sum(this.rep.entry(i) * x^i) for i in [0, this.rep.length())
*/
import components.sequence.Sequence;
import components.sequence.Sequence1L;

/**
 * Polynomial function built on Sequence<Double>.
 *
 * @author Aiden Novak-Howgate
 */
public class MathFunctionSequence extends MathFunctionSecondary {
    /**
     * Sequence<Double> repersentation of this.
     */
    private Sequence<Double> rep;

    /**
     * Constructor of this.
     */
    public MathFunctionSequence() {
        this.createNewRep();
    }

    /**
     * Create new rep of this.
     */
    private void createNewRep() {
        this.rep = new Sequence1L<Double>();
    }

    /**
     * Adds term with a given coefficient.
     *
     * @requires degree >= 0
     * @param degree
     *            degree of coefficient
     * @param coefficient
     *            coefficient of degree
     * @ensure this = #this union (degree*x^(coefficient))
     */
    @Override
    public void add(int degree, double coefficient) {
        if (degree < this.size()) {
            this.rep.replaceEntry(degree, coefficient);
        } else {
            for (int i = this.size(); i < degree; i++) {
                this.rep.add(i, 0.0);
            }
            this.rep.add(degree, coefficient);
        }
    }

    /**
     * Returns function value at a given x.
     *
     * @param x
     *            x value that is evaluated
     * @return y value given x
     * @ensures f(x) = sum(coefficient *x^degree)
     */
    @Override
    public double f(double x) {
        double value = 0.0;
        for (int i = 0; i < this.size(); i++) {
            value += Math.pow(x, i) * this.coefficient(i);
        }
        return value;
    }

    /**
     * Returns coefficient of a given degree.
     *
     * @param degree
     *            degree of coefficient being returned
     * @return coefficient coeficcient of a given degree
     * @ensures coefficient of x = c where degree n = cx^n
     *
     */
    @Override
    public double coefficient(int degree) {
        return this.rep.entry(degree);
    }

    /**
     * Retirns size of the poloynomial.
     *
     * @return size of underlying data structure
     * @ensures size = this.length
     */
    @Override
    public int size() {
        return this.rep.length();
    }

    /**
     * Returns coefficient of a given degree.
     *
     * @return new math function object
     * @ensures new math function object f(x) = 0
     *
     */
    @Override
    public MathFunction newInstance() {
        return new MathFunctionSequence();
    }

    /**
     * Clears entires of this.
     */
    @Override
    public void clear() {
        this.createNewRep();
    }

    /**
     * Transfers data of this to arg0.
     *
     * @param arg0
     *            param to add data of this to
     */
    @Override
    public void transferFrom(MathFunctionKernel arg0) {
        for (int i = 0; i < arg0.size(); i++) {
            this.add(i, arg0.coefficient(i));
        }
        arg0.clear();
    }

    /**
     * Returns whether two math functions are equivalent.
     *
     * @param other
     *            the math function to compare to this
     * @return true if this == other
     */

    @Override
    public boolean equals(MathFunction other) {
        boolean equal = true;
        MathFunction larger = this;
        MathFunction smaller = other;

        int i = 0;
        if (this.size() < other.size()) {
            larger = other;
            smaller = this;
        }
        while (i < smaller.size() && equal) {
            if (!(other.coefficient(i) == this.coefficient(i))) {
                equal = false;
            }
            i++;
        }

        if (larger.size() > i + 1) {
            while (i < larger.size() && equal) {
                if (larger.coefficient(i) != 0) {
                    equal = false;
                }
            }
        }

        return equal;

    }

    /**
     * returns hash of this.
     *
     * @return hash of this.
     *
     */

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < this.size(); i++) {
            hash += this.rep.entry(i).hashCode();
        }
        return hash;
    }

    /**
     * Creates string rep of this.
     *
     * @return string repersentation of this
     */
    @Override
    public String toString() {
        String stringRep = "";
        for (int i = 0; i < this.size() - 1; i++) {
            if (this.coefficient(i) != 0.0) {
                stringRep += this.coefficient(i) + "x^" + i + " + ";
            }

        }
        stringRep += (this.coefficient(this.size() - 1) + "x^"
                + (this.size() - 1));
        return stringRep;
    }

}
