package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopOptionFrame extends JFrame {
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private ChickenRiceProduct chickenRiceProduct;
    private ChickenRiceAddOn chickenRiceAddOn;
    private String label;


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
            System.out.println(chickenRiceProductsList);
            System.out.println(chickenRiceAddOnsList);
            ChickenRiceShopOptionFrame.super.setVisible(false);
            ChickenRiceShopProductMemuFrame productMenu = new ChickenRiceShopProductMemuFrame(label, chickenRiceShop, chickenRiceProductsList, chickenRiceAddOnsList);

            productMenu.addWindowListener(new CustomWindowListener());

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
            ChickenRiceShopOptionFrame.super.setVisible(true);

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
