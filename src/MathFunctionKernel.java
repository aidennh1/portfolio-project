import components.standard.Standard;

/**
 * Kernel interface for math function.
 *
 * @author Aiden Novak-Howgate
 */
public interface MathFunctionKernel extends Standard<MathFunctionKernel> {
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
    void add(int degree, int coefficient);

    /**
     * Returns function value at a given x.
     *
     * @param x
     *            x value that is evaluated
     * @return y value given x
     * @ensures f(x) = sum(coefficient *x^degree)
     */
    double f(int x);

    /**
     * Returns coefficient of a given degree.
     *
     * @param degree
     *            degree of coefficient being returned
     * @return coefficient coeficcient of a given degree
     * @ensures coefficient of x = c where degree n = cx^n
     *
     */
    int coefficient(int degree);

    /**
     * Retirns size of the poloynomial.
     *
     * @return size of underlying data structure
     * @ensures size = this.length
     */
    int size();
}