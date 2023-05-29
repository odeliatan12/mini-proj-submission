package nus.project.server.model;

import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Reservation {

    private Integer id;
    private Integer restaurantId;
    private String dateReserve;
    private Integer pax;
    private Integer capacityId;
    private Time startTiming;
    private Time endTiming;
    private String name;
    private String contact;
    private String address;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    public String getDateReserve() {
        return dateReserve;
    }
    public void setDateReserve(String dateReserve) {
        this.dateReserve = dateReserve;
    }
    public Integer getPax() {
        return pax;
    }
    public void setPax(Integer pax) {
        this.pax = pax;
    }
    public Integer getCapacityId() {
        return capacityId;
    }
    public void setCapacityId(Integer capacityId) {
        this.capacityId = capacityId;
    }
    public Time getStartTiming() {
        return startTiming;
    }
    public void setStartTiming(Time startTiming) {
        this.startTiming = startTiming;
    }
    public Time getEndTiming() {
        return endTiming;
    }
    public void setEndTiming(Time endTiming) {
        this.endTiming = endTiming;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public static Reservation toReservation(SqlRowSet rs){
        Reservation r = new Reservation();
        r.setId(rs.getInt("id"));
        r.setRestaurantId(rs.getInt("restaurant_id"));
        r.setDateReserve(rs.getString("date_reserve"));
        r.setPax(rs.getInt("pax"));
        r.setCapacityId(rs.getInt("capacity_id"));
        r.setStartTiming(rs.getTime("starttiming"));
        r.setEndTiming(rs.getTime("endtiming"));
        r.setContact(rs.getString("contact"));
        r.setName(rs.getString("name"));
        r.setAddress(rs.getString("address"));
        return r;
    }
}
