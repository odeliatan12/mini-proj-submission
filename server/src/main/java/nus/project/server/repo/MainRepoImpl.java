package nus.project.server.repo;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.project.server.dto.RestaurantDto;
import nus.project.server.model.Restaurant;
import nus.project.server.model.Review;
import nus.project.server.model.Users;

import static nus.project.server.repo.Queries.*;

@Repository
public class MainRepoImpl {

    @Autowired
    private JdbcTemplate template;

    public Users getUserInfo(Integer userId){
        List<Users> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETUSERINFO, userId);

        while(rs.next()){
            udt.add(Users.toUsers(rs));
        }

        System.out.println(rs);
        return udt.get(0);

    }

    public List<Restaurant> getAllRestaurants(){
        
        List<Restaurant> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESTAURANTS);

        System.out.println(rs);

        while(rs.next()){
            udt.add(Restaurant.toRestaurant(rs));
        }

        return udt;
        
    }

    public List<Restaurant> findRestaurantbyCuisine(Integer id){
        
        List<Restaurant> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESTAURANTDETAILSBYCUISINEID, id);

        while(rs.next()){
            udt.add(Restaurant.toRestaurant(rs));
        }

        return udt;
    }

    public Restaurant getRestaurant(Integer id){

        List<Restaurant> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESTAURANTDETAILS, id);

        while(rs.next()){
            udt.add(Restaurant.toRestaurant(rs));
        }

        return udt.get(0);
    }

    public List<RestaurantDto> getRestaurantbyName(String name){

        List<RestaurantDto> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESTAURANTBYNAME, name);

        while(rs.next()){
            udt.add(RestaurantDto.toRestaurant(rs));
        }

        return udt;
    }

    public List<Review> getListofReviews(Integer restaurantId){

        List<Review> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETREVIEWS, restaurantId);

        while(rs.next()){
            udt.add(Review.toReviews(rs));
            System.out.println(rs.getString("timestamp"));
        }

        return udt;
    }

    public Boolean insertReview(String description, Integer ratings, Integer restaurantId, Integer userId, Timestamp time){

        Integer result = template.update(SQL_INSERTREVIEWS, description,  ratings, restaurantId, userId, time);
        return result > 0 ? true : false;
    }

    public Integer getReviewCount(Integer restaurantId){
        return template.queryForObject(SQL_GETREVIEWCOUNT, Integer.class, restaurantId);
    }

    public Boolean updateUser(String name, String email, String contact, Integer id){

        Integer result = template.update(SQL_UPDATEUSER, name, email, contact, id);
        
        return result > 0 ? true : false;
    }
    
}
