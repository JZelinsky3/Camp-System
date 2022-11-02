package System;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;
/**
 * An objects storing a camper's information
 */
public class Camper
{
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private ArrayList<Address> address;
    private ArrayList<Gender> gender;
    private ArrayList<Contact> emergencyContacts;
    private ArrayList<Contact> guardians;
    private Medical medical;
    private int expulsion;
    private ArrayList<String> reasonForExpulsion;
    private ArrayList<Session> sessions;

    public Camper(String firstName, String lastName, LocalDate birthday)
    {
        this.id = UUID.randomUUID();
        this.birthday = birthday;
        this.address = new ArrayList<Address>();
        this.gender = new ArrayList<Gender>();
        this.medical = medical;
        reasonForExpulsion = new ArrayList<>();
        sessions = new ArrayList<>();
    }

    public Camper(UUID id, String firstName, String lastName, LocalDate birthday){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        emergencyContacts = new ArrayList<>();
        guardians = new ArrayList<>();
        reasonForExpulsion = new ArrayList<>();
        sessions = new ArrayList<>();
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public ArrayList<Contact> getEmergencyContacts() {
        return this.emergencyContacts;
    }

    public ArrayList<Contact> getGuardians() {
        return this.guardians;
    }

    public Medical getMedical() {
        return this.medical;
    }

    public int getExpulsion() {
        return this.expulsion;
    }

    public ArrayList<String> getReasonForExpulsion() {
        return this.reasonForExpulsion;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public UUID getUUID() {
        return id;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setExpulsion(int strikes) {
        this.expulsion = strikes;
    }

    public void addEmergencyContacts(ArrayList<Contact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public void addGuardians(ArrayList<Contact> guardians) {
        this.guardians = guardians;
    }

    public void addMedical(Medical medical) {
        this.medical = medical;
    }

    public void addExpulsionReason(ArrayList<String> reasons) {
        this.reasonForExpulsion = reasons;
    }

    public void addSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void giveExpulsion(String reason){
        expulsion++;
        reasonForExpulsion.add(reason);
    }

    public String toString(){
        String print = "Name :"+this.firstName+" "+this.lastName+"\nBirthday :"+this.birthday+"\nEmergency contacts :";
        for (int i = 0; i < emergencyContacts.size(); i++) {
			if (emergencyContacts.get(i) != null) {
                print += emergencyContacts.get(i)+"\n";
            }
		}
        print += "\nGuardians : ";
        for (int i = 0; i < guardians.size(); i++) {
			if (guardians.get(i) != null) {
                print += guardians.get(i)+"\n";
            }
		}
        print += "\nMedical information: "+this.medical+"\nPExpelled: "+this.expulsion+"Reason for Expulsion: ";
        for (int i = 0; i < reasonForExpulsion.size(); i++) {
			if (reasonForExpulsion.get(i) != null) {
                print += reasonForExpulsion.get(i)+"\n";
            }
		}
        print += "\nSessions: ";
        for (int i = 0; i < sessions.size(); i++) {
			if (sessions.get(i) != null) {
                print += sessions.get(i)+"\n";
            }
		}
        return print;
    }
}