package nus.project.server.repo;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.project.server.model.Distance;
import nus.project.server.model.MealNames;
import nus.project.server.model.MealRest;
import nus.project.server.model.Meals;

import static nus.project.server.repo.Queries.*;

@Repository
public class MealRepoImpl {

    @Autowired
    private JdbcTemplate template;

    public List<MealNames> getMealNames(){
        List<MealNames> ml = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GETMEALNAMES);

        while(rs.next()){
            ml.add(MealNames.toMealNames(rs));
        }

        return ml;
    }

    public List<MealNames> getMealCategories(){
        List<MealNames> ml = new LinkedList<>();

        SqlRowSet rs = template.queryForRowSet(SQL_GETMEALCATEGORIES);

        while(rs.next()){
            ml.add(MealNames.toMealNames(rs));
        }

        return ml;
    }
    
    // Batch insert
    public int[] insertMeals(List<Meals> meals){

        List<Object[]> params = meals.stream().map(v -> new Object[]{v.getRestaurantId(), v.getNameId(), v.getCategoryId(), v.getAmount()}).collect(Collectors.toList());

        int added[] = template.batchUpdate(SQL_INSERTMEALS, params);

        return added;

    }

    // Get RestaurantInformation
    public List<MealRest> getInformation(String request){
        List<MealRest> mR = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETMEALANDRESTINFO, request);

        while(rs.next()){
            mR.add(MealRest.toMealRest(rs));
        }

        return mR;
    }

    // Get Distance that user wants
    public List<Distance> getDistance(){
        List<Distance> d = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETDISTANCE);

        while(rs.next()){
            d.add(Distance.toDistance(rs));
        }

        return d;
    }
}
