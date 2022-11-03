package System;

import java.util.ArrayList;

public class Director extends User{
    private ArrayList<String> counselorNames;

    public Director(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
        counselorNames = new ArrayList<>();
    }

    //gives the director power to expell a camper
    public void giveExpulsion(Camper camper, String reason) {
        for (int i = 0; i < campers.size(); i++) {
            if(campers.get(i) == camper) {
                camper.giveExpulsion(reason); 
            }
        } 
    }   

    public String toString() {
        String print = super.toString()+"\nCounselor names: ";
        for (int i = 0; i < counselorNames.size(); i++) {
			if (counselorNames.get(i) != null) {
                print += counselorNames.get(i)+"\n";
            }
		}
        return print;
    }

    //makes sure all counselors are 18 or older
    public boolean verifyCounselor(User counselor) {
        if(counselor.calculateAge() >= 18) {
            return true;
        }
        return false;
    }
}