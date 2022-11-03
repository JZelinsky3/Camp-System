package System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Random;

//assigns cabins schedule, checks camper age range, and stores activities
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
        
        Activity bre = new Activity("Breakfast", "Cafe");
        bre.addStartTime("09:00");
        bre.addEndTime("09:45");
        Activity lun = new Activity("Lunch", "Cafe");
        lun.addStartTime("12:30");
        lun.addEndTime("13:15");
        Activity din = new Activity("Dinner", "Cafe");
        din.addStartTime("18:30");
        din.addEndTime("19:15");
        int number = 0;
        Random rand = new Random();
        for (int i = 0; i < 7; i++) {
            ArrayList<Activity> template = new ArrayList<Activity>();
            Activity a1 = new Activity("Baseball", "Backyard");
            Activity a2 = new Activity("Obstacle Course", "Backyard");
            Activity a3 = new Activity("River Fun", "River");
            Activity a4 = new Activity("Movie Night", "Rec Hall");
            Activity a5 = new Activity("Board Games", "Rec Hall");
            Activity a6 = new Activity("Scavenger Hunt", "Backyard");
            Activity a7 = new Activity("Relay Race", "Backyard");
            Activity a8 = new Activity("Capture the Flag", "Backyard");
            Activity a9 = new Activity("Potato Sack Relay", "Backyard Center");
            Activity a10 = new Activity("Talent Show", "Rec Hall");
            Activity a11 = new Activity("Hiking", "Woods");
            Activity a12 = new Activity("Basketball", "Field House");
            Activity a13 = new Activity("S'mores", "Campfire");
            Activity a14 = new Activity("Tubing", "River");
            Activity a15 = new Activity("DIY", "Rec Hall");
            Activity a16 = new Activity("Volleyball", "Field House");
            Activity a17 = new Activity("Story Telling", "Campfire");
            Activity a18 = new Activity("Tug of War", "Backyard");
            Activity a19 = new Activity("Ultimate Frisbee", "Backyard");
            Activity a20 = new Activity("Hide and Seek", "Backyard");
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
            template.add(a12);
            template.add(a13);
            template.add(a14);
            template.add(a15);
            template.add(a16);
            template.add(a17);
            template.add(a18);
            template.add(a19);
            template.add(a20);
            ArrayList<Activity> schedule = new ArrayList<Activity>();

            schedule.add(bre);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("10:15");
            template.get(number).addEndTime("11:00");
            schedule.add(template.get(number));
            template.remove(number);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("11:30");
            template.get(number).addEndTime("12:15");
            schedule.add(template.get(number));
            template.remove(number);

            schedule.add(lun);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("13:45");
            template.get(number).addEndTime("15:30");
            schedule.add(template.get(number));
            template.remove(number);
            
            number = rand.nextInt(template.size());
            template.get(number).addStartTime("16:00");
            template.get(number).addEndTime("18:00");
            schedule.add(template.get(number));
            template.remove(number);

            schedule.add(din);

            number = rand.nextInt(template.size());
            template.get(number).addStartTime("19:45");
            template.get(number).addEndTime("21:15");
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

    public Cabin(UUID fromString, int intValue) {
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
        String print = "Age Range for Cabin: "+this.lowCabinAge+" to "+this.maxCabinAge+"\nCapacity for Cabin: "+this.CabinCapacity;
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