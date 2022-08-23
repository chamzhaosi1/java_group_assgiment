package Console_User_Interface;
public class Validate_Num {
    private boolean validate;
    private int number;

    public Validate_Num(boolean validate, int number) {
        this.validate = validate;
        this.number = number;
    }
    

    public boolean isValidate() {
        return this.validate;
    }

    public boolean getValidate() {
        return this.validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
