package System;

import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;

public class Session {

    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int ageGroup;
    private int availableSpots;
    private ArrayList<Cabin> cabins;

    public Session(LocalDate startDate, LocalDate endDate, int ageGroup) {

    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    public Session(UUID id, LocalDate startDate, LocalDate endDate, int ageGroup) {
        this.id = id;
    }

    public void setAvailableSpots(int availableSpots) {

    }

    public UUID getUUID() {
        return id;
    }
    
    /**
     * 
     * @return
     */
    public boolean isAvailable() {
        return true;
    }
    
    public int viewAgeGroup() {
        return 0;
    }
    
    public void addCabins(ArrayList<Cabin> cabins) {
        ;
    }

    public void addCabin(Cabin cabin) {
        ;
    }

    public String toString() {
        return "";
    }
}