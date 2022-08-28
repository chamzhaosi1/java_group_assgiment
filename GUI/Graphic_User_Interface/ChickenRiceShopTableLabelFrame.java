package Graphic_User_Interface;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChickenRiceShopTableLabelFrame extends JFrame{
    private ChickenRiceShop chickenRiceShop = new ChickenRiceShop();
    private String label;
    private String[] tabelLabel;

    private String title = "POS System - Table Label"; 
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350; 
    private JPanel mainPanel;
    private JButton finishBtn;
    private JTextField[] tableLabelTextFieldsList; 

    public ChickenRiceShopTableLabelFrame(String label, ChickenRiceShop chickenRiceShop){
        this.label = label;
        this.chickenRiceShop = chickenRiceShop;

        setTitle(title + " - " + this.label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    public void makePanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.setBorder(new EmptyBorder(10,10,10,10));
        JLabel tabelLabel = new JLabel("Table Label : ", SwingConstants.RIGHT);
        tabelLabel.setBorder(new EmptyBorder(20,0,0,10));

        finishBtn = new JButton("Finish");
        finishBtn.addActionListener(new finishButtonListener()); 
        finishBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.gray, 1), 
            BorderFactory.createEmptyBorder(5, 5, 10, 10)));
        
        leftPanel.add(tabelLabel, BorderLayout.NORTH);
        leftPanel.add(finishBtn, BorderLayout.SOUTH);
        leftPanel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));

        mainPanel.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(10,10,10,10));
        centerPanel.setLayout(new GridLayout(3,3, 15, 15));
        tableLabelTextFieldsList = new JTextField[9]; 
        
        for (int i = 0; i<tableLabelTextFieldsList.length; i++){
            tableLabelTextFieldsList[i] = new JTextField(5);
            centerPanel.add(tableLabelTextFieldsList[i]);
        }
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private class finishButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //check whether had fill in the table label, at least one.
            if (checkAllTabelLabelTextFields()){

                // retrieve all table label data
                retrieveAllTableLabel();
                // for (String s : tabelLabel){
                //     System.out.println(s);
                // }

                // assign to chickenRiceShopProduct, becuase it need to pass for next Jframe
                chickenRiceShop.setTableLabel(tabelLabel);

                new ChickenRiceShopProductFrame(label, "Main Product", chickenRiceShop, null, null);
                ChickenRiceShopTableLabelFrame.super.dispose();
                
            }else{
                JOptionPane.showMessageDialog(mainPanel, "You have to fill at least one of the table label!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void retrieveAllTableLabel(){

        
        ArrayList<String> tableLablelList = new ArrayList<>();
        // int index = 0;

        for (JTextField tempTextField: tableLabelTextFieldsList){
            if (tempTextField.getText().equals("")){
                continue;
            }else{
                tableLablelList.add(tempTextField.getText());
                // tabelLabel[index] = tempTextField.getText();
                // index++;
            }
        }

        tabelLabel = new String[tableLablelList.size()];
        for(int i=0; i< tableLablelList.size(); i++){
            tabelLabel[i] = tableLablelList.get(i);
        }

    }

    private boolean checkAllTabelLabelTextFields(){

        for (JTextField tempTextField: tableLabelTextFieldsList){
            if (tempTextField.getText().equals("")){
                continue;
            }else{
                // at least one of the table label text field has fill in, then can return true
                return true;
            }
        }
        return false;
    }
}
