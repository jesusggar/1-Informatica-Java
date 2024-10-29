package com.gildedrose.refactoredV3.item.impl;

import com.gildedrose.refactoredV3.item.UpdatableItem;

public class AgedBrie extends UpdatableItem {
    public AgedBrie(int sellIn, int quality) {
        super("AgedBrie", sellIn, quality);
    }

    @Override
    public void update() {
        increaseQuality();
        updateSellIn();
        if (hasExpired()) {
            increaseQuality();
        }
    }
}
