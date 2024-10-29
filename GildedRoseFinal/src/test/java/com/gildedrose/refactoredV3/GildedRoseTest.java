/*package com.gildedrose.refactoredV3;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.refactoredV3.item.*;
import com.gildedrose.refactoredV3.item.impl.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName(("The Gilded Rose"))
class GildedRoseTest {

  @Nested
  @DisplayName("When update Aged Brie")
  class TestCasesForBrie {
    @DisplayName("Increases its quality by one if sellIn is greater than zero")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityIncreasesByOne() {
      // Arrange
      int quality = 0;
      int sellIn = 2;
      UpdatableItem item = new AgedBrie(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[]{item});

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
      UpdatableItem item = new AgedBrie(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[]{item});

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
      UpdatableItem item = new AgedBrie(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new AgedBrie(sellIn, quality) ;
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new AgedBrie(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new AgedBrie(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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

    @DisplayName("Increases its quality by one when sell in is greater than 10")
    @Test
    void givenSellInIsGreaterThan10WhenUpdateThenItsQualityIncreasesByOne() {
      // Arrange
      int quality = 20;
      int sellIn = 11;
      UpdatableItem item = new BackstagePass(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new BackstagePass(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new BackstagePass(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new BackstagePass(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new BackstagePass(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new BackstagePass(sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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

    @DisplayName("Its quality does not decrease")
    @Test
    void whenUpdateThenItsQualityRemainsTheSame() {
      // Arrange
      int quality = 80;
      int sellIn = 4;
      UpdatableItem item = new Sulfuras();
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      UpdatableItem item = new Sulfuras();
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      int previousSellIn = item.sellIn ;
      gildedRose.updateQuality();

      // Assert
      int newSellIn = item.sellIn;
      assertEquals(previousSellIn, newSellIn);
    }
  }

  @Nested
  @DisplayName("When update a normal item")
  class TestCasesForRegularItems {

    @DisplayName("Decreases its quality by one if sellIn is greater than zero")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityDecreasesByOne() {
      // Arrange
      int quality = 10;
      int sellIn = 2;
      Normal item = new Normal("Item name", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

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
      Normal item = new Normal("Item name", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality - 2, newQuality);
    }
  }

  @Nested
  @DisplayName("When update a conjured item")
  class TestCasesForConjuredItems {

    @DisplayName("Decreases its quality by two if sellIn is greater than one")
    @Test
    void givenAQualityValueAndPositiveSellInWhenUpdateThenQualityDecreasesByOne() {
      // Arrange
      int quality = 10;
      int sellIn = 2;
      Conjured item = new Conjured("Item name", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality - 2, newQuality);
    }

    @DisplayName("Decreases its quality by four if sellIn is zero")
    @Test
    void givenAQualityValueAndSellInIsZeroWhenUpdateThenQualityDecreasesByFour() {
      // Arrange
      int quality = 10;
      int sellIn = 0;
      Conjured item = new Conjured("Conjured item", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(quality - 4, newQuality);
    }

    @DisplayName("Decreases its quality to zero if its value is one")
    @Test
    void givenAQualityValueOfTwoWhenUpdateThenQualityDecreasesToOne() {
      // Arrange
      int quality = 1;
      int sellIn = 3;
      Conjured item = new Conjured("Conjured item", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(0, newQuality);
    }

    @DisplayName("Decreases its quality to zero if its value is two")
    @Test
    void givenAQualityValueOfTwoWhenUpdateThenQualityDecreasesToZero() {
      // Arrange
      int quality = 2;
      int sellIn = 3;
      Conjured item = new Conjured("Conjured item", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(0, newQuality);
    }

    @DisplayName("If quality is one and sellIn is zero, quality value is zero")
    @Test
    void givenAQualityValueOfOneAndSellInIsZeroWhenUpdateThenQualityIsZero() {
      // Arrange
      int quality = 1;
      int sellIn = 0;
      Conjured item = new Conjured("Conjured item", sellIn, quality);
      GildedRose gildedRose = new GildedRose(new UpdatableItem[] {item});

      // Act
      gildedRose.updateQuality();

      // Assert
      int newQuality = item.quality;
      assertEquals(0, newQuality);
    }

  }
}
*/