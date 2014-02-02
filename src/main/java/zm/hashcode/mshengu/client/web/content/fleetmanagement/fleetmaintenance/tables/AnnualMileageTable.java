/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.tables;

import com.vaadin.ui.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import zm.hashcode.mshengu.app.facade.fleet.TruckFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.client.web.MshenguMain;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlyMileageData;
import zm.hashcode.mshengu.client.web.content.fleetmanagement.fleetmaintenance.models.MonthlySpendData;
import zm.hashcode.mshengu.domain.fleet.Truck;

/**
 *
 * @author Colin
 */
public class AnnualMileageTable extends AnnualDataSuperTable {

    private final MshenguMain main;
    private DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
//    // Use a specific locale for formatting decimal numbers
//    final Locale locale = new Locale("za", "ZA");
//    // Format a decimal value for a specific locale
//    DecimalFormat df = new DecimalFormat("###,###,##0.00", new DecimalFormatSymbols(locale));
    private static int[] truckTotalArray;
    public static int monthTotal = 0;
    public static int grandTotal = 0;
    public static String[] truckVehicleNumberArray;
//    private static BigDecimal randPerKm = BigDecimal.ZERO;
    private static int annualDataMonthCount = 0;

    public AnnualMileageTable(MshenguMain main) {
        super();
        this.main = main;
        // create FIRST COLUMN in the Table
        // addContainerProperty(java.lang.Object propertyId, java.lang.Class<?> type, java.lang.Object defaultValue, java.lang.String columnHeader, Resource columnIcon, Table.Align columnAlignment)
        addContainerProperty("RowHead", String.class, null, "", null, Table.Align.RIGHT); // SHORT DATE e.g. Jun-2013
    }

    public void populateAnnualMileageTable(List<MonthlyMileageData> totalMonthlyMileageDataList, int annualDataMonthCount, Date startDate) {
        this.annualDataMonthCount = annualDataMonthCount;
        // Sort in Ascending Order of VehicleNumber happened in TruckService's  findAllServiceAndUtilityVehicles
        List<Truck> serviceUtilityTrucksList = TruckFacade.getTruckService().findAllServiceAndUtilityVehicles();
        int columnSize = serviceUtilityTrucksList.size() + 2;
        int rowSize = annualDataMonthCount + 1;

        // DYNAMICALLY create the other Table columns
        createTruckColumns(serviceUtilityTrucksList, serviceUtilityTrucksList.size());

        this.removeAllItems();
        populateTable(totalMonthlyMileageDataList, rowSize, columnSize, serviceUtilityTrucksList, startDate);

        /*
         super.resetColumnWidths();
         performTableFontSizeStyling();
         */
    }

    public void createTruckColumns(List<Truck> serviceUtilityTrucksList, int truckListSize) {
        for (int i = 1; i <= truckListSize; i++) {
            // COLUMNS in between First and Last Columns
//            addContainerProperty(String.valueOf(i), String.class, "0.00", serviceUtilityTrucksList.get(i - 1).getVehicleNumber(), null, Table.Align.RIGHT); // BigDecimal
            addContainerProperty(serviceUtilityTrucksList.get(i - 1).getVehicleNumber(), Integer.class, 0, serviceUtilityTrucksList.get(i - 1).getVehicleNumber(), null, Table.Align.RIGHT); // BigDecimal
        }
        // create LAST COLUMN in the Table
        addContainerProperty("Total", Integer.class, 0, "Total", null, Table.Align.RIGHT);
    }

    public void populateTable(List<MonthlyMileageData> totalMonthlyMileageDataList, int rowSize, int columnSize, List<Truck> serviceUtilityTrucksList, Date startDate) {
//        // unHide Column Header
//        setColumnHeaderMode(Table.ColumnHeaderMode.EXPLICIT);
//        this.setRowHeaderMode(Table.RowHeaderMode.ID);

        truckTotalArray = new int[columnSize];
        truckVehicleNumberArray = new String[columnSize]; //columnSize = serviceUtilityTrucksList.size()+2
        // initialize above arrays
        initializeTruckTotalArray();
        initializeTruckVehicleNumberArray(serviceUtilityTrucksList, columnSize);

        createRows(rowSize);

        // INCREMENT the Date. Keep track of Months without Data and enter Zero Rows for them
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
//        calendar.add(Calendar.MONTH, 1);

//        System.out.println("\nIn Populate MILEAGE Table Method the ff data was found");
        int i = 1; // Table row counter, Row 0 is Total Row
        Date transactDate = totalMonthlyMileageDataList.get(0).getTransactDate();

        for (MonthlyMileageData monthlyMileageData : totalMonthlyMileageDataList) {
            int x = 0;
//            System.out.println(monthlyMileageData.getVehicleNumber() + " - " + monthlyMileageData.getTransactionDate() + " - " + monthlyMileageData.getTruckMonthlyMileageTotal());

            // NB The last Record in the totalMonthlyMileageDataList will not enter this IF Statement
            if (dateTimeFormatHelper.resetTimeAndMonthStart(monthlyMileageData.getTransactDate()).compareTo(dateTimeFormatHelper.resetTimeAndMonthStart(transactDate)) > 0) {
                // INCREMENT the Date. Keep track of Months without Data and enter Zero Rows for them
                if (dateTimeFormatHelper.resetTimeAndMonthStart(transactDate).after(dateTimeFormatHelper.resetTimeAndMonthStart(calendar.getTime()))) {
                    getItem(i).getItemProperty("RowHead").setValue(dateTimeFormatHelper.getMonthYearMonthAsMediumString(calendar.getTime().toString()));
                    getItem(i).getItemProperty("Total").setValue(0);

                    // truckTotal must be base on Truck Vehicle Number
                    // Total, msv-01, msv-02, ... muv-04, GrandTotal // MSV-01 is 1
                    for (int z = 0; z < truckVehicleNumberArray.length; z++) {
                        getItem(i).getItemProperty(monthlyMileageData.getVehicleNumber()).setValue(0);
                    }



                } else {
                    getItem(i).getItemProperty("RowHead").setValue(totalMonthlyMileageDataList.get(totalMonthlyMileageDataList.indexOf(monthlyMileageData) - 1).getTransactionDate());
                    getItem(i).getItemProperty("Total").setValue(monthTotal);
                    // Reset
                    monthTotal = 0;
                    transactDate = monthlyMileageData.getTransactDate();
                    i++;
                }
                calendar.add(Calendar.MONTH, 1);
            }
//
            if (monthlyMileageData.getTruckMonthlyMileageTotal() > 0) {
                getItem(i).getItemProperty(monthlyMileageData.getVehicleNumber()).setValue(monthlyMileageData.getTruckMonthlyMileageTotal());
                // truckTotal must be base on Truck Vehicle Number
                // Total, msv-01, msv-02, ... muv-04, GrandTotal // MSV-01 is 1
                for (int z = 0; z < truckVehicleNumberArray.length; z++) {
                    if (truckVehicleNumberArray[z + 1].equals(monthlyMileageData.getVehicleNumber())) {
                        x = z + 1;
                        break;
                    }
                }
                truckTotalArray[x] += monthlyMileageData.getTruckMonthlyMileageTotal();
                monthTotal += monthlyMileageData.getTruckMonthlyMileageTotal();
                grandTotal += monthlyMileageData.getTruckMonthlyMileageTotal();
            }

            // Test if this is the last record in the List totalMonthlyMileageDataList
            if (totalMonthlyMileageDataList.indexOf(monthlyMileageData) == totalMonthlyMileageDataList.size() - 1) {
                getItem(i).getItemProperty("RowHead").setValue(monthlyMileageData.getTransactionDate());
                getItem(i).getItemProperty("Total").setValue(monthTotal);
//                // Reset
//               monthTotal += monthlyMileageData.getTruckMonthlyMileageTotal();
                transactDate = monthlyMileageData.getTransactDate();
//                i++;
            }






            ///////////////////////////////////////////////////////
        }

        if (i < annualDataMonthCount) {
            int monthsToGo = annualDataMonthCount - i;
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(transactDate);
            for (int k = 0; k < monthsToGo; k++) {
                i++;
                startCalendar.add(Calendar.MONTH, 1);
                String month = dateTimeFormatHelper.getMonthYearMonthAsMediumString(startCalendar.getTime().toString());
                getItem(i).getItemProperty("RowHead").setValue(month);
            }
        }

        truckTotalArray[truckTotalArray.length - 1] = grandTotal;

        // Add TOTAL Row
        getItem(0).getItemProperty("RowHead").setValue("Total");
        for (int j = 1; j < truckTotalArray.length; j++) {
            getItem(0).getItemProperty(truckVehicleNumberArray[j]).setValue(truckTotalArray[j]); // serviceUtilityTrucksList.get(j-1).getVehicleNumber()
        }

        performTableFontSizeStyling();
    }

    public void createRows(int rowSize) {
        for (int j = 0; j < rowSize; j++) {
            this.addItem(j); // Add items begins numbering Row Id from 0 ...
        }
    }

    public void initializeTruckTotalArray() {
        for (int i = 0; i < truckTotalArray.length; i++) {
            truckTotalArray[i] = 0;
        }
    }

    public void initializeTruckVehicleNumberArray(List<Truck> serviceUtilityTrucksList, int columnSize) {
        for (int k = 1; k < columnSize - 1; k++) {
            truckVehicleNumberArray[k] = serviceUtilityTrucksList.get(k - 1).getVehicleNumber();
        }
        truckVehicleNumberArray[0] = "RowHead";
        truckVehicleNumberArray[columnSize - 1] = "Total";
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

                String style = null;
                //
                if (row % 2 == 0) {
                    style = "blue";
                } else if ((row % 2 != 0)) {
                    style = "white";
                }
                if (row == 0) {
                    style = "monthTotalBold";
                }

                // The first column which is OUR Row Header.
                if (col.equals("RowHead")) {
                    style = "rowheader";
                } else if (col.equals("Total")) {
                    style = "monthTotalBold";// Bold truck n Month total Row n Column
                }

                return style;
            }
        });
    }
}
