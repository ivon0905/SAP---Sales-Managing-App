package myApp.com.gui;

import myApp.com.common.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AdminGUI extends JFrame implements ActionListener {
    JButton productsButton;
    JButton managersButton;
    JButton salesButton;
    ImageIcon image = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\background.jpg");
    JLabel imageLabel;
    JLabel title;

    AdminGUI(){
        buttons();

        imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setBounds(0,-150,700, 600);
        imageLabel.setVisible(true);

        title = new JLabel("Sales Managing App");
        title.setFont(new Font("Times New Roman",Font.ITALIC,40));
        title.setOpaque(true);
        title.setForeground(Color.DARK_GRAY);
        title.setBounds(10,300, 500,50);
        title.setVisible(true);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("SMA - admin");
        this.setSize(700,600);
        this.setBackground(new Color(0XEFE4EA));
        this.setResizable(false);
        this.setLayout(null);
        this.add(title);
        this.add(imageLabel);
        this.add(productsButton);
        this.add(managersButton);
        this.add(salesButton);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    DBConnection.closeConnection();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void buttons(){
        productsButton = new JButton();
        productsButton.setText("Products Catalogue");
        productsButton.setFont(new Font("Times New Roman",Font.ITALIC,20));
        productsButton.setBounds(0,400,230,50);
        productsButton.setForeground(Color.DARK_GRAY);
        productsButton.setBackground(new Color(0XC8B0BD));
        productsButton.setFocusable(false);
        productsButton.addActionListener(this);

        managersButton = new JButton();
        managersButton.setText("Sales Managers");
        managersButton.setFont(new Font("Times New Roman",Font.ITALIC,20));
        managersButton.setBounds(230,400,240,50);
        managersButton.setForeground(Color.DARK_GRAY);
        managersButton.setBackground(new Color(0XC8B0BD));
        managersButton.setFocusable(false);
        managersButton.addActionListener(this);

        salesButton = new JButton();
        salesButton.setText("Sales Analysis");
        salesButton.setFont(new Font("Times New Roman",Font.ITALIC,20));
        salesButton.setBounds(470,400,230,50);
        salesButton.setForeground(Color.DARK_GRAY);
        salesButton.setBackground(new Color(0XC8B0BD));
        salesButton.setFocusable(false);
        salesButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==productsButton){
            try {
                ProductsGUI productsGUI = new ProductsGUI();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==managersButton){
            try {
                ManagersGUI managersGUI = new ManagersGUI();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==salesButton){
            try {
                SalesAnalysisGUI salesGUI = new SalesAnalysisGUI();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
