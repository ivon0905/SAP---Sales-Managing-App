package myApp.com.gui;

import myApp.com.sql.SalesAnalysisSQL;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class AnalysisByMonthGUI extends JFrame{
    JTable table;
    JScrollPane pane;
    Object[] cols = {"ID of product", "Quantity","Price", "Final Price","Brand","Date"};
    JPanel panel;

    AnalysisByMonthGUI(int month) throws SQLException {
        tableAndJScrollPane(month);
        addChartToJFrame();

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

    public void addChartToJFrame() throws SQLException {
        AllSalesChart chart = new AllSalesChart();
        JFreeChart c = chart.getChart();

        ChartPanel chartpanel = new ChartPanel(c);
        chartpanel.setDomainZoomable(true);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel);
        panel.setBounds(10,260,660,290);
    }

    public void tableAndJScrollPane(int month) throws SQLException {
        Object[][] lines = new SalesAnalysisSQL().getSalesByMonth(month);
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
}
