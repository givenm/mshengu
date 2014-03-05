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
import zm.hashcode.mshengu.app.util.charts.ServiceFleetEfficiencyLineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.ServiceFleetOneMonthlyEfficiencyBean;

/**
 *
 * @author Colin
 */
public class OneMonthEfficiencyLineChart implements Serializable {

    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public DCharts createChart(List<ServiceFleetOneMonthlyEfficiencyBean> serviceFleetOneMonthlyEfficiencyBeanList) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
//        String label = "Service Vehicles - Monthly Mileages";
//        String formattedGrandTotalMileage = df.format(Double.parseDouble(grandTotalMileage.toString()));
//        String title = "Total Mileage for last " + serviceFleetOneMonthlyEfficiencyBeanList.size() + " Month(s):    " + formattedGrandTotalMileage + " Km";
        float tickInterval = Float.parseFloat("2.00");
        Object minTickValue = 0.00;
        BigDecimal highestEfficiencyValue = BigDecimal.ZERO;
        // finding the highest Mileage figure
        for (ServiceFleetOneMonthlyEfficiencyBean serviceFleetOneMonthlyEfficiencyBean : serviceFleetOneMonthlyEfficiencyBeanList) {
            if (serviceFleetOneMonthlyEfficiencyBean.getMonthlyEfficiencyValue().compareTo(highestEfficiencyValue) > 0) {
                highestEfficiencyValue = serviceFleetOneMonthlyEfficiencyBean.getMonthlyEfficiencyValue();
            }
        }

        if (highestEfficiencyValue.compareTo(new BigDecimal("14.00")) < 0) {
            tickInterval = Float.parseFloat("2.50");
//            minTickValue = 0;
        } else if (highestEfficiencyValue.compareTo(new BigDecimal("12.00")) < 0) {
            tickInterval = Float.parseFloat("2.00");
//            minTickValue = 0;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Get Objects of Data
        for (ServiceFleetOneMonthlyEfficiencyBean serviceFleetOneMonthlyEfficiencyBean : serviceFleetOneMonthlyEfficiencyBeanList) {
            totalList.add(serviceFleetOneMonthlyEfficiencyBean.getMonthlyEfficiencyValue());
            monthList.add(serviceFleetOneMonthlyEfficiencyBean.getMonth());
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final ServiceFleetEfficiencyLineChart serviceFleetEfficiencyLineChart = new ServiceFleetEfficiencyLineChart();
        DCharts dLineChart = serviceFleetEfficiencyLineChart.buildLineChart(totalListArray, monthListArray, tickInterval, minTickValue, null);

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
}
