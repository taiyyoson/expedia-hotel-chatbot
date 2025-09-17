package hotelapp.datatypes;

/**
 * Represents the frequency of a word within a specific review.
 * Implements Comparable to enable sorting by frequency and review metadata.
 */
public class WordFrequency implements Comparable<WordFrequency> {
    private Review review;
    private int frequency;

    /**
     * Constructs a WordFrequency object with the specified review and frequency.
     *
     * @param review the review containing the word
     * @param frequency the number of times the word appears in the review
     */
    public WordFrequency(Review review, int frequency) {
        this.review = review;
        this.frequency = frequency;
    }

    /**
     * Compares word frequencies by frequency (descending), then by review date (descending), then by review ID.
     *
     * @param other the WordFrequency to compare to
     * @return negative if this has higher frequency, positive if lower, 0 if equal
     */
    @Override
    public int compareTo(WordFrequency other) {

        if (this.frequency < other.frequency) {
            return 1;
        }
        if (this.frequency > other.frequency) {
            return -1;
        }

        int dateCompare = other.review.getReviewDate().compareTo(this.review.getReviewDate());
        if (dateCompare != 0) {
            return dateCompare;
        }
        return this.review.getReviewId().compareTo(other.review.getReviewId());
    }

    /**
     * Returns a string representation showing review ID and frequency.
     *
     * @return formatted string with review ID and frequency
     */
    @Override
    public String toString() {
        return review.getReviewId() + " " + frequency;
    }

    /**
     * Returns the review associated with this word frequency.
     *
     * @return the review containing the word
     */
    public Review getReview() { return review; }

    /**
     * Returns the frequency of the word in the review.
     *
     * @return the number of times the word appears
     */
    public int getFrequency() { return frequency; }
}