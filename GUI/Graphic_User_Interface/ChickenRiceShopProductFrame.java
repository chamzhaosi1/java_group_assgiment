package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class ChickenRiceShopProductFrame extends JFrame{
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private ChickenRiceProduct chickenRiceProduct;
    private ChickenRiceAddOn chickenRiceAddOn;
    private String label;

    private String title = "POS System";
    private String subLabel;
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel = new JPanel();
    private JTextField productNameTextField;
    private JTextField initialQuantityTextField;
    private JTextField descriptionTextField;
    private JTextField unitPriceTextField;
    private JButton finishButton;
    private JButton addMoreSaveButton;

    public ChickenRiceShopProductFrame(String label, String subLabel, ChickenRiceShop chickenRiceShop, ArrayList<ChickenRiceProduct> chickenRiceProductsList, ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList){
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceProductsList = chickenRiceProductsList;
        this.chickenRiceAddOnsList = chickenRiceAddOnsList;
        this.label = label;
        this.subLabel = subLabel;

        // if arraylist == null, then create a new object
        createObjectArrayList();

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
        JPanel productFillInPanel = new JPanel();
        productFillInPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();  

        JLabel mainProductLabel = new JLabel(subLabel + " Configuration ");
        JLabel productNameLabel = new JLabel("Product Name*: ");
        JLabel initialQuantity = new JLabel("Initial Quantity*: ");
        JLabel description = new JLabel("Description*: ");
        JLabel unitPrice = new JLabel("Unit Price(RM)*: ");
        
        productNameTextField = new JTextField(20);
        initialQuantityTextField = new JTextField(20);
        descriptionTextField = new JTextField(20);
        unitPriceTextField = new JTextField(20);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,0,0,0);  //top padding  
        gbc.ipady = 15;  
        productFillInPanel.add(mainProductLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        productFillInPanel.add(productNameLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        productFillInPanel.add(productNameTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        productFillInPanel.add(initialQuantity, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        productFillInPanel.add(initialQuantityTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        productFillInPanel.add(description, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        productFillInPanel.add(descriptionTextField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        productFillInPanel.add(unitPrice, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        productFillInPanel.add(unitPriceTextField, gbc);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(productFillInPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        finishButton = new JButton("Finish");
        finishButton.setEnabled(false);
        finishButton.addActionListener(new finishButtonListener());
        finishButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.gray, 1), 
            BorderFactory.createEmptyBorder(5, 5, 10, 10)));

        addMoreSaveButton = new JButton("Add More & Save");
        addMoreSaveButton.addActionListener(new addMoreSaveButtonListener());
        addMoreSaveButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.gray, 1), 
            BorderFactory.createEmptyBorder(5, 5, 10, 10)));
        // addMoreSaveButton.setBorder(new EmptyBorder(10,50,10,10));
        
        buttonPanel.setLayout(new GridLayout(1,2, 10,10));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        buttonPanel.add(finishButton);
        buttonPanel.add(addMoreSaveButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private class addMoreSaveButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //check whether all textfield had been fill in
            if (checkAllTextField()){

                // check the initail quantity and price data type is int and double or not
                if (checkInputValidate()){
                    // retrieve all product data
                    retrieveAllProductData();
                    
                    if (subLabel.equals("Main Product")){
                        // chickenRiceProductsList = new ArrayList<>();

                        // one by one add the product to list
                        chickenRiceProductsList.add(chickenRiceProduct);
                        System.out.println(chickenRiceProductsList);
                        System.out.println(chickenRiceProductsList.toString());
                    }else{
                        // chickenRiceAddOnsList = new ArrayList<>();

                        // one by one add the product to list
                        chickenRiceAddOnsList.add(chickenRiceAddOn);
                        System.out.println(chickenRiceAddOnsList);
                        System.out.println(chickenRiceAddOnsList.toString());
                    }

                    // clear all text field
                    clearAllTextField();

                    // clickable the finish button
                    finishButton.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(mainPanel, "You have to fill in with correct data type", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }else{
                JOptionPane.showMessageDialog(mainPanel, "You have to fill in all product's information that we required..", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class finishButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // check whether text has fill in some thing or not
            if (checkDataHaventSave()){
                JOptionPane.showMessageDialog(mainPanel, "Please make sure you have saved the data before clicking the finish, if not please let them blank..", "Error", JOptionPane.ERROR_MESSAGE);
            }else{
                // if the sublabel is main product, then next need to invoke add on product JFrame
                if (subLabel.equals("Main Product")){
                    ChickenRiceShopProductFrame.super.dispose();
                    new ChickenRiceShopProductFrame(label, "Add On Product", chickenRiceShop, chickenRiceProductsList, null);
                }else{
                    // if the sublabel not main product or add on product, then next need to invoke option frame
                    ChickenRiceShopProductFrame.super.dispose();
                    new ChickenRiceShopOptionFrame(label, chickenRiceShop, chickenRiceProductsList, chickenRiceAddOnsList);
                } 
            } 
        }
    }

    // clear all the text field, ready for filling in other product
    private void clearAllTextField(){
        productNameTextField.setText("");
        initialQuantityTextField.setText("");
        descriptionTextField.setText("");
        unitPriceTextField.setText("");
    }


    private void retrieveAllProductData(){
        //if sublable is main product, data will be assign to main product class
        if (subLabel.equals("Main Product")){
            chickenRiceProduct = new ChickenRiceProduct();

            chickenRiceProduct.setProductName(productNameTextField.getText());
            chickenRiceProduct.setInitialQuantity(Integer.parseInt(initialQuantityTextField.getText()));
            chickenRiceProduct.setProductDescription(descriptionTextField.getText());
            chickenRiceProduct.setProductPrice(Double.parseDouble(unitPriceTextField.getText()));
            chickenRiceProduct.setBalanceQuantity(chickenRiceProduct.getInitialQuantity());

        }else{
            //if sublable is add on product, data will be assign to add on product class
            chickenRiceAddOn = new ChickenRiceAddOn();

            chickenRiceAddOn.setProductName(productNameTextField.getText());
            chickenRiceAddOn.setInitialQuantity(Integer.parseInt(initialQuantityTextField.getText()));
            chickenRiceAddOn.setProductDescription(descriptionTextField.getText());
            chickenRiceAddOn.setProductPrice(Double.parseDouble(unitPriceTextField.getText()));
            chickenRiceAddOn.setBalanceQuantity(chickenRiceAddOn.getInitialQuantity());
        }
    }

    private boolean checkInputValidate(){
        try {
            Integer.parseInt(initialQuantityTextField.getText());
            Double.parseDouble(unitPriceTextField.getText());
            return true;
        } catch (Exception e) {
            return false;
        }   
    }

    // if one of the text field is not clear/empty, then it may mean got item haven't save yet
    private boolean checkDataHaventSave(){
        if (!productNameTextField.getText().equals("")){
            return true;
        }

        if (!initialQuantityTextField.getText().equals("")){
            return true;
        }

        if (!descriptionTextField.getText().equals("")){
            return true;
        }

        if (!unitPriceTextField.getText().equals("")){
            return true;
        }

        return false;
    }

    // if the one of the text field is empty, then will be disallow to save
    private boolean checkAllTextField(){
        if (productNameTextField.getText().equals("")){
            return false;
        }

        if (initialQuantityTextField.getText().equals("")){
            return false;
        }

        if (descriptionTextField.getText().equals("")){
            return false;
        }

        if (unitPriceTextField.getText().equals("")){
            return false;
        }

        return true;
    }

    
    private void createObjectArrayList(){
        if (chickenRiceProductsList == null){
            chickenRiceProductsList = new ArrayList<>();
        }

        if (chickenRiceAddOnsList == null){
            chickenRiceAddOnsList = new ArrayList<>();
        }
    }
}
