package nus.project.server.Utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.project.server.dto.RestaurantDto;
import nus.project.server.model.Cuisine;
import nus.project.server.model.Restaurant;

public class AdminUtils {

    public static JsonObject toJSON(Restaurant r){

        System.out.println(r.getAddress());

        return Json.createObjectBuilder()
            .add("id", r.getId())
            .add("name", r.getName())
            .add("about", r.getAbout())
            .add("contact", r.getContact())
            .add("restaurantLink", r.getRestaurantLink())
            .add("menu", r.getMenu())
            .add("address", r.getAddress())
            .add("cuisine_id", r.getCuisine_id())
            .add("mondayOpening", r.getMondayOpening().toString())
            .add("mondayClosing", r.getMondayClosing().toString())
            .add("tuesdayOpening", r.getTuesdayOpening().toString())
            .add("tuesdayClosing", r.getTuesdayClosing().toString())
            .add("wednesdayOpening", r.getWednesdayOpening().toString())
            .add("wednesdayClosing", r.getWednesdayClosing().toString())
            .add("thursdayOpening", r.getThursdayOpening().toString())
            .add("thursdayClosing", r.getThursdayClosing().toString())
            .add("fridayOpening", r.getFridayOpening().toString())
            .add("fridayClosing", r.getFridayClosing().toString())
            .add("saturdayOpening", r.getSaturdayOpening().toString())
            .add("saturdayClosing", r.getSaturdayClosing().toString())
            .add("sundayOpening", r.getSundayOpening().toString())
            .add("sundayClosing", r.getSundayClosing().toString())
            .build();
    }

    public static JsonObject toJSONrestList(RestaurantDto d){
        return Json.createObjectBuilder()
            .add("id", d.getId())
            .add("name", d.getName())
            .add("about", d.getAbout())
            .add("resturantLink", d.getRestaurantLink())
            .build();
    }

    public static JsonObject toJSONCuisine(Cuisine c){
        return Json.createObjectBuilder()
            .add("id", c.getId())
            .add("type", c.getType())
            .build();
    }
    
}
