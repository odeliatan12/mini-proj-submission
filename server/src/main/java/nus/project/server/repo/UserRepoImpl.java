package nus.project.server.repo;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.project.server.dto.RolesDto;
import nus.project.server.dto.UsersDto;
import nus.project.server.model.Users;

import static nus.project.server.repo.Queries.*;

@Repository
public class UserRepoImpl {

    @Autowired
    private JdbcTemplate template;

    // finding results by username
    public UsersDto findbyUsername(String userName){
        
        List<UsersDto> udt = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_FINDBYUSERNAME, userName);

        while(rs.next()){
            udt.add(UsersDto.toUsers(rs));
        }

        return udt.get(0);
    }

    // get list of roles
    public List<RolesDto> getRolesbyId(String userName){
        Integer userId = template.queryForObject(SQL_GETIDBYNAME, Integer.class, userName);
        
        List<RolesDto> dto = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GETLISTOFROLES, userId);

        while(rs.next()){
            dto.add(RolesDto.toRoles(rs));
        }

        return dto;
    }

    // get password
    public String getPassword(String username){
        return template.queryForObject(SQL_GETPASSWORDBYNAME, String.class, username);
    }

    public String getRolebyId(String username){
        Integer userId = template.queryForObject(SQL_GETIDBYNAME, Integer.class, username);
        return template.queryForObject(SQL_GETROLE, String.class, userId);
    }

    // finding if the username exists
    public Boolean userNameExists(String userName){

        Integer result = template.queryForObject(SQL_USERNAMEEXISTS, Integer.class, userName);

        return result > 0 ? true : false;
    }

    // Getting role by id from role table
    public Integer getRoleId(String roleName){
        return template.queryForObject(SQL_GETIDYNAME_ROLE, Integer.class, roleName);
    }

    public Integer getUserId(String userName){
        return template.queryForObject(SQL_GETIDBYNAME, Integer.class, userName);
    }

    // Saving user 
    public Boolean save(Users user){
        Integer result = template.update(SQL_INSERTUSER, user.getUsername(), user.getPassword(), user.getEmail(), user.getContact());
        
        return result > 0 ? true : false;
    }

    public Boolean saveUserRole(Integer id, Integer role_id){
        Integer result = template.update(SQL_INSERTUSERROLE, id, role_id);
        return result > 0 ? true : false;
    }

}
