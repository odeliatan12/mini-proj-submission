package nus.project.server.model;

import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Restaurant {

    private Integer id;
    private String name;
    private String about;
    private String contact;
    private String restaurantLink;
    private String menu;
    private String address;
    private Integer cuisine_id;
    private Time mondayOpening;
    private Time mondayClosing;
    private Time tuesdayOpening;
    private Time tuesdayClosing;
    private Time wednesdayOpening;
    private Time wednesdayClosing;
    private Time thursdayOpening;
    private Time thursdayClosing;
    private Time fridayOpening;
    private Time fridayClosing;
    private Time saturdayOpening;
    private Time saturdayClosing;
    private Time sundayOpening;
    private Time sundayClosing;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAbout() {
        return about;
    }
    public void setAbout(String about) {
        this.about = about;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getRestaurantLink() {
        return restaurantLink;
    }
    public void setRestaurantLink(String restaurantLink) {
        this.restaurantLink = restaurantLink;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Integer getCuisine_id() {
        return cuisine_id;
    }
    public void setCuisine_id(Integer cuisine_id) {
        this.cuisine_id = cuisine_id;
    }
    public Time getMondayOpening() {
        return mondayOpening;
    }
    public void setMondayOpening(Time mondayOpening) {
        this.mondayOpening = mondayOpening;
    }
    public Time getMondayClosing() {
        return mondayClosing;
    }
    public void setMondayClosing(Time mondayClosing) {
        this.mondayClosing = mondayClosing;
    }
    public Time getTuesdayOpening() {
        return tuesdayOpening;
    }
    public void setTuesdayOpening(Time tuesdayOpening) {
        this.tuesdayOpening = tuesdayOpening;
    }
    public Time getTuesdayClosing() {
        return tuesdayClosing;
    }
    public void setTuesdayClosing(Time tuesdayClosing) {
        this.tuesdayClosing = tuesdayClosing;
    }
    public Time getWednesdayOpening() {
        return wednesdayOpening;
    }
    public void setWednesdayOpening(Time wednesdayOpening) {
        this.wednesdayOpening = wednesdayOpening;
    }
    public Time getWednesdayClosing() {
        return wednesdayClosing;
    }
    public void setWednesdayClosing(Time wednesdayClosing) {
        this.wednesdayClosing = wednesdayClosing;
    }
    public Time getThursdayOpening() {
        return thursdayOpening;
    }
    public void setThursdayOpening(Time thursdayOpening) {
        this.thursdayOpening = thursdayOpening;
    }
    public Time getThursdayClosing() {
        return thursdayClosing;
    }
    public void setThursdayClosing(Time thursdayClosing) {
        this.thursdayClosing = thursdayClosing;
    }
    public Time getFridayOpening() {
        return fridayOpening;
    }
    public void setFridayOpening(Time fridayOpening) {
        this.fridayOpening = fridayOpening;
    }
    public Time getFridayClosing() {
        return fridayClosing;
    }
    public void setFridayClosing(Time fridayClosing) {
        this.fridayClosing = fridayClosing;
    }
    public Time getSaturdayOpening() {
        return saturdayOpening;
    }
    public void setSaturdayOpening(Time saturdayOpening) {
        this.saturdayOpening = saturdayOpening;
    }
    public Time getSaturdayClosing() {
        return saturdayClosing;
    }
    public void setSaturdayClosing(Time saturdayClosing) {
        this.saturdayClosing = saturdayClosing;
    }
    public Time getSundayOpening() {
        return sundayOpening;
    }
    public void setSundayOpening(Time sundayOpening) {
        this.sundayOpening = sundayOpening;
    }
    public Time getSundayClosing() {
        return sundayClosing;
    }
    public void setSundayClosing(Time sundayClosing) {
        this.sundayClosing = sundayClosing;
    }

    public static Restaurant toRestaurant(SqlRowSet rs){
        Restaurant r = new Restaurant();
        r.setId(rs.getInt("id"));
        r.setName(rs.getString("name"));
        r.setAbout(rs.getString("about"));
        r.setContact(rs.getString("contact"));
        r.setRestaurantLink(rs.getString("restaurantLink"));
        r.setMenu(rs.getString("menu"));
        r.setAddress(rs.getString("address"));
        r.setCuisine_id(rs.getInt("cuisine_id"));
        r.setMondayOpening(rs.getTime("mondayOpening"));
        r.setMondayClosing(rs.getTime("mondayClosing"));
        r.setTuesdayOpening(rs.getTime("tuesdayOpening"));
        r.setTuesdayClosing(rs.getTime("tuesdayClosing"));
        r.setWednesdayOpening(rs.getTime("wednesdayOpening"));
        r.setWednesdayClosing(rs.getTime("wednesdayClosing"));
        r.setThursdayOpening(rs.getTime("thursdayOpening"));
        r.setThursdayClosing(rs.getTime("thursdayClosing"));
        r.setFridayOpening(rs.getTime("fridayOpening"));
        r.setFridayClosing(rs.getTime("fridayClosing"));
        r.setSaturdayOpening(rs.getTime("saturdayOpening"));
        r.setSaturdayClosing(rs.getTime("saturdayClosing"));
        r.setSundayOpening(rs.getTime("sundayOpening"));
        r.setSundayClosing(rs.getTime("sundayClosing"));
        return r;
    }
    
    
}
