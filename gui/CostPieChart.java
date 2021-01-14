package myApp.com.gui;

import myApp.com.sql.SalesAnalysisSQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import java.awt.*;
import java.sql.SQLException;

public class CostPieChart{
    JFreeChart getChart() throws SQLException {
        PieDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset, "Distribution by cost of sold products");

        return chart;
    }
    private  PieDataset createDataset() throws SQLException {
        DefaultPieDataset result = new DefaultPieDataset();
        Object[][] o = new SalesAnalysisSQL().getQuantityAndCostOfProductsByBrands();
        for(int i=0;i<o.length;i++)
            result.setValue(String.valueOf(o[i][2]),Double.valueOf((Double) o[i][1]));
        return result;
    }
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title,
                dataset,
                true,
                true,
                false
        );
        chart.setBackgroundPaint(Color.WHITE);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
    }
}
