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
import zm.hashcode.mshengu.app.util.charts.PieChart;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendBySupplier;

/**
 *
 * @author Colin
 */
public class MaintenanceSpendBySupplierPieChart implements Serializable {

    public DCharts createChart(List<TotalMaintenanceSpendBySupplier> totalMaintenanceSpendBySupplierList, String chartPeriod) {
        List<Object> serviceProviderNameList = new ArrayList<>();
        List<Object> percentageList = new ArrayList<>();
        BigDecimal sumOfPercentage = BigDecimal.ZERO;

        // Get Objects of Data
        for (TotalMaintenanceSpendBySupplier totalMaintenanceSpendBySupplier : totalMaintenanceSpendBySupplierList) {
            serviceProviderNameList.add(truncate(totalMaintenanceSpendBySupplier.getSupplierName(), 14));
            percentageList.add(totalMaintenanceSpendBySupplier.getSpendPercentage());
            sumOfPercentage = sumOfPercentage.add(totalMaintenanceSpendBySupplier.getSpendPercentage());
        }

        Object[] serviceProviderNameListArray = serviceProviderNameList.toArray(new Object[serviceProviderNameList.size()]);
        Object[] percentageListArray = percentageList.toArray(new Object[percentageList.size()]);

        final PieChart pieChart = new PieChart();
        DCharts dPieChart = pieChart.buildPieChart(serviceProviderNameListArray, percentageListArray, sumOfPercentage);
        dPieChart.setWidth("380px");
        dPieChart.setHeight("300px");
        dPieChart.setMarginLeft(0);
        dPieChart.getOptions().getLegend().setFontSize("10");

        dPieChart.show();

        return dPieChart;
    }

    public static String truncate(Object value, int length) {
        String strValue = value.toString();
        if (strValue != null && strValue.length() > length) {
            strValue = strValue.substring(0, length);
        }
        return strValue;
    }
}
