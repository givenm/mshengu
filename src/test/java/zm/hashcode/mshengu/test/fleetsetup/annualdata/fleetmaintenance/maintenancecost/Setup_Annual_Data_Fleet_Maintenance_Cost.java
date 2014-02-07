/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetmaintenance.maintenancecost;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import zm.hashcode.mshengu.domain.procurement.AnnualDataFleetMaintenanceCost;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.services.procurement.AnnualDataFleetMaintenanceCostService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author ColinWa
 */
public class Setup_Annual_Data_Fleet_Maintenance_Cost extends AppTest {

    @Autowired
    private AnnualDataFleetMaintenanceCostService annualDataFleetMaintenanceCostService;
    @Autowired
    private PersonService personService;
    @Autowired
    private TruckService truckService; //
    DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private static Truck truck = null;
    private static Person driver = null;
    private static String vehicleNumber;
    private static String employeeNumber;

    @Test
    public void testSheet() {
        try {
            truckService = ctx.getBean(TruckService.class);
            annualDataFleetMaintenanceCostService = ctx.getBean(AnnualDataFleetMaintenanceCostService.class);
            personService = ctx.getBean(PersonService.class);

//            StringTokenizer stringTokenizer;
            SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
            simpleFormat.applyPattern("dd-MMM-yyyy");

            // Open the Excel File
            URL url = this.getClass().getResource("/setupfleet/Fleet_Maintenance_Annual_Cost_&_Mileage.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book

            // Process each Sheet at a time. API FAILS to move to next sheets
            for (int j = 0; j <= 0; j++) { // // First Sheet is 0 , second is 1 and so on
                Date datee = null;
                String truckId = null;
                String driverPersonId = null;
                BigDecimal monthMaintenanceCost;

                HSSFSheet worksheet = workbook.getSheetAt(j);

                for (int column = 1; column <= 16; column++) { // we will navigate 16 columns
                    vehicleNumber = worksheet.getRow(2).getCell(column).getStringCellValue(); // getStringCellValue() returns result of fomula // DONT USE .toString().trim().;
                    employeeNumber = worksheet.getRow(4).getCell(column).toString().trim();

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
//                        for (int i = 4; i <= worksheet.getPhysicalNumberOfRows(); i++) { // if Row 6,  index is (5)
                        System.out.println("\n\n" + "Begin processing Sheet: " + j + " i.e. " + vehicleNumber + ", Driver: " + employeeNumber + ", Driver Id: " + driverPersonId);
                        for (int i = 8; i <= 24; i++) { // we will navigate Row 9 to 25 e.g. if Row 6,  index is (5)

                            try {
                                datee = simpleFormat.parse(worksheet.getRow(i).getCell(0).toString().trim());  // String to Date
                            } catch (ParseException ex) {
                                Logger.getLogger(Setup_Annual_Data_Fleet_Maintenance_Cost.class.getName()).log(Level.SEVERE, null, ex);
                                List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList = annualDataFleetMaintenanceCostService.findAll();
                                for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
                                    System.out.println("READING DATABASE - TruckId: " + annualDataFleetMaintenanceCost.getTruckId() + ". Date: " + annualDataFleetMaintenanceCost.getTransactionMonth() + ". Maintenance Cost: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost() + ". Driver Person ID: " + annualDataFleetMaintenanceCost.getDriverPersonId());
                                }
                            }
//
                            monthMaintenanceCost = new BigDecimal(worksheet.getRow(i).getCell(column).getNumericCellValue());// Get numeric values with ".getNumericCellValue()" // .toString().trim());

                            if (monthMaintenanceCost.compareTo(new BigDecimal("0.0")) == 0) {
                                monthMaintenanceCost = new BigDecimal("0.00");
                            }
//
//                            String mileageToken = worksheet.getRow(i).getCell(3).toString().trim();
//                            stringTokenizer = new StringTokenizer(mileageToken, ".");
//                            closingMileage = Integer.parseInt(stringTokenizer.nextElement().toString());
                            //
                            System.out.println("TruckId: " + truckId + ". Date: " + datee + ". Maintenance Cost: " + monthMaintenanceCost.setScale(2, RoundingMode.HALF_UP) + ". Driver Person ID: " + driverPersonId); // . Closing Mileage: " + closingMileage + "

                            // Build and Persist the AnnualDataFleetFuel Object
                            AnnualDataFleetMaintenanceCost annualData = createAnnualDataFleetMaintenanceCostEntity(datee, monthMaintenanceCost.setScale(2, RoundingMode.HALF_UP), truckId, driverPersonId); // closingMileage,
                            annualDataFleetMaintenanceCostService.persist(annualData);
                            // Build and Update the Truck Object
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

    public AnnualDataFleetMaintenanceCost createAnnualDataFleetMaintenanceCostEntity(Date datee, BigDecimal maintenanceCost, String truckId, String driverPersonId) { //Integer closingMileage,
        final AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost = new AnnualDataFleetMaintenanceCost.Builder(datee)
                .driverPersonId(driverPersonId)
                .monthlyMaintenanceCost(maintenanceCost)
                .truckId(truckId)
                .build();
        return annualDataFleetMaintenanceCost;
    }

    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<AnnualDataFleetMaintenanceCost> annualDataFleetMaintenanceCostList = annualDataFleetMaintenanceCostService.findAll();
        if (annualDataFleetMaintenanceCostList.isEmpty()) {
            System.out.println("\n\nNo AnnualDataFleetMaintenanceCost FOUND in DB\n\n");
        } else {
            for (AnnualDataFleetMaintenanceCost annualDataFleetMaintenanceCost : annualDataFleetMaintenanceCostList) {
                System.out.println("READING DATABASE - TruckId: " + annualDataFleetMaintenanceCost.getTruckId() + ". Date: " + annualDataFleetMaintenanceCost.getTransactionMonth() + ". Maintenance Cost: " + annualDataFleetMaintenanceCost.getMonthlyMaintenanceCost());
            }
        }
    }
}
