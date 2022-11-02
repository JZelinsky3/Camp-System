package System;
import java.util.ArrayList;

//Stores medical information on a student
public class Medical {
    private Contact physician;
    private ArrayList <String> allergies;
    private ArrayList <Treatment> treatments;

    public Medical(Contact physician) {
        this.physician = physician;
        allergies = new ArrayList<>();
        treatments = new ArrayList<>();
    }

    public void addAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }

    public void addTreatments(ArrayList<Treatment> treatments) {
        this.treatments = treatments;
    }

    public void addTreatments(Treatment treatment) {
        this.treatments.add(treatment);
    }
  
    public Contact getPhysician() {
        return this.physician;
    }

    public ArrayList<String> getAllergies() {
        return this.allergies;
    }

    public ArrayList<Treatment> getTreatments() {
        return this.treatments;
    }

    public String toString() {
        String print = "Physician: "+this.physician+"\nAllergies: ";
        for (int i = 0; i < this.allergies.size(); i++) {
			if (this.allergies.get(i) != null) {
                print += this.allergies.get(i)+"\n";
            }
		}
        print += "\nTreatments: ";
        for (int i = 0; i < this.treatments.size(); i++) {
			if (this.treatments.get(i) != null) {
                print += this.treatments.get(i)+"\n";
            }
		}
        return print;
    }
}