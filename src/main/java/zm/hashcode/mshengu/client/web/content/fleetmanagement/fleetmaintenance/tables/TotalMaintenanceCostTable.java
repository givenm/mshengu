/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Table;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;

/**
 *
 * @author Colin
 */
public class TotalMaintenanceCostTable extends Table {

    private final MshenguMain main;
    // Use a specific locale for formatting decimal numbers
    final Locale locale = new Locale("za", "ZA");
    // Format a decimal value for a specific locale
    final DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));

    public TotalMaintenanceCostTable(MshenguMain main) {
        this.main = main;
        // Hide Column Header
        //        setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        setStyleName("panelTable");
        addContainerProperty("", String.class, null);
        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
        setSelectable(false);
//        // Send changes in selection immediately to server.
//        setImmediate(false);
        // Alignments
        setColumnAlignment("", Table.Align.RIGHT);
        addItem(new Object[]{"0.00"}, new Integer("1"));

    }

    public void populateTotalMaintenanceCostTable(List<TotalMaintenanceSpendKmTraveled> totalMaintenanceSpendKmTraveledList, List<TotalMaintenanceSpendByVehicle> totalMaintenanceSpendByVehicleList) {
        this.removeAllItems();
        initializeRows(totalMaintenanceSpendKmTraveledList);

        // NB NB The order of Trucks in the Chart has been flipped bc of the nature of the chart.
        // in ArrayList, the index of zero begins from bottom in the chart.
        Integer i = totalMaintenanceSpendKmTraveledList.size() - 1;

        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : totalMaintenanceSpendKmTraveledList) {
            for (TotalMaintenanceSpendByVehicle totalMaintenanceSpendByVehicle : totalMaintenanceSpendByVehicleList) {
                if (totalMaintenanceSpendKmTraveled.getVehicleNumber().equals(totalMaintenanceSpendByVehicle.getVehicleNumber())) {
                    getItem(i).getItemProperty("").setValue(totalMaintenanceSpendByVehicle.getTotal().compareTo(BigDecimal.ZERO) == 0 ? "0.00" : df.format(Double.parseDouble(totalMaintenanceSpendByVehicle.getTotal().toString())));
//                    addItem(new Object[]{totalMaintenanceSpendByVehicle.getTotal().compareTo(BigDecimal.ZERO) == 0 ? "0.00" : df.format(Double.parseDouble(totalMaintenanceSpendByVehicle.getTotal().toString()))}, i);
                    break;
                }
            }
            i--; // counter of totalMaintenanceSpendKmTraveledList to match Ids created by method initializeRows
        }

        resetColumnWidths();
    }

    public void initializeRows(List<TotalMaintenanceSpendKmTraveled> totalMaintenanceSpendKmTraveledList) {
        int i = 0;
        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : totalMaintenanceSpendKmTraveledList) {
            addItem(new Object[]{"0.00"}, i);
            i++;
        }
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
