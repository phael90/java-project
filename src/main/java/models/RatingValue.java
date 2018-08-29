package models;

public enum RatingValue {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int ratingNumber;

    RatingValue(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }
}
