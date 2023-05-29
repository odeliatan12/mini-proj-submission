package nus.project.server.Utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.project.server.model.Distance;
import nus.project.server.model.MealNames;
import nus.project.server.model.MealRest;

public class MealUtils {

    public static JsonObject toJSON(MealNames ml){
        return Json.createObjectBuilder()
            .add("id", ml.getId())
            .add("name", ml.getName())
            .build();
    }

    public static JsonObject toJSONMR(MealRest mR){
        return Json.createObjectBuilder()
            .add("name", mR.getName())
            .add("amount", mR.getAmount())
            .add("restaurant_id", mR.getRestaurant_id())
            .add("latitude", mR.getLatitude())
            .add("longitude", mR.getLongtitude())
            .add("category_name", mR.getCategory_name())
            .add("restaurant_name", mR.getRestaurant_name())
            .add("address", mR.getAddress())
            .add("contact", mR.getContact())
            .build();
    }
    
    public static JsonObject toJSOND(Distance d){
        return Json.createObjectBuilder()
            .add("id", d.getId())
            .add("distance", d.getDistance())
            .build();
    }
}
