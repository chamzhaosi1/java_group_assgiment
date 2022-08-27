package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopOrderProductFrame extends JFrame{
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private ArrayList<ChickenRiceProduct> chickenRiceMainProductsOrderList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnProductOrderList;
    private ArrayList<Integer> chickenRiceMainProductsOrderQuantityList;
    private ArrayList<Integer> chickenRiceAddOnProductsOrderQuantityList;
    private ChickenRiceOrder chickenRiceOrder;
    private String label;
    private String header;
    private String allOrderRemark = "";

    private String title = "POS System";
    private static final int WIDTH = 720;
    private static final int HEIGHT = 400;
    private JPanel mainPanel;
    private JComboBox<String> mainProductComboBox;
    private JComboBox<String> addOnProductComboBox;
    private JSpinner mainProductQuantitySpinner;
    private JSpinner addOnProductQuantitySpinner;
    private JTextArea remarkTextArea;
    private JTextArea orderProductListTArea;
    private JButton addButton;
    private JButton resetButton;
    private JButton finishButton;
    private JButton returnButton;

    public ChickenRiceShopOrderProductFrame(String label, ChickenRiceShop chickenRiceShop,
            ArrayList<ChickenRiceProduct> chickenRiceProductsList, ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList) {
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceProductsList = chickenRiceProductsList;
        this.chickenRiceAddOnsList = chickenRiceAddOnsList;
        this.label = label;

        this.header = "###############################################\n" + 
                            "Shop Name: " + chickenRiceShop.getShopName() + "   (" + chickenRiceShop.getShopRegisterNumber()  + ")\n" +
                            "Location: " + chickenRiceShop.getLocation() + "\n###############################################\n" + 
                            "---------------------------------- Order List -------------------------------\n";

        setTitle(title + " - " + label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    private void makePanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2, 10,10));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel leftAbovePanel = new JPanel();
        leftAbovePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel productLabel = new JLabel("Product: ");
        JLabel addOnLabel = new JLabel("Add on: ");
        JLabel mainQuantityLabel = new JLabel("Quantity: ");
        JLabel AddOnQuantityLabel = new JLabel("Quantity: ");
        JLabel remarkLabel = new JLabel("Remark: ");

        String[] mainProductNameList = castArrayListToArray(1);
        mainProductComboBox = new JComboBox<>(mainProductNameList);
        String[] addOnProductNameList = castArrayListToArray(2);
        addOnProductComboBox = new JComboBox<>(addOnProductNameList);
        mainProductQuantitySpinner = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        addOnProductQuantitySpinner = new JSpinner(new SpinnerNumberModel(0,0,100,1));
        remarkTextArea = new JTextArea(5,10);

        addButton = new JButton("Add");
        addButton.addActionListener(new addButtonListener());

        resetButton = new JButton("Reset");
        resetButton.setEnabled(false);
        resetButton.addActionListener(new resetButtonListener());

        finishButton = new JButton("Finish");
        finishButton.setEnabled(false);
        finishButton.addActionListener(new finishButtonListener());

        returnButton = new JButton("Return");
        returnButton.addActionListener(new returnButtonListener());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,0,0);  //top padding  
        gbc.ipady = 15;  
        leftAbovePanel.add(productLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        leftAbovePanel.add(mainProductComboBox, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        leftAbovePanel.add(mainQuantityLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        leftAbovePanel.add(mainProductQuantitySpinner, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftAbovePanel.add(addOnLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        leftAbovePanel.add(addOnProductComboBox, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        leftAbovePanel.add(AddOnQuantityLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        leftAbovePanel.add(addOnProductQuantitySpinner, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftAbovePanel.add(remarkLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;  
        leftAbovePanel.add(remarkTextArea, gbc);
        leftPanel.add(leftAbovePanel, BorderLayout.NORTH);

        JPanel leftBelowPanel = new JPanel();
        leftBelowPanel.setBorder(new EmptyBorder(10,10,10,10));
        leftBelowPanel.setLayout(new GridLayout(1, 4, 10, 10));
        
        leftBelowPanel.add(addButton);
        leftBelowPanel.add(resetButton);
        leftBelowPanel.add(finishButton);
        leftBelowPanel.add(returnButton);
        leftPanel.add(leftBelowPanel, BorderLayout.SOUTH);

        mainPanel.add(leftPanel);

        JPanel rightPanel = new JPanel();
        orderProductListTArea = new JTextArea(21,30);
        orderProductListTArea.setText(header);
        // orderProductListTArea.setPreferredSize(this.getPreferredSize());
        orderProductListTArea.setEditable(false);
        orderProductListTArea.setLineWrap(true);
        rightPanel.add(orderProductListTArea);
        
        mainPanel.add(rightPanel);

        chickenRiceMainProductsOrderList = new ArrayList<>();
        chickenRiceAddOnProductOrderList = new ArrayList<>();
        chickenRiceMainProductsOrderQuantityList =  new ArrayList<>();
        chickenRiceAddOnProductsOrderQuantityList =  new ArrayList<>();

    }

    private class addButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // check whethe the balance is enough to provide
            if (checkBalanceQuantity()){
                
                resetButton.setEnabled(true);
                finishButton.setEnabled(true);

                // retrieve data
                int mainProductSelectedIndex = mainProductComboBox.getSelectedIndex();
                int mainProductOrderQuantity = (Integer) mainProductQuantitySpinner.getValue();
                int AddOnProductSelectedIndex = addOnProductComboBox.getSelectedIndex();
                int AddOnProductOrderQuantity = (Integer) addOnProductQuantitySpinner.getValue();
                String remark = remarkTextArea.getText();

                String tempMainProductName = chickenRiceProductsList.get(mainProductSelectedIndex).getProductName();
                double tempMainProductTotalPrice = chickenRiceProductsList.get(mainProductSelectedIndex).getProductPrice() * mainProductOrderQuantity;
                chickenRiceMainProductsOrderList.add(chickenRiceProductsList.get(mainProductSelectedIndex));
                chickenRiceMainProductsOrderQuantityList.add(mainProductOrderQuantity);

                // deduct the current product balance. 
                chickenRiceProductsList.get(mainProductSelectedIndex).minusProductQuantity(mainProductOrderQuantity);

                // show the order list
                orderProductListTArea.append(new String(tempMainProductName + "\n\tx " + mainProductOrderQuantity + "\t" + tempMainProductTotalPrice + "\n"));

                // because add on product is unnecessary item to order
                // so, we need to check wheter has order add on product or not
                if (AddOnProductOrderQuantity > 0){
                    String tempAddOnProductName = chickenRiceAddOnsList.get(AddOnProductSelectedIndex).getProductName();
                    double tempAddOnProductTotalPrice = chickenRiceAddOnsList.get(AddOnProductSelectedIndex).getProductPrice() * AddOnProductOrderQuantity;
                    chickenRiceAddOnProductOrderList.add(chickenRiceAddOnsList.get(AddOnProductSelectedIndex));
                    chickenRiceAddOnProductsOrderQuantityList.add(AddOnProductOrderQuantity);

                    // deduct the current product balance. 
                    chickenRiceAddOnsList.get(AddOnProductSelectedIndex).minusProductQuantity(AddOnProductOrderQuantity);

                        // show the order list
                    orderProductListTArea.append(new String(tempAddOnProductName + "\n\tx " + AddOnProductOrderQuantity + "\t" + tempAddOnProductTotalPrice + "\n"));
                }

                // because i don't want to change the ChickenRiceProduct class remark setting, so i just append
                // them together, and after that substring to get each of the remark.
                if (!remark.equals("")){
                    orderProductListTArea.append("**Remark: " + remark + "\n");
                    allOrderRemark = allOrderRemark + "," +remark;
                }else{
                    allOrderRemark = allOrderRemark + "," +"null";
                }

                // auto resize
                ChickenRiceShopOrderProductFrame.super.pack();
            }
        }
    }

    /**
     * To check whethe balance of the product is enough to provide
     * @return if balance enough to provide then retrun true, else false
     */
    private boolean checkBalanceQuantity(){
        String mainProductName = chickenRiceProductsList.get(mainProductComboBox.getSelectedIndex()).getProductName();
        String addOnProductName = chickenRiceAddOnsList.get(mainProductComboBox.getSelectedIndex()).getProductName();
        int mainBalance = chickenRiceProductsList.get(mainProductComboBox.getSelectedIndex()).getBalanceQuantity();
        int addOnBalance = chickenRiceAddOnsList.get(addOnProductComboBox.getSelectedIndex()).getBalanceQuantity();

        if (mainBalance >= (Integer) mainProductQuantitySpinner.getValue()){
            if (addOnBalance >= (Integer) addOnProductQuantitySpinner.getValue()){
                return true;
            }else{
                JOptionPane.showMessageDialog(mainPanel, addOnProductName+ " Not enough to provide. Current Balance: " + addOnBalance, "Notice", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(mainPanel, mainProductName+ " Not enough to provide. Current Balance: " + mainBalance, "Notice", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }

    // clear all of the order list, which save when click the add button
    private class resetButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int resetAnwser = JOptionPane.showConfirmDialog(mainPanel, "Are you sure want to reset all? Be careful, this operation would be recovered..");

            if (resetAnwser == 0){
                resetButton.setEnabled(false);
                finishButton.setEnabled(false);
                addButton.setEnabled(true);

                orderProductListTArea.setText(header);
                chickenRiceMainProductsOrderList.removeAll(chickenRiceMainProductsOrderList);
                chickenRiceAddOnProductOrderList.removeAll(chickenRiceAddOnProductOrderList);
                chickenRiceMainProductsOrderQuantityList.removeAll(chickenRiceMainProductsOrderQuantityList);
                chickenRiceAddOnProductsOrderQuantityList.removeAll(chickenRiceAddOnProductsOrderQuantityList);
                
                chickenRiceOrder = null;
            }

            // auto resize
            ChickenRiceShopOrderProductFrame.super.pack();
        }
    }

    //retrieve all the data and assign to a order list variable, and count the total amount
    private class finishButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int finishAnwser = JOptionPane.showConfirmDialog(mainPanel, "Are you sure finish take the order? Be careful, you wil not be allow to add the further product for this order bill..");
            double totalOrderPrice = 0;
            chickenRiceOrder = new ChickenRiceOrder();

            if (finishAnwser == 0){
                // unable to add order again
                addButton.setEnabled(false);
                finishButton.setEnabled(false);

                ChickenRiceProduct[] chickenRiceProductsOrdered = new ChickenRiceProduct[chickenRiceMainProductsOrderList.size()];
                for (int i = 0; i<chickenRiceMainProductsOrderList.size(); i++){
                    chickenRiceProductsOrdered[i] = chickenRiceMainProductsOrderList.get(i);
                }

                Integer[] chickenRiceProductsOrderedQuantity = new Integer[chickenRiceMainProductsOrderQuantityList.size()];
                for (int i = 0; i<chickenRiceMainProductsOrderQuantityList.size(); i++){
                    chickenRiceProductsOrderedQuantity[i] = chickenRiceMainProductsOrderQuantityList.get(i);

                    totalOrderPrice += chickenRiceProductsOrdered[i].getProductPrice() * chickenRiceProductsOrderedQuantity[i];
                }

                chickenRiceOrder.setChickenRiceProduct(chickenRiceProductsOrdered);
                chickenRiceOrder.setChickenRiceOrderQuantity(chickenRiceProductsOrderedQuantity);

                if (chickenRiceAddOnProductOrderList.size() > 0){
                    ChickenRiceAddOn[] chickenRiceAddOnOrdered = new ChickenRiceAddOn[chickenRiceAddOnProductOrderList.size()];
                    for (int i = 0; i<chickenRiceAddOnProductOrderList.size(); i++){
                        chickenRiceAddOnOrdered[i] = chickenRiceAddOnProductOrderList.get(i);
                    }

                    Integer[] chickenRiceAddOnsOrderedQuantity = new Integer[chickenRiceAddOnProductsOrderQuantityList.size()];
                    for (int i = 0; i<chickenRiceAddOnProductsOrderQuantityList.size(); i++){
                        chickenRiceAddOnsOrderedQuantity[i] = chickenRiceAddOnProductsOrderQuantityList.get(i);

                        totalOrderPrice += chickenRiceAddOnOrdered[i].getProductPrice() * chickenRiceAddOnsOrderedQuantity[i];
                    }

                    chickenRiceOrder.setChickenRiceAddOn(chickenRiceAddOnOrdered);
                    chickenRiceOrder.setChickenAddOnOrderQuantity(chickenRiceAddOnsOrderedQuantity);
                }

                chickenRiceOrder.setTotalPrice(totalOrderPrice);
                chickenRiceOrder.setRemark(allOrderRemark);

            }

            String total = "\n###############################################\n"+ "\t Total: \t" + totalOrderPrice + "\n###############################################\n";
            orderProductListTArea.append(total);   
            
            // auto resize
            ChickenRiceShopOrderProductFrame.super.pack();
            
        }

    }

    private class returnButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int returnAnwser;
            
            // if the add button is disable to click, which mean user has add the order
            if (!addButton.isEnabled()){
                returnAnwser = JOptionPane.showConfirmDialog(mainPanel, "Please make sure you has been finished order for this table, because you will not be allowed to edit it after return..");
            }else {
                // if the add button is able to click, which mean user hasn't click the finish button
                returnAnwser = JOptionPane.showConfirmDialog(mainPanel, "You haven't save the order, do u want cancel the order?");
            }

            if (returnAnwser == 0){
                ChickenRiceShopOrderProductFrame.super.dispose();
            }
            
        }

    }

    public ChickenRiceOrder getChickenRiceOrderDetail(){
        System.out.println(chickenRiceOrder);
        return chickenRiceOrder;
    }
  
    /**
     * To cast the arraylist to array
     * @param flag - 1= main product, 2 add on product
     * @return after cast array 
     */
    private String[] castArrayListToArray(int flag){
        if (flag == 1){
            String[] productName = new String[chickenRiceProductsList.size()];

            for (int i = 0; i<productName.length; i++){
                productName[i] = chickenRiceProductsList.get(i).getProductName();
            }
            return productName;

        }else{
            String[] productName = new String[chickenRiceAddOnsList.size()];

            for (int i = 0; i<productName.length; i++){
                productName[i] = chickenRiceAddOnsList.get(i).getProductName();
            }
            return productName;
        }
    }

    
} 
