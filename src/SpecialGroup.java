public class SpecialGroup extends Client{
    private float Discount;
    private String RepresentativeName;
    private String RepresentativePhone;
    private String BusinessType;
    private int NumberOfMembers;

    public SpecialGroup(String name, String phoneNumber, String nationality, boolean isVip,
                        float discount, String representativeName, String representativePhone,
                        String businessType, int numberOfMembers) {
        super(name, phoneNumber, nationality, isVip);
    }

    @Override
    public String toString(){
        return (this.Name + "\n" + this.PhoneNumber + "\n" + this.Nationality + "\n" + this.IsVip + "\n" +
                this.Discount + "\n" + this.RepresentativeName + "\n" + this.RepresentativePhone + "\n" +
                this.BusinessType + "\n" + this.NumberOfMembers);
    }

    public void setRepresentativeName(String representativeName) {
        RepresentativeName = representativeName;
    }

    public void setRepresentativePhone(String representativePhone) {
        RepresentativePhone = representativePhone;
    }

    public void setDiscount(float discount) {
        Discount = discount;
    }

    public void setBusinessType(String businessType) {
        BusinessType = businessType;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        NumberOfMembers = numberOfMembers;
    }

    public String getRepresentativeName() {
        return RepresentativeName;
    }

    public String getRepresentativePhone() {
        return RepresentativePhone;
    }

    public float getDiscount() {
        return Discount;
    }

    public String getBusinessType() {
        return BusinessType;
    }

    public int getNumberOfMembers() {
        return NumberOfMembers;
    }
}
