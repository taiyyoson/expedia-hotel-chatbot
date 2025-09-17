package hotelapp.jsonParsing;

import hotelapp.datatypes.Hotel;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Utility class for parsing hotel data from JSON files into Hotel objects.
 */
public class ParseHotel {
    /**
     * Parses hotel data from a JSON file and returns a list of Hotel objects.
     *
     * @param filename the path to the JSON file containing hotel data
     * @return list of Hotel objects parsed from the JSON file
     */
    public List<Hotel> parseHotelJson(String filename) {

        //Gson gson = new Gson();
        List<Hotel> hotels = new ArrayList<>();

        try(FileReader fr = new FileReader(filename)) {
            JsonObject jobj = (JsonObject) JsonParser.parseReader(fr);
            JsonArray hotelJsonArr = jobj.getAsJsonArray("sr");
            for (JsonElement e : hotelJsonArr) {
                JsonObject hotelObj = e.getAsJsonObject();

                String id = hotelObj.get("id").getAsString();
                String name = hotelObj.get("f").getAsString();

                JsonObject location = hotelObj.getAsJsonObject("ll");
                double lat = Double.parseDouble(location.get("lat").getAsString());
                double lng = Double.parseDouble(location.get("lng").getAsString());

                String address = hotelObj.get("ad").getAsString();
                String city = hotelObj.get("ci").getAsString();

                Hotel hotel = new Hotel(name, id, address, city, lat, lng);
                hotels.add(hotel);
            }



        }
        catch (IOException e) {
            System.out.println("Error reading hotels json file: " + e);
        }
        return hotels;
    }
}