import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Room implements Serializable {
    protected int RoomID;
    protected int RoomNumber;
    protected int Capacity;
    protected String Location;
    private static ArrayList<String> StatusOptions = new ArrayList<>(List.of("Available", "Booked", "Used"));
    protected int Status;
    protected Double PricePerDay;
    private static ArrayList<Integer> RateOptions = new ArrayList<>(List.of(1, 2, 3, 4, 5));
    protected int Rate;


    public Room(){

    }

    public Room(int roomNumber, int capacity, String location, Double pricePerDay, int rate){
        this.RoomID = 0;
        this.RoomNumber = roomNumber;
        this.Capacity = capacity;
        this.Location = location;
        this.Status = StatusOptions.indexOf("Available");
        this.PricePerDay = pricePerDay;
        this.Rate = RateOptions.indexOf(rate);
    }


    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public void setRoomNumber(int roomNumber) {
        RoomNumber = roomNumber;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setStatus(String status) {
        Status = StatusOptions.indexOf(status);
    }

    public void setPricePerDay(Double pricePerDay) {
        PricePerDay = pricePerDay;
    }

    public void setRate(int rate) {
        Rate = RateOptions.indexOf(rate);

    }


    public int getRoomID(){
        return this.RoomID;
    }

    public int getRoomNumber(){
        return this.RoomNumber;
    }
    public int getCapacity(){
        return this.Capacity;
    }
    public String getLocation(){
        return this.Location;
    }
    public String getStatus(){
        return StatusOptions.get(this.Status);
    }

    public Double getPricePerDay(){
        return this.PricePerDay;
    }

    public int getRate(){
        return RateOptions.get(this.Rate);
    }


    @Override
    public String toString(){
        return (this.RoomNumber + "\n" + this.Capacity + "\n" + this.getStatus() + "\n" + this.Location + "\n" + this.PricePerDay);
    }

}
