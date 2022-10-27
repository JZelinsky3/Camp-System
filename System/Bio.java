package System;

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
        return "Name: "+firstName+" "+lastName+" Date of Birth: "+birthdate+" Gender: "+gender+" Hometown: "+hometown;
    }
}
