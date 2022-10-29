package System;

import java.util.ArrayList;

public class CounselorList {
    
    private ArrayList<Counselor> counselors=new ArrayList<Counselor>();

    private ArrayList<Cabin> cabin= new ArrayList<>();

    private CounselorList counselorList;

    public CounselorList getInstance(){
        counselorList=new CounselorList();
        counselorList.CounselorList();
        return counselorList;
    }

    private void CounselorList() {
    }

    public void addCounselor(Counselor counselor){
        counselors.add(counselor);
    }

    public Counselor getCounselor(Counselor counselor){
        return counselors.get(counselors.indexOf(counselor));
    }

    public void editCounselor(Counselor counselor){
        
    }

    public void saveCounselor(Counselor counselor){
        
    }
}
