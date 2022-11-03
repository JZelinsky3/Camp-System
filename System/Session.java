package System;
import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores data on sessions the camp will be hosting
 */
public class Session {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int spotsLeft;
    private String theme;
    private ArrayList<Cabin> cabins;

    public Session(LocalDate startDate, LocalDate endDate) {
        id = UUID.randomUUID();
        this.startDate = startDate;
        this.endDate = endDate;
        cabins = new ArrayList<>();
    }

    public Session(UUID id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        cabins = new ArrayList<>();
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public ArrayList<Cabin> getCabins(){
        return this.cabins;
    }

    public UUID getUUID() {
        return this.id;
    }

    //adds themes and decription
    public String getTheme() {
        return this.theme;
    }

    public int getSpotsLeft() {
        return this.spotsLeft;
    }

    public void removeSpotsLeft() { 
        this.spotsLeft--;
    }

    public void setSpotsLeft(int spotsLeft) {
        this.spotsLeft = spotsLeft;
    }

    public boolean isSpots() {
        return cabins.size() < spotsLeft;
    }
    
    public void addCabins(ArrayList<Cabin> cabins) {
        this.cabins = cabins;
    }

    public void addTheme(String theme) {
        this.theme = theme;
    }

    public void addCabin(Cabin cabin) {
        this.cabins.add(cabin);
    }
    
    public String toString() {
        String print = "Start Date: "+this.startDate+"\nEnd Date: "+this.endDate+"\nSpots left: "+this.spotsLeft+"\nTheme: "+this.theme+"\nCabins: ";
        for (int i = 0; i < cabins.size(); i++) {
			if (cabins.get(i) != null) {
                print += cabins.get(i)+"\n";
            }
		}
        return print;
    }
}