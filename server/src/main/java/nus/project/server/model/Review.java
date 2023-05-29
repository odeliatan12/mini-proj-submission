package nus.project.server.model;

import java.sql.Time;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Review {

    private Integer id;
    private String description;
    private Integer ratings;
    private Integer userId;
    private Integer restaurantId;
    private String timestamp;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getRatings() {
        return ratings;
    }
    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public Integer getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public static Review toReviews(SqlRowSet rs){
        Review r = new Review();
        r.setId(rs.getInt("id"));
        r.setDescription(rs.getString("description"));
        r.setRatings(rs.getInt("ratings"));
        r.setUserId(rs.getInt("user_id"));
        r.setRestaurantId(rs.getInt("restaurant_id"));
        r.setTimestamp(rs.getString("timestamp"));
        return r;
    }
    
}
