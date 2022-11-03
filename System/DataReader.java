package System;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.time.LocalDate;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants {

    private static ArrayList<User> users;
    private static ArrayList<Counselor> counselors;
    private static ArrayList<Camper> campers;
    private static ArrayList<Session> sessions;
    private static ArrayList<Cabin> cabins;

    public static void main(String[] args) {
        ArrayList<Session> us = getAllSessions();;
        for (Session user : us) {
            System.out.println(user);
            System.out.println(user.getTheme());
        }
    }

    public static ArrayList<User> getAllUsers() {
        users = new ArrayList<>();

        try {
            FileReader userReader = new FileReader(USERS_FILE_NAME);
            JSONParser parser = new JSONParser();
			
            JSONArray userJSON = (JSONArray) new JSONParser().parse(userReader);

            for (int i=0; i<userJSON.size(); i++) {
                JSONObject user = (JSONObject) userJSON.get(i);

                User newUser = null;
                newUser = getUser(user, newUser);
                           
                users.add(newUser);
            }    

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public static ArrayList<Counselor> getAllCounselors() {
        counselors = new ArrayList<>();
        
        try {
            FileReader counselorReader = new FileReader(COUNSELORS_FILE_NAME);
            JSONParser counselorParser = new JSONParser();
            JSONArray counselorJSON = (JSONArray) new JSONParser().parse(counselorReader);

            for (int i=0; i<counselorJSON.size(); i++) {
                JSONObject counselor = (JSONObject) counselorJSON.get(i);

                Counselor newCounselor = null;
                newCounselor = (Counselor) getUser(counselor, newCounselor);
                
                newCounselor.addBio((String) counselor.get(BIO));
                newCounselor.addMedical(getMedical(
                    (JSONObject) counselor.get(MEDICAL)));

                newCounselor.addCabins(getSomeCabins(counselor));

                counselors.add(newCounselor);
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return counselors;
    }

    public static ArrayList<Session> getAllSessions() {
        initiate();
        return sessions;
    }

    public static ArrayList<Camper> getAllCampers() {
        initiate();
        return campers;
    }

    public static ArrayList<Cabin> getAllCabins() {
        initiate();
        return cabins;
    }

    private static void initiate() {
        if (users == null || counselors == null) {
            getAllUsers();
            getAllCounselors();
        }
    }

    private static ArrayList<Camper> getCampers() {
        
        campers = new ArrayList<>();
        
        try {
            
            FileReader camperReader = new FileReader(CAMPERS_FILE_NAME);
            JSONParser camperParser = new JSONParser();
            JSONArray camperJSON = (JSONArray) new JSONParser().parse(camperReader);

            for (int i=0; i<camperJSON.size(); i++) {
                JSONObject camper = (JSONObject) camperJSON.get(i);

                String birthday = (String) camper.get(BIRTHDAY);

                Camper newCamper = new Camper(
                    UUID.fromString((String) camper.get(USER_ID)),
                    (String) camper.get(FIRST_NAME), 
                    (String) camper.get(LAST_NAME), 
                    LocalDate.parse(birthday));

                ArrayList<Contact> newEmegConts = getContacts( (JSONArray) camper.get(EMERGENCY_CONTACTS) );
                newCamper.addEmergencyContacts(newEmegConts);

                ArrayList<Contact> newGuardians = getContacts( (JSONArray) camper.get(GUARDIANS) );
                newCamper.addGuardians(newGuardians);

                JSONObject medical = (JSONObject) camper.get(MEDICAL);
                newCamper.addMedical(getMedical(medical));    

                campers.add(newCamper);
            }
            
            getSessions();

            for (int i=0; i<camperJSON.size(); i++) {

                JSONObject camper = (JSONObject) camperJSON.get(i);
                JSONArray JSONSessions = (JSONArray) camper.get(SESSIONS);
                
                ArrayList<Session> newSessions = new ArrayList<>();
                for (int j=0; j<JSONSessions.size(); j++) {
                    
                    Session newSession = getSessionByUUID(
                        UUID.fromString((String) JSONSessions.get(j)));

                    if (newSession != null)
                        newSessions.add(newSession);

                campers.get(i).addSessions(newSessions);
            }

        }
    } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return campers;
    }
    
    private static ArrayList<Session> getSessions() {
        sessions = new ArrayList<>();
        
        try {
            
            FileReader sessionsReader = new FileReader(SESSION_FILE_NAME);
            JSONParser sessionsParser = new JSONParser();
            JSONArray sessionsJSON = (JSONArray) new JSONParser().parse(sessionsReader);

            for (int i=0; i<sessionsJSON.size(); i++) {
                JSONObject session = (JSONObject) sessionsJSON.get(i);

                Session newSession = new Session(
                    UUID.fromString((String) session.get(USER_ID)),
                    LocalDate.parse((String) session.get(START_DATE)),
                    LocalDate.parse((String) session.get(END_DATE)));

                newSession.setSpotsLeft(
                    ((Long) session.get(SPOTS_LEFT)).intValue());
                
                newSession.addTheme((String) session.get(THEME));

                newSession.addCabins(getSomeCabins(session));

                sessions.add(newSession);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessions;
    }

    private static ArrayList<Cabin> getCabins() {
        cabins = new ArrayList<>();
        
        try {
            
            FileReader cabinsReader = new FileReader(CABIN_FILE_NAME);
            JSONParser cabinsParser = new JSONParser();
            JSONArray cabinsJSON = (JSONArray) new JSONParser().parse(cabinsReader);

            for (int i=0; i<cabinsJSON.size(); i++) {
                JSONObject cabin = (JSONObject) cabinsJSON.get(i);

                Cabin newCabin = new Cabin(
                    UUID.fromString((String) cabin.get(USER_ID)), 
                    ((Long) cabin.get(LOW_CABIN_AGE)).intValue(),
                    ((Long) cabin.get(MAX_CABIN_AGE)).intValue());

                newCabin.addMaxCapacity( ((Long) cabin.get(CABIN_CAPACITY)).intValue() );

                HashMap<Day, Schedule> newSchedules = new HashMap<>();
                JSONObject schedules = (JSONObject) cabin.get(SCHEDULES);
                
                Set<String> s =  schedules.keySet();
                ArrayList<String> dayList = new ArrayList<>(s);

                for (int j=0; j<schedules.size(); j++) {
                    
                    String day = dayList.get(j);

                    Schedule newSchedule = new Schedule(
                        getActivities((JSONArray) schedules.get(day)));

                    newSchedules.put(Day.valueOf(day), newSchedule);
                    
                }
                newCabin.addSchedules(newSchedules);

                cabins.add(newCabin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cabins;
    }

    private static void addCampersToCabins() {

        try {
            
            FileReader cabinsReader = new FileReader(CABIN_FILE_NAME);
            JSONParser cabinsParser = new JSONParser();
            JSONArray cabinsJSON = (JSONArray) new JSONParser().parse(cabinsReader);

            for (int i=0; i<cabinsJSON.size(); i++) {
                JSONObject cabin = (JSONObject) cabinsJSON.get(i);
                cabins.get(i).addCampers(getSomeCampers(cabin));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    private static ArrayList<Camper> getSomeCampers(JSONObject someObject) {
        if (campers == null)
            getCampers();
                    
        JSONArray JSONcampers = (JSONArray) someObject.get(CAMPERS);
        ArrayList<Camper> newCampers = new ArrayList<>();

        for (int i=0; i<JSONcampers.size(); i++) {

            UUID camperID = UUID.fromString((String) JSONcampers.get(i));
            
            Camper newCamper = getCamperByUUID(camperID);

            if (newCamper != null)
                newCampers.add(newCamper);
            
        }

        return newCampers;
    }

    private static ArrayList<Cabin> getSomeCabins(JSONObject someObject) {
        if (cabins == null)
            getCabins();
                          
        JSONArray JSONcabins = (JSONArray) someObject.get(CABINS);
        ArrayList<Cabin> newCabins = new ArrayList<>();

        for (int i=0; i<JSONcabins.size(); i++) {

            UUID cabinID = UUID.fromString((String) JSONcabins.get(i));
            
            Cabin newCabin = getCabinByUUID(cabinID);

            if (newCabin != null)
                newCabins.add(newCabin);
            
        }

        return newCabins;
    }


    private static Session getSessionByUUID(UUID id) {
        for (Session session : sessions) {
            if (session.getUUID().compareTo(id) == 0)
                return session;
        }

        return null;
    }

    private static Cabin getCabinByUUID(UUID id) {
        for (Cabin cabin : cabins) {
            if (cabin.getUUID().compareTo(id) == 0)
                return cabin;
        }

        return null;
    }

    private static Camper getCamperByUUID(UUID id) {
        for (Camper camper : campers) {
            if (camper.getUUID().compareTo(id) == 0)
                return camper;
        }

        return null;
    }

    private static User getUser(JSONObject userObj, User user) {
       
        user = new User(
            UUID.fromString((String) userObj.get(USER_ID)),
            (String) userObj.get(FIRST_NAME),
            (String) userObj.get(LAST_NAME),
            (String) userObj.get(USERNAME));
            
        user.setPassword( (String) userObj.get(PASSWORD) );
        user.addEmail( (String) userObj.get(EMAIL) );
        user.addPhoneNumber( (String) userObj.get(PHONE_NUMBER) );
        user.addBirthday( LocalDate.parse((String) userObj.get(BIRTHDAY)) );
        user.addAddress( (String) userObj.get(ADDRESS) );
       
        String type = (String) userObj.get(TYPE);
        Type newType = Type.valueOf(type);
        user.setType(newType);

        getCampers();

        addCampersToCabins();
        
        user.addCampers(getSomeCampers(userObj));

        return user;
    }

    private static User getUser(JSONObject userObj, Counselor counselor) {
        
        counselor = new Counselor(
            UUID.fromString((String) userObj.get(USER_ID)),
            (String) userObj.get(FIRST_NAME),
            (String) userObj.get(LAST_NAME),
            (String) userObj.get(USERNAME));
            
        counselor.setPassword( (String) userObj.get(PASSWORD) );
        counselor.addEmail( (String) userObj.get(EMAIL) );
        counselor.addPhoneNumber( (String) userObj.get(PHONE_NUMBER) );
        counselor.addBirthday( LocalDate.parse((String) userObj.get(BIRTHDAY)) );
        counselor.addAddress( (String) userObj.get(ADDRESS) );
       
        String type = (String) userObj.get(TYPE);
        Type newType = Type.valueOf(type);
        counselor.setType(newType);

        getCampers();

        addCampersToCabins();
        
        counselor.addCampers(getSomeCampers(userObj));

        return counselor;
    }

    private static Medical getMedical(JSONObject medical) {
        
        JSONObject physician = (JSONObject) medical.get(PHYSICIAN);
    
        Contact doc = new Contact(
            (String) physician.get(FIRST_NAME), 
            (String) physician.get(LAST_NAME), 
            (String) physician.get(PHONE_NUMBER));

        ArrayList<String> newAllergies = new ArrayList<>();
        JSONArray allergies = (JSONArray) medical.get(ALLERGIES);

        for (int i=0; i<allergies.size(); i++)
            newAllergies.add( (String) allergies.get(i) );
        
        ArrayList<Treatment> newTreatments = new ArrayList<>();
        JSONArray treatments = (JSONArray) medical.get(TREATMENTS);
        
        for (int i=0; i<treatments.size(); i++) {
            JSONObject treatment = (JSONObject) treatments.get(i);

            newTreatments.add( new Treatment(
                (String) treatment.get(NAME),
                (String) treatment.get(DESCRIPTION),
                (String) treatment.get(TIME) ));
        }
        
        Medical toRetMed = new Medical(doc);
        toRetMed.addAllergies(newAllergies);
        toRetMed.addTreatments(newTreatments);
        
        return toRetMed;
    }

    private static ArrayList<Contact> getContacts(JSONArray contacts) {
        ArrayList<Contact> contactsList = new ArrayList<>();

        for (int j=0; j<contacts.size(); j++) {
            JSONObject contact = (JSONObject) contacts.get(j);

            Contact newContact = new Contact(
                (String) contact.get(FIRST_NAME), 
                (String) contact.get(LAST_NAME), 
                (String) contact.get(PHONE_NUMBER));

            contactsList.add(newContact);
        }

        return contactsList;
    }

    private static ArrayList<Activity> getActivities(JSONArray schedules) {
       ArrayList<Activity> newActivites = new ArrayList<>();

        for (int i=0; i<schedules.size(); i++) {
            JSONObject activity = (JSONObject) schedules.get(i);
            
            Activity newActivity = new Activity(
                (String) activity.get(TITLE),
                (String) activity.get(LOCATION));

            newActivity.addStartTime( (String) activity.get(START_TIME) );
            newActivity.addEndTime( (String) activity.get(END_TIME) );

            newActivites.add(newActivity);
        }

        return newActivites;
    }
}