package com.gildedrose.refactoredV2;

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
    if (isSulfuras(item)) {
      // do nothing
    } else if (isAgedBrie(item)) {
      increaseQuality(item);
      updateSellIn(item);
      if (hasExpired(item)) {
        increaseQuality(item);
      }
    } else if (isBackstagePass(item)) {
      increaseQuality(item);
      if (item.sellIn < 11) {
        increaseQuality(item);
      }
      if (item.sellIn < 6) {
        increaseQuality(item);
      }

      updateSellIn(item);

      if (hasExpired(item)) {
        item.quality = 0;
      }
    } else {
      decreaseQuality(item);
      updateSellIn(item);
      updateExpiredQuality(item);
    }
  }

  private void updateExpiredQuality(Item item) {
    if (hasExpired(item)) {
      decreaseQuality(item);
    }
  }

  private void updateSellIn(Item item) {
    item.sellIn = item.sellIn - 1;
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
