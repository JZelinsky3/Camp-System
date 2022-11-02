package System;
import java.util.ArrayList;
/**
 * Lists users stored in the system
 */
public class UserList {
    private ArrayList<User> users;
    private static UserList userList;

    private UserList() {
        userList = this;
        users = DataReader.getAllUsers();
    }

    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		return userList;   
    }

    public User getUser(String userName) {
        for(User user : users)
        {
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }
    /*
     * Checks if a user has a given username and returns true if it is and false if it isn't
     */
    public boolean hasUser(String userName) {
        for(User user : users)
        {
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void editUser(User user, String newFirstName, String newLastName, String newUserName) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i) == user) {
                users.set(i,new User(newFirstName, newLastName, newUserName));
            }
        }
    }

    public void saveUsers() {
        DataWriter.saveUsers();
    }    
}