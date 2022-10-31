import java.util.ArrayList;

public class Counselor extends Staff {
    private ArrayList<Schedule> schedule;
    public Counselor(String birthdate, Bio bio, Medicallnfo medicallnfo, ArrayList<Integer> cabinNum) {
        super(birthdate, bio, medicallnfo);
        this.cabinNum = cabinNum;
    }
    public void addCamperSchedule(Schedule schedule) {
        this.schedule.add(schedule);
    }
    public int getCabinNum(ArrayList cabinNum) {
        return cabinNum;
    }
}
