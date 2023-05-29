package nus.project.server.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import jakarta.json.JsonObject;
import nus.project.server.Utils.ReservationUtils;
import nus.project.server.dto.ReservationDto;
import nus.project.server.exception.ReservationException;
import nus.project.server.model.Capacity;
import nus.project.server.model.Reservation;
import nus.project.server.repo.ReserveRepoImpl;

@Service
public class ReservationService {

    @Autowired
    private ReserveRepoImpl repository;

    // Get Available Reservation Timings
    public List<JsonObject> getReservationTimings(Integer restaurantId){
        
        List<Capacity> c = repository.getReservationTimings(restaurantId);

        List<JsonObject> o = c.stream().map(v -> ReservationUtils.toJSON(v)).toList();

        return o;
    }

    public List<JsonObject> getRemainingAvailableTimings(String date, Integer restaurantId){

        List<Capacity> c = repository.getReservationTimings(restaurantId);
        List<Capacity> availableTimings = new ArrayList<>();

        for(Capacity timings: c){
            Integer totalCapacity = repository.getTotalCapacity(timings.getId());

            Integer totalReserved = repository.getTotalPlacesReserved(date, timings.getId());

            if(totalReserved == null){
                totalReserved = 0;
            }

            if(totalReserved < totalCapacity){
                availableTimings.add(timings);
            }
        }

        System.out.println(availableTimings);
        return availableTimings.stream().map(v -> ReservationUtils.toJSON(v)).toList();

    }

    public ReservationDto insertReservation(ReservationDto dto, Integer restaurantId, Integer userId) throws ParseException, ReservationException{

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dto.getDateReserve());
        System.out.println(date);
        LocalDate currentDate = LocalDate.now();
        if(date.before(java.sql.Date.valueOf(currentDate))){
            throw new ReservationException("Please enter a future date");
        }

        Integer totalReservedSeat = repository.getTotalPlacesReserved(dto.getDateReserve(), dto.getTimeReserve());

        Integer totalPaxAvailable = repository.getTotalCapacity(dto.getTimeReserve());

        if(totalReservedSeat >= totalPaxAvailable ){
            throw new ReservationException("No seats available for this reservation");
        }

        Boolean result = repository.insertReservation(restaurantId, userId, dto);

        if(result == false){
            throw new ReservationException("Reservation cannot be booked");
        }

        return dto;
    }

    public List<JsonObject> getReservationbyId(Integer id){

        List<Reservation> r = repository.getReservationsbyId(id);
        List<Reservation> futureReservation = new ArrayList<>();

        for(Reservation res: r){

            LocalDate reservedDate = LocalDate.parse(res.getDateReserve());
            LocalDate currentDate = LocalDate.now();

            if(reservedDate.isAfter(currentDate)){
                futureReservation.add(res);
            }
        }

        List<JsonObject> o = futureReservation.stream().map(v -> ReservationUtils.toJSONReservation(v)).toList();
        return o;
    }

    public JsonObject getReservation(Integer id){
        Reservation r = repository.getReservationbyId(id);
        JsonObject o = ReservationUtils.toJSONReservation(r);
        return o;
    }

    
    
}
