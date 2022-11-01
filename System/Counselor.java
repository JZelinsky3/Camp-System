import java.util.ArrayList;

public class Counselor {
    private ArrayList<Schedule> schedule;
    public Counselor(String birthdate, Bio bio, Medical medicallnfo, ArrayList<Integer> cabinNum) {
        super();
        this.cabinNum = cabinNum;
    }
    public void addCamperSchedule(Schedule schedule) {
        this.schedule.add(schedule);
    }
    public int getCabinNum(ArrayList cabinNum) {
        return cabinNum;
    }
}
