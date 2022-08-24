package Graphic_User_Interface;
import java.util.ArrayList;
public class ChickenRiceShopMain {
    private static ChickenRiceShop chickenRiceShop;
    private static ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private static ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;

    public static void main(String[] args) {
        // new ChickenRiceShopBuild();
        // new ChickenRiceShopGUI();
        // new ChickenRiceShopTableLabel("test");

        String label = "Chicken Rice Shop";
        
        registerChickenRiceShop();
        addChickenRiceProduct();
        addChickenRiceAddOn();
        new ChickenRiceShopOptionFrame(label, chickenRiceShop, chickenRiceProductsList, chickenRiceAddOnsList);
    }

    private static void registerChickenRiceShop() {
        chickenRiceShop = new ChickenRiceShop("LCL Chicken Rice Shop",
                "Blok B&C, Lot, 5, Seksyen 10, Jalan Bukit, Taman Bukit Mewah, 43000 Kajang, Selangor", "LCL-12345", "012-1234567");

        String tableLabel[] = { "A1", "A2", "A3", "A4", "A5" };
        chickenRiceShop.setTableLabel(tableLabel);
    }

    private static void addChickenRiceProduct() {
        ChickenRiceProduct roastedChicken = new ChickenRiceProduct("Roasted Chicken", 10.50,
                "Crispy skin, juicy meat, flavorful at every turn, Roasted Chicken is one of the tastiest & most satisfying dishes you can ever have.",
                10, 10);

        ChickenRiceProduct steamChicken = new ChickenRiceProduct("Steamed Chicken", 9.50,
                "An ideal method for cooking boneless chicken breasts and small whole birds such as Cornish hens.", 10,
                10);

        ChickenRiceProduct friedChicken = new ChickenRiceProduct("Fried Chicken", 8.50,
                "Done properly, deep-frying creates a satisfying contrast between the crispy-crunchy coating and tender chicken.",
                10, 10);

        chickenRiceProductsList = new ArrayList<>();

        chickenRiceProductsList.add(roastedChicken);
        chickenRiceProductsList.add(steamChicken);
        chickenRiceProductsList.add(friedChicken);
    }

    private static void addChickenRiceAddOn() {
        ChickenRiceAddOn soup = new ChickenRiceAddOn("Soup", 1.00, "A delicous soup", 20, 20);
        ChickenRiceAddOn drink = new ChickenRiceAddOn("Rice", 1.00, "A sepcial drink", 20, 20);
        ChickenRiceAddOn tauge = new ChickenRiceAddOn("tauge", 1.00, "More tauge, more amazing", 20, 20);

        chickenRiceAddOnsList = new ArrayList<>();

        chickenRiceAddOnsList.add(soup);
        chickenRiceAddOnsList.add(drink);
        chickenRiceAddOnsList.add(tauge);
    }
}
