package nus.project.server.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.project.server.Utils.AdminUtils;
import nus.project.server.dto.RestaurantDetailsDto;
import nus.project.server.dto.RestaurantDto;
import nus.project.server.model.Cuisine;
import nus.project.server.model.Restaurant;
import nus.project.server.repo.AdminRepoImpl;
import nus.project.server.service.MainService;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminRepoImpl adminRepo;

    @Autowired
    private MainService service;

    @GetMapping(path = "/admin")
    public ResponseEntity<String> getAllRestaurant(@RequestParam String userId){

        List<RestaurantDto> dto = new LinkedList<>();
        Integer id = Integer.parseInt(userId);
        dto = adminRepo.findRestaurantbyUsername(id);
        if(dto.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
        } else {
            List<JsonObject> o = dto.stream().map(v -> AdminUtils.toJSONrestList(v)).toList();
            return ResponseEntity.status(HttpStatus.OK).body(o.toString());
        }
    }

    @PostMapping(path = "/admin/insertRestaurant/{userId}")
    public ResponseEntity<String> insertRestaurantDetails(@PathVariable String userId, @RequestBody RestaurantDetailsDto dto){

        Integer id = Integer.parseInt(userId);
        
        adminRepo.saveRestaurant(dto.getName(), dto.getAbout(), dto.getAddress(), dto.getCuisineId(), dto.getContact(), dto.getRestaurantLink(), dto.getMenu(),  dto.getMondayOpening(), dto.getMondayClosing(), dto.getTuesdayOpening(), dto.getTuesdayClosing(), dto.getWednesdayOpening(), dto.getWednesdayClosing(), dto.getThursdayOpening(), dto.getThursdayClosing(), dto.getFridayOpening(), dto.getFridayClosing(), dto.getSaturdayOpening(), dto.getSaturdayClosing(), dto.getSundayOpening(), dto.getSundayClosing(), dto.getLatitude(), dto.getLongtitude(), id);
        return ResponseEntity.status(HttpStatus.OK).body("Restaurant saved");
    }

    @PostMapping(path = "/admin/updateRestaurant")
    public ResponseEntity<String> updateRestaurantParticulars(@RequestParam Integer restaurantId, @RequestParam String userId, @RequestBody RestaurantDetailsDto dto){

        Integer uId = Integer.parseInt(userId);

        adminRepo.updateRestaurant(dto.getName(), dto.getAbout(), dto.getContact(), dto.getRestaurantLink(), dto.getMenu(), dto.getAddress(), dto.getCuisineId(),  dto.getMondayOpening(), dto.getMondayClosing(), dto.getTuesdayOpening(), dto.getTuesdayClosing(), dto.getWednesdayOpening(), dto.getWednesdayClosing(), dto.getThursdayOpening(), dto.getThursdayClosing(), dto.getFridayOpening(), dto.getFridayClosing(), dto.getSaturdayOpening(), dto.getSaturdayClosing(), dto.getSundayOpening(), dto.getSundayClosing(), restaurantId, uId);
        return ResponseEntity.status(HttpStatus.OK).body("Restaurant details has been updated, new images has been added");
    }
    
    @GetMapping(path = "/admin/updateRestaurant/{restaurantId}")
    public ResponseEntity<String> getRestaurantParticulars(@PathVariable Integer restaurantId){
        Restaurant r = adminRepo.findRestaurantDetailsbyId(restaurantId);
        // List<Restaurant> r = adminRepo.findRestaurantDetailsbyId(restaurantId);
        System.out.println(r.getName());
        // System.out.println(r.get(0));
        JsonObject o = AdminUtils.toJSON(r);
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @DeleteMapping(path = "/admin/delete/{restaurantId}")
    public ResponseEntity<String> deleteRestaurantParticulars(@PathVariable Integer restaurantId){
        Boolean result = adminRepo.deleteRestaurant(restaurantId);
        if(result == true){
            return ResponseEntity.status(HttpStatus.OK).body("Restaurant details has been deleted");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurant details has not been deleted");
        }
    }

    @GetMapping(path = "/admin/cuisine")
    public ResponseEntity<String> getCuisine(){
        List<Cuisine> c = adminRepo.getCuisines();
        List<JsonObject> j = c.stream().map(v -> AdminUtils.toJSONCuisine(v)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(j.toString());
    }

    @GetMapping(path = "/admin/cuisineType")
    public ResponseEntity<String> getCuisineType(@RequestParam Integer cuisineId){
        return ResponseEntity.status(HttpStatus.OK).body(adminRepo.getCuisinebyId(cuisineId));
    }

    @GetMapping(path = "/admin/cuisineString")
    public ResponseEntity<String> getCuisineString(@RequestParam Integer cuisineId){
        String type = adminRepo.getCuisinebyId(cuisineId);
        JsonObject o = Json.createObjectBuilder()
            .add("type", type)
            .build();
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @PostMapping(path = "/admin/insertImages/{restaurantId}")
    public ResponseEntity<String> insertImages(@PathVariable Integer restaurantId, @RequestPart MultipartFile imgFile) throws SerialException, SQLException, IOException{
        service.insertImage(imgFile, restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body("image file has been inserted");
    }
    
    @GetMapping(path = "image/{restaurantId}")
    public ResponseEntity<String> getImagebyId(@PathVariable Integer restaurantId) throws SQLException{
        String response = service.getImagebyId(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    
}
