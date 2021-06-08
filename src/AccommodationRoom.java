import java.util.ArrayList;
import java.util.List;


public class AccommodationRoom extends Room{
    private int BedsNumber;
    private static ArrayList<String> ClassificationOptions = new ArrayList<>(
            List.of("Standard", "Comfortable", "Lux", "DeLux", "Sweet"));
    private int Classification;

    public AccommodationRoom(){

    }

   public AccommodationRoom(int roomNumber, int capacity, String location, Double pricePerDay, int rate,
                                    int bedsNumber, String classification){
       super(roomNumber, capacity, location, pricePerDay, rate);
       this.BedsNumber = bedsNumber;
       this.Classification = ClassificationOptions.indexOf(classification);

   }

    public String toString(){
        return (this.RoomID + "\n" + this.RoomNumber + "\n" + this.Capacity + "\n" + this.getStatus() + "\n" + this.Location + "\n" +
                this.PricePerDay  + "\n" + this.BedsNumber + "\n" + this.getClassification());
    }

    public int getBedsNumber(){
       return this.BedsNumber;

   }



    public String getClassification() {
        return ClassificationOptions.get(this.Classification);
    }
}
