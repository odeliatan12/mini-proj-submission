package nus.project.server.Utils;

import java.math.BigDecimal;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.project.server.dto.DealsDto;
import nus.project.server.dto.VoucherDto;
import nus.project.server.model.Categories;
import nus.project.server.model.Deals;
import nus.project.server.model.Vouchers;

public class DealsUtils {

    public static Document insertDeal(DealsDto dto, String id, Integer restaurantId, String restaurantName){
        Document d = new Document();
        d.put("id", id);
        d.put("restaurantId", restaurantId);
        d.put("restaurantName", restaurantName);
        d.put("name", dto.getName());
        d.put("originalAmount", dto.getOriginalAmount());
        d.put("newAmount", dto.getNewAmount());
        d.put("quantity", dto.getQuantity());
        d.put("category", dto.getCategory());
        d.put("mondayFrom", dto.getMondayFrom());
        d.put("mondayTo", dto.getMondayTo());
        d.put("tuesdayFrom", dto.getTuesdayFrom());
        d.put("tuesdayto", dto.getTuesdayTo());
        d.put("wednesdayFrom", dto.getWednesdayFrom());
        d.put("wednesdayto", dto.getWednesdayTo());
        d.put("thursdayFrom", dto.getThursdayFrom());
        d.put("thursdayTo", dto.getThursdayTo());
        d.put("fridayFrom", dto.getFridayFrom());
        d.put("fridayTo", dto.getFridayTo());
        d.put("saturdayFrom", dto.getSaturdayFrom());
        d.put("saturdayTo", dto.getSaturdayTo());
        d.put("sundayFrom", dto.getSundayFrom());
        d.put("sundayTo", dto.getSundayTo());
        return d;
    }

    public static Document insertCategories(String categories, String id){
        Document doc = new Document();
        doc.put("id", id);
        doc.put("category", categories);
        return doc;
    }

    public static Document insertVoucher(VoucherDto dto, String id, Integer restaurantId, Integer userId){
        Document d = new Document();
        d.put("id", id);
        d.put("dealId", dto.getDealId());
        d.put("amount", dto.getNewAmount());
        d.put("restaurantId", restaurantId);
        d.put("userId", userId);
        return d;
    }

    public static Categories toCategories(Document doc){
        Categories c = new Categories();
        c.setId(doc.getString("id"));
        c.setCategory(doc.getString("category"));
        return c;
    }

    public static Vouchers toVouchers(Document doc){
        Vouchers v = new Vouchers();
        v.setId(doc.getString("id"));
        v.setDealId(doc.getString("dealId"));
        v.setAmount(doc.getDouble("amount"));
        v.setRestaurantId(doc.getInteger("restaurantId"));
        v.setUserId(doc.getInteger("userId"));
        return v;
    }

    public static JsonObject toJSON(Categories c){
        return Json.createObjectBuilder()
            .add("id", c.getId())
            .add("category", c.getCategory())
            .build();
    }

    public static JsonObject toJSON(Vouchers v){
        return Json.createObjectBuilder()
            .add("id", v.getId())
            .add("dealId", v.getDealId())
            .add("amount", v.getAmount())
            .add("restaurantId", v.getRestaurantId())
            .add("userId", v.getUserId())
            .build();
    }

    public static Deals toDeals(Document doc){
        Deals d = new Deals();
        d.setId(doc.getString("id"));
        d.setRestaurantId(doc.getInteger("restaurantId"));
        d.setRestaurantName(doc.getString("restaurantName"));
        d.setName(doc.getString("name"));
        d.setOriginalAmount(doc.getDouble("originalAmount"));
        d.setNewAmount(doc.getDouble("newAmount"));
        d.setQuantity(doc.getInteger("quantity"));
        d.setCategory(doc.getString("category"));
        d.setMondayFrom(doc.getString("mondayFrom"));
        d.setMondayTo(doc.getString("mondayTo"));
        d.setTuesdayFrom(doc.getString("tuesdayFrom"));
        d.setTuesdayTo(doc.getString("tuesdayto"));
        d.setWednesdayFrom(doc.getString("wednesdayFrom"));
        d.setWednesdayTo(doc.getString("wednesdayTo"));
        d.setThursdayTo(doc.getString("thursdayTo"));
        d.setThursdayFrom(doc.getString("thursdayFrom"));
        d.setFridayTo(doc.getString("fridayTo"));
        d.setFridayFrom(doc.getString("fridayFrom"));
        d.setSaturdayFrom(doc.getString("saturdayFrom"));
        d.setSaturdayTo(doc.getString("saturdayTo"));
        d.setSundayFrom(doc.getString("sundayFrom"));
        d.setSundayTo(doc.getString("sundayTo"));
        return d;
    }

    public static JsonObject toJSONDeals(Deals dto){
        return Json.createObjectBuilder()
            .add("id", dto.getId())
            .add("restaurantId", dto.getRestaurantId())
            .add("restaurantName", dto.getRestaurantName())
            .add("name", dto.getName())
            .add("originalAmount", dto.getOriginalAmount())
            .add("newAmount", dto.getNewAmount())
            .add("quantity", dto.getQuantity())
            .add("category", dto.getCategory())
            .add("mondayFrom", dto.getMondayFrom())
            .add("mondayTo", dto.getMondayTo())
            .add("tuesdayTo", dto.getTuesdayTo())
            .add("tuesdayFrom", dto.getTuesdayFrom())
            .add("wednesdayFrom", dto.getWednesdayFrom())
            .add("thursdayTo", dto.getThursdayTo())
            .add("thursdayFrom", dto.getThursdayFrom())
            .add("fridayFrom", dto.getFridayFrom())
            .add("fridayTo", dto.getFridayTo())
            .add("saturdayFrom", dto.getSaturdayFrom())
            .add("saturdayTo", dto.getSaturdayTo())
            .add("sundayFrom", dto.getSundayFrom())
            .add("sundayTo", dto.getSundayTo())
            .build();
    }
    
}
