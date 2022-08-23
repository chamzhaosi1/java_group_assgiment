package Graphic_User_Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChickenRiceShopOptionFrame extends JFrame{
    private String title = "POS System";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel = new JPanel();
    
    public ChickenRiceShopOptionFrame(String label){
        setTitle(title + " - " + label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    public void makePanel(){
        JLabel optionMenuLabel = new JLabel("Option Menu");
        
    }
}
