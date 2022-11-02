package System;
/**
 * Description of a user
 */
public class Bio {
    private String firstName;
    private String lastName;
    private String birthdate;
    private String gender;
    private String hometown;

    public Bio(String firstName, String lastName, String birthdate, String gender, String hometown){
        this.firstName=firstName;
        this.lastName=lastName;
        this.birthdate=birthdate;
        this.gender=gender;
        this.hometown=hometown;
    }

    public String toString(){
        return "Name: "+firstName+" "+lastName+"\nDate of Birth: "+birthdate+"\nGender: "+gender+"\nHometown: "+hometown;
    }
}
