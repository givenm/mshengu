/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.charts.actualcharts;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.dussan.vaadin.dcharts.DCharts;
import zm.hashcode.mshengu.app.util.charts.BarChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendBySupplier;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierBarChart implements Serializable {

    public DCharts createChart(List<TotalMaintenanceSpendBySupplier> totalMaintenanceSpendBySupplierList, String chartPeriod) {
        // Deciding Ticks e.g. .5, 1, 1.5, 2, 2.5 ... etc
        String label = "Spend By Supplier";
        float tickInterval = Float.parseFloat("100000");
        Object minTickValue = 0;
        BigDecimal highestTotal = BigDecimal.ZERO;
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
            if (totalMaintenanceSpendBySupplier.getTotal().compareTo(highestTotal) > 0) {
                highestTotal = totalMaintenanceSpendBySupplier.getTotal();
            }
        }

        if (highestTotal.compareTo(new BigDecimal("300000")) < 0) {
            tickInterval = Float.parseFloat("50000");
//             minTickValue = 5000;
        } else if (highestTotal.compareTo(new BigDecimal("600000")) < 0) {
            tickInterval = Float.parseFloat("100000");
//            minTickValue = 10000;
        }

        List<Object> totalList = new ArrayList<>();
        List<Object> serviceProviderNameList = new ArrayList<>();

        // Get Objects of Data
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
            totalList.add(totalMaintenanceSpendBySupplier.getTotal());
            // Truncate the Names
            serviceProviderNameList.add(truncateNames(totalMaintenanceSpendBySupplier.getSupplierName()));
            // Add Percentage for PIE
        }
        Object[] totalListArray = totalList.toArray(new Object[totalList.size()]);
        Object[] serviceProviderNameListArray = serviceProviderNameList.toArray(new Object[serviceProviderNameList.size()]);

        final BarChart barChart = new BarChart();
        DCharts dBarChart = barChart.buildBarChart(totalListArray, serviceProviderNameListArray, tickInterval, label, minTickValue, null);
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

    public static String truncate(String value, int length) {
        if (value != null && value.length() > length) {
            value = value.substring(0, length);
        }
        return value;
    }

    public static String truncateNames(String allNames) {
        if (allNames.length() < 11) {
            return allNames;
        } else {
            String[] stringArray = allNames.split(" "); // String[] stringArray = allNames.split(" ", 2);
            if (stringArray[0].equals(" ")) {
                char aChar = stringArray[2].charAt(0);
                return stringArray[1] + " " + aChar + ".";
            } else {
                char aChar = stringArray[1].charAt(0);
                return stringArray[0] + " " + aChar + ".";
            }
        }
    }
}
