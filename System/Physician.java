package System;

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
}
