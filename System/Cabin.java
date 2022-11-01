import java.util.ArrayList;

public class Cabin{
    private ArrayList<Camper>  camper;
    private ArrayList<Counselor> counselor;
    private ArrayList<Schedule> schedule;
    
    Cabin(ArrayList<Camper> campers,ArrayList<Counselor> counselors,ArrayList<Schedule> schedules){
        this.camper=campers;
        this.counselor=counselors;
        this.schedule=schedules;
    }
    
    public void addToCabin(User user){
        
    }
    
    public void removeFromCabin(User user){
        
    }
}
