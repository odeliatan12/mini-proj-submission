package nus.project.server.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import nus.project.server.model.Role;

import static nus.project.server.repo.Queries.*;

import java.util.List;

@Repository
public class RoleRepoImpl {

    @Autowired
    private JdbcTemplate template;

    public List<Role> findByName(String name){
        return template.query(SQL_FINDBYNAME_ROLE, BeanPropertyRowMapper.newInstance(Role.class), name);
    }

}
