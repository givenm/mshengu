/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.base.elements.XYseries;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.TextAligns;
import org.dussan.vaadin.dcharts.metadata.TooltipAxes;
import org.dussan.vaadin.dcharts.metadata.XYaxes;
import org.dussan.vaadin.dcharts.metadata.locations.TooltipLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.TickRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.Series;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.options.Title;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.AxisTickRenderer;
import org.dussan.vaadin.dcharts.renderers.tick.CanvasAxisTickRenderer;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendBySupplier;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierPieChart implements Serializable {

    public DCharts createChart(List<TotalMaintenanceSpendBySupplier> totalMaintenanceSpendBySupplierList, String chartPeriod /* , String chartType */) {
//        List<Object> totalList = new ArrayList<>();
        List<Object> serviceProviderNameList = new ArrayList<>();
        List<Object> percentageList = new ArrayList<>();
        BigDecimal sumOfPercentage = BigDecimal.ZERO;
        //
        DataSeries dataSeries = new DataSeries();
        Series series = new Series();
        Axes axes = new Axes();
        XYaxis xyAxis = new XYaxis();
        Ticks ticks = new Ticks();

        // Get Objects of Data
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
//            totalList.add(totalMaintenanceSpendBySupplier.getTotal());
            serviceProviderNameList.add(totalMaintenanceSpendBySupplier.getSupplierName());
            percentageList.add(totalMaintenanceSpendBySupplier.getSpendPercentage());
            sumOfPercentage = sumOfPercentage.add(totalMaintenanceSpendBySupplier.getSpendPercentage());
        }
//        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] serviceProviderNameListArray = serviceProviderNameList.toArray(new Object[serviceProviderNameList.size()]);
        Object[] percentageListArray = percentageList.toArray(new Object[percentageList.size()]);

        for (int i = 0; i < serviceProviderNameListArray.length; i++) {
            BigDecimal seriesPercentValue = new BigDecimal(percentageListArray[i] + "").multiply(sumOfPercentage);
            seriesPercentValue = seriesPercentValue.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);

            dataSeries.newSeries().add(serviceProviderNameListArray[i], seriesPercentValue);
        }

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setRenderer(SeriesRenderers.PIE)
                .setRendererOptions(
                new PieRenderer()
                .setShowDataLabels(true));

        Legend legend = new Legend()
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
                .setOptions(options)
                .show();

        dChart.setWidth("600px");
        dChart.setHeight("300px");

        return dChart;
    }

//              BigDecimal percentage = totalMaintenanceSpendBySupplier.getTotal().divide(grandTotalMaintenanceSpend, 2, RoundingMode.HALF_UP); // BigDecimal.ROUND_UP
//            percentage = percentage.multiply(new BigDecimal("100"));
//            percentage = roundBigDecimal(percentage, 0);
//            //
//            totalMaintenanceSpendBySupplier.setSpendPercentage(percentage);
//        }
//    }
    public BigDecimal roundBigDecimal(BigDecimal value, int decimalPlaces) {
        if (decimalPlaces < 0) {
            throw new IllegalArgumentException();
        }
        return value.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
    }
}
