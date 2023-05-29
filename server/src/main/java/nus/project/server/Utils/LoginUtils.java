package nus.project.server.Utils;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import nus.project.server.security.SecurityConfig;

import java.util.UUID;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUtils {

    @Autowired
    private static PasswordEncoder passwordEncoder;

    public static JsonObject toJSON(Integer id, String token, String role){
        return Json.createObjectBuilder()
            .add("id", id)
            .add("token", token)
            .add("role", role)
            .build();
    }
    
    public static JsonObject toJSONGoogleRegister(Payload payload){
        return Json.createObjectBuilder()
            .add("name", (String) payload.get("name"))
            .add("familyName",(String) payload.get("family_name"))
            .add("givenName", (String) payload.get("given_name"))
            .add("email", (String) payload.get("email"))
            .add("googleLogin", true)
            .add("password", generatePassword())
            .build();
    }

    public static JsonObject toJSONGoogleLogin(Payload payload){
        return Json.createObjectBuilder()
            .add("givenName", (String) payload.get("given_name"))
            .add("name", (String) payload.get("name"))
            .add("email", (String) payload.get("email"))
            .add("password", generateRandomPassword(8))
            .build();
    }

    private static String generatePassword(){

        
        String password = "empty password";

        String passwordEncoded = passwordEncoder.encode(password);

        return passwordEncoded;
    }

    private static String generateRandomPassword(int length) {
        String randPw = UUID.randomUUID().toString().replace("-", "").replace("_", "")
                        .substring(0, length);

        return randPw;
    }
}
