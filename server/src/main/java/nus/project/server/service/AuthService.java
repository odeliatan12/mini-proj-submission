package nus.project.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import jakarta.json.JsonObject;
import nus.project.server.Utils.LoginUtils;
import nus.project.server.model.Users;
import nus.project.server.repo.UserRepoImpl;
import nus.project.server.security.JwtGenerator;

@Service
public class AuthService {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private UserRepoImpl repository;

    @Autowired 
    private UserRepoImpl userRepository;

    public String loginGoogle(Payload payload){

        JsonObject o = LoginUtils.toJSONGoogleLogin(payload);

        System.out.println(o.getString("givenName"));

        String givenName = o.getString("givenName");

        try {

            Boolean userExists = repository.userNameExists(givenName);
            System.out.println(userExists);

            if(userExists == true){
                String token = jwtGenerator.generateTokenwithGoogle(givenName);
                Integer userId = userRepository.getUserId(givenName);
                return LoginUtils.toJSON(userId, token, "USER").toString();
            } else {
                Users u = new Users();
                u.setEmail(o.getString("email"));
                u.setUsername(givenName);
                u.setPassword(o.getString("password"));
                u.setContact("null");
                userRepository.save(u);
                Integer userId = userRepository.getUserId(u.getUsername());
                userRepository.saveUserRole(userId, 2);
                String token = jwtGenerator.generateTokenwithGoogle(givenName);
                Integer id = userRepository.getUserId(givenName);
                return LoginUtils.toJSON(id, token, "USER").toString();
            }
        } catch (Exception e) {
            // TODO: handle exception
            return "Google login error";
        }
    }
    
}
