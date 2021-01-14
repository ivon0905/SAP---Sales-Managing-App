package myApp.com.gui;

import javax.swing.*;
import java.awt.*;

public class MessageGUI extends JFrame {
    MessageGUI() {
        JLabel approvalLabel = new JLabel("Your data was saved successfully!");
        approvalLabel.setBounds(80,25,400,40);
        approvalLabel.setFont(new Font("Times New Roman",Font.ITALIC,25));
        approvalLabel.setForeground(Color.BLACK);
        approvalLabel.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500,150);
        this.setResizable(false);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.add(approvalLabel);
        this.setTitle("Message");
    }
}
