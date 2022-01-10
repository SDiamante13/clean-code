package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GildedRoseADefaultItemTest {

    private static final String NORMAL_ITEM = "NORMAL_ITEM";
    public static final int NOT_EXPIRED_SELLIN = 15;
    public static final int EXPIRED_SELLIN = -1;
    public static final int DEFAULT_QUALITY = 3;

    @Test
    void updateQuality_qualityShouldDecreaseByOne_whenNormalItemIsNotExpired() {
        GildedRose app = createGildedRoseWithOneItem(NORMAL_ITEM, NOT_EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        assertThat(app.items)
                .extracting("name", "sellIn", "quality")
                .contains(tuple(NORMAL_ITEM, NOT_EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 1));
    }

    @Test
    void updateQuality_qualityShouldDecreaseByTwo_whenNormalItemIsExpired() {
        GildedRose app = createGildedRoseWithOneItem(NORMAL_ITEM, EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        assertThat(app.items)
                .extracting("name", "sellIn", "quality")
                .contains(tuple(NORMAL_ITEM, EXPIRED_SELLIN - 1, DEFAULT_QUALITY - 2));
    }

    private GildedRose createGildedRoseWithOneItem(String itemName, int sellIn, int quality) {
        return new GildedRose(new Item[]{
                new Item(itemName, sellIn, quality)
        });
    }
}