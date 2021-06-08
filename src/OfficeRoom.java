public class OfficeRoom extends Room{
    private int ChairsNumber;
    private int BenchDesksNumbers;
    private int PersonalComputersNumber;

    public OfficeRoom(int roomNumber, int capacity, String location, Double pricePerDay, int rate,
                      int chairsNumber, int benchDesksNumbers, int personalComputersNumber){
        super(roomNumber, capacity, location, pricePerDay, rate);
                this.ChairsNumber = chairsNumber;
        this.BenchDesksNumbers = benchDesksNumbers;
        this.PersonalComputersNumber = personalComputersNumber;
    }

    public String toString(){
        return (this.RoomNumber + "\n" + this.Capacity + "\n" + this.getStatus() + "\n" + this.Location + "\n" +
                this.PricePerDay  + "\n" + this.ChairsNumber + "\n" + this.BenchDesksNumbers + "\n" +
                this.PersonalComputersNumber);
    }

    public int getChairsNumber() {
        return ChairsNumber;
    }

    public int getBenchDesksNumbers() {
        return BenchDesksNumbers;
    }

    public void setPersonalComputersNumber(int personalComputersNumber) {
        PersonalComputersNumber = personalComputersNumber;
    }
}
