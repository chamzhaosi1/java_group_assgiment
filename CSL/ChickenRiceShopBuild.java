import java.util.Scanner;
import java.util.ArrayList;

public class ChickenRiceShopBuild {
    private static final String INPUT_ERROR_MESSAGE = "Your selection is invalid please try again!!";

    private ChickenRiceProduct chickenRiceProduct[];
    private ChickenRiceShop chickenRiceShop;
    private ChickenRiceAddOn chickenRiceAddOn[];
    private ChickenRiceOrder chickenRiceOrder;
    private ArrayList<ChickenRiceOrder> chickenRiceOrderList = new ArrayList<>();
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
        // Variable for checking input value valid or not
        boolean validate = false;
        int tabelLabelInput = -1;
        chickenRiceOrder = new ChickenRiceOrder();
        int tabelLabelLength, i;

        do {
            System.out.println(
                    "\n######################################################################################################");
            System.out.println("Please choose which table label mark an order:");
            String tabelLabelList[] = chickenRiceShop.getTableLable();

            // Get the list lenght
            tabelLabelLength = tabelLabelList.length;

            for (i = 0; i < tabelLabelLength; i++) {
                System.out.println((i + 1) + ": " + tabelLabelList[i]);
            }
            // User allow to select cancle option to turn back main menu
            System.out.println((i + 1) + ": Return previous section");
            System.out.println((i + 2) + ": Return to home memu");

            try {
                // Read user input integer
                tabelLabelInput = input.nextInt();
                // System.out.println(tabelLabelInput);
                // System.out.println("1");
                // tabelLabelList.length+2 = 8, less than 8, return true
                validate = checkInputIntValidation(tabelLabelInput, 0, (tabelLabelLength + 3), INPUT_ERROR_MESSAGE);
                // System.out.println("2");
                // If validate == ture
                if (validate && tabelLabelInput < (tabelLabelLength + 1)) {
                    // Because the length of the array only have 5, and index 0-4, so need to minus
                    // one
                    tabelLabelInput -= 1;
                    chickenRiceOrder.setTableLabel(tabelLabelList[tabelLabelInput]);

                    // After select the table label, call product list function
                    boolean returnThisPage = productListOption();

                    // If the return == true, then loop again, while return == false, back to the
                    // home menu
                    if (returnThisPage) {
                        validate = false;
                    } else if (!returnThisPage) {

                    }

                    // Either select 6 or 7, process will return to home menu section
                } else if (tabelLabelInput == (tabelLabelLength + 1) || tabelLabelInput == (tabelLabelLength + 2)) {
                    System.out.println("Return to the home menu!\n");
                    validate = true;
                }

            } catch (Exception e) {
                // If the user not key in the integer number, then handle the exception
                // System.out.println(e);
                System.out.println(INPUT_ERROR_MESSAGE);
                // Clear the system input buffer before loop again
                clearInputBuffer();
            }
        } while (!validate);
    }

    public boolean productListOption() {
        // Variable for checking input value valid or not
        boolean validate = false;
        int productListInput = -1;
        int productItemLength, i;
        ArrayList<ChickenRiceProduct> orderProdcutList = new ArrayList<>();
        ArrayList<Integer> orderQuntityList = new ArrayList<>();

        do {
            System.out.println(
                    "\n######################################################################################################");
            System.out.println("Please choose the item:");
            // String productItemList[] = chickenRiceProduct.getProductName();

            // Get the list lenght
            productItemLength = chickenRiceProduct.length;

            for (i = 0; i < productItemLength; i++) {
                System.out.println((i + 1) + ": " + chickenRiceProduct[i].getProductName() + ", RM "
                        + chickenRiceProduct[i].getProductPrice() + "\n "
                        + chickenRiceProduct[i].getProductDescription() + "\n balance: "
                        + chickenRiceProduct[i].getBalanceQuantity());
            }
            // User allow to select cancle option to turn back main menu
            System.out.println((i + 1) + ": Return to previous section");
            System.out.println((i + 2) + ": Return to home mune");

            try {
                // Read user input integer
                productListInput = input.nextInt();
                // System.out.println(productListInput);
                // System.out.println("1");
                // tabelLabelList.length+3 = 6, less than 6, return true
                validate = checkInputIntValidation(productListInput, 0, (productItemLength + 3), INPUT_ERROR_MESSAGE);
                // System.out.println("2");
                // if validate == ture
                if (validate && productListInput < (productItemLength + 1)) {
                    // because the length of the array only have 5, and index 0-4, so need to minus
                    // one
                    productListInput -= 1;
                    orderProdcutList.add(chickenRiceProduct[productListInput]);

                    System.out.println(
                            "\n######################################################################################################");
                    System.out.print("Please enter the order quantity: ");

                    try {
                        int prodcutItemQuantity = input.nextInt();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                } else if (productListInput == (productItemLength + 1)) {
                    System.out.println("Return to previous menu!");
                    break;
                } else if (productListInput == (productItemLength + 2)) {
                    System.out.println("Return to home menu!");
                    return false;
                }

            } catch (Exception e) {
                // If the user not key in the integer number, then handle the exception
                // System.out.println(e);
                System.out.println(INPUT_ERROR_MESSAGE);
                // Clear the system input buffer before loop again
                clearInputBuffer();
            }
        } while (!validate);

        return true;
    }

    // When user select option 2

    // When user select option 3

    // When user select option 4

    // When user select option 5
    private void exitOption() {
        System.out.println("Are you sure want to exit the system, your unsaved data may not be recovered! (Y/N)");
        Scanner input = new Scanner(System.in);

        // Read the first letter of user key in only
        char charOptionInput = input.next().charAt(0);

        // Check whether the user's input validate
        boolean validate = checkInputBoolValidation(charOptionInput, INPUT_ERROR_MESSAGE);

        // If validate format, then check whether y or n
        if (validate && (charOptionInput == 'Y' || charOptionInput == 'y')) {
            System.exit(0);
            clearInputBuffer();
        }
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