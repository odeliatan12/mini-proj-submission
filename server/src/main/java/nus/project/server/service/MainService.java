package nus.project.server.service;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.JsonObject;
import nus.project.server.Utils.AdminUtils;
import nus.project.server.Utils.MainUtils;
import nus.project.server.dto.ImageDto;
import nus.project.server.dto.RestaurantDto;
import nus.project.server.model.Image;
import nus.project.server.model.Restaurant;
import nus.project.server.model.Review;
import nus.project.server.repo.ImageRepoImpl;
import nus.project.server.repo.MainRepoImpl;

@Service
public class MainService {

    @Autowired
    private MainRepoImpl mainRepository;

    @Autowired
    private ImageRepoImpl imageRepository;

    public JsonObject getRestaurantReviews(Integer restaurantId){
        Restaurant r = mainRepository.getRestaurant(restaurantId);
        List<Review> rv = mainRepository.getListofReviews(restaurantId);
        // System.out.println(rv.get(0).getTimestamp());
        JsonObject rObj = AdminUtils.toJSON(r);

        return MainUtils.toJSONREV(rv, rObj);
    }

    public List<JsonObject> getRestaurantbyName(String name){
        List<RestaurantDto> r = mainRepository.getRestaurantbyName(name);
        List<JsonObject> o = r.stream().map(v -> AdminUtils.toJSONrestList(v)).toList();
        return o;
    }

    public Boolean insertReview(String description, Integer ratings, Integer restaurantId, Integer userId){

        long timestamp = System.currentTimeMillis();
        Timestamp ts = new Timestamp(timestamp);
        Boolean result = mainRepository.insertReview( description, ratings, restaurantId, userId, ts);
        return result;
    }

    public Integer insertImage(MultipartFile file, Integer restaurantId) throws SerialException, SQLException, IOException{
        Image i = Image.create(file, restaurantId);
        imageRepository.uploadPost(i);
        return i.getId();
    }

    public String getImagebyId(Integer id) throws SQLException{
        ImageDto i = imageRepository.getImage(id);
        return i.toJsonString();
    }
    
}
