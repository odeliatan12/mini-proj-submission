package nus.project.server.model;

import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Capacity {

    private Integer id;
    private Integer capacity;
    private Time startTiming;
    private Time endTiming;
    private Integer restaurantId;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getCapacity() {
        return capacity;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
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
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    public static Capacity toCapacity(SqlRowSet rs){
        Capacity c = new Capacity();
        c.setId(rs.getInt("id"));
        c.setCapacity(rs.getInt("capacity"));
        c.setStartTiming(rs.getTime("starttiming"));
        c.setEndTiming(rs.getTime("endtiming"));
        c.setRestaurantId(rs.getInt("restaurant_id"));
        return c;
    }
    
    
}
