/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.annualdata.fleetfuel;

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
import zm.hashcode.mshengu.domain.fleet.AnnualDataFleetFuel;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.services.fleet.AnnualDataFleetFuelService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;
import zm.hashcode.mshengu.test.fleetsetup.dailyinputs.Setup_DailyInputSheetOne;

/**
 *
 * @author ColinWa
 */
public class Setup_Annual_Data_Sheet_Four extends AppTest {

    @Autowired
    private AnnualDataFleetFuelService annualDataService;
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
            annualDataService = ctx.getBean(AnnualDataFleetFuelService.class);
            personService = ctx.getBean(PersonService.class);

            StringTokenizer stringTokenizer;
            SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
            simpleFormat.applyPattern("dd-MMM-yyyy");

            // Open the Excel File
            URL url = this.getClass().getResource("/setupfleet/Fleet_Data_Annual.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book

            // Process each Sheet at a time. API FAILS to move to next sheets
            for (int j = 3; j <= 3; j++) {
                Date datee = null;
                String truckId = null;
                String driverPersonId = null;
                BigDecimal amount;
                Integer closingMileage;

                // Get the first Sheet(0)
                HSSFSheet worksheet = workbook.getSheetAt(j); // First Sheet is 0 , second is 1 and so on

                vehicleNumber = worksheet.getRow(4).getCell(0).toString().trim();
                employeeNumber = worksheet.getRow(4).getCell(4).toString().trim();
                System.out.println("\n\n" + "Begin processing Sheet: " + j + " i.e. " + vehicleNumber + ", Driver: " + employeeNumber);

                // Get the Truck based of the VEHICLEnUMBER
                truck = truckService.findByVehicleNumber(vehicleNumber);
                // Set this as Start Milleage for Truck
                if (truck != null) {
                    truckId = truck.getId();
                }

                // Get personId
                driver = personService.findDriverWithEmployeeNumber(employeeNumber);
                if (driver != null) {
                    driverPersonId = driver.getId();
                }

                if (truck != null && driver != null) {
                    // iterated through all the rows
                    for (int i = 4; i <= worksheet.getPhysicalNumberOfRows(); i++) { // if Row 6,  index is (5)
                        try {
                            datee = simpleFormat.parse(worksheet.getRow(i).getCell(1).toString().trim());  // String to Date
                        } catch (ParseException ex) {
                            Logger.getLogger(Setup_DailyInputSheetOne.class.getName()).log(Level.SEVERE, null, ex);
                            List<AnnualDataFleetFuel> annualDataFleetFuelList = annualDataService.findAll();
                            for (AnnualDataFleetFuel annualDataFuel : annualDataFleetFuelList) {
                                System.out.println("READING DATABASE - TruckId: " + annualDataFuel.getTruckId() + ". Date: " + annualDataFuel.getTransactionDate() + ". Amount: " + annualDataFuel.getMonthlyFuelCost() + ". Closing Mileage: " + annualDataFuel.getClosingMileage() + ". Driver Person ID: " + annualDataFuel.getDriverPersonId());
                            }
                        }
//
                        amount = new BigDecimal(worksheet.getRow(i).getCell(2).toString().trim());

                        if (amount.compareTo(new BigDecimal("0.0")) == 0) {
                            amount = new BigDecimal("0.00");
                        }
//
                        String mileageToken = worksheet.getRow(i).getCell(3).toString().trim();
                        stringTokenizer = new StringTokenizer(mileageToken, ".");
                        closingMileage = Integer.parseInt(stringTokenizer.nextElement().toString());
                        //
                        System.out.println("TruckId: " + truckId + ". Date: " + datee + ". Amount: " + amount.setScale(2, RoundingMode.HALF_UP) + ". Closing Mileage: " + closingMileage + ". Driver Person ID: " + driverPersonId);

                        // Build and Persist the AnnualDataFleetFuel Object
                        AnnualDataFleetFuel annualData = createAnnualDataEntity(datee, amount.setScale(2, RoundingMode.HALF_UP), closingMileage, truckId, driverPersonId);
                        annualDataService.persist(annualData);
//                    // Build and Update the Truck Object
//                    addTruckAnnualData(annualData);

                    }
                } else {
                    if (truck != null) {
                        System.out.println("Truck NOT FOUND");
                    }
                    if (driver != null) {
                        System.out.println("DRIVER NOT FOUND");
                    }
                }
            }
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("\n\n" + "File Not Found  in function setupDailyInputs()");
            System.out.println("" + e);
        } catch (IOException e) {
            System.out.println("\n\n" + "Problem Reading File: /setupfleet/Fleet_Data_Annual.xls");
            System.out.println("" + e);
        }
    }

    public AnnualDataFleetFuel createAnnualDataEntity(Date datee, BigDecimal amount, Integer closingMileage, String truckId, String driverPersonId) {
        final AnnualDataFleetFuel annualData = new AnnualDataFleetFuel.Builder(closingMileage)
                .driverPersonId(driverPersonId)
                .monthlyFuelCost(amount)
                .transactionDate(datee)
                .truckId(truckId)
                .build();
        return annualData;
    }

    @Test(dependsOnMethods = {"testSheet"})
    public void readDatabase() {
        List<AnnualDataFleetFuel> annualDataFleetFuelList = annualDataService.findAll();
        for (AnnualDataFleetFuel annualDataFuel : annualDataFleetFuelList) {
            System.out.println("READING DATABASE - TruckId: " + annualDataFuel.getTruckId() + ". Date: " + annualDataFuel.getTransactionDate() + ". Amount: " + annualDataFuel.getMonthlyFuelCost() + ". Closing Mileage: " + annualDataFuel.getClosingMileage() + ". Driver Person ID: " + annualDataFuel.getDriverPersonId());
        }
    }
}
