package myApp.com.gui;

import myApp.com.models.NewAccount;
import myApp.com.sql.NewAccountSQL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccount1GUI extends JFrame implements ActionListener {
    ImageIcon background = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\backgroundLogin.png");
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,15);
    JLabel backgroundLabel, titleLabel;
    Color colorBtn = new Color(0x6495ED);
    JPasswordField passTxtField, verifyPassTxtField;
    JLabel userLabel, passLabel, verifyPassLabel, errLabel;
    JTextField userNameTxtField;
    JButton finishBtn;
    NewAccount accountInfo;

    NewAccount1GUI(NewAccount n){
        labels();
        txtFields();
        buttons();
        accountInfo = n;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Create account");
        this.add(backgroundLabel);
        this.add(titleLabel);
        this.add(userLabel);
        this.add(passLabel);
        this.add(verifyPassLabel);
        this.add(userNameTxtField);
        this.add(finishBtn);
        this.add(passTxtField);
        this.add(verifyPassTxtField);
        this.add(errLabel);
    }

    public void labels() {
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(Color.WHITE);
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(0, 0, 350, 600);
        backgroundLabel.setVisible(true);

        titleLabel = new JLabel("Create new account");
        titleLabel.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBounds(400, 50, 300, 40);
        titleLabel.setVisible(true);

        userLabel = new JLabel("Username");
        userLabel.setBounds(370,190, 200,20);
        userLabel.setFont(fontLabels);
        userLabel.setForeground(Color.BLUE);
        userLabel.setVisible(true);

        passLabel = new JLabel("Password");
        passLabel.setBounds(370,250, 200,20);
        passLabel.setFont(fontLabels);
        passLabel.setForeground(Color.BLUE);
        passLabel.setVisible(true);

        verifyPassLabel = new JLabel("Confirm Password");
        verifyPassLabel.setBounds(370,310, 200,20);
        verifyPassLabel.setFont(fontLabels);
        verifyPassLabel.setForeground(Color.BLUE);
        verifyPassLabel.setVisible(true);

        errLabel = new JLabel("Different password");
        errLabel.setBounds(370,370, 200,20);
        errLabel.setFont(fontLabels);
        errLabel.setForeground(Color.BLUE);
        errLabel.setVisible(false);
    }

    public void txtFields(){
        userNameTxtField = new JTextField();
        userNameTxtField.setBounds(370,210,300,40);

        passTxtField = new JPasswordField(30);
        passTxtField.setBounds(370,270,300,40);
        passTxtField.setEchoChar('*');

        verifyPassTxtField = new JPasswordField(30);
        verifyPassTxtField.setBounds(370,330,300,40);
        verifyPassTxtField.setEchoChar('*');
    }

    public void buttons(){
        finishBtn = new JButton("Finish");
        finishBtn.setBounds(470,450,100,30);
        finishBtn.setBackground(colorBtn);
        finishBtn.setForeground(Color.BLACK);
        finishBtn.setFont(fontLabels);
        finishBtn.setFocusable(false);
        finishBtn.addActionListener(this);
        finishBtn.setVisible(true);
    }

    public NewAccount getTxtFromJTxtFields(){
        NewAccount n = new NewAccount(userNameTxtField.getText(),String.valueOf(passTxtField.getPassword()));
        return n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==finishBtn){
             if(String.valueOf(passTxtField.getPassword()).equals(String.valueOf(verifyPassTxtField.getPassword()))){
                 try {
                     new NewAccountSQL().addNewAccount(accountInfo,getTxtFromJTxtFields());
                     this.dispose();
                     MessageGUI m = new MessageGUI();
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
             }
             else
                 errLabel.setVisible(true);
         }
    }
}
