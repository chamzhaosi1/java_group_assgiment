// Shop Super class
public class Shop{
    private String shopName;
    private String location;
    private String shopRegisterNumber;

    public Shop(String shopName, String location, String shopRegisterNumber) {
        this.shopName = shopName;
        this.location = location;
        this.shopRegisterNumber = shopRegisterNumber;
    }


    public String getshopName() {
        return this.shopName;
    }

    public void setshopName(String shopName) {
        this.shopName = shopName;
    }

    public String getlocation() {
        return this.location;
    }

    public void setlocation(String location) {
        this.location = location;
    }

    public String getshopRegisterNumber() {
        return this.shopRegisterNumber;
    }

    public void setshopRegisterNumber(String shopRegisterNumber) {
        this.shopRegisterNumber = shopRegisterNumber;
    }

    @Override
    public String toString() {
        return "{" +
            " shopName='" + getshopName() + "'" +
            ", location='" + getlocation() + "'" +
            ", shopRegisterNumber='" + getshopRegisterNumber() + "'" +
            "}";
    }

}