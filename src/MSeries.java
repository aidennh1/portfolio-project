import java.util.Scanner;

public class MSeries {
    public static double factorial(int n) {
        double sum = 1;
        for (int i = 1; i <= n; i++) {
            sum = sum * i;
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MathFunction sin = new MathFunctionSequence();
        System.out.print("Enter the number of terms for sinx approximation: ");
        int terms = in.nextInt();
        for (int i = 0; i < terms; i++) {
            sin.add((2 * i + 1), Math.pow(-1, i) / (factorial(2 * i + 1)));

        }
        System.out.println(sin.toString());
        System.out.println(sin.f((Math.PI) / 2));
        in.close();
    }
}