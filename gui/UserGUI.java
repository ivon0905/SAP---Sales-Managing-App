package myApp.com.gui;

import myApp.com.common.DBConnection;
import myApp.com.sql.AccountSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class UserGUI extends JFrame implements ActionListener {
    JButton clientsButton;
    JButton salesCatalogueButton;
    JButton myAccountButton;
    ImageIcon image = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\background.jpg");
    JLabel imageLabel;
    JLabel title;
    JLabel nameLabel;
    String ID = "";

    UserGUI(String id) throws SQLException {
        ID = id;
        String[] accountData = new AccountSQL().getAccountData(id);
        buttons();
        labels(accountData);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("SMA");
        this.setSize(700,600);
        this.setBackground(new Color(0XEFE4EA));
        this.setResizable(false);
        this.setLayout(null);
        this.add(title);
        this.add(imageLabel);
        this.add(clientsButton);
        this.add(salesCatalogueButton);
        this.add(myAccountButton);
        this.add(nameLabel);
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

    public void labels(String[] accountData){
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

        nameLabel = new JLabel(accountData[0]+" "+accountData[1]+"   "+accountData[2]+"   "+accountData[3]+"   "+accountData[4]);
        nameLabel.setBounds(10,530,600,20);
        nameLabel.setFont(new Font("Times New Roman",Font.ITALIC,15));
        nameLabel.setForeground(Color.DARK_GRAY);
        nameLabel.setVisible(true);
    }

    public void buttons(){
        clientsButton = new JButton();
        clientsButton.setText("List of clients");
        clientsButton.setFont(new Font("Times New Roman",Font.ITALIC,20));
        clientsButton.setBounds(0,400,230,50);
        clientsButton.setForeground(Color.DARK_GRAY);
        clientsButton.setBackground(new Color(0XC8B0BD));
        clientsButton.setFocusable(false);
        clientsButton.addActionListener(this);

        salesCatalogueButton = new JButton();
        salesCatalogueButton.setText("My Sales");
        salesCatalogueButton.setFont(new Font("Times New Roman",Font.ITALIC,20));
        salesCatalogueButton.setBounds(230,400,240,50);
        salesCatalogueButton.setForeground(Color.DARK_GRAY);
        salesCatalogueButton.setBackground(new Color(0XC8B0BD));
        salesCatalogueButton.setFocusable(false);
        salesCatalogueButton.addActionListener(this);

        myAccountButton = new JButton();
        myAccountButton.setText("Analysis");
        myAccountButton.setFont(new Font("Times New Roman",Font.ITALIC,20));
        myAccountButton.setBounds(470,400,230,50);
        myAccountButton.setForeground(Color.DARK_GRAY);
        myAccountButton.setBackground(new Color(0XC8B0BD));
        myAccountButton.setFocusable(false);
        myAccountButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==clientsButton){
             try {
                 ClientsGUI client = new ClientsGUI();
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         }
         if(e.getSource()==salesCatalogueButton){
             try {
                 SalesCatalogueGUI sales = new SalesCatalogueGUI(ID);
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         }
         if(e.getSource()==myAccountButton){
             try {
                 UserAnalysisGUI analysis = new UserAnalysisGUI(ID);
             } catch (SQLException ex) {
                 ex.printStackTrace();
             }
         }
    }
}
