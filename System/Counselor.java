package System;
import java.util.ArrayList;
import java.util.UUID;

public class Counselor extends User{
    private String bio;
    private ArrayList<Cabin> cabins;
    private Medical medical;

    public Counselor(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
        cabins = new ArrayList<>();
    }

    public Counselor(UUID id, String firstName, String lastName, String userName) {
        super(id, firstName, lastName, userName);
        cabins = new ArrayList<>();
    }

    public void addBio(String bio) {
        this.bio = bio;
    }

    public void addMedical(Medical medical) {
        this.medical = medical;
    }

    public void addCabins(ArrayList<Cabin> cabins) {
        this.cabins = cabins;
    }

    public String getBio() {
        return this.bio;
    }

    public ArrayList<Cabin> getCabins() {
        return this.cabins;
    }

    public Medical getMedical() {
        return this.medical;
    }

    public String toString() {
        String print = super.toString()+"Bio: "+this.bio+"\nCabins: ";
        for (int i = 0; i < cabins.size(); i++) {
			if (cabins.get(i) != null) {
                print += cabins.get(i)+"\n";
            }
		}
        return print+="\nMedical info: "+this.medical;
    }
}