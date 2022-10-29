package System;
import java.util.ArrayList;
import java.util.UUID;


public class UserList {

    private ArrayList<User> user;
    private ArrayList<Counselor> counselors;
    private ArrayList<Camper> campers;

    private static UserList userList;

    private UserList() {
        user = DataReader.getAllUsers();
        counselors = DataReader.getAllCounselors();
        campers = DataReader.getAllCampers();
    }

    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		return userList;   
    }
    
    public User getUser(String userName) {
        return null;
    }

    public boolean hasUser(String userName) {
        return true;
    }

    public ArrayList<User> getUsers() {
        return null;
    }

    public boolean addUser(User user) {
        return true;
    }

    public void editUser(String userName, String password) {

    }

    public void saveUsers() {

    }

    
}