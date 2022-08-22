import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDateTime; 

public class ChickenRiceShopBuild {
    private static final String INPUT_ERROR_MESSAGE = "Your selection is invalid please try again!!\n";

    private ChickenRiceProduct chickenRiceProduct[];
    private ChickenRiceShop chickenRiceShop;
    private ChickenRiceAddOn chickenRiceAddOn[];
    private ChickenRiceOrder chickenRiceOrder;
    private ArrayList<ChickenRiceOrder> chickenRiceSoldList = new ArrayList<>();
    private ArrayList<ChickenRiceOrder> chickenRiceOrderList = new ArrayList<>();
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
                "Blok B&C, Lot, 5, Seksyen 10, Jalan Bukit, Taman Bukit Mewah, 43000 Kajang, Selangor", "LCL-12345", "012-1234567");

        String tableLabel[] = { "A1", "A2", "A3", "A4", "A5" };
        chickenRiceShop.setTableLabel(tableLabel);

        // String shopName;
        // String registerNumber;
        // String address;
        // String telephone;

        // System.out.println(
        //             "\n######################################################################################################");
        // System.out.println("Please enter your shop detail: ");
        // System.out.print("Shop Name: ");
        // shopName = input.nextLine();

        // System.out.print("Register Number: ");
        // registerNumber = input.nextLine();

        // System.out.print("Address: ");
        // address = input.nextLine();

        // System.out.print("Telephone: ");
        // telephone = input.nextLine();

        // System.out.print("Shop detail has been configured...");

        // chickenRiceShop = new ChickenRiceShop(shopName, registerNumber, address, telephone);

        // System.out.println(
        //             "\n\n######################################################################################################");
        // System.out.println("Please enter your shop table label(enter 'N' for finish): ");
        // int count = 1;
        // ArrayList<String> initialTabelLabelList = new ArrayList<>(); 

        // do{
        //     System.out.print(count + ": ");
        //     String tableLabel = input.nextLine();

        //     if (tableLabel.equals("N") || tableLabel.equals("n")){
        //         break;
        //     }else{
        //         initialTabelLabelList.add(tableLabel);
        //     }
        //     count++;

        // }while(true);

        // String[] tblLabelList = new String[initialTabelLabelList.size()];

        // int i=0;
        // for (String tabelList : initialTabelLabelList){
        //     tblLabelList[i] = tabelList;
        //     i++;
        // }

        // chickenRiceShop.setTableLabel(tblLabelList);
        // System.out.print("Shop's table label has been saved...");
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

        // System.out.println(
        //         "\n######################################################################################################");
        // System.out.println("Please enter your shop's main product detail: ");
        // int count = 1;
        // String productName;
        // String productDescription;
        // double productUnitPrice;
        // int initialQuantity;
        // int balanceQuantity;
        // char confirmInput;
        // ArrayList<ChickenRiceProduct> mainProductList = new ArrayList<>();
        // boolean validate_confirmInput, validate= false;

        // do{
        //     try {
        //         System.out.println(count + " Main Product");
        //         System.out.print("Name: ");
        //         productName = input.nextLine();

        //         System.out.print("Description: ");
        //         productDescription = input.nextLine();

        //         System.out.print("Unit Price: ");
        //         productUnitPrice = input.nextDouble();

        //         System.out.print("Initial Quantity: ");
        //         initialQuantity = input.nextInt();

        //         System.out.print("Balance Quantity: ");
        //         balanceQuantity = input.nextInt();

        //         mainProductList.add(new ChickenRiceProduct(productName, productUnitPrice, productDescription, initialQuantity, balanceQuantity));

        //         System.out.println("\nDo you want add another main product? (Y/N)");
        //         confirmInput = input.next().charAt(0);

        //         validate_confirmInput = checkInputBoolValidation(confirmInput, INPUT_ERROR_MESSAGE);

        //         if (validate_confirmInput && (confirmInput == 'Y' || confirmInput == 'y')){
        //             clearInputBuffer();
        //             validate = false;
        //             count++;
        //         }else if (validate_confirmInput && (confirmInput == 'N' || confirmInput == 'n')){
        //             clearInputBuffer();
        //             validate = true;
        //         }

        //     } catch (Exception e) {
        //         // e.printStackTrace();
        //         clearInputBuffer();
        //         System.out.println(INPUT_ERROR_MESSAGE);
        //         validate = false;
        //     }
        // }while(!validate);

        // chickenRiceProduct = new ChickenRiceProduct[mainProductList.size()];

        // int i=0;
        // for (ChickenRiceProduct crp : mainProductList){
        //     chickenRiceProduct[i] = crp;
        //     i++;
        // }
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

        // System.out.println(
        //         "\n######################################################################################################");
        // System.out.println("Please enter your shop's add on product detail: ");
        // int count = 1;
        // String productName;
        // String productDescription;
        // double productUnitPrice;
        // int initialQuantity;
        // int balanceQuantity;
        // char confirmInput;
        // ArrayList<ChickenRiceAddOn> addOnProductList = new ArrayList<>();
        // boolean validate_confirmInput, validate= false;

        // do{
        //     try {
        //         System.out.println(count + " Add On Product");
        //         System.out.print("Name: ");
        //         productName = input.nextLine();

        //         System.out.print("Description: ");
        //         productDescription = input.nextLine();

        //         System.out.print("Unit Price: ");
        //         productUnitPrice = input.nextDouble();

        //         System.out.print("Initial Quantity: ");
        //         initialQuantity = input.nextInt();

        //         System.out.print("Balance Quantity: ");
        //         balanceQuantity = input.nextInt();

        //         addOnProductList.add(new ChickenRiceAddOn(productName, productUnitPrice, productDescription, initialQuantity, balanceQuantity));

        //         System.out.println("\nDo you want add another add on product? (Y/N)");
        //         confirmInput = input.next().charAt(0);

        //         validate_confirmInput = checkInputBoolValidation(confirmInput, INPUT_ERROR_MESSAGE);

        //         if (validate_confirmInput && (confirmInput == 'Y' || confirmInput == 'y')){
        //             clearInputBuffer();
        //             validate = false;
        //             count++;
        //         }else if (validate_confirmInput && (confirmInput == 'N' || confirmInput == 'n')){
        //             clearInputBuffer();
        //             validate = true;
        //         }

        //     } catch (Exception e) {
        //         // e.printStackTrace();
        //         clearInputBuffer();
        //         System.out.println(INPUT_ERROR_MESSAGE);
        //         validate = false;
        //     }
            
        // }while(!validate);

        // chickenRiceAddOn = new ChickenRiceAddOn[addOnProductList.size()];

        // int i=0;
        // for (ChickenRiceAddOn crao : addOnProductList){
        //     chickenRiceAddOn[i] = crao;
        //     i++;
        // }
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
            System.out.println("Telephone: " + chickenRiceShop.getTelephone());
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
                        case 2:
                            makePaymentOption();
                            break;
                        // case 3: showProductBalanceOption(); break;
                        case 4: summaryDailySalesOption(); break;
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
            // invoke the function to check number input validate or not, and what numbe
            // user keyin
            validate_num = selectOption(label, tabelLabelList, 1);

            // Get validate status and number input
            validate = validate_num.getValidate();
            tabelLabelInput = validate_num.getNumber();

            // If validate == ture
            if (validate && tabelLabelInput < (tabelLabelLength + 1)) {
                // Because the length of the array only have 5, and index 0-4, so need to minus
                // one
                tabelLabelInput -= 1;

                int i;
                if (chickenRiceOrderList.size() != 0) {
                    for (i = 0; i < chickenRiceOrderList.size(); i++) {
                        if (chickenRiceOrderList.get(i).getTableLabel().equals(tabelLabelList[tabelLabelInput])) {
                            System.out.println(
                                    "This table customer haven't leave or make a payment yet. Please select another one table label.");
                            validate = false;
                            break;
                        }
                    }

                    if (i == chickenRiceOrderList.size()) {
                        chickenRiceOrder.setTableLabel(tabelLabelList[tabelLabelInput]);

                        // After select the table label, call product list function
                        returnThisPage = productListOption();

                        // If the return == true, then loop again, while return == false, back to the
                        // home menu
                        if (returnThisPage) {
                            validate = false;
                        }
                    }

                } else {
                    chickenRiceOrder.setTableLabel(tabelLabelList[tabelLabelInput]);

                    // After select the table label, call product list function
                    returnThisPage = productListOption();

                    // If the return == true, then loop again, while return == false, back to the
                    // home menu
                    if (returnThisPage) {
                        validate = false;
                    }
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

        // assign a tempory product balace varible, for later check store balance enough
        // or not
        int[] tempProductQuantity = new int[chickenRiceProduct.length];

        for (int i = 0; i < chickenRiceProduct.length; i++) {
            tempProductQuantity[i] = chickenRiceProduct[i].getBalanceQuantity();
        }

        do {
            // invoke the function to check number input validate or not, and what numbe
            // user keyin
            validate_num = selectOption(label, chickenRiceProduct, 2);

            // Get validate status and number input
            validate = validate_num.getValidate();
            productListInput = validate_num.getNumber();

            // if validate == true
            if (validate && productListInput < (productItemLength + 1)) {

                // because the length of the array only have 3, and index 0-2, so need to minus
                // one
                productListInput -= 1;
                // add the seleted chicken rice product into an array
                orderChickenRiceList.add(chickenRiceProduct[productListInput]);

                do {
                    System.out.println(
                            "\n######################################################################################################");
                    System.out.print("Please enter the order quantity (Current Store Balance "
                            + tempProductQuantity[productListInput] + "): ");

                    try {
                        // read the input quantity
                        prodcutItemQuantity = input.nextInt();
                        // check whether the balance enough to sell
                        if (prodcutItemQuantity <= tempProductQuantity[productListInput]) {
                            clearInputBuffer();
                            // add the seleted chicken rice product quantity into an array
                            // but only quantity bigger than 0
                            if (prodcutItemQuantity > 0) {
                                orderChickenRiceQuantityList.add(prodcutItemQuantity);
                            } else {
                                // else the select item also be remove from the list
                                orderChickenRiceList.remove(orderChickenRiceList.size() - 1);
                            }

                            // true then process without loop again
                            validate_quntity = true;

                            tempProductQuantity[productListInput] -= prodcutItemQuantity;

                            do {
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
                                } else if (validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                                    clearInputBuffer();

                                    // invoke the add on menu function
                                    returnThisPage = addOnListOption();
                                    // System.out.println(returnThisPage);
                                    if (returnThisPage) {
                                        validate = false;
                                    } else {
                                        // return false, then back to the home menu
                                        return false;
                                    }
                                }
                            } while (!validate_addInput);
                        } else {
                            System.out.println(
                                    "Not enough store, current balance is : " + tempProductQuantity[productListInput]);
                            // false then process loop again
                            validate = false;
                            break;
                        }

                    } catch (Exception e) {
                        // If the user not key in the integer number, then handle the exception
                        // System.out.println(ex);
                        System.out.println(INPUT_ERROR_MESSAGE);
                        // Clear the system input buffer before loop again
                        clearInputBuffer();

                        // false then process loop again
                        validate_quntity = false;
                    }
                } while (!validate_quntity);

                // because above have minus 1, [0-3], so add one to become 4
            } else if (productListInput == (productItemLength + 1)) {
                System.out.println("Return to previous menu!");

                // To avoid the user select return to previous section, after selectingt "Y" for
                // add aother chicken rice product
                // if count = 0, then return to table list option
                if (count == 0) {
                    break;

                    // if count > 0, then redirect ro add on list menu
                } else {
                    // invoke the add on menu function
                    returnThisPage = addOnListOption();
                    // System.out.println(returnThisPage);
                    if (returnThisPage) {
                        validate = false;
                    } else {
                        // return false, then back to the home menu
                        return false;
                    }
                }

                // return to the main menu
            } else if (productListInput == (productItemLength + 2)) {
                System.out.println("Return to home menu!");
                return false;
            }

        } while (!validate);

        return true;
    }

    private boolean addOnListOption() {
        char charOptionInput;
        boolean validate_addInput;
        int count = 0;

        do {
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

                // assign a tempory product balace varible, for later check store balance enough
                // or not
                int[] tempAddOnQuantity = new int[chickenRiceAddOn.length];

                for (int i = 0; i < chickenRiceProduct.length; i++) {
                    tempAddOnQuantity[i] = chickenRiceAddOn[i].getBalanceQuantity();
                }

                do {
                    // invoke the function to check number input validate or not, and what numbe
                    // user keyin
                    validate_num = selectOption(label, chickenRiceAddOn, 2);

                    // Get validate status and number input
                    validate = validate_num.getValidate();
                    addOnitemInput = validate_num.getNumber();

                    // if validate == true
                    if (validate && addOnitemInput < (addOnItemLength + 1)) {

                        // because the length of the array only have 3, and index 0-2, so need to minus
                        // one
                        addOnitemInput -= 1;
                        // add the seleted chicken rice product into an array
                        orderAddOnList.add(chickenRiceAddOn[addOnitemInput]);

                        do {
                            System.out.println(
                                    "\n######################################################################################################");
                            System.out.print("Please enter the order quantity (Current Store Balance "
                                    + tempAddOnQuantity[addOnitemInput] + "): ");

                            try {
                                // read the input quantity
                                addOnItemQuantity = input.nextInt();

                                // check whether the balance enough to sell
                                if (addOnItemQuantity <= tempAddOnQuantity[addOnitemInput]) {
                                    clearInputBuffer();
                                    // add the seleted chicken rice product quantity into an array
                                    // but only quantity bigger than 0
                                    if (addOnItemQuantity > 0) {
                                        orderAddOnQuantityList.add(addOnItemQuantity);
                                    } else {
                                        // else the select item also be remove from the list
                                        orderAddOnList.remove(orderAddOnList.size() - 1);
                                    }

                                    // true then process without loop again
                                    validate_quntity = true;

                                    tempAddOnQuantity[addOnitemInput] -= addOnItemQuantity;
                                    do {
                                        System.out.println("\nDo you want add another add on? (Y/N)");
                                        // read the input char
                                        charOptionInput = input.next().charAt(0);

                                        // Check whether the user's input validate
                                        validate_addInput = checkInputBoolValidation(charOptionInput,
                                                INPUT_ERROR_MESSAGE);
                                        // If validate format, then check whether y or n
                                        if (validate_addInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                                            clearInputBuffer();

                                            // false then process will loop again
                                            validate = false;
                                            // to record down how many product added
                                            count++;
                                            break;
                                        } else if (validate_addInput
                                                && (charOptionInput == 'N' || charOptionInput == 'n')) {
                                            clearInputBuffer();

                                            // invoke the add remark function
                                            remarkOption();
                                            // return false, then back to the home menu
                                            return false;
                                        }

                                    } while (!validate_addInput);
                                } else {
                                    System.out.println("Not enough store, current balance is : "
                                            + tempAddOnQuantity[addOnitemInput]);
                                    // false then process loop again
                                    validate = false;
                                    break;
                                }
                            } catch (Exception e) {
                                // If the user not key in the integer number, then handle the exception
                                // System.out.println(ex);
                                System.out.println(INPUT_ERROR_MESSAGE);
                                // Clear the system input buffer before loop again
                                clearInputBuffer();
                                // false then process will loop again
                                validate_quntity = false;
                            }
                        } while (!validate_quntity);

                    } else if (addOnitemInput == (addOnItemLength + 1)) {
                        System.out.println("Return to previous menu!");

                        // To avoid the user select return to previous section, after selectingt "Y" for
                        // add aother chicken rice product
                        // if count = 0, then return to table list option
                        if (count == 0) {
                            break;

                            // if count > 0, then redirect ro add on list menu
                        } else {
                            remarkOption();
                            // return false, then back to the home menu
                            return false;
                        }

                        // return to the main menu
                    } else if (addOnitemInput == (addOnItemLength + 2)) {
                        System.out.println("Return to home menu!");
                        return false;
                    }
                } while (!validate);

                // if user dont want add on, then will direct to remark function
            } else if (validate_addInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();

                // invoke the add remark function
                remarkOption();
                // return false, then back to the home menu
                return false;
            }

        } while (!validate_addInput);
        // return false, then back to the previous menu
        return true;
    }

    private void remarkOption() {
        char charOptionInput;
        boolean validate_remarkInput;
        String remark = "No remark";

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

                // if user dont want add on, then will direct to remark function
            } else if (validate_remarkInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();

                // if user select n, then default remark will be added
                chickenRiceOrder.setRemark(remark);

                confirmMakeOrder();
            }
        } while (!validate_remarkInput);
    }

    public void confirmMakeOrder() {
        char charOptionInput;
        boolean validate_confirmInput;

        do {
            // Invoke the function to show the order list
            receipt("Order List", chickenRiceOrder, orderChickenRiceList, orderChickenRiceQuantityList, orderAddOnList,
                    orderAddOnQuantityList);
            System.out.println("Are you sure mark the order? (Y/N)");

            charOptionInput = input.next().charAt(0);

            // Check whether the user's input validate
            validate_confirmInput = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

            // If validate format, then check whether y or n
            if (validate_confirmInput && (charOptionInput == 'Y' || charOptionInput == 'y')) {
                clearInputBuffer();

                System.out.println("Order is saving..");

                // Add the item into the order class
                orderSavingArrayOnly();

                System.out.println("Order has been saved..");

                // if user dont want add on, then will direct to remark function
            } else if (validate_confirmInput && (charOptionInput == 'N' || charOptionInput == 'n')) {
                clearInputBuffer();

                char charClearAllInput;
                boolean validate_clearAllInput;

                do {
                    System.out.println("All of the previous operation will be clear. Are you sure to do that? (Y/N)");

                    charClearAllInput = input.next().charAt(0);

                    // Check whether the user's input validate
                    validate_clearAllInput = checkInputBoolValidation(charClearAllInput, INPUT_ERROR_MESSAGE);

                    if (validate_clearAllInput && (charClearAllInput == 'Y' || charClearAllInput == 'y')) {
                        System.out.println("All of the previous operation has been clear.");
                        clearInputBuffer();
                        break;

                    } else if (validate_clearAllInput && (charClearAllInput == 'N' || charClearAllInput == 'n')) {
                        validate_confirmInput = false;
                        clearInputBuffer();
                        break;
                    }
                } while (!validate_clearAllInput);
            }
        } while (!validate_confirmInput);
    }

    // fucntion to arrayList to array, because cannot whole arrayList cast to an
    // array, need one element by element
    private void orderSavingArrayOnly() {
        ChickenRiceOrder tempChickenRiceOrder = new ChickenRiceOrder();

        // Cast chicken rice product
        int chickenRiceProductSize = orderChickenRiceList.size();
        ChickenRiceProduct[] tempChickenRiceProductList = new ChickenRiceProduct[chickenRiceProductSize];

        for (int i = 0; i < chickenRiceProductSize; i++) {
            tempChickenRiceProductList[i] = orderChickenRiceList.get(i);
        }
        tempChickenRiceOrder.setChickenRiceProduct(tempChickenRiceProductList);

        // Cast chicken rice product quantity
        int chickenRiceProductQuantitySize = orderChickenRiceQuantityList.size();
        Integer[] tempChickenRiceProductQuantityList = new Integer[chickenRiceProductQuantitySize];

        for (int i = 0; i < chickenRiceProductQuantitySize; i++) {
            tempChickenRiceProductQuantityList[i] = orderChickenRiceQuantityList.get(i);

            tempChickenRiceProductList[i].minusProductQuantity(tempChickenRiceProductQuantityList[i]);
        }
        tempChickenRiceOrder.setChickenRiceOrderQuantity(tempChickenRiceProductQuantityList);

        // Cast add on product
        int chickenRiceAddOnSize = orderAddOnList.size();
        if (chickenRiceAddOnSize > 0) {
            ChickenRiceAddOn[] tempChickenRiceAddOnList = new ChickenRiceAddOn[chickenRiceAddOnSize];

            for (int i = 0; i < chickenRiceAddOnSize; i++) {
                tempChickenRiceAddOnList[i] = orderAddOnList.get(i);
            }
            tempChickenRiceOrder.setChickenRiceAddOn(tempChickenRiceAddOnList);

            // Cast add on quantity product
            int chickenRiceAddOnQuantitySize = orderAddOnQuantityList.size();
            Integer[] tempChickenRiceAddOnQuantiyyList = new Integer[chickenRiceAddOnQuantitySize];

            for (int i = 0; i < chickenRiceAddOnQuantitySize; i++) {
                tempChickenRiceAddOnQuantiyyList[i] = orderAddOnQuantityList.get(i);

                tempChickenRiceAddOnList[i].minusProductQuantity(orderAddOnQuantityList.get(i));
            }
            tempChickenRiceOrder.setChickenAddOnOrderQuantity(tempChickenRiceAddOnQuantiyyList);
        }

        tempChickenRiceOrder.setTableLabel(chickenRiceOrder.getTableLabel());
        tempChickenRiceOrder.setRemark(chickenRiceOrder.getRemark());
        tempChickenRiceOrder.setTotalPrice(chickenRiceOrder.getTotalPrice());

        chickenRiceOrderList.add(tempChickenRiceOrder);
        clearPreviousRecord();
    }

    /**
     * 
     * @param title                        - reciept title
     * @param chickenRiceOrder             - keep the table label, remark and total
     *                                     price
     * @param orderChickenRiceList         - all ordered the chicken rice product
     *                                     list
     * @param orderChickenRiceQuantityList - each of the chicken rice quantity
     * @param orderAddOnList               - all ordered the add on list
     * @param orderAddOnQuantityList       - each of the add on quantiyt
     */
    private void receipt(String title, ChickenRiceOrder chickenRiceOrder,
            ArrayList<ChickenRiceProduct> orderChickenRiceList, ArrayList<Integer> orderChickenRiceQuantityList,
            ArrayList<ChickenRiceAddOn> orderAddOnList, ArrayList<Integer> orderAddOnQuantityList) {
        double mainTotal = 0;
        double addOnTotal = 0;

        System.out.println(
                "\n######################################################################################################");
        System.out.println("Shop Name: " + chickenRiceShop.getshopName() + " ( "
                + chickenRiceShop.getshopRegisterNumber() + " )");
        System.out.println("Address: " + chickenRiceShop.getlocation());
        System.out.println("Telephone: " + chickenRiceShop.getTelephone());
        System.out.println(
                "######################################################################################################");

        System.out
                .println("####################################### " + title + " ################################### ");

        System.out.println("Tabel: " + chickenRiceOrder.getTableLabel());

        // List out all ordered chicker rice product
        System.out.println("\nMain Product:");

        for (int i = 0; i < orderChickenRiceList.size(); i++) {
            System.out.println((i + 1) + " : " + orderChickenRiceList.get(i).getProductName() + "\t x "
                    + orderChickenRiceQuantityList.get(i) + "\tRM " + String.format("%.2f",
                            orderChickenRiceList.get(i).getProductPrice() * orderChickenRiceQuantityList.get(i)));

            mainTotal += orderChickenRiceList.get(i).getProductPrice() * orderChickenRiceQuantityList.get(i);
        }

        // List out all ordered add on product
        if (orderAddOnList.size() > 0) {
            System.out.println("\nAdd On Product:");
            for (int j = 0; j < orderAddOnList.size(); j++) {
                System.out.println((j + 1) + " : " + orderAddOnList.get(j).getProductName() + "\t x "
                        + orderAddOnQuantityList.get(j) + "\tRM " + String.format("%.2f",
                                orderAddOnList.get(j).getProductPrice() * orderAddOnQuantityList.get(j)));

                addOnTotal += orderAddOnList.get(j).getProductPrice() * orderAddOnQuantityList.get(j);
            }
        } else {
            System.out.println("\nEmpty Add On Product");
        }

        System.out.print("\nRemark:");
        System.out.println(chickenRiceOrder.getRemark());

        double tprice = mainTotal + addOnTotal;
        System.out.println("\nTotal Price: RM " + String.format("%.2f", tprice));
        chickenRiceOrder.setTotalPrice(tprice);

        System.out.println(
                "\n######################################################################################################");
    }

    private void clearPreviousRecord() {
        chickenRiceOrder.setTableLabel("");
        orderChickenRiceList.clear();
        orderChickenRiceQuantityList.clear();
        orderAddOnList.clear();
        orderAddOnQuantityList.clear();
    }

    // When user select option 2
    private void makePaymentOption() {
        if (chickenRiceOrder != null) {

            for (int i = 0; i < chickenRiceOrderList.size(); i++) {
                // System.out.println(chickenRiceOrderList.get(i).getTableLabel() + " - RM"
                //         + chickenRiceOrderList.get(i).getTotalPrice());

                ChickenRiceOrder co = new ChickenRiceOrder();
                co.setRemark(chickenRiceOrderList.get(i).getRemark());
                co.setTableLabel(chickenRiceOrderList.get(i).getTableLabel());
                co.setTotalPrice(chickenRiceOrderList.get(i).getTotalPrice());

                receipt("Receipt", co,
                        new ArrayList<>(Arrays.asList(chickenRiceOrderList.get(i).getChickenRiceProduct())),
                        new ArrayList<>(Arrays.asList(chickenRiceOrderList.get(i).getChickenRiceOrderQuantity())),
                        new ArrayList<>(Arrays.asList(chickenRiceOrderList.get(i).getChickenRiceAddOn())),
                        new ArrayList<>(Arrays.asList(chickenRiceOrderList.get(i).getChickenAddOnOrderQuantity())));
            }

            // System.out.println(chickenRiceOrder.toString());
            // receipt("Receipt", chickenRiceOrder, orderChickenRiceList,
            //         orderChickenRiceQuantityList, orderAddOnList, orderAddOnQuantityList);
        } else {
            System.out.println("No any order yet!");
        }
    }

    // When user select option 3
    private void showProductBalanceOption(){

    }

    // When user select option 4
    private void summaryDailySalesOption(){
        chickenRiceSoldList = chickenRiceOrderList;

        int[] soldMainProductQuantity = countMainProductDailySales(chickenRiceSoldList);
        int[] soldAddOnProductQuantity = countAddOnProductDailySales(chickenRiceSoldList);
        double totalSold = 0;

        System.out.println(
                "\n######################################################################################################");
        System.out.println("Shop Name: " + chickenRiceShop.getshopName() + " ( "
                + chickenRiceShop.getshopRegisterNumber() + " )");
        System.out.println("Address: " + chickenRiceShop.getlocation());
        System.out.println("Telephone: " + chickenRiceShop.getTelephone());
        System.out.println(
                "######################################################################################################");

        System.out
                .println("####################################### Daily Sales for " + java.time.LocalDate.now() + " ################################### ");  

        System.out.println("Main Product Status: \n");

        for (int i=0; i<chickenRiceProduct.length; i++){
            System.out.println(chickenRiceProduct[i].getProductName() + "\t x " + soldMainProductQuantity[i] + "\t RM " + (chickenRiceProduct[i].getProductPrice()*soldMainProductQuantity[i]));

            totalSold += chickenRiceProduct[i].getProductPrice()*soldMainProductQuantity[i];
        }

        System.out.println("\nAdd On Product Status: \n");
        
        for (int i=0; i<chickenRiceAddOn.length; i++){
            System.out.println(chickenRiceAddOn[i].getProductName() + "\t x " + soldAddOnProductQuantity[i] + "\t RM " + (chickenRiceAddOn[i].getProductPrice()*soldAddOnProductQuantity[i]));

            totalSold += chickenRiceAddOn[i].getProductPrice()*soldAddOnProductQuantity[i];
        }

        System.out.println("\nTotal daily sales : RM " + String.format("%.2f", totalSold));
                
    }

    private int[] countMainProductDailySales(ArrayList<ChickenRiceOrder> chickenRiceSoldList){
        int[] soldMainProductQuantity = new int[chickenRiceProduct.length];

        for (int l = 0; l<soldMainProductQuantity.length; l++){
            soldMainProductQuantity[l] = 0;
        }

        for (int i = 0; i<chickenRiceSoldList.size(); i++){
            for (int j = 0; j<chickenRiceSoldList.get(i).getChickenRiceProduct().length; j++){
                for (int k = 0; k<chickenRiceProduct.length; k++){
                    if(chickenRiceProduct[k].getProductName().equals(chickenRiceSoldList.get(i).getChickenRiceProduct()[j].getProductName())){
                        soldMainProductQuantity[k]+=chickenRiceSoldList.get(i).getChickenRiceOrderQuantity()[j];
                        break;
                    }
                }
            }
        }

        return soldMainProductQuantity;
    }

    private int[] countAddOnProductDailySales(ArrayList<ChickenRiceOrder> chickenRiceSoldList){
        int[] soldAddOnProductQuantity = new int[chickenRiceProduct.length];

        for (int l = 0; l<soldAddOnProductQuantity.length; l++){
            soldAddOnProductQuantity[l] = 0;
        }

        for (int i = 0; i<chickenRiceSoldList.size(); i++){
            for (int j = 0; j<chickenRiceSoldList.get(i).getChickenRiceAddOn().length; j++){
                for (int k = 0; k<chickenRiceAddOn.length; k++){
                    if(chickenRiceAddOn[k].getProductName().equals(chickenRiceSoldList.get(i).getChickenRiceAddOn()[j].getProductName())){
                        soldAddOnProductQuantity[k]+=chickenRiceSoldList.get(i).getChickenAddOnOrderQuantity()[j];
                        break;
                    }
                }
            }
        }

        return soldAddOnProductQuantity;
    }

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
     * @param label    - Label hits for user
     * @param itemList - item that need to let user choose
     * @param flag     - each invoke the item list may be different so use the flag
     *                 to identify them
     * @return validate_num, whether the number validate and what is the number
     */

    private Validate_Num selectOption(String label, Object[] itemList, int flag) {
        // Variable for checking input value valid or not
        boolean validate = false;
        int itemListInput = -1;
        int itemListLength, i = 0;

        System.out.println(
                "\n######################################################################################################");
        System.out.println(label);

        // Get the list lenght
        itemListLength = itemList.length;

        if (flag == 1) {
            for (i = 0; i < itemListLength; i++) {
                System.out.println((i + 1) + ": " + itemList[i]);
            }

        } else if (flag == 2) {

            if (itemList instanceof ChickenRiceProduct[]) {
                for (i = 0; i < itemListLength; i++) {
                    System.out.println((i + 1) + ": " + ((ChickenRiceProduct) itemList[i]).getProductName() + ", RM "
                            + ((ChickenRiceProduct) itemList[i]).getProductPrice() + "\n "
                            + ((ChickenRiceProduct) itemList[i]).getProductDescription());
                }
            } else if (itemList instanceof ChickenRiceAddOn[]) {
                for (i = 0; i < itemListLength; i++) {
                    System.out.println((i + 1) + ": " + ((ChickenRiceAddOn) itemList[i]).getProductName() + ", RM "
                            + ((ChickenRiceAddOn) itemList[i]).getProductPrice() + " - "
                            + ((ChickenRiceAddOn) itemList[i]).getProductDescription());
                }
            }
        }
        // User allow to select cancle option to turn back main menu

        System.out.println((i + 1) + ": Return previous section");
        System.out.println((i + 2) + ": Return to home memu");
        System.out
                .println("Be Careful: Select option " + (i + 2) + " will be caused previous saved operation be clear.");

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