package System;
import java.util.ArrayList;

/**
 * Lists the activies a user will participate in
 */
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
        String print = "Activities: ";
        for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i) != null) {
                print += activities.get(i)+"\n";
            }
		}
        return print;
    }
}