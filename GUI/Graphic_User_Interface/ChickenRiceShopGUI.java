package Graphic_User_Interface;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ChickenRiceShopGUI extends JFrame{
    private String title = "POS System"; 
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JTextField shopNameTextField;
    private JTextField registerNumTextField;
    private JTextArea addressTextArea;
    private JTextField telTextField;
    private JButton finishButton;
    private JPanel mainPanel;

    public ChickenRiceShopGUI(){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    public void makePanel(){
        mainPanel = new JPanel();
        JPanel shopDetialPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();  
        JLabel shopNameLabel = new JLabel("Shop Name*: ");
        JLabel registerNumberLabel = new JLabel("Register Number*: ");
        JLabel addressLabel = new JLabel("Address*: ");
        JLabel telphoneLabel = new JLabel("Telphone*: ");
         
        shopNameTextField = new JTextField(30);
        registerNumTextField = new JTextField(15);

        addressTextArea = new JTextArea(5,15);
        // By default, text area without border line, so i manual add it
        addressTextArea.setBorder(new LineBorder(Color.black, 1, true));

        telTextField = new JTextField(15);
        finishButton = new JButton("Finish");
        finishButton.addActionListener(new FinishButtonListener());
        
        shopDetialPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,0,0,0);  //top padding  
        gbc.ipady = 15;  
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        shopDetialPanel.add(shopNameLabel, gbc);
        gbc.gridx = 1;  
        gbc.gridy = 0; 
        shopDetialPanel.add(shopNameTextField, gbc);
        gbc.gridx = 0;  
        gbc.gridy = 1;  
        shopDetialPanel.add(registerNumberLabel, gbc);
        gbc.gridx = 1;  
        gbc.gridy = 1; 
        shopDetialPanel.add(registerNumTextField, gbc);
        gbc.gridx = 0;  
        gbc.gridy = 2;  
        shopDetialPanel.add(addressLabel, gbc);
        gbc.gridx = 1;  
        gbc.gridy = 2; 
        shopDetialPanel.add(addressTextArea, gbc);
        gbc.gridx = 0;  
        gbc.gridy = 3;  
        shopDetialPanel.add(telphoneLabel, gbc);
        gbc.gridx = 1;  
        gbc.gridy = 3; 
        shopDetialPanel.add(telTextField, gbc);
        gbc.gridx = 0;  
        gbc.gridy = 4;
        gbc.gridwidth = 2;  
        shopDetialPanel.add(finishButton, gbc);
        
        mainPanel.add(shopDetialPanel);
    }

    public class FinishButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //check whether all textfield is entered
            if (checkAllTextField()){

                //get all shop's information
                ChickenRiceShop chickenRiceShopData = retrieveAllShopData();

                //becuase we didn't have database, so all of the data will need to pass when invoke next jframe
                new ChickenRiceShopTableLabelFrame(chickenRiceShopData.getShopName(), chickenRiceShopData);
                ChickenRiceShopGUI.super.dispose();
                
            }else{
                JOptionPane.showMessageDialog(mainPanel, "You have to fill in all shop's information that we required..", "Error", JOptionPane.ERROR_MESSAGE);
            }  
        }
    }

    private ChickenRiceShop retrieveAllShopData(){

        ChickenRiceShop tempChickenRiceShopData = new ChickenRiceShop();

        tempChickenRiceShopData.setShopName(shopNameTextField.getText());
        tempChickenRiceShopData.setShopRegisterNumber(registerNumTextField.getText());
        tempChickenRiceShopData.setLocation(addressTextArea.getText());
        tempChickenRiceShopData.setTelephone(telTextField.getText());

        return tempChickenRiceShopData;
    }

    private boolean checkAllTextField(){
        if (shopNameTextField.getText().equals("")){
            return false;
        }

        if (registerNumTextField.getText().equals("")){
            return false;
        }

        if (addressTextArea.getText().equals("")){
            return false;
        }

        if (telTextField.getText().equals("")){
            return false;
        }

        return true;
    }
}
