/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.charts.actualcharts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.app.util.charts.BarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.FuelSpendMonthlyCostBean;

/**
 *
 * @author Colin
 */
public class TotalFleetFuelSpendBarChart implements Serializable {

    public DCharts createChart(List<FuelSpendMonthlyCostBean> fuelSpendMonthlyCostBeanList, BigDecimal grandTotal) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
        String label = "Total Fuel Spend";
        String title = "Total Fuel Spend for the last " + fuelSpendMonthlyCostBeanList.size() + "M: " + grandTotal;
        float tickInterval = Float.parseFloat("350000");
        Object minTickValue = 0;
        BigDecimal highestTotal = BigDecimal.ZERO;
        for (FuelSpendMonthlyCostBean FuelSpendMonthlyCostBean : fuelSpendMonthlyCostBeanList) {
            if (FuelSpendMonthlyCostBean.getMonthlyAmountSpend().compareTo(highestTotal) > 0) {
                highestTotal = FuelSpendMonthlyCostBean.getMonthlyAmountSpend();
            }
        }

        if (highestTotal.compareTo(new BigDecimal("350000")) < 0) {
            tickInterval = Float.parseFloat("50000");
//            minTickValue=5000;
        } else if (highestTotal.compareTo(new BigDecimal("600000")) < 0) {
            tickInterval = Float.parseFloat("100000");
//             minTickValue=10000;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Get Objects of Data
        for (FuelSpendMonthlyCostBean fuelSpendMonthlyCostBean : fuelSpendMonthlyCostBeanList) {
            totalList.add(fuelSpendMonthlyCostBean.getMonthlyAmountSpend());
            // Truncate the Names
            monthList.add(fuelSpendMonthlyCostBean.getMonth());
            // Add Percentage for PIE
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final BarChart barChart = new BarChart();
        DCharts dBarChart = barChart.buildBarChart(totalListArray, monthListArray, tickInterval, label, minTickValue);

        dBarChart.getOptions().getTitle().setText(title);
        dBarChart.setWidth("600px");
        dBarChart.setHeight("300px");
        dBarChart.show();

        // USEFUL Statements
//        dBarChart.setMarginBottom(10);
//        dBarChart.setMarginLeft(10);
//        dBarChart.setMarginRight(10);
//        dBarChart.setMarginTop(-3);
//        dBarChart.getOptions().setTitle("");

        return dBarChart;
    }
}
