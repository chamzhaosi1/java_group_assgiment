import java.util.Scanner;
import java.util.ArrayList;

public class ChickenRiceShopBuild {
    private static final String INPUT_ERROR_MESSAGE = "Your selection is invalid please try again!!";

    private ChickenRiceProduct chickenRiceProduct[];
    private ChickenRiceShop chickenRiceShop;
    private ChickenRiceAddOn chickenRiceAddOn[];
    private ChickenRiceOrder chickenRiceOrder;
    private ArrayList<ChickenRiceOrder> chickenRiceOrderList = new ArrayList<>();
    private ArrayList<ChickenRiceProduct> orderChickeRiceList = new ArrayList<>();
    private ArrayList<Integer> orderChickeRiceQuantityList = new ArrayList<>();
    private ArrayList<ChickenRiceAddOn> orderAddOnList = new ArrayList<>();
    private ArrayList<Integer> orderAddOnQuantityList = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public ChickenRiceShopBuild() {
        registerChickenRiceShop();
        addChickenRiceProduct();
        addChickenRiceAddOn();
        optionMenu();
    }

    // Register a chicken shop
    private void registerChickenRiceShop() {
        chickenRiceShop = new ChickenRiceShop("LCL Chicken Rice Shop",
                "Blok B&C, Lot, 5, Seksyen 10, Jalan Bukit, Taman Bukit Mewah, 43000 Kajang, Selangor", "LCL-12345");

        String tableLabel[] = { "A1", "A2", "A3", "A4", "A5" };
        chickenRiceShop.setTableLabel(tableLabel);
    }

    // add chicken rice product
    private void addChickenRiceProduct() {
        ChickenRiceProduct roastedChicken = new ChickenRiceProduct("Roasted Chicken", 10.50,
                "Crispy skin, juicy meat, flavorful at every turn, Roasted Chicken is one of the tastiest & most satisfying dishes you can ever have.",
                10, 10);

        ChickenRiceProduct steamChicken = new ChickenRiceProduct("Steamed Chicken", 9.50,
                "An ideal method for cooking boneless chicken breasts and small whole birds such as Cornish hens.", 10,
                10);

        ChickenRiceProduct friedChicken = new ChickenRiceProduct("Fried Chicken", 8.50,
                "Done properly, deep-frying creates a satisfying contrast between the crispy-crunchy coating and tender chicken.",
                10, 10);

        chickenRiceProduct = new ChickenRiceProduct[3];

        chickenRiceProduct[0] = roastedChicken;
        chickenRiceProduct[1] = steamChicken;
        chickenRiceProduct[2] = friedChicken;
    }

    // add chicke rice add on product
    private void addChickenRiceAddOn() {
        ChickenRiceAddOn soup = new ChickenRiceAddOn("Soup", 1.00, "A delicous soup", 20, 20);
        ChickenRiceAddOn drink = new ChickenRiceAddOn("Rice", 1.00, "A sepcial drink", 20, 20);
        ChickenRiceAddOn tauge = new ChickenRiceAddOn("tauge", 1.00, "More tauge, more amazing", 20, 20);

        chickenRiceAddOn = new ChickenRiceAddOn[3];

        chickenRiceAddOn[0] = soup;
        chickenRiceAddOn[1] = drink;
        chickenRiceAddOn[2] = tauge;
    }

    private void optionMenu() {

        boolean validata = false;
        int mainMemuOptionInput = 0;

        // Show the list at first time, if condition false then loop again
        do {
            // Show out our shop detail
            System.out.println(
                    "######################################################################################################");
            System.out.println("Shop Name: " + chickenRiceShop.getshopName() + " ( "
                    + chickenRiceShop.getshopRegisterNumber() + " )");
            System.out.println("Address: " + chickenRiceShop.getlocation());
            System.out.println(
                    "######################################################################################################");

            // List all available option
            System.out.println("You are allow to choose following option to operate our POS system: ");
            System.out.println("1: Product Menu");
            System.out.println("2: Make Payment");
            System.out.println("3: Show product balance");
            System.out.println("4: Summary Daily Sales");
            System.out.println("5: Exit");

            try {
                // Read the user input integer
                mainMemuOptionInput = input.nextInt();
                // System.out.println(mainMemuOptionInput);

                // Check whether the input valid or not
                validata = checkInputIntValidation(mainMemuOptionInput, 0, 6, INPUT_ERROR_MESSAGE);
                // System.out.println(validata);

                if (validata) {
                    // After invoke the below function, the main menu while show again, process will
                    // not be end, becuase validate false.
                    validata = false;
                    switch (mainMemuOptionInput) {
                        case 1:
                            productMenuOption();
                            break;
                        // case 2: makePaymentOption(); break;
                        // case 3: showProductBalanceOption(); break;
                        // case 4: summaryDailtSalesOption(); break;
                        case 5:
                            exitOption();
                            break;
                    }
                }
            } catch (Exception ex) {
                // If the user not key in the integer number, then handle the exception
                // System.out.println(ex);
                System.out.println(INPUT_ERROR_MESSAGE);
                // Clear the system input buffer before loop again
                input.nextLine();
            }

            // False inverse = true, the query again
        } while (!validata);
        input.close();
    }

    // When user select option 1
    public void productMenuOption() {
        chickenRiceOrder = new ChickenRiceOrder();

        String label = "Please choose which table label mark an order";
        String tabelLabelList[] = chickenRiceShop.getTableLable();
        Validate_Num validate_num;
        boolean validate, returnThisPage;
        int tabelLabelInput = -1;
        int tabelLabelLength = tabelLabelList.length;

        do {  
            // invoke the function to check number input validate or not, and what numbe user keyin
            validate_num = selectOption(label, tabelLabelList, 1);

            // Get validate status and number input
            validate = validate_num.getValidate();
            tabelLabelInput = validate_num.getNumber();

            // If validate == ture
            if (validate && tabelLabelInput < (tabelLabelLength + 1)) {
                // Because the length of the array only have 5, and index 0-4, so need to minus
                // one
                tabelLabelInput -= 1;

                // set table label number 
                chickenRiceOrder.setTableLabel(tabelLabelList[tabelLabelInput]);

                // After select the table label, call product list function
                returnThisPage = productListOption();

                // If the return == true, then loop again, while return == false, back to the
                // home menu
                if (returnThisPage) {
                    validate = false;
                }

                // Either select 6 or 7, process will return to home menu section
            } else if (tabelLabelInput == (tabelLabelLength + 1) || tabelLabelInput == (tabelLabelLength + 2)) {
                System.out.println("Return to the home menu!\n");
                validate = true;
            }

        } while (!validate);
    }

    public boolean productListOption() {
        // Variable for checking input value valid or not
        boolean validate = false, validate_quntity = false, validate_addInput = false;
        char charOptionInput; 
        int productListInput = -1;
        int prodcutItemQuantity = -1;
        int productItemLength = chickenRiceProduct.length;
        Validate_Num validate_num;
        String label = "Please choose the item:";
        int count = 0;

        do {
            // invoke the function to check number input validate or not, and what numbe user keyin
            validate_num = selectOption(label, chickenRiceProduct, 2);

            // Get validate status and number input
            validate = validate_num.getValidate();
            productListInput = validate_num.getNumber();

            // if validate == true
            if (validate && productListInput < (productItemLength + 1)) {
                // because the length of the array only have 3, and index 0-2, so need to minus one
                productListInput -= 1;
                // add the seleted chicken rice product into an array
                orderChickeRiceList.add(chickenRiceProduct[productListInput]);

                do{
                    System.out.println(
                        "\n######################################################################################################");
                    System.out.print("Please enter the order quantity: ");

                    try {
                        // read the input quantity
                        prodcutItemQuantity = input.nextInt();

                        // add the seleted chicken rice product quantity into an array
                        orderChickeRiceQuantityList.add(prodcutItemQuantity);

                        // true then process without loop again
                        validate_quntity = true;
                        do{
                            System.out.println("Do you want add another product? (Y/N)");
    
                            charOptionInput = input.next().charAt(0);
    
                            // Check whether the user's input validate
                            validate_addInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);
    
                            // If validate format, then check whether y or n
                            if (validate_addInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                                clearInputBuffer();

                                // false then process loop again
                                validate = false;

                                // to record down how many product added
                                count++;
                                break;
                            }else if (!validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                                clearInputBuffer();

                                //invoke the add on menu function
                                addOnListOption();
                            }
                        }while(!validate_addInput);
                    } catch (Exception e) {
                        // If the user not key in the integer number, then handle the exception
                        // System.out.println(ex);
                        System.out.println(INPUT_ERROR_MESSAGE);
                        // Clear the system input buffer before loop again
                        input.nextLine();

                        // false then process loop again
                        validate_quntity = false;
                    }
                }while(!validate_quntity);

            // because above have minus 1, [0-3], so add one to become 4
            } else if (productListInput == (productItemLength + 1)) {
                System.out.println("Return to previous menu!");

                // To avoid the user select return to previous section, after selectingt "Y" for add aother chicken rice product
                // if count = 0, then return to table list option
                if(count == 0){
                    break;

                // if count > 0, then redirect ro add on list menu
                }else {
                    addOnListOption();
                    break;
                }

                // return to the main menu
            } else if (productListInput == (productItemLength + 2)) {
                System.out.println("Return to home menu!");
                return false;
            }

        } while (!validate);
        return true;
    }


    private void addOnListOption(){
        char charOptionInput;
        boolean validate_addInput;

        System.out.println("Do you want add on? (Y/N)");
    
        charOptionInput = input.next().charAt(0);

        // Check whether the user's input validate
        validate_addInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

        do {
            // If validate format, then check whether y or n
            if (validate_addInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                clearInputBuffer();

                Validate_Num validate_num;
                String label = "Please select one of the item:";
                int addOnItemLength = chickenRiceAddOn.length;
                int addOnitemInput, addOnItemQuantity;
                boolean validate, validate_quntity;

                do{
                    // invoke the function to check number input validate or not, and what numbe user keyin
                    validate_num = selectOption(label, chickenRiceAddOn, 2);

                    // Get validate status and number input
                    validate = validate_num.getValidate();
                    addOnitemInput = validate_num.getNumber();

                    // if validate == true
                    if (validate && addOnitemInput < (addOnItemLength + 1)) {

                        // because the length of the array only have 3, and index 0-2, so need to minus one
                        addOnitemInput -= 1;
                        // add the seleted chicken rice product into an array
                        orderAddOnList.add(chickenRiceAddOn[addOnitemInput]);

                        do{
                            System.out.println(
                                "\n######################################################################################################");
                            System.out.print("Please enter the order quantity: ");
        
                            try {
                                // read the input quantity
                                addOnItemQuantity = input.nextInt();

                                // add the seleted add on product quantity into an array
                                orderChickeRiceQuantityList.add(addOnItemQuantity);

                                // true then process without loop again
                                validate_quntity = true;
                                do{
                                    System.out.println("Do you want add another add on? (Y/N)");
                                    // read the input char
                                    charOptionInput = input.next().charAt(0);
            
                                    // Check whether the user's input validate
                                    validate_addInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);
            
                                    // If validate format, then check whether y or n
                                    if (validate_addInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                                        clearInputBuffer();

                                        // false then process will loop again
                                        validate = false;
                                        break;
                                    }else if (!validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')){
                                        clearInputBuffer();

                                        //invoke the add remark function
                                        remarkOption();
                                    }
                                }while(!validate_addInput);
                            } catch (Exception e) {
                                // If the user not key in the integer number, then handle the exception
                                // System.out.println(ex);
                                System.out.println(INPUT_ERROR_MESSAGE);
                                // Clear the system input buffer before loop again
                                input.nextLine();
                                // false then process will loop again
                                validate_quntity = false;
                            }
                        }while(!validate_quntity);
                    }


                }while(!validate);

                // if user dont want add on, then will direct to remark function
            }else if (!validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();

                //invoke the add remark function
                remarkOption();
            }
        }while(!validate_addInput);
    }

    private void remarkOption(){

    }

    // When user select option 2

    // When user select option 3

    // When user select option 4

    // When user select option 5
    private void exitOption() {
        System.out.println("Are you sure want to exit the system, your unsaved data may not be recovered! (Y/N)");

        // Read the first letter of user key in only
        char charOptionInput = input.next().charAt(0);

        // Check whether the user's input validate
        boolean validate = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

        // If validate format, then check whether y or n
        if (validate && (charOptionInput == 'Y' || charOptionInput == 'y')) {
            System.exit(0);
        }

        clearInputBuffer();
    }

     /**
     * @param label   - Label hits for user 
     * @param itemList  - item that need to let user choose
     * @param flag - each invoke the item list may be different so use the flag to identify them
     * @return validate_num, whether the number validate and what is the number
     */

    private Validate_Num selectOption(String label, Object[] itemList,  int flag){
        // Variable for checking input value valid or not
        boolean validate = false;
        int itemListInput = -1;
        int itemListLength, i = 0;
        
        System.out.println(
                "\n######################################################################################################");
        System.out.println(label);

        // Get the list lenght
        itemListLength = itemList.length;

        if (flag == 1){
            for (i = 0; i < itemListLength; i++) {
                System.out.println((i + 1) + ": " + itemList[i]);
            }

        }else if(flag == 2){
   
            if (itemList instanceof ChickenRiceProduct[]){
                for (i = 0; i < itemListLength; i++) {
                    System.out.println((i + 1) + ": " + ((ChickenRiceProduct) itemList[i]).getProductName() + ", RM "
                            + ((ChickenRiceProduct) itemList[i]).getProductPrice() + "\n "
                            + ((ChickenRiceProduct) itemList[i]).getProductDescription() + "\n balance: "
                            + ((ChickenRiceProduct) itemList[i]).getBalanceQuantity());
                }
            }else if (itemList instanceof ChickenRiceAddOn[]){
                for (i = 0; i < itemListLength; i++) {
                    System.out.println((i + 1) + ": " + ((ChickenRiceAddOn) itemList[i]).getProductName() + ", RM "
                            + ((ChickenRiceAddOn) itemList[i]).getProductPrice() + " - "
                            + ((ChickenRiceAddOn) itemList[i]).getProductDescription() + "\n balance: "
                            + ((ChickenRiceAddOn) itemList[i]).getBalanceQuantity());
                }
            }


            
        }
        // User allow to select cancle option to turn back main menu
        System.out.println((i + 1) + ": Return previous section");
        System.out.println((i + 2) + ": Return to home memu");

        try {
            // Read user input integer
            itemListInput = input.nextInt();
  
            // tabelLabelList.length+2 = 8, less than 8, return true
            validate = checkInputIntValidation(itemListInput, 0, (itemListLength + 3), INPUT_ERROR_MESSAGE);

        } catch (Exception e) {
            // If the user not key in the integer number, then handle the exception
            // System.out.println(e);
            System.out.println(INPUT_ERROR_MESSAGE);
            // Clear the system input buffer before loop again
            clearInputBuffer();
        }

        return new Validate_Num(validate, itemListInput);
    }

    // Check whether the input valid or not
    /**
     * @param inputInt   - number of the user keyin
     * @param leftBound  - left interval / more than what number
     * @param rightBound - rigth interval / less than what number
     * @param message    - message while invalid input
     * @return true / false
     */
    private boolean checkInputIntValidation(int inputInt, int leftBound, int rightBound, String message) {
        if (inputInt > leftBound && inputInt < rightBound) {
            return true;
        }
        System.out.println(message);
        return false;
    }

    /**
     * @param inputChar - first letter of the user keyin
     * @param message   - message while invalid input
     * @return true / false
     */
    private boolean checkInputBoolValidation(char inputChar, String message) {
        if (inputChar == 'Y' || inputChar == 'y' || inputChar == 'N' || inputChar == 'n') {
            return true;
        }
        System.out.println(message);
        return false;
    }

    private void clearInputBuffer() {
        input.nextLine();
    }

}