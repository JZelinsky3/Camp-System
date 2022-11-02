package System;
import java.time.LocalDate;
import java.util.ArrayList;

public class SessionList {
    private ArrayList<Session> sessions;
    private static SessionList sessionList;

    //initializes instance of the SessionList class
    private SessionList() {
        sessions = DataReader.getAllSessions();
        sessionList = this;
    }

    public ArrayList<Session> getSessions(){
        return this.sessions;
    }

    public static SessionList getInstance(){
        if (sessionList == null) {
			sessionList = new SessionList();
		}
		return sessionList;    
    }

    public Session getSession(LocalDate startDate, LocalDate endDate) {
        for(int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == new Session(startDate, endDate)){
                return sessions.get(i);
            }
        }
        return null;
    }

    public boolean hasSession(LocalDate startDate, LocalDate endDate){
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == new Session(startDate, endDate)) {
                return true;
            }
        }
        return false;
    }

    public boolean addSession(Session session) {
        if(!hasSession(session.getStartDate(), session.getEndDate())) {
            sessions.add(session);
            return true;
        }
        return false;
    }

    public void editSession(Session session, LocalDate newStartDate, LocalDate newEndDate){
        for (int i = 0; i < sessions.size(); i++) {
            if(sessions.get(i) == session) {
                sessions.set(i,new Session(newStartDate, newEndDate));
            }
        }
    }

    //saves the SessionList
    public void saveSessions(){
        DataWriter.saveSessions();
    }
}