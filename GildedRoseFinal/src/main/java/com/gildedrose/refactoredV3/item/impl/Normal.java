package com.gildedrose.refactoredV3.item.impl;

import com.gildedrose.refactoredV3.item.UpdatableItem;

public class Normal extends UpdatableItem {
    public Normal(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void update() {
        decreaseQuality();
        updateSellIn();
        updateExpiredQuality();
    }
}
