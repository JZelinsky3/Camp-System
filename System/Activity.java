package System;
import java.time.Duration;
import java.util.ArrayList;

public class Activity {
    private String title;
    private String location;
    private String startTime;
    private String endTime;
    private ArrayList<String> notes = new ArrayList<String>();

    public Activity(String title, String location, String startTime, String endTime){
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
        int hourStringToInt=startTime.indexOf(":");
        String hour=startTime.substring(0, hourStringToInt);
        int hoursToMinutes=Integer.parseInt(hour)*60;
        int minutesStringToInt=Integer.parseInt(startTime.substring(hourStringToInt));
        int startTimeInt=hoursToMinutes+minutesStringToInt;
        hourStringToInt=endTime.indexOf(":");
        hour=endTime.substring(0, hourStringToInt);
        hoursToMinutes=Integer.parseInt(hour)*60;
        minutesStringToInt=Integer.parseInt(endTime.substring(hourStringToInt));
        int endTimeInt=hoursToMinutes+minutesStringToInt;
        int duration=endTimeInt-startTimeInt;
        return duration;
    }
    
    public String toString(){
        return "Title: "+this.title+" Locations: "+this.location+" Start Time: "+this.startTime+" End Time: "+this.endTime;
    }
    
}