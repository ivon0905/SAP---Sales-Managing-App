package myApp.com.gui;

import myApp.com.common.DBConnection;
import myApp.com.sql.LoginSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginGUI extends JFrame implements ActionListener {
    ImageIcon background = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\backgroundLogin.png");
    ImageIcon userImage = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\user.png");
    ImageIcon passImage = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\pass.png");
    Font fontButtons = new Font("Times New Roman",Font.ITALIC,15);
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,30);
    JLabel backgroundLabel;
    JPasswordField passTxtField;
    JLabel loginLabel, userLabel, passLabel, errLabel;
    JTextField userTxtField;
    JButton logBtn, newAccountBtn;
    JCheckBox viewPass;
    Color colorBtn = new Color(0x6495ED);

    public LoginGUI(){
        DBConnection.getInstance();
        buttons();
        labels();
        txtFields();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Login");
        this.add(passLabel);
        this.add(passTxtField);
        this.add(backgroundLabel);
        this.add(loginLabel);
        this.add(userTxtField);
        this.add(logBtn);
        this.add(viewPass);
        this.add(newAccountBtn);
        this.add(userLabel);
        this.add(errLabel);
        this.setLocationRelativeTo(null);
    }

    public void buttons(){
        logBtn = new JButton("Login");
        logBtn.setBounds(470,400,100,30);
        logBtn.setBackground(colorBtn);
        logBtn.setForeground(Color.BLACK);
        logBtn.setFont(fontButtons);
        logBtn.setFocusable(false);
        logBtn.addActionListener(this);
        logBtn.setVisible(true);

        newAccountBtn = new JButton("Create new account");
        newAccountBtn.setBounds(420, 450, 200, 30);
        newAccountBtn.setForeground(Color.BLUE);
        newAccountBtn.setFont(fontButtons);
        newAccountBtn.setBorderPainted(false);
        newAccountBtn.setBorder(null);
        newAccountBtn.setVisible(true);
        newAccountBtn.addActionListener(this);

        viewPass = new JCheckBox("View password");
        viewPass.setBounds(390,350,300,15);
        viewPass.setFont(new Font("Times New Roman",Font.ITALIC,15));
        viewPass.setVisible(true);
        viewPass.addActionListener(this);
    }

    public void labels(){
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(Color.WHITE);
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(0,0,350,600);
        backgroundLabel.setVisible(true);

        userLabel = new JLabel();
        userLabel.setIcon(userImage);
        userLabel.setBounds(350,250,40,35);
        userLabel.setVisible(true);

        passLabel = new JLabel();
        passLabel.setIcon(passImage);
        passLabel.setBounds(350, 300,41,41);
        passLabel.setVisible(true);

        loginLabel = new JLabel("Log In");
        loginLabel.setBounds(480, 150,100,40);
        loginLabel.setForeground(Color.BLUE);
        loginLabel.setFont(fontLabels);
        loginLabel.setVisible(true);

        errLabel = new JLabel("Wrong password or username");
        errLabel.setBounds(390,200,200,30);
        errLabel.setForeground(Color.BLUE);
        errLabel.setFont(fontButtons);
        errLabel.setVisible(false);
    }

    public void txtFields(){
        passTxtField = new JPasswordField(30);
        passTxtField.setBounds(390,300,250,40);
        passTxtField.setEchoChar('*');
        passTxtField.setVisible(true);

        userTxtField = new JTextField(20);
        userTxtField.setBounds(390,250,250, 40);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==logBtn){
            String[] data = new String[0];
            try {
                data = new LoginSQL().checkData(userTxtField.getText(), String.valueOf(passTxtField.getPassword()));
                if(data[0].equals("a")){
                    errLabel.setVisible(true);
                }
                if(data[0].equalsIgnoreCase("admin")) {
                    this.dispose();
                    this.setVisible(false);
                    AdminGUI admin = new AdminGUI();
                }if(data[0].equalsIgnoreCase("manager")) {
                    this.dispose();
                    this.setVisible(false);
                    try {
                        UserGUI user = new UserGUI(data[1]);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==viewPass){
            passTxtField.setEchoChar('\u0000');
        }
        if(e.getSource()==newAccountBtn){
            new NewAccountGUI();
        }
    }
}
