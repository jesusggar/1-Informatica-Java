package com.gildedrose.refactoredV3.item.impl;

import com.gildedrose.refactoredV3.item.UpdatableItem;

public class BackstagePass extends UpdatableItem {
    public BackstagePass(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    public void update() {
        increaseQuality();
        if (sellIn < 11) {
            increaseQuality();
        }
        if (sellIn < 6) {
            increaseQuality();
        }

        updateSellIn();

        if (hasExpired()) {
            quality = 0;
        }
    }
}
