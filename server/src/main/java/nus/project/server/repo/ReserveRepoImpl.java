package nus.project.server.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.project.server.dto.CapacityDto;
import nus.project.server.dto.ReservationDto;
import nus.project.server.model.Capacity;
import nus.project.server.model.Reservation;

import static nus.project.server.repo.Queries.*;

@Repository
public class ReserveRepoImpl {

    @Autowired
    private JdbcTemplate template;

    // Batch insert
    public int[] insertCapacity(List<CapacityDto> meals){

        List<Object[]> params = meals.stream().map(v -> new Object[]{v.getCapacity(), v.getStarttiming(), v.getEndtiming(), v.getRestaurantId()}).collect(Collectors.toList());

        int added[] = template.batchUpdate(SQL_INSERTCAPACITY, params);

        return added;

    }

    // Get Reservation timings
    public List<Capacity> getReservationTimings(Integer restaurantId){

        List<Capacity> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESERVATIONTIMINGS, restaurantId);

        while(rs.next()){
            udt.add(Capacity.toCapacity(rs));
        }

        return udt;
    }

    // Post Reservation
    public Boolean insertReservation(Integer restaurantId, Integer userId, ReservationDto dto){
        Integer result = template.update(SQL_INSERTRESERVATION, dto.getTimeReserve(), restaurantId, userId, dto.getDateReserve(), dto.getPax());
        return result > 0 ? true : false;
    }

    // Get total capacity of restaurant
    public Integer getTotalCapacity(Integer id){
        return template.queryForObject(SQL_GETTOTALCAPACITY, Integer.class, id);        
    }

    // Get total number of places reserved on the day
    public Integer getTotalPlacesReserved(String date, Integer timeId){
        return template.queryForObject(SQL_GETTOTALPAX, Integer.class,date, timeId);
    }

    // Get reservations from user id
    public List<Reservation> getReservationsbyId(Integer id){

        List<Reservation> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESERVATIONBYUSERID, id);

        while(rs.next()){
            udt.add(Reservation.toReservation(rs));
        }

        return udt;
    }

    public Reservation getReservationbyId(Integer id){

        List<Reservation> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETRESERVATIONBYID, id);

        while(rs.next()){
            udt.add(Reservation.toReservation(rs));
        }

        return udt.get(0);
    }

    public void deleteReservation(Integer id){
        template.update(SQL_DELETERESERVATION, id);
    }

    
}
