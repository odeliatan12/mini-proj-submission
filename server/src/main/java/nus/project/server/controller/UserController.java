package nus.project.server.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.JsonObject;
import nus.project.server.Utils.AdminUtils;
import nus.project.server.Utils.MainUtils;
import nus.project.server.dto.ReviewDto;
import nus.project.server.dto.UserUpdateDto;
import nus.project.server.model.Restaurant;
import nus.project.server.model.Users;
import nus.project.server.repo.MainRepoImpl;
import nus.project.server.service.MainService;

@RestController
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private MainRepoImpl mainRepository;

    @Autowired
    private MainService mainService;

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<String> getUserInformation(@PathVariable String userId){

        Integer id = Integer.parseInt(userId);
        Users u = mainRepository.getUserInfo(id);
        JsonObject o = MainUtils.toJSONUSER(u);
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @GetMapping(path = "/allRestaurants")
    public ResponseEntity<String> getAllRestaurants(){

        List<Restaurant> r = mainRepository.getAllRestaurants();

        List<JsonObject> o = r.stream().map(v -> AdminUtils.toJSON(v)).toList();

        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    // @GetMapping(path = "/restaurant/{cuisineId}")
    // public ResponseEntity<String> getRestaurantDetailsbyCuisine(@PathVariable Integer cuisineId){

    //     List<Restaurant> r = mainRepository.findRestaurantbyCuisine(cuisineId);

    //     List<JsonObject> o = r.stream().map(v -> AdminUtils.toJSON(v)).toList();

    //     return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    // }

    @GetMapping(path = "/restaurant/{restaurantId}")
    public ResponseEntity<String> getRestaurant(@PathVariable Integer restaurantId){
        
        JsonObject o = mainService.getRestaurantReviews(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @GetMapping(path = "/restaurantName/{restaurantName}")
    public ResponseEntity<String> getRestaurantbyName(@PathVariable String restaurantName){

        List<JsonObject> o = mainService.getRestaurantbyName(restaurantName);

        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @PostMapping(path = "/user/insertReview/{userId}")
    public ResponseEntity<String> insertReview(@RequestParam Integer restaurantId, @PathVariable String userId, @RequestBody ReviewDto reviewDto){

        System.out.println(restaurantId);
        Integer uId = Integer.parseInt(userId);

        mainService.insertReview(reviewDto.getDescription(), reviewDto.getRatings(), restaurantId, uId);

        return ResponseEntity.status(HttpStatus.OK).body("New Review has been added successfully");    
    }

    @GetMapping(path = "user/{restaurantId}/getReviewCount")
    public ResponseEntity<Integer> getReviewCount(@PathVariable Integer restaurantId){
        return ResponseEntity.status(HttpStatus.OK).body(mainRepository.getReviewCount(restaurantId));
    }

    @PutMapping(path = "user/updateUser/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody UserUpdateDto dto){
        
        Integer id = Integer.parseInt(userId);

        mainRepository.updateUser(dto.getUsername(), dto.getEmail(), dto.getPhone(), id);

        return ResponseEntity.status(HttpStatus.OK).body("User has been updated!");

    }
    
}
