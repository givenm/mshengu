/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.mileage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceMileage;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceMileageService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;
import zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecost.Setup_Annual_Data_Fleet_Maintenance_Cost;

/**
 *
 * @author ColinWa
 */
public class Setup_Annual_Data_Fleet_Maintenance_Mileage extends AppTest {

    @Autowired
    private AnnualDataFleetMaintenanceMileageService annualDataFleetMaintenanceMileageService;
    @Autowired
    private PersonService personService;
    @Autowired
    private TruckService truckService; //
    DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private static Truck truck = null;
    private static Person driver = null;
    private static String vehicleNumber;
    private static String employeeNumber;

//    @Test
    public void testSheet() {
        try {
            truckService = ctx.getBean(TruckService.class);
            annualDataFleetMaintenanceMileageService = ctx.getBean(AnnualDataFleetMaintenanceMileageService.class);
            personService = ctx.getBean(PersonService.class);

            StringTokenizer stringTokenizer;
            SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
            simpleFormat.applyPattern("dd-MMM-yyyy");

            // Open the Excel File
            URL url = this.getClass().getResource("/setupfleet/Fleet_Maintenance_Annual_Cost_&_Mileage.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book

            // Process each Sheet at a time. API FAILS to move to next sheets
            for (int j = 0; j <= 0; j++) { // First Sheet is 0 , second is 1 and so on
                Date datee = null;
                String truckId = null;
                String driverPersonId = null;
                Integer monthlyMileage;

                HSSFSheet worksheet = workbook.getSheetAt(j);

                for (int column = 1; column <= 16; column++) { // we will navigate Column 1 to 16
                    vehicleNumber = worksheet.getRow(26).getCell(column).getStringCellValue(); // Row 27 is the Vehicle Numbers for Mileage Section
                    employeeNumber = worksheet.getRow(28).getCell(column).toString().trim(); // Row 28 must have Employee Number instead of Employee full names

                    // Get the Truck based of the VEHICLEnUMBER
                    try {
                        truck = truckService.findByVehicleNumber(vehicleNumber);
                        // Set this as Start Milleage for Truck
                        if (truck != null) {
                            truckId = truck.getId();
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("\n\nNo Truck Found");
                    }
                    // Get personId
                    try {
                        driver = personService.findDriverWithEmployeeNumber(employeeNumber);
                        if (driver != null) {
                            driverPersonId = driver.getId();
                        }
                    } catch (NullPointerException ex) {
                        System.out.println("\n\nNo Driver Found");
                    }
                    //
                    if (truck != null && driver != null) {
                        // iterated through all the rows
                        System.out.println("\n\n" + "Begin processing Sheet: " + j + " i.e. " + vehicleNumber + ", Driver: " + employeeNumber + ", Driver Id: " + driverPersonId);
                        for (int i = 31; i <= 47; i++) { // // Row 32 to 48 e.g. if Row 1,  index is (0)  of the Mileage Columns

                            try {
                                datee = simpleFormat.parse(worksheet.getRow(i).getCell(0).toString().trim());  // String to Date
                            } catch (ParseException ex) {
                                Logger.getLogger(Setup_Annual_Data_Fleet_Maintenance_Cost.class.getName()).log(Level.SEVERE, null, ex);
                                List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageServiceList = annualDataFleetMaintenanceMileageService.findAll();
                                for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageServiceList) {
                                    System.out.println("READING DATABASE - TruckId: " + annualDataFleetMaintenanceMileage.getTruckId() + ". Date: " + annualDataFleetMaintenanceMileage.getTransactionMonth() + ". Maintenance Cost: " + annualDataFleetMaintenanceMileage.getMonthlyMileage() + ". Driver Person ID: " + annualDataFleetMaintenanceMileage.getDriverPersonId());
                                }
                            }

                            String mileageToken = worksheet.getRow(i).getCell(column).toString().trim();
                            stringTokenizer = new StringTokenizer(mileageToken, ".");
                            monthlyMileage = Integer.parseInt(stringTokenizer.nextElement().toString());
//                          monthlyMileage = new Integer(worksheet.getRow(i).getCell(column).getNumericCellValue()).intValue();// Get numeric values with ".getNumericCellValue()" // .toString().trim());

                            System.out.println("TruckId: " + truckId + ". Date: " + datee + ". Monthly Mileage: " + monthlyMileage + ". Driver Person ID: " + driverPersonId);

                            // Build and Persist the AnnualDataFleetFuel Object
                            AnnualDataFleetMaintenanceMileage annualData = createAnnualDataFleetMaintenanceMileageEntity(datee, monthlyMileage, truckId, driverPersonId);
                            annualDataFleetMaintenanceMileageService.persist(annualData);

//                    // Build and Update the Truck Object
//                    addTruckAnnualData(annualData);

                        }
                    }
                }
            }
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("\n\n" + "File Not Found  in function setupDailyInputs()");
            System.out.println("" + e);
        } catch (IOException e) {
            System.out.println("\n\n" + "Problem Reading File: /setupfleet/Fleet_Maintenance_Test_Case_Data.xls");
            System.out.println("" + e);
        }
    }

    public AnnualDataFleetMaintenanceMileage createAnnualDataFleetMaintenanceMileageEntity(Date datee, Integer monthlyMileage, String truckId, String driverPersonId) { //Integer closingMileage,
        final AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage = new AnnualDataFleetMaintenanceMileage.Builder(datee)
                .driverPersonId(driverPersonId)
                .monthlyMileage(monthlyMileage)
                .truckId(truckId)
                .build();
        return annualDataFleetMaintenanceMileage;
    }

//    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<AnnualDataFleetMaintenanceMileage> annualDataFleetMaintenanceMileageList = annualDataFleetMaintenanceMileageService.findAll();
        if (annualDataFleetMaintenanceMileageList.isEmpty()) {
            System.out.println("\n\nNo AnnualDataFleetMaintenanceMileage FOUND in DB\n\n");
        } else {
            for (AnnualDataFleetMaintenanceMileage annualDataFleetMaintenanceMileage : annualDataFleetMaintenanceMileageList) {
                System.out.println("READING DATABASE - TruckId: " + annualDataFleetMaintenanceMileage.getTruckId() + ". Date: " + annualDataFleetMaintenanceMileage.getTransactionMonth() + ". Monthly Mileage: " + annualDataFleetMaintenanceMileage.getMonthlyMileage() + ". Driver Person ID: " + annualDataFleetMaintenanceMileage.getDriverPersonId());
            }
        }
    }
}
