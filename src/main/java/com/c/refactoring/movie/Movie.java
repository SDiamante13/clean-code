package com.c.refactoring.movie;

import com.c.refactoring.StringUtils;

import static java.lang.Integer.parseInt;

public class Movie {

    public static final int B_RATING_MINIMUM_VALUE = 1;
    public static final int B_RATING_MAXIMUM_VALUE = 4;
    private String rating;

    public Movie(String rating) {
        this.rating = rating;
    }


    /*Axx or By
    Where x represents any digit between 0 and 9, and y represents 
    any digit between 1 and 4*/
    public boolean isValidRating() {
        if (rating == null) return false;

        if (isFirstLetter("B") &&
                isRatingOfLength(2) &&
                areNumbersPresentInRange(1, 2) &&
                isNumberRatingValid(parseInt(this.rating.substring(1, 2)))
        ) {
            return true;
        }

        if (isFirstLetter("A") && isRatingOfLength(3) && areNumbersPresentInRange(1, 3)) {
            return true;
        }
        return false;
    }

    private boolean areNumbersPresentInRange(int lowerBound, int higherBound) {
        return StringUtils.isNumeric(this.rating.substring(lowerBound, higherBound));
    }

    private boolean isRatingOfLength(int requiredLength) {
        return this.rating.length() == requiredLength;
    }

    private boolean isFirstLetter(String expectedFirstLetter) {
        return this.rating.substring(0, 1).equalsIgnoreCase(expectedFirstLetter);
    }

    private boolean isNumberRatingValid(int numberRating) {
        return numberRating >= B_RATING_MINIMUM_VALUE && numberRating <= B_RATING_MAXIMUM_VALUE;
    }
}
