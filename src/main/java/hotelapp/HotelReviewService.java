package hotelapp;

/**
 * Main service class for loading and searching hotel and review data.
 * Provides functionality to load data from JSON files and process search queries.
 */
public class HotelReviewService {
    // Do NOT store maps of hotels/reviews directly here.
    // Instead, create other classes to store hotel and review data in maps
    // This keeps core logic ("domain") separate from "edges" (I/O, parsing).
    private DataSearchMain dataSearchMain;

    /**
     * Constructs a new HotelReviewService with no data loaded.
     */
    public HotelReviewService() {
        this.dataSearchMain = null;
    }

    /**
     * Parse given arguments that contain paths to the hotel file and the reviews folder,
     * and load hotel and review data into the corresponding data structures.
     *
     * - Do
     * - Do NOT store t NOT do the actual parsing here.he data structures here.
     * Instead:
     *   - Write separate classes; this class should just delegate: "load hotels from X", "load reviews from Y"
     *
     * @param args Arguments can be given in the following format:
     *  -hotels pathToHotelFile -reviews pathToReviewsFolder
     *   or
     *  -reviews pathToReviewsFolder -hotels pathToHotelFile
     */
    public void loadData(String[] args) {
        // FILL IN CODE: delegate loading/parsing to helper classes

        ArgumentParser argParse = new ArgumentParser(args);

        if (!argParse.hasRequiredArguments()) {
            System.err.println("Missing required arguments! Missing -hotels & -reviews file/directory paths");
            System.exit(1);
        }

        String hotelsPath = argParse.getHotelsPath(); // getting paths
        String reviewsPath = argParse.getReviewsPath();

        System.out.println("Hotels path: " + hotelsPath);
        System.out.println("Reviews path: " + reviewsPath);

        // Parsing data into built data structures
        dataSearchMain = new DataSearchMain(hotelsPath, reviewsPath);
        dataSearchMain.buildDataStructures();
        System.out.println("Data loaded successfully.");
    }

    /**
     * Process a given query and return the result as a string.
     *
     * Queries can be in one of the following formats:
     *   - "findHotel hotelId"
     *   - "findReviews hotelId"
     *   - "findWord word"
     *
     * - Do NOT write all search logic here.
     * - Delegate search to other classes
     *
     * @param query String query in one of the formats above
     * @return String result of the query
     */
    public String processQuery(String query) {
        // FILL IN CODE:  parse the query and call appropriate classes.
        return dataSearchMain.queryProcessor(query);
    }

    /**
     * Main method to demonstrate the hotel review service functionality.
     * Loads hotel and review data from command line arguments and executes a sample query.
     *
     * @param args command line arguments in format: -hotels pathToHotelFile -reviews pathToReviewsFolder
     *             or -reviews pathToReviewsFolder -hotels pathToHotelFile
     */
    public static void main(String[] args) {
        // They can be specified in top menu in IntelliJ: Run->Edit Configuration->Program Arguments
        // Note: Future projects will add more arguments. Your code should handle multiple arguments in the -key value format.
        // Arguments may appear in any order, but each -key is always followed by its value.
        HotelReviewService service = new HotelReviewService();
        try {
            service.loadData(args);
            System.out.println(service.processQuery("findHotel 10323"));
        }
        catch (Exception e) {
            System.out.println("Could not load data or process a query.");
        }
    }
}

