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
    private ChickenRiceProduct chickenRiceProduct;
    private ChickenRiceAddOn chickenRiceAddOn;
    private String label;

    private String title = "POS System";
    private static final int WIDTH = 700;
    private static final int HEIGHT = 300;
    private JPanel mainPanel;
    private JComboBox<String> mainProductComboBox;
    private JComboBox<String> addOnProductComboBox;
    private JSpinner mainProductQuantitySpinner;
    private JSpinner addOnProductQuantitySpinner;
    private JTextArea remarkTextArea;
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
        addOnProductQuantitySpinner = new JSpinner(new SpinnerNumberModel(1,1,100,1));
        remarkTextArea = new JTextArea(5,10);

        addButton = new JButton("Add");
        resetButton = new JButton("Reset");
        finishButton = new JButton("Finish");
        returnButton = new JButton("Return");

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
        JTextArea orderProductListTArea = new JTextArea(15,20);
        // orderProductListTArea.setPreferredSize(this.getPreferredSize());
        orderProductListTArea.setEditable(false);
        rightPanel.add(orderProductListTArea);
        
        mainPanel.add(rightPanel);
    }

    /**
     * 
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
