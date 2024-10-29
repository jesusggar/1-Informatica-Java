package com.gildedrose.original;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Gilded Rose project
 *
 * @author Antonio J. Nebro
 */
@DisplayName(("The Gilded Rose"))
class GildedRoseTest {

  @Nested
  @DisplayName("When update Aged Brie")
  class TestCasesForBrie {
    public static final String AGED_BRIE = "Aged Brie";

    @DisplayName("Increases its quality by one if sellIn is greater than zero")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityIncreasesByOne() {
      // Arrange
      int quality = 0;
      int sellIn = 2;
      Item item = new Item(AGED_BRIE, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 1, newQuality);
    }

    @DisplayName("Increases its quality by two if sellIn is zero")
    @Test
    void givenAQualityValueAndSellInIsZeroWhenUpdateThenQualityIncreasesByOne() {
      // Arrange
      int quality = 10;
      int sellIn = 0;
      Item item = new Item(AGED_BRIE, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 2, newQuality);
    }

    @DisplayName("Does not increase its quality with this is 50 and sell in is 0")
    @Test
    void givenAQualityValueOf50AndSellInIsZeroWhenUpdateThenQualityRemains() {
      // Arrange
      int quality = 50;
      int sellIn = 0;
      Item item = new Item(AGED_BRIE, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality, newQuality);
    }

    @DisplayName("Does not increase its quality with this is 50 and sell in is positive")
    @Test
    void givenAQualityValueOf50AndSellInIsPositiveWhenUpdateThenQualityRemains() {
      // Arrange
      int quality = 50;
      int sellIn = 2;
      Item item = new Item(AGED_BRIE, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality, newQuality);
    }

    @DisplayName("Decreases sell in with this is positive")
    @Test
    void givenAPositiveSellInValueWhenUpdateThenItsValueDecreasesByOne() {
      // Arrange
      int quality = 50;
      int sellIn = 2;
      Item item = new Item("Aged Brie", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newSellIn = item.sellIn;
      assertEquals(sellIn - 1, newSellIn);
    }

    @DisplayName("Decreases sell in with this is zero")
    @Test
    void givenAZeroSellInValueWhenUpdateThenItsValueDecreasesByOne() {
      // Arrange
      int quality = 50;
      int sellIn = 0;
      Item item = new Item("Aged Brie", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newSellIn = item.sellIn;
      assertEquals(sellIn - 1, newSellIn);
    }
  }

  @Nested
  @DisplayName("When update a backstage pass")
  class TestCasesForBackstagePass {
    public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";

    @DisplayName("Increases its quality by one when sell in is greater than 10")
    @Test
    void givenSellInIsGreaterThan10WhenUpdateThenItsQualityIncreasesByOne() {
      // Arrange
      int quality = 20;
      int sellIn = 11;
      Item item = new Item(BACKSTAGE_PASS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 1, newQuality);
    }

    @DisplayName("Increases its quality by two when sell in is 10")
    @Test
    void givenSellInIs10WhenUpdateThenItsQualityIncreasesByTwo() {
      // Arrange
      int quality = 20;
      int sellIn = 10;
      Item item = new Item(BACKSTAGE_PASS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 2, newQuality);
    }

    @DisplayName("Increases its quality by two when sell in is lower than 10 and higher than 5")
    @Test
    void givenSellInLowerThan10AndHigherThan5WhenUpdateThenItsQualityIncreasesByTwo() {
      // Arrange
      int quality = 20;
      int sellIn = 8;
      Item item = new Item(BACKSTAGE_PASS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 2, newQuality);
    }

    @DisplayName("Increases its quality by three when sell in is 5")
    @Test
    void givenSellInIs5WhenUpdateThenItsQualityIncreasesByThree() {
      // Arrange
      int quality = 20;
      int sellIn = 5;
      Item item = new Item(BACKSTAGE_PASS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 3, newQuality);
    }

    @DisplayName("Increases its quality by three when sell in is one")
    @Test
    void givenSellInIs1WhenUpdateThenItsQualityIncreasesByThree() {
      // Arrange
      int quality = 20;
      int sellIn = 1;
      Item item = new Item(BACKSTAGE_PASS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality + 3, newQuality);
    }

    @DisplayName("Its quality is zero when sell in is zero")
    @Test
    void givenSellInIsZeroWhenUpdateThenItsQualityIsZero() {
      // Arrange
      int quality = 20;
      int sellIn = 0;
      Item item = new Item(BACKSTAGE_PASS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(0, newQuality);
    }
  }

  @Nested
  @DisplayName("When update Sulfuras")
  class TestCasesForSulfuras {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    @DisplayName("Its quality does not decrease")
    @Test
    void whenUpdateThenItsQualityRemainsTheSame() {
      // Arrange
      int quality = 80;
      int sellIn = 4;
      Item item = new Item(SULFURAS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality, newQuality);
    }

    @DisplayName("Its sell in date does not decrease")
    @Test
    void whenUpdateThenItsSellInValueRemainsTheSame() {
      // Arrange
      int quality = 80;
      int sellIn = 4;
      Item item = new Item(SULFURAS, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newSellIn = item.sellIn;
      assertEquals(sellIn, newSellIn);
    }
  }

  @Nested
  @DisplayName("When update a regular item")
  class TestCasesForRegularItems {
    public static final String REGULAR_ITEM = "An Item";

    @DisplayName("Decreases its quality by one if sellIn is greater than zero")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityDecreasesByOne() {
      // Arrange
      int quality = 10;
      int sellIn = 2;
      Item item = new Item(REGULAR_ITEM, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality - 1, newQuality);
    }

    @DisplayName("Decreases its quality by two if sellIn is zero")
    @Test
    void givenAQualityValueAndSellInIsZeroWhenUpdateThenQualityDecreasesByTwo() {
      // Arrange
      int quality = 10;
      int sellIn = 0;
      Item item = new Item(REGULAR_ITEM, sellIn, quality);
      GildedRose gildedRose = new GildedRose(new Item[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality - 2, newQuality);
    }
  }
}
