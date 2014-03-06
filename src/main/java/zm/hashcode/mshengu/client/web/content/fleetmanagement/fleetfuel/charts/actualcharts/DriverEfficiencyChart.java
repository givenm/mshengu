/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.app.util.charts.BarChart;
import zm.hashcode.mshengu.app.util.charts.DriverEfficiencyBarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.DriverEfficiencyBean;

/**
 *
 * @author Colin
 */
public class DriverEfficiencyChart implements Serializable {

    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));
    private String title = null;

    public DCharts createChart(List<DriverEfficiencyBean> driverEfficiencyBeanList /* , BigDecimal grandTotal */) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
        String label = "Total Fuel Spend";
//        String grandTotalAmount = df.format(Double.parseDouble(grandTotal.toString()));
        title = "< R6.00/Km = Green  |  R6.00 - R8.00/Km = Yellow  |  >R8.00/Km = Red";
        float tickInterval = Float.parseFloat("1");
        Object minTickValue = 0;
        BigDecimal highestEfficiency = BigDecimal.ZERO;
        for (DriverEfficiencyBean driverEfficiencyBean : driverEfficiencyBeanList) {
            if (driverEfficiencyBean.getMonthlyEfficiencyValue().compareTo(highestEfficiency) > 0) {
                highestEfficiency = driverEfficiencyBean.getMonthlyEfficiencyValue();
            }
        }

        if (highestEfficiency.compareTo(new BigDecimal("10")) < 0) {
            tickInterval = Float.parseFloat("1");
//            minTickValue=5000;
        } else {
            tickInterval = Float.parseFloat("2");
//             minTickValue=10000;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Get Objects of Data
        for (DriverEfficiencyBean driverEfficiencyBean : driverEfficiencyBeanList) {
            totalList.add(driverEfficiencyBean.getMonthlyEfficiencyValue());
            // Truncate the Names
            monthList.add(truncate(driverEfficiencyBean.getDriverName()));
            // Add Percentage for PIE
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final DriverEfficiencyBarChart barChart = new DriverEfficiencyBarChart();
        DCharts dBarChart = barChart.buildBarChart(totalListArray, monthListArray, tickInterval, label, minTickValue, title);
        dBarChart.setWidth("300px");
        dBarChart.setHeight("150px");
        dBarChart.show();

        // USEFUL Statement
//        dBarChart.setMarginBottom(10);
//        dBarChart.setMarginLeft(10);
//        dBarChart.setMarginRight(10);
//        dBarChart.setMarginTop(-3);
//        dBarChart.getOptions().setTitle("");

        return dBarChart;
    }

    public static String truncate(String value) {
        if (value != null) {
            String[] stringArray = value.split(" ", 2);
            return stringArray[0];
        }
        return value;
    }
}
