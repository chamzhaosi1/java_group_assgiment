package Graphic_User_Interface;

import javax.swing.table.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopDailySalesFrame extends JFrame{
    private String label;
    private ChickenRiceShop chickenRiceShop;
    private ArrayList<ChickenRiceProduct> chickenRiceProductsList;
    private ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList;
    private ArrayList<ChickenRiceOrder> chickenRiceShopSoldList = new ArrayList<>();

    private String title = "POS System";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel = new JPanel();
    private JTable salesSummaryTable;
    private JButton generateButton;
    private JButton returnButton;

    public ChickenRiceShopDailySalesFrame(String label, ChickenRiceShop chickenRiceShop, ArrayList<ChickenRiceOrder> chickenRiceShopSoldList, ArrayList<ChickenRiceProduct> chickenRiceProductsList, ArrayList<ChickenRiceAddOn> chickenRiceAddOnsList) {
        this.chickenRiceShop = chickenRiceShop;
        this.chickenRiceShopSoldList = chickenRiceShopSoldList;
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
        mainPanel.setBorder(new EmptyBorder(10,10,10,10));
        mainPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        buttonPanel.setLayout(new GridLayout(1, 2, 10,10));

    
        String columnHeader[] = {"Product Name","Quantity","Total Price (RM)"};
        String data [][] = retrieveSoldOrderData();
        salesSummaryTable = new JTable(data, columnHeader);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        salesSummaryTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        salesSummaryTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        JScrollPane salesSummaryScrollPane = new JScrollPane(salesSummaryTable);   
        salesSummaryScrollPane.setPreferredSize(new Dimension(WIDTH, 200)); 
        
        returnButton = new JButton("Return");
        returnButton.addActionListener(new returnBtnListener());

        buttonPanel.add(returnButton);
        
        mainPanel.add(salesSummaryScrollPane, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private String[][] retrieveSoldOrderData(){

        int[] soldMainProductQuantity = countMainProductDailySales(chickenRiceShopSoldList);
        int[] soldAddOnProductQuantity = countAddOnProductDailySales(chickenRiceShopSoldList);
        double totalAmount=0;

        int lenghArray = chickenRiceProductsList.size() + chickenRiceProductsList.size();
        // one more for total amount
        String data [][] = new String[lenghArray+1][3];

        for (int i=0; i< lenghArray; i++){
            if (i < chickenRiceProductsList.size()){
                data[i][0] = chickenRiceProductsList.get(i).getProductName();
                data[i][1] = ""+soldMainProductQuantity[i];
                data[i][2] = String.format("%.2f", (Double.parseDouble(""+chickenRiceProductsList.get(i).getProductPrice() *soldMainProductQuantity[i])));
                totalAmount += chickenRiceProductsList.get(i).getProductPrice() *soldMainProductQuantity[i];
            }else {
                data[i][0] = chickenRiceAddOnsList.get(i-chickenRiceProductsList.size()).getProductName();
                data[i][1] = ""+soldAddOnProductQuantity[i-chickenRiceProductsList.size()];
                data[i][2] = String.format("%.2f", (Double.parseDouble(""+chickenRiceAddOnsList.get(i-chickenRiceProductsList.size()).getProductPrice() *soldAddOnProductQuantity[i-3])));
                totalAmount += chickenRiceAddOnsList.get(i-chickenRiceProductsList.size()).getProductPrice() *soldAddOnProductQuantity[i-3];
            }
        }
        
        data[lenghArray][1]= "Total Amount";
        data[lenghArray][2]= String.format("%.2f", totalAmount);
        return data;
    }

    private int[] countMainProductDailySales(ArrayList<ChickenRiceOrder> chickenRiceSoldList) {
        // to keep the quantity
        int[] soldMainProductQuantity = new int[chickenRiceProductsList.size()];

        // initial all element equal to 0 (Actually is to avoid the sequace of the item)
        for (int l = 0; l < soldMainProductQuantity.length; l++) {
            soldMainProductQuantity[l] = 0;
        }

        // check if the name same, than record it sold quantity
        // System.out.println(chickenRiceSoldList.size());
        for (int i = 0; i < chickenRiceSoldList.size(); i++) {
            for (int j = 0; j < chickenRiceSoldList.get(i).getChickenRiceProduct().length; j++) {
                for (int k = 0; k < chickenRiceProductsList.size(); k++) {
                    if (chickenRiceProductsList.get(k).getProductName()
                            .equals(chickenRiceSoldList.get(i).getChickenRiceProduct()[j].getProductName())) {
                        soldMainProductQuantity[k] += chickenRiceSoldList.get(i).getChickenRiceOrderQuantity()[j];
                        break;
                    }
                }
            }
        }

        return soldMainProductQuantity;
    }

    private int[] countAddOnProductDailySales(ArrayList<ChickenRiceOrder> chickenRiceSoldList) {
        // to keep the quantity
        int[] soldAddOnProductQuantity = new int[chickenRiceAddOnsList.size()];

        // initial all element equal to 0 (Actually is to avoid the sequace of the item)
        for (int l = 0; l < soldAddOnProductQuantity.length; l++) {
            soldAddOnProductQuantity[l] = 0;
        }

        // check if the name same, than record it sold quantity
        for (int i = 0; i < chickenRiceSoldList.size(); i++) {
            if (chickenRiceSoldList.get(i).getChickenRiceAddOn() != null) {
                for (int j = 0; j < chickenRiceSoldList.get(i).getChickenRiceAddOn().length; j++) {
                    for (int k = 0; k < chickenRiceAddOnsList.size(); k++) {
                        if (chickenRiceAddOnsList.get(k).getProductName()
                                .equals(chickenRiceSoldList.get(i).getChickenRiceAddOn()[j].getProductName())) {
                            soldAddOnProductQuantity[k] += chickenRiceSoldList.get(i).getChickenAddOnOrderQuantity()[j];
                            break;
                        }
                    }
                }
            }
        }

        return soldAddOnProductQuantity;
    }

    public class returnBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ChickenRiceShopDailySalesFrame.super.dispose();
            
        }

    }
    
}   
