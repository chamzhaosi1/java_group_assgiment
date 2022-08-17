public class ChickenRiceOrder{
    private String tableLabel;
    private ChickenRiceProduct[] chickenRiceProduct;
    private int[] chickenRiceOrderQuantity;
    private ChickenRiceAddOn[] chickenRiceAddOn;
    private int[] chickenAddOnOrderQuantity;
    private String remark;

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

    public int[] getChickenRiceOrderQuantity() {
        return this.chickenRiceOrderQuantity;
    }

    public void setChickenRiceOrderQuantity(int[] chickenRiceOrderQuantity) {
        this.chickenRiceOrderQuantity = chickenRiceOrderQuantity;
    }

    public ChickenRiceAddOn[] getChickenRiceAddOn() {
        return this.chickenRiceAddOn;
    }

    public void setChickenRiceAddOn(ChickenRiceAddOn[] chickenRiceAddOn) {
        this.chickenRiceAddOn = chickenRiceAddOn;
    }

    public int[] getChickenAddOnOrderQuantity() {
        return this.chickenAddOnOrderQuantity;
    }

    public void setChickenAddOnOrderQuantity(int[] chickenAddOnOrderQuantity) {
        this.chickenAddOnOrderQuantity = chickenAddOnOrderQuantity;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}