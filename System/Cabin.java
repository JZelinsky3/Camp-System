package System;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Cabin {
    private UUID id;
    private int cabinAge;
    private int CabinCapacity = 15;
    private ArrayList<Camper> campers;
    private HashMap<Day, Schedule> schedules;

    public Cabin(int cabinAge) {
        this.cabinAge = cabinAge;
    }

    public Cabin(UUID id, int cabinAge) {
        this.id = id;
        this.cabinAge = cabinAge;
    }

    public UUID getUUID() {
        return id;
    }

    public int getCabinAge(){
        return cabinAge;
    }

    public int getCabinCapacity(){
        return CabinCapacity;
    }

    public ArrayList<Camper> getCampers(){
        return campers;
    }

    public HashMap<Day, Schedule> getSchedules() {
        return this.schedules;
    }

    public void addMaxCapacity(int maxCapacity) {
        this.CabinCapacity = maxCapacity;
    }

    public void addCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    public void addCamper(Camper camper) {
        this.campers.add(camper);
    }

 
    public void addSchedules(HashMap<Day, Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule, Day day) {
        schedules.put(day, schedule);
    }

    public String viewSchedules() {
        String print = "";
        for (Day day : schedules.keySet()) {
            print += day;
            print += schedules.get(day)+"\n";
        }
        return print;
    }

    public void editSchedule(Schedule oldSchedule, Schedule newSchedule) {
        for (Day day : this.schedules.keySet()) {
            if(schedules.get(day) == oldSchedule) {
                schedules.replace(day, newSchedule);
            }
        }
    }

    public boolean hasAvailableBed() {
        return campers.size() < this.CabinCapacity;
    }

    public String toString() {
        String print = "Cabin age: "+this.cabinAge+"\nMaximum number of campers: "+this.CabinCapacity;
        for (int i = 0; i < campers.size(); i++) {
			if (campers.get(i) != null) {
                print += campers.get(i)+"\n";
            }
		}
        print += "\nSchedules: ";
        for (Day day : schedules.keySet()) {
            print += day+", ";
            print += schedules.get(day)+"\n";
        }
        return print;
    }
}