package Graphic_User_Interface;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class ChickenRiceShopTableLabelFrame extends JFrame{
    private String title = "POS System"; 
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350; 
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton addMore_SaveBtn;
    private JButton finishBtn;
    private JTextField[] tableLabelTextFieldsLis; 

    public ChickenRiceShopTableLabelFrame(String label){
        setTitle(title + " - " + label);
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
        JLabel tabelLabel = new JLabel("Table Label : ", SwingConstants.RIGHT);

        buttonPanel = new JPanel();
        addMore_SaveBtn = new JButton("Save");
        // addMore_SaveBtn.setBorder(new EmptyBorder(10,10,10,10));
        addMore_SaveBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.gray, 1), 
            BorderFactory.createEmptyBorder(5, 5, 10, 10)));

        finishBtn = new JButton("Finish");
        finishBtn.addActionListener(new finishButtonListener());
        // finishBtn.setBorder(new EmptyBorder(10,10,10,10));
        finishBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.gray, 1), 
            BorderFactory.createEmptyBorder(5, 5, 10, 10)));

        buttonPanel.setLayout(new GridLayout(2,1, 0, 10));
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));
        buttonPanel.add(addMore_SaveBtn);
        buttonPanel.add(finishBtn);
        
        leftPanel.add(tabelLabel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        leftPanel.setPreferredSize(new Dimension(WIDTH/3, HEIGHT));

        mainPanel.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(new EmptyBorder(10,10,10,10));
        centerPanel.setLayout(new GridLayout(3,3, 15, 15));
        tableLabelTextFieldsLis = new JTextField[9]; 
        
        for (int i = 0; i<tableLabelTextFieldsLis.length; i++){
            tableLabelTextFieldsLis[i] = new JTextField(5);
            centerPanel.add(tableLabelTextFieldsLis[i]);
        }
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private class finishButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ChickenRiceShopTableLabelFrame.super.dispose();
            new ChickenRiceShopProductFrame("Chicken Rice Shop", "Main Product");
        }
    }
}
