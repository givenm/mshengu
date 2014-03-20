/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts.monthlyfuelexpense;

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
import zm.hashcode.mshengu.app.util.charts.NonServiceVehiclesTwelveMonthLineChart;
import zm.hashcode.mshengu.app.util.charts.LineChart;
import zm.hashcode.mshengu.app.util.charts.MileageLineChart;
import zm.hashcode.mshengu.app.util.charts.ServiceVehiclesTwelveMonthLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.ServiceVehiclesTwelveMonthBean;

/**
 *
 * @author Colin
 */
public class ServiceVehiclesTwelveMonthChart implements Serializable {

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public DCharts createChart(List<ServiceVehiclesTwelveMonthBean> serviceVehiclesTwelveMonthBeanList) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
        float tickInterval = Float.parseFloat("50000");
        Object minTickValue = 100000;
        boolean isZeroFound = false;
        BigDecimal highestFuelSpendTotal = BigDecimal.ZERO;
        for (ServiceVehiclesTwelveMonthBean serviceVehiclesTwelveMonthBean : serviceVehiclesTwelveMonthBeanList) {
            if (serviceVehiclesTwelveMonthBean.getTotalAmount().compareTo(highestFuelSpendTotal) > 0) {
                highestFuelSpendTotal = serviceVehiclesTwelveMonthBean.getTotalAmount();
//                System.out.println("R/Ltr for " + fuelSpendMonthlyCostBean.getMonth() + ": " + fuelSpendMonthlyCostBean.getMonthRandPerLiter());
            }
            if (serviceVehiclesTwelveMonthBean.getTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
                isZeroFound = true;
            }
        }

        if (highestFuelSpendTotal.compareTo(new BigDecimal("350000")) < 0) {
            tickInterval = Float.parseFloat("50000");
            minTickValue = 100000;
        } else if (highestFuelSpendTotal.compareTo(new BigDecimal("500000")) < 0) {
            tickInterval = Float.parseFloat("100000");
            minTickValue = 150000;
        }
        if (isZeroFound) {
            tickInterval = Float.parseFloat("150000");
            minTickValue = 0.00;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Add element at start and end of List to force axis to be inside chart rather than be at border and cannot be read by user
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(resetMonthToFirstDay(serviceVehiclesTwelveMonthBeanList.get(0).getTransactionMonth()));
        calendar.add(Calendar.MONTH, -1);
        //
        ServiceVehiclesTwelveMonthBean ServiceVehiclesTwelveMonthBeann = new ServiceVehiclesTwelveMonthBean();
        ServiceVehiclesTwelveMonthBeann.setId(new Integer("-1") + "");
        ServiceVehiclesTwelveMonthBeann.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
        ServiceVehiclesTwelveMonthBeann.setTotalAmount(serviceVehiclesTwelveMonthBeanList.get(0).getTotalAmount());
        ServiceVehiclesTwelveMonthBeann.setTransactionMonth(calendar.getTime());
        serviceVehiclesTwelveMonthBeanList.add(0, ServiceVehiclesTwelveMonthBeann);
//        //
//        calendar.setTime(resetMonthToFirstDay(serviceVehiclesTwelveMonthBeanList.get(serviceVehiclesTwelveMonthBeanList.size() - 1).getTransactionMonth()));
//        calendar.add(Calendar.MONTH, 1);
//        //
//        ServiceVehiclesTwelveMonthBean ServiceVehiclesTwelveMonthBeann1 = new ServiceVehiclesTwelveMonthBean();
//        ServiceVehiclesTwelveMonthBeann1.setId(new Integer((serviceVehiclesTwelveMonthBeanList.size() + 1) + "") + "");
//        ServiceVehiclesTwelveMonthBeann1.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
//        ServiceVehiclesTwelveMonthBeann1.setTotalAmount(serviceVehiclesTwelveMonthBeanList.get(serviceVehiclesTwelveMonthBeanList.size() - 1).getTotalAmount());
//        ServiceVehiclesTwelveMonthBeann1.setTransactionMonth(calendar.getTime());
//        serviceVehiclesTwelveMonthBeanList.add(ServiceVehiclesTwelveMonthBeann1);

        // Get Objects of Data
        for (ServiceVehiclesTwelveMonthBean serviceVehiclesTwelveMonthBean : serviceVehiclesTwelveMonthBeanList) {
            totalList.add(serviceVehiclesTwelveMonthBean.getTotalAmount());
            monthList.add(serviceVehiclesTwelveMonthBean.getMonth());
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);


        final ServiceVehiclesTwelveMonthLineChart serviceVehiclesTwelveMonthLineChart = new ServiceVehiclesTwelveMonthLineChart();
        DCharts dBarChart = serviceVehiclesTwelveMonthLineChart.buildLineChart(totalListArray, monthListArray, tickInterval, minTickValue, null); // null for Chart Title
//        dBarChart.getOptions().getTitle().setText(title);
        dBarChart.setWidth("450px");
        dBarChart.setHeight("225px");
        dBarChart.show();

        // USEFUL Statements
//        dBarChart.setMarginBottom(10);
//        dBarChart.setMarginLeft(10);
//        dBarChart.setMarginRight(10);
//        dBarChart.setMarginTop(-3);
//        dBarChart.getOptions().setTitle("");

        return dBarChart;
    }

    public Date resetMonthToFirstDay(Date date) {
        return dateTimeFormatHelper.resetTimeAndMonthStart(date);
    }
}
