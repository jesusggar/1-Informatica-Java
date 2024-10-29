package org.iis.ajnebro;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(15, calculator.add(10, 5), "10 + 5 should equal 15");
    }

    @Test
    void testSubtract() {
        assertEquals(5, calculator.subtract(10, 5), "10 - 5 should equal 5");
    }

    @Test
    void testMultiply() {
        assertEquals(50, calculator.multiply(10, 5), "10 * 5 should equal 50");
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(10, 5), "10 / 5 should equal 2");
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0),
                "Dividing by zero should throw IllegalArgumentException");
    }
}
