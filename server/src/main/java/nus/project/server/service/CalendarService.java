// package nus.project.server.service;

// import java.io.IOException;
// import java.security.GeneralSecurityException;
// import java.util.Arrays;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import com.google.api.client.auth.oauth2.Credential;
// import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
// import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
// import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
// import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
// import com.google.api.client.http.HttpTransport;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.JsonFactory;
// import com.google.api.client.json.gson.GsonFactory;
// import com.google.api.client.util.DateTime;
// import com.google.api.services.calendar.Calendar;
// import com.google.api.services.calendar.CalendarScopes;
// import com.google.api.services.calendar.model.Event;
// import com.google.api.services.calendar.model.EventDateTime;
// import com.google.api.services.calendar.model.EventReminder;

// import nus.project.server.config.GoogleCalendarConfig;
// import nus.project.server.model.Reservations;

// import static nus.project.server.Constants.*;

// @Service
// public class CalendarService {

//     private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

//     public Event createGoogleEvent(String email, Reservations r) 
//         throws GeneralSecurityException, IOException{
        
//         final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

//         Calendar service =
//              new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, GoogleCalendarConfig.getCredentials(HTTP_TRANSPORT))
//                     .setApplicationName(APPLICATION_NAME)
//                     .build();

//         System.out.println(">>>>>>> service" + service);

//         Event event = new Event()
//                     .setSummary(r.getSummary())
//                     .setLocation(r.getLocation());
        
//         System.out.println(">>>>>>>> event" + event);

//         System.out.println(r.getDate());

//         // 2019-10-12T14:20:50.52+07:00
//         String datestartTimeString = r.getDate() + "T" + r.getStartDateTime() + ":00+08:00";
//         String dateendTimeString = r.getDate() + "T" + r.getEndDateTime() + ":00+08:00";
        
//         System.out.println(">>>>>>>> date" + datestartTimeString);
//         System.out.println(">>>>>>>>>>> todays date " + r.getDate());

//         // 2023-05-19T08:00:00+8:00
//         // Creating the date on calendar
//         DateTime startDateTime = DateTime.parseRfc3339(datestartTimeString);

//         System.out.println(startDateTime);
//         EventDateTime start = new EventDateTime()
//             .setDateTime(startDateTime)
//             .setTimeZone("Asia/Singapore");
//         event.setStart(start);

//         System.out.println(">>>>>>>> eventdatetime" + start);

//         DateTime endDateTime = DateTime.parseRfc3339(dateendTimeString);
//         EventDateTime end = new EventDateTime()
//             .setDateTime(endDateTime)
//             .setTimeZone("Asia/Singapore");

//         event.setEnd(end);

//         EventReminder[] reminderOverrides = new EventReminder[] {
//             new EventReminder().setMethod("email").setMinutes(24 * 60),
//             new EventReminder().setMethod("popup").setMinutes(10),
//         };

//         Event.Reminders reminders = new Event.Reminders()
//             .setUseDefault(false)
//             .setOverrides(Arrays.asList(reminderOverrides));
//         event.setReminders(reminders);

//         event = service.events().insert("odeliatan1@gmail.com", event).execute();

//         System.out.println(">>>>>>> event" + event);

//         return event;
//     }
    
// }
