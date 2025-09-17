package hotelapp.jsonParsing;

import com.google.gson.*;
import hotelapp.datatypes.Review;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for parsing review data from JSON files into Review objects.
 * Handles directory traversal and multiple review files.
 */
public class ParseReview {
    
    /**
     * Loads and parses review data from JSON files in the specified directory.
     *
     * @param reviewsPath the path to the directory containing review JSON files
     * @return list of Review objects parsed from all JSON files in the directory
     */
    public List<Review> loadReviews(String reviewsPath) {
        List<Review> allReviews = new ArrayList<>();
        Path reviewsDir = Paths.get(reviewsPath);
        
        try {
            traverseWithDirectoryStream(reviewsDir, allReviews);
        } catch (IOException e) {
            System.err.println("Error traversing reviews directory: " + e);
        }
        
        return allReviews;
    }
    
    private void traverseWithDirectoryStream(Path directory, List<Review> allReviews) throws IOException {
        if (!Files.exists(directory) || !Files.isDirectory(directory)) {
            return; // base case, not a directory or file path does not exist
        }
        
        try (DirectoryStream<Path> pathsInDir = Files.newDirectoryStream(directory)) {
            for (Path fPath : pathsInDir) {
                if (Files.isDirectory(fPath)) {
                    traverseWithDirectoryStream(fPath, allReviews);
                } else if (fPath.toString().endsWith(".json")) {
                    parseReviewFile(fPath, allReviews);
                }
            }
        }
    }
    
    private void parseReviewFile(Path file, List<Review> allReviews) throws IOException {
        try (FileReader reader = new FileReader(file.toFile())) {
            JsonObject root = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray reviewArr = root.getAsJsonObject("reviewDetails").getAsJsonObject("reviewCollection").getAsJsonArray("review");
           
            if (reviewArr != null) {
                for (JsonElement element : reviewArr) {
                    Review review = parseReview(element.getAsJsonObject());
                    if (review != null) {
                        allReviews.add(review);
                    }
                }
            }
        } 
    }
    
    private Review parseReview(JsonObject reviewObj) {
            String hotelId = reviewObj.get("hotelId").getAsString();
            String reviewId = reviewObj.get("reviewId").getAsString();
            double rating = reviewObj.get("ratingOverall").getAsDouble();
            String title = reviewObj.get("title").getAsString();
            String reviewText = reviewObj.get("reviewText").getAsString();
            String userNickname = reviewObj.get("userNickname").getAsString();
            
            String dateString = reviewObj.get("reviewSubmissionDate").getAsString();
            LocalDate reviewDate = LocalDate.parse(dateString);
            
            return new Review(hotelId, reviewId, reviewText, rating, title, userNickname, reviewDate);
    }
}