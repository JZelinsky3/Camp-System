package System;

import java.util.UUID;

public class Treatment {
    private UUID id;
    private String name;
    private String details;
    private String quantity;
    private String sinceExposure;
    private String directions;

    public Treatment(String name, String quantity, String sinceExposure, String directions){
        this.details = name;
        this.quantity = quantity;
        this.sinceExposure = sinceExposure;
        this.directions = directions;

    }
    public String toString(){
        return " ";
    }


}