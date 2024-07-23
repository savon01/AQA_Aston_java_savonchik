package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTest {

    @Test
    public void testFactorialForZero() {
        int result = Factorial.factorial(0);
        Assert.assertEquals(result, 1, "Факториал от нуля должен быть 1");
    }

    @Test
    public void testFactorialForOne() {
        int result = Factorial.factorial(1);
        Assert.assertEquals(result, 1, "Факториал от единицы должен быть 1");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialForNegativeNumber() {
        Factorial.factorial(-5);
    }

    @Test
    public void testFactorialForPositiveNumber() {
        int result = Factorial.factorial(5);
        Assert.assertEquals(result, 120, "Факториал от положительного числа 5 должен быть 120");
    }
}