/**
 * Enhanced interface for MathFunction component.
 *
 * @author Aiden Novak-Howgate
 */
public interface MathFunction extends MathFunctionKernel {

    /**
     * Adds all terms of the given polynomial to this polynomial.
     *
     * @param p
     *            the polynomial to add
     * @updates this
     * @requires p != null
     * @ensures this = #this + p
     */
    void add(MathFunction p);

    /**
     * Scales all coefficients by the given scalar.
     *
     * @param scalar
     *            the value to scale by
     * @updates this
     * @ensures every coefficient in this = #coefficient * scalar
     */
    void scale(int scalar);

    /**
     * Returns the derivative of this polynomial evaluated at x.
     *
     * @param x
     *            the value to evaluate the derivative at
     * @return the deribative of this polynomial evaluated at x
     * @ensures derivative = d/dx of #this evaluated at x
     */
    double derivative(double x);

    /**
     * Returns the definite integral of this polynomial from a to b.
     *
     * @param a
     *            the lower bound of integration
     * @param b
     *            the upper bound of integration
     * @return the definite integral of this polynomial from a to b
     * @requires a <= b - could change this and just if to flip and make
     *           negative
     * @ensures integral = integral of #this from a to b
     */
    double integral(double a, double b);

    /**
     * Returns the zeros of this polynomial over the given range.
     *
     * @param a
     *            the lower bound of the range
     * @param b
     *            the upper bound of the range
     * @return array of x values where this polynomial equals zero
     * @requires a <= b
     * @ensures all values in zeros are in [a, b] and satisfy f(x) = 0
     */
    double[] zeros(double a, double b);

    /**
     * Returns the x values of all relative maximums over the given range.
     *
     * @param a
     *            the lower bound of the range
     * @param b
     *            the upper bound of the range
     * @return array of x values where relative maximums occur
     * @requires a <= b
     * @ensures all values in relativeMaxs are in [a, b] and satisfy
     *          derivative(x) = 0 and the derivative changes from positive to
     *          negative at x
     */
    double[] relativeMaxs(double a, double b);

    /**
     * Returns the x values of all relative minimums over the given range.
     *
     * @param a
     *            the lower bound of the range
     * @param b
     *            the upper bound of the range
     * @return array of x values where relative minimums occur
     * @requires a <= b
     * @ensures all values in relativeMins are in [a, b] and satisfy
     *          derivative(x) = 0 and the derivative changes from negative to
     *          positive at x
     */
    double[] relativeMins(double a, double b);

    /**
     * Returns a string representation of this polynomial.
     *
     * @return a string representation of this polynomial
     * @ensures toString = a representation of this polynomial in the form "ax^n
     *          + bx^(n-1) + ... + c"
     */
    @Override
    String toString();

}