package nus.project.server.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MealRest {
    
    private String name;
    private Double amount;
    private Integer restaurant_id;
    private Double latitude;
    private Double longtitude;
    private String category_name;
    private String restaurant_name;
    private String address;
    private String contact;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public Integer getRestaurant_id() {
        return restaurant_id;
    }
    public void setRestaurant_id(Integer restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongtitude() {
        return longtitude;
    }
    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }
    public String getCategory_name() {
        return category_name;
    }
    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    public String getRestaurant_name() {
        return restaurant_name;
    }
    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public static MealRest toMealRest(SqlRowSet rs){
        MealRest mR = new MealRest();
        mR.setName(rs.getString("name"));
        mR.setAmount(rs.getDouble("amount"));
        mR.setRestaurant_id(rs.getInt("restaurant_Id"));
        mR.setLatitude(rs.getDouble("latitude"));
        mR.setLongtitude(rs.getDouble("longtitude"));
        mR.setCategory_name(rs.getString("category_name"));
        mR.setRestaurant_name(rs.getString("restaurant_name"));
        mR.setAddress(rs.getString("address"));
        mR.setContact(rs.getString("contact"));
        return mR;
    }
    
}
