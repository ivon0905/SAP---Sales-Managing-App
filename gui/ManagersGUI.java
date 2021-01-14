package myApp.com.gui;

import myApp.com.models.Manager;
import myApp.com.models.NewAccount;
import myApp.com.sql.ManagersSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ManagersGUI extends JFrame implements ActionListener {
    JTable table, tableRequests;
    JScrollPane pane, paneRequests;
    JButton editButton;
    JButton deleteButton;
    JButton saveButton;
    JButton checkRequestsBtn, addBtn, delBtn;
    JLabel idLabel, brandLabel, nameLabel, lastNameLabel, emailLabel, phoneLabel;
    JTextField idTextField, brandTextField, nameTextField, lastNameTextField,
            phoneTextField, emailTextField;
    Object[] cols = {"ID", "First Name", "Last Name","Email", "Phone Number","Brand"};
    Object[] columns = {"First Name", "Last Name","Email", "Phone Number","Brand"};
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,15);
    Font fontButtons = new Font("Times New Roman",Font.ITALIC,20);
    Color colorButton = new Color(0XC8B0BD);
    JFrame requestFrame, editFrame;

    ManagersGUI() throws SQLException {
        tableAndJScrollPane();
        buttons();
        request();
        edit();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Sale Representatives");
        this.add(pane);
        this.add(editButton);
        this.add(deleteButton);
        this.add(checkRequestsBtn);
        this.setLocationRelativeTo(null);
    }

    public void tableAndJScrollPane() throws SQLException {
        Object[][] lines = new ManagersSQL().getManagers();
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
        editButton = new JButton();
        editButton.setText("Edit");
        editButton.setBounds(0,400,230,50);
        editButton.setFocusable(false);
        editButton.setFont(fontButtons);
        editButton.setBackground(colorButton);
        editButton.setForeground(Color.DARK_GRAY);
        editButton.addActionListener(this);

        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setBounds(230,400,240,50);
        deleteButton.setFocusable(false);
        deleteButton.setFont(fontButtons);
        deleteButton.setBackground(colorButton);
        deleteButton.setForeground(Color.DARK_GRAY);
        deleteButton.addActionListener(this);

        checkRequestsBtn = new JButton();
        checkRequestsBtn.setText("Check Requests");
        checkRequestsBtn.setBounds(470,400,230,50);
        checkRequestsBtn.setFocusable(false);
        checkRequestsBtn.setFont(fontButtons);
        checkRequestsBtn.setBackground(colorButton);
        checkRequestsBtn.setForeground(Color.DARK_GRAY);
        checkRequestsBtn.setVisible(true);
        checkRequestsBtn.addActionListener(this);

        saveButton = new JButton();
        saveButton.setText("Save");
        saveButton.setBounds(290,160,100,20);
        saveButton.setFocusable(false);
        saveButton.setFont(fontButtons);
        saveButton.setBackground(colorButton);
        saveButton.setForeground(Color.DARK_GRAY);
        saveButton.setVisible(false);
        saveButton.addActionListener(this);
    }

    public void labels(){
        idLabel = new JLabel();
        idLabel.setText("ID");
        idLabel.setBounds(120,20,100,35);
        idLabel.setFont(fontLabels);
        idLabel.setVisible(true);

        nameLabel = new JLabel();
        nameLabel.setText("First Name");
        nameLabel.setBounds(310,20,100,35);
        nameLabel.setFont(fontLabels);
        nameLabel.setVisible(true);

        lastNameLabel = new JLabel();
        lastNameLabel.setText("Last Name");
        lastNameLabel.setBounds(520,20,100,35);
        lastNameLabel.setFont(fontLabels);
        lastNameLabel.setVisible(true);

        emailLabel = new JLabel();
        emailLabel.setText("Email");
        emailLabel.setBounds(110,80,100,35);
        emailLabel.setFont(fontLabels);
        emailLabel.setVisible(true);

        phoneLabel = new JLabel();
        phoneLabel.setText("Phone Number");
        phoneLabel.setBounds(300,80,100,35);
        phoneLabel.setFont(fontLabels);
        phoneLabel.setVisible(true);

        brandLabel = new JLabel();
        brandLabel.setText("Brand");
        brandLabel.setBounds(520,80,100,35);
        brandLabel.setFont(fontLabels);
        brandLabel.setVisible(true);
    }

    public void textFields(){
        idTextField = new JTextField();
        idTextField.setBounds(30,50,200,35);

        nameTextField = new JTextField();
        nameTextField.setBounds(240,50,200,35);

        lastNameTextField = new JTextField();
        lastNameTextField.setBounds(450,50,200,35);

        emailTextField = new JTextField();
        emailTextField.setBounds(30,105,200,35);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(240,105,200,35);

        brandTextField = new JTextField();
        brandTextField.setBounds(450,105,200,35);
    }
    public void request() throws SQLException {
        setTableRequests();
        buttonsRequests();

        requestFrame = new JFrame("Requests");
        requestFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        requestFrame.setVisible(false);
        requestFrame.setSize(700,300);
        requestFrame.setResizable(false);
        requestFrame.setLayout(null);
        requestFrame.add(paneRequests);
        requestFrame.add(addBtn);
        requestFrame.add(delBtn);
    }
    public void setTableRequests() throws SQLException {
        Object[][] lines = new ManagersSQL().getRequests();
        DefaultTableModel model = new DefaultTableModel(lines,columns);
        tableRequests = new JTable(model);
        tableRequests.setBackground(Color.WHITE);
        tableRequests.setForeground(Color.DARK_GRAY);
        tableRequests.setRowHeight(30);
        tableRequests.setFont(new Font("Times New Roman",Font.ITALIC,15));
        tableRequests.setVisible(true);

        paneRequests = new JScrollPane(tableRequests);
        paneRequests.setViewportView(tableRequests);
        paneRequests.setBounds(10,10,660,180);
    }

    public void buttonsRequests(){
        addBtn = new JButton("Add");
        addBtn.setBounds(150,200,200,40);
        addBtn.setFocusable(false);
        addBtn.setFont(fontButtons);
        addBtn.setBackground(colorButton);
        addBtn.setForeground(Color.DARK_GRAY);
        addBtn.setVisible(true);
        addBtn.addActionListener(this);

        delBtn = new JButton("Delete");
        delBtn.setBounds(350,200,200,40);
        delBtn.setFocusable(false);
        delBtn.setFont(fontButtons);
        delBtn.setBackground(colorButton);
        delBtn.setForeground(Color.DARK_GRAY);
        delBtn.setVisible(true);
        delBtn.addActionListener(this);
    }

    public void edit(){
        labels();
        textFields();
        buttons();

        editFrame = new JFrame("Edit");
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.setVisible(false);
        editFrame.setSize(700,300);
        editFrame.setResizable(false);
        editFrame.setLayout(null);
        editFrame.add(idLabel);
        editFrame.add(nameLabel);
        editFrame.add(lastNameLabel);
        editFrame.add(emailLabel);
        editFrame.add(phoneLabel);
        editFrame.add(brandLabel);
        editFrame.add(idTextField);
        editFrame.add(nameTextField);
        editFrame.add(lastNameTextField);
        editFrame.add(emailTextField);
        editFrame.add(phoneTextField);
        editFrame.add(brandTextField);
        editFrame.add(saveButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==editButton){
            editFrame.setVisible(true);
            setTextToTextFields(getSelectedRow());
            saveButton.setVisible(true);
        }
        if(e.getSource()==deleteButton){
            String id = deleteRowFromJTable();
            try {
                new ManagersSQL().deleteManager(id);
                new ManagersSQL().deletePassAndUser(id);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==saveButton){
            try {
                new ManagersSQL().updateMySQLTable(readManagersFromTxtFields());
                updateTable();
                cleanTextFields();
                saveButton.setVisible(false);
                editFrame.dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==checkRequestsBtn){
            requestFrame.setVisible(true);
        }
        if(e.getSource()==addBtn){
            Manager m = null;
            try {
                m = new ManagersSQL().addManagerToSQL(getSelectedRowFromRequests());
                addRowToJTable(m);
                deleteRequestFromJTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==delBtn){
            try {
                new ManagersSQL().deleteRequest(getSelectedRowFromRequests());
                deleteRequestFromJTable();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    public Manager readManagersFromTxtFields(){
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String lastN = lastNameTextField.getText();
        String email = emailTextField.getText();
        String num = phoneTextField.getText();
        String brand = brandTextField.getText();
        Manager m = new Manager(id,name,lastN,email,num,brand);
        return m;
    }
    public void addRowToJTable(Manager m){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{m.getId(),m.getName(),m.getLastName(),m.getEmail(),
                m.getPhoneNum(),m.getBrand()});
    }

    public String deleteRowFromJTable(){
        int row = table.getSelectedRow();
        String id = (String) table.getModel().getValueAt(row, 0);
        int modelRow = table.convertRowIndexToModel(row);
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.removeRow(modelRow);
        return id;
    }

    public void deleteRequestFromJTable(){
        int row = tableRequests.getSelectedRow();
        int modelRow = tableRequests.convertRowIndexToModel(row);
        DefaultTableModel model = (DefaultTableModel)tableRequests.getModel();
        model.removeRow(modelRow);
    }

    public void cleanTextFields(){
        idTextField.setText("");
        brandTextField.setText("");
        nameTextField.setText("");
        lastNameTextField.setText("");
        emailTextField.setText("");
        phoneTextField.setText("");
    }

    public void setTextToTextFields(Manager m){
        idTextField.setText(m.getId());
        nameTextField.setText(m.getName());
        lastNameTextField.setText(m.getLastName());
        emailTextField.setText(m.getEmail());
        phoneTextField.setText(m.getPhoneNum());
        brandTextField.setText(m.getBrand());
    }

    public void updateTable(){
        int row = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.setValueAt(idTextField.getText(),row,0);
        model.setValueAt(nameTextField.getText(),row,1);
        model.setValueAt(lastNameTextField.getText(),row,2);
        model.setValueAt(emailTextField.getText(),row,3);
        model.setValueAt(phoneTextField.getText(),row,4);
        model.setValueAt(brandTextField.getText(),row,5);
    }

    public Manager getSelectedRow(){
        int row = table.getSelectedRow();
        Manager m = null;
        String id="", name="", lastName="", email="", phone="", brand="";
        if(row!=-1){
            id = (String) table.getModel().getValueAt(row,0);
            name = (String) table.getModel().getValueAt(row,1);
            lastName = (String) table.getModel().getValueAt(row,2);
            email = (String) table.getModel().getValueAt(row,3);
            phone = (String) table.getModel().getValueAt(row,4);
            brand = (String) table.getModel().getValueAt(row,5);
            m = new Manager(id,name,lastName,email,phone,brand);
        }else
            System.out.println("Nothing selected!");
        return m;
    }

    public NewAccount getSelectedRowFromRequests(){
        int row = tableRequests.getSelectedRow();
        NewAccount n = null;
        String name = "", lName = "", email = "", phone = "", brand = "";
        if(row!=-1) {
            name = (String) tableRequests.getModel().getValueAt(row, 0);
            lName = (String) tableRequests.getModel().getValueAt(row, 1);
            email = (String) tableRequests.getModel().getValueAt(row, 2);
            phone = (String) tableRequests.getModel().getValueAt(row, 3);
            brand = (String) tableRequests.getModel().getValueAt(row, 4);
            n = new NewAccount(name,lName,email,phone,brand);
        }else
            System.out.println("Nothing selected");
        return n;
    }
}
