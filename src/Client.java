import java.io.Serializable;
public class Client implements Serializable {
    private int ID;
    protected String Name;
    protected String PhoneNumber;
    protected String Nationality;
    protected boolean IsVip;

    public Client(String name, String phoneNumber, String nationality, boolean isVip){
        this.ID = 0;
        this.Name = name;
        this.PhoneNumber = phoneNumber;
        this.Nationality = nationality;
        this.IsVip = isVip;
    }

    @Override
    public String toString(){
        return (this.Name + "\n" + this.PhoneNumber + "\n" + this.Nationality + "\n" + this.IsVip);
    }


    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getNationality() {
        return Nationality;
    }

    public boolean isVip() {
        return IsVip;
    }
}
