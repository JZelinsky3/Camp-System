package System;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Camper {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private ArrayList<Contact> emergencyContacts;
    private Medical medical;

    public Camper(String firstName, String lastName, Date birthday, Contact emergencyContact, Contact guardian){
        
    }

    public void addEmergencyContact(Contact emergencyContact) {

    }

    public void addGuardian(Contact guardian){

    }

    public String toString(){
        return "this is a camper";
    }

}