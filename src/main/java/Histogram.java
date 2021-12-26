import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.Color;
import java.util.Map;

public class Histogram extends JFrame {

    public Histogram(Map<String, Double> healthByCountry) {
        initializeForm(healthByCountry);
    }

    private void initializeForm(Map<String, Double> healthByCountry) {
        CategoryDataset dataset = createDataset(healthByCountry);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setVerticalAxisTrace(true);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Bar chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(Map<String, Double> healthByCountry) {
        var dataset = new DefaultCategoryDataset();
        healthByCountry.forEach((c, h) -> dataset.setValue(h, "HealthScore", c));
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Health scores in countries",
                "Countries",
                "Health score",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);
    }
}
