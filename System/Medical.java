package System;
import java.util.ArrayList;

public class Medical {
    
    private Contact physician;
    private ArrayList<String> allergies=new ArrayList<String>();
    private ArrayList<Treatment> treatments=new ArrayList<Treatment>();

    public Medical(Contact physician) {
        this.physician=physician;
    }

    public void addAllergy(String allergy) {
        allergies.add(allergy);
    }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public String toString() {
        return "";
    }


}