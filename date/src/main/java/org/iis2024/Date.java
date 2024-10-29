package org.iis2024;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents a calendar date with validation capabilities.
 *
 * This class encapsulates a date specified by day, month, and year components. It offers
 * functionality to validate the date against specific rules: the year must be within the range of
 * 1900 to 2050, inclusive; the month must be within 1 to 12, inclusive; and the day must be valid
 * within the context of the specified month and year, taking into account variations for leap
 * years.
 *
 * Instances of this class are immutable, meaning that once a Date object is created, the day,
 * month, and year values cannot be changed.
 */
public class Date {
  private final int day;
  private final int month;
  private final int year;

  /**
   * Retrieves the day component of this date.
   *
   * @return The day of the month (1-31).
   */
  public int getDay() {
    return day;
  }

  /**
   * Retrieves the month component of this date.
   *
   * @return The month of the year (1-12).
   */
  public int getMonth() {
    return month;
  }

  /**
   * Retrieves the year component of this date.
   *
   * @return The year (within the valid range of 1900 to 2050).
   */
  public int getYear() {
    return year;
  }

  /**
   * Constructs a Date object representing a specific day, month, and year.
   *
   * @param day The day of the month (1-31).
   * @param month The month of the year (1-12).
   * @param year The year (should ideally be within the valid range of 1900 to 2050 for validation
   *     to pass).
   */
  public Date(int day, int month, int year) {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Validates the date according to the predefined rules: the year must be within 1900 to 2050, the
   * month within 1 to 12, and the day valid within the given month and year, considering leap
   * years.
   *
   * This method utilizes {@link SimpleDateFormat} to parse the date, configured to be
   * non-lenient to ensure strict validation against the provided day, month, and year.
   *
   * @return true if the date is valid, false otherwise.
   */
  public boolean validate() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    dateFormat.setLenient(false);

    String dateString = String.format("%d/%d/%d", day, month, year);

    try {
      dateFormat.parse(dateString);
      return (year >= 1900) && (year <= 2050);
    } catch (ParseException e) {
      return false;
    }
  }
}
