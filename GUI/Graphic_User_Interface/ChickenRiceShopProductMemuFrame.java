package Graphic_User_Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChickenRiceShopProductMemuFrame extends JFrame {
    private String title = "POS System";
    private static final int WIDTH = 500;
    private static final int HEIGHT = 350;
    private JPanel mainPanel = new JPanel();

    public ChickenRiceShopProductMemuFrame(String label){
        setTitle(title + " - " + label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        makePanel();
        add(mainPanel);

        setVisible(true);
    }

    public void makePanel(){
        JButton returnBtn = new JButton("Return");
        returnBtn.addActionListener(new ReturnBtnListener());

        mainPanel = new JPanel();

        mainPanel.add(returnBtn);
    }

    public class ReturnBtnListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ChickenRiceShopProductMemuFrame.super.dispose();
        }

    }
}
