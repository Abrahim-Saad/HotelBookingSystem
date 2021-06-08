import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Staff implements Serializable {
    private int ID;
    private String Name;
    private int Age;
    private String PhoneNumber;
    private String Address;
    private String Certification;
    private double Salary;
    private String Position;
    private static ArrayList<String> DepartmentOptions = new ArrayList<>(List.of("Kitchen", "Security", "Room Service",
            "Secretary"));
    private int Department;

    public Staff(String name, int age, String phoneNumber, String address,
                         String certification, double salary, String position, String department){
        this.ID = 0;
        this.Name = name;
        this.Age = age;
        this.PhoneNumber = phoneNumber;
        this.Address = address;
        this.Certification = certification;
        this.Salary = salary;
        this.Position = position;
        this.Department = DepartmentOptions.indexOf(department);
    }

    @Override
    public String toString(){
        return (this.Name + "\n" + this.Age + "\n" + this.PhoneNumber + "\n" + this.Address + "\n" +
                this.Certification + "\n" + this.Salary + "\n" + this.Address + "\n" + this.Position + "\n" +
                getDepartment());
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setCertification(String certification) {
        Certification = certification;
    }

    public void setDepartment(String department) {
        this.Department = DepartmentOptions.indexOf(department);
    }

    public void setName(String name) {
        Name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public void setSalary(double salary) {
        Salary = salary;
    }


    public int getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public int getAge() {
        return Age;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getAddress() {
        return Address;
    }

    public String getCertification() {
        return Certification;
    }

    public double getSalary() {
        return Salary;
    }

    public String getPosition() {
        return Position;
    }

    public String getDepartment() {
        return DepartmentOptions.get(this.Department);
    }
}
