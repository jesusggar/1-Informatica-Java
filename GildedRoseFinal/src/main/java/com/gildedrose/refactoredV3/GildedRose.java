package com.gildedrose.refactoredV3;

import com.gildedrose.refactoredV3.item.Item;
import com.gildedrose.refactoredV3.item.UpdatableItem;

class GildedRose {
  public static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
  public static final String AGED_BRIE = "Aged Brie";
  public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  public static final int MAX_QUALITY = 50;
  UpdatableItem[] items;

  public GildedRose(UpdatableItem[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (UpdatableItem item : items) {
      updateItem(item);
    }
  }

  private void updateItem(UpdatableItem item) {
    item.update(); //Delego en el propio item su actualización
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
