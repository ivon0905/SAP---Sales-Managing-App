package myApp.com.gui;

import myApp.com.sql.AccountSQL;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserAnalysisGUI extends JFrame{

    JPanel panel;

    UserAnalysisGUI(String id) throws SQLException {
        String[] accountData = new AccountSQL().getAccountData(id);
        addChartToJFrame(accountData[4]);

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setSize(700,360);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Analysis");
        this.add(panel);
    }

    public void addChartToJFrame(String brand) throws SQLException {
        SalesRepresentativeChart chart = new SalesRepresentativeChart();
        JFreeChart c = chart.getChart(brand);

        ChartPanel chartpanel = new ChartPanel(c);
        chartpanel.setDomainZoomable(true);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel);
        panel.setBounds(10,10,660,300);
    }
}
