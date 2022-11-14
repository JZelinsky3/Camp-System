package System.test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import System.User;
import System.UserList;

public class UserListTest {
    public UserList userList;

    @BeforeEach
	public void setup() {
        userList = UserList.getInstance();
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("Timmy", "Buckets", "timbo"));
        users.add(new User("Buck", "Jones", "jonesBuck"));
        users.add(new User("Shocker", "Adams", "ShockCity"));
        userList.setUsers(users);
	}
	
	@AfterEach
	public void tearDown() {
		userList = null;
	}

    @Test
    public void testHasUserFalse(){
        assertFalse(userList.hasUser("timmmmmmy"));
    }

    @Test
    public void testHasUserTrue(){
        assertTrue(userList.hasUser("timbo"));   
    }
    
}