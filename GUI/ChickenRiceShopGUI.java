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
        JLabel shopNameLabel = new JLabel("Shop Name: ");
        JLabel registerNumberLabel = new JLabel("Register Number: ");
        JLabel addressLabel = new JLabel("Address: ");
        JLabel telphoneLabel = new JLabel("Telphone: ");
         
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
            ChickenRiceShopGUI.super.dispose();
            new ChickenRiceShopTableLabel("Chicken Rice Shop");
        }
    }
}
