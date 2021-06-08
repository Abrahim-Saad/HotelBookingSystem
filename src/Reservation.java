import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation implements Serializable {
    private int RoomID;
    private int ClientID;
    private Date ArrivalDate;
    private Date DepartureDate;
    private float Discount;
    private double TotalReceipt;



    public Reservation(int roomID, int clientID, String arrivalDate, String departureDate, float discount,
                       double totalReceipt) throws ParseException {
        this.RoomID = roomID;
        this.ClientID = clientID;
        this.ArrivalDate = new SimpleDateFormat("dd/MM/yyyy").parse(arrivalDate);
        this.DepartureDate = new SimpleDateFormat("dd/MM/yyyy").parse(departureDate);
        this.Discount = discount;
        this.TotalReceipt = totalReceipt;
    }

    @Override
    public String toString(){
        return (this.RoomID + "\n" + this.ClientID + "\n" + this.ArrivalDate + "\n" + this.DepartureDate + "\n" +
                this.Discount + "\n" + this.TotalReceipt);
    }

    public boolean isAvailable(){
        return !DepartureDate.after(new Date());
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }



    public void setRoomID(int roomID) {
        RoomID = roomID;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }

    public void setDepartureDate(Date departureDate) {
        DepartureDate = departureDate;
    }

    public void setTotalReceipt(double totalReceipt) {
        TotalReceipt = totalReceipt;
    }


    public Date getArrivalDate() {
        return ArrivalDate;
    }

    public Date getDepartureDate() {
        return DepartureDate;
    }

    public double getTotalReceipt() {
        return TotalReceipt;
    }

    public int getClientID() {
        return ClientID;
    }

    public int getRoomID() {
        return RoomID;
    }

    public float getDiscount() {
        return Discount;
    }


}
