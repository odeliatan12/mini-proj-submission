package nus.project.server.dto;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class RestaurantDto {

    private Integer id;
    private String name;
    private String about;
    private String restaurantLink;
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
    public String getRestaurantLink() {
        return restaurantLink;
    }
    public void setRestaurantLink(String restaurantLink) {
        this.restaurantLink = restaurantLink;
    }

    public static RestaurantDto toRestaurant(SqlRowSet rs){
        RestaurantDto r = new RestaurantDto();
        r.setId(rs.getInt("id"));
        r.setName(rs.getString("name"));
        r.setAbout(rs.getString("about"));
        r.setRestaurantLink(rs.getString("restaurantlink"));
        return r;
    }
    
}
