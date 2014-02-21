/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.metadata.locations.LegendLocations;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;
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

        DataSeries dataSeries = new DataSeries();
        for (int i = 0; i < serviceProviderNameListArray.length; i++) {
            BigDecimal seriesPercentValue = new BigDecimal(percentageListArray[i] + "").multiply(sumOfPercentage);
            seriesPercentValue = seriesPercentValue.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            dataSeries.newSeries().add(truncate(serviceProviderNameListArray[i], 14), seriesPercentValue);
        }

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setRenderer(SeriesRenderers.PIE)
                .setRendererOptions(
                new PieRenderer()
                .setShowDataLabels(true));

        Legend legend = new Legend()
                .setShow(true)
                .setLocation(LegendLocations.NORTH_EAST);

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

        dChart.setWidth("300px");
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

    public static String truncate(Object value, int length) {
        String strValue = value.toString();
        if (strValue != null && strValue.length() > length) {
            strValue = strValue.substring(0, length);
        }
        return strValue;
    }
}
