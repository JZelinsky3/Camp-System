package System;
import java.util.UUID;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

//Stores data on a user
public class User {
    protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String userName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected LocalDate birthday;
    protected String address;
    protected Type type;
    protected ArrayList<Camper> campers;

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    public User(UUID id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return this.id;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getUserName() {
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }
    public LocalDate getBirthday() {
        return this.birthday;
    }
    public String getAddress() {
        return this.address;
    }
    public Type getType() {
        return this.type;
    }
    public ArrayList<Camper> getCampers() {
        return this.campers;
    }
    public void addCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }
    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void addPassword(String password){
        this.password = password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void addEmail(String email) {
        this.email = email;
    }
    public void addBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public void addCamper(Camper camper) {
        this.campers.add(camper);
    }
    public void addAddress(String address) {
        this.address = address;
    }
    public void setType(Type type) {
        this.type = type;
    }
    protected int calculateAge() {    
        LocalDate currentDate = LocalDate.now();  
        return Period.between(this.birthday, currentDate).getYears();
    }
    public void enrollCamper(Camper camper) {
        this.campers.add(camper);
    }

    public String toString() {
        String print = "Name :"+this.firstName+" "+this.lastName+"\nUsername :"+this.userName+"\nPassword :"+this.password+"\nEmail: "+this.email+
        "\nPhone Number: "+this.phoneNumber+"\nAddress: "+this.address+"\nType of account: "+this.type+"\nCampers: ";
        for (int i = 0; i < campers.size(); i++) {
			if (campers.get(i) != null) {
                print += campers.get(i)+"\n";
            }
		}
        return print;
    }  
}