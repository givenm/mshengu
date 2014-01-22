/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zm.hashcode.mshengu.test.systemsetup;

import com.google.common.collect.Collections2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import zm.hashcode.mshengu.app.facade.ui.util.StatusFacade;
import zm.hashcode.mshengu.app.util.DateTimeFormatHelper;
import zm.hashcode.mshengu.app.util.predicates.location.ChildLocationNamePredicate;
import zm.hashcode.mshengu.domain.customer.Contract;
import zm.hashcode.mshengu.domain.customer.ContractType;
import zm.hashcode.mshengu.domain.customer.Customer;
import zm.hashcode.mshengu.domain.customer.Invoice;
import zm.hashcode.mshengu.domain.customer.Order;
import zm.hashcode.mshengu.domain.people.ContactPerson;
import zm.hashcode.mshengu.domain.products.Site;
import zm.hashcode.mshengu.domain.products.SiteServiceContractLifeCycle;
import zm.hashcode.mshengu.domain.products.SiteServiceLog;
import zm.hashcode.mshengu.domain.products.SiteUnit;
import zm.hashcode.mshengu.domain.ui.location.Address;
import zm.hashcode.mshengu.domain.ui.location.Location;
import zm.hashcode.mshengu.domain.ui.util.Status;
import zm.hashcode.mshengu.services.customer.ContractService;
import zm.hashcode.mshengu.services.customer.ContractTypeService;
import zm.hashcode.mshengu.services.customer.CustomerService;
import zm.hashcode.mshengu.services.people.ContactPersonService;
import zm.hashcode.mshengu.services.products.SiteService;
import zm.hashcode.mshengu.services.products.SiteServiceContractLifeCycleService;
import zm.hashcode.mshengu.services.ui.location.AddressService;
import zm.hashcode.mshengu.services.ui.location.LocationService;
import zm.hashcode.mshengu.test.AppTest;
import static zm.hashcode.mshengu.test.AppTest.ctx;

/**
 *
 * @author boniface
 */
public class SetUp_01_Client extends AppTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private ContactPersonService contactPersonService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private ContractTypeService contractTypeService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private SiteServiceContractLifeCycleService siteServiceContractLifeCycleService;
    DateTimeFormatHelper dateTimeFormatHelper;
    private String customerId;
    private String contactPersonId;
    private String contractId;
    private String prevCustomerName;
    int counter = 1;

//    @Test
    public void client_00_CreateCustomerContactPerson() {
        try {


            contactPersonService = ctx.getBean(ContactPersonService.class);

            URL url = this.getClass().getResource("/setupdata/Customer.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                String fName = worksheet.getRow(i).getCell(0).toString().trim();
                String sName = worksheet.getRow(i).getCell(1).toString().trim();
                String email = worksheet.getRow(i).getCell(2).toString().trim();
                String mainNumber = worksheet.getRow(i).getCell(3).toString().trim();
                String otherNumber = worksheet.getRow(i).getCell(4).toString().trim();
                String Position = worksheet.getRow(i).getCell(5).toString().trim();
                String address = worksheet.getRow(i).getCell(6).toString().trim();

                String parentName = worksheet.getRow(i).getCell(7).toString().trim();


                ContactPerson contractPerson = new ContactPerson.Builder(fName, sName)
                        .emailAddress(email)
                        .mainNumber(mainNumber)
                        .otherNumber(otherNumber)
                        .position(Position)
                        .address1(address)
                        .parentId(parentName)
                        .build();

                contactPersonService.persist(contractPerson);
                contactPersonId = contractPerson.getId();
//        return contractPerson
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function client_00_CreateCustomerContactPerson()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }
    }

//    @Test(dependsOnMethods = {"client_00_CreateCustomerContactPerson"})
    public void client_01_CreateCustomerContract() {


//        return contract;
        try {


            contractService = ctx.getBean(ContractService.class);
            contractTypeService = ctx.getBean(ContractTypeService.class);

            DecimalFormat decFormate = new DecimalFormat("0");
            URL url = this.getClass().getResource("/setupdata/Customer.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(1); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {


                new java.text.DecimalFormat("0").format(worksheet.getRow(i).getCell(0).getNumericCellValue());

                int sDay = (int) worksheet.getRow(i).getCell(0).getNumericCellValue();

                int sMonth = (int) worksheet.getRow(i).getCell(1).getNumericCellValue();
                int sYear = (int) worksheet.getRow(i).getCell(2).getNumericCellValue();
                int eDay = (int) worksheet.getRow(i).getCell(3).getNumericCellValue();
                int eMonth = (int) worksheet.getRow(i).getCell(4).getNumericCellValue();
                int eYear = (int) worksheet.getRow(i).getCell(5).getNumericCellValue();


                String contractTypeName = worksheet.getRow(i).getCell(6).toString().trim();
                int numbeOfUnits = (int) worksheet.getRow(i).getCell(7).getNumericCellValue();
                BigDecimal price = new BigDecimal(worksheet.getRow(i).getCell(8).toString().trim());
                String statusName = worksheet.getRow(i).getCell(9).toString().trim();
                String parentName = worksheet.getRow(i).getCell(10).toString().trim();



                dateTimeFormatHelper = new DateTimeFormatHelper();
                Date startDate = dateTimeFormatHelper.getDate(sDay, sMonth, sYear);
                Date endDate = dateTimeFormatHelper.getDate(eDay, eMonth, eYear);
                ContractType contractType = contractTypeService.findByType(contractTypeName);
                Status status = StatusFacade.getStatusService().findById(statusName);
                
                Contract contract = new Contract.Builder(startDate)
                        .contractType(contractType)
                        .endDate(endDate)
                        .numberOfUnits(numbeOfUnits)
                        .pricePerUnit(price)
                        .parentId(parentName)
                        .status(statusName)
                        .build();

                contractService.persist(contract);
                contractId = contract.getId();
//        return contractPerson;
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function client_01_CreateCustomerContract()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }
    }

//    @Test(dependsOnMethods = {"client_01_CreateCustomerContract"})
    public void client_02_CreateCustomer() {


//        return contract;
        try {


            customerService = ctx.getBean(CustomerService.class);
            contractService = ctx.getBean(ContractService.class);
            contactPersonService = ctx.getBean(ContactPersonService.class);

            URL url = this.getClass().getResource("/setupdata/Customer.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(2); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

                String name = worksheet.getRow(i).getCell(0).toString().trim();

                Customer customer = customerService.findByName(name);
                if (customer == null) {
                    Set<Invoice> invoiceList = new HashSet<>();
                    Set<Order> orderList = new HashSet<>();
                    Set<Site> siteList = new HashSet<>();
                    Set<Contract> contractList = new HashSet<>();


                    ContactPerson contactPerson = contactPersonService.findByParentId(name);
                    Contract contract = contractService.findByParentId(name);
                    contractList.add(contract);

                    customer = new Customer.Builder(name)
                            .contactPerson(contactPerson)
                            .contracts(contractList)
                            .invoices(invoiceList)
                            .orders(orderList)
                            .sites(siteList)
                            .build();

                    customerService.persist(customer);
                }
                customerId = customer.getId();
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function client_02_CreateCustomer()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }
    }

//    @Test (dependsOnMethods = {"client_02_CreateCustomer"})
    public void client_03_CreateCustomerSites() {


//        return contract;
        try {


            contractService = ctx.getBean(ContractService.class);
            contractTypeService = ctx.getBean(ContractTypeService.class);

            URL url = this.getClass().getResource("/setupdata/ServiceSchedule4.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows
            for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
                System.out.println("counter-" + counter + "");
                i = counter;

                System.out.println("i-" + i + "");
                System.out.println("getPhysicalNumberOfRows-" + worksheet.getPhysicalNumberOfRows() + "-");

                String customerName = worksheet.getRow(i).getCell(0).toString().trim();

                Customer customer = findCustomer(customerName);
                if (customer != null) {
                    System.out.println("Customer " + customerName + " found");

                    Set<Site> combinedSiteList = new HashSet<>();

                    combinedSiteList.addAll(customer.getSites());

                    if (counter < worksheet.getPhysicalNumberOfRows()) {
                        Set<Site> newSiteList = saveSitve(customer.getId(), customerName, i);
                        combinedSiteList.addAll(newSiteList);
                    }
                    Customer customer2 = new Customer.Builder(customerName)
                            .customer(customer)
                            .sites(combinedSiteList)
                            .build();


                    customerService.merge(customer2);

                    // skip already added sites

                } else {
                    System.out.println("Customer " + customerName + "Not found");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function client_01_CreateCustomerContract()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }
    }
//    @Test(dependsOnMethods = {"client_01_CreateCustomerContract"})

    private Set<Site> saveSitve(String customerId, String customerNameIn, int startIndex) {

        Set<Site> siteList = new HashSet<>();
        siteService = ctx.getBean(SiteService.class);

        try {
            URL url = this.getClass().getResource("/setupdata/ServiceSchedule4.xls");
            FileInputStream fileInputStream = new FileInputStream(url.getFile());
            HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream); //Open Spreadsheet Work book
            //Get Contract Type Sheet(0)
            HSSFSheet worksheet = workbook.getSheetAt(0); // First Sheet is 0 , second is 2 and so on
            //iterated through all the rows


            System.out.println("startIndex-" + startIndex + "");
            for (int i = startIndex; i < worksheet.getPhysicalNumberOfRows(); i++) {
                boolean siteFound = false;

                System.out.println("i-" + i + "-");
                System.out.println("getPhysicalNumberOfRows-" + worksheet.getPhysicalNumberOfRows() + "-");

                String customerName = worksheet.getRow(i).getCell(0).toString().trim();
                String siteName = worksheet.getRow(i).getCell(1).toString().trim();
                String suburbName = worksheet.getRow(i).getCell(2).toString().trim();
                String regionName = worksheet.getRow(i).getCell(3).toString().trim();

                if (customerName.equalsIgnoreCase(customerNameIn)) {
                    List<Site> siteFoundList = findSite(siteName);

                    Location region = findRegion(regionName);
                    Location suburb = findSuburb(regionName, suburbName);

                    if (suburb == null) {
                        System.out.println("Suburb-" + suburbName + "-not found");
                    } else {
                        System.out.println("Suburb -" + suburb.getName() + "-");
                    }

                    System.out.println("Location -" + suburbName + "-");
                    System.out.println("Location -" + regionName + "-");
//                    System.out.println("Suburb " + suburb.getName() + " Parent should be " + region.getName());
//                    System.out.println("Location " + suburb.getName() + " Parent IS be " + locationNullCheckHelper.getNameNullCheck(suburb.getParent()));

                    String suburbName2 = suburb.getName();
                    String regionName2 = suburb.getParentLocationName();

//                    for (Site site : siteFoundList) {
//                        if ((suburbName.equalsIgnoreCase(site.getLocationName())) && (regionName.equalsIgnoreCase(getParentLocationName(site.getLocation())))) {
//                            if ((site.getParentId().equalsIgnoreCase(customerId))) {
//                                System.out.println("Site " + siteName + "located in " + suburbName + " " + regionName + " already exists");
//                                siteFound = true;
//                            } else {
//                            }
//                        }
//
//                    }
                    if (siteFoundList.size() > 0) {
                        System.out.println("Site " + siteName + "located in " + suburbName + " " + regionName + " already exists");

                        siteFound = true;
                    }

                    if (!siteFound) {
                        System.out.println("New Site " + siteName + "located in " + suburbName + " " + regionName + " will be added");

                        int expectedNoOfUnits = (int) worksheet.getRow(i).getCell(4).getNumericCellValue();
//                        int servicesPerMonth = (int) worksheet.getRow(i).getCell(5).getNumericCellValue();

                        boolean active = true;//worksheet.getRow(i).getCell(6).getBooleanCellValue();
                        boolean monday = worksheet.getRow(i).getCell(7).getBooleanCellValue();
                        boolean tuesday = worksheet.getRow(i).getCell(8).getBooleanCellValue();
                        boolean wednesday = worksheet.getRow(i).getCell(9).getBooleanCellValue();
                        boolean thursday = worksheet.getRow(i).getCell(10).getBooleanCellValue();
                        boolean friday = worksheet.getRow(i).getCell(11).getBooleanCellValue();
                        boolean saturday = worksheet.getRow(i).getCell(12).getBooleanCellValue();
                        boolean sunday = worksheet.getRow(i).getCell(13).getBooleanCellValue();
                        Date actionDate = worksheet.getRow(i).getCell(14).getDateCellValue();
                        String StreetAddress = worksheet.getRow(i).getCell(15).toString().trim();

                        Address address = saveAddress(StreetAddress);
                        Set<SiteServiceLog> siteServiceLogs = new HashSet<>();
                        Set<SiteServiceContractLifeCycle> serviceContractLifeCyclesList = new HashSet<>();

                        SiteServiceContractLifeCycle serviceContractLifeCycle = createSiteContract(actionDate, expectedNoOfUnits, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
                        serviceContractLifeCyclesList.add(serviceContractLifeCycle);

                        Site newSite = new Site.Builder(siteName)
                                .address(address)
                                .location(suburb)
                                .status("ACTIVE")
                                .active(active)
                                .parentId(customerId)
                                .siteServiceLog(siteServiceLogs)
                                .siteServiceContractLifeCycle(serviceContractLifeCyclesList)
                                .build();
                        siteService.persist(newSite);
                        siteList.add(newSite);

                        System.out.println("Site " + siteName + " saved");
                        //                                .address(null)
                        //                                .contactPerson(null)
                        //                        Site site = new Site.Builder(siteName)
                        //                              .address(null)
                    }
                    counter = i;
                } else { //next client

                    counter = i--;

                    System.out.println("move from customer " + customerNameIn + " to costomer " + customerName);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "File Not Found in function client_01_CreateCustomerContract()");
        } catch (IOException e) {
            System.out.println("" + e);
            System.out.println("\n\n" + "Problem Reading File");
        }
        return siteList;


    }

    private SiteServiceContractLifeCycle createSiteContract(Date actionDate, int expectedNoOfUnits,
            boolean monday, boolean tuesday, boolean wednesday,
            boolean thursday, boolean friday, boolean saturday, boolean sunday) {

        siteServiceContractLifeCycleService = ctx.getBean(SiteServiceContractLifeCycleService.class);

        Set<SiteUnit> siteUniList = new HashSet<>();
        SiteServiceContractLifeCycle siteServiceContractLifeCycle = new SiteServiceContractLifeCycle.Builder(actionDate)
                .expectedNumberOfUnits(expectedNoOfUnits)
                .monday(monday)
                .tuesday(tuesday)
                .wednesday(wednesday)
                .thursday(thursday)
                .friday(friday)
                .saturday(saturday)
                .sunday(sunday)
                .siteUnit(siteUniList)
                .build();

        siteServiceContractLifeCycleService.persist(siteServiceContractLifeCycle);
        return siteServiceContractLifeCycle;
    }

    private Customer findCustomer(String customerName) {
        customerService = ctx.getBean(CustomerService.class);
        Customer customer = customerService.findByName(customerName);
        return customer;
    }

    private List<Site> findSite(String siteName) {
        siteService = ctx.getBean(SiteService.class);
        List<Site> siteList = siteService.findByName(siteName, "");
        return siteList;

    }

    private Location findRegion(String locationName) {
        locationService = ctx.getBean(LocationService.class);
        Location location = locationService.findByName(locationName);
        return location;
    }

    private Location findSuburb(String regionName, String suburbName) {
        locationService = ctx.getBean(LocationService.class);
        List<Location> locations = locationService.findAll();
        Location suburb = null;

//        System.out.println("Child Location In -" + suburbName + "-");
//        System.out.println("Parent Location In -" + regionName + "-");
        Collection<Location> childLocations = Collections2.filter(locations, new ChildLocationNamePredicate(regionName));

        System.out.println("Number of Child Locations -" + childLocations.size() + "-");
        for (Location location : childLocations) {
//            System.out.println("Child Location Out -" + location.getName() + "-");
            if (suburbName.equalsIgnoreCase(location.getName())) {
                suburb = location;
                System.out.println("Child Location -" + location.getName() + "- located at -" + regionName + "-was found");
                break;
            }
        }
        return suburb;
    }

    private Address saveAddress(String streetAddress) {
        addressService = ctx.getBean(AddressService.class);
        final Address address = new Address.Builder(streetAddress)
                .postalCode("")
                .build();

        addressService.persist(address);
        return address;
    }

    private String getParentLocationName(Location location) {
        if (location != null) {
            return location.getParentLocationName();
        } else {
            return null;
        }
    }
}
//    // @Test
//    public void createRole() {
//        roleService = ctx.getBean(RoleService.class);
//        Role role = new Role.Builder("ROLE_ADMIN").description("System Administrator").build();
//        roleService.persist(role);
//        roleId = role.getId();
//    }
//
//    //@Test(dependsOnMethods = {"createRole"})
//    public void craeteUser() {
//        roleService = ctx.getBean(RoleService.class);
//        personService = ctx.getBean(PersonService.class);
//
//        Role role = roleService.findById(roleId);
//        Set<Role> roles = new HashSet<>();
//        roles.add(role);
//        String password = PasswordEncrypt.encrypt("admin");
//        Person user = new Person.Builder("Admin")
//                .username("admin")
//                .password(password)
//                .role(roles)
//                .enable(true)
//                .firstname("Administrator")
//                .build();
//
//        personService.persist(user);
//    }

