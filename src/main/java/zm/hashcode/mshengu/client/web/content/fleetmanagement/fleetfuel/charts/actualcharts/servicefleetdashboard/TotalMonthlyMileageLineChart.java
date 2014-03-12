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
import java.util.Calendar;
import java.util.Date;
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

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
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

        // Add element at start and end of List to force axis to be inside chart rather than be at border and cannot be read by user
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthlyMileageTotalBeanList.get(0).getTransactionMonth());
        calendar.add(Calendar.MONTH, -1);
        //
        MonthlyMileageTotalBean monthlyMileageTotalBeann = new MonthlyMileageTotalBean();
        monthlyMileageTotalBeann.setId(new Integer("-1") + "");
        monthlyMileageTotalBeann.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
        monthlyMileageTotalBeann.setMonthlyMileageTotal(0);
        monthlyMileageTotalBeann.setTransactionMonth(calendar.getTime());
        monthlyMileageTotalBeanList.add(0, monthlyMileageTotalBeann);
        //
        calendar.setTime(monthlyMileageTotalBeanList.get(monthlyMileageTotalBeanList.size() - 1).getTransactionMonth());
        calendar.add(Calendar.MONTH, 1);
        //
        monthlyMileageTotalBeann.setId(new Integer(monthlyMileageTotalBeanList.size() + "") + "");
        monthlyMileageTotalBeann.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
        monthlyMileageTotalBeann.setMonthlyMileageTotal(0);
        monthlyMileageTotalBeann.setTransactionMonth(calendar.getTime());
        monthlyMileageTotalBeanList.add(monthlyMileageTotalBeann);

        // Get Objects of Data
        for (MonthlyMileageTotalBean monthlyMileageTotalBean : monthlyMileageTotalBeanList) {
            totalList.add(monthlyMileageTotalBean.getMonthlyMileageTotal());
            // Truncate Year from yyyy to yy
            monthList.add(monthlyMileageTotalBean.getTransactionMonth());
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
