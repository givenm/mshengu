/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.fleetsetup.dailyinputs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import zm.hashcode.mshengu.domain.fleet.OperatingCost;
import zm.hashcode.mshengu.domain.fleet.ServiceCost;
import zm.hashcode.mshengu.domain.fleet.Truck;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.domain.people.Person;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.services.fleet.OperatingCostService;
import zm.hashcode.mshengu.services.fleet.TruckService;
import zm.hashcode.mshengu.services.people.PersonService;
import zm.hashcode.mshengu.test.AppTest;

/**
 *
 * @author geek
 */
public class Setup_DailyInputsSheetFourteen extends AppTest {

    @Autowired
    private OperatingCostService operatingCostService;
    @Autowired
    private PersonService personService;
    @Autowired
    private TruckService truckService; //
    DateTimeFormatHelper dateTimeFormatHelper = new DateTimeFormatHelper();
    private static Truck truck = null;
    private static TruckCategory truckCategory = null;
    private static Person driver = null;
    private static List<Site> routeList = new ArrayList<>(); //existingTruck.getRoutes();
    private static List<ServiceCost> serviceCostList = new ArrayList<>();
    private static List<OperatingCost> operatingCostList = new ArrayList<>();
    private static String vehicleNumber;
//    private static Double operatingSpec = 0.00; // in R/km
    private static Double manufacturingSpec = 0.00; // in Ltrs/Km
//    private static BigDecimal operationalAllowance; //
//    private static Integer fuelSpec;

    @Test
    public void testSheet() {
        try {
            operatingCostService = ctx.getBean(OperatingCostService.class);
            personService = ctx.getBean(PersonService.class);
            truckService = ctx.getBean(TruckService.class);
            StringTokenizer stringTokenizer;
            SimpleDateFormat simpleFormat = (SimpleDateFormat) DateFormat.getDateInstance();
            simpleFormat.applyPattern("dd-MMM-yyyy");

            // Open the Excel File
            URL url = this.getClass().getResource("/setupfleet/fleet_data_Mfg_Spec.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book

            // Process each Sheet at a time. API FAILS to move to next sheets
            for (int j = 13; j <= 13; j++) {
                // Get the first Sheet(0)
                HSSFSheet worksheet = workbook.getSheetAt(j); // First Sheet is 0 , second is 1 and so on

                vehicleNumber = worksheet.getRow(4).getCell(0).toString().trim(); // Row 6 Column A
                System.out.println("\n\n" + "Begin processing Sheet: " + j + " i.e. " + vehicleNumber);
//                operatingSpec = Double.parseDouble(worksheet.getRow(2).getCell(8).toString().trim());
                manufacturingSpec = Double.parseDouble(worksheet.getRow(2).getCell(10).toString().trim());
                if (manufacturingSpec > 0.0) {
                    manufacturingSpec *= 100;
                }

                // Get the Truck based of the VEHICLEnUMBER
                truck = truckService.findByVehicleNumber(vehicleNumber);
                // Set this as Start Milleage for Truck
                if (truck != null) {
                    truckCategory = truck.getCategory();

                    if (truck.getDriverId() != null) {
                        driver = personService.findById(truck.getDriverId());
                    }

                    try {
                        routeList.addAll(truck.getRoutes());
                    } catch (java.lang.NullPointerException ex) {
                    }
                    try {
                        serviceCostList.addAll(truck.getServiceCosts());
                    } catch (java.lang.NullPointerException ex) {
                    }

                    try {
                        operatingCostList.addAll(truck.getOperatingCosts());
                    } catch (java.lang.NullPointerException ex) {
                    }

//                    System.out.println("\n\n Start Mileage: " + worksheet.getRow(4).getCell(5).toString().trim());
                    String mileageStart = worksheet.getRow(4).getCell(5).toString().trim();
                    stringTokenizer = new StringTokenizer(mileageStart, ".");
                    Integer startMileage = Integer.parseInt(stringTokenizer.nextElement().toString());
//                    System.out.println("\n\n" + startMileage);
                    updateTruckStartMileageAndSpecs(startMileage);

                    //iterated through all the rows
                    for (int i = 5; i <= worksheet.getPhysicalNumberOfRows(); i++) {  // Row 6 is index(5)

                        Date datee = null;
                        try {
                            datee = simpleFormat.parse(worksheet.getRow(i).getCell(1).toString().trim());  // String to Date
//                            System.out.println("\n\n DATE CONVERTED IS: " + datee);
                        } catch (ParseException ex) {
                            Logger.getLogger(Setup_DailyInputSheetOne.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        String dateString = simpleFormat.format(datee); // Date to String

                        BigDecimal amount = new BigDecimal(worksheet.getRow(i).getCell(2).toString().trim()); // Row 6 Column C
                        Double litres = Double.parseDouble(worksheet.getRow(i).getCell(3).toString().trim()); // Row 6 Column D

                        BigDecimal randPerLitre = BigDecimal.ZERO;
                        if (amount.compareTo(new BigDecimal("0.0")) == 0) {
                            amount = new BigDecimal("0.00");
                            litres = Double.parseDouble("0.00");
                            randPerLitre = new BigDecimal("0.00");
                        } else {
                            randPerLitre = amount.divide(new BigDecimal(litres), 2, RoundingMode.HALF_UP); // amount.divide by litres // Row 5 Column E is a Calculation
                        }

                        String mileageToken = worksheet.getRow(i).getCell(5).toString().trim();
                        stringTokenizer = new StringTokenizer(mileageToken, ".");
                        Integer mileage = Integer.parseInt(stringTokenizer.nextElement().toString()); // Row 6 Column F
                        String employeeNumber = worksheet.getRow(i).getCell(6).toString().trim(); // Row 6 Column G
                        Person driverr = personService.findDriverWithEmployeeNumber(employeeNumber);
                        //
                        System.out.println("Spreadsheet Date: " + datee + ". Amount: " + amount + ". LItres: " + litres + ". RandPerLitre: " + randPerLitre + ". Mileage: " + mileage + ". Employee Num: " + employeeNumber + ". Driver: " + driverr.getFirstname() + " " + driverr.getLastname());


                        // Build and Persist the OperatingCost Object
                        OperatingCost operatingCost = createOperatingCostEntity(datee, amount, litres, BigDecimal.ZERO, new Double("0.00"), mileage, "0000", randPerLitre, driverr);
                        operatingCostService.persist(operatingCost);
                        // Build and Update the Truck Object
                        addTruckOperatingCost(operatingCost);

                    }
                }

            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n\n" + "File Not Found in function setupDailyInputs()");
            System.out.println("" + e);
        } catch (IOException e) {
            System.out.println("\n\n" + "Problem Reading File: /setupfleet/fleet_data_Mfg_Spec.xls");
            System.out.println("" + e);
        }

    }

    private OperatingCost createOperatingCostEntity(Date transactionDate, BigDecimal fuelCost, Double fuelLitres, BigDecimal oilCost, Double oilLitres, Integer speedometer, String slipNo, BigDecimal randPerLitre, Person driverr) {
        final OperatingCost operatingCosts = new OperatingCost.Builder(transactionDate)
                .fuelCost(fuelCost)
                .fuelLitres(fuelLitres)
                .oilCost(oilCost)
                .oilLitres(oilLitres)
                .speedometer(speedometer)
                .slipNo(slipNo)
                .randPerLitre(randPerLitre)
                .driver(driverr)
                .build();

        return operatingCosts;
    }

    private void addTruckOperatingCost(OperatingCost operatingCost) {
        operatingCostList.add(operatingCost);
        Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
                .operatingCosts(operatingCostList)
                .serviceCosts(serviceCostList)
                .trackerGPS(truck.getTrackerGPS())
                .brand(truck.getBrand())
                .category(truckCategory)
                .radioSerialNumber(truck.getRadioSerialNumber())
                .dateOfExpire(truck.getDateOfExpire())
                .description(truck.getDescription())
                .driver(driver)
                .engineNo(truck.getEngineNo())
                .isActive(truck.isIsActive())
                .vehicleNumber(truck.getVehicleNumber())
                .model(truck.getModel())
                .vehicleCost(truck.getVehicleCost())
                .registerYear(truck.getRegisterYear())
                .routes(routeList)
                .tare(truck.getTare())
                .vinNo(truck.getVinNo())
                .manufacturingSpec(truck.getManufacturingSpec())
                .operatingSpec(truck.getOperatingSpec())
                .operationalAllowance(truck.getOperationalAllowance())
                .startMileage(truck.getStartMileage())
                .fuelSpec(truck.getFuelSpec())
                .id(truck.getId())
                .build();
        truckService.merge(updatedTruck);
    }

    private void updateTruckStartMileageAndSpecs(Integer startMileage) {
        System.out.println("\nBefore Update Start Mileage = " + truck.getStartMileage());
        Truck updatedTruck = new Truck.Builder(truck.getNumberPlate())
                .operatingCosts(operatingCostList)
                .serviceCosts(serviceCostList)
                .trackerGPS(truck.getTrackerGPS())
                .brand(truck.getBrand())
                .category(truckCategory)
                .radioSerialNumber(truck.getRadioSerialNumber())
                .dateOfExpire(truck.getDateOfExpire())
                .description(truck.getDescription())
                .driver(driver)
                .engineNo(truck.getEngineNo())
                .isActive(truck.isIsActive())
                .vehicleNumber(truck.getVehicleNumber())
                .model(truck.getModel())
                .vehicleCost(truck.getVehicleCost())
                .registerYear(truck.getRegisterYear())
                .routes(routeList)
                .tare(truck.getTare())
                .vinNo(truck.getVinNo())
                .manufacturingSpec(manufacturingSpec)
                .operatingSpec(truck.getOperatingSpec())
                .operationalAllowance(truck.getOperationalAllowance())
                .startMileage(startMileage)
                .fuelSpec(truck.getFuelSpec())
                .id(truck.getId())
                .build();
        truckService.merge(updatedTruck);
        truck = truckService.findByVehicleNumber(vehicleNumber);
        System.out.println("\nAfter Update Start Mileage = " + truck.getStartMileage() + ". Opr Spec = " + truck.getOperatingSpec() + ". Mfg SPec = " + truck.getManufacturingSpec());
    }
}
