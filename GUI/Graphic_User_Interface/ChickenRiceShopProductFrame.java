package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;


public class ChickenRiceShopProductFrame extends JFrame{
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

    public ChickenRiceShopProductFrame(String label, String subLabel){
        this.subLabel = subLabel;
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
        JLabel productNameLabel = new JLabel("Product Name: ");
        JLabel initialQuantity = new JLabel("Initial Quantity: ");
        JLabel description = new JLabel("Description: ");
        JLabel unitPrice = new JLabel("Unit Price(RM): ");
        
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
        finishButton.addActionListener(new finishButtonListener());
        finishButton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.gray, 1), 
            BorderFactory.createEmptyBorder(5, 5, 10, 10)));

        addMoreSaveButton = new JButton("Add More & Save");
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

    private class finishButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (subLabel.equals("Main Product")){
                ChickenRiceShopProductFrame.super.dispose();
                new ChickenRiceShopProductFrame("Chicken Rice Shop", "Add On Product");
            }else{
                ChickenRiceShopProductFrame.super.dispose();
                new ChickenRiceShopOptionFrame("Chicken Rice Shop");
            } 
        }
    }

}
