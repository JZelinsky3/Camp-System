package System.test;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.UriSelector;

import System.Cabin;
import System.CabinList;
import System.Camper;
import System.CamperList;
import System.Contact;
import System.Counselor;
import System.CounselorList;
import System.DataReader;
import System.DataWriter;
import System.Medical;
import System.Session;
import System.SessionList;
import System.Type;
import System.User;
import System.UserList;

class DataReaderTest {

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
        users.clear();
        
        User u = new User("Pett", "Hersh",
             "petty");
        u.addPassword("hershbaby");
        u.addEmail("hershy@gmail.com");
        u.addPhoneNumber("221-556-0891");
        u.addBirthday(LocalDate.parse("2010-05-10"));
        u.addAddress("1000 Johnny Lane, SC, USA");
        u.setType(Type.PARENT);

        userList.addUser(u);
        DataWriter.saveUsers();
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
    void testGetUsersSize() {
		users = DataReader.getAllUsers();
		assertEquals(1, users.size());
	}

    @Test
	void testGetUsersSizeZero() {
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
		assertEquals(0, users.size());
	}

    @Test
	void testGetUserBirthday() {
		users = DataReader.getAllUsers();
		assertEquals("2010-05-10", 
            users.get(0).getBirthday().toString());
	}

    @Test
    void testEmptyCampers() {
        users = DataReader.getAllUsers();
        assertEquals(0, users.get(0).getCampers().size());
    }

    @Test
    void testCamperAndUserID() {
        
        User u = new User("Jimmy", "Neutron",
                "jimmyN");
        u.addBirthday(LocalDate.parse("2015-01-02"));
        u.setType(Type.PARENT);
   
        Camper c = new Camper("Ben", "McLen", LocalDate.parse("2017-05-05"));
        Medical med = new Medical(new Contact("Tommy", "Jones", "810-551-8236"));
        c.addMedical(med);
        u.addCamper(c);

        userList.addUser(u);
        camperList.addCamper(c);
        DataWriter.saveUsers();
        DataWriter.saveCampers();

        campers = DataReader.getAllCampers();
        users = DataReader.getAllUsers();
    
        assertEquals(0,
            campers.get(0).getUUID().compareTo( 
            users.get(1).getCampers().get(0).getUUID() ));
    }

    @Test
    void testWritingCabinConstructor() {
        Cabin c = new Cabin(8, 10);
        cabinList.addCabin(c);
        DataWriter.saveCabins();

        cabins = DataReader.getAllCabins();
    
        assertEquals(1, cabins.size());
    }

    @Test
    void testWritingCamperConstructor() {
        Camper c = new Camper("Ben", "McLen", LocalDate.parse("2017-05-05"));

        camperList.addCamper(c);
        DataWriter.saveCampers();

        campers = DataReader.getAllCampers();
    
        assertEquals(1, campers.size());
    }
    
    @Test
    void testWritingSessionConstructor() {

        Session s = new Session(LocalDate.parse("2023-07-09"),
        LocalDate.parse("2023-07-16"));
        sessionList.addSession(s);
        
        DataWriter.saveSessions();
        sessions = DataReader.getAllSessions();
    
        assertEquals(1, sessions.size());
    }

    @Test
    void testWritingCounselorConstructor() {

        Counselor c = new Counselor("Jess", "Miller", "JessM");
        counselorList.addCounselor(c);
        
        DataWriter.saveCounselors();
        counselors = DataReader.getAllCounselors();
    
        assertEquals(1, counselors.size());
    }

    @Test
    void testWritingUserConstructor() {

        userList.addUser(new User("Jess", "Miller", "JessM"));
        
        DataWriter.saveUsers();
        users = DataReader.getAllUsers();
    
        assertEquals(2, users.size());
    }

    @Test
    void testWritingEachOneByOne() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
        int totalSize = 0;

        userList.addUser(new User("null", "void", "empty"));
        DataWriter.saveUsers();

        cabinList.addCabin(new Cabin(8, 10));
        DataWriter.saveCabins();

        camperList.addCamper(new Camper("null", "empty",
            LocalDate.parse("2023-08-12")));
        DataWriter.saveCampers();

        sessionList.addSession(new Session(LocalDate.parse("2023-08-01"),
            LocalDate.parse("2023-08-22")));
        DataWriter.saveSessions();

        counselorList.addCounselor(new Counselor("null", "null", "null"));
        DataWriter.saveCounselors();

        totalSize += DataReader.getAllSessions().size()
            + DataReader.getAllUsers().size() 
            + DataReader.getAllCabins().size() 
            + DataReader.getAllCampers().size() 
            + DataReader.getAllCounselors().size() ;
    
        assertEquals(5, totalSize);
    }
}