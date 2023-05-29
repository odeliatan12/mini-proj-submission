package nus.project.server.dto;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class UsersDto {

    private Integer id;
    private String username;
    private String password;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public static UsersDto toUsers(SqlRowSet rs){
        UsersDto udt = new UsersDto();
        udt.setId(rs.getInt("id"));
        udt.setUsername(rs.getString("name"));
        udt.setPassword(rs.getString("password"));
        return udt;
    }
    
    
}
