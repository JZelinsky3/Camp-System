package System;
import java.util.ArrayList;

/**
 * Stores a list of counselors saved in the system
 */
public class CounselorList {

    private ArrayList<Counselor> counselors;
    private static CounselorList counselorList;

    private CounselorList() {  
        counselors = DataReader.getAllCounselors();
        counselorList = this;
    }

    public static CounselorList getInstance(){
        if(counselorList == null){
            counselorList = new CounselorList();
        }
        return counselorList;
    }

    public Counselor getCounselor(String username){
        for(Counselor c : counselors){
            if(username.equals(c.getUserName())){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Counselor> getCounselors(){
        return counselors;
    }

    public void addCounselor(Counselor counselor) {
        counselors.add(counselor);
    }

    public void saveCounselors(){
        DataWriter.saveCounselors();
    }
}