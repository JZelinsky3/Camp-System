import java.util.ArrayList;

public class UserData {
    private ArrayList<User> users;
    private UserData() {
        users = new ArrayList<User>();
    }
    public static UserData getInstance() {
        return new UserData();
    }
    public void addUser(String firstName, String lastName, String userName, String email, String password) {
        users.add(new User(firstName, lastName, userName, email, password));
    }
    public User getUser(String userName) {
        for (User u : users) { 
            if (u.getUserName().equals(userName)) { 
                return u; 
            } 
        } 
        return null;
    }
    public void editUser(String userName, String firstName, String lastName, String email, String password) { 
        User u = getUser(userName); u.setFirstName(firstName); u.setLastName(lastName); u.setEmail(email); u.setPassword(password); 
    } 
    public void saveUsers() {

    }
}
