package nus.project.server.dto;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class RolesDto {

    private String name;

    public RolesDto(){ }

    public RolesDto(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static RolesDto toRoles(SqlRowSet rs){
        RolesDto rd = new RolesDto();
        rd.setName(rs.getString("name"));
        return rd;
    }
    
}
