public class Agent extends Client{
    private String Address;
    private float Discount;
    private String RepresentativeName;
    private String RepresentativePhone;


    public Agent(String name, String phoneNumber, String nationality, boolean isVip,
                 String address, float discount, String representativeName, String representativePhone) {
        super(name, phoneNumber, nationality, isVip);
        this.Address = address;
        this.Discount = discount;
        this.RepresentativeName = representativeName;
        this.RepresentativePhone = representativePhone;
    }

    @Override
    public String toString(){
        return (this.Name + "\n" + this.PhoneNumber + "\n" + this.Nationality + "\n" + this.IsVip + "\n" +
                this.Address + "\n" + this.Discount + "\n" + this.RepresentativeName + "\n" + this.RepresentativePhone);
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }

    public void setRepresentativeName(String representativeName) {
        RepresentativeName = representativeName;
    }

    public void setRepresentativePhone(String representativePhone) {
        RepresentativePhone = representativePhone;
    }

    public String getAddress() {
        return Address;
    }

    public float getDiscount() {
        return Discount;
    }

    public String getRepresentativeName() {
        return RepresentativeName;
    }

    public String getRepresentativePhone() {
        return RepresentativePhone;
    }
}
