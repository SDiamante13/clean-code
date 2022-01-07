package com.a.introduction.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GildedRoseADefaultItemTest {

    @Test
    void updateQuality_qualityAndSellInShouldDecreaseByOne_whenItemIsNotExpired() {
        GildedRose app = new GildedRose(new Item[]{
                new Item("DEFAULT_ITEM", 15, 3)
        });

        app.updateQuality();

        assertThat(app.items)
                .extracting("name", "sellIn", "quality")
                .contains(tuple("DEFAULT_ITEM", 14, 2));
    }

    @Test
    void updateQuality_qualityShouldDecreaseByTwo_whenItemIsExpired() {
        GildedRose app = new GildedRose(new Item[]{
                new Item("DEFAULT_ITEM", -1, 3)
        });

        app.updateQuality();

        assertThat(app.items)
                .extracting("name", "sellIn", "quality")
                .contains(tuple("DEFAULT_ITEM", -2, 1));
    }
}