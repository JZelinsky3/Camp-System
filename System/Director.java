package System;

import java.lang.reflect.Array;
import java.util.ArrayList;
/**
 * Stores information on the camp for the director to modify
 */
public class Director {
    
    private ArrayList<Schedule> schedules=new ArrayList<Schedule>();

    private ArrayList<Price> pricing=new ArrayList<Price>();

    private ArrayList<Capacity> availability=new ArrayList<Capacity>();

    private ArrayList<Expelled> expulsion=new ArrayList<Expelled>();

    private ArrayList<Scholarship> scholarships=new ArrayList<Scholarship>();

    public void addCamperSchedule(Schedule schedule){
        schedules.add(schedule);
    }

    public void addPrice(Price price){
        pricing.add(price);
    }

    public void addCapacity(Capacity capacity){
        availability.add(capacity);
    }

    public void addExpelled(Expelled expelled){
        expulsion.add(expelled);
    }

    public void removeExpelled(Expelled expelled){
        expulsion.remove(expelled);
    }

    public void addScholarships(Scholarship scholarship){
        scholarships.add(scholarship);
    }
}
