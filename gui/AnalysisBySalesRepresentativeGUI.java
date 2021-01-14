package myApp.com.gui;

import myApp.com.sql.SalesAnalysisSQL;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class AnalysisBySalesRepresentativeGUI extends JFrame {
    JTable table;
    JScrollPane pane;
    JPanel panel;
    Object[] cols = {"ID of product", "Quantity","Price", "Final Price","Brand","Date"};

    public AnalysisBySalesRepresentativeGUI(String brand) throws SQLException {
        tableAndJScrollPane(brand);
        addChartToJFrame(brand);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Sales");
        this.add(pane);
        this.add(panel);
        this.setLocationRelativeTo(null);
    }

    public void addChartToJFrame(String brand) throws SQLException {
        SalesRepresentativeChart chart = new SalesRepresentativeChart();
        JFreeChart c = chart.getChart(brand);

        ChartPanel chartpanel = new ChartPanel(c);
        chartpanel.setDomainZoomable(true);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel);
        panel.setBounds(10,255,660,290);
    }

    public void tableAndJScrollPane(String brand) throws SQLException {
        Object[][] lines = new SalesAnalysisSQL().getSalesByBrand(brand);
        DefaultTableModel model = new DefaultTableModel(lines,cols);
        table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.DARK_GRAY);
        table.setRowHeight(30);
        table.setFont(new Font("Times New Roman",Font.ITALIC,15));
        table.setVisible(true);

        pane = new JScrollPane(table);
        pane.setViewportView(table);
        pane.setBounds(10,10,660,240);
    }
}
