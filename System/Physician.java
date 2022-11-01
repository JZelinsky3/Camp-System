package System;
/**
 * Stores information on a medical professional that may need to be contacted incase something happens to a user
 */
public class Physician {
    
    private String firstName;
    private String lastName;
    private int phoneNum;
    private String address;

    public Physician(String firstname, String lastname, String address, int phoneNum){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNum=phoneNum;
        this.address=address;
    }

    public String toString(){
        return "Name: "+this.firstName+" "+this.lastName+"\nPhone Number: "+this.phoneNum+"\nAddress: "+this.address;
    }
}
