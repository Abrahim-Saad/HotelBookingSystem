public class HallRoom extends Room{
    private int ChairsNumber;
    private int StagesNumber;
    private int ScreensNumber;
    private boolean IsPrepared;

    public HallRoom(int roomNumber, int capacity, String location, Double pricePerDay, int rate,
                            int chairsNumber, int stagesNumber, int screensNumber, boolean isPrepared){
        super(roomNumber, capacity, location, pricePerDay, rate);
        this.ChairsNumber = chairsNumber;
        this.StagesNumber = stagesNumber;
        this.ScreensNumber = screensNumber;
        this.IsPrepared = isPrepared;
    }

    public String toString(){
        return (this.RoomNumber + "\n" + this.Capacity + "\n" + this.getStatus() + "\n" + this.Location + "\n" +
                this.PricePerDay  + "\n" + this.ChairsNumber + "\n" + this.StagesNumber + "\n" +
                this.ScreensNumber + "\n" + this.IsPrepared);
    }

    public int getChairsNumber() {
        return ChairsNumber;
    }

    public int getStagesNumber() {
        return StagesNumber;
    }

    public int getScreensNumber() {
        return ScreensNumber;
    }

    public boolean isPrepared() {
        return IsPrepared;
    }
}
