package Console_User_Interface;
//Subclass of the Product
public class ChickenRiceProduct extends Product{
    private int initialQuantity;
    private int balanceQuantity;

    public ChickenRiceProduct() {
        super();
    }

    public ChickenRiceProduct(String productName, double productPrice, String productDescription, int initialQuantity, int balanceQuantity) {
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

    public void addProductQuantity(int quantity){
        this.balanceQuantity += quantity;
    }
    
    public boolean minusProductQuantity(int quantity){
        if (this.balanceQuantity >= quantity ){
            this.balanceQuantity -= quantity;
            return true;
        }else{
            return false;
        }
    }
}
