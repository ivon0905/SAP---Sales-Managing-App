package myApp.com.gui;

import myApp.com.models.Sale;
import myApp.com.sql.AccountSQL;
import myApp.com.sql.SalesCatalogueSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SalesCatalogueGUI extends JFrame implements ActionListener {
    JTable table;
    JScrollPane pane;
    JButton addButton;
    JButton deleteButton;
    JLabel idLabel, quantityLabel, priceLabel;
    JTextField idTextField, quantityTextField, priceTextField;
    Object[] cols = {"ID of product", "Quantity","Price", "Final Price","Brand","Date"};
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,15);
    Font fontButtons = new Font("Times New Roman",Font.ITALIC,20);
    Color colorButton = new Color(0XC8B0BD);
    String ID = "";

    SalesCatalogueGUI(String id) throws SQLException {
        ID = id;
        tableAndJScrollPane();
        buttons();
        labels();
        textFields();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Sales Catalogue");
        this.add(pane);
        this.add(addButton);
        this.add(deleteButton);
        this.add(idLabel);
        this.add(quantityLabel);
        this.add(priceLabel);
        this.add(idTextField);
        this.add(quantityTextField);
        this.add(priceTextField);
        this.setLocationRelativeTo(null);
    }

    public void tableAndJScrollPane() throws SQLException {
        Object[][] lines = new SalesCatalogueSQL().getSales(ID);
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
        addButton.setBounds(0,460,350,40);
        addButton.setFocusable(false);
        addButton.setFont(fontButtons);
        addButton.setBackground(colorButton);
        addButton.setForeground(Color.DARK_GRAY);
        addButton.addActionListener(this);

        deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setBounds(350,460,350,40);
        deleteButton.setFocusable(false);
        deleteButton.setFont(fontButtons);
        deleteButton.setBackground(colorButton);
        deleteButton.setForeground(Color.DARK_GRAY);
        deleteButton.addActionListener(this);
    }
    public void labels(){
        idLabel = new JLabel();
        idLabel.setText("ID");
        idLabel.setBounds(120,310,100,35);
        idLabel.setFont(fontLabels);
        idLabel.setVisible(true);

        quantityLabel = new JLabel();
        quantityLabel.setText("Quantity");
        quantityLabel.setBounds(310,310,100,35);
        quantityLabel.setFont(fontLabels);
        quantityLabel.setVisible(true);

        priceLabel = new JLabel();
        priceLabel.setText("Price");
        priceLabel.setBounds(520,310,100,35);
        priceLabel.setFont(fontLabels);
        priceLabel.setVisible(true);
    }

    public void textFields(){
        idTextField = new JTextField();
        idTextField.setBounds(30,340,200,35);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(240,340,200,35);

        priceTextField = new JTextField();
        priceTextField.setBounds(450,340,200,35);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addButton){
            try {
                new SalesCatalogueSQL().addSale(readSales());
                addRowToJTable();
                cleanTextFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==deleteButton){
            try {
                new SalesCatalogueSQL().deleteSale(deleteRowFromJTable());
                cleanTextFields();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Sale readSales() throws SQLException {
        String id = idTextField.getText() ;
        int quantity = Integer.valueOf(quantityTextField.getText());
        Double price = Double.valueOf(priceTextField.getText());
        Double finalPrice = quantity * price;
        String[] brand = new AccountSQL().getAccountData(ID);
        String date = getDate();
        Sale s = new Sale(id,quantity,price,finalPrice,brand[4],date);
        return s;
    }

    public void cleanTextFields(){
        idTextField.setText("");
        quantityTextField.setText("");
        priceTextField.setText("");
    }

    public void addRowToJTable() throws SQLException {
        Sale s = readSales();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{s.getId(),s.getQuantity(),s.getPrice(),s.getFinalPrice(),
                                  s.getBrand(),getDate()});
    }

    public String deleteRowFromJTable(){
        int row = table.getSelectedRow();
        String id = "";
        if(row!=-1) {
            id = (String) table.getModel().getValueAt(row, 0);
            System.out.println(id);
            int modelRow = table.convertRowIndexToModel(row);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.removeRow(modelRow);
        }
        return id;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
}
