/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetfuel.model.monthlyfuelexpense.MonthlyFuelExpenseBean;

/**
 *
 * @author Colin
 */
public class MonthlyFuelExpenseTable extends Table {

    private final MshenguMain main;
    private static BigDecimal nonOperationalTwelveMonthTotal = BigDecimal.ZERO;
    private static BigDecimal operationalTwelveMonthTotal = BigDecimal.ZERO;
    private static BigDecimal serviceTwelveMonthTotal = BigDecimal.ZERO;
    private static BigDecimal grandTwelveMonthTotal = BigDecimal.ZERO;
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public MonthlyFuelExpenseTable(MshenguMain main) {
        this.main = main;
        // Send changes in selection immediately to server.
        setImmediate(true);
        setStyleName("monthlyFuelExpenseTable");
        ////        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
//        setSelectable(false);
        addContainerProperty("Month", String.class, null);
        addContainerProperty("Non-Operational", String.class, null);
        addContainerProperty("Operational", String.class, null);
        addContainerProperty("Service Fleet", String.class, null);
        addContainerProperty("Total", String.class, null);

    }

    public void createTable(List<MonthlyFuelExpenseBean> monthlyFuelExpenseBeanList) {
        int counter = 0;
        this.removeAllItems();
        for (MonthlyFuelExpenseBean monthlyFuelExpenseBean : monthlyFuelExpenseBeanList) {
            counter++;
            populateDailyInputTable(monthlyFuelExpenseBean, counter);
        }
        addTotalRow();
        resetColumnWidths();
        performTableCellStyling();

        // Alignments
        setColumnAlignment("Month", Table.Align.RIGHT);
        setColumnAlignment("Non-Operational", Table.Align.RIGHT);
        setColumnAlignment("Operational", Table.Align.RIGHT);
        setColumnAlignment("Service Fleet", Table.Align.RIGHT);
        setColumnAlignment("Total", Table.Align.RIGHT);

        // RESET
        nonOperationalTwelveMonthTotal = BigDecimal.ZERO;
        operationalTwelveMonthTotal = BigDecimal.ZERO;
        serviceTwelveMonthTotal = BigDecimal.ZERO;
        grandTwelveMonthTotal = BigDecimal.ZERO;
    }

    private void populateDailyInputTable(MonthlyFuelExpenseBean monthlyFuelExpenseBean, int counter) {
        addItem(new Object[]{
            monthlyFuelExpenseBean.getMonth(),
            df.format(Double.parseDouble(monthlyFuelExpenseBean.getNonOperationalTrucksFuelTotal().toString())),
            df.format(Double.parseDouble(monthlyFuelExpenseBean.getOperationalTrucksFuelTotal().toString())),
            df.format(Double.parseDouble(monthlyFuelExpenseBean.getServiceTrucksFuelTotal().toString())),
            df.format(Double.parseDouble(monthlyFuelExpenseBean.getAllTrucksFuelTotal().toString())), //                operatingCost.getOilCost() == null ? "" : df.format(Double.parseDouble(operatingCost.getOilCost().toString()))
        }, counter);
        //  Totalings
        nonOperationalTwelveMonthTotal = nonOperationalTwelveMonthTotal.add(monthlyFuelExpenseBean.getNonOperationalTrucksFuelTotal());
        operationalTwelveMonthTotal = operationalTwelveMonthTotal.add(monthlyFuelExpenseBean.getOperationalTrucksFuelTotal());
        serviceTwelveMonthTotal = serviceTwelveMonthTotal.add(monthlyFuelExpenseBean.getServiceTrucksFuelTotal());
        grandTwelveMonthTotal = grandTwelveMonthTotal.add(monthlyFuelExpenseBean.getAllTrucksFuelTotal());
    }

    private void addTotalRow() {
        addItem(new Object[]{
            "12M Total",
            df.format(Double.parseDouble(nonOperationalTwelveMonthTotal.toString())),
            df.format(Double.parseDouble(operationalTwelveMonthTotal.toString())),
            df.format(Double.parseDouble(serviceTwelveMonthTotal.toString())),
            df.format(Double.parseDouble(grandTwelveMonthTotal.toString())), //                operatingCost.getOilCost() == null ? "" : df.format(Double.parseDouble(operatingCost.getOilCost().toString()))
        }, 0);
    }

    private void performTableCellStyling() {
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {
//                if (propertyId != null) {
//                    return null;
//                }
                int row = ((Integer) itemId).intValue();
                if (row == 0) {
                    return "monthTotalBold"; // yellowrow
                } else if (row % 2 == 0) {
                    return "blue";
                } else {//if (row % 2 != 0)
                    return "white";
                }
            }
        });
    }

    public void resetColumnWidths() {
        alwaysRecalculateColumnWidths = true;
        for (Object object : this.getVisibleColumns()) {
            setColumnWidth(object, -1);
        }
    }
}
