package System; 
import java.util.ArrayList;

/**
 * Stores a list of campers saved in the system
 */
public class CamperList {
    private ArrayList<Camper> campers;
    private static CamperList camperList;

    private CamperList(){
        campers = DataReader.getAllCampers();
        camperList = this;
    }

    public static CamperList getInstance(){
        if (camperList == null) {
			camperList = new CamperList();
		}
		return camperList;    
    }

    public void addCamper(Camper camper){
        campers.add(camper);
    }

    public ArrayList<Camper> getCampers(){
        return campers;
    }

    public void saveCampers(){
        DataWriter.saveCampers();
    }
    
}
