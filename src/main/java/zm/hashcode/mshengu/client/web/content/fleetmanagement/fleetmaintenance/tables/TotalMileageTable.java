/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceMileage;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;

/**
 *
 * @author Colin
 */
public class TotalMileageTable extends Table {

    private final MshenguMain main;

    public TotalMileageTable(MshenguMain main) {
        this.main = main;
        // Hide Column Header
        //        setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        setStyleName("panelTable");

        addContainerProperty("", Integer.class, null);
        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
        setSelectable(false);
        // Send changes in selection immediately to server.
//        setImmediate(false);
        // Alignments
        setColumnAlignment("", Table.Align.RIGHT);
        addItem(new Object[]{new Integer("1")}, new Integer("1"));

    }

    public void populateTotalMileageTable(List<TotalMaintenanceSpendKmTraveled> totalMaintenanceSpendKmTraveledList, List<TotalMaintenanceMileage> totalMaintenanceMileageList) {
//        // Remove Table's ValueChangeListener, add contents to Table then Add  Table's ValueChangeListener
//        table.removeValueChangeListener((Property.ValueChangeListener) this);
//
        this.removeAllItems();
        Integer i = 0;
        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : totalMaintenanceSpendKmTraveledList) {
            for (TotalMaintenanceMileage totalMaintenanceMileage : totalMaintenanceMileageList) {
                if (totalMaintenanceSpendKmTraveled.getVehicleNumber().equals(totalMaintenanceMileage.getVehicleNumber())) {
                    addItem(new Object[]{totalMaintenanceMileage.getTruckMileagetotal()}, i);
                    i++;
                }
            }
        }
//        table.addValueChangeListener((Property.ValueChangeListener) this);
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
