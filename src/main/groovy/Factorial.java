public class Factorial {

    public static int factorial(double n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }
        if (n != (int) n) {
            throw new IllegalArgumentException("Необходимо ввести целое число");
        }
        if (n == 0) {
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
