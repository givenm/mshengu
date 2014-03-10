/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.servicefleetdashboard;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.charts.LineChart;
import zm.hashcode.mshengu.app.util.charts.MileageLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard.MonthlyMileageTotalBean;

/**
 *
 * @author Colin
 */
public class TotalMonthlyMileageLineChart implements Serializable {

    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0", new DecimalFormatSymbols(locale));

    public DCharts createChart(List<MonthlyMileageTotalBean> monthlyMileageTotalBeanList, Integer grandTotalMileage) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
//        String label = "Service Vehicles - Monthly Mileages";
        String formattedGrandTotalMileage = df.format(Double.parseDouble(grandTotalMileage.toString()));
        String title = "Total Mileage for last " + monthlyMileageTotalBeanList.size() + " Month(s):    " + formattedGrandTotalMileage + " Km";
        float tickInterval = Float.parseFloat("5000");
        Object minTickValue = 0;
        Integer highestMileage = new Integer("0");
        // finding the highest Mileage figure
        for (MonthlyMileageTotalBean monthlyMileageTotalBean : monthlyMileageTotalBeanList) {
            if (monthlyMileageTotalBean.getMonthlyMileageTotal().compareTo(highestMileage) > 0) {
                highestMileage = monthlyMileageTotalBean.getMonthlyMileageTotal();
            }
        }

        if (highestMileage.compareTo(new Integer("40000")) < 0) {
            tickInterval = Float.parseFloat("5000");
//            minTickValue = 0;
        } else if (highestMileage.compareTo(new Integer("70000")) < 0) {
            tickInterval = Float.parseFloat("6500");
//            minTickValue = 0;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Get Objects of Data
        for (MonthlyMileageTotalBean monthlyMileageTotalBean : monthlyMileageTotalBeanList) {
            totalList.add(monthlyMileageTotalBean.getMonthlyMileageTotal());
            monthList.add(monthlyMileageTotalBean.getMonth());
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final MileageLineChart mileageLineChart = new MileageLineChart();
        DCharts dLineChart = mileageLineChart.buildLineChart(totalListArray, monthListArray, tickInterval, minTickValue, title);

//        dBarChart.getOptions().getTitle().setText(title);
        dLineChart.setWidth("600px");
        dLineChart.setHeight("300px");
        dLineChart.show();

        // USEFUL Statements
//        dBarChart.setMarginBottom(10);
//        dBarChart.setMarginLeft(10);
//        dBarChart.setMarginRight(10);
//        dBarChart.setMarginTop(-3);
//        dBarChart.getOptions().setTitle("");

        return dLineChart;
    }
}
