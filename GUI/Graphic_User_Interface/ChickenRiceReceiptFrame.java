package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceReceiptFrame extends JFrame {
    private ChickenRiceShop chickenRiceShop;
    private String label;
    private ChickenRiceOrder chickenRiceOrder;

    private String title = "POS System - Payment";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel;
    private JButton payBtn;
    private JButton returnBtn;
    private JTextArea receiptTextArea;
    private String header;
    private boolean deleteResult = false;

    public ChickenRiceReceiptFrame(String label, ChickenRiceShop chickenRiceShop,
            ChickenRiceOrder chickenRiceOrder) {
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceOrder = chickenRiceOrder;
        this.label = label;

        this.header = "###############################################\n" +
                "Shop Name: " + chickenRiceShop.getShopName() + "   (" + chickenRiceShop.getShopRegisterNumber() + ")\n"
                +
                "Location: " + chickenRiceShop.getLocation() + "\n###############################################\n" +
                "---------------------------------- Receipt -------------------------------\n";

        setTitle(title + " - " + label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);

        System.out.println(chickenRiceOrder);
    }

    private void makePanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        receiptTextArea = new JTextArea(21, 30);
        receiptTextArea.setBorder(new LineBorder(Color.GRAY, 1));
        receiptTextArea.setText(header);
        receiptTextArea.setEditable(false);
        receiptTextArea.setLineWrap(true);
        mainPanel.add(receiptTextArea, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.setBorder(new EmptyBorder(10, 8, 10, 10));
        btnPanel.setLayout(new GridLayout(2, 1, 10, 10));
        payBtn = new JButton("Pay Now");
        payBtn.addActionListener(new PayBtnListener());
        returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ReturnBtnListener());
        btnPanel.add(payBtn);
        btnPanel.add(returnBtn);

        mainPanel.add(btnPanel, BorderLayout.EAST);

        showReceiptData();
    }

    public void showReceiptData() {
        String receiptData = "";
        for (int i = 0; i < chickenRiceOrder.getChickenRiceProduct().length; i++) {
            receiptData = receiptData + chickenRiceOrder.getChickenRiceProduct()[i].getProductName() + "\n\t x "
                    + chickenRiceOrder.getChickenRiceOrderQuantity()[i] + "\t" + (chickenRiceOrder.getChickenRiceProduct()[i].getProductPrice() * chickenRiceOrder.getChickenRiceOrderQuantity()[i])
                    + "\n";
        }

        if (chickenRiceOrder.getChickenRiceAddOn() != null) {
            for (int i = 0; i < chickenRiceOrder.getChickenRiceAddOn().length; i++) {
                receiptData = receiptData + chickenRiceOrder.getChickenRiceAddOn()[i].getProductName() + "\n\t x "
                        + chickenRiceOrder.getChickenAddOnOrderQuantity()[i] + "\t" + (chickenRiceOrder.getChickenRiceAddOn()[i].getProductPrice() * chickenRiceOrder.getChickenAddOnOrderQuantity()[i])
                        + "\n";
            }
        }

        receiptData = receiptData + "\n###############################################\n" + "\tTotal\t RM "
                + chickenRiceOrder.getTotalPrice() + "\n###############################################\n";
        receiptTextArea.append(receiptData);

    }

    public class ReturnBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChickenRiceReceiptFrame.super.dispose();
        }
    }

    public class PayBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirmResult = JOptionPane.showConfirmDialog(mainPanel, "Confirm want to pay?", "Notice",
                    JOptionPane.INFORMATION_MESSAGE);
            if (confirmResult == 0) {
                deleteResult = true;
            }
            ChickenRiceReceiptFrame.super.dispose();
        }
    }

    public boolean getDeleteResult() {
        return deleteResult;
    }
}

