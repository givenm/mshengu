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
import zm.hashcode.mshengu.app.util.charts.MileageLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.NonServiceVehiclesTwelveMonthBean;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.ServiceVehiclesTwelveMonthBean;

/**
 *
 * @author Colin
 */
public class NonServiceVehiclesTwelveMonthChart implements Serializable {

    private final DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public DCharts createChart(List<NonServiceVehiclesTwelveMonthBean> nonServiceVehiclesTwelveMonthBeanList) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
        float tickInterval = Float.parseFloat("5000");
        Object minTickValue = 0.00;
        boolean isZeroFound = false;
        BigDecimal highestFuelSpendTotal = BigDecimal.ZERO;
        for (NonServiceVehiclesTwelveMonthBean nonServiceVehiclesTwelveMonthBean : nonServiceVehiclesTwelveMonthBeanList) {
            if (nonServiceVehiclesTwelveMonthBean.getNonOperationalTotalAmount().compareTo(highestFuelSpendTotal) > 0) {
                highestFuelSpendTotal = nonServiceVehiclesTwelveMonthBean.getNonOperationalTotalAmount();
//                System.out.println("R/Ltr for " + fuelSpendMonthlyCostBean.getMonth() + ": " + fuelSpendMonthlyCostBean.getMonthRandPerLiter());
            }
            if (nonServiceVehiclesTwelveMonthBean.getNonOperationalTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
                isZeroFound = true;
            }
        }

        if (highestFuelSpendTotal.compareTo(new BigDecimal("35000")) < 0) {
            tickInterval = Float.parseFloat("5000");
//            minTickValue = 0.00;
        } else if (highestFuelSpendTotal.compareTo(new BigDecimal("50000")) < 0) {
            tickInterval = Float.parseFloat("8000");
//            minTickValue = 0.00;
        }
//        if (isZeroFound) {
//            tickInterval = Float.parseFloat("150000");
////            minTickValue = 0.00;
//        }

        List<Object> nonOperationalTotalList = new ArrayList<>();
        List<Object> operationalTotalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        if (!nonServiceVehiclesTwelveMonthBeanList.isEmpty()) {
            // Add element at start and end of List to force axis to be inside chart rather than be at border and cannot be read by user
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(resetMonthToFirstDay(nonServiceVehiclesTwelveMonthBeanList.get(0).getTransactionMonth()));
            calendar.add(Calendar.MONTH, -1);
            //
            NonServiceVehiclesTwelveMonthBean nonServiceVehiclesTwelveMonthBeann = new NonServiceVehiclesTwelveMonthBean();
            nonServiceVehiclesTwelveMonthBeann.setId(new Integer("-1") + "");
            nonServiceVehiclesTwelveMonthBeann.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
            nonServiceVehiclesTwelveMonthBeann.setNonOperationalTotalAmount(nonServiceVehiclesTwelveMonthBeanList.get(0).getNonOperationalTotalAmount());
            nonServiceVehiclesTwelveMonthBeann.setOperationalTotalAmount(nonServiceVehiclesTwelveMonthBeanList.get(0).getOperationalTotalAmount());
            nonServiceVehiclesTwelveMonthBeann.setTransactionMonth(calendar.getTime());
            nonServiceVehiclesTwelveMonthBeanList.add(0, nonServiceVehiclesTwelveMonthBeann);
            //
            calendar.setTime(resetMonthToFirstDay(nonServiceVehiclesTwelveMonthBeanList.get(nonServiceVehiclesTwelveMonthBeanList.size() - 1).getTransactionMonth()));
            calendar.add(Calendar.MONTH, 1);
            //
            NonServiceVehiclesTwelveMonthBean nonServiceVehiclesTwelveMonthBeann1 = new NonServiceVehiclesTwelveMonthBean();
            nonServiceVehiclesTwelveMonthBeann1.setId(new Integer((nonServiceVehiclesTwelveMonthBeanList.size() + 1) + "") + "");
            nonServiceVehiclesTwelveMonthBeann1.setMonth(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
            nonServiceVehiclesTwelveMonthBeann1.setNonOperationalTotalAmount(nonServiceVehiclesTwelveMonthBeanList.get(nonServiceVehiclesTwelveMonthBeanList.size() - 1).getNonOperationalTotalAmount());
            nonServiceVehiclesTwelveMonthBeann1.setOperationalTotalAmount(nonServiceVehiclesTwelveMonthBeanList.get(nonServiceVehiclesTwelveMonthBeanList.size() - 1).getOperationalTotalAmount());
            nonServiceVehiclesTwelveMonthBeann1.setTransactionMonth(calendar.getTime());
            nonServiceVehiclesTwelveMonthBeanList.add(nonServiceVehiclesTwelveMonthBeann1);
        }
        // Get Objects of Data
        for (NonServiceVehiclesTwelveMonthBean nonServiceVehiclesTwelveMonthBean : nonServiceVehiclesTwelveMonthBeanList) {
            nonOperationalTotalList.add(nonServiceVehiclesTwelveMonthBean.getNonOperationalTotalAmount());
            operationalTotalList.add(nonServiceVehiclesTwelveMonthBean.getOperationalTotalAmount());
            monthList.add(nonServiceVehiclesTwelveMonthBean.getMonth());
        }
        Object[] nonOperationalTotalListArray = nonOperationalTotalList.toArray(new Object[nonOperationalTotalList.size()]);
        Object[] operationalTotalListArray = operationalTotalList.toArray(new Object[operationalTotalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final NonServiceVehiclesTwelveMonthLineChart nonServiceVehiclesTwelveMonthLineChart = new NonServiceVehiclesTwelveMonthLineChart();
        DCharts dBarChart = nonServiceVehiclesTwelveMonthLineChart.buildLineChart(nonOperationalTotalListArray, operationalTotalListArray, monthListArray, tickInterval, minTickValue, null); // null for Chart Title

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
