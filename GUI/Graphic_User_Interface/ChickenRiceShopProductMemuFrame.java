package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopProductMemuFrame extends JFrame {
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private ArrayList<String> orderTableLabel;
    private ChickenRiceProduct chickenRiceProduct;
    private ChickenRiceAddOn chickenRiceAddOn;
    private String label;
    private JButton tempButton;

    private ChickenRiceOrder chickenRiceOrder;
    private ArrayList<ChickenRiceOrder> chickenRiceOrderList;

    private ChickenRiceShopOrderProductFrame chickenRiceShopOrderProductFrame;

    private String title = "POS System";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel;
    private JButton[] tableLabelBtnList;
    private JButton returnBtn;

    public ChickenRiceShopProductMemuFrame(String label, ArrayList<String> orderTableLabel, ChickenRiceShop chickenRiceShop,
            ArrayList<ChickenRiceProduct> chickenRiceProductsList, ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList) {
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceProductsList = chickenRiceProductsList;
        this.chickenRiceAddOnsList = chickenRiceAddOnsList;
        this.orderTableLabel = orderTableLabel;
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
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));

        JLabel hitLable = new JLabel("Which table wants to make an order? ");
        mainPanel.add(hitLable, BorderLayout.NORTH);

        int tableListLength = chickenRiceShop.getTableLabel().length;

        JPanel tableLabelBtnListPanel = new JPanel();
        tableLabelBtnListPanel.setLayout(new GridLayout(3, 3, 10, 10));
        tableLabelBtnListPanel.setBorder(new EmptyBorder(10,10,10,10));
        tableLabelBtnList = new JButton[tableListLength];

        // System.out.println(tableListLength);
        for (int i = 0; i<tableListLength; i++) {
            tableLabelBtnList[i] = new JButton(chickenRiceShop.getTableLabel()[i]);
            tableLabelBtnList[i].addActionListener(new tableLabelBtnListener());
            tableLabelBtnListPanel.add(tableLabelBtnList[i]);
        }

        orderTableLabelDisable();

        mainPanel.add(tableLabelBtnListPanel, BorderLayout.CENTER);

        returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ReturnBtnListener());

        mainPanel.add(returnBtn, BorderLayout.SOUTH);
    }

    private void orderTableLabelDisable(){
        if(orderTableLabel.size() > 0){
            for (int i=0; i<orderTableLabel.size(); i++){
                for(int j=0; j<tableLabelBtnList.length; j++){
                    if (orderTableLabel.get(i).equals(tableLabelBtnList[j].getText())){
                        tableLabelBtnList[j].setBackground(Color.ORANGE);
                        break;
                    }
                }
            }
        }
    }

    public class ReturnBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ChickenRiceShopProductMemuFrame.super.dispose();
        }
    }

    public class tableLabelBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // System.out.println(e.getSource());
            tempButton = (JButton) e.getSource();
            tempButton.setEnabled(false);

            if (!(tempButton.getBackground() == Color.ORANGE)){
                String tableLabel = tempButton.getText();

                chickenRiceOrder = new ChickenRiceOrder();
                chickenRiceOrder.setTableLabel(tableLabel);

                chickenRiceShopOrderProductFrame = new ChickenRiceShopOrderProductFrame(label, chickenRiceShop, chickenRiceProductsList, chickenRiceAddOnsList);
                chickenRiceShopOrderProductFrame.addWindowListener(new CustomWindowListener());
            }else{
                
            }
        }
    }

    public class CustomWindowListener implements WindowListener{

        @Override
        public void windowOpened(WindowEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            ChickenRiceOrder temChickenRiceOrder = chickenRiceShopOrderProductFrame.getChickenRiceOrderDetail();
            
            if(temChickenRiceOrder != null){
                chickenRiceOrder.setChickenRiceProduct(temChickenRiceOrder.getChickenRiceProduct());
                chickenRiceOrder.setChickenRiceOrderQuantity(temChickenRiceOrder.getChickenRiceOrderQuantity());
                chickenRiceOrder.setChickenRiceAddOn(temChickenRiceOrder.getChickenRiceAddOn());
                chickenRiceOrder.setChickenAddOnOrderQuantity(temChickenRiceOrder.getChickenAddOnOrderQuantity());
                chickenRiceOrder.setRemark(temChickenRiceOrder.getRemark());
                chickenRiceOrder.setTotalPrice(temChickenRiceOrder.getTotalPrice());
            }

            // System.out.println(chickenRiceOrder);

            ChickenRiceShopProductMemuFrame.super.dispose();
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

    public ChickenRiceOrder getOrderDetail(){
        return chickenRiceOrder;
    }
}
