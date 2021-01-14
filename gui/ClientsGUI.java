package myApp.com.gui;

import myApp.com.models.Client;
import myApp.com.sql.ClientsSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ClientsGUI extends JFrame implements ActionListener {

    JTable table;
    JScrollPane pane;
    JButton addButton;
    JButton editButton;
    JButton deleteButton;
    JButton saveButton;
    JLabel nameLabel, lastNameLabel, emailLabel, phoneLabel;
    JTextField nameTextField, lastNameTextField, phoneTextField, emailTextField;
    Object[] cols = {"First Name", "Last Name","Email", "Phone Number"};
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,15);
    Font fontButtons = new Font("Times New Roman",Font.ITALIC,20);
    Color colorButton = new Color(0XC8B0BD);

    ClientsGUI() throws SQLException {
        tableAndJScrollPane();
        buttons();
        labels();
        textFields();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Clients");
        this.add(pane);
        this.add(addButton);
        this.add(editButton);
        this.add(deleteButton);
        this.add(saveButton);
        this.add(nameLabel);
        this.add(lastNameLabel);
        this.add(emailLabel);
        this.add(phoneLabel);
        this.add(nameTextField);
        this.add(lastNameTextField);
        this.add(emailTextField);
        this.add(phoneTextField);
        this.setLocationRelativeTo(null);
    }

    public void tableAndJScrollPane() throws SQLException{
        Object[][] lines = new ClientsSQL().getClients();
        DefaultTableModel model = new DefaultTableModel(lines,cols);
        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.DARK_GRAY);
        table.setRowHeight(30);
        table.setFont(new Font("Times New Roman",Font.ITALIC,15));
        table.setVisible(true);

        pane = new JScrollPane(table);
        pane.setViewportView(table);
        pane.setBounds(10,10,660,250);
    }

    public void buttons(){
        addButton = new JButton();
        addButton.setText("Add");
        addButton.setBounds(240,460,200,40);
        addButton.setFocusable(false);
        addButton.setFont(fontButtons);
        addButton.setBackground(colorButton);
        addButton.setForeground(Color.DARK_GRAY);
        addButton.addActionListener(this);

        editButton = new JButton();
        editButton.setText("Edit");
        editButton.setBounds(30,460,200,40);
        editButton.setFocusable(false);
        editButton.setFont(fontButtons);
        editButton.setBackground(colorButton);
        editButton.setForeground(Color.DARK_GRAY);
        editButton.addActionListener(this);

        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setBounds(450,460,200,40);
        deleteButton.setFocusable(false);
        deleteButton.setFont(fontButtons);
        deleteButton.setBackground(colorButton);
        deleteButton.setForeground(Color.DARK_GRAY);
        deleteButton.addActionListener(this);

        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setBounds(290,430,100,20);
        saveButton.setFocusable(false);
        saveButton.setFont(fontButtons);
        saveButton.setBackground(colorButton);
        saveButton.setForeground(Color.DARK_GRAY);
        saveButton.setVisible(false);
        saveButton.addActionListener(this);
    }

    public void labels(){
        nameLabel = new JLabel();
        nameLabel.setText("First Name");
        nameLabel.setBounds(145,290,320,35);
        nameLabel.setFont(fontLabels);
        nameLabel.setVisible(true);

        lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        lastNameLabel.setBounds(470,290,320,35);
        lastNameLabel.setFont(fontLabels);
        lastNameLabel.setVisible(true);

        emailLabel = new JLabel();
        emailLabel.setText("Email");
        emailLabel.setBounds(165,355,320,35);
        emailLabel.setFont(fontLabels);
        emailLabel.setVisible(true);

        phoneLabel = new JLabel();
        phoneLabel.setText("Phone Number");
        phoneLabel.setBounds(470,355,320,35);
        phoneLabel.setFont(fontLabels);
        phoneLabel.setVisible(true);

    }

    public void textFields(){
        nameTextField = new JTextField();
        nameTextField.setBounds(20,320,320,35);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(360,320,320,35);

        emailTextField = new JTextField();
        emailTextField.setBounds(20,385,320,35);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(360,385,320,35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addButton){
            try {
                new ClientsSQL().addClient(readClientsFromTxtFields());
                addRowToJTable();
                cleanTextFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==deleteButton){
            try {
                new ClientsSQL().deleteClient(deleteRowFromJTable());
                cleanTextFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==editButton){
            editRow();
            saveButton.setVisible(true);
        }
        if(e.getSource()==saveButton){
            try {
                new ClientsSQL().updateMySQLTable(readClientsFromTxtFields());
                updateTable();
                cleanTextFields();
                saveButton.setVisible(false);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Client readClientsFromTxtFields(){
        String name = nameTextField.getText();
        String lastN = lastNameTextField.getText();
        String email = emailTextField.getText();
        String num = phoneTextField.getText();
        Client c = new Client(name,lastN,email,num);
        return c;
    }
    public void addRowToJTable(){
        Client c = readClientsFromTxtFields();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{c.getName(),c.getLastName(),c.getEmail(),
                c.getPhoneNum()});
    }

    public String deleteRowFromJTable(){
        int row = table.getSelectedRow();
        String email = (String) table.getModel().getValueAt(row, 2);
        System.out.println(email);
        int modelRow = table.convertRowIndexToModel(row);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.removeRow(modelRow);
        return email;
    }

    public void editRow(){
        int row = table.getSelectedRow();
        Object[] cols = new Object[4];
        if(row!=-1) {
            for (int i = 0; i < 4; i++) {
                cols[i] = table.getModel().getValueAt(row, i);
            }
            nameTextField.setText(String.valueOf(cols[0]));
            lastNameTextField.setText(String.valueOf(cols[1]));
            emailTextField.setText(String.valueOf(cols[2]));
            phoneTextField.setText(String.valueOf(cols[3]));
        }else
            System.out.println("Nothing selected!");
    }

    public void updateTable(){
        int row = table.getSelectedRow();
        if(row!=-1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setValueAt(nameTextField.getText(), row, 0);
            model.setValueAt(lastNameTextField.getText(), row, 1);
            model.setValueAt(emailTextField.getText(), row, 2);
            model.setValueAt(phoneTextField.getText(), row, 3);
        }else
            System.out.println("Nothing selected!");
    }

    public void cleanTextFields(){
        nameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
    }
}
