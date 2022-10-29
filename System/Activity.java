package System;
import java.util.ArrayList;

public class Activity {
    private String title;
    private String location;
    private String startTime;
    private String endTime;
    private ArrayList<String> notes = new ArrayList<String>();

    public Activity(String title, String location){
        this.title = title;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public void addNotes(String note) {
        notes.add(note);
    }
    
    public void removeNotes(String note){
        notes.remove(note);
    }
    
    public String viewTime(){
        return this.startTime+"-"+this.endTime;
    }
    
    public void changeTime(String time){
        
    }
    
    public int calculateDuration(){
        return 0;
    }
    
    public String toString(){
        return "Title: "+this.title+" Locations: "+this.location+" Start Time: "+this.startTime+" End Time: "+this.endTime;
    }
    
}