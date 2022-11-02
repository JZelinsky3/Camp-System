package System;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.Duration;

public class Activity {
    private String title;
    private String location;
    private String startTime;
    private String endTime;
    private ArrayList<String> info;

    public Activity(String title, String location){
        this.title = title;
        this.location = location;
        info = new ArrayList<>();
    }

    public void addStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void addEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void addInfo(ArrayList<String> info){
        this.info = info;
    }

    public void removeNote(String info){
        for(int i = 0; i < this.info.size(); i++) {
            if(this.info.get(i) == info) {
                this.info.remove(i);
            }
        }
    }

    public String viewTime(){
        return "The activity starts "+this.startTime+" and ends at "+this.endTime+".";
    }

    public long calculateDuration(){
        LocalTime start = LocalTime.of(Integer.parseInt(startTime.substring(0, 1)), Integer.parseInt(startTime.substring(3, 4)), 0);
        LocalTime end = LocalTime.of(Integer.parseInt(endTime.substring(0, 1)), Integer.parseInt(endTime.substring(3, 4)), 0);
        return Duration.between(start, end).toMinutes();
    }

    public String getTitle() {
        return this.title;
    }

    public String getLocation() {
        return this.location;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public ArrayList<String> getInfo() {
        return this.info;
    }

    public String toString(){
        return this.startTime+" to "+ this.endTime + ": " + this.title + " at " + this.location;
    }
    
}