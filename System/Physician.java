package System;
//Stores information on a medical professional that may need to be contacted incase something happens to a user
public class Physician {
    
    private String firstName;
    private String lastName;
    private int phoneNumber;

    public Physician(String firstname, String lastname, int phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
    }

    public String toString(){
        return "Name: "+this.firstName+" "+this.lastName+"\nPhone Number: "+this.phoneNumber;
    }
}
