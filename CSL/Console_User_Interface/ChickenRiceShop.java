package Console_User_Interface;

// Subclass of the Shop
public class ChickenRiceShop extends Shop {
    private String[] tableLabel;

    public ChickenRiceShop(String shopName, String location, String shopRegisterNumber, String telephone) {
        super(shopName, location, shopRegisterNumber, telephone);
    }

    public String[] getTableLabel() {
        return this.tableLabel;
    }

    public void setTableLabel(String[] tableLabel) {
        this.tableLabel = tableLabel;
    }
}
