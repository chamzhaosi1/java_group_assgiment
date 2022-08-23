package Graphic_User_Interface;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import java.awt.*;

public class ChickenRiceShopTableLabel extends JFrame{
    private String title = "POS System"; 
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350; 
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton addMore_SaveBtn;
    private JButton finishBtn;
    private JTextField[] tableLabelTextFieldsLis; 

    public ChickenRiceShopTableLabel(String label){
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
        JPanel leftPanel = new JPanel();
        JLabel tabelLabel = new JLabel("Table Label");

        buttonPanel = new JPanel();
        addMore_SaveBtn = new JButton("Add More & Save");
        finishBtn = new JButton("Finish");

        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(addMore_SaveBtn);
        buttonPanel.add(finishBtn);
        
        leftPanel.add(tabelLabel, BorderLayout.NORTH);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        tableLabelTextFieldsLis = new JTextField[9]; 
        
        for (int )
    }
}
