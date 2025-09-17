package hotelapp;

import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

import hotelapp.datatypes.Hotel;
import hotelapp.datatypes.Review;
import hotelapp.datatypes.WordFrequency;
import hotelapp.jsonParsing.*;

/**
 * Core data management and search functionality for hotel and review data.
 * Handles data loading, indexing, and query processing for hotels, reviews, and word searches.
 */
public class DataSearchMain {
    
    //storing list of hotels and reviews
    private List<Hotel> hotels;
    private List<Review> reviews;

    //Data Structures to handle find operations
    private HashMap<String, Hotel> hotelMap; 
    private HashMap<String, TreeSet<Review>> reviewsByHotel;
    private HashMap<String, TreeSet<WordFrequency>> wordIndex;

    /**
     * Constructs a DataSearchMain instance and loads hotels and reviews from specified paths.
     *
     * @param hotelPath path to the hotel JSON file
     * @param reviewsPath path to the directory containing review JSON files
     */
    public DataSearchMain(String hotelPath, String reviewsPath) {
        this.hotelMap = new HashMap<>();
        this.reviewsByHotel = new HashMap<>();
        this.wordIndex = new HashMap<>();

        this.hotels = loadHotels(hotelPath);
        this.reviews = loadReviews(reviewsPath);
        
    }

    private List<Hotel> loadHotels(String hotelPath) {
        ParseHotel hotelParser = new ParseHotel(); 
        return hotelParser.parseHotelJson(hotelPath);
    }

    private List<Review> loadReviews(String reviewsPath) {
        ParseReview reviewParser = new ParseReview();
        return reviewParser.loadReviews(reviewsPath);
    }

    /**
     * Builds optimized data structures for efficient searching.
     * Creates hotel maps, review maps grouped by hotel, and word frequency indices.
     */
    public void buildDataStructures() {
        //building hotelMap
        for (Hotel hotel : hotels) { 
            hotelMap.put(hotel.getId(), hotel);
        }

        //building reviewsByHotel
        for (Review review : reviews) {
            String hotelId = review.getHotelId();
            reviewsByHotel.putIfAbsent(hotelId, new TreeSet<>());
            reviewsByHotel.get(hotelId).add(review);
        }

        //building wordIndex
        String WORD_REGEX = "[,\\.\\!\\?\\s]+";
        for (Review review: reviews) {
            String reviewText = review.getReviewText().toLowerCase();
            String[] words = reviewText.split(WORD_REGEX);
            HashMap<String, Integer> wordMapPerReview = new HashMap<>();
            for (String word : words) {
                if (word != null && word.length() > 0) {
                    if (wordMapPerReview.containsKey(word)) {
                        wordMapPerReview.put(word, wordMapPerReview.get(word) + 1);
                    } else {
                        wordMapPerReview.put(word, 1);
                    }
                }
            }

            for (String word : wordMapPerReview.keySet()) {
                WordFrequency wordFreq = new WordFrequency(review, wordMapPerReview.get(word));

                //same logic as above
                if (wordIndex.containsKey(word)) {
                    wordIndex.get(word).add(wordFreq);
                } else {
                    TreeSet<WordFrequency> newWordSet = new TreeSet<>();
                    newWordSet.add(wordFreq);
                    wordIndex.put(word, newWordSet);
                }
                
            }

        }
    }


    /**
     * Processes search queries and returns formatted results.
     * Supports findHotel, findReviews, and findWord query types.
     *
     * @param queryInput the search query string (e.g., "findHotel 25622")
     * @return formatted search results as a string
     * @throws IllegalArgumentException if query is malformed or contains invalid parameters
     */
    public String queryProcessor (String queryInput) {
        if (queryInput == null || queryInput.length() == 0) {
            return "";
        }

        String[] query = queryInput.split(" ", 2);
        String queryType = query[0];

        // Check if parameter is missing
        if (query.length < 2 || query[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Missing parameter for " + queryType);
        }

        String queryParam = query[1];
        switch (queryType) {
            case "findHotel":
                return findHotel(queryParam);
            case "findReviews":
                return findReviews(queryParam);
            case "findWord":
                return findWord(queryParam.toLowerCase());
            default:
                return "Invalid query type";
        }
    }

    private String findHotel(String hotelId) {
        if (hotelMap.containsKey(hotelId)) {
            return hotelMap.get(hotelId).toString();
        } else {
            throw new IllegalArgumentException("Invalid hotel ID: " + hotelId);
        }
    }

    private String findReviews(String hotelId) {
        StringBuilder sb = new StringBuilder();
        if (!reviewsByHotel.containsKey(hotelId)) {
            throw new IllegalArgumentException("Invalid hotel ID: " + hotelId);
        }
        for (Review review : reviewsByHotel.get(hotelId)) {
            sb.append(review.toString()).append(System.lineSeparator()).append("--------------------").append(System.lineSeparator());
        }

        return sb.toString();
    }

    private String findWord (String word) {
        StringBuilder sb = new StringBuilder();

        if(!wordIndex.containsKey(word)) {
            return word + " not found";
        }

        for (WordFrequency wf : wordIndex.get(word)) {
            sb.append(wf.getFrequency()).append(System.lineSeparator());
            sb.append(wf.getReview().toString()).append(System.lineSeparator());   
            sb.append("--------------------").append(System.lineSeparator());
        }
        return sb.toString();
    }
}


