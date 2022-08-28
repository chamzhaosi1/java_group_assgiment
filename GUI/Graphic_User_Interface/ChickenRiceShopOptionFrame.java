package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopOptionFrame extends JFrame {
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceOrder> chickenRiceOrdersList = new ArrayList<>();
    private ArrayList<ChickenRiceOrder> chickenRiceShopSoldList = new ArrayList<>();
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private String label;
    private ArrayList<String> newOrderedTableLabel = new ArrayList<>();
    private ArrayList<String> oldOrderedTableLabel = new ArrayList<>();
    private ChickenRiceShopProductMenuFrame productMenu;
    private ChickenRiceShopPaymentMenuFrame paymentMenu;
    private ChickenRiceShopProductBalanceFrame balanceFrame;
    private ChickenRiceShopDailySalesFrame dailySalesFrame;

    private String title = "POS System - Option Menu";
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

            // System.out.println(newOrderedTableLabel);
            // System.out.println(oldOrderedTableLabel);
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
            // System.out.println(chickenRiceProductsList.get(0).getBalanceQuantity());
            // System.out.println(chickenRiceAddOnsList.get(0).getBalanceQuantity());
            productMenu = new ChickenRiceShopProductMenuFrame(label, newOrderedTableLabel, chickenRiceShop, chickenRiceProductsList, chickenRiceAddOnsList);

            productMenu.addWindowListener(new OrderProductWindowListener());
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
                if (chickenRiceProductsList.get(j).getProductName().equals(tempChickenRiceOrder.getChickenRiceProduct()[i].getProductName())){
                    chickenRiceProductsList.get(j).addProductQuantity(tempChickenRiceOrder.getChickenRiceOrderQuantity()[i]);
                    break;
                }
                
            }
        }

        if(tempChickenRiceOrder.getChickenRiceAddOn() != null && tempChickenRiceOrder.getChickenRiceAddOn() != null){
            for (int i=0; i<tempChickenRiceOrder.getChickenRiceAddOn().length; i++){
                for (int j=0; j<chickenRiceAddOnsList.size(); j++){
                    if (chickenRiceAddOnsList.get(j).getProductName().equals(tempChickenRiceOrder.getChickenRiceAddOn()[i].getProductName())){
                        chickenRiceAddOnsList.get(j).addProductQuantity(tempChickenRiceOrder.getChickenAddOnOrderQuantity()[i]);
                        break;
                    }
                }
            }
        }

    }

    public class OrderProductWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e){}

        @Override
        public void windowClosing(WindowEvent e){}

        @Override
        public void windowClosed(WindowEvent e) {
            // once the product order had been make/and finish, the visible this JFrame again.
            ChickenRiceShopOptionFrame.super.setVisible(true);

            // get the latest order list
            if(productMenu.getOrderDetail() != null){
                // chickenRiceOrdersList.add(productMenu.getOrderDetail());
                chickenRiceOrdersList.add(productMenu.getOrderDetail());
            }
            // System.out.println(chickenRiceOrdersList);


            //get the latest table label, which has been make an order
            newOrderedTableLabel = productMenu.getLatestOrderedTableLabel();
            // System.out.println(newOrderedTableLabel);
        
        }

        @Override
        public void windowIconified(WindowEvent e){}

        @Override
        public void windowDeiconified(WindowEvent e){}

        @Override
        public void windowActivated(WindowEvent e){}

        @Override
        public void windowDeactivated(WindowEvent e){}

    }

    public class MakePaymentBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // if newOrderedTablelabel lower then oldOrderTableLabel, then mean had order
            // been deleteChickenRiceOrder
            // so we need delete the order in the list, and add back the quantity
            if (newOrderedTableLabel.size() < oldOrderedTableLabel.size()) {
                deleteChickenRiceOrder();
            }

            // copy and assign the latest ordered table label to a old ordered table lable
            // in order to compere whether has order been deleted
            oldOrderedTableLabel.removeAll(oldOrderedTableLabel);
            oldOrderedTableLabel.addAll(newOrderedTableLabel);


            ChickenRiceShopOptionFrame.super.setVisible(false);
            paymentMenu = new ChickenRiceShopPaymentMenuFrame(label,
                    chickenRiceShop, chickenRiceOrdersList);

            paymentMenu.addWindowListener(new PaymentWindowListener());
        }

    }

    public class PaymentWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {}

        @Override
        public void windowClosed(WindowEvent e) {
            if (paymentMenu.getPaidOrder() != null){
                chickenRiceShopSoldList.add(paymentMenu.getPaidOrder());
            }
            chickenRiceOrdersList = paymentMenu.getLatestOrderList();
            newOrderedTableLabel = paymentMenu.getLatestTableLabelList();
            ChickenRiceShopOptionFrame.super.setVisible(true);
        }

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}
    }

    public class ShowProductBalanceBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // if newOrderedTablelabel lower then oldOrderTableLabel, then mean had order
            // been deleteChickenRiceOrder
            // so we need delete the order in the list, and add back the quantity
            if (newOrderedTableLabel.size() < oldOrderedTableLabel.size()) {
                deleteChickenRiceOrder();
            }

            // copy and assign the latest ordered table label to a old ordered table lable
            // in order to compere whether has order been deleted
            oldOrderedTableLabel.removeAll(oldOrderedTableLabel);
            oldOrderedTableLabel.addAll(newOrderedTableLabel);

            System.out.println(chickenRiceProductsList.get(0).getBalanceQuantity());
            balanceFrame = new ChickenRiceShopProductBalanceFrame(label, chickenRiceProductsList, chickenRiceAddOnsList);
            chickenRiceProductsList = balanceFrame.getLatestChickenRiceProduct();
            chickenRiceAddOnsList = balanceFrame.getLatestChickenRiceAddOn();
            balanceFrame.addWindowListener(new balanceWindowListener());

            ChickenRiceShopOptionFrame.super.setVisible(false);
        }

    }

    public class balanceWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {}

        @Override
        public void windowClosing(WindowEvent e) {}

        @Override
        public void windowClosed(WindowEvent e) {
            ChickenRiceShopOptionFrame.super.setVisible(true);
            
        }

        @Override
        public void windowIconified(WindowEvent e) {}

        @Override
        public void windowDeiconified(WindowEvent e) {}

        @Override
        public void windowActivated(WindowEvent e) {}

        @Override
        public void windowDeactivated(WindowEvent e) {}

    }

    public class SummaryDailySalesBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dailySalesFrame = new ChickenRiceShopDailySalesFrame(label, chickenRiceShop, chickenRiceShopSoldList, chickenRiceProductsList, chickenRiceAddOnsList);
            dailySalesFrame.addWindowListener(new dailySalesWindowListener());
            ChickenRiceShopOptionFrame.super.setVisible(false);
        }
    }

    public class dailySalesWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e){}

        @Override
        public void windowClosing(WindowEvent e){}

        @Override
        public void windowClosed(WindowEvent e) {
            ChickenRiceShopOptionFrame.super.setVisible(true);
        }

        @Override
        public void windowIconified(WindowEvent e){}

        @Override
        public void windowDeiconified(WindowEvent e){}

        @Override
        public void windowActivated(WindowEvent e){}

        @Override
        public void windowDeactivated(WindowEvent e){}

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
