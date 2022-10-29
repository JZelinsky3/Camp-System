import java.util.ArrayList;

public class CamperList {
    
    private ArrayList<Camper> campers=new ArrayList<Camper>();

    private CamperList camperList;

    private CamperList(){
        
    }

    public CamperList getInstance(){
        return camperList;
    }

    public void addCamper(Camper camper){
        campers.add(camper);
    }

    public Camper getCamper(Camper camper){
        return campers.get(campers.indexOf(camper));
    }

    public void editCamper(Camper camper){

    }

    public void saveCamper(Camper camper){
        
    }
}
