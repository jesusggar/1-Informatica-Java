package es.uma.itis.iis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarTest {
    private Car testCar;

    @BeforeEach
    void setUp() {
        testCar = new Car();
    }

    @AfterEach
    void tearDown() {
        testCar = null;
    }

    @Test
    void anExceptionIsThrowWhenTryingToReduceMileage() {
        testCar.setMileage(1000);
        assertThrows(Exception.class, ()-> testCar.setMileage(500));
    }
}
