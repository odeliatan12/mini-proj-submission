package nus.project.server.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Distance {

    private Integer id;
    private Integer distance;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDistance() {
        return distance;
    }
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public static Distance toDistance(SqlRowSet rs){
        Distance distance = new Distance();
        distance.setId(rs.getInt("id"));
        distance.setDistance(rs.getInt("distance"));
        return distance;
    }
}
