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
import zm.hashcode.mshengu.app.util.charts.ServiceFleetEfficiencyLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.servicefleetdashboard.ServiceFleetOneMonthlyEfficiencyBean;

/**
 *
 * @author Colin
 */
public class OneMonthEfficiencyLineChart implements Serializable {

    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));
    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();

    public DCharts createChart(List<ServiceFleetOneMonthlyEfficiencyBean> serviceFleetOneMonthlyEfficiencyBeanList) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
//        String label = "Service Vehicles - Monthly Mileages";
//        String formattedGrandTotalMileage = df.format(Double.parseDouble(grandTotalMileage.toString()));
//        String title = "Total Mileage for last " + serviceFleetOneMonthlyEfficiencyBeanList.size() + " Month(s):    " + formattedGrandTotalMileage + " Km";
        float tickInterval = Float.parseFloat("2.00");
        Object minTickValue = 0.00;
//        Object maxTickValue = 0.00;
        BigDecimal highestEfficiencyValue = BigDecimal.ZERO;
        // finding the highest Mileage figure
        for (ServiceFleetOneMonthlyEfficiencyBean serviceFleetOneMonthlyEfficiencyBean : serviceFleetOneMonthlyEfficiencyBeanList) {
            if (serviceFleetOneMonthlyEfficiencyBean.getMonthlyEfficiencyValue().compareTo(highestEfficiencyValue) > 0) {
                highestEfficiencyValue = serviceFleetOneMonthlyEfficiencyBean.getMonthlyEfficiencyValue();
            }
        }

        if (highestEfficiencyValue.compareTo(new BigDecimal("12.00")) > 0) {
            tickInterval = Float.parseFloat("2.50");
//            minTickValue = 0;
        } else {
            tickInterval = Float.parseFloat("2.00");
//            minTickValue = 0;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Add element at start and end of List to force axis to be inside chart rather than be at border and cannot be read by user
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resetMonthToFirstDay(serviceFleetOneMonthlyEfficiencyBeanList.get(0).getTransactionMonth()));
        calendar.add(Calendar.MONTH, -1);
        //
        ServiceFleetOneMonthlyEfficiencyBean ServiceFleetOneMonthlyEfficiencyBeann = new ServiceFleetOneMonthlyEfficiencyBean();
        ServiceFleetOneMonthlyEfficiencyBeann.setId(new Integer("-1") + "");
        ServiceFleetOneMonthlyEfficiencyBeann.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
        ServiceFleetOneMonthlyEfficiencyBeann.setMonthlyEfficiencyValue(serviceFleetOneMonthlyEfficiencyBeanList.get(0).getMonthlyEfficiencyValue());
        ServiceFleetOneMonthlyEfficiencyBeann.setTransactionMonth(calendar.getTime());
        serviceFleetOneMonthlyEfficiencyBeanList.add(0, ServiceFleetOneMonthlyEfficiencyBeann);
        //
////        calendar.setTime(resetMonthToFirstDay(serviceFleetOneMonthlyEfficiencyBeanList.get(serviceFleetOneMonthlyEfficiencyBeanList.size() - 1).getTransactionMonth()));
////        calendar.add(Calendar.MONTH, 1);
////        //
////        ServiceFleetOneMonthlyEfficiencyBean ServiceFleetOneMonthlyEfficiencyBeann1 = new ServiceFleetOneMonthlyEfficiencyBean();
////        ServiceFleetOneMonthlyEfficiencyBeann1.setId(new Integer(serviceFleetOneMonthlyEfficiencyBeanList.size() + "") + "");
////        ServiceFleetOneMonthlyEfficiencyBeann1.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
////        ServiceFleetOneMonthlyEfficiencyBeann1.setMonthlyEfficiencyValue(serviceFleetOneMonthlyEfficiencyBeanList.get(serviceFleetOneMonthlyEfficiencyBeanList.size() - 1).getMonthlyEfficiencyValue());
////        ServiceFleetOneMonthlyEfficiencyBeann1.setTransactionMonth(calendar.getTime());
////        serviceFleetOneMonthlyEfficiencyBeanList.add(ServiceFleetOneMonthlyEfficiencyBeann1);

        // Get Objects of Data
        for (ServiceFleetOneMonthlyEfficiencyBean serviceFleetOneMonthlyEfficiencyBean : serviceFleetOneMonthlyEfficiencyBeanList) {
            totalList.add(serviceFleetOneMonthlyEfficiencyBean.getMonthlyEfficiencyValue());
            monthList.add(serviceFleetOneMonthlyEfficiencyBean.getMonth());
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final ServiceFleetEfficiencyLineChart serviceFleetEfficiencyLineChart = new ServiceFleetEfficiencyLineChart();
        DCharts dLineChart = serviceFleetEfficiencyLineChart.buildLineChart(totalListArray, monthListArray, tickInterval, minTickValue,/* maxTickValue, */ null);

//        dBarChart.getOptions().getTitle().setText(title);
        dLineChart.setWidth("300px");
        dLineChart.setHeight("150px");
        dLineChart.show();

        // USEFUL Statements
//        dBarChart.setMarginBottom(10);
//        dBarChart.setMarginLeft(10);
//        dBarChart.setMarginRight(10);
//        dBarChart.setMarginTop(-3);
//        dBarChart.getOptions().setTitle("");
        return dLineChart;
    }

    public Date resetMonthToFirstDay(Date date) {
        return dateTimeFormatHelper.resetTimeAndMonthStart(date);
    }
}
