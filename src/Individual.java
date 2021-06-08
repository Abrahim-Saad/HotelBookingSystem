import java.util.ArrayList;
import java.util.List;

public class Individual extends Client{
    private int PersonalID;
    private boolean IsMarried;
    private String Address;
    private String Job;
    private static ArrayList<String> GenderOptions = new ArrayList<>(List.of("Male", "Female"));
    private int Gender;
    private int Age;


    public Individual(String name, String phoneNumber, String nationality, boolean isVip,
                      int personalID, boolean isMarried, String address, String job, String gender, int age) {
        super(name, phoneNumber, nationality, isVip);
        this.PersonalID = personalID;
        this.IsMarried = isMarried;
        this.Address  = address;
        this.Job = job;
        this.Gender = GenderOptions.indexOf(gender);
        this.Age = age;
    }

    @Override
    public String toString(){
        return (this.Name + "\n" + this.PhoneNumber + "\n" + this.Nationality + "\n" + this.IsVip + "\n" +
                this.PersonalID + "\n" + this.IsMarried + "\n" + this.Address + "\n" + this.Job + "\n" +
                this.getGender() + "\n" + this.Age);
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setGender(String gender) {
        Gender = GenderOptions.indexOf(gender);
    }

    public void setJob(String job) {
        Job = job;
    }

    public void setMarried(boolean married) {
        IsMarried = married;
    }

    public void setPersonalID(int personalID) {
        PersonalID = personalID;
    }
    public String getAddress() {
        return Address;
    }

    public int getAge() {
        return Age;
    }

    public String getGender() {

        return GenderOptions.get(Gender);
    }

    public int getPersonalID() {
        return PersonalID;
    }

    public boolean isMarried() {
        return IsMarried;
    }

    public String getJob() {
        return Job;
    }

}
