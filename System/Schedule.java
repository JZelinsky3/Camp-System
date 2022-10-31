import java.util.ArrayList;

public class Schedule {

    private Cabin cabin;    
    private ArrayList<Activity> activities; 
    private int breakfast;  
    private int lunch;    
    private int dinner;   

    public Schedule(Cabin cabin, ArrayList<Activity> activities, int breakfast, int lunch, int dinner) {    
        this.cabin = cabin; 
        this.activities = activities; 
        this.breakfast = breakfast;   
        this.lunch = lunch;   
        this.dinner = dinner; 
    }

    public void editSchedule(ArrayList<String> activities) {
        
    }
}

