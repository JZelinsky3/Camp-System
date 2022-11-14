package System.test;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.Cabin;
import System.CabinList;
import System.Camper;
import System.CamperList;
import System.Counselor;
import System.CounselorList;
import System.DataReader;
import System.DataWriter;
import System.Session;
import System.SessionList;
import System.User;
import System.UserList;

class DataWriterTest {

    private UserList userList = UserList.getInstance();
    private CabinList cabinList = CabinList.getInstance();
    private CamperList camperList = CamperList.getInstance();
    private SessionList sessionList = SessionList.getInstance();
    private CounselorList counselorList = CounselorList.getInstance();
    
    private ArrayList<User> users = userList.getUsers();
    private ArrayList<Cabin> cabins = cabinList.getCabins();
    private ArrayList<Camper> campers = camperList.getCampers();
    private ArrayList<Session> sessions = sessionList.getSessions();
    private ArrayList<Counselor> counselors = counselorList.getCounselors();

    @BeforeEach
    public void setup() {
        UserList.getInstance().getUsers().clear();
        CabinList.getInstance().getCabins().clear();
        CamperList.getInstance().getCampers().clear();
        SessionList.getInstance().getSessions().clear();
        CounselorList.getInstance().getCounselors().clear();
        DataWriter.saveUsers();
        DataWriter.saveCabins();
        DataWriter.saveCampers();
        DataWriter.saveSessions();
        DataWriter.saveCounselors();
    }

    @AfterEach
	public void tearDown() {
        UserList.getInstance().getUsers().clear();
        CabinList.getInstance().getCabins().clear();
        CamperList.getInstance().getCampers().clear();
        SessionList.getInstance().getSessions().clear();
        CounselorList.getInstance().getCounselors().clear();
        DataWriter.saveUsers();
        DataWriter.saveCabins();
        DataWriter.saveCampers();
        DataWriter.saveSessions();
        DataWriter.saveCounselors();
	}

    @Test
	void testWritingZeroUsers() {
		users = DataReader.getAllUsers();
		assertEquals(0, users.size());
	}

    @Test
	void testWritingZeroCabins() {
		cabins = DataReader.getAllCabins();
		assertEquals(0, cabins.size());
	}

    @Test
	void testWritingZeroCampers() {
		campers = DataReader.getAllCampers();
		assertEquals(0, campers.size());
	}

    @Test
	void testWritingZeroSessions() {
		sessions = DataReader.getAllSessions();
		assertEquals(0, sessions.size());
	}

    @Test
	void testWritingZeroCounselors() {
		counselors = DataReader.getAllCounselors();
		assertEquals(0, counselors.size());
	}

    @Test
	void testWritingOneUser() {

        User u = new User("Jimmy", "Joe",
             "joe");
    
        userList.addUser(u);

        DataWriter.saveUsers();
		assertEquals("joe", 
            DataReader.getAllUsers().get(0).getUserName());
	}

	@Test
	void testWritingFiveUsers() {
		userList.addUser(new User("John", "Nose", "johnny"));
		userList.addUser(new User("Tom", "Hills", "hills"));
		userList.addUser(new User("Ryan", "Lockhart", "lockhart"));
		userList.addUser(new User("Ashley", "Jones", "Ajones"));
		userList.addUser(new User("Jessica", "Wright", "wrightleft"));
		
        DataWriter.saveUsers();

        assertEquals("esmith", DataReader.getAllUsers().get(4).getUserName());
	}

    @Test
	void testWritingOneCabin() {
        Cabin c = new Cabin(10, 12);
        cabinList.addCabin(c);

        DataWriter.saveCabins();
		assertEquals(12,
            DataReader.getAllCabins().get(0).getMaxCabinAge());
	}

    @Test
	void testWritingOneCamper() {
        Camper c = new Camper("Tina", "Fey", 
            LocalDate.parse("2023-07-02"));
        camperList.addCamper(c);

        DataWriter.saveCampers();
		assertEquals("Fey",
            DataReader.getAllCampers().get(0).getLastName());
	}

    @Test
	void testWritingOneSession() {
        Session s = new Session(LocalDate.parse("2023-07-02"),
            LocalDate.parse("2023-07-09"));
        sessionList.addSession(s);
        
        DataWriter.saveSessions();
		assertEquals("2023-07-09",
            DataReader.getAllSessions().get(0).getEndDate().toString());
	}

    @Test
	void testWritingOneCounselor() {
        Counselor c = new Counselor("Paul", "Allen", "Paulie");
        counselorList.addCounselor(c);
        
        DataWriter.saveCounselors();
		assertEquals("Allen",
            DataReader.getAllCounselors().get(0).getLastName());
	}

	@Test
	void testWritingEmptyUser() {
		userList.addUser(new User("", "", ""));
		DataWriter.saveUsers();
		
        assertEquals("", DataReader.getAllUsers().get(0).getUserName());
	}

    @Test
	void testWritingNullUser() {
		User us = new User(null, null, null);
        
		userList.addUser(us);
        DataWriter.saveUsers();

        assertEquals(null, 
            DataReader.getAllUsers().get(0).getPhoneNumber());
	}

    @Test
	void testWritingEmptyCabin() {        
		cabinList.addCabin(new Cabin(0,0));
        DataWriter.saveCabins();

        assertEquals(0, 
            DataReader.getAllCabins().get(0).getCampers().size());
	}

    @Test
	void testWritingNullCamper() {
		Camper ca = new Camper(null, null, null);
        
		camperList.addCamper(ca);
        DataWriter.saveCampers();

        assertEquals(null, 
            DataReader.getAllCampers().get(0).getBirthday());
	}
    
    @Test
	void testWritingNullSession() {
		Session se = new Session(null, null);

		sessionList.addSession(se);
        DataWriter.saveSessions();

        assertEquals(null, 
            DataReader.getAllSessions().get(0).getTheme());
	}

    @Test
	void testWritingNullCounselor() {
		Counselor cs = new Counselor(null, null, null);
        
		counselorList.addCounselor(cs);
        DataWriter.saveCounselors();

        assertEquals(null, 
            DataReader.getAllCounselors().get(0).getPhoneNumber());
	}

    @Test
    void testWritingEmptyCamper() {
        camperList.addCamper(new Camper("", "", null));
        DataWriter.saveCampers();
        assertEquals("", 
            DataReader.getAllCampers().get(0).getLastName());
    }
}