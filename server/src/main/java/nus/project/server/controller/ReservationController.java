package nus.project.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.services.calendar.model.Event;

import jakarta.json.JsonObject;
import nus.project.server.dto.CapacityDto;
import nus.project.server.dto.ReservationDto;
import nus.project.server.model.Reservations;
import nus.project.server.model.Users;
import nus.project.server.repo.MainRepoImpl;
import nus.project.server.repo.ReserveRepoImpl;
// import nus.project.server.service.CalendarService;
import nus.project.server.service.ReservationService;

@RestController
@CrossOrigin(origins = "*")
public class ReservationController {

    @Autowired
    private ReserveRepoImpl repository;

    @Autowired
    private MainRepoImpl mainRepository;

    @Autowired
    private ReservationService service;

    // @Autowired
    // private CalendarService calService;

    @PostMapping(path = "/capacity/insertCapacity")
    public ResponseEntity<String> insertMeals(@RequestBody List<CapacityDto> capacities){
        repository.insertCapacity(capacities);
        return ResponseEntity.status(HttpStatus.OK).body("Meals inserted");
    }

    @PostMapping(path = "/postReservation")
    public ResponseEntity<String> insertReservation(@RequestBody ReservationDto dto, @RequestParam Integer restaurantId, @RequestParam String userId){

        Integer id = Integer.parseInt(userId);
        repository.insertReservation(restaurantId, id, dto);
        return ResponseEntity.status(HttpStatus.OK).body("reservation booked!");
    }

    @GetMapping(path = "/getAvailabletimings/{restaurantId}")
    public ResponseEntity<String> getTimings(@PathVariable Integer restaurantId, @RequestParam String date){

        List<JsonObject> availableTimings = service.getRemainingAvailableTimings(date, restaurantId);

        return ResponseEntity.status(HttpStatus.OK).body(availableTimings.toString());

    }

    @GetMapping(path = "/getReservation/{userId}")
    public ResponseEntity<String> getReservationbyId(@PathVariable String userId){
        Integer id = Integer.parseInt(userId);
        List<JsonObject> reservations = service.getReservationbyId(id);
        return ResponseEntity.status(HttpStatus.OK).body(reservations.toString());
    }

    @GetMapping(path = "/getReservationbyId/{reservationId}")
    public ResponseEntity<String> getReservation(@PathVariable Integer id){
        JsonObject o = service.getReservation(id);
        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    } 
    
    // @PostMapping(path = "/createGoogleEvent")
    // public ResponseEntity<String> createGoogleEvent(@RequestBody Reservations reservation, @RequestParam String userId) throws GeneralSecurityException, IOException{

    //     Integer id = Integer.parseInt(userId);
    //     Users u = mainRepository.getUserInfo(id);
    //     Event e = calService.createGoogleEvent(u.getEmail(), reservation);
    //     System.out.println(e);
    //     return ResponseEntity.status(HttpStatus.OK).body("event is created");
    // }

    @DeleteMapping(path = "/deleteReservation/{eventId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Integer eventId){
        repository.deleteReservation(eventId);
        return ResponseEntity.status(HttpStatus.OK).body("reservation has been deleted");
    }
}
