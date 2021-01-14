package myApp.com.gui;

import myApp.com.models.Product;
import myApp.com.sql.ProductsSQL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class ProductsGUI extends JFrame implements ActionListener {

    JTable table;
    JScrollPane pane;
    JButton addButton;
    JButton redactButton;
    JButton deleteButton;
    JButton saveButton;
    JLabel idLabel, brandLabel, colorLabel, desLabel, priceLabel, typeLabel, quantityLabel, secLabel;
    JTextField idTextField, brandTextField, colorTextField, descrTextField,
            priceTextField, typeTextField, quantityTextField, sectionTextField;
    Object[] cols = {"ID", "Brand", "Color","Description", "Price","Product type","Quantity","Section"};
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,15);
    Font fontButtons = new Font("Times New Roman",Font.ITALIC,20);
    Color colorButton = new Color(0XC8B0BD);

    ProductsGUI() throws SQLException {
        tableAndPane();
        buttons();
        labelsAndTextFields();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        this.setTitle("Products Catalogue");
        this.add(pane);
        this.add(idTextField);
        this.add(brandTextField);
        this.add(colorTextField);
        this.add(descrTextField);
        this.add(priceTextField);
        this.add(typeTextField);
        this.add(quantityTextField);
        this.add(sectionTextField);
        this.add(idLabel);
        this.add(brandLabel);
        this.add(colorLabel);
        this.add(desLabel);
        this.add(priceLabel);
        this.add(typeLabel);
        this.add(quantityLabel);
        this.add(secLabel);
        this.add(addButton);
        this.add(redactButton);
        this.add(deleteButton);
        this.add(saveButton);
        this.setLocationRelativeTo(null);
    }

    public void tableAndPane() throws SQLException {
        Object[][] lines = new ProductsSQL().getProducts();
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

        redactButton = new JButton();
        redactButton.setText("Edit");
        redactButton.setBounds(30,460,200,40);
        redactButton.setFocusable(false);
        redactButton.setFont(fontButtons);
        redactButton.setBackground(colorButton);
        redactButton.setForeground(Color.DARK_GRAY);
        redactButton.addActionListener(this);

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

    public void labelsAndTextFields(){
        idLabel = new JLabel();
        idLabel.setText("ID");
        idLabel.setBounds(85,290,100,35);
        idLabel.setFont(fontLabels);
        idLabel.setVisible(true);
        idTextField = new JTextField();
        idTextField.setBounds(15,320,150,35);

        brandLabel = new JLabel();
        brandLabel.setText("Brand");
        brandLabel.setBounds(240,290,100,35);
        brandLabel.setFont(fontLabels);
        brandLabel.setVisible(true);
        brandTextField = new JTextField();
        brandTextField.setBounds(185,320,150,35);

        colorLabel = new JLabel();
        colorLabel.setText("Color");
        colorLabel.setBounds(410,290,100,35);
        colorLabel.setFont(fontLabels);
        colorLabel.setVisible(true);
        colorTextField = new JTextField();
        colorTextField.setBounds(355,320,150,35);

        desLabel = new JLabel();
        desLabel.setText("Description");
        desLabel.setBounds(565,290,100,35);
        desLabel.setFont(fontLabels);
        desLabel.setVisible(true);
        descrTextField = new JTextField();
        descrTextField.setBounds(525,320,150,35);

        priceLabel = new JLabel();
        priceLabel.setText("Price");
        priceLabel.setBounds(70,355,100,35);
        priceLabel.setFont(fontLabels);
        priceLabel.setVisible(true);
        priceTextField = new JTextField();
        priceTextField.setBounds(15,385,150,35);

        typeLabel = new JLabel();
        typeLabel.setText("Type");
        typeLabel.setBounds(240,355,100,35);
        typeLabel.setFont(fontLabels);
        typeLabel.setVisible(true);
        typeTextField = new JTextField();
        typeTextField.setBounds(185,385,150,35);

        quantityLabel = new JLabel();
        quantityLabel.setText("Quantity");
        quantityLabel.setBounds(405,355,100,35);
        quantityLabel.setFont(fontLabels);
        quantityLabel.setVisible(true);
        quantityTextField = new JTextField();
        quantityTextField.setBounds(355,385,150,35);

        secLabel = new JLabel();
        secLabel.setText("Section");
        secLabel.setFont(fontLabels);
        secLabel.setBounds(580,355,100,35);
        secLabel.setVisible(true);
        sectionTextField = new JTextField();
        sectionTextField.setBounds(525,385,150,35);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==addButton){
            try {
                new ProductsSQL().addProduct(readProductsFromTxtFields());
                addRowToJTable();
                cleanTextFields();
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
        if(e.getSource()==deleteButton){
            try {
                new ProductsSQL().deleteProduct(deleteRowFromJTable());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==redactButton){
            editRow();
            saveButton.setVisible(true);
        }
        if(e.getSource()==saveButton){
            try {
                new ProductsSQL().updateMySQLTable(readProductsFromTxtFields());
                updateTable();
                cleanTextFields();
                saveButton.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public Product readProductsFromTxtFields(){
        String id = idTextField.getText() ;
        String brand = brandTextField.getText();
        String color = colorTextField.getText();
        String des = descrTextField.getText();
        Double price = Double.valueOf(priceTextField.getText());
        String type = typeTextField.getText();
        int quantity = Integer.valueOf(quantityTextField.getText());
        String sec = sectionTextField.getText();
        Product products = new Product(id,brand,color,des,price,type,quantity,sec);
        return products;
    }


    public void addRowToJTable(){
        Product p = readProductsFromTxtFields();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{p.getId(),p.getBrand(),p.getColor(),p.getDescription(),
                p.getPrice(),p.getType(),p.getQuantity(),p.getSection()});
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
    public void editRow(){
        int row = table.getSelectedRow();
        if(row!=-1) {
            Object[] cols = new Object[8];
            for (int i = 0; i < 8; i++) {
                cols[i] = table.getModel().getValueAt(row, i);
            }
            idTextField.setText(String.valueOf(cols[0]));
            brandTextField.setText(String.valueOf(cols[1]));
            colorTextField.setText(String.valueOf(cols[2]));
            descrTextField.setText(String.valueOf(cols[3]));
            priceTextField.setText(String.valueOf(cols[4]));
            typeTextField.setText(String.valueOf(cols[5]));
            quantityTextField.setText(String.valueOf(cols[6]));
            sectionTextField.setText(String.valueOf(cols[7]));
        }else
            System.out.println("Nothing selected!");
    }

    public void updateTable(){
        int row = table.getSelectedRow();
        if(row!=-1) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setValueAt(idTextField.getText(), row, 0);
            model.setValueAt(brandTextField.getText(), row, 1);
            model.setValueAt(colorTextField.getText(), row, 2);
            model.setValueAt(descrTextField.getText(), row, 3);
            model.setValueAt(priceTextField.getText(), row, 4);
            model.setValueAt(typeTextField.getText(), row, 5);
            model.setValueAt(quantityTextField.getText(), row, 6);
            model.setValueAt(sectionTextField.getText(), row, 7);
        }else
            System.out.println("Nothing selected!");
    }

    public void cleanTextFields(){
       idTextField.setText("");
       brandTextField.setText("");
       colorTextField.setText("");
       descrTextField.setText("");
       priceTextField.setText("");
       typeTextField.setText("");
       quantityTextField.setText("");
       sectionTextField.setText("");
   }
}
