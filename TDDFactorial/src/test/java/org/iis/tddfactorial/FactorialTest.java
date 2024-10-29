package org.iis.tddfactorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @Test
    void theFactorialOfZeroIsOne() {
        Factorial factorial = new Factorial();
        int value = 0;
        int obtainResult = factorial.compute(value);
        int expectedResult = 1;
        assertEquals(expectedResult, obtainResult);
    }

    @Test
    void theFactorialOfOneIsOne() {
        Factorial factorial = new Factorial();
        int value = 1;
        int obtainResult = factorial.compute(value);
        int expectedResult = 1;
        assertEquals(expectedResult, obtainResult);
    }

    @Test
    void theFactorialOfTwoIsTwo() {
        Factorial factorial = new Factorial();
        int value = 2;
        int obtainResult = factorial.compute(value);
        int expectedResult = 2;
        assertEquals(expectedResult, obtainResult);
    }

    @Test
    void theFactorialOfThreeIsSix() {
        Factorial factorial = new Factorial();
        int value = 3;
        int obtainResult = factorial.compute(value);
        int expectedResult = 6;
        assertEquals(expectedResult, obtainResult);
    }

    @Test
    void theFactorialOfFourIsTwentyFour() {
        Factorial factorial = new Factorial();
        int value = 4;
        int obtainResult = factorial.compute(value);
        int expectedResult = 24;
        assertEquals(expectedResult, obtainResult);
    }
}
