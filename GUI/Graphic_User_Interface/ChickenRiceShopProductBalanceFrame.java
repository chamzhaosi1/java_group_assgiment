package Graphic_User_Interface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer; // import center text method
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopProductBalanceFrame extends JFrame{
    private final static int WIDTH = 500;
    private final static int HEIGHT = 350;
    private JPanel mainPanel;
    private JPanel tablePanel;
    private JPanel productBlPanel;
    private JScrollPane scrollPane;
    private JSpinner mainProductQuantitySpinner;
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private String label;
    private JLabel productBlLabel;
    private JTable productBlTable;
    private GridBagConstraints gbc = new GridBagConstraints();  
    
    private String title = "POS System - Product Balance";
    private JComboBox<String> mainProductComboBox;
    private JButton addButton;
    private JButton returnButton;

    public ChickenRiceShopProductBalanceFrame(String label, ArrayList<ChickenRiceProduct> chickenRiceProductsList, ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList) {
        this.chickenRiceProductsList = chickenRiceProductsList;
        this.chickenRiceAddOnsList = chickenRiceAddOnsList;
        this.label = label;

        // Set the window title.
        setTitle(title + " - " + label);

        // Specify what happens when the close button is clicked.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        // Build the panel and add it to the frame.
        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    private void makePanel(){
        mainPanel = new JPanel();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JPanel cenPanel = new JPanel();
        cenPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel productLabel = new JLabel("Product: ");
        JLabel mainQuantityLabel = new JLabel("Quantity: ");

        String[] mainProductNameList = castArrayListToArray();
        mainProductComboBox = new JComboBox<>(mainProductNameList);
        
        mainProductQuantitySpinner = new JSpinner(new SpinnerNumberModel(1,1,100,1));

        addButton = new JButton("Add");
        addButton.addActionListener(new addButtonListener());

        returnButton = new JButton("Return");
        returnButton.addActionListener(new returnButtonListener());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10,10,0,0);  //top padding  
        gbc.ipady = 15;  

        cenPanel.add(productLabel, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        cenPanel.add(mainProductComboBox, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        cenPanel.add(mainQuantityLabel, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        cenPanel.add(mainProductQuantitySpinner, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        
        centerPanel.add(cenPanel, BorderLayout.CENTER);
        centerPanel.setBorder(new EmptyBorder(20,10,10,10));

        JPanel centerBottomPanel = new JPanel();
        centerBottomPanel.setBorder(new EmptyBorder(35,10,10,10));
        centerBottomPanel.setLayout(new GridLayout(1, 4, 10, 10));
        
        centerBottomPanel.add(addButton);
        centerBottomPanel.add(returnButton);
        centerPanel.add(centerBottomPanel, BorderLayout.SOUTH);

        productInventoryListPanel();
        tablePanel = new JPanel();
        tablePanel.add(productBlPanel);

        mainPanel.add(tablePanel);
        mainPanel.add(centerPanel);
    }

    private void productInventoryListPanel() {
		String data[][] = retrieveProductData();
                
		String column[] = { "Product Name", "Stock Quantity" };

        productBlPanel = new JPanel();

        DefaultTableModel tableModel = new DefaultTableModel(data, column);
        // tableModel.fireTableDataChanged();

        productBlLabel = new JLabel("Inventory List");
        productBlTable = new JTable(tableModel);

        // Center text
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER );
        productBlTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );

        scrollPane = new JScrollPane(productBlTable);
        scrollPane.setPreferredSize(new Dimension(450, 120));
        productBlTable.setRowHeight(20);
        
        productBlPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        productBlPanel.add(productBlLabel, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        productBlPanel.add(scrollPane,gbc);

    }

    private String[][] retrieveProductData() {
        String data[][] = new String [chickenRiceProductsList.size() + chickenRiceAddOnsList.size()][2];


        for (int i = 0; i<chickenRiceProductsList.size() + chickenRiceAddOnsList.size(); i++){
            if (i < chickenRiceProductsList.size()){
                data[i][0] = chickenRiceProductsList.get(i).getProductName();
                data[i][1] = "" + chickenRiceProductsList.get(i).getBalanceQuantity();
            } else {
                data[i][0] = chickenRiceAddOnsList.get(i - chickenRiceProductsList.size()).getProductName();
                data[i][1] = "" + chickenRiceAddOnsList.get(i - chickenRiceProductsList.size()).getBalanceQuantity();
            }
        }
        
        return data;
    }

    private String[] castArrayListToArray(){
        String[] productName = new String[chickenRiceProductsList.size()+chickenRiceAddOnsList.size()];

        for (int i = 0; i<productName.length; i++){
            if (i < chickenRiceProductsList.size()){
                productName[i] = chickenRiceProductsList.get(i).getProductName();
            } else {
                productName[i] = chickenRiceAddOnsList.get(i - chickenRiceProductsList.size()).getProductName();
            }
        }
        return productName;
    }

    private class addButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            int mainProductSelectedIndex = mainProductComboBox.getSelectedIndex();
            int mainProductOrderQuantity = (Integer) mainProductQuantitySpinner.getValue();
            
            if (mainProductSelectedIndex < chickenRiceProductsList.size()){
                chickenRiceProductsList.get(mainProductSelectedIndex).addProductQuantity(mainProductOrderQuantity);
            } else {
                chickenRiceAddOnsList.get(mainProductSelectedIndex - chickenRiceProductsList.size()).addProductQuantity(mainProductOrderQuantity);
            }
            // scrollPane.remove(productBlTable);
            // mainPanel.remove(tablePanel);
            mainPanel.revalidate();
            mainPanel.repaint();



            // mainPanel.add(tablePanel);
            // tablePanel.add(productBlPanel);
            // productBlPanel.add(productBlLabel, gbc);
            // productBlPanel.add(scrollPane,gbc);
            // scrollPane.add(productBlTable);

            String data[][] = retrieveProductData();
                
		    String column[] = { "Product Name", "Stock Quantity" };

            DefaultTableModel tableModel = new DefaultTableModel(data, column);
            productBlTable.setModel(tableModel);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER );
            productBlTable.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
            
            // productBlTable = new JTable(data, column);

            // scrollPane.add(productBlTable);
        }
    }

    private class returnButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int returnAnwser;

            returnAnwser = JOptionPane.showConfirmDialog(mainPanel, "Are you sure you want to return?");
            if (returnAnwser == 0){
                ChickenRiceShopProductBalanceFrame.super.dispose();
            }
        }

    }

    public ArrayList<ChickenRiceProduct> getLatestChickenRiceProduct(){
        return chickenRiceProductsList;
    }

    public ArrayList<ChickenRiceAddOn> getLatestChickenRiceAddOn(){
        return chickenRiceAddOnsList;        
    }
}
