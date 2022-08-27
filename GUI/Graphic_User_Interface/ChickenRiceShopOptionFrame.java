package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopOptionFrame extends JFrame {
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceOrder> chickenRiceOrdersList = new ArrayList<>();
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private String label;
    private ArrayList<String> newOrderedTableLabel = new ArrayList<>();
    private ArrayList<String> oldOrderedTableLabel = new ArrayList<>();
    private ChickenRiceShopProductMemuFrame productMenu;

    private String title = "POS System";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel = new JPanel();
    private JButton productMenuBtn;
    private JButton makePaymentBtn;
    private JButton showProductBalanceBtn;
    private JButton summaryDailySalesBtn;
    private JButton exitBtn;

    public ChickenRiceShopOptionFrame(String label, ChickenRiceShop chickenRiceShop, ArrayList<ChickenRiceProduct> chickenRiceProductsList, ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList) {
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceProductsList = chickenRiceProductsList;
        this.chickenRiceAddOnsList = chickenRiceAddOnsList;
        this.label = label;

        setTitle(title + " - " + label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    public void makePanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel optionMenuLabel = new JLabel("Option Menu");
        productMenuBtn = new JButton("Product Menu");
        productMenuBtn.addActionListener(new ProductMenuBtnListener());

        makePaymentBtn = new JButton("Make Payment");
        makePaymentBtn.addActionListener(new MakePaymentBtnListener());

        showProductBalanceBtn = new JButton("Show Product Balance");
        showProductBalanceBtn.addActionListener(new ShowProductBalanceBtnListener());

        summaryDailySalesBtn = new JButton("Summary Daily Sales");
        summaryDailySalesBtn.addActionListener(new SummaryDailySalesBtnListener());

        exitBtn = new JButton("Exit");
        exitBtn.addActionListener(new ExitBtnListener());

        mainPanel.add(optionMenuLabel);
        mainPanel.add(productMenuBtn);
        mainPanel.add(makePaymentBtn);
        mainPanel.add(showProductBalanceBtn);
        mainPanel.add(summaryDailySalesBtn);
        mainPanel.add(exitBtn);
    }

    public class ProductMenuBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // if user has make a order, then the balance of the product need to deduct
            if (chickenRiceOrdersList.size() > 0){
                minusProductBalance(); 
            }

            // if newOrderedTablelabel lower then oldOrderTableLabel, then mean had order been deleteChickenRiceOrder
            // so we need delete the order in the list, and add back the quantity
            if(newOrderedTableLabel.size() < oldOrderedTableLabel.size()){
                deleteChickenRiceOrder();
            }

            // copy and assign the latest ordered table label to a old ordered table lable
            // in order to compere whether has order been deleted
            oldOrderedTableLabel.removeAll(oldOrderedTableLabel);
            oldOrderedTableLabel.addAll(newOrderedTableLabel);

            // temporary hide the option Jframe vision
            ChickenRiceShopOptionFrame.super.setVisible(false);
            productMenu = new ChickenRiceShopProductMemuFrame(label, newOrderedTableLabel, chickenRiceShop, chickenRiceProductsList, chickenRiceAddOnsList);

            productMenu.addWindowListener(new CustomWindowListener());
        }

    }

    // delete an order
    private void deleteChickenRiceOrder(){
        oldOrderedTableLabel.removeAll(newOrderedTableLabel);
        
        for (int i = 0; i<chickenRiceOrdersList.size(); i++){
            if (oldOrderedTableLabel.get(0).equals(chickenRiceOrdersList.get(i).getTableLabel())){
                addBackDeleteOrderedProductQuantity(chickenRiceOrdersList.get(i));

                chickenRiceOrdersList.remove(i);
                break;
            }
        }
    }

    // add back the delete order quantity
    private void addBackDeleteOrderedProductQuantity(ChickenRiceOrder tempChickenRiceOrder){

        for (int i=0; i<tempChickenRiceOrder.getChickenRiceProduct().length; i++){
            for (int j=0; j<chickenRiceProductsList.size(); j++){
                if (chickenRiceProductsList.get(j).getProductName().equals(tempChickenRiceOrder.getChickenRiceProduct()[i].getProductName()));
                chickenRiceProductsList.get(j).addProductQuantity(tempChickenRiceOrder.getChickenRiceOrderQuantity()[i]);
                break;
            }
        }

        if(tempChickenRiceOrder.getChickenRiceAddOn() != null && tempChickenRiceOrder.getChickenRiceAddOn() != null){
            for (int i=0; i<tempChickenRiceOrder.getChickenRiceAddOn().length; i++){
                for (int j=0; j<chickenRiceAddOnsList.size(); j++){
                    if (chickenRiceAddOnsList.get(j).getProductName().equals(tempChickenRiceOrder.getChickenRiceAddOn()[i].getProductName()));
                    chickenRiceAddOnsList.get(j).addProductQuantity(tempChickenRiceOrder.getChickenAddOnOrderQuantity()[i]);
                    break;
                }
            }
        }

    }

    // deduct the product quantity after finish take an order
    private void minusProductBalance(){
        ChickenRiceOrder tempChickenRiceOrder = productMenu.getOrderDetail();

        if(tempChickenRiceOrder !=null && tempChickenRiceOrder.getChickenRiceProduct() != null){
            newOrderedTableLabel.add(tempChickenRiceOrder.getTableLabel());
            

            for (int i=0; i<tempChickenRiceOrder.getChickenRiceProduct().length; i++){
                for (int j=0; j<chickenRiceProductsList.size(); j++){
                    if (chickenRiceProductsList.get(j).getProductName().equals(tempChickenRiceOrder.getChickenRiceProduct()[i].getProductName()));
                    chickenRiceProductsList.get(j).minusProductQuantity(tempChickenRiceOrder.getChickenRiceOrderQuantity()[i]);
                    break;
                }
            }
      
            if(tempChickenRiceOrder.getChickenRiceAddOn() != null && tempChickenRiceOrder.getChickenRiceAddOn() != null){
                for (int i=0; i<tempChickenRiceOrder.getChickenRiceAddOn().length; i++){
                    for (int j=0; j<chickenRiceAddOnsList.size(); j++){
                        if (chickenRiceAddOnsList.get(j).getProductName().equals(tempChickenRiceOrder.getChickenRiceAddOn()[i].getProductName()));
                        chickenRiceAddOnsList.get(j).minusProductQuantity(tempChickenRiceOrder.getChickenAddOnOrderQuantity()[i]);
                        break;
                    }
                }
            }
        }
    }

    public class CustomWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void windowClosing(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void windowClosed(WindowEvent e) {
            // once the product order had been make/and finish, the visible this JFrame again.
            ChickenRiceShopOptionFrame.super.setVisible(true);

            // get the latest order list
            chickenRiceOrdersList.add(productMenu.getOrderDetail());

            //get the latest table label, which has been make an order
            newOrderedTableLabel = productMenu.getLatestOrderedTableLabel();
        }

        @Override
        public void windowIconified(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void windowActivated(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public class MakePaymentBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public class ShowProductBalanceBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public class SummaryDailySalesBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

        }

    }

    public class ExitBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int a = JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure want to exit the system, your unsaved data may not be recovered!");

            if (a == 0) {
                ChickenRiceShopOptionFrame.super.dispose();
                System.exit(0);
            }
        }

    }

}
