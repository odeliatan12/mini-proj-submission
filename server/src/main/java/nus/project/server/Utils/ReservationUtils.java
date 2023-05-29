package nus.project.server.Utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.project.server.model.Capacity;
import nus.project.server.model.Reservation;

public class ReservationUtils {

    public static JsonObject toJSON(Capacity c){
        return Json.createObjectBuilder()
            .add("id", c.getId())
            .add("startTiming", c.getStartTiming().toString())
            .add("endTiming", c.getEndTiming().toString())
            .add("capacity", c.getCapacity())
            .add("restaurantId", c.getRestaurantId())
            .build();
    }

    public static JsonObject toJSONReservation(Reservation r){
        return Json.createObjectBuilder()
            .add("id", r.getId())
            .add("restaurantId", r.getRestaurantId())
            .add("dateReserve", r.getDateReserve())
            .add("pax", r.getPax())
            .add("capacityId", r.getCapacityId())
            .add("startTiming", r.getStartTiming().toString())
            .add("endTiming", r.getEndTiming().toString())
            .add("name", r.getName())
            .add("contact", r.getContact())
            .add("address", r.getAddress())
            .build();
    }
    
}
