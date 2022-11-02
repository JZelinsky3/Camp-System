package System;

/**
 * Contact information for a user
 */
public class Contact {
    //private UUID id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String relationship;

    public Contact(String firstName, String lastName, String phoneNumber, String relationship) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.relationship = relationship;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getRelationship() {
        return this.relationship;
    }

    public String toString() {
        return "Name: "+this.firstName+" "+this.lastName+"\nPhone number: "+this.phoneNumber+"\nRelationship :"+this.relationship;
    }
}