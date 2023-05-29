package nus.project.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import jakarta.json.JsonObject;
import nus.project.server.Utils.LoginUtils;
import nus.project.server.dto.LoginDto;
import nus.project.server.dto.RegisterDto;
import nus.project.server.exception.RegisteredUserException;
import nus.project.server.model.Role;
import nus.project.server.model.Users;
import nus.project.server.repo.RoleRepoImpl;
import nus.project.server.repo.UserRepoImpl;
import nus.project.server.security.GoogleVerifier;
import nus.project.server.security.JwtGenerator;
import nus.project.server.service.AuthService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepoImpl userRepository;

    @Autowired
    private RoleRepoImpl roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){

        System.out.println(loginDto.getUsername());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtGenerator.generateToken(authentication);

        String role = userRepository.getRolebyId(loginDto.getUsername());

        System.out.println(role);

        Integer userId = userRepository.getUserId(loginDto.getUsername());
        JsonObject o = LoginUtils.toJSON(userId, token, role);

        return ResponseEntity.status(HttpStatus.OK).body(o.toString());
    }

    @GetMapping("getServer")
    public ResponseEntity<String> getServer(){
        return ResponseEntity.status(HttpStatus.OK).body("ok");
    }

    @PostMapping("/register")
    // @CrossOrigin(origins = "*")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){

        System.out.println(registerDto.getEmail());
        if(userRepository.userNameExists(registerDto.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is taken!");
        }

        Users u = new Users();
        u.setUsername(registerDto.getUsername());
        u.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        u.setEmail(registerDto.getEmail());
        u.setContact(registerDto.getContact());

        Role r = roleRepository.findByName("USER").get(0);
        u.setRoles(Collections.singletonList(r));

        userRepository.save(u);
        Integer userId = userRepository.getUserId(registerDto.getUsername());
        Integer role_id = Integer.parseInt(registerDto.getRoleId());
        userRepository.saveUserRole(userId, role_id);

        return ResponseEntity.status(HttpStatus.OK).body("User registered success!");
    }

    @PostMapping(path = "googleLogin")
    public ResponseEntity<String> googleLogin(@RequestBody String token) throws GeneralSecurityException, IOException{
        try {
            GoogleIdToken idToken = GoogleVerifier.getVerifier().verify(token);
            if (idToken != null) {
                Payload payload = idToken.getPayload();
                System.out.println(payload);

                // Generate JWT token and return it
                return ResponseEntity.status(HttpStatus.OK).body(authService.loginGoogle(payload));
            }
        } catch (IOException e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.OK).body("Error loggin in");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid google login");
    }
    
}
