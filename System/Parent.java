import java.util.ArrayList;

public class Parent {
    private ArrayList<Camper> children = new ArrayList<Camper>();
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;


    Parent(ArrayList<Camper> children,String firstName, String lastName,String username, String password, String email){
        this.children = children;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public void register(Camper camper, Camp camp){

    }
    public void addCamper(Camper camper){

    }
}
