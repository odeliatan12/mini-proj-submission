package nus.project.server.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import nus.project.server.dto.RestaurantDto;
import nus.project.server.model.Cuisine;
import nus.project.server.model.Restaurant;

import static nus.project.server.repo.Queries.*;
import static nus.project.server.Constants.*;

import java.util.LinkedList;
import java.util.List;

@Repository
public class AdminRepoImpl {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private MongoTemplate template2;

    public Boolean saveRestaurant(String name, String about, String address, Integer cuisineId, String contact, String restaurantLink, String menu, String mondayOpening, String mondayClosing, String tuesdayOpening, String tuesdayClosing, String wednesdayOpening, String wednesdayClosing, String thursdayOpening, String thursdayClosing, String fridayOpening, String fridayClosing, String saturdayOpening, String saturdayClosing, String sundayOpening, String sundayClosing, Double latitude, Double longtitude ,Integer userId){
        
        Integer result = template.update(SQL_INSERTRESTUARANT, name, about, contact, restaurantLink, menu, userId, address, cuisineId, latitude, longtitude);

        Integer restaurantId = getRestaurantIdbyName(name);
        System.out.println(restaurantId);
        
        template.update(SQL_INSERTDAYS, mondayOpening, mondayClosing, tuesdayOpening, tuesdayClosing, wednesdayOpening, wednesdayClosing, thursdayOpening, thursdayClosing,fridayOpening, fridayClosing, saturdayOpening, saturdayClosing, sundayOpening, sundayClosing, restaurantId);
        return result > 0 ? true : false;
        
    }

    public Boolean updateRestaurant(String name, String about, String contact, String restaurantLink, String menu, String address, Integer cuisineId, String mondayOpening, String mondayClosing, String tuesdayOpening, String tuesdayClosing, String wednesdayOpening, String wednesdayClosing, String thursdayOpening, String thursdayClosing, String fridayOpening, String fridayClosing, String saturdayOpening, String saturdayClosing, String sundayOpening, String sundayClosing, Integer restaurantId, Integer userId){

        template.update(SQL_UPDATERESTAURANTWOIMAGE, name, about, contact, restaurantLink, menu, userId, address, cuisineId, restaurantId);

        Integer result = template.update(SQL_UPDATEDAYS, mondayOpening, mondayClosing, tuesdayOpening, tuesdayClosing, wednesdayOpening, wednesdayClosing, thursdayOpening, thursdayClosing, fridayOpening, fridayClosing, saturdayOpening, saturdayClosing, sundayOpening, sundayClosing, restaurantId);

        return result > 0 ? true : false;

    }

    public Integer getRestaurantIdbyName(String name){
        return template.queryForObject(SQL_GETIDBYRESTAURANTNAME, Integer.class, name);
    }

    // public Restaurant findRestaurantDetailsbyId(Integer id){
    //     return template.queryForObject(SQL_GETRESTAURANTDETAILS, BeanPropertyRowMapper.newInstance(Restaurant.class), id);
    // }

    public Restaurant findRestaurantDetailsbyId(Integer id){
        List<Restaurant> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESTAURANTDETAILS, id);

        System.out.println(rs);

        while(rs.next()){
            udt.add(Restaurant.toRestaurant(rs));
        }

        return udt.get(0);
    }

    public List<RestaurantDto> findRestaurantbyUsername(Integer id){
        return template.query(SQL_GETRESTAURANTBYID, BeanPropertyRowMapper.newInstance(RestaurantDto.class), id);
    }

    /*
     * db.deals.deleteOne({ restaurantId: ? })
     */
    public boolean deleteRestaurant(Integer restaurantId){
        template.update(SQL_DELETEREVIEWS, restaurantId);
        template.update(SQL_DELETEDAYS, restaurantId);
        template.update(SQL_DELETEIMAGES, restaurantId);
        template.update(SQL_DELETEMEALS, restaurantId);

        Query query = Query.query(Criteria.where(FIELD_RESTAURANTID).is(restaurantId));
        template2.remove(query, COLLECTION_DEALS);
        
        Integer result = template.update(SQL_DELETERESTAURANT, restaurantId);
        return result > 0? true : false;
    }

    public List<Cuisine> getCuisines(){
        return template.query(SQL_GETCUISINE, BeanPropertyRowMapper.newInstance(Cuisine.class));
    }

    public String getCuisinebyId(Integer id){
        return template.queryForObject(SQL_GETCUISINEBYID, String.class, id);
    }

}
