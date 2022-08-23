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
        JLabel testLabel = new JLabel("testing");
        JPanel testPanel = new JPanel();
        mainPanel = new JPanel();
        testPanel.add(testLabel);
        mainPanel.add(testPanel);
    }
}
