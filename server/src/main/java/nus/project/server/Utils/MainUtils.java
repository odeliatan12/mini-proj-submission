package nus.project.server.Utils;

import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import nus.project.server.model.Review;
import nus.project.server.model.Users;

public class MainUtils {

    public static JsonObjectBuilder toJSON(Review r){
        return Json.createObjectBuilder()
            .add("id", r.getId())
            .add("description", r.getDescription())
            .add("ratings", r.getRatings())
            .add("userId", r.getUserId())
            .add("restaurantId", r.getRestaurantId())
            .add("timestamp", r.getTimestamp().toString());
    }

    public static JsonObject toJson(){
        return Json.createObjectBuilder()
            .add("reviews", "no reviews")
            .build();
    }

    public static JsonObject toJSONResRev(JsonObject j, String k){
        return Json.createObjectBuilder()
            .add("restaurant", j)
            .add("reviews", k)
            .build();
    }

    public static JsonObject toJSONREV(List<Review> r, JsonObject j){

        JsonArrayBuilder arrBld = Json.createArrayBuilder();

        List<JsonObjectBuilder> listofReviews = r.stream().map(v -> toJSON(v)).toList();

        for(JsonObjectBuilder x : listofReviews){
            arrBld.add(x);
        }

        return Json.createObjectBuilder()
            .add("restaurant", j)
            .add("reviews", arrBld)
            .build();

    }

    public static JsonObject toJSONUSER(Users r){
        return Json.createObjectBuilder()
            .add("id", r.getId())
            .add("userName", r.getUsername())
            .add("email", r.getEmail())
            .add("contact", r.getContact())
            .build();
    }
    
}
