package jasper.dev;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;

import java.awt.Color;

/**
 * Created by ubu on 20.11.16.
 */
public class BarChartCustomizer  implements JRChartCustomizer {


    public void customize(JFreeChart chart, JRChart jasperChart)
    {
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.green);
        renderer.setSeriesPaint(1, Color.orange);
    }

}
