// Subclass of the Product
public class ChickenRiceAddOn extends Product{
    private int initialQuantity;
    private int balanceQuantity;

    public ChickenRiceAddOn() {
    }

    public ChickenRiceAddOn(String productName, double productPrice, String productDescription, int initialQuantity, int balanceQuantity) {
        super(productName, productPrice, productDescription);
        this.initialQuantity = initialQuantity;
        this.balanceQuantity = balanceQuantity;
    }

    public int getInitialQuantity() {
        return this.initialQuantity;
    }

    public void setInitialQuantity(int initialQuantity) {
        this.initialQuantity = initialQuantity;
    }

    public int getBalanceQuantity() {
        return this.balanceQuantity;
    }

    public void setBalanceQuantity(int balanceQuantity) {
        this.balanceQuantity = balanceQuantity;
    }

}

