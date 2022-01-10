package com.a.introduction.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class GildedRoseBAgedBrieTest {
    private static final String AGED_BRIE = "Aged Brie";
    private static final int UNEXPIRED_SELLIN = 4;
    private static final int EXPIRED_SELLIN = -1;
    private static final int DEFAULT_QUALITY = 3;
    private static final String[] fields = {"name", "sellIn", "quality"};
    private static final int MAX_QUALITY = 50;

    @Test
    void updateQuality_increasesQualityBy1_forAgedBrie() {
        GildedRose app = createGildedRoseWithOneAgedBrie(AGED_BRIE, UNEXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        assertThat(app.items)
                .extracting(fields)
                .contains(tuple(AGED_BRIE, UNEXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1));
    }

    @Test
    void updateQuality_increasesQualityBy2_forExpiredAgedBrie() {
        GildedRose app = createGildedRoseWithOneAgedBrie(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);

        app.updateQuality();

        assertThat(app.items)
                .extracting(fields)
                .contains(tuple(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2));
    }

    @Test
    void updateQuality_DoesNotIncreaseQuality_forAgedBrieWithMaximumQuality() {
        GildedRose app = createGildedRoseWithOneAgedBrie(AGED_BRIE, UNEXPIRED_SELLIN, MAX_QUALITY);

        app.updateQuality();

        assertThat(app.items)
                .extracting(fields)
                .contains(tuple(AGED_BRIE, UNEXPIRED_SELLIN - 1, MAX_QUALITY));
    }

    private GildedRose createGildedRoseWithOneAgedBrie(String AGED_BRIE, int UNEXPIRED_SELLIN, int DEFAULT_QUALITY) {
        return new GildedRose(new Item[]{new Item(AGED_BRIE, UNEXPIRED_SELLIN, DEFAULT_QUALITY)});
    }
}
