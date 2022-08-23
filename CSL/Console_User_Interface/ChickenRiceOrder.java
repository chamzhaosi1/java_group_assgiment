package Console_User_Interface;
public class ChickenRiceOrder{
    private String tableLabel;
    private ChickenRiceProduct[] chickenRiceProduct;
    private Integer[] chickenRiceOrderQuantity;
    private ChickenRiceAddOn[] chickenRiceAddOn;
    private Integer[] chickenAddOnOrderQuantity;
    private String remark;
    private double totalPrice;

    public String getTableLabel() {
        return this.tableLabel;
    }

    public void setTableLabel(String tableLabel) {
        this.tableLabel = tableLabel;
    }

    public ChickenRiceProduct[] getChickenRiceProduct() {
        return this.chickenRiceProduct;
    }

    public void setChickenRiceProduct(ChickenRiceProduct[] chickenRiceProduct) {
        this.chickenRiceProduct = chickenRiceProduct;
    }

    public Integer[] getChickenRiceOrderQuantity() {
        return this.chickenRiceOrderQuantity;
    }

    public void setChickenRiceOrderQuantity(Integer[] chickenRiceOrderQuantity) {
        this.chickenRiceOrderQuantity = chickenRiceOrderQuantity;
    }

    public ChickenRiceAddOn[] getChickenRiceAddOn() {
        return this.chickenRiceAddOn;
    }

    public void setChickenRiceAddOn(ChickenRiceAddOn[] chickenRiceAddOn) {
        this.chickenRiceAddOn = chickenRiceAddOn;
    }

    public Integer[] getChickenAddOnOrderQuantity() {
        return this.chickenAddOnOrderQuantity;
    }

    public void setChickenAddOnOrderQuantity(Integer[] chickenAddOnOrderQuantity) {
        this.chickenAddOnOrderQuantity = chickenAddOnOrderQuantity;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "{" +
            " tableLabel='" + getTableLabel() + "'" +
            ", chickenRiceProduct='" + getChickenRiceProduct() + "'" +
            ", chickenRiceOrderQuantity='" + getChickenRiceOrderQuantity() + "'" +
            ", chickenRiceAddOn='" + getChickenRiceAddOn() + "'" +
            ", chickenAddOnOrderQuantity='" + getChickenAddOnOrderQuantity() + "'" +
            ", remark='" + getRemark() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            "}";
    }


}