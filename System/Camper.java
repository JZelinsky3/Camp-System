import java.util.ArrayList;
import java.util.UUID;
/**
 * An objects storing a camper's information
 */
public class Camper
{
    private UUID id;
    private String birthdate;
    private ArrayList<Address> address;
    private ArrayList<Gender> gender;
    private Medical medicalInfo;

    public Camper(String birthdate, String firstName, String lastName, String username, String password, String email, MedicalInfo medicalInfo)
    {
        this.id = UUID.randomUUID();
        this.birthdate = birthdate;
        this.address = new ArrayList<Address>();
        this.gender = new ArrayList<Gender>();
        this.medicalInfo = medicalInfo;
    }

    public UUID getId()
    {
        return this.id;
    }

    public String getBirthdate()
    {
        return this.birthdate;
    }

    public ArrayList<Address> getAddress()
    {
        return this.address;
    }

    public ArrayList<Gender> getGender()
    {
        return this.gender;
    }

    public Medical getMedicalInfo()
    {
        return this.medicalInfo;
    }

    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    public void setAddress(ArrayList<Address> address)
    {
        this.address = address;
    }

    public void setGender(ArrayList<Gender> gender)
    {
        this.gender = gender;
    }

    public void setMedicalInfo(Medical medicalInfo)
    {
        this.medicalInfo = medicalInfo;
    }

    public void addAddress(String location, String streetAddress, String city, String state, String zipcode)
    {
        Address newAddress = new Address(location, streetAddress, city, state, zipcode);
        this.address.add(newAddress);
    }

    public void addGender(Gender gender)
    {
        this.gender.add(gender);
    }

    public boolean isValidAge(String birthdate)
    {
        return true;
    }
}
