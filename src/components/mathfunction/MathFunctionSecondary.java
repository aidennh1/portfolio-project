package components.mathfunction;

import java.util.Arrays;

/**
 * Abstract class.
 *
 * @author Aiden Novak-Howgate
 */
public abstract class MathFunctionSecondary implements MathFunction {

    /**
     * The step size used for numerical approximations.
     */
    private final double deltaX = 1. / 1_000_000;

    /**
     * Adds the coefficients of another function to this function.
     *
     * @param a
     *            the function whose coefficients are added to this function
     * @requires a.size() <= this.size()
     * @ensures for all i in [0, a.size()): this.coefficient(i) =
     *          #this.coefficient(i) + a.coefficient(i)
     */
    @Override
    public void add(MathFunction a) {
        for (int i = 0; i < a.size(); i++) {
            this.add(i, a.coefficient(i));
        }
    }

    /**
     * Scales all coefficients of this function by the given scalar.
     *
     * @param scalar
     *            the value by which each coefficient is multiplied
     * @ensures for all i in [0, this.size()): this.coefficient(i) =
     *          #this.coefficient(i) * scalar
     */
    @Override
    public void scale(double scalar) {
        for (int i = 0; i < this.size(); i++) {
            this.add(i, this.coefficient(i) * scalar - this.coefficient(i));
        }
    }

    /**
     * Returns the approximate derivative of this function at x using the limit
     * definition.
     *
     *
     * @param x
     *            the point at which the derivative is approximated
     * @return the approximate derivative f'(x)
     * @ensures derivative = (f(x + deltaX) - f(x)) / deltaX
     */
    @Override
    public double derivative(double x) {
        return (this.f(x + this.deltaX) - this.f(x)) / this.deltaX;
    }

    /**
     * Returns the approximate definite integral of this function over [a,b]
     * using the trapezoidal rule.
     *
     * @param a
     *            the lower bound of integration
     * @param b
     *            the upper bound of integration
     * @requires a <= b
     * @return the approximate value of the integral over [a, b]
     * @ensures integral = sum((f(i) + f(i + deltaX)) / 2 * deltaX) for i in [a,
     *          b) stepping by deltaX
     */
    @Override
    public double integral(double a, double b) {
        double result = 0.0;

        for (double i = a; i < b; i += this.deltaX) {
            result += (this.f(i) + this.f(i + this.deltaX)) / 2.0 * this.deltaX;
        }

        return result;
    }

    /**
     * Returns an array of approximate zeros of this function in [a,b].
     *
     * @param a
     *            the lower bound of the search interval
     * @param b
     *            the upper bound of the search interval
     * @requires a < b
     * @return array of x values where f(x) = 0 within [a, b]
     * @ensures for all x in zeros: f(x) * f(x + deltaX) < 0 or f(x) = 0
     */
    @Override
    public double[] zeros(double a, double b) {
        int n = (int) ((b - a) / this.deltaX);
        double[] temp = new double[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            double x = a + i * this.deltaX;
            if (this.f(x) * this.f(x + this.deltaX) < 0 || this.f(x) == 0.0) {
                temp[count++] = x;
            }
        }
        double[] result = new double[] { -1 };
        if (count != 0) {
            result = Arrays.copyOf(temp, count);
        }
        return result;

    }

    /**
     * Returns an array of approximate relative maximum points of this function
     * in [a,b].
     *
     * @param a
     *            the lower bound of the interval
     * @param b
     *            the upper bound of the interval
     * @requires a < b
     * @return array of x values where a relative maximum occurs within [a, b]
     * @ensures for all x in relativeMaxs: f'(x) >= 0 and f'(x + deltaX) < 0
     */
    @Override
    public double[] relativeMaxs(double a, double b) {
        int n = (int) ((b - a) / this.deltaX);
        double[] temp = new double[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            double x = a + i * this.deltaX;
            if (this.derivative(x) >= 0
                    && this.derivative(x + this.deltaX) < 0) {
                temp[count++] = x;
            }
        }

        double[] result = new double[] { -1 };
        if (count != 0) {
            result = Arrays.copyOf(temp, count);
        }
        return result;
    }

    /**
     * Returns an array of approximate relative minimum points of this function
     * in [a,b].
     *
     * @param a
     *            the lower bound of the search interval
     * @param b
     *            the upper bound of the search interval
     * @requires a < b
     * @return array of x values where a relative minimum occurs within [a, b]
     * @ensures for all x in relativeMins: f'(x) <= 0 and f'(x + deltaX) > 0
     */
    @Override
    public double[] relativeMins(double a, double b) {
        int n = (int) ((b - a) / this.deltaX);
        double[] temp = new double[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            double x = a + i * this.deltaX;
            if (this.derivative(x) <= 0
                    && this.derivative(x + this.deltaX) > 0) {
                temp[count++] = x;
            }
        }
        double[] result = new double[] { -1 };
        if (count != 0) {
            result = Arrays.copyOf(temp, count);
        }
        return result;
    }

    /**
     * Returns a string repersentation of this.
     *
     * @return string rep
     * @ensures toString= coefficient(i)x^(i) for all non-zero terms
     */
    @Override
    public String toString() {
        String stringRep = "";
        if (this.coefficient(0) == 0 && this.size() <= 1) {
            stringRep += "0";
        } else if (this.coefficient(0) != 0) {
            stringRep += this.coefficient(0);
        }
        for (int i = 1; i < this.size(); i++) {
            if (this.coefficient(i) > 0) {
                stringRep += "+" + this.coefficient(i) + "x^" + i + ", ";
            } else if (this.coefficient(i) < 0) {
                stringRep += this.coefficient(i) + "x^" + i + ", ";
            }
        }
        return stringRep;
    }

    /**
     * Returns whether this function is equal to the given function.
     *
     * @param p
     *            the functio to compare to this function
     * @return true if this function equals p, false otherwise
     * @ensures equals = for all i in larger, coeficient of i == 0. for all i in
     *          larger and smaller larger.coefficeint(i) ==
     *          smaller.coefficient(i)
     */
    @Override
    public boolean equals(MathFunction p) {
        boolean isEqual = true;
        MathFunction larger = this;
        MathFunction smaller = p;
        if (this.size() < p.size()) {
            larger = p;
            smaller = this;
        }
        int largerSize = larger.size() - 1;

        while (largerSize >= smaller.size() && isEqual) {
            if (larger.coefficient(largerSize--) != 0) {
                isEqual = false;
            }
        }

        while (largerSize >= 0 && isEqual) {
            if (larger.coefficient(largerSize) != smaller
                    .coefficient(largerSize)) {
                isEqual = false;
            }
            largerSize--;
        }

        return isEqual;
    }

    /**
     * computes hash of this.
     *
     * @return hash of this
     */
    @Override
    public int hashCode() {
        // prevent collision to some extent ie 1 + 2x + 3x^2 != 3 + 2x + x^2
        final int ten = 10;
        int hash = 0;
        for (int i = 0; i < this.size(); i++) {
            hash += ((Double) (this.coefficient(i)) * ten);
        }
        return hash;
    }

}
