package System.test;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.Cabin;
import System.Session;

public class SessionTest {
    public Session session;

    @BeforeEach
	public void setup() {
		session = new Session(LocalDate.parse("2023-07-02"), LocalDate.parse("2023-07-09"));
	}
	
	@AfterEach
	public void tearDown() {
		session = null;
	}

    @Test
    public void testAvailableFalse(){
		session.setSpotsLeft(8);
		ArrayList<Cabin> cabins = new ArrayList<Cabin>();
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		session.addCabins(cabins);
        assertFalse(session.isSpots());
    }

	@Test
    public void testAvailableTrue(){
		session.setSpotsLeft(4);
		ArrayList<Cabin> cabins = new ArrayList<Cabin>();
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		cabins.add(new Cabin(10, 12));
		session.addCabins(cabins);
        assertTrue(session.isSpots());
    }
    
}