package myApp.com.gui;

import myApp.com.exceptions.EmailException;
import myApp.com.exceptions.PhoneNumberException;
import myApp.com.exceptions.ValidateEmail;
import myApp.com.exceptions.ValidatePhoneNumber;
import myApp.com.models.NewAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewAccountGUI extends JFrame implements ActionListener {
    ImageIcon background = new ImageIcon("D:\\Projects\\ManagingSalesApplication\\src\\myApp\\com\\images\\backgroundLogin.png");
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,15);
    JLabel backgroundLabel, titleLabel;
    Color colorBtn = new Color(0x6495ED);
    JTextField nameTxtField, lastNTxtField, emailTxtField, phoneTxtField, brandTxtField;
    JLabel nameLabel, lastNLabel, emailLabel, phoneLabel, brandLabel;
    JButton nextBtn;

    NewAccountGUI(){
        labels();
        txtFields();
        buttons();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Create account");
        this.add(backgroundLabel);
        this.add(titleLabel);
        this.add(nameTxtField);
        this.add(lastNTxtField);
        this.add(emailTxtField);
        this.add(phoneTxtField);
        this.add(brandTxtField);
        this.add(lastNLabel);
        this.add(nameLabel);
        this.add(emailLabel);
        this.add(phoneLabel);
        this.add(brandLabel);
        this.add(nextBtn);
        this.setLocationRelativeTo(null);
    }
    public void labels() {
        backgroundLabel = new JLabel();
        backgroundLabel.setBackground(Color.WHITE);
        backgroundLabel.setIcon(background);
        backgroundLabel.setBounds(0, 0, 350, 600);
        backgroundLabel.setVisible(true);

        titleLabel = new JLabel("Create new account");
        titleLabel.setFont(new Font("Times New Roman",Font.ITALIC,30));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBounds(400, 50,300,40);
        titleLabel.setVisible(true);

        nameLabel = new JLabel("First name");
        nameLabel.setBounds(370, 130, 200,20);
        nameLabel.setForeground(Color.BLUE);
        nameLabel.setFont(fontLabels);
        nameLabel.setVisible(true);

        lastNLabel = new JLabel("Last Name");
        lastNLabel.setBounds(370,190, 200,20);
        lastNLabel.setFont(fontLabels);
        lastNLabel.setForeground(Color.BLUE);
        lastNLabel.setVisible(true);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(370,250, 200,20);
        emailLabel.setFont(fontLabels);
        emailLabel.setForeground(Color.BLUE);
        emailLabel.setVisible(true);

        phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(370,310, 200,20);
        phoneLabel.setFont(fontLabels);
        phoneLabel.setForeground(Color.BLUE);
        phoneLabel.setVisible(true);

        brandLabel = new JLabel("Brand");
        brandLabel.setBounds(370,370, 200,20);
        brandLabel.setFont(fontLabels);
        brandLabel.setForeground(Color.BLUE);
        brandLabel.setVisible(true);
    }
    public void txtFields(){
        nameTxtField = new JTextField();
        nameTxtField.setBounds(370,150, 300,40);

        lastNTxtField = new JTextField();
        lastNTxtField.setBounds(370,210,300,40);

        emailTxtField = new JTextField();
        emailTxtField.setBounds(370,270,300,40);

        phoneTxtField = new JTextField();
        phoneTxtField.setBounds(370,330,300,40);

        brandTxtField = new JTextField();
        brandTxtField.setBounds(370,390,300,40);
    }
    public void buttons(){
        nextBtn = new JButton("Next");
        nextBtn.setBounds(470,450,100,30);
        nextBtn.setBackground(colorBtn);
        nextBtn.setForeground(Color.BLACK);
        nextBtn.setFont(fontLabels);
        nextBtn.setFocusable(false);
        nextBtn.addActionListener(this);
        nextBtn.setVisible(true);
    }

    public NewAccount getTxtFromJTextFields(){
        NewAccount n = new NewAccount(nameTxtField.getText(), lastNTxtField.getText(), emailTxtField.getText(),
                phoneTxtField.getText(), brandTxtField.getText());
        return n;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==nextBtn){
            try {
                NewAccount a = getTxtFromJTextFields();
                if (ValidateEmail.checkEmail(a.getEmail()) && ValidatePhoneNumber.checkPhoneNum(a.getPhone())) {
                    NewAccount1GUI n = new NewAccount1GUI(a);
                    this.dispose();
                    this.setVisible(false);
                }
            } catch (EmailException | PhoneNumberException ex) {
                ex.getMessage();
            }
        }
    }
}
