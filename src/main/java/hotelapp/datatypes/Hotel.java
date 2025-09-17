package hotelapp.datatypes;

/**
 * Represents a hotel with basic information including location and contact details.
 */
public class Hotel {
    private String name;
    private String id;
    private String streetAddress;
    private String city;
    private double lat;
    private double lon;

    /**
     * Constructs a Hotel object with the specified details.
     *
     * @param name the name of the hotel
     * @param id the unique identifier of the hotel
     * @param streetAddress the street address of the hotel
     * @param city the city where the hotel is located
     * @param lat the latitude coordinate of the hotel
     * @param lon the longitude coordinate of the hotel
     */
    public Hotel(String name, String id, String streetAddress, String city, double lat, double lon) {
        this.name = name;
        this.id = id;
        this.streetAddress = streetAddress;
        this.city = city;
        this.lat = lat;
        this.lon = lon;
    }

    /**
     * Returns a formatted string representation of the hotel information.
     *
     * @return formatted hotel details including name, ID, coordinates, and address
     */
    @Override
    public String toString() {
        return "hotelName = " + name + System.lineSeparator() +
               "hotelId = " + id + System.lineSeparator() +
               "latitude = " + String.format("%.6f", lat) + System.lineSeparator() +
               "longitude = " + String.format("%.6f", lon) + System.lineSeparator() +
               "address = " + streetAddress + ", " + city + ", CA, USA";
    }

    /**
     * Returns the name of the hotel.
     *
     * @return the name of the hotel.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the id of the hotel.
     *
     * @return the id of the hotel
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the latitude of the hotel.
     *
     * @return the latitude of the hotel
     */
    public double getLat() {
        return lat;
    }
    /**
     * Returns the longitude of the hotel.
     *
     * @return the longitude of the hotel
     */
    public double getLon() {
        return lon;
    }
    /**
     * Returns the street address of the hotel.
     *
     * @return the street address of the hotel
     */
    public String getStreetAddress() {
        return streetAddress;
    }
    /**
     * Returns the city of the hotel.
     *
     * @return the city of the hotel
     */
    public String getCity() {
        return city;
    }
}
