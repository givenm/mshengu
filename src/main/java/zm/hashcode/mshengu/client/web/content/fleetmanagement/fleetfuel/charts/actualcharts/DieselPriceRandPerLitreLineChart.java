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
import zm.hashcode.mshengu.app.util.charts.LineChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.FuelSpendMonthlyCostBean;

/**
 *
 * @author Colin
 */
public class DieselPriceRandPerLitreLineChart implements Serializable {

    public DCharts createChart(List<FuelSpendMonthlyCostBean> fuelSpendMonthlyCostBeanList /*, BigDecimal grandTotal*/) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
//        String label = "Diesel Price Rand/Litre";
//        String title = "Total Fuel Spend for the last " + fuelSpendMonthlyCostBeanList.size() + " M: " + grandTotal;
        float tickInterval = Float.parseFloat(".50");
        Object minTickValue = 0.00;
        boolean isZeroFound = false;
        BigDecimal highestRandPerLitre = BigDecimal.ZERO;
        for (FuelSpendMonthlyCostBean fuelSpendMonthlyCostBean : fuelSpendMonthlyCostBeanList) {
            if (fuelSpendMonthlyCostBean.getMonthRandPerLiter().compareTo(highestRandPerLitre) > 0) {
                highestRandPerLitre = fuelSpendMonthlyCostBean.getMonthRandPerLiter();
//                System.out.println("R/Ltr for " + fuelSpendMonthlyCostBean.getMonth() + ": " + fuelSpendMonthlyCostBean.getMonthRandPerLiter());
            }
            if (fuelSpendMonthlyCostBean.getMonthRandPerLiter().compareTo(BigDecimal.ZERO) == 0) {
                isZeroFound = true;
            }
        }

        if (highestRandPerLitre.compareTo(new BigDecimal("15")) < 0) {
            tickInterval = Float.parseFloat(".50");
            minTickValue = 10.50;
        } else if (highestRandPerLitre.compareTo(new BigDecimal("20")) < 0) {
            tickInterval = Float.parseFloat("2.50");
            minTickValue = 12.50;
        }
        if (isZeroFound) {
            tickInterval = Float.parseFloat("1.50");
            minTickValue = 0.00;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> monthList = new ArrayList<>();

        // Get Objects of Data
        for (FuelSpendMonthlyCostBean fuelSpendMonthlyCostBean : fuelSpendMonthlyCostBeanList) {
            totalList.add(fuelSpendMonthlyCostBean.getMonthRandPerLiter());
            // Truncate the Names
            monthList.add(fuelSpendMonthlyCostBean.getMonth());
////            monthList.add(dateTimeFormatHelper.);

        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] monthListArray = monthList.toArray(new Object[monthList.size()]);

        final LineChart lineChart = new LineChart();
        DCharts dBarChart = lineChart.buildLineChart(totalListArray, monthListArray, tickInterval, minTickValue, null); // null for Chart Title

//        dBarChart.getOptions().getTitle().setText(title);
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
