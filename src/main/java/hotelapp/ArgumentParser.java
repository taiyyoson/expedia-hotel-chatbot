package hotelapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Helper class for parsing command line arguments in key-value format.
 * Handles arguments like: -hotels path/to/hotels.json -reviews path/to/reviews
 */
public class ArgumentParser {
    private Map<String, String> arguments;

    /**
     * Constructs an ArgumentParser and parses the given command line arguments.
     * @param args command line arguments in -key value format
     */
    public ArgumentParser(String[] args) {
        this.arguments = new HashMap<>();
        parseArguments(args);
    }

    /**
     * Parses command line arguments into key-value pairs and stores them internally.
     * @param args command line arguments to parse
     */
    private void parseArguments(String[] args) {
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("-")) {
                arguments.put(args[i], args[i + 1]);
            }
        }
    }

    /**
     * Gets the hotels file path from the parsed arguments.
     * @return path to the hotels JSON file as specified by the -hotels argument,
     *         or null if the -hotels argument was not provided
     */
    public String getHotelsPath() {
        return arguments.get("-hotels");
    }

    /**
     * Gets the reviews directory path from the parsed arguments.
     *
     * @return path to the reviews directory as specified by the -reviews argument,
     *         or null if the -reviews argument was not provided
     */
    public String getReviewsPath() {
        return arguments.get("-reviews");
    }

    /**
     * Checks if both required arguments are present.
     *
     * @return true if both -hotels and -reviews arguments are present, false otherwise
     */
    public boolean hasRequiredArguments() {
        return arguments.containsKey("-hotels") && arguments.containsKey("-reviews");
    }
}