package org.iis2024;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/** Unit tests for the Date class. */
public class DateTest {

  @Test
  void givenValidDate_whenValidating_thenTrue() {
    // Arrange
    Date validDate = new Date(15, 3, 2021);

    // Act
    boolean isValid = validDate.validate();

    // Assert
    assertTrue(isValid, "Date should be valid");
  }

  @Test
  void givenDateWithInvalidYear_whenValidating_thenFalse() {
    // Arrange
    Date invalidYearDate = new Date(10, 10, 1850);

    // Act
    boolean isValid = invalidYearDate.validate();

    // Assert
    assertFalse(isValid, "Date with year out of valid range should be invalid");
  }

  @Test
  void givenDateWithInvalidMonth_whenValidating_thenFalse() {
    // Arrange
    Date invalidMonthDate = new Date(1, 13, 2020);

    // Act
    boolean isValid = invalidMonthDate.validate();

    // Assert
    assertFalse(isValid, "Date with month out of valid range should be invalid");
  }

  @Test
  void givenDateWithInvalidDay_whenValidating_thenFalse() {
    // Arrange
    Date invalidDayDate = new Date(32, 1, 2020);

    // Act
    boolean isValid = invalidDayDate.validate();

    // Assert
    assertFalse(isValid, "Date with day out of valid range should be invalid");
  }

  @Test
  void givenLeapYearDate_whenValidating_thenTrue() {
    // Arrange
    Date leapYearDate = new Date(29, 2, 2020);

    // Act
    boolean isValid = leapYearDate.validate();

    // Assert
    assertTrue(isValid, "Date should be valid in a leap year");
  }

  @Test
  void givenNonLeapYearDate_whenValidating_thenFalse() {
    // Arrange
    Date nonLeapYearDate = new Date(29, 2, 2019);

    // Act
    boolean isValid = nonLeapYearDate.validate();

    // Assert
    assertFalse(isValid, "Date should be invalid in a non-leap year");
  }
}

