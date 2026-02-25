import components.sequence.Sequence;
import components.sequence.Sequence1L;

public final class mvp {
    private Sequence<Integer> rep;

    //Constructors

    private void constructorLogic() {
        this.rep = new Sequence1L<>();
    }

    private void constructorLogic(int[] coefficients) {
        this.constructorLogic();
        int start = 0;
        int end = coefficients.length - 1;
        //sequence adds to the front not end, in a final design this should be nade a helper or built into the loop
        while (start != end) {
            int temp = coefficients[start];
            coefficients[start] = coefficients[end];
            coefficients[end] = temp;
            start++;
            end--;
        }
        for (int i = 0; i < coefficients.length; i++) {
            this.rep.add(i, coefficients[i]);
        }
    }

    //Kernels
    private void add(int degree, int coefficient) {
        //If rep already has defree, just replace its existing coefficient
        if (degree < this.rep.length()) {
            this.rep.replaceEntry(degree, coefficient);
        } else {
            //entries w/ 0 coefficient until reaching desired position
            while (this.rep.length() < degree) {
                this.rep.add(this.rep.length(), 0);
            }
            this.rep.add(degree, coefficient);
        }
    }

    //No need for remove, just use add with a zero

    private double f(double x) {
        double result = 0;

        for (int i = 0; i < this.rep.length(); i++) {
            result += this.rep.entry(i) * Math.pow(x, i);
        }

        return result;
    }

    //Secondary methods

    private double integral(double start, double end) {
        int dxIterations = 1000000;
        double dxSize = (end - start) / dxIterations;
        double result = 0.0;
        final double midpoint = .5;

        for (int i = 0; i < dxIterations; i++) {
            double x = start + (i + midpoint) * dxSize;
            result += this.f(x) * dxSize;
        }

        return result;
    }

    private double derivative(double x) {
        double dx = 1.0 / 1000000;

        return (this.f(x + dx) - this.f(x)) / dx;

    }

    public static void main(String[] args) {
        // create a polynomial 2x^2 + 3x + 1
        mvp poly = new mvp();
        poly.constructorLogic(new int[] { 2, 3, 1 });

        //check f(x)
        System.out.println("f(0) = " + poly.f(0));
        // expected: 1
        System.out.println("f(1) = " + poly.f(1));
        // expected: 6
        System.out.println("f(2) = " + poly.f(2));
        // expected: 15

        // derivative: f'(x) = 4x + 3
        System.out.println("\nf'(0) = " + poly.derivative(0));
        // expected: 3
        System.out.println("f'(1) = " + poly.derivative(1));
        // expected: 7
        System.out.println("f'(3) = " + poly.derivative(3));
        // expected: 15

        // integral of 2x^2 + 3x + 1 from 0 to 1
        // at x=1:3.1667
        System.out.println("\nIntegral from 0 to 1 = " + poly.integral(0, 1));

        // odify polynomial: change x^1 coefficient to 0, making it 2x^2 + 1
        poly.add(1, 0);
        System.out.println("\nAfter removing x term:");
        System.out.println("f(2) = " + poly.f(2));
        // expected: 9
    }
}
