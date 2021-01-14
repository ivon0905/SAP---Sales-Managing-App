package myApp.com.gui;

import myApp.com.sql.SalesAnalysisSQL;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.sql.SQLException;

public class SalesRepresentativeChart{
    String b;
    public JFreeChart getChart(String brand) throws SQLException {
        b = brand;
        Object[][] o = new SalesAnalysisSQL().getSalesByBrandForChart(b);
        CategoryDataset dataset1 = createDataset1(o);
        CategoryDataset dataset2 = createDataset2(o);
        JFreeChart chart = createChart(dataset1, dataset2);
        return chart;
    }
    private CategoryDataset createDataset1(Object[][] o) {
        final String series1 = "Products";
        final String series2 = "Dummy 1";

        final String category1 = "July";
        final String category2 = "August";
        final String category3 = "September";
        final String category4 = "October";
        final String category5 = "November";
        final String category6 = "December";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(Integer.valueOf((Integer) o[0][0]), series1, category1);
        dataset.addValue(Integer.valueOf(String.valueOf(o[1][0])), series1, category2);
        dataset.addValue(Integer.valueOf(String.valueOf(o[2][0])), series1, category3);
        dataset.addValue(Integer.valueOf(String.valueOf(o[3][0])), series1, category4);
        dataset.addValue(Integer.valueOf(String.valueOf(o[4][0])), series1, category5);
        dataset.addValue(Integer.valueOf(String.valueOf(o[5][0])), series1, category6);

        dataset.addValue(null, series2, category1);
        dataset.addValue(null, series2, category2);
        dataset.addValue(null, series2, category3);
        dataset.addValue(null, series2, category4);
        dataset.addValue(null, series2, category5);
        dataset.addValue(null, series2, category6);

        return dataset;

    }
    private CategoryDataset createDataset2(Object[][] o) {
        final String series1 = "Dummy 2";
        final String series2 = "Cost";

        final String category1 = "July";
        final String category2 = "August";
        final String category3 = "September";
        final String category4 = "October";
        final String category5 = "November";
        final String category6 = "December";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(null, series1, category1);
        dataset.addValue(null, series1, category2);
        dataset.addValue(null, series1, category3);
        dataset.addValue(null, series1, category4);
        dataset.addValue(null, series1, category5);
        dataset.addValue(null, series1, category6);

        dataset.addValue(Double.valueOf((Double) o[0][1]), series2, category1);
        dataset.addValue(Double.valueOf((Double) o[1][1]), series2, category2);
        dataset.addValue(Double.valueOf((Double) o[2][1]), series2, category3);
        dataset.addValue(Double.valueOf((Double) o[3][1]), series2, category4);
        dataset.addValue(Double.valueOf((Double) o[4][1]), series2, category5);
        dataset.addValue(Double.valueOf((Double) o[5][1]), series2, category6);

        return dataset;
    }
    private JFreeChart createChart(final CategoryDataset dataset1, final CategoryDataset dataset2) {

        final CategoryAxis domainAxis = new CategoryAxis("Month");
        final NumberAxis rangeAxis = new NumberAxis("Number of sold products");
        final BarRenderer renderer1 = new BarRenderer();
        final CategoryPlot plot = new CategoryPlot(dataset1, domainAxis, rangeAxis, renderer1) {

            public LegendItemCollection getLegendItems() {

                final LegendItemCollection result = new LegendItemCollection();

                final CategoryDataset data = getDataset();
                if (data != null) {
                    final CategoryItemRenderer r = getRenderer();
                    if (r != null) {
                        final LegendItem item = r.getLegendItem(0, 0);
                        result.add(item);
                    }
                }

                final CategoryDataset dset2 = getDataset(1);
                if (dset2 != null) {
                    final CategoryItemRenderer renderer2 = getRenderer(1);
                    if (renderer2 != null) {
                        final LegendItem item = renderer2.getLegendItem(1, 1);
                        result.add(item);
                    }
                }
                return result;
            }
        };

        final JFreeChart chart = new JFreeChart("Sales", plot);
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        final ValueAxis axis2 = new NumberAxis("Value of sold products");
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        final BarRenderer renderer2 = new BarRenderer();
        plot.setRenderer(1, renderer2);

        return chart;
    }
}
