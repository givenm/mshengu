/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.app.util.charts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.LegendPlacements;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;

/**
 *
 * @author Colin
 */
public class PieChart implements Serializable {

    public DCharts buildPieChart(Object[] nameListArray, Object[] percentageListArray, BigDecimal sumOfPercentage /* , String chartPeriod */) {

        DataSeries dataSeries = new DataSeries();
        for (int i = 0; i < nameListArray.length; i++) {
            BigDecimal seriesPercentValue = new BigDecimal(percentageListArray[i] + "").multiply(sumOfPercentage);
            seriesPercentValue = seriesPercentValue.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            dataSeries.newSeries().add(nameListArray[i], seriesPercentValue);
        }

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setRenderer(SeriesRenderers.PIE)
                .setRendererOptions(
                new PieRenderer()
                .setShowDataLabels(true));

        Legend legend = new Legend()
                .setPlacement(LegendPlacements.OUTSIDE_GRID)
                .setLocation(LegendLocations.NORTH_EAST)
                .setShow(true);


        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setShowTooltip(true)
                .setTooltipAlwaysVisible(true)
                .setKeepTooltipInsideChart(true);

        Options options = new Options()
                .setSeriesDefaults(seriesDefaults)
                .setLegend(legend)
                .setHighlighter(highlighter);

        DCharts dChart = new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options);

        return dChart;
    }
}
