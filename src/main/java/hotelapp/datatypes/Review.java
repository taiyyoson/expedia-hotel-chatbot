package hotelapp.datatypes;

import java.time.LocalDate;

/**
 * Represents a hotel review with rating, text content, and metadata.
 * Implements Comparable to enable sorting by date and review ID.
 */
public class Review implements Comparable<Review> {
    private String hotelId;
    private String reviewId;
    private String reviewText;
    private double overallRating;
    private String reviewTitle;
    private String userNickname;
    private LocalDate reviewDate;


    /**
     * Constructs a Review object with the specified details.
     *
     * @param hotelId the ID of the hotel being reviewed
     * @param reviewId the unique identifier of the review
     * @param reviewText the text content of the review
     * @param overallRating the numerical rating given to the hotel
     * @param reviewTitle the title of the review
     * @param userNickname the nickname of the user who wrote the review
     * @param reviewDate the date when the review was submitted
     */
    public Review(String hotelId, String reviewId, String reviewText, double overallRating, String reviewTitle, String userNickname, LocalDate reviewDate) {
        this.hotelId = hotelId;
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.overallRating = overallRating;
        this.reviewTitle = reviewTitle;
        this.userNickname = userNickname;
        this.reviewDate = reviewDate;
    }

    /**
     * Returns the review ID.
     *
     * @return the unique identifier of the review
     */
    public String getReviewId() {
        return reviewId;
    }

    /**
     * Returns the hotel ID.
     *
     * @return the ID of the hotel being reviewed
     */
    public String getHotelId() {
        return hotelId;
    }

    /**
     * Returns the review text.
     *
     * @return the text content of the review
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Returns the overall rating.
     *
     * @return the numerical rating given to the hotel
     */
    public double getOverallRating() {
        return overallRating;
    }

    /**
     * Returns the review date.
     *
     * @return the date when the review was submitted
     */
    public LocalDate getReviewDate() {
        return reviewDate;
    }
    /**
     * Compares reviews by date (most recent first), then by review ID.
     *
     * @param other the review to compare to
     * @return negative if this review is newer, positive if older, 0 if equal
     */
    @Override
    public int compareTo(Review other) {
        int dateCompare = other.reviewDate.compareTo(this.reviewDate);
        if (dateCompare != 0) { return dateCompare; }
        return this.reviewId.compareTo(other.reviewId);
    }

    /**
     * Returns a formatted string representation of the review.
     *
     * @return formatted review details including hotel ID, rating, title, text, user, and date
     */
    @Override
    public String toString() {
        return "hotelId = " + hotelId + System.lineSeparator() +
               "reviewId = " + reviewId + System.lineSeparator() +
               "averageRating = " + overallRating + System.lineSeparator() +
               "title = " + reviewTitle + System.lineSeparator() +
               "reviewText = " + reviewText + System.lineSeparator() +
               "userNickname = " + userNickname + System.lineSeparator() +
               "submissionDate = " + reviewDate;
    }



}
