public class innerArea {
    public static void main(String[] args) {
        MathFunction polynomial1 = new MathFunctionSequence();
        MathFunction polynomial2 = new MathFunctionSequence();
        for (int i = 0; i < 10; i++) {
            polynomial1.add(i, i);
            polynomial2.add(10 - i, i);
        }

        System.out.print(
                polynomial1.integral(0, 12) - polynomial2.integral(0, 12));
    }
}
