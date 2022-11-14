package System;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SystemFACADE {
    private User currentUser;
    private UserList users;
    private CounselorList counselors;
    private CamperList campers;
    private SessionList sessions;
    private CabinList cabins;
    private FileWriter writer;

    public SystemFACADE(){
        users = UserList.getInstance();
        counselors = CounselorList.getInstance();
        campers = CamperList.getInstance();
        cabins = CabinList.getInstance();
        sessions = SessionList.getInstance();
    }

    public int login(String userName, String password){
        for(User user : users.getUsers()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                currentUser = user;
                if(user.type == Type.PARENT){
                    return 1;
                }else if(user.type == Type.DIRECTOR){
                    return 2;
                }
            }
        }
        for(Counselor counselor : counselors.getCounselors()){
            if(counselor.getUserName().equals(userName) && counselor.getPassword().equals(password)){
                currentUser = counselor;
                return 3;
            }
        }
        return -1;
    }

    //takes all inputed info to create a user account
    public void createUserAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, LocalDate birthday, String address){
       
        User newUser = new User(firstName, lastName, userName);
        newUser.addAddress(address);
        newUser.addEmail(email);
        newUser.addPassword(password);
        newUser.addBirthday(birthday);
        newUser.addPhoneNumber(phoneNumber);
        newUser.setType(Type.PARENT);

        users.addUser(newUser);
    }

    //takes all inputed info to create a counselor account
    public void createCounselorAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, LocalDate birthday, String address, String bio, Medical medical){
        
        Counselor newCounselor = new Counselor(firstName, lastName, userName);
        newCounselor.addAddress(address);
        newCounselor.addEmail(email);
        newCounselor.addPassword(password);
        newCounselor.addBirthday(birthday);
        newCounselor.addPhoneNumber(phoneNumber);
        newCounselor.addBio(bio);
        newCounselor.addMedical(medical);
        newCounselor.setType(Type.COUNSELOR);

        counselors.addCounselor(newCounselor);
    }

    public boolean addCamper(String firstName, String lastName, LocalDate birthday, Contact emergencyContact, Medical medical){
        Camper newCamper = new Camper(firstName, lastName, birthday);
        ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
        emergencyContacts.add(emergencyContact);
        newCamper.addEmergencyContacts(emergencyContacts);
        newCamper.addMedical(medical);

        campers.addCamper(newCamper);
        currentUser.addCamper(newCamper);

        return true;
    }

    public int sessionSignup(Camper camper, Session session){
        int counter = 0;
        for(Cabin cabin : session.getCabins()){
            counter ++;
            if(camper.getAge() >= cabin.getLowCabinAge() && camper.getAge() <= cabin.getMaxCabinAge()){
                if(cabin.getCabinCapacity() <= cabin.getCampers().size()){
                    continue;
                }
                camper.addSession(session);
                session.removeSpotsLeft();
                cabin.addCamper(camper);
                return counter;
            }
        }
        return -1;
    }

    public Camper findCamper(String firstName, String lastName){
        Camper camper = null;
        for(Camper c : campers.getCampers()){
            if(c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)){
                camper = c;
            }
        }
        return camper;
    }

    public ArrayList<Session> findAvailableSessions(){
        ArrayList<Session> availableSessions = new ArrayList<Session>();
        for(Session s : sessions.getSessions()){
            if(s.isSpots()){
                availableSessions.add(s);
            }
        }
        return availableSessions;
    }

    public void logout(){
        users.saveUsers();
        campers.saveCampers();
        counselors.saveCounselors();
        cabins.saveCabins();
        sessions.saveSessions();
        
        currentUser = null;
    }

    public boolean checkUsernameOpen(String username){
        if(!users.getUsers().isEmpty()){
            for(User user : users.getUsers()){
                if(user.getUserName().equals(username)){
                    return false;
                }
            }
        }
        if(!counselors.getCounselors().isEmpty()){
            for(Counselor counselor : counselors.getCounselors()){
                if(counselor.getUserName().equals(username)){
                    return false;
                }
            }
        }
        return true;
    }

    public void giveExpulsion(String firstName, String lastName, String reason){
        Camper camper = findCamper(firstName, lastName);
        camper.giveExpulsion(reason);
    }

    public void createSession(String theme, LocalDate start, LocalDate end){
        Session session = new Session(start, end);
        session.addTheme(theme);
        sessions.addSession(session);
    }

    public void addCabinToSessions(Cabin cabin){
        cabins.addCabin(cabin);
        for(Session session : sessions.getSessions()){
            session.addCabin(cabin);
        }
    }

    public String getUserInfo(){
        String info = new String();
        if(currentUser.getCampers().isEmpty()){
            info = "Your account currently has no campers added.\n";
        }else{
            info = "Your account has the following campers added:\n";
            for(Camper c : currentUser.getCampers()){
                info += c.getFirstName() + " " + c.getLastName();
                if(!c.getSessions().isEmpty()){
                    info += "\n   Registered sessions:\n";
                    for(Session s : c.getSessions()){
                        info += "   - " + s.getStartDate() + " - " + s.getEndDate() + ", Theme: " + s.getTheme();
                    }
                }
            }
        }
        return info;
    }

    public String listSessions(){
        String sessionList = "Sessions:\n";
        int counter = 1;
        for(Session s : sessions.getSessions()){
            sessionList += counter + ") Start: " + s.getStartDate() + ", Theme: " + s.getTheme() + "\n";
            counter ++;
        }
        return sessionList;
    }

    public void printRoster(int sessionNumber){
        Session session = sessions.getSessions().get(sessionNumber - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File rosterFile = new File("System/txt/directory.txt");
        try{
            rosterFile.createNewFile();
            writer = new FileWriter(rosterFile);
            writer.write("List of Campers in the Cabin: ");
            for(Camper c : toPrint.getCampers()){
                writer.write("- " + c.getFirstName() + c.getLastName() + ", " + c.getAge());
            }
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printWeekInfo(int sessionNum){
        Session session = sessions.getSessions().get(sessionNum - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File infoFile = new File("System/txt/info.txt");
        try{
            infoFile.createNewFile();
            writer = new FileWriter(infoFile);
            writer.write("--- Important information for the week of " + session.getStartDate() + " to " + session.getEndDate() + " ---");
            for(Camper c : toPrint.getCampers()){
                writer.write("- " + c.getFirstName() + c.getLastName() + ":");
                writer.write("  -> Alergic to: " + c.getMedical().getAllergies());
                if(c.getMedical().getAllergies().isEmpty()){
                    writer.write("      doesn't have any allergies");
                } else{
                    for(String allergy : c.getMedical().getAllergies()){
                        writer.write("      - " + allergy);
                    }
                }
                writer.write("  -> Emergency Contacts: ");
                for(Contact emergency : c.getEmergencyContacts()){
                    writer.write("      - " + emergency.getFirstName() + " " + emergency.getLastName() + ", " + emergency.getPhoneNumber() + ", " );
                }
                writer.write("  -> Physician Details: ");
                Contact doc = c.getMedical().getPhysician();
                writer.write("      - : " + doc.getFirstName() + " " + doc.getLastName() + ", " + doc.getPhoneNumber());
                writer.write("      - Treatments: ");
                for(Treatment m : c.getMedical().getTreatments()){
                    writer.write("      " + m.getName() + ", " + m.getTime());
                }
                writer.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printSchedule(int sessionNumber){
        Session session = sessions.getSessions().get(sessionNumber - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File scheduleFile = new File("System/txt/sched.txt");
        try{
            scheduleFile.createNewFile();
            writer = new FileWriter(scheduleFile);
            writer.write(toPrint.viewSchedules());
            writer.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private Cabin findCounselorsCabin(Session session){
        Counselor counselor = (Counselor) currentUser;
        for(Cabin c : session.getCabins()){
            for(Cabin counselorCabin : counselor.getCabins()){
                if(c == counselorCabin){
                    return c;
                }
            }
        }
        return null;
    }

    public void setCurrentUser(Object object) {
    }
}