package System;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class DataWriter extends DataConstants {

    public static void main(String[] args) {
        CabinList c = CabinList.getInstance();

        Cabin nc = new Cabin(10, 12);
        c.addCabin(nc);
        
        saveCabins();
    }

    public static void saveUsers() {
        UserList userListClass = UserList.getInstance();
        ArrayList<User> users = userListClass.getUsers();

        JSONArray jsonUsers = new JSONArray();

        //creates json objects
        for (User user : users) {
            jsonUsers.add(getUserJSON(user));
        }

        //  Write JSON file
        try (FileWriter file = new FileWriter(USERS_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(FIRST_NAME, user.getFirstName());
		userDetails.put(LAST_NAME, user.getLastName());
		userDetails.put(USERNAME, user.getUserName());
		userDetails.put(PASSWORD, user.getPassword());
		userDetails.put(EMAIL, user.getEmail());
		userDetails.put(PHONE_NUMBER, user.getPhoneNumber());
		userDetails.put(BIRTHDAY, user.getBirthday().toString());
		userDetails.put(ADDRESS, user.getAddress());
		userDetails.put(TYPE, user.getType().toString());
		
        JSONArray jsonCampers = new JSONArray();
        ArrayList<Camper> campers = user.getCampers();
        for (Camper camper : campers)
            jsonCampers.add(camper.getUUID().toString());
        userDetails.put(CAMPERS, jsonCampers);
        
        return userDetails;
    }

    public static void saveCounselors() {
        CounselorList counselorListClass = CounselorList.getInstance();
        ArrayList<Counselor> counselors = counselorListClass.getCounselors();

        JSONArray jsonCounselors = new JSONArray();

        //creates json objects
        for (Counselor counselor : counselors) {
            jsonCounselors.add(getCounselorJSON(counselor));
        }

        try (FileWriter file = new FileWriter(COUNSELORS_FILE_NAME)) {
            file.write(jsonCounselors.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getCounselorJSON(Counselor counselor) {

        JSONObject counselorDetails = getUserJSON(counselor);
		
        //writing bio
        counselorDetails.put(BIO, counselor.getBio());

        JSONObject jsonMedical = new JSONObject();
        Medical med = counselor.getMedical();

        // writing physician
        JSONObject jsonPhysician = new JSONObject();
        Contact doctor = med.getPhysician();
        jsonPhysician.put(FIRST_NAME, doctor.getFirstName());
        jsonPhysician.put(LAST_NAME, doctor.getLastName());
        jsonPhysician.put(PHONE_NUMBER, doctor.getPhoneNumber());

        jsonMedical.put(PHYSICIAN, jsonPhysician);

        //writing allergies
        JSONArray jsonAllergies = new JSONArray();
        ArrayList<String> allergies = med.getAllergies();
        
        for (String allergy : allergies)
            jsonAllergies.add(allergy);

        jsonMedical.put(ALLERGIES, jsonAllergies);

        //treatments
        JSONArray jsonTreatments = new JSONArray();
        ArrayList<Treatment> treatments = med.getTreatments();
        
        for (Treatment treatment : treatments) {
            JSONObject jsonTreatment = new JSONObject();
            
            jsonTreatment.put(NAME, treatment.getName());
            jsonTreatment.put(TIME, treatment.getTime());

            jsonTreatments.add(jsonTreatment);
        }    
        
        jsonMedical.put(TREATMENTS, jsonTreatments);

        counselorDetails.put(MEDICAL, jsonMedical);

        JSONArray jsonCabins = new JSONArray();
        ArrayList<Cabin> cabins = counselor.getCabins();
        
        for (Cabin cabin : cabins)
            jsonCabins.add(cabin.getUUID().toString());    

        counselorDetails.put(CABINS, jsonCabins);
        
        return counselorDetails;
    }

    public static void saveCampers() {
        CamperList camperListClass = CamperList.getInstance();
        ArrayList<Camper> campers = camperListClass.getCampers();
        JSONArray jsonCampers = new JSONArray();

        for (Camper camper : campers) {
            jsonCampers.add(getCamperJSON(camper));
        }

        try (FileWriter file = new FileWriter(CAMPERS_FILE_NAME)) {
            file.write(jsonCampers.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getCamperJSON(Camper camper) {
        JSONObject camperDetails = new JSONObject();

        camperDetails.put(USER_ID, camper.getUUID().toString());
		camperDetails.put(FIRST_NAME, camper.getFirstName());
		camperDetails.put(LAST_NAME, camper.getLastName());
        camperDetails.put(BIRTHDAY, camper.getBirthday().toString());

        //emergency contacts
        camperDetails.put(EMERGENCY_CONTACTS, 
            getContactJSON( camper.getEmergencyContacts() ));

        //guradian contacts
        camperDetails.put(GUARDIANS, 
            getContactJSON( camper.getGuardians() ));

        JSONObject jsonMedical = new JSONObject();
        Medical med = camper.getMedical();

        //writing physician
        JSONObject jsonPhysician = new JSONObject();
        Contact physician = med.getPhysician();
        jsonPhysician.put(FIRST_NAME, physician.getFirstName());
        jsonPhysician.put(LAST_NAME, physician.getLastName());
        jsonPhysician.put(PHONE_NUMBER, physician.getPhoneNumber());

        jsonMedical.put(PHYSICIAN, jsonPhysician);

        //writing allergies
        JSONArray jsonAllergies = new JSONArray();
        ArrayList<String> allergies = med.getAllergies();
        
        for (String allergy : allergies)
            jsonAllergies.add(allergy);

        jsonMedical.put(ALLERGIES, jsonAllergies);

        // writing treatments
        JSONArray jsonTreatments = new JSONArray();
        ArrayList<Treatment> treatments = med.getTreatments();
        
        for (Treatment treatment : treatments) {
            JSONObject jsonTreatment = new JSONObject();
            
            jsonTreatment.put(NAME, treatment.getName());
            jsonTreatment.put(TIME, treatment.getTime());

            jsonTreatments.add(jsonTreatment);
        }    
        
        jsonMedical.put(TREATMENTS, jsonTreatments);
        camperDetails.put(MEDICAL, jsonMedical);
        camperDetails.put(EXPULSION, camper.getExpulsion());

        // Write reason of expulsion
        JSONArray jsonReasonForExpulsion = new JSONArray();
        ArrayList<String> reasonForExpulsion = camper.getReasonForExpulsion();
        for (String reasonExpulsion : reasonForExpulsion)
            jsonReasonForExpulsion.add(reasonExpulsion);
        camperDetails.put(REASON_FOR_EXPULSION, jsonReasonForExpulsion);

        //sessions
        JSONArray jsonSessions = new JSONArray();
        ArrayList<Session> sessions = camper.getSessions();
        for (Session session : sessions)
            jsonReasonForExpulsion.add(session.getUUID().toString());
        camperDetails.put(SESSIONS, jsonSessions);

        return camperDetails;
    }

    public static void saveSessions() {
        SessionList sessionListClass = SessionList.getInstance();
        ArrayList<Session> sessions = sessionListClass.getSessions();
        JSONArray jsonSessions = new JSONArray();

        for (Session session: sessions) {
            jsonSessions.add(getSessionJSON(session));
        }

        try (FileWriter file = new FileWriter(SESSION_FILE_NAME)) {
            file.write(jsonSessions.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getSessionJSON(Session session) {
        JSONObject sessionDetails = new JSONObject();

		sessionDetails.put(USER_ID, session.getUUID().toString());
        sessionDetails.put(START_DATE, session.getStartDate().toString());
        sessionDetails.put(END_DATE, session.getEndDate().toString());
        sessionDetails.put(SPOTS_LEFT, session.getSpotsLeft());
        sessionDetails.put(THEME, session.getTheme());

        ArrayList<Cabin> cabins = session.getCabins();
        JSONArray jsonCabins = new JSONArray();

        for (Cabin cabin : cabins)
            jsonCabins.add(cabin.getUUID().toString());
        sessionDetails.put(CABINS, jsonCabins);
        
        return sessionDetails;
    }

    public static void saveCabins() {
        CabinList cabinClassList = CabinList.getInstance();
        ArrayList<Cabin> cabins = cabinClassList.getCabins();

        JSONArray jsonCabins = new JSONArray();

        for (Cabin cabin: cabins) {
            jsonCabins.add(getCabinJSON(cabin));
        }

        try (FileWriter file = new FileWriter(SESSION_FILE_NAME)) {
            file.write(jsonCabins.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getCabinJSON(Cabin cabin) {
        JSONObject cabinDetails = new JSONObject();

	    cabinDetails.put(USER_ID, cabin.getUUID().toString());
        cabinDetails.put(LOW_CABIN_AGE, cabin.getLowCabinAge());
        cabinDetails.put(MAX_CABIN_AGE, cabin.getMaxCabinAge());
        cabinDetails.put(CABIN_CAPACITY, cabin.getCabinCapacity());
        
        JSONObject jsonSchedule = new JSONObject();
        HashMap<Day, Schedule> schedules = cabin.getSchedules();

        Set<Day> setDays = schedules.keySet();

        ArrayList<String> days = new ArrayList<>();
        for (Day day : setDays)
            days.add(day.toString());
  
        for (String day : days) {
            JSONArray daySchedules = new JSONArray();
            Schedule sched = schedules.get(Day.valueOf(day));
            
            ArrayList<Activity> activities = sched.getActivities();

            for (Activity activity : activities) {
                JSONObject jsonActivity = new JSONObject();
                
                jsonActivity.put(TITLE, activity.getTitle());
                jsonActivity.put(LOCATION, activity.getLocation());
                jsonActivity.put(START_TIME, activity.getStartTime());
                jsonActivity.put(END_TIME, activity.getEndTime());
            
                daySchedules.add(jsonActivity);
            }

            jsonSchedule.put(day, daySchedules);
        }
        cabinDetails.put(SCHEDULES, jsonSchedule);

        JSONArray jsonCampers = new JSONArray();
        ArrayList<Camper> campers = cabin.getCampers();
        for (Camper camper : campers)
            jsonCampers.add(camper.getUUID().toString());

        cabinDetails.put(CAMPERS, jsonCampers);
		
        return cabinDetails;
    }

    private static JSONArray getContactJSON(ArrayList<Contact> contacts) {
        JSONArray jsonArray = new JSONArray();
        
        for (Contact contact : contacts) {
            JSONObject jsonContact = new JSONObject();
            
            jsonContact.put(FIRST_NAME, contact.getFirstName());
            jsonContact.put(LAST_NAME, contact.getLastName());
            jsonContact.put(PHONE_NUMBER, contact.getPhoneNumber());

            jsonArray.add(jsonContact);
        }

        return jsonArray;
    }
}