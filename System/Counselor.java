package System;

public class Counselor extends User{
    private String description;
    private ArrayList<Cabin> cabins;
    private ArrayList<Schedule> schedule = new ArrayList<Schedule>;

    public Counselor(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
    }

    public void addDescription(String description) {

    }

    public addCamperSchedule(Schedule schedule){
        return schedule;
    }

    public String toString() {
        return "";
    }
}