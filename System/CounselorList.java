package System;

import java.util.ArrayList;

public class CounselorList {
    
    private ArrayList<Counselor> counselors=new ArrayList<Counselor>();

    private ArrayList<Cabin> cabin= new ArrayList<>();

    private CounselorList counselorList;

    private CounselorList(){

    }

    public CounselorList getInstance(){
        return counselorList;
    }

    public void addCounselor(Counselor counselor){

    }

    public Counselor getCounselor(Counselor counselor){
        return counselor;
    }

    public void editCounselor(Counselor counselor){

    }

    public void saveCounselor(Counselor counselor){
        
    }
}
