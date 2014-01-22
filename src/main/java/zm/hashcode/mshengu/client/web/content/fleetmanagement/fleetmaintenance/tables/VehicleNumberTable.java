/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.data.Item;
import com.vaadin.ui.Table;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class VehicleNumberTable extends Table {

    private final MshenguMain main;

    public VehicleNumberTable(MshenguMain main) {
        this.main = main;
        setStyleName("panelTable");
        // Hide Column Header
        //        setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);

        addContainerProperty("", String.class, null);

        // Allow selecting items from the table.
        //        setNullSelectionAllowed(false);
        setSelectable(false);
        // Send changes in selection immediately to server.
//        setImmediate(false);
        // Alignments
        setColumnAlignment("", Align.CENTER);
        addItem(new Object[]{""}, new Integer("1"));

    }

    public void populateVehicleNumberTable(List<TotalMaintenanceSpendKmTraveled> spendByKmTravelledChartDataList) {
//        // Remove Table's ValueChangeListener, add contents to Table then Add  Table's ValueChangeListener
//        table.removeValueChangeListener((Property.ValueChangeListener) this);
//
        this.removeAllItems();
        Integer i = 0;
        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : spendByKmTravelledChartDataList) {
            addItem(new Object[]{totalMaintenanceSpendKmTraveled.getVehicleNumber()}, i);
            i++;
        }
//        table.addValueChangeListener((Property.ValueChangeListener) this);
        // NB NB NB Autofit cells after populating data in table
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
