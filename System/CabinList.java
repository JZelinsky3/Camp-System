package System;

import java.util.ArrayList;

public class CabinList {
    
    private ArrayList<Cabin> cabins=new ArrayList<Cabin>();

    private CabinList cabinList;

    private CabinList(){
        
    }

    public CabinList getInstance(){
        return null;
    }

    public void addCabin(Cabin cabin){
        cabins.add(cabin);
    }

    public Cabin getCabin(Cabin cabin){
        return cabins.get(cabins.indexOf(cabin));
    }

    public void editCabin(Cabin cabin){

    }

    public void saveCabin(Cabin cabin){
        
    }
}
