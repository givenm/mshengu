/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Embedded;
import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendByVehicle;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.TotalMaintenanceSpendKmTraveled;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.utils.FleetMaintenanceUtil;

/**
 *
 * @author Colin
 */
public class RatingTable extends Table {

    private final MshenguMain main;
    private final FleetMaintenanceUtil fleetMaintenanceUtil = new FleetMaintenanceUtil();

    public RatingTable(MshenguMain main) {
        this.main = main;
        // Hide Column Header
        //        setColumnHeaderMode(Table.COLUMN_HEADER_MODE_HIDDEN);
        setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        setStyleName("panelTable");
        addContainerProperty("", Embedded.class, null);
        // Allow selecting items from the table.
//        setNullSelectionAllowed(false);
        setSelectable(false);
        // Send changes in selection immediately to server.
        setImmediate(false);
        // Alignments
        setColumnAlignment("", Table.Align.CENTER);
        addItem(new Object[]{new Embedded()}, new Integer("1"));

    }

    public void populateRatingTable(List<TotalMaintenanceSpendKmTraveled> totalMaintenanceSpendKmTraveledList) {
//        // Remove Table's ValueChangeListener, add contents to Table then Add  Table's ValueChangeListener
//        table.removeValueChangeListener((Property.ValueChangeListener) this);
//
        this.removeAllItems();
        initializeRows(totalMaintenanceSpendKmTraveledList);

        // NB NB The order of Trucks in the Chart has been flipped bc of the nature of the chart.
        // in ArrayList, the index of zero begins from bottom in the chart.
        Integer i = totalMaintenanceSpendKmTraveledList.size() - 1;

        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : totalMaintenanceSpendKmTraveledList) {
            getItem(i).getItemProperty("").setValue(fleetMaintenanceUtil.determineFlag(totalMaintenanceSpendKmTraveled.getRandPerKilometre()));
//            addItem(new Object[]{fleetMaintenanceUtil.determineFlag(totalMaintenanceSpendKmTraveled.getRandPerKilometre())}, i);
            i--;
        }
//        table.addValueChangeListener((Property.ValueChangeListener) this); // TotalMaintenanceSpendKmTraveled spendByKmTravelledChartDataList
//        setSizeFull();
        resetColumnWidths();
    }

    public void initializeRows(List<TotalMaintenanceSpendKmTraveled> spendByKmTravelledChartDataList) {
        int i = 0;
        for (TotalMaintenanceSpendKmTraveled totalMaintenanceSpendKmTraveled : spendByKmTravelledChartDataList) {
            addItem(new Object[]{new Embedded()}, i);
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
