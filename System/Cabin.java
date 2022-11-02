package System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Random;

public class Cabin {
    private UUID id;
    private int lowCabinAge;
    private int maxCabinAge;
    private int CabinCapacity = 15;
    private ArrayList<Camper> campers;
    private HashMap<Day, Schedule> schedules;

    public Cabin(int lowCabinAge, int maxCabinAge) {
        id = UUID.randomUUID();
        campers = new ArrayList<>();
        schedules = new HashMap<Day, Schedule>();
        this.lowCabinAge = lowCabinAge;
        this.maxCabinAge = maxCabinAge;
        Day days[] = {Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY};
        ArrayList<Activity> template = new ArrayList<Activity>();
        template.add(new Activity("Archery", "Field"));
        template.add(new Activity("Swimming Time", "Pool"));
        template.add(new Activity("Fishing", "Lake"));
        template.add(new Activity("Arts and Crafts", "Recreation Center"));
        template.add(new Activity("Card Games", "Recreation Center"));
        template.add(new Activity("Scavenger Hunt", "Forest"));
        template.add(new Activity("Relay Race", "Field"));
        template.add(new Activity("Capture the Flag", "Field"));
        template.add(new Activity("Game Time", "Game Center"));
        template.add(new Activity("Talent Show", "Recreation Center"));
        template.add(new Activity("Forest Hike", "Forest"));
        ArrayList<Activity> schedule = new ArrayList<Activity>();
        int number = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;
        int numberLoop = -1;
        Random rand = new Random();
        number = rand.nextInt(template.size());
        schedule.add(template.get(number));
        for (int i = 0; i < 7; i++) {
            schedule.add(new Activity("Breakfast", "Cafe"));
            while (numberLoop == -1) {
                number2 = rand.nextInt(template.size());
                if (number2 != number) {
                    schedule.add(template.get(number2));
                    numberLoop = 1;
                }
            }
            schedule.add(new Activity("Lunch", "Cafe"));
            numberLoop = -1;
            while (numberLoop == -1) {
                number3 = rand.nextInt(template.size());
                if (number3 != number2 && number3 != number) {
                    schedule.add(template.get(number3));
                    numberLoop = 1;
                }
            }
            numberLoop = -1;
            while (numberLoop == -1) {
                schedule.add(template.get(number));
                number4 = rand.nextInt(template.size());
                if (number4 != number3 && number4 != number2 && number4 != number) {
                    schedule.add(template.get(number4));
                    numberLoop = 1;
                }
            }
            schedule.add(new Activity("Dinner", "Cafe"));
            numberLoop = -1;
            while (numberLoop == -1) {
                number5 = rand.nextInt(template.size());
                if (number5 != number4 && number5 != number3 && number5 != number2 && number5 != number) {
                    schedule.add(template.get(number5));
                    numberLoop = 1;
                }
            }
            schedules.put(days[i], new Schedule(schedule));
        }
    }

    public Cabin(UUID id, int lowCabinAge, int maxCabinAge) {
        this.id = id;
        this.lowCabinAge = lowCabinAge;
        this.maxCabinAge = maxCabinAge;
        campers = new ArrayList<>();
        schedules = new HashMap<>();
    }

    public UUID getUUID() {
        return id;
    }

    public int getLowCabinAge(){
        return this.lowCabinAge;
    }

    public int getMaxCabinAge(){
        return maxCabinAge;
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
        String print = new String();
        for(Day day : Day.values()){
            print += day.toString() + ": \n";
            print += schedules.get(day).toString();
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
        String print = "Cabin Age Range: "+this.lowCabinAge+" to "+this.maxCabinAge+"\nMaximum number of campers: "+this.CabinCapacity;
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