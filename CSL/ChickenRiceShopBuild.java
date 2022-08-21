import java.util.Scanner;
import java.util.ArrayList;

public class ChickenRiceShopBuild {
    private static final String INPUT_ERROR_MESSAGE = "Your selection is invalid please try again!!\n";

    private ChickenRiceProduct chickenRiceProduct[];
    private ChickenRiceShop chickenRiceShop;
    private ChickenRiceAddOn chickenRiceAddOn[];
    private ChickenRiceOrder chickenRiceOrder;
    private ArrayList<ChickenRiceOrder> ChickenRiceOrderList = new ArrayList<>();
    private ArrayList<ChickenRiceProduct> orderChickenRiceList = new ArrayList<>();
    private ArrayList<Integer> orderChickenRiceQuantityList = new ArrayList<>();
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
                    "\n######################################################################################################");
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
                    clearInputBuffer();
                    // After invoke the below function, the main menu while show again, process will
                    // not be end, becuase validate false.
                    validata = false;
                    switch (mainMemuOptionInput) {
                        case 1:
                            productMenuOption();
                            break;
                        case 2: makePaymentOption(); break;
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
                ex.printStackTrace();
                System.out.println(INPUT_ERROR_MESSAGE);
                // Clear the system input buffer before loop again
                clearInputBuffer();
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
        boolean validate = false, validate_quntity = false, validate_addInput = false, returnThisPage;
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
                orderChickenRiceList.add(chickenRiceProduct[productListInput]);

                do{
                    System.out.println(
                        "\n######################################################################################################");
                    System.out.print("Please enter the order quantity: ");

                    try {
                        // read the input quantity
                        prodcutItemQuantity = input.nextInt();

                        // add the seleted chicken rice product quantity into an array
                        orderChickenRiceQuantityList.add(prodcutItemQuantity);                                                                                                  

                        // true then process without loop again
                        validate_quntity = true;
                        do{
                            System.out.println("\nDo you want add another product? (Y/N)");
    
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
                            }else if (validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                                clearInputBuffer();

                                //invoke the add on menu function
                                returnThisPage = addOnListOption();
                                // System.out.println(returnThisPage);
                                if (returnThisPage) {
                                    validate = false;
                                }else {
                                    // return false, then back to the home menu
                                    return false;
                                }
                            }
                        }while(!validate_addInput);
                    } catch (Exception e) {
                        // If the user not key in the integer number, then handle the exception
                        // System.out.println(ex);
                        System.out.println(INPUT_ERROR_MESSAGE);
                        // Clear the system input buffer before loop again
                        clearInputBuffer();

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


    private boolean addOnListOption(){
        char charOptionInput;
        boolean validate_addInput;
        int count = 0;

        do{
            System.out.println(
                                "\n######################################################################################################");
            System.out.println("Do you want add on? (Y/N)");
    
            charOptionInput = input.next().charAt(0);

            // Check whether the user's input validate
            validate_addInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

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
                                orderAddOnQuantityList.add(addOnItemQuantity);

                                // true then process without loop again
                                validate_quntity = true;
                                do{
                                    System.out.println("\nDo you want add another add on? (Y/N)");
                                    // read the input char
                                    charOptionInput = input.next().charAt(0);
            
                                    // Check whether the user's input validate
                                    validate_addInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);
                                    // If validate format, then check whether y or n
                                    if (validate_addInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                                        clearInputBuffer();

                                        // false then process will loop again
                                        validate = false;
                                        // to record down how many product added
                                        count++;
                                        break;
                                    }else if (validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')){
                                        clearInputBuffer();

                                        //invoke the add remark function
                                        remarkOption();
                                        return false;
                                    }

                                }while(!validate_addInput);
                            } catch (Exception e) {
                                // If the user not key in the integer number, then handle the exception
                                // System.out.println(ex);
                                System.out.println(INPUT_ERROR_MESSAGE);
                                // Clear the system input buffer before loop again
                                clearInputBuffer();
                                // false then process will loop again
                                validate_quntity = false;
                            }
                        }while(!validate_quntity);

                    }else if (addOnitemInput == (addOnItemLength + 1)) {
                        System.out.println("Return to previous menu!");
        
                        // To avoid the user select return to previous section, after selectingt "Y" for add aother chicken rice product
                        // if count = 0, then return to table list option
                        if(count == 0){
                            break;
        
                        // if count > 0, then redirect ro add on list menu
                        }else {
                            remarkOption();
                            return false;
                            // break;
                        }
        
                        // return to the main menu
                    } else if (addOnitemInput == (addOnItemLength + 2)) {
                        System.out.println("Return to home menu!");
                        return false;
                    }
                }while(!validate);

                // if user dont want add on, then will direct to remark function
            }else if (validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();

                //invoke the add remark function
                remarkOption();
                return false;
            }
            
        }while(!validate_addInput);
        return true;
    }

    private void remarkOption(){
            char charOptionInput;
            boolean validate_remarkInput;
            String remark ="No remark";

        do {
            System.out.println(
                                "\n######################################################################################################");
            System.out.println("Do you want add remark? (Y/N)");
        
            charOptionInput = input.next().charAt(0);

            // Check whether the user's input validate
            validate_remarkInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

            // If validate format, then check whether y or n
            if (validate_remarkInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                clearInputBuffer();

                System.out.println(
                    "\n######################################################################################################");
                System.out.print("Please enter the remark: ");

                remark = input.nextLine();
                
                chickenRiceOrder.setRemark(remark);

                confirmMakeOrder();

                // System.out.println(remark);

                // if user dont want add on, then will direct to remark function
            }else if (validate_remarkInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();
                chickenRiceOrder.setRemark(remark);
                confirmMakeOrder();
            }
        }while(!validate_remarkInput);
    }

    public void confirmMakeOrder(){
        char charOptionInput;
        boolean validate_confirmInput;

        do {
            // Invoke the function to show the order list
            receipt("Order List");
            System.out.println("Are you sure mark the order? (Y/N)");

            charOptionInput = input.next().charAt(0);

            // Check whether the user's input validate
            validate_confirmInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

            // If validate format, then check whether y or n
            if (validate_confirmInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                clearInputBuffer();
             
                System.out.println("Order is saving..");

                orderSavingArrayOnly();

                System.out.println("Order has been saved..");

                // if user dont want add on, then will direct to remark function
            }else if (validate_confirmInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();

                char charClearAllInput;
                boolean validate_clearAllInput;

                do{
                    System.out.println("All of the previous operation will be clear. Are you sure to do that? (Y/N)");

                    charClearAllInput = input.next().charAt(0);

                    // Check whether the user's input validate
                    validate_clearAllInput = checkInputBoolValidation(charClearAllInput, INPUT_ERROR_MESSAGE);

                    if (validate_clearAllInput && (charClearAllInput == 'Y' || charClearAllInput == 'y')) {
                        System.out.println("All of the previous operation has been clear.");
                        clearInputBuffer();
                        break;

                    } else if(validate_clearAllInput && (charClearAllInput == 'N' || charClearAllInput == 'n')){
                        validate_confirmInput = false;
                        clearInputBuffer();
                        break; 
                    }
                }while(!validate_clearAllInput);
            }
        }while(!validate_confirmInput);
    }

    //fucntion to arrayList to array, because cannot whole arrayList cast to an array, need one element by element
    private void orderSavingArrayOnly(){
        // Cast chicken rice product
        int chickenRiceProductSize = orderChickenRiceList.size();
        ChickenRiceProduct[] tempChickenRiceProductList = new ChickenRiceProduct[chickenRiceProductSize];

        for (int i = 0; i<chickenRiceProductSize; i++){
            tempChickenRiceProductList[i] = orderChickenRiceList.get(i);
        }
        chickenRiceOrder.setChickenRiceProduct(tempChickenRiceProductList);

        // Cast chicken rice product quantity
        int chickenRiceProductQuantitySize = orderChickenRiceQuantityList.size();
        int[] tempChickenRiceProductQuantityList = new int [chickenRiceProductQuantitySize];

        for (int i = 0; i<chickenRiceProductSize; i++){
            tempChickenRiceProductQuantityList[i] = orderChickenRiceQuantityList.get(i);
        }
        chickenRiceOrder.setChickenRiceOrderQuantity(tempChickenRiceProductQuantityList);

        // Cast chicken rice product
        int chickenRiceAddOnSize = orderAddOnList.size();
        if(chickenRiceAddOnSize > 0){
            ChickenRiceAddOn[] tempChickenRiceAddOnList = new ChickenRiceAddOn[chickenRiceAddOnSize];

            for (int i = 0; i<chickenRiceAddOnSize; i++){
                tempChickenRiceAddOnList[i] = orderAddOnList.get(i);
            }
            chickenRiceOrder.setChickenRiceAddOn(tempChickenRiceAddOnList);


            int chickenRiceAddOnQuantitySize = orderAddOnQuantityList.size();
            int[] tempChickenRiceAddOnQuantiyyList = new int[chickenRiceAddOnQuantitySize];

            for (int i = 0; i<chickenRiceAddOnQuantitySize; i++){
                tempChickenRiceAddOnQuantiyyList[i] = orderAddOnQuantityList.get(i);
            }
            chickenRiceOrder.setChickenAddOnOrderQuantity(tempChickenRiceAddOnQuantiyyList);

        }
    }

    private void receipt(String title){
        double mainTotal = 0;
        double addOnTotal = 0;

        System.out.println(
                "\n######################################################################################################");
        System.out.println("Shop Name: " + chickenRiceShop.getshopName() + " ( "
                + chickenRiceShop.getshopRegisterNumber() + " )");
        System.out.println("Address: " + chickenRiceShop.getlocation());
        System.out.println(
                    "######################################################################################################");
    
        System.out.println("####################################### "+ title +" ################################### ");

        System.out.println("Tabel: " + chickenRiceOrder.getTableLabel());

        // List out all ordered chicker rice product
        System.out.println("\nMain Product:");

        for (int i = 0; i<orderChickenRiceList.size(); i++){
            System.out.println((i+1) + " : " + orderChickenRiceList.get(i).getProductName() + "\t x " + orderChickenRiceQuantityList.get(0) +"\tRM " + String.format("%.2f",orderChickenRiceList.get(i).getProductPrice() * orderChickenRiceQuantityList.get(0)));

            mainTotal += orderChickenRiceList.get(i).getProductPrice() * orderChickenRiceQuantityList.get(0);
        }

        // List out all ordered add on product
        if(orderAddOnList.size() > 0){
            System.out.println("\nAdd On Product:");
            for (int j = 0; j<orderAddOnList.size(); j++){
                System.out.println((j+1) + " : " + orderAddOnList.get(j).getProductName() + "\t x " + orderAddOnQuantityList.get(0) +"\tRM " + String.format( "%.2f",orderAddOnList.get(j).getProductPrice() * orderAddOnQuantityList.get(0)));
    
                addOnTotal += orderAddOnList.get(j).getProductPrice() * orderAddOnQuantityList.get(0);
            }
        }else {
            System.out.println("\nEmpty Add On Product");
        }

        System.out.print("\nRemark:");
        System.out.println(chickenRiceOrder.getRemark());

        System.out.println("\nTotal Price: RM " + String.format("%.2f", (mainTotal+addOnTotal)));

        System.out.println(
                "\n######################################################################################################");
    }

    // When user select option 2
    private void makePaymentOption(){
        if (chickenRiceOrder != null){
            // System.out.println(chickenRiceOrder.toString());
            receipt("Receipt");
        }else {
            System.out.println("No any order yet!");
        }   
    }

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
        System.out.println("Be Careful: Select option " +(i+2) +" will be caused previous saved operation be clear.");

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
        String text = input.nextLine();
    }

}