package nus.project.server.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class MealNames {

    private Integer id;
    private String name;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static MealNames toMealNames(SqlRowSet rs){
        MealNames ml = new MealNames();
        ml.setId(rs.getInt("id"));
        ml.setName(rs.getString("name"));
        return ml;
    }
}
