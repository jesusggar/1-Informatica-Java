package com.gildedrose.refactoredV3.item.impl;

import com.gildedrose.refactoredV3.item.UpdatableItem;

public class Conjured extends UpdatableItem {
    public Conjured(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        decreaseQuality();
        updateSellIn();
        updateExpiredQuality();
    }

    @Override
    protected void decreaseQuality() {
        if (quality > 1) {
            quality = quality - 2;
        } else {
            quality = 0;
        }
    }
}
