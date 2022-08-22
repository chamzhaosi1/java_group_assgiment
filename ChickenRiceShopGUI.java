import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class ChickenRiceShopGUI extends JFrame{
    private String title = "POS System"; 
    private static final int WIDTH = 500;
    private static final int HEIGHT = 200;
    private JTextField shopNameTextField;
    private JTextField registerNumTextField;
    private JTextArea addressTextArea;
    private JTextField telTextField;
    private JButton finishButton;

    public ChickenRiceShopGUI(){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        makePanel();

        setVisible(true);
    }

    public void makePanel(){
        JPanel shopDetialPanel = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();  
        JLabel shopNameLabel;
        JLabel registerNumberLabel;
        JLabel addressLabel;
        JLabel telphoneLabel;
         
        shopNameTextField = new JTextField();
        registerNumTextField = new JTextField();
        addressTextArea = new JTextArea();
        telTextField = new JTextField();
        finishButton = new JButton("Finish");
        
        shopDetialPanel.setLayout(new GridBagLayout());

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;  
        gbc.gridy = 0;  
        

    }
}
