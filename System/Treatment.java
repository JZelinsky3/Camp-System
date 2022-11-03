package System;

//Stores data on a treatment a user might need
public class Treatment {
    private String name;
    private String description;
    private String time;

    public Treatment(String name, String description, String time){
        this.name = name;
        this.description = description;
        this.time = time; 
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getTime() {
        return this.time;
    }

    public String toString(){
        return "Name "+this.name+"\nDescription: "+this.description+"\nTime: "+this.time;
    }
}