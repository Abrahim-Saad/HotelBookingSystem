public class MeetingRoom extends Room{
    private int ChairsNumber;
    private int TablesNumber;
    private int ScreensNumber;
    private boolean IsPrepared;

    public MeetingRoom(int roomNumber, int capacity, String location, Double pricePerDay, int rate,
                               int chairsNumber, int tablesNumber, int screensNumber, boolean isPrepared){
        super(roomNumber, capacity, location, pricePerDay, rate);
        this.ChairsNumber = chairsNumber;
        this.TablesNumber = tablesNumber;
        this.ScreensNumber = screensNumber;
        this.IsPrepared = isPrepared;
    }

    public String toString(){
        return (this.RoomNumber + "\n" + this.Capacity + "\n" + this.getStatus() + "\n" + this.Location + "\n" +
                this.PricePerDay  + "\n" + this.ChairsNumber + "\n" + this.TablesNumber + "\n" +
                this.ScreensNumber + "\n" + this.IsPrepared);
    }

    public int getChairsNumber() {
        return ChairsNumber;
    }

    public int getTablesNumber() {
        return TablesNumber;
    }

    public int getScreensNumber() {
        return ScreensNumber;
    }

    public boolean isPrepared() {
        return IsPrepared;
    }
}
