package nus.project.server.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import nus.project.server.dto.RolesDto;
import nus.project.server.dto.UsersDto;
import nus.project.server.repo.UserRepoImpl;

@Service
@EnableWebSecurity
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepoImpl userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UsersDto user = userRepository.findbyUsername(username);

        List<RolesDto> role = userRepository.getRolesbyId(username);

        return new User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(role));
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<RolesDto> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
