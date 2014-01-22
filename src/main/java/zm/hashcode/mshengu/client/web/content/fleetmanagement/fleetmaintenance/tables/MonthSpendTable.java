/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil;

/**
 *
 * @author Colin
 */
public class MonthSpendTable extends Table {

    private final MshenguMain main;
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("#0.00", new DecimalFormatSymbols(locale));
    private final FleetMaintenanceUtil fleetMaintenanceUtil = new FleetMaintenanceUtil();

    public MonthSpendTable(MshenguMain main) {
        this.main = main;

        // Hide Column Header
        //        setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        setStyleName("panelTable");
        addContainerProperty("", String.class, null);
        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
        setSelectable(false);
        // Send changes in selection immediately to server.
        setImmediate(false);
        // Alignments
        setColumnAlignment("", Table.Align.RIGHT);
        addItem(new Object[]{"0.00"}, new Integer("1"));

    }

    public void populateMonthSpendTable(List<TotalMaintenanceSpendKmTraveled> totalMaintenanceSpendKmTraveledList) {
//        // Remove Table's ValueChangeListener, add contents to Table then Add  Table's ValueChangeListener
//        table.removeValueChangeListener((Property.ValueChangeListener) this);
//
        this.removeAllItems();
        Integer i = 0;
        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : totalMaintenanceSpendKmTraveledList) {
            addItem(new Object[]{totalMaintenanceSpendKmTraveled.getRandPerKilometre()}, i);
            addItem(new Object[]{totalMaintenanceSpendKmTraveled.getRandPerKilometre().compareTo(BigDecimal.ZERO) == 0 ? "0.00" : df.format(Double.parseDouble(totalMaintenanceSpendKmTraveled.getRandPerKilometre().toString()))}, i);
            i++;
        }
//        table.addValueChangeListener((Property.ValueChangeListener) this); // TotalMaintenanceSpendKmTraveled spendByKmTravelledChartDataList
//        setSizeFull();
        resetColumnWidths();
    }

    public void performTableCellStyling() {
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {

                int row = ((Integer) itemId).intValue();

                if (row % 2 == 0) {
                    return "blueCellHeight";
                } else {
                    return "whiteCellHeight";
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