package System;

<<<<<<< HEAD
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
=======
import java.time.LocalDate;
import java.util.UUID;

public class Session {
    
    private UUID id;

    private LocalDate startDate;

    private LocalDate endDate;

    private int ageGroup;

    private String theme;

    private int availableSports;

    public UUID getUuid(){
        return id;
    }

    public Session(LocalDate startDate, LocalDate endDate, int ageGroup){
        this.startDate=startDate;
        this.endDate=endDate;
        this.ageGroup=ageGroup;
    }
}
>>>>>>> edf40814e97daf1cf2c3888310f54499101243b3
