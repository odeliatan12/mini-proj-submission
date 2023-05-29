// package nus.project.server.config;

// import java.io.FileNotFoundException;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.util.Collections;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Configuration;

// import com.google.api.client.auth.oauth2.Credential;
// import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
// import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
// import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
// import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.JsonFactory;
// import com.google.api.client.json.gson.GsonFactory;
// import com.google.api.client.util.store.FileDataStoreFactory;
// import com.google.api.services.calendar.CalendarScopes;

// @Configuration
// public class GoogleCalendarConfig {

//     private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
//     private static final String TOKENS_DIRECTORY_PATH = "classpath:tokens";
//     private static final List<String> SCOPES =
//             Collections.singletonList(CalendarScopes.CALENDAR_EVENTS);
//     private static final String CREDENTIALS_FILE_PATH = "/leafy-antonym-361013-063d437329c4.json";
//     private static final String CALLBACK_PATH = "/oauth2callback";

//     @Value("${google.api.client.id}")
//     private static String clientId;

//     @Value("${google.api.client.secret}")
//     private static String clientSecret;

//     @Value("${google.api.calendar.redirect.uri}")
//     private String redirectUri;

//     public static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
//     throws IOException {

//         InputStream in = GoogleCalendarConfig.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
//         if (in == null) {
//             throw new FileNotFoundException("credentials.json" + CREDENTIALS_FILE_PATH);
//         }

//         // Not able to obtain client id and client secret
//         GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
//             clientSecrets.setInstalled(new GoogleClientSecrets.Details());
//             clientSecrets.getInstalled()
//                 .setClientId(clientId);
//             clientSecrets.getInstalled()
//                 .setClientSecret(clientSecret);

//         GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                 HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//                 .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//                 .setAccessType("offline")
//                 .build();

//         String authorizationUrl = flow.newAuthorizationUrl()
//                 .setRedirectUri("http://your-angular-app.com/callback")  // Set your Angular app's callback URL
//                 .setScopes(SCOPES)
//                 .build();

//         LocalServerReceiver receiver = new LocalServerReceiver
//             .Builder()
//             .setPort(8880)
//             .setCallbackPath(CALLBACK_PATH)
//             .build();
            
//         Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");

//         return credential;
//     }
    
// }
