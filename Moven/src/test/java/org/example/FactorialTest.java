package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FactorialTest {
    @Test
    public void testFactorialZero() {
        assertEquals(1, Factorial.factorial(0));
    }

    @Test
    public void testFactorialFive() {
        assertEquals(120, Factorial.factorial(5));
    }

    @Test
    public void testNegativeNumberFactorial() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> Factorial.factorial(-5)
        );
    }
}
