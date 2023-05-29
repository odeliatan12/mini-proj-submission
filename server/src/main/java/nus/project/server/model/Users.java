package nus.project.server.model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Users {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String contact;
    private List<Role> roles = new LinkedList<>();
    
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
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public static Users toUsers(SqlRowSet rs){
        Users u = new Users();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setContact(rs.getString("contact"));
        return u;
    }
    
}
