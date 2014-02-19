/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecostbysupplier;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.procurement.MaintenanceSpendBySupplier;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProvider;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.procurement.MaintenanceSpendBySupplierService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Colin
 */
public class SpendBySupplierMUV_01 extends AppTest {

    @Autowired
    private MaintenanceSpendBySupplierService maintenanceSpendBySupplierService;
    @Autowired
    private ServiceProviderService serviceProviderService;
    @Autowired
    private TruckService truckService; //
    private static Truck truck = null;
    private static ServiceProvider serviceProvider = null;

//    @Test
    public void testSheet() {
        try {
            serviceProviderService = ctx.getBean(ServiceProviderService.class);
            truckService = ctx.getBean(TruckService.class);
            maintenanceSpendBySupplierService = ctx.getBean(MaintenanceSpendBySupplierService.class);
            SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
            simpleFormat.applyPattern("dd-MMM-yyyy");
            // Open the Excel File
            URL url = this.getClass().getResource("/setupfleet/TotalMaintenanceSpendSupplier.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            // Process each Sheet at a time. API FAILS to move to next sheets
            Date datee = null;
            String truckId = null;
            String serviceProviderId = null;
            BigDecimal maintenanceCost;
            HSSFSheet worksheet = workbook.getSheetAt(11); // WorkSheet ????? Change this number for each Sheet (Sheet Number -1)
            String vehicleNumber = worksheet.getRow(0).getCell(3).getStringCellValue(); // Row 0 Column 3 is the Vehicle Number cell

            // Get the Truck based of the VEHICLEnUMBER
            try {
                truck = truckService.findByVehicleNumber(vehicleNumber);
                truckId = truck.getId();
            } catch (NullPointerException ex) {
                System.out.println("\n\nNo Truck Found");
            }

            if (truck != null) {
                // iterated through all the rows
                System.out.println("\n\n" + "Begin processing Sheet: " + vehicleNumber);
                for (int i = 3; i <= 59; i++) { // For each row, from Row 4 to 60 e.g. Row 1 is index @(0)  ????? Change this number (i <= ?) for each Sheet
                    // we will navigate Column A to B. C is read below
                    // Starts @Row 4
                    String vendorNumber = worksheet.getRow(i).getCell(2).getStringCellValue(); // Column 3(C) is the Vehicle Number cell
                    // Get sUPPLIERid (ServiceProviderId)// Starts @Row 4
                    try {
                        serviceProvider = serviceProviderService.findByVendorNumber(vendorNumber);
                        serviceProviderId = serviceProvider.getId();
                    } catch (NullPointerException ex) {
                        System.out.println("\n\nNo ServiceProvider Found");
                    }

                    if (serviceProviderId != null) {
                        try {
                            datee = simpleFormat.parse(worksheet.getRow(i).getCell(0).toString().trim());  // Column 3(A) is the Date cell
                        } catch (ParseException ex) {
                            Logger.getLogger(SpendBySupplierMSV_01.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        maintenanceCost = new BigDecimal(worksheet.getRow(i).getCell(1).getNumericCellValue());// Column 3(C) is the MaintenanceCost cell
                        if (maintenanceCost.compareTo(new BigDecimal("0.0")) == 0) {
                            maintenanceCost = new BigDecimal("0.00");
                        } else {
                            maintenanceCost = maintenanceCost.setScale(2, BigDecimal.ROUND_HALF_UP);
                        }

                        System.out.println("TruckId: " + truckId + ". Date: " + datee + ". MaintenanceCost: " + maintenanceCost + ". ServiceProvider ID: " + serviceProviderId);

                        // Build and Persist the AnnualDataFleetFuel Object
                        MaintenanceSpendBySupplier maintenanceSpendBySupplier = createMaintenanceSpendBySupplierEntity(datee, maintenanceCost, truckId, serviceProviderId);
                        maintenanceSpendBySupplierService.persist(maintenanceSpendBySupplier);
                    }
                }
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n\n" + "File Not Found  in method testSheet()");
            System.out.println("" + e);
        } catch (IOException e) {
            System.out.println("\n\n" + "Problem Reading File: /setupfleet/TotalMaintenanceSpendSupplier.xls");
            System.out.println("" + e);
        }
    }

    public MaintenanceSpendBySupplier createMaintenanceSpendBySupplierEntity(Date datee, BigDecimal maintenanceCost, String truckId, String supplierId) {
        final MaintenanceSpendBySupplier maintenanceSpendBySupplier = new MaintenanceSpendBySupplier.Builder(datee)
                .supplierId(supplierId)
                .maintenanceCost(maintenanceCost)
                .truckId(truckId)
                .build();
        return maintenanceSpendBySupplier;
    }

//    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<MaintenanceSpendBySupplier> maintenanceSpendBySupplierList = maintenanceSpendBySupplierService.findAll();
        if (maintenanceSpendBySupplierList.isEmpty()) {
            System.out.println("\n\nNo MaintenanceSpendBySupplier FOUND in DB\n\n");
        } else {
            for (MaintenanceSpendBySupplier maintenanceSpendBySupplier : maintenanceSpendBySupplierList) {
                System.out.println("READING DATABASE - TruckId: " + maintenanceSpendBySupplier.getTruckId() + ". Date: " + maintenanceSpendBySupplier.getTransactionDate() + ". Maintenance Cost: " + maintenanceSpendBySupplier.getMaintenanceCost() + ". Supplier ID: " + maintenanceSpendBySupplier.getSupplierId());
            }
        }
    }
}
