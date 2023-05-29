package nus.project.server.security;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

public class GoogleVerifier {

    public static final String CLIENT_ID = "833064911004-809r50phgjvm0p084vsbhk60un9dbc5j.apps.googleusercontent.com";

    public static GoogleIdTokenVerifier getVerifier() throws GeneralSecurityException, IOException {
        return new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
    }
    
}
