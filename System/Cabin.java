package System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Random;

/**
 * Stores data on the campers, age range, and schedule assigned to a cabin
 */
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
        Day days[] = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};
        
        Activity breakf = new Activity("Breakfast", "Cafeteria");
        breakf.addStartTime("09:00");
        breakf.addEndTime("09:45");
        Activity lunch = new Activity("Lunch", "Cafeteria");
        lunch.addStartTime("12:30");
        lunch.addEndTime("13:15");
        Activity dinner = new Activity("Dinner", "Cafeteria");
        dinner.addStartTime("18:30");
        dinner.addEndTime("19:15");
        int number = 0;
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            ArrayList<Activity> template = new ArrayList<Activity>();
            Activity a1 = new Activity("Archery", "Field");
            Activity a2 = new Activity("Swimming Time", "Pool");
            Activity a3 = new Activity("Fishing", "Lake");
            Activity a4 = new Activity("Arts and Crafts", "Recreation Center");
            Activity a5 = new Activity("Card Games", "Recreation Center");
            Activity a6 = new Activity("Scavenger Hunt", "Forest");
            Activity a7 = new Activity("Relay Race", "Field");
            Activity a8 = new Activity("Capture the Flag", "Field");
            Activity a9 = new Activity("Game Time", "Game Center");
            Activity a10 = new Activity("Talent Show", "Recreation Center");
            Activity a11 = new Activity("Forest Hike", "Forest");
            template.add(a1);
            template.add(a2);
            template.add(a3);
            template.add(a4);
            template.add(a5);
            template.add(a6);
            template.add(a7);
            template.add(a8);
            template.add(a9);
            template.add(a10);
            template.add(a11);
            ArrayList<Activity> schedule = new ArrayList<Activity>();

            schedule.add(breakf);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("09:00");
            template.get(number).addEndTime("10:30");
            schedule.add(template.get(number));
            template.remove(number);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("11:00");
            template.get(number).addEndTime("11:45");
            schedule.add(template.get(number));
            template.remove(number);

            schedule.add(lunch);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("13:00");
            template.get(number).addEndTime("15:00");
            schedule.add(template.get(number));
            template.remove(number);
            
            number = rand.nextInt(template.size());
            template.get(number).addStartTime("15:30");
            template.get(number).addEndTime("17:30");
            schedule.add(template.get(number));
            template.remove(number);

            schedule.add(dinner);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("19:00");
            template.get(number).addEndTime("18:00");
            schedule.add(template.get(number));
            template.remove(number);

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

    public void addMaxCapacity(int addMaxCapacity) {
        this.CabinCapacity = addMaxCapacity;
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
            print += "\n";
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