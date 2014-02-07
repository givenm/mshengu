/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.systemsetup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import zm.hashcode.mshengu.domain.customer.ContractType;
import zm.hashcode.mshengu.domain.fleet.TruckCategory;
import zm.hashcode.mshengu.domain.incident.IncidentType;
import zm.hashcode.mshengu.domain.products.UnitType;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderCategory;
import zm.hashcode.mshengu.domain.serviceprovider.ServiceProviderProductCategory;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.location.LocationType;
import zm.hashcode.mshengu.domain.ui.util.Country;
import zm.hashcode.mshengu.domain.ui.util.JobPosition;
import zm.hashcode.mshengu.domain.ui.util.PaymentMethod;
import zm.hashcode.mshengu.domain.ui.util.Sequence;
import zm.hashcode.mshengu.domain.ui.util.SequenceType;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.domain.ui.util.StatusType;
import zm.hashcode.mshengu.services.customer.ContractTypeService;
import zm.hashcode.mshengu.services.fleet.TruckCategoryService;
import zm.hashcode.mshengu.services.incident.IncidentTypeService;
import zm.hashcode.mshengu.services.products.UnitTypeService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceCategoryService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderCategoryService;
import zm.hashcode.mshengu.services.serviceprovider.ServiceProviderProductCategoryService;
import zm.hashcode.mshengu.services.ui.location.LocationService;
import zm.hashcode.mshengu.services.ui.location.LocationTypeService;
import zm.hashcode.mshengu.services.ui.util.CountryService;
import zm.hashcode.mshengu.services.ui.util.JobPositionService;
import zm.hashcode.mshengu.services.ui.util.PaymentMethodService;
import zm.hashcode.mshengu.services.ui.util.SequenceService;
import zm.hashcode.mshengu.services.ui.util.SequenceTypeService;
import zm.hashcode.mshengu.services.ui.util.StatusService;
import zm.hashcode.mshengu.services.ui.util.StatusTypeService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author Tiwana Siyabonga
 */
public class SetUp_00_Utils extends AppTest {

    @Autowired
    private ContractTypeService contractTypeService;
    @Autowired
    private PaymentMethodService paymentMethodService;
    @Autowired
    private StatusTypeService statusTypeService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private SequenceTypeService sequenceTypeService;
    @Autowired
    private SequenceService sequenceService;
    @Autowired
    private LocationTypeService locationTypeService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private UnitTypeService unitTypeService;
    @Autowired
    private TruckCategoryService truckCategoryService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private JobPositionService jobPositionService;
    @Autowired
    private IncidentTypeService incidentTypeService;
    @Autowired
    private ServiceCategoryService serviceCategoryService;
    @Autowired
    private ServiceProviderCategoryService serviceProviderCategoryService;
    @Autowired
    private ServiceProviderProductCategoryService serviceProviderProductCategoryService;

//    @Test
    public void setUp_00_ContractType() {

        try {

            contractTypeService = ctx.getBean(ContractTypeService.class);
            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String type = worksheet.getRow(i).getCell(0).toString().trim();
                ContractType contractType;

                contractType = contractTypeService.findByType(type);

                if (contractType == null) { //ignore if already loaded
                    contractType = new ContractType.Builder(type)
                            .build();
                    contractTypeService.persist(contractType);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_00_ContractType()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_01_PaymentMethods() {

        try {

            paymentMethodService = ctx.getBean(PaymentMethodService.class);
            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(1); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String methodName = worksheet.getRow(i).getCell(0).toString().trim();
                PaymentMethod paymentMethod;
//                System.out.println("paymentMethod -->> " + methodName);
                paymentMethod = paymentMethodService.findByPaymentMethod(methodName);

                if (paymentMethod == null) { //ignore if already loaded
                    paymentMethod = new PaymentMethod.Builder(methodName)
                            .build();
                    paymentMethodService.persist(paymentMethod);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_01_PaymentMethods()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_02_StatusType() {

        try {

            statusTypeService = ctx.getBean(StatusTypeService.class);
            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(2); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String statusName = worksheet.getRow(i).getCell(0).toString().trim();
                String statusTypeDescription = worksheet.getRow(i).getCell(1).toString().trim();
                StatusType statusType;
//                System.out.println("paymentMethod -->> " + methodName);
                statusType = statusTypeService.findByName(statusName);

                if (statusType == null) { //ignore if already loaded
                    statusType = new StatusType.Builder(statusName)
                            .description(statusTypeDescription)
                            .build();
                    statusTypeService.persist(statusType);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_02_SatusType()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_03_Satus() {

        try {

            statusService = ctx.getBean(StatusService.class);
            statusTypeService = ctx.getBean(StatusTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(3); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String statusTypeName = worksheet.getRow(i).getCell(0).toString().trim();
                String statusName = worksheet.getRow(i).getCell(1).toString().trim();

                StatusType statusType = statusTypeService.findByName(statusTypeName);

                Status status;
//                System.out.println("paymentMethod -->> " + methodName);
                status = statusService.findByName(statusName);

                if (status == null) { //ignore if already loaded
                    status = new Status.Builder(statusName)
                            .statusType(statusType)
                            .build();
                    statusService.persist(status);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_03_Satus()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_04_SequenceType() {

        try {

            sequenceTypeService = ctx.getBean(SequenceTypeService.class);
            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(4); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String sequenceName = worksheet.getRow(i).getCell(0).toString().trim();
                String sequenceTypeDescription = worksheet.getRow(i).getCell(1).toString().trim();
                SequenceType sequenceType;
//                System.out.println("paymentMethod -->> " + methodName);
                sequenceType = sequenceTypeService.findByName(sequenceName);

                if (sequenceType == null) { //ignore if already loaded
                    sequenceType = new SequenceType.Builder(sequenceName)
                            .description(sequenceTypeDescription)
                            .build();
                    sequenceTypeService.persist(sequenceType);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_02_SatusType()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_05_Sequence() {

        try {

            sequenceService = ctx.getBean(SequenceService.class);
            sequenceTypeService = ctx.getBean(SequenceTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(5); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String sequenceTypeName = worksheet.getRow(i).getCell(0).toString().trim();
                String sequenceName = worksheet.getRow(i).getCell(1).toString().trim();
                String sequenceCode = worksheet.getRow(i).getCell(2).toString().trim();
                int sequenceValue = 1; //Integer.parseInt(worksheet.getRow(i).getCell(3).toString().trim());

                SequenceType sequenceType = sequenceTypeService.findByName(sequenceTypeName);

                Sequence sequence;
//                System.out.println("paymentMethod -->> " + methodName);
                sequence = sequenceService.findByName(sequenceName);

                if (sequence == null) { //ignore if already loaded
                    sequence = new Sequence.Builder(sequenceName)
                            .sequenceType(sequenceType)
                            .namingCode(sequenceCode)
                            .value(sequenceValue)
                            .build();
                    sequenceService.persist(sequence);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_03_Satus()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_06_LocationType() {

        try {

            locationTypeService = ctx.getBean(LocationTypeService.class);
            URL url = this.getClass().getResource("/setupdata/Utils.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Location Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(6); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String name = worksheet.getRow(i).getCell(0).toString().trim();
                String code = worksheet.getRow(i).getCell(1).toString().trim();
                LocationType locationType;

                locationType = locationTypeService.findByName(name);

                if (locationType == null) { //ignore if already loaded
                    locationType = new LocationType.Builder(name)
                            .code(code)
                            .build();
                    locationTypeService.persist(locationType);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_06_LocationType()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_07_Country() {

        try {

            locationService = ctx.getBean(LocationService.class);
            locationTypeService = ctx.getBean(LocationTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Location.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                Set<Location> locationList = new HashSet<>();

                //   String locationParent = worksheet.getRow(i).getCell(0).toString().trim();
                //   System.out.println("locationParent -->> " + locationParent);
                String locationName = worksheet.getRow(i).getCell(1).toString().trim();
                System.out.println("locationName -->> -" + locationName + "-");
                String locationTypeName = worksheet.getRow(i).getCell(2).toString().trim();
                String latitude = worksheet.getRow(i).getCell(3).toString().trim();
                String longitude = worksheet.getRow(i).getCell(4).toString().trim();

                LocationType locationType = locationTypeService.findByName(locationTypeName);

                Location location;
//                System.out.println("paymentMethod -->> " + methodName);
                location = locationService.findByName(locationName);

                boolean sameType = false;
                if (location != null) { //ignore if already loaded

                    if (locationTypeName.equalsIgnoreCase(locationType.getName())) {
                        sameType = true;
                    }
                }
                if (!sameType) {
                    location = new Location.Builder(locationName)
                            .locationType(locationType)
                            .latitude(latitude)
                            .longitude(longitude)
                            .children(locationList)
                            .build();
                    locationService.persist(location);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_07_Country()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_08_Province() {

        try {

            locationService = ctx.getBean(LocationService.class);
            locationTypeService = ctx.getBean(LocationTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Location.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(1); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                Set<Location> locationList = new HashSet<>();

                String parentLocationName = worksheet.getRow(i).getCell(0).toString().trim();
                System.out.println("locationParent -->> -" + parentLocationName + "-");
                String locationName = worksheet.getRow(i).getCell(1).toString().trim();
                System.out.println("locationName -->> -" + locationName + "-");
                String locationTypeName = worksheet.getRow(i).getCell(2).toString().trim();
                String latitude = worksheet.getRow(i).getCell(3).toString().trim();
                String longitude = worksheet.getRow(i).getCell(4).toString().trim();

                LocationType locationType = locationTypeService.findByName(locationTypeName);

                Location location;
                Location parentLocation = locationService.findByName(parentLocationName);
//                System.out.println("paymentMethod -->> " + methodName);
                location = locationService.findByName(locationName);

                boolean sameType = false;
                if (location != null) { //ignore if already loaded

                    if (locationTypeName.equalsIgnoreCase(locationType.getName())) {
                        sameType = true;
                    }
                }
                if (!sameType) {//ignore if already loaded
                    location = new Location.Builder(locationName)
                            .locationType(locationType)
                            .latitude(latitude)
                            .longitude(longitude)
                            .children(locationList)
                            .parent(parentLocation)
                            .build();
                    locationService.persist(location);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_08_Province()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_09_Region() {

        try {

            locationService = ctx.getBean(LocationService.class);
            locationTypeService = ctx.getBean(LocationTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Location.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(2); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                Set<Location> locationList = new HashSet<>();

                String parentLocationName = worksheet.getRow(i).getCell(0).toString().trim();
                System.out.println("locationParent -->> -" + parentLocationName + "-");
                String locationName = worksheet.getRow(i).getCell(1).toString().trim();
                System.out.println("locationName -->> -" + locationName + "-");
                String locationTypeName = worksheet.getRow(i).getCell(2).toString().trim();
                String latitude = worksheet.getRow(i).getCell(3).toString().trim();
                String longitude = worksheet.getRow(i).getCell(4).toString().trim();

                LocationType locationType = locationTypeService.findByName(locationTypeName);

                Location location;
                Location parentLocation = locationService.findByName(parentLocationName);
//                System.out.println("paymentMethod -->> " + methodName);
                location = locationService.findByName(locationName);

                boolean sameType = false;
                if (location != null) { //ignore if already loaded

                    if (locationTypeName.equalsIgnoreCase(location.getLocationType().getName())) {
                        System.out.println("found location Type-->> -" + location.getLocationType().getName() + "-");

                        System.out.println("newlocation Type-->> -" + locationTypeName + "-");
                        sameType = true;
                    }
                }
                if (!sameType) {//ignore if already loaded
                    location = new Location.Builder(locationName)
                            .locationType(locationType)
                            .latitude(latitude)
                            .longitude(longitude)
                            .children(locationList)
                            .parent(parentLocation)
                            .build();
                    locationService.persist(location);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_09_Region()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_10_Suburb() {

        try {

            locationService = ctx.getBean(LocationService.class);
            locationTypeService = ctx.getBean(LocationTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Location.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(3); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                Set<Location> locationList = new HashSet<>();

                String parentLocationName = worksheet.getRow(i).getCell(0).toString().trim();
                System.out.println("locationParent -->> -" + parentLocationName + "-");
                String locationName = worksheet.getRow(i).getCell(1).toString().trim();
                System.out.println("locationName -->> -" + locationName + "-");
                String locationTypeName = worksheet.getRow(i).getCell(2).toString().trim();
                String latitude = worksheet.getRow(i).getCell(3).toString().trim();
                String longitude = worksheet.getRow(i).getCell(4).toString().trim();

                LocationType locationType = locationTypeService.findByName(locationTypeName);

                 List<Location> foundLocationList;
                Location parentLocation = locationService.findByName(parentLocationName);
//                System.out.println("paymentMethod -->> " + methodName);
                foundLocationList = locationService.findByName(locationName, "");

                boolean sameType = false;
                for(Location location : foundLocationList){
                //ignore if already loaded

                    if (locationTypeName.equalsIgnoreCase(location.getLocationTypeName())) {
                        System.out.println("found location Type-->> -" + location.getLocationTypeName() + "-");

                        System.out.println("newlocation Type-->> -" + locationTypeName + "-");
                        if (parentLocationName.equalsIgnoreCase(location.getParentLocationName())) {
                            sameType = true;
                            break;
                        }
                    }
                }
                if (!sameType) {//ignore if already loaded
                 Location   location = new Location.Builder(locationName)
                            .locationType(locationType)
                            .latitude(latitude)
                            .longitude(longitude)
                            .children(locationList)
                            .parent(parentLocation)
                            .build();
                    locationService.persist(location);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_10_Suburb()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_11_UnitType() {

        try {

//            statusService = ctx.getBean(StatusService.class);
            unitTypeService = ctx.getBean(UnitTypeService.class);

            URL url = this.getClass().getResource("/setupdata/Asset.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String unitTypeName = worksheet.getRow(i).getCell(0).toString().trim();
                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());

                UnitType unitType = unitTypeService.findByName(unitTypeName);


                if (unitType == null) { //ignore if already loaded
                    unitType = new UnitType.Builder(unitTypeName)
                            .unitPrice(unitPrice)
                            .build();
                    unitTypeService.persist(unitType);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_11_UnitType()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_12_VehicleCategory() {

        try {

//            statusService = ctx.getBean(StatusService.class);
            truckCategoryService = ctx.getBean(TruckCategoryService.class);

            URL url = this.getClass().getResource("/setupdata/Asset.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(1); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String vehicleName = worksheet.getRow(i).getCell(0).toString().trim();
//                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());

                TruckCategory vehicle = truckCategoryService.findByCategoryName(vehicleName);


                if (vehicle == null) { //ignore if already loaded
                    vehicle = new TruckCategory.Builder(vehicleName)
                            .build();
                    truckCategoryService.persist(vehicle);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_12_VehicleCategory()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_13_Nationality() {

        try {

//            statusService = ctx.getBean(StatusService.class);
            countryService = ctx.getBean(CountryService.class);

            URL url = this.getClass().getResource("/setupdata/HR.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String countryName = worksheet.getRow(i).getCell(0).toString().trim();
                String nationality = worksheet.getRow(i).getCell(1).toString().trim();

                Country country = countryService.findByName(countryName);


                if (country == null) { //ignore if already loaded
                    country = new Country.Builder(countryName)
                            .nationality(nationality)
                            .build();
                    countryService.persist(country);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_13_Nationality()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_14_Position() {

        try {

//            statusService = ctx.getBean(StatusService.class);
            jobPositionService = ctx.getBean(JobPositionService.class);

            URL url = this.getClass().getResource("/setupdata/HR.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(1); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String positionName = worksheet.getRow(i).getCell(0).toString().trim();
//                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());
                JobPosition vehicle = jobPositionService.findByName(positionName);

                if (vehicle == null) { //ignore if already loaded
                    vehicle = new JobPosition.Builder(positionName)
                            .build();
                    jobPositionService.persist(vehicle);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_14_Position()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_15_IncidentType() {

        try {
//            statusService = ctx.getBean(StatusService.class);
            incidentTypeService = ctx.getBean(IncidentTypeService.class);

            URL url = this.getClass().getResource("/setupdata/FieldServices.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String incidentTypeName = worksheet.getRow(i).getCell(0).toString().trim();
//                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());
                IncidentType vehicle = incidentTypeService.findByName(incidentTypeName);

                if (vehicle == null) { //ignore if already loaded
                    vehicle = new IncidentType.Builder(incidentTypeName)
                            .build();
                    incidentTypeService.persist(vehicle);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_15_IncidentType()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_16_ServiceProviderCategory() {

        try {
//            statusService = ctx.getBean(StatusService.class);
            serviceProviderCategoryService = ctx.getBean(ServiceProviderCategoryService.class);

            URL url = this.getClass().getResource("/setupdata/Procurement.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String serviceProviderCategoryeName = worksheet.getRow(i).getCell(0).toString().trim();
//                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());
                ServiceProviderCategory serviceProviderCategory = serviceProviderCategoryService.findByCategoryName(serviceProviderCategoryeName);

                if (serviceProviderCategory == null) { //ignore if already loaded
                    serviceProviderCategory = new ServiceProviderCategory.Builder(serviceProviderCategoryeName)
                            .build();
                    serviceProviderCategoryService.persist(serviceProviderCategory);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_16_ServiceProviderCategory()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_17_ServiceProviderProductCategory() {

        try {
//            statusService = ctx.getBean(StatusService.class);
            serviceProviderProductCategoryService = ctx.getBean(ServiceProviderProductCategoryService.class);

            URL url = this.getClass().getResource("/setupdata/Procurement.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(1); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String serviceProviderProductCategoryName = worksheet.getRow(i).getCell(0).toString().trim();
//                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());
                ServiceProviderProductCategory vehicle = serviceProviderProductCategoryService.findByCategoryName(serviceProviderProductCategoryName);

                if (vehicle == null) { //ignore if already loaded
                    vehicle = new ServiceProviderProductCategory.Builder(serviceProviderProductCategoryName)
                            .build();
                    serviceProviderProductCategoryService.persist(vehicle);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_17_ServiceProviderProductCategory()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }

//    @Test
    public void setUp_18_ServiceCategory() {

        try {
//            statusService = ctx.getBean(StatusService.class);
            serviceCategoryService = ctx.getBean(ServiceCategoryService.class);

            URL url = this.getClass().getResource("/setupdata/Procurement.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(2); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String serviceCategoryName = worksheet.getRow(i).getCell(0).toString().trim();
//                BigDecimal unitPrice = new BigDecimal(worksheet.getRow(i).getCell(1).toString().trim());
                ServiceCategory vehicle = serviceCategoryService.findByName(serviceCategoryName);

                if (vehicle == null) { //ignore if already loaded
                    vehicle = new ServiceCategory.Builder(serviceCategoryName)
                            .build();
                    serviceCategoryService.persist(vehicle);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function setUp_18_ServiceCategory()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }

    }
//    @Test
//    public void setUp_01_Countries() {
//
//        try {
//
//            locationService = ctx.getBean(LocationService.class);;
//            URL url = this.getClass().getResource("/setupfiles/Country.xls");
//            FileInputStream fileInputStream = new FileInputStream(url.getFile());
//            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
//            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
//            //iterated through all the rows
//            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
//
//                String name = worksheet.getRow(i).getCell(0).toString().trim();
//
//                Location location = new Location.Builder(name).
//                Title title = new Title.Builder(name).build();
//                titleService.persist(title);
//            }
//        } catch (FileNotFoundException e) {
//        } catch (IOException e) {
//        }
//
//    }
}
