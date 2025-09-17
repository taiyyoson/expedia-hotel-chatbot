# Hotel Review Search System

A Java-based hotel and review search application that allows users to load hotel data and review information from JSON files, then query the data using various search commands.

## 🏨 Features

- **Hotel Search**: Find hotels by ID with detailed information including name, location, and coordinates
- **Review Search**: Search for reviews by hotel ID with sorting by date (newest first)
- **Word Search**: Find reviews containing specific words or phrases
- **Data Loading**: Load hotel and review data from JSON files and directories
- **Flexible Arguments**: Support for command-line arguments in any order

## 📋 Prerequisites

- Java 24 or higher
- Maven 3.6+
- JSON dataset files (hotels and reviews)

## 🚀 Getting Started

### Building the Project

```bash
mvn compile
```

### Running Tests

```bash
mvn test
```

### Running the Application

```bash
mvn exec:java -Dexec.mainClass="hotelapp.HotelReviewService" -Dexec.args="-hotels dataset/hotels/hotels.json -reviews dataset/reviews"
```

Or with tiny dataset for testing:
```bash
mvn exec:java -Dexec.mainClass="hotelapp.HotelReviewService" -Dexec.args="-hotels dataset/hotelsTiny/hotel1.json -reviews dataset/reviewsTiny"
```

## 💻 Usage

### Command Line Arguments

The application accepts the following arguments in any order:

- `-hotels <path_to_hotel_file>`: Path to JSON file containing hotel data
- `-reviews <path_to_reviews_directory>`: Path to directory containing review JSON files

### Query Commands

Once data is loaded, you can process queries using these formats:

1. **Find Hotel by ID**:
   ```
   findHotel <hotelId>
   ```
   Example: `findHotel 10323`

2. **Find Reviews by Hotel ID**:
   ```
   findReviews <hotelId>
   ```
   Example: `findReviews 25622`

3. **Find Reviews by Word**:
   ```
   findWord <word>
   ```
   Example: `findWord excellent`

## 📁 Project Structure

```
src/
├── main/java/hotelapp/
│   ├── HotelReviewService.java      # Main service class
│   ├── ArgumentParser.java          # Command-line argument parser
│   ├── DataSearchMain.java          # Core data search functionality
│   ├── ParseHotel.java             # Hotel JSON parser
│   ├── ParseReview.java            # Review JSON parser
│   └── datatypes/
│       ├── Hotel.java              # Hotel data model
│       ├── Review.java             # Review data model
│       └── WordFrequency.java      # Word frequency data model
└── test/java/
    ├── SearchHotelTest.java        # Hotel search tests
    ├── SearchReviewsTest.java      # Review search tests
    ├── SearchByWordTest.java       # Word search tests
    ├── SearchInvalidQueriesTest.java # Invalid query tests
    └── ArgUtil.java                # Test utility for arguments
```

## 🗃️ Data Format

### Hotel JSON Structure
```json
{
  "sr": [
    {
      "id": "12539",
      "f": "Hotel Name",
      "ad": "Street Address",
      "ci": "City",
      "ll": {
        "lat": "37.784580",
        "lng": "-122.408540"
      }
    }
  ]
}
```

### Review JSON Structure
```json
{
  "reviewDetails": {
    "hotelId": "25622",
    "reviewId": "123456",
    "ratingOverall": 4,
    "title": "Review Title",
    "reviewText": "Review content...",
    "userNickname": "Username",
    "reviewSubmissionTime": "2021-01-15T10:30:00Z"
  }
}
```

## 🏗️ Architecture

The application follows a clean architecture pattern:

- **Service Layer**: `HotelReviewService` - Main entry point and coordination
- **Parsing Layer**: `ParseHotel`, `ParseReview` - JSON data parsing
- **Data Layer**: `DataSearchMain` - Core business logic and data structures
- **Models**: `Hotel`, `Review`, `WordFrequency` - Data transfer objects
- **Utilities**: `ArgumentParser` - Command-line argument processing

## 🧪 Testing

The project includes comprehensive test coverage:

- **Hotel Search Tests**: Verify hotel lookup functionality
- **Review Search Tests**: Test review retrieval by hotel ID
- **Word Search Tests**: Validate text-based search capabilities
- **Invalid Query Tests**: Ensure proper error handling

Run all tests with:
```bash
mvn test
```

## 📊 Dependencies

- **JUnit 5**: Testing framework
- **Gson**: JSON parsing and serialization
- **Maven Surefire**: Test execution

## 🔧 Development

### Code Style
- Follow Java naming conventions
- Use meaningful variable and method names
- Include comprehensive JavaDoc documentation
- Maintain separation of concerns between classes

### Adding New Features
1. Create appropriate data models in `datatypes` package
2. Add parsing logic if needed
3. Extend `DataSearchMain` for new query types
4. Update `HotelReviewService` to handle new commands
5. Add corresponding tests

## 📈 Performance Considerations

- Reviews are sorted by date (newest first) then by review ID
- Hotel data is loaded into memory for fast lookups
- Word search is case-insensitive and supports partial matching

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

## 📄 License

This project is part of an academic assignment and is for educational purposes.