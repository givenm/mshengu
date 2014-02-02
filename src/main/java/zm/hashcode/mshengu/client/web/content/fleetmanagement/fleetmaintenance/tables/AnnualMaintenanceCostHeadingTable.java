/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Table;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class AnnualMaintenanceCostHeadingTable extends AnnualDataSuperTable {

    private final MshenguMain main;

    public AnnualMaintenanceCostHeadingTable(MshenguMain main) {
        super();
        this.main = main;
        // addContainerProperty(java.lang.Object propertyId, java.lang.Class<?> type, java.lang.Object defaultValue, java.lang.String columnHeader, Resource columnIcon, Table.Align columnAlignment)
        addContainerProperty("RowHead", String.class, null, "", null, null);
    }

    public void populateAnnualMaintenanceCostHeadingTable() {
//        // unHide Column Header
//        setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT);
//        this.setRowHeaderMode(Table.RowHeaderMode.ID);

        // Sort in Ascending Order of VehicleNumber happened in TruckService's  findAllServiceAndUtilityVehicles
        List<Truck> serviceUtilityTrucksList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();

//        int i = 1;
        for (Truck truck : serviceUtilityTrucksList) {
// addContainerProperty(java.lang.Object propertyId, java.lang.Class<?> type, java.lang.Object defaultValue, java.lang.String columnHeader, Resource columnIcon, Table.Align columnAlignment)
            addContainerProperty(truck.getVehicleNumber(), String.class, null, String.valueOf(truck.getVehicleNumber()), null, null);
//            i++;
        }
//
        this.removeAllItems();
        initializeRows(serviceUtilityTrucksList);

        super.resetColumnWidths();
        performTableFontSizeStyling();

    }

    public void initializeRows(List<Truck> serviceUtilityTrucksList) {
        String[] rowHeaders = {"Number", "Driver", "Driver ID", "Number Plate", "Reg. Year"};
        Object[] rowObjects = new Object[serviceUtilityTrucksList.size() + 1]; // Added one because of Column 0 is Row Header


//                System.out.println("\nPRINTING SERVICE n UTILITY TRUCKS");
//                System.out.println(truck.getVehicleNumber());

        for (int i = 0; i < rowHeaders.length; i++) { //
            int j = 1;
            for (Truck truck : serviceUtilityTrucksList) {

                switch (rowHeaders[i]) {  // switch(header)
                    case "Number":
                        rowObjects[j] = truck.getVehicleNumber();
                        break;
                    case "Driver":
                        rowObjects[j] = truck.getDriver().getFirstname() + " " + truck.getDriver().getLastname().charAt(0) + ".";
                        break;
                    case "Driver ID":
                        rowObjects[j] = truck.getDriver().getEmployeeDetails().getEmployeeNumber();
                        break;
                    case "Number Plate":
                        rowObjects[j] = truck.getNumberPlate();
                        break;
                    default:
                        rowObjects[j] = truck.getRegisterYear() + "";
                        break;
                }
                j++;
            }
            rowObjects[0] = rowHeaders[i];
            addItem(rowObjects, i);
//            j = 1;

        }
    }

    @Override
    public void performTableFontSizeStyling() {
        setCellStyleGenerator(new Table.CellStyleGenerator() {
            @Override
            public String getStyle(Table source, Object itemId, Object propertyId) {

                // Row header column which is hidden has a null for propertyId.
                // do this is you have to parse propertyId for column number
                if (propertyId == null) {
                    return "blue"; // Will not actually be visible
                }

                int row = ((Integer) itemId).intValue();
                String col = ((String) propertyId);
                // The first column which is OUR Row Header.
                if (col.equals("RowHead")) {
                    return "rowheader";
                }

//                if (row % 2 == 0) {
//                    return "blue";
//                } else {
//                    return "white";
//                }
                return "white";
            }
        });
    }
//    table.addColumnResizeListener(new Table.ColumnResizeListener(){
//    public void columnResize(ColumnResizeEvent event) {
//        // Get the new width of the resized column
//        int width = event.getCurrentWidth();
//
//        // Get the property ID of the resized column
//        String column = (String) event.getPropertyId();
//
//        // Do something with the information
//        table.setColumnFooter(column, String.valueOf(width) + "px");
//    }
//});
}
