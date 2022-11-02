package System;
import java.util.ArrayList;

public class CabinList {
    private ArrayList<Cabin> cabins;
    private static CabinList cabinList;

    private CabinList(){
        cabins = DataReader.getAllCabins();
        cabinList = this;
    }

    public static CabinList getInstance(){
        if (cabinList == null) {
			cabinList = new CabinList();
		}
		return cabinList;    
    }

    public Cabin getCabin(int lowCabinAge, int maxCabinAge) {
        for(int i = 0; i < cabins.size(); i++) {
            if(cabins.get(i) == new Cabin(lowCabinAge, maxCabinAge)){
                return cabins.get(i);
            }
        }
        return null;
    }

    public ArrayList<Cabin> getCabins() {
        return cabins;
    }

    public boolean hasCabin(int lowCabinAge, int maxCabinAge) {
        for (int i = 0; i < cabins.size(); i++) {
            if(cabins.get(i) == new Cabin(lowCabinAge, maxCabinAge)) {
                return true;
            }
        }
        return false;
    }

    public boolean addCabin(Cabin cabin) {
        if(!hasCabin(cabin.getLowCabinAge(), cabin.getMaxCabinAge())) {
            cabins.add(cabin);
            return true;
        }
        return false;
    }

    public void editCabin(Cabin cabin, int minCabinAge, int maxCabinAge) {
        for (int i = 0; i < cabins.size(); i++) {
            if(cabins.get(i) == cabin) {
                cabins.set(i,new Cabin(minCabinAge, maxCabinAge));
            }
        }
    }

    //saves the CabinList
    public void saveCabins() {
        DataWriter.saveCabins();
    } 
}