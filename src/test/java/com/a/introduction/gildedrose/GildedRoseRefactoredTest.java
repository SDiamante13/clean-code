package com.a.introduction.gildedrose;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseRefactoredTest {
	
	private static final int POSITIVE_SELLIN_LESS_THAN_5 = 2;
	private static final int SELLIN_BETWEEN_5_AND_10 = 7;
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	private static final int SELLIN_GREATER_THAN_10 = 15;
	private static final int MINIMUM_QUALITY = 0;

	private static final String AGED_BRIE = "Aged Brie";
	private static final int UNEXPIRED_SELLIN = 4;
	private static final String[] fields = {"name", "sellIn", "quality"};
	private static final int MAX_QUALITY = 50;

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

	@Test
	void updateQuality_increasesQualityBy1_forAgedBrie() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, UNEXPIRED_SELLIN, DEFAULT_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(AGED_BRIE, UNEXPIRED_SELLIN - 1, DEFAULT_QUALITY + 1));
	}

	@Test
	void updateQuality_increasesQualityBy2_forExpiredAgedBrie() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, EXPIRED_SELLIN, DEFAULT_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(AGED_BRIE, EXPIRED_SELLIN - 1, DEFAULT_QUALITY + 2));
	}

	@Test
	void updateQuality_DoesNotIncreaseQuality_forAgedBrieWithMaximumQuality() {
		GildedRose app = createGildedRoseWithOneItem(AGED_BRIE, UNEXPIRED_SELLIN, MAX_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(AGED_BRIE, UNEXPIRED_SELLIN - 1, MAX_QUALITY));
	}
	
	@Test
	void updateQuality_increasesBy1_forBackstagePassesWithMoreThan10DaysLeft() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10, DEFAULT_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(BACKSTAGE_PASSES, SELLIN_GREATER_THAN_10 - 1, DEFAULT_QUALITY + 1));
	}

	@Test
	void updateQuality_increasesBy2_forBackstagePassesWithBetween5And10DaysLeft() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, SELLIN_BETWEEN_5_AND_10, DEFAULT_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(BACKSTAGE_PASSES, SELLIN_BETWEEN_5_AND_10 - 1, DEFAULT_QUALITY + 2));
	}

	@Test
	void updateQuality_increasesByOne_forBackstagePassesWithLessThan5DaysLeft() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, POSITIVE_SELLIN_LESS_THAN_5, DEFAULT_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(BACKSTAGE_PASSES, POSITIVE_SELLIN_LESS_THAN_5 - 1, DEFAULT_QUALITY + 3));
	}

	@Test
	void updateQuality_increasesByOne_forBackstagePassesWithMoreThan10DaysLeft() {
		GildedRose app = createGildedRoseWithOneItem(BACKSTAGE_PASSES, EXPIRED_SELLIN, DEFAULT_QUALITY);

		app.updateQuality();

		assertThat(app.items)
				.extracting(fields)
				.contains(tuple(BACKSTAGE_PASSES, EXPIRED_SELLIN - 1, MINIMUM_QUALITY));
	}


	private GildedRose createGildedRoseWithOneItem(String itemType, int sellin, int quality) {
		Item item = new Item(itemType, sellin, quality);
		Item[] items = new Item[] { item };
		GildedRose app = new GildedRose(items);
		return app;
	}

}