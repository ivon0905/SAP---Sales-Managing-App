package myApp.com.gui;

import myApp.com.sql.SalesAnalysisSQL;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SalesAnalysisGUI extends JFrame implements ActionListener {
    JTable table;
    JScrollPane pane;
    JPanel panel1,panel2,panel3,panel4;
    Object[] cols = {"ID of product", "Quantity","Price", "Final Price","Brand","Date"};
    JLabel brandLabel, dateLabel;
    JComboBox<String> brandCB, monthCB;
    JButton brandBtn, dateBtn;
    Font fontLabels = new Font("Times New Roman",Font.ITALIC,20);
    Font fontButtons = new Font("Times New Roman",Font.ITALIC,15);
    Color colorButton = new Color(0XC8B0BD);

    SalesAnalysisGUI() throws SQLException {
        tableAndJScrollPane();
        labels();
        JPanelAndJComboBox();
        buttons();
        addProductsPieChart();
        addCostPieChart();

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Sales");
        this.add(pane);
        this.add(brandLabel);
        this.add(dateLabel);
        this.add(panel1);
        this.add(panel2);
        this.add(panel3);
        this.add(panel4);
        this.add(brandBtn);
        this.add(dateBtn);
        this.setLocationRelativeTo(null);
    }
    public void tableAndJScrollPane() throws SQLException {
        Object[][] lines = new SalesAnalysisSQL().getProducts();
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
    public void addProductsPieChart() throws SQLException {
        ProductsPieChart p = new ProductsPieChart();
        JFreeChart chart = p.getChart();

        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(true);

        panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        panel3.add(chartpanel);
        panel3.setBounds(10,270,340,200);
    }
    public void addCostPieChart() throws SQLException {
        CostPieChart c = new CostPieChart();
        JFreeChart chart = c.getChart();

        ChartPanel chartpanel = new ChartPanel(chart);
        chartpanel.setDomainZoomable(true);

        panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        panel4.add(chartpanel);
        panel4.setBounds(345,270,335,200);
    }
    public void labels(){
        brandLabel = new JLabel();
        brandLabel.setText("Check sales by sale representative");
        brandLabel.setBounds(10,480,300,35);
        brandLabel.setFont(fontLabels);
        brandLabel.setVisible(true);

        dateLabel = new JLabel();
        dateLabel.setText("Check sales by month");
        dateLabel.setBounds(10,520,200,35);
        dateLabel.setFont(fontLabels);
        dateLabel.setVisible(true);
    }

    public void JPanelAndJComboBox() throws SQLException {
        panel1 = new JPanel();
        panel1.setBounds(280,480,150,25);
        String[] brands = new SalesAnalysisSQL().getBrands();
        String[] months = {"7","8","9","10","11","12"};

        brandCB = new JComboBox<>(brands);
        brandCB.setBounds(280,480,150,25);
        brandCB.setVisible(true);
        panel1.add(brandCB);

        panel2 = new JPanel();
        panel2.setBounds(280,520,150,25);
        monthCB = new JComboBox<>(months);
        monthCB.setBounds(280, 520,150,25);
        monthCB.setVisible(true);
        panel2.add(monthCB);
    }
    public void buttons(){
        brandBtn = new JButton();
        brandBtn.setText("View");
        brandBtn.setBounds(450,490,70,25);
        brandBtn.setFocusable(false);
        brandBtn.setFont(fontButtons);
        brandBtn.setBackground(colorButton);
        brandBtn.setForeground(Color.DARK_GRAY);
        brandBtn.addActionListener(this);

        dateBtn = new JButton();
        dateBtn.setText("View");
        dateBtn.setBounds(450,520,70,25);
        dateBtn.setFocusable(false);
        dateBtn.setFont(fontButtons);
        dateBtn.setBackground(colorButton);
        dateBtn.setForeground(Color.DARK_GRAY);
        dateBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==brandBtn){
            String brand = brandCB.getSelectedItem().toString();
            try {
                new AnalysisBySalesRepresentativeGUI(brand);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if(e.getSource()==dateBtn){
            String month = monthCB.getSelectedItem().toString();
            int m = Integer.valueOf(month);
            try {
                new AnalysisByMonthGUI(m);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
