package System;
import java.util.ArrayList;

public class Schedule {
    private ArrayList<Activity> activities;

    public Schedule(ArrayList<Activity> activities){ 
        this.activities = activities;
    }

    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    public ArrayList<Activity> getActivities() {
        return this.activities;
    }

    public void removeActivity(Activity activity) {
        for(int i = 0; i < this.activities.size(); i++) {
            if(this.activities.get(i) == activity) {
                this.activities.remove(i);
            }
        }
    }

    public String toString(){
        String print = new String();
        for(Activity a : activities){
            print += a.toString() + "\n";
        }
        return print;
    }
}