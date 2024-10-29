package com.gildedrose.refactoredV3.item;

public abstract class UpdatableItem extends Item{
    public static final int MAX_QUALITY = 50;

    protected UpdatableItem(String name, int sellIn, int quality){
        super(name, sellIn, quality);
    }

    public abstract void update();

    protected void increaseQuality() {
        if (quality < MAX_QUALITY) {
            quality = quality + 1;
        }
    }

    protected void decreaseQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    protected void updateExpiredQuality() {
        if (hasExpired()) {
            decreaseQuality();
        }
    }

    protected boolean hasExpired() {
        return sellIn < 0;
    }

    protected void updateSellIn() {
        sellIn = sellIn - 1;
    }

}
