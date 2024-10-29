package com.gildedrose.refactored;

class GildedRose {
  public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
  public static final String AGED_BRIE = "Aged Brie";
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  public static final int MAX_QUALITY = 50;
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      updateItem(item);
    }
  }

  private void updateItem(Item item) {
    if (!isSulfuras(item)) {
      updateRegularQuality(item);
      updateSellIn(item);
      updateExpiredQuality(item);
    }
  }

  private void updateRegularQuality(Item item) {
    if (isAgedBrie(item)) {
      increaseQuality(item);
    } else if (isBackstagePass(item)) {
      increaseQuality(item);
      if (item.sellIn < 11) {
        increaseQuality(item);
      }
      if (item.sellIn < 6) {
        increaseQuality(item);
      }
    } else {
      decreaseQuality(item);
    }
  }

  private void updateExpiredQuality(Item item) {
    if (hasExpired(item)) {
      if (isAgedBrie(item)) {
        increaseQuality(item);
      } else if (isBackstagePass(item)) {
        item.quality = 0;
      } else {
        decreaseQuality(item);
      }
    }
  }

  private void updateSellIn(Item item) {
    item.sellIn = item.sellIn - 1;
  }

  private boolean isNormal(Item item) {
    return !isAgedBrie(item) && !isBackstagePass(item) && !isSulfuras(item);
  }

  private boolean hasExpired(Item item) {
    return item.sellIn < 0;
  }

  private boolean isSulfuras(Item item) {
    return item.name.equals(SULFURAS);
  }

  private boolean isBackstagePass(Item item) {
    return item.name.equals(BACKSTAGE_PASS);
  }

  private boolean isAgedBrie(Item item) {
    return item.name.equals(AGED_BRIE);
  }

  private void increaseQuality(Item item) {
    if (item.quality < MAX_QUALITY) {
      item.quality = item.quality + 1;
    }
  }

  private void decreaseQuality(Item item) {
    if (item.quality > 0) {
      item.quality = item.quality - 1;
    }
  }
}
