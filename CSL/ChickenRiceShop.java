// Subclass of the Shop
public class ChickenRiceShop extends Shop {
    private String[] tableLabel;

    public ChickenRiceShop(String shopName, String location, String shopRegisterNumber) {
        super(shopName, location, shopRegisterNumber);
    }

    public String[] getTableLable() {
        return this.tableLabel;
    }

    public void setTableLabel(String[] tableLabel) {
        this.tableLabel = tableLabel;
    }
}
