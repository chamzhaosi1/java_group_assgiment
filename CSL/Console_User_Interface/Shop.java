package Console_User_Interface;
// Shop Super class
public class Shop{
    private String shopName;
    private String location;
    private String shopRegisterNumber;
    private String telephone;

    public Shop(String shopName, String location, String shopRegisterNumber, String telephone) {
        this.shopName = shopName;
        this.location = location;
        this.shopRegisterNumber = shopRegisterNumber;
        this.telephone = telephone;
    }

    public String getShopName() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShopRegisterNumber() {
        return this.shopRegisterNumber;
    }

    public void setShopRegisterNumber(String shopRegisterNumber) {
        this.shopRegisterNumber = shopRegisterNumber;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
            " shopName='" + getShopName() + "'" +
            ", location='" + getLocation() + "'" +
            ", shopRegisterNumber='" + getShopRegisterNumber() + "'" +
            ", telephone='" + getTelephone() + "'" +
            "}";
    }
}