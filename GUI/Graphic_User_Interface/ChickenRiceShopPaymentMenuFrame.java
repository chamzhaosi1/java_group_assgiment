package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopPaymentMenuFrame extends JFrame {
    private ChickenRiceShop chickenRiceShop;
    private String label;
    private JButton tempButton;

    private ChickenRiceOrder chickenRiceOrder;
    private ArrayList<ChickenRiceOrder> chickenRiceOrderList;

    private ChickenRiceShopOrderProductFrame chickenRiceShopOrderProductFrame;

    private String title = "POS System - Payment";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel;
    private JButton[] tableLabelBtnList;
    private JButton returnBtn;
    private ChickenRiceReceiptFrame chickenRiceReceiptFrame;

    public ChickenRiceShopPaymentMenuFrame(String label, ChickenRiceShop chickenRiceShop,
            ArrayList<ChickenRiceOrder> chickenRiceOrderList) {
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceOrderList = chickenRiceOrderList;
        // System.out.println(chickenRiceOrderList);
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
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel hitLable = new JLabel("Which table wants to make payment? ");
        mainPanel.add(hitLable, BorderLayout.NORTH);

        int orderedTableListLength = chickenRiceOrderList.size();

        JPanel tableLabelBtnListPanel = new JPanel();
        tableLabelBtnListPanel.setLayout(new GridLayout(3, 3, 10, 10));
        tableLabelBtnListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        tableLabelBtnList = new JButton[orderedTableListLength];

        // System.out.println(orderedTableListLength);
        for (int i = 0; i < orderedTableListLength; i++) {
            // System.out.println(i);
            // System.out.println(orderedTableListLength);
            if (chickenRiceOrderList.get(i) != null) {
                tableLabelBtnList[i] = new JButton(chickenRiceOrderList.get(i).getTableLabel());
                tableLabelBtnList[i].addActionListener(new orderedTableLabelBtnListener());
                tableLabelBtnListPanel.add(tableLabelBtnList[i]);
            }
        }

        // orderTableLabelDisable();

        mainPanel.add(tableLabelBtnListPanel, BorderLayout.CENTER);

        returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ReturnBtnListener());

        mainPanel.add(returnBtn, BorderLayout.SOUTH);
    }

    /**
     * if the tabel had been take order than, table button become orange
     */
    /*
     * private void orderTableLabelDisable() {
     * if (orderTableLabel.size() > 0) {
     * for (int i = 0; i < orderTableLabel.size(); i++) {
     * for (int j = 0; j < tableLabelBtnList.length; j++) {
     * if (orderTableLabel.get(i).equals(tableLabelBtnList[j].getText())) {
     * tableLabelBtnList[j].setBackground(Color.ORANGE);
     * break;
     * }
     * }
     * }
     * }
     * }
     */

    public class ReturnBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChickenRiceShopPaymentMenuFrame.super.dispose();
        }
    }

    public class orderedTableLabelBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            tempButton = (JButton) e.getSource();
            tempButton.setEnabled(false);
            // System.out.println(chickenRiceOrderList.size());
            for (int i = 0; i < chickenRiceOrderList.size(); i++) {
                // System.out.println(chickenRiceOrderList.get(i).getTableLabel());
                // System.out.println(tempButton.getText());
                // System.out.println(chickenRiceOrderList.get(i).getTableLabel().equals(tempButton.getText()));
                if (chickenRiceOrderList.get(i).getTableLabel().equals(tempButton.getText())) {
                    chickenRiceReceiptFrame = new ChickenRiceReceiptFrame(label,
                            chickenRiceShop, chickenRiceOrderList.get(i));
                    chickenRiceReceiptFrame.addWindowListener(new PaymentWindowListener());
                }
            }

        }
    }

    public class PaymentWindowListener implements WindowListener {

        @Override
        public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {
            // System.out.println(chickenRiceOrder);

            ChickenRiceShopPaymentMenuFrame.super.dispose();
            if (chickenRiceReceiptFrame.getDeleteResult()) {
                for (int i = 0; i < chickenRiceOrderList.size(); i++) {
                    if (chickenRiceOrderList.get(i).getTableLabel().equals(tempButton.getText())) {
                        chickenRiceOrderList.remove(i);
                    }
                }
            }
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

    public ArrayList<ChickenRiceOrder> getLatestOrderList() {
        return chickenRiceOrderList;
    }

}
