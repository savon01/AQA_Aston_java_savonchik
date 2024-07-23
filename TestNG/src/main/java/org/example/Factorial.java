package org.example;

public class Factorial {

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число должно быть положительным");
        }

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n-1);
    }
}
