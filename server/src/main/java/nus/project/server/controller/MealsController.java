package nus.project.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import nus.project.server.Utils.MealUtils;
import nus.project.server.model.Distance;
import nus.project.server.model.MealNames;
import nus.project.server.model.MealRest;
import nus.project.server.model.Meals;
import nus.project.server.repo.MealRepoImpl;

@RestController
@CrossOrigin(origins = "*")
public class MealsController {

    @Autowired
    private MealRepoImpl repository;

    @GetMapping(path = "/meal/allNames")
    public ResponseEntity<String> getAllMealNames(){
        List<MealNames> ml = repository.getMealNames();
        List<JsonObject> o = ml.stream().map(v -> MealUtils.toJSON(v)).toList();
        System.out.println(o.toString());
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @GetMapping(path = "/meal/allCategories")
    public ResponseEntity<String> getAllCategories(){
        List<MealNames> ml = repository.getMealCategories();
        List<JsonObject> o = ml.stream().map(v -> MealUtils.toJSON(v)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @PostMapping(path = "/meal")
    public ResponseEntity<String> insert(){
        return ResponseEntity.status(HttpStatus.OK).body("insertion ok");
    }

    @PostMapping(path = "/meal/insertMeals")
    public ResponseEntity<String> insertMeals(@RequestBody List<Meals> meals){
        repository.insertMeals(meals);
        return ResponseEntity.status(HttpStatus.OK).body("Meals inserted");
    }

    @GetMapping(path = "/meal/search")
    public ResponseEntity<String> search(@RequestParam String meals){
        List<MealRest> ml = repository.getInformation(meals);
        List<JsonObject> o = ml.stream().map(v -> MealUtils.toJSONMR(v)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @GetMapping(path = "/meal/getDistance")
    public ResponseEntity<String> getDistance(){
        List<Distance> d = repository.getDistance();
        List<JsonObject> o = d.stream().map(v -> MealUtils.toJSOND(v)).toList();
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }
    
}
